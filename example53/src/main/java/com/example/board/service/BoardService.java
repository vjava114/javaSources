package com.example.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.board.domain.Board;
import com.example.board.domain.Member;

public interface BoardService {
	public List<Board> getAllBoardByPage(Integer page);
	public Board getBoardByNo(Integer no);
	public Integer getBoardTotal();
	public void addBoard(Board board);
	public void modifyBoard(Board board);
	public void removeBoard(Integer no);
	public void raiseLookupCount(Integer no);
	public boolean checkId(String id);
	public void addMember(Member member);
}

//
//public List<Board> getAllBoardByPage(Map<String, Integer> map);
//public Board getBoardByNo(Integer no);
//public Integer getBoardTotal();
//public void addBoard(Board board);
//public void modifyGroupNo(Map<String, Integer> map);
//public void modifyBoard(Board board);
//public void removeBoard(Integer no);
//public void raiseLookup(Integer no);
//public void modifyNoParentBoard(Integer no);
//public void reorderBoard(HashMap<String, Integer> map);
//public Integer checkId(String id);
//public void addMember(Member member);