package com.backend.fitchallenge.domain.tag.repository;

import com.backend.fitchallenge.domain.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.AbstractDocument;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Boolean existsByContent(String content);

    Optional<Tag> findByContent(String content);
}
