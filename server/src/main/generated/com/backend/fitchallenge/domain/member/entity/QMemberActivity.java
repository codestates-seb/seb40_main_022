package com.backend.fitchallenge.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberActivity is a Querydsl query type for MemberActivity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberActivity extends BeanPath<MemberActivity> {

    private static final long serialVersionUID = 1679491413L;

    public static final QMemberActivity memberActivity = new QMemberActivity("memberActivity");

    public final NumberPath<Integer> dayCount = createNumber("dayCount", Integer.class);

    public final NumberPath<Integer> kilogram = createNumber("kilogram", Integer.class);

    public final NumberPath<Double> point = createNumber("point", Double.class);

    public QMemberActivity(String variable) {
        super(MemberActivity.class, forVariable(variable));
    }

    public QMemberActivity(Path<? extends MemberActivity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberActivity(PathMetadata metadata) {
        super(MemberActivity.class, metadata);
    }

}

