package com.mily.article.milyx.comment;

import com.mily.article.milyx.MilyX;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
public class MilyXComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String createDate;

    private String modifyDate;

    @Column(columnDefinition = "TEXT")
    private String body;

//    @ManyToOne
//    private MilyLawyer author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milyx_id")
    private MilyX milyX;
}