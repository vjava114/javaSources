package com.example.board.service;

import java.util.List;

import com.example.board.domain.Board;
import com.example.board.domain.Member;

public interface BoardService {
	public Integer getTotalPage();
	public List<Board> getBoardsByPage(Integer page);
	public void addBoard(Board board);
	public void deleteBoard(Integer no);
	public void addMember(Member member);
	public boolean checkId(String id);
	public Board getBoardByNo(Integer no);
}
