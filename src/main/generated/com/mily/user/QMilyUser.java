package com.mily.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMilyUser is a Querydsl query type for MilyUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMilyUser extends EntityPathBase<MilyUser> {

    private static final long serialVersionUID = 1654759167L;

    public static final QMilyUser milyUser = new QMilyUser("milyUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> milyPoint = createNumber("milyPoint", Integer.class);

    public final ListPath<com.mily.payment.Payment, com.mily.payment.QPayment> payments = this.<com.mily.payment.Payment, com.mily.payment.QPayment>createList("payments", com.mily.payment.Payment.class, com.mily.payment.QPayment.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> userCreateDate = createDateTime("userCreateDate", java.time.LocalDateTime.class);

    public final StringPath userDateOfBirth = createString("userDateOfBirth");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userLoginId = createString("userLoginId");

    public final StringPath userName = createString("userName");

    public final StringPath userNickName = createString("userNickName");

    public final StringPath userPassword = createString("userPassword");

    public final StringPath userPhoneNumber = createString("userPhoneNumber");

    public QMilyUser(String variable) {
        super(MilyUser.class, forVariable(variable));
    }

    public QMilyUser(Path<? extends MilyUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMilyUser(PathMetadata metadata) {
        super(MilyUser.class, metadata);
    }

}

