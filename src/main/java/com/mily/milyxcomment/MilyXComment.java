package com.mily.milyxcomment;

import com.mily.milyx.MilyX;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MilyXComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createDate;

//    @ManyToOne
//    private Member memberNickName;
//    멤버 구현 시 사용 가능 + 온도

    @ManyToOne
    @JoinColumn(name = "milyx_id")
    private MilyX milyX;
}