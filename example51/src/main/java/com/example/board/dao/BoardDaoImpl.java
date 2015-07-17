package com.example.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.example.board.domain.Board;
import com.example.board.domain.Member;
import com.example.board.domain.PageHelper;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Resource
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public Integer getTotalBoard() {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("com.example.board.mybatis.BoardMapper.getTotalBoard");
	}

	@Override
	public List<Board> getBoardsByPage(PageHelper pageHelper) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectList("com.example.board.mybatis.BoardMapper.getBoardsByPage", pageHelper);
	}

	@Override
	public void addBoard(Board board) {
		// TODO Auto-generated method stub
		sessionTemplate.insert("com.example.board.mybatis.BoardMapper.addBoard", board);
	}

	@Override
	public void deleteBoard(Integer no) {
		// TODO Auto-generated method stub
		sessionTemplate.delete("com.example.board.mybatis.BoardMapper.deleteBoard", no);
	}

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		sessionTemplate.insert("com.example.board.mybatis.BoardMapper.addMember", member);
	}

	@Override
	public Integer checkId(String id) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("com.example.board.mybatis.BoardMapper.checkId", id);
	}

	@Override
	public Board getBoardByNo(Integer no) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("com.example.board.mybatis.BoardMapper.getBoardByNo", no);
	}

}
