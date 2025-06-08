package com.leetanyul.practice.board.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardJpaEntity, Long> {
    List<BoardJpaEntity> findByCreatorId(Long creatorId);
}
