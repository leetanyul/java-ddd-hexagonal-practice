package com.leetanyul.practice.board.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "boards")
public class BoardJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String content;

    @Setter
    @Column(name = "creator_id")
    private Long creatorId;

    public BoardJpaEntity(String title, String content, Long creatorId) {
        this.title = title;
        this.content = content;
        this.creatorId = creatorId;
    }
}
