package com.leetanyul.practice.board.application.service;

import com.leetanyul.practice.account.domain.AccountId;
import com.leetanyul.practice.board.application.port.in.GetBoardsByCreatorUseCase;
import com.leetanyul.practice.board.application.port.out.LoadBoardsByCreatorPort;
import com.leetanyul.practice.board.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBoardsByCreatorService implements GetBoardsByCreatorUseCase {

    private final LoadBoardsByCreatorPort loadBoardsByCreatorPort;

    public GetBoardsByCreatorService(LoadBoardsByCreatorPort loadBoardsByCreatorPort) {
        this.loadBoardsByCreatorPort = loadBoardsByCreatorPort;
    }

    @Override
    public List<Board> getBoardsByCreator(AccountId creatorId) {
        return loadBoardsByCreatorPort.loadBoardsByCreator(creatorId);
    }
}

