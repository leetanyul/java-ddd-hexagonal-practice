package com.leetanyul.practice.board.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class BoardId {
    private final Long value;
}
