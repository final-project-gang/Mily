package com.mily.article.milyx.repository;

import com.mily.article.milyx.MilyX;
import com.mily.board.Board;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import static com.mily.article.milyx.QMilyX.milyX;

@RequiredArgsConstructor
public class MilyXRepositoryImpl implements MilyXRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MilyX> findByKw(Board board, String kwType, String kw, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(milyX.board.eq(board));

        switch (kwType) {
            case "subject" -> builder.and(milyX.subject.containsIgnoreCase(kw));
            case "body" -> builder.and(milyX.body.containsIgnoreCase(kw));
            case "nickname" -> builder.and(milyX.author.userNickName.containsIgnoreCase(kw));
            default -> builder.and(
                    milyX.subject.containsIgnoreCase(kw)
                            .or(milyX.body.containsIgnoreCase(kw))
                            .or(milyX.author.userNickName.containsIgnoreCase(kw))
            );
        }

        JPAQuery<MilyX> articlesQuery = jpaQueryFactory
                .selectDistinct(milyX)
                .from(milyX)
                .where(builder);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(milyX.getType(), milyX.getMetadata());
            articlesQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        articlesQuery.offset(pageable.getOffset()).limit(pageable.getPageSize());

        JPAQuery<Long> totalQuery = jpaQueryFactory
                .select(milyX.count())
                .from(milyX)
                .where(builder);

        return PageableExecutionUtils.getPage(articlesQuery.fetch(), pageable, totalQuery::fetchOne);
    }
}