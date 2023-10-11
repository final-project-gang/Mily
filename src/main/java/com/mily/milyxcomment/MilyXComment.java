package com.mily.milyxcomment;

import com.mily.milyx.MilyX;
import com.mily.user.MilyUser;
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

    @ManyToOne
    private MilyUser milyUser;

    @ManyToOne
    @JoinColumn(name = "milyx_id")
    private MilyX milyX;
}