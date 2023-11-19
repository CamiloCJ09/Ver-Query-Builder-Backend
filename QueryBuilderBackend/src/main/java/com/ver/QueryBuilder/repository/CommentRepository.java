package com.ver.QueryBuilder.repository;

import com.ver.QueryBuilder.dto.response.CommentOutDTO;
import com.ver.QueryBuilder.model.ownEntites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Optional<CommentOutDTO>> findAllById(UUID uuid);
}
