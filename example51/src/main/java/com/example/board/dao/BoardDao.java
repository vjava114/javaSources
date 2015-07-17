package com.example.board.dao;

import java.util.List;

import com.example.board.domain.Board;
import com.example.board.domain.Member;
import com.example.board.domain.PageHelper;

public interface BoardDao {
	
	public Integer getTotalBoard();
	public List<Board> getBoardsByPage(PageHelper pageHelper);
	public void addBoard(Board board);
	public void deleteBoard(Integer no);
	public void addMember(Member member);
	public Integer checkId(String id);
	public Board getBoardByNo(Integer no);

}
