package com.example.board.controller;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.domain.Board;
import com.example.board.domain.Member;
import com.example.board.service.BoardService;
import com.example.util.PagingHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	public static void main(String[] args){
		System.out.println("aaa");
	}
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String getAllBoardByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			Model model,
			Authentication authentication) {

		int total = service.getBoardTotal().intValue();
		PagingHelper helper = new PagingHelper(total);
		
		model.addAttribute("page", page);
		model.addAttribute("link", helper.getPageLink(page.intValue()));
		model.addAttribute("boards", service.getAllBoardByPage(page));
		
		if(authentication != null) {
			model.addAttribute("id", authentication.getName());
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "login_error", required = false) String login_error,
			Model model) {
		if(login_error != null) 
		{
			model.addAttribute("error", "���� ����");
		}
		return "login";
	}
	
	
	/*
	 *  [ȸ����]
	 *  1. ��ó�� ȸ�� �����ϸ� ���� /join(POST) �� ���ؼ� ���´�.
	 *  2-1. �����ϸ� DB insert ��, success.jsp �� id�� ��޵ȴ�.
	 *  2-2. �����ϸ� join_error �Ķ���Ϳ� ������������ ��Ƽ� ����������. 
	 *  
	 *    ���... �����̷�Ʈ�� request ��ü�� ��� �Ҿ������.
	 * 
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST )
	public String addMember(
			@RequestParam(value = "id") String id,	
			@RequestParam(value = "password") String password,	
			@RequestParam(value = "email") String email,
			Model model) 
	{
		/*
		 *  -- �����ڵ� [join_error] --
		 *  1. tag (�±׻�� �Ұ�)
		 *  2. exist (�̹� ������)
		 */
		if(id.matches("(.*)[<>](.*)")) 
		{
			return "redirect:/join?join_error=tag";		
		}
		if( service.checkId(id) )	// �����ϴ� ���̵� �������. 
		{
			Member member = new Member();
			member.setId(id);
			member.setPassword(password);
			member.setEmail(email);
			service.addMember(member);
			model.addAttribute("id", id); 
			return "success";
		} 
		else // �����ϴ� ���̵� �������.
		{
			return "redirect:/join?join_error=exist?&id="+id;
		}
	}	
	
	
	
	/*
	 *  ȸ���� ������ ���, �Ʒ� ��Ʈ�ѷ��� Ž.
	 *  -- �����ڵ� [join_error] --
	 *  1. tag (�±׻�� �Ұ�)
	 *  2. exist (�̹� ������)
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String showJoinForm(@RequestParam(value = "join_error", required = false) String join_error,
			Model model) {
		if(join_error != null) {
			if(join_error.equals("exist")) 
			{
				model.addAttribute("error", "�̹� �����ϴ� �����Դϴ�.");
			} 
			else if(join_error.equals("tag")) 
			{
				model.addAttribute("error", "������ �±׸� ����ϸ� �ȵ˴ϴ�.");
			} 
			else 
			{
				model.addAttribute("error", "�� �� ��� �����Դϴ�.");
			}
		}
		return "join";
	}
	
	

	
	
	
	
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getBoardByNo(
			@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "back", required = false, defaultValue = "n") String back, 
			Model model,
			Authentication authentication) {
		if(back.equals("n")) {
			service.raiseLookupCount(no);
		}
		Board board = service.getBoardByNo(no);
		board.setContent(board.getContent().replace("\r\n", "<br />"));
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("id", authentication.getName());
		return "view";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String setupForInsert(@RequestParam(value = "no", required = false) Integer no, 
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "action") String action, 
			Model model,
			Authentication authentication) {
		
		model.addAttribute("page", page);
		model.addAttribute("action", action);
		
		if(action.equals("new")) {
			model.addAttribute("title", "�� �� ����");
			Board board = new Board();
			board.setId(authentication.getName());
			model.addAttribute("board", board);
		} else if(action.equals("answer")) {
			model.addAttribute("title", "��� ����");
			Board board = service.getBoardByNo(no);
			board.setId(authentication.getName());
			board.setTitle("[���]" + board.getTitle());
			board.setContent("\r\n\r\n========================<��>========================\r\n" + board.getContent());
			model.addAttribute("board", board);
		} else if(action.equals("update")) {
			Board board = service.getBoardByNo(no);
			if(board.getId().equals(authentication.getName())) {
				model.addAttribute("title", "����");
				model.addAttribute("board", board);
			} else {
				throw new RuntimeException("�ۼ��ڰ� �ƴմϴ�.");
			}
		}
		return "edit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(value = "action") String action, 
			Board board,
			Authentication authentication) {
		
		board.setId(authentication.getName());
		
		if(action.equals("new")) {
			service.addBoard(board);
			return "redirect:/index?page=1";
		} else if(action.equals("answer")) {
			board.setRef_no(no);
			service.addBoard(board);
			return "redirect:/index?page=" + page.intValue();
		} else if(action.equals("update")) {
			board.setNo(no);
			service.modifyBoard(board);
			return "redirect:/view?no=" + no.intValue() + "&page=" + page.intValue() + "&back=y";
		} else {
			throw new RuntimeException("���������� �����Դϴ�.");
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "no") Integer no, 
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			Authentication authentication) {
		Board board = service.getBoardByNo(no);
		if(board.getId().equals(authentication.getName())) {
			service.removeBoard(no);
			return "redirect:/index?page=" + page.intValue();
		} else {
			throw new RuntimeException("�ۼ��ڰ� �ƴմϴ�.");
		}
	}
}
