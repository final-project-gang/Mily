package com.mily.user.lawyerUser;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLawyerUser is a Querydsl query type for LawyerUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLawyerUser extends EntityPathBase<LawyerUser> {

    private static final long serialVersionUID = 255753731L;

    public static final QLawyerUser lawyerUser = new QLawyerUser("lawyerUser");

    public final StringPath area = createString("area");

    public final StringPath createDate = createString("createDate");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduce = createString("introduce");

    public final StringPath major = createString("major");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath userLoginId = createString("userLoginId");

    public final StringPath userPassword = createString("userPassword");

    public QLawyerUser(String variable) {
        super(LawyerUser.class, forVariable(variable));
    }

    public QLawyerUser(Path<? extends LawyerUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLawyerUser(PathMetadata metadata) {
        super(LawyerUser.class, metadata);
    }

}

