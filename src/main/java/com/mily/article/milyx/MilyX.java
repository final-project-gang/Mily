package com.mily.article.milyx;

import com.mily.article.milyx.category.entity.FirstCategory;
import com.mily.article.milyx.category.entity.SecondCategory;
import com.mily.user.MilyUser;
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
public class MilyX {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String createDate;

    private String modifyDate;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    private MilyUser author;

    @ManyToOne
    private FirstCategory firstCategory;

    @ManyToOne
    private SecondCategory secondCategory;
}