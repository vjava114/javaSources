package com.example.board.controller;

import java.util.List;

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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final int TOTAL_PAGE_LINK = 10;
	
	@Autowired
	private BoardService service;
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
			Model model,
			Authentication authentication) {
		// ǥ���� ������
		int currPage = 1; 		// page�� �������� ������ ù ��° ������
		if(page != null) { 		// page�� �Ķ���ͷ� �Ѿ���� �ش� ���� ���� ��������...
			currPage = page.intValue();
		}
		
		// ��ü ������
		int totalPage = service.getTotalPage();
		
		// ���� �������� 5 �������̸�, ������ ��ũ�� ������ ���� ǥ�õǾ�� �Ѵ�.
		// [1][2][3][4][5][6][7][8][9][10]>
		// ���� �������� 15�������̸�, ������ ��ũ�� ������ ���� ǥ�õǾ�� �Ѵ�.
		// <[11][12][13][14][15][16][17][18][19][20]>
		
		// ������ ��ũ�� ���۵Ǵ� ������
		// ���� ������ ��ȣ�� ��ũ ������ ���� ���� �ø��ϰ�, ��ũ������ ���� ����, ��ũ ����-1�� ���ָ� �ȴ�.
		// ���� �������� 5�������̸�, 5 / 10 = 0.5 �� �ø��ϸ� 1�̰�, 1 * 10 - (10 - 1) = 1
		int lastLink = (int)Math.ceil((double)currPage / TOTAL_PAGE_LINK) * TOTAL_PAGE_LINK;
		int firstPage = lastLink - (TOTAL_PAGE_LINK - 1);
		
		// ������ ��ũ�� ������ ������
		// ���� ������ ��ȣ�� ��ũ ������ ���� ���� �ø��� ��, ��ũ ������ ����
		// ���� �������� 5�������̸�, 5 / 10 = 0.5 �� �ø��ϸ� 1�̰�, 1 * 10 = 10
		// ��, ������ �������� ��ü �������� �ʰ��ϸ�, ������ �������� ��ü �������� �ȴ�.
		int lastPage = Math.min(lastLink, totalPage);
		
		model.addAttribute("currPage", currPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("lastPage", lastPage);
		
		List<Board> boards = service.getBoardsByPage(currPage);
		model.addAttribute("boards", boards);
		
		if(authentication != null) {
			model.addAttribute("id", authentication.getName());
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "login_error", required = false) String login_error,
			Model model) {
		if(login_error != null) {
			model.addAttribute("error", "���� ����");
		}
		return "login";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String showJoinForm(@RequestParam(value = "join_error", required = false) String join_error,
			Model model) {
		if(join_error != null) {
			model.addAttribute("error", "�̹� �����ϴ� �����Դϴ�.");
		}
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST )
	public String addMember(@RequestParam(value = "id") String id, 
			@RequestParam(value = "password") String password, 
			@RequestParam(value = "email") String email, 
			Model model) {
		if(service.checkId(id)) {
			Member member = new Member();
			member.setId(id);
			member.setPassword(password);
			member.setEmail(email);
			service.addMember(member);
			model.addAttribute("id", id);
			return "success";
		} else {
			return "redirect:/join?join_error=t";
		}
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Authentication authentication, 
			@RequestParam(value = "content") String content) {
		// ��ũ��Ʈ �Է� ����...
		content = content.replace("<", "&lt;").replace(">", "&gt;");
		
		Board board = new Board();
		board.setId(authentication.getName());
		board.setContent(content);
		service.addBoard(board);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Authentication authentication,
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "no") Integer no) throws Exception {
		Board board = service.getBoardByNo(no);
		if(board.getId().equals(authentication.getName())) {
			service.deleteBoard(no);
		} else {
			throw new Exception("�ùٸ��� ���� �����Դϴ�.");
		}
		
		return "redirect:/index?page=" + page;
	}
}
