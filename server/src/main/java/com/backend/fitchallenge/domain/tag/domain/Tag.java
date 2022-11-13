package com.backend.fitchallenge.domain.tag.domain;

import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name ="content", nullable = false)
    private String content;

    public Tag(String content) {
        this.content = content;
    }

    static public Tag of(String name) {
        return new Tag(name);
    }
}
