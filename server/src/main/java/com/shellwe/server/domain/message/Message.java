package com.shellwe.server.domain.message;

import com.shellwe.server.domain.member.entity.Member;
import com.shellwe.server.domain.room.Room;
import com.shellwe.server.utils.TimeTracker;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Message extends TimeTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private Long id;

    @Column
    private String payload;

    @Column(columnDefinition = "boolean default true")
    private boolean unread;

    @Column
    private boolean notification;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}