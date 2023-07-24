package com.shellwe.server.domain.memberRoom;

import com.shellwe.server.domain.member.entity.Member;
import com.shellwe.server.domain.room.Room;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class MemberRoom {
    public MemberRoom(Room room) {
        this.room = room;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ROOM_ID")
    private Long id;

    @Column
    private boolean active = true;

    @Column
    private Long myShellId;

    @Column
    private Long traderShellId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "room_id")
    private Room room;
}