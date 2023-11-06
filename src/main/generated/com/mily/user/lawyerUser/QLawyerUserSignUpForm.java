package com.mily.user.lawyerUser;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLawyerUserSignUpForm is a Querydsl query type for LawyerUserSignUpForm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLawyerUserSignUpForm extends EntityPathBase<LawyerUserSignUpForm> {

    private static final long serialVersionUID = 170296447L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLawyerUserSignUpForm lawyerUserSignUpForm = new QLawyerUserSignUpForm("lawyerUserSignUpForm");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduce = createString("introduce");

    public final StringPath licenseNumber = createString("licenseNumber");

    public final StringPath major = createString("major");

    public final com.mily.user.QMilyUser milyUser;

    public final StringPath officeAddress = createString("officeAddress");

    public QLawyerUserSignUpForm(String variable) {
        this(LawyerUserSignUpForm.class, forVariable(variable), INITS);
    }

    public QLawyerUserSignUpForm(Path<? extends LawyerUserSignUpForm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLawyerUserSignUpForm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLawyerUserSignUpForm(PathMetadata metadata, PathInits inits) {
        this(LawyerUserSignUpForm.class, metadata, inits);
    }

    public QLawyerUserSignUpForm(Class<? extends LawyerUserSignUpForm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.milyUser = inits.isInitialized("milyUser") ? new com.mily.user.QMilyUser(forProperty("milyUser"), inits.get("milyUser")) : null;
    }

}

