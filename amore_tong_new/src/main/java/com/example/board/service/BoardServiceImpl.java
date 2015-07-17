package com.example.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dao.BoardMapper;
import com.example.board.domain.Board;
import com.example.board.domain.Member;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	private static final int LINES_PER_PAGE = 15;
	
	@Override
	@Transactional(readOnly = true)
	public List<Board> getAllBoardByPage(Integer page) {
		// TODO Auto-generated method stub
		int begin = (page.intValue() - 1) * LINES_PER_PAGE;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("size", LINES_PER_PAGE);
		
		List<Board> boards = mapper.getAllBoardByPage(map);
		
		// 들여쓰기 처리
		for(Board board : boards) {
			StringBuilder builder = new StringBuilder();
			for(int i=0; i<board.getIndent_in_group(); i++) {
				builder.append("&nbsp;&nbsp;&nbsp;");
			}
			if(board.getRef_no()==-1) {
				builder.append("<span class='error-text'>[원문이 삭제됨]</span>");
			}
			board.setTitle(builder.toString() + board.getTitle());
		}
		return boards;
	}

	@Override
	@Transactional(readOnly = true)
	public Board getBoardByNo(Integer no) {
		// TODO Auto-generated method stub
		return mapper.getBoardByNo(no);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getBoardTotal() {
		// TODO Auto-generated method stub
		return mapper.getBoardTotal();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkId(String id) {
		// TODO Auto-generated method stub
		return mapper.checkId(id).intValue() == 0 ? true : false;
	}

	@Override
	public void addBoard(Board board) {
		// TODO Auto-generated method stub

		board.setTitle(removeTag(board.getTitle()));
		board.setContent(removeTag(board.getContent()));
		
		if(board.getRef_no()>0) {
			// 자식 글을 입력하기 위해 답글의 순서를 재배치한다. 
			Board parent = mapper.getBoardByNo(board.getRef_no());
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("parent_group_no", parent.getGroup_no());
			map.put("parent_sequence_in_group", parent.getSequence_in_group());
			mapper.reorderBoard(map);
			
			// 자식 글
			board.setGroup_no(parent.getGroup_no());
			board.setSequence_in_group(parent.getSequence_in_group() + 1);
			board.setIndent_in_group(parent.getIndent_in_group() + 1);
			mapper.addBoard(board);
			
		} else {
			// 부모 글
			board.setSequence_in_group(0);
			board.setIndent_in_group(0);
			mapper.addBoard(board);
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("no", board.getNo());
			map.put("group_no", board.getNo());
			mapper.modifyGroupNo(map);
		}
	}

	@Override
	public void modifyBoard(Board board) {
		// TODO Auto-generated method stub
		board.setTitle(removeTag(board.getTitle()));
		board.setContent(removeTag(board.getContent()));
		
		mapper.modifyBoard(board);
	}

	@Override
	public void removeBoard(Integer no) {
		// TODO Auto-generated method stub
		mapper.removeBoard(no);
		mapper.modifyNoParentBoard(no);
	}

	@Override
	public void raiseLookupCount(Integer no) {
		// TODO Auto-generated method stub
		mapper.raiseLookup(no);
	}

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		mapper.addMember(member);
	}
	
	private String removeTag(String str) {
		return str.replace("<", "&lt;").replace(">", "&gt");
	}
}
