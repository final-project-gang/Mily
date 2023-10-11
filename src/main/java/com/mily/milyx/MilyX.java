package com.mily.milyx;

import com.mily.milyxcomment.MilyXComment;
import com.mily.user.MilyUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class MilyX {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createDate;

    @ManyToOne
    private MilyUser milyUser;

    @OneToMany(mappedBy = "milyX", cascade = CascadeType.REMOVE)
    private List<MilyXComment> mxc;
    // 변호사 답변 기능
}