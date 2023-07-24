package com.shellwe.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "SHELL")
@Entity
public class Shell extends TimeTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHELL_ID")
    private Long id;

    @Column(name = "TITLE", length = 60)
    @Size(max = 30)
    private String title;

    @Column(name = "BODY", columnDefinition = "TEXT")
    @Size(max = 1500)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
