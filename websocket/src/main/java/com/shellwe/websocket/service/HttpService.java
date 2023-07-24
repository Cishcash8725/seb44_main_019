package com.shellwe.websocket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shellwe.websocket.dto.ChatRoom;
import com.shellwe.websocket.dto.ResponseDto;
import com.shellwe.websocket.dto.RoomDto;
import com.shellwe.websocket.entity.*;
import com.shellwe.websocket.exception.businessLogicException.BusinessLogicException;
import com.shellwe.websocket.exception.businessLogicException.ExceptionCode;
import com.shellwe.websocket.mapper.RoomMapper;
import com.shellwe.websocket.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class HttpService extends com.shellwe.websocket.service.Service {
    private final AsyncService asyncService;
    public HttpService(MemberRoomRepository memberRoomRepository,
                       MemberRepository memberRepository,
                       RoomRepository roomRepository,
                       MessageRepository messageRepository,
                       RoomMapper roomMapper,
                       ShellRepository shellRepository,
                       AsyncService asyncService) {
        super(memberRoomRepository, memberRepository, roomRepository, messageRepository, roomMapper,shellRepository);
        this.asyncService = asyncService;
    }

    public List<RoomDto.Response> findAllRoom() {
        long myId = getLoggedInMemberId();
        List<MemberRoom> memberRooms = memberRoomRepository.findAllMyRoomsWithSeller(myId);

        List<RoomDto.Response> response = memberRooms.stream().map(mr->{
            Long unreadCount = messageRepository.unReadCount(mr.getRoom().getId(), myId);
            Message message = messageRepository.findFirstByRoomOrderByIdDesc(mr.getRoom());
            return roomMapper.memberRoomToWsResponse(mr, unreadCount, message.getPayload(), message.getCreatedAt());
        }).sorted(Comparator.comparing(RoomDto.Response::getLastMessageCreatedAt).reversed()).collect(Collectors.toList());

        return response;
    }

    public void deleteRoom(long roomId){
        long memberId = getLoggedInMemberId();

        MemberRoom memberRoom = findExistsMemberRoom(roomId, memberId);

        memberRoom.setActive(false);
        memberRoomRepository.save(memberRoom);
    }
    public ResponseDto createRoom(RoomDto.Post requestBody) {
        long myShellId = requestBody.getMyShellId();
        long traderShellId = requestBody.getSellerShellId();

        verifyExistsMemberRoom(myShellId, traderShellId);

        Room room = roomRepository.save(new Room());
        long myId = getLoggedInMemberId();
        long traderId = requestBody.getSellerMemberId();

        // 생성된 룸과 멤버들 연결
        linkMemberToRoom(room, myId, myShellId, traderShellId);
        // 상대 멤버 존재하는지 체크
        linkMemberToRoom(room, traderId, traderShellId, myShellId);

        // 생성된 룸에 상품정보 메세지 생성
        createInitMessage(room,myId,myShellId);
        createInitMessage(room,traderId,traderShellId);

        // 해당 거래 요청 제안 삭제 비동기
        asyncService.deleteTrade(myShellId, traderShellId);

        // 프론트엔드와 상의 후 response 다시 정의
        return ResponseDto.builder()
                .roomsUrl("http://localhost:8080/chat")
                .roomUrl("ws://localhost:8080/chat?roomId="+ room.getId())
                .build();
    }

    private void linkMemberToRoom(Room room, long memberId, long myShellId, long traderShellId){
        MemberRoom memberRoom = new MemberRoom();
                memberRoom.setMember(new Member(memberId));
                memberRoom.setMyShellId(myShellId);
                memberRoom.setTraderShellId(traderShellId);
                memberRoom.setRoom(room);
        memberRoomRepository.save(memberRoom);
    }

    private void createInitMessage(Room room, long memberId, long shellId){
        Shell shell = findExistsShell(shellId);
        Member member = findExistsMember(memberId);
        Message message = new Message();
                message.setRoom(room);
                message.setNotification(true);
                message.setPayload(member.getDisplayName()+"님께서 거래하실 Shell : " + shell.getTitle());
        messageRepository.save(message);
    }
}