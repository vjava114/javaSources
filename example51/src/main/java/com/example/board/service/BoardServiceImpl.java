package com.example.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dao.BoardDao;
import com.example.board.domain.Board;
import com.example.board.domain.Member;
import com.example.board.domain.PageHelper;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	@Transactional(readOnly = true)
	public Integer getTotalPage() {
		// TODO Auto-generated method stub
		int total = boardDao.getTotalBoard().intValue();
		int totalPage = (int)Math.ceil((double)total / PageHelper.LINES_PER_PAGE);
		return new Integer(totalPage);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Board> getBoardsByPage(Integer page) {
		// TODO Auto-generated method stub
		return boardDao.getBoardsByPage(new PageHelper(page.intValue()));
	}

	@Override
	public void addBoard(Board board) {
		// TODO Auto-generated method stub
		boardDao.addBoard(board);
	}

	@Override
	public void deleteBoard(Integer no) {
		// TODO Auto-generated method stub
		boardDao.deleteBoard(no);
	}

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		boardDao.addMember(member);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkId(String id) {
		// TODO Auto-generated method stub
		// ���ϰ��� true�̸� �ߺ��� Id�� �������� �����Ƿ� �ش� ������ ��� �� �� ����.
		// ���ϰ��� false�̸� ���� Id�� �浹�ϹǷ� �ش� ������ ��� �� �� ����.
		return boardDao.checkId(id).intValue() == 0;
	}

	@Override
	@Transactional(readOnly = true)
	public Board getBoardByNo(Integer no) {
		// TODO Auto-generated method stub
		return boardDao.getBoardByNo(no);
	}
	
}
