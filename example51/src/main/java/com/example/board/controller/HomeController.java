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
		// 표시할 페이지
		int currPage = 1; 		// page가 지정되지 않으면 첫 번째 페이지
		if(page != null) { 		// page가 파라미터로 넘어오면 해당 값을 현재 페이지로...
			currPage = page.intValue();
		}
		
		// 전체 페이지
		int totalPage = service.getTotalPage();
		
		// 현재 페이지가 5 페이지이면, 페이지 링크는 다음과 같이 표시되어야 한다.
		// [1][2][3][4][5][6][7][8][9][10]>
		// 현재 페이지가 15페이지이면, 페이지 링크는 다음과 같이 표시되어야 한다.
		// <[11][12][13][14][15][16][17][18][19][20]>
		
		// 페이지 링크가 시작되는 페이지
		// 현재 페이지 번호를 링크 갯수로 나눈 몫을 올림하고, 링크갯수를 곱한 다음, 링크 갯수-1을 빼주면 된다.
		// 현재 페이지가 5페이지이면, 5 / 10 = 0.5 를 올림하면 1이고, 1 * 10 - (10 - 1) = 1
		int lastLink = (int)Math.ceil((double)currPage / TOTAL_PAGE_LINK) * TOTAL_PAGE_LINK;
		int firstPage = lastLink - (TOTAL_PAGE_LINK - 1);
		
		// 페이지 링크의 마지막 페이지
		// 현재 페이지 번호를 링크 갯수로 나눈 값을 올림한 뒤, 링크 갯수를 곱함
		// 현재 페이지가 5페이지이면, 5 / 10 = 0.5 를 올림하면 1이고, 1 * 10 = 10
		// 단, 마지막 페이지가 전체 페이지를 초과하면, 마지막 페이지가 전체 페이지가 된다.
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
			model.addAttribute("error", "인증 실패");
		}
		return "login";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String showJoinForm(@RequestParam(value = "join_error", required = false) String join_error,
			Model model) {
		if(join_error != null) {
			model.addAttribute("error", "이미 존재하는 계정입니다.");
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
		// 스크립트 입력 방지...
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
			throw new Exception("올바르지 않은 접근입니다.");
		}
		
		return "redirect:/index?page=" + page;
	}
}
