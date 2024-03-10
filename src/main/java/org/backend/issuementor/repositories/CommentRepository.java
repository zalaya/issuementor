package org.backend.issuementor.repositories;

import org.backend.issuementor.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}