package com.example.board.controller;

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
			model.addAttribute("error", "인증 실패");
		}
		return "login";
	}
	
	
	/*
	 *  [강준영] 아래 POST 방식부터 볼것!!
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String showJoinForm(@RequestParam(value = "join_error", required = false) String join_error,
			Model model) {
		if(join_error != null) {
			if(join_error.equals("exist")) 
			{
				model.addAttribute("error", "이미 존재하는 계정입니다.");
			} 
			else if(join_error.equals("tag")) 
			{
				model.addAttribute("error", "계정에 태그를 사용하면 안됩니다.");
			} 
			else 
			{
				model.addAttribute("error", "알 수 없는 오류입니다.");
			}
		}
		return "join";
	}
	
	
	/*
	 *  [강준영]
	 *  
	 *  1. 맨처음 로그인 가입하면 여기 /join(POST) 를 통해서 들어온다.
	 *  2-1. 성공하면 DB insert 후, success.jsp 로 id값 전달된다.
	 *  2-2. 실패하면 join_error 파라미터에 무슨에러인지 담아서 돌려보낸다. 
	 *  
	 *    참고... 리다이렉트는 request 객체를 모두 잃어버린다.
	 * 
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST )
	public String addMember(
			@RequestParam(value = "id") String id,	
			@RequestParam(value = "password") String password,	
			@RequestParam(value = "email") String email,
			Model model) 
	{
		if(id.matches("(.*)[<>](.*)")) 
		{
			return "redirect:/join?join_error=tag";		
		}
		if( service.checkId(id) )	// 존재하는 아이디가 없을경우. 
		{
			Member member = new Member();
			member.setId(id);
			member.setPassword(password);
			member.setEmail(email);
			service.addMember(member);
			model.addAttribute("id", id); 
			return "success";
		} 
		else // 존재하는 아이디가 있을경우.
		{
			return "redirect:/join?join_error=exist?&id="+id;
		}
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getBoardByNo(@RequestParam(value = "no") Integer no, 
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
			model.addAttribute("title", "새 글 쓰기");
			Board board = new Board();
			board.setId(authentication.getName());
			model.addAttribute("board", board);
		} else if(action.equals("answer")) {
			model.addAttribute("title", "답글 쓰기");
			Board board = service.getBoardByNo(no);
			board.setId(authentication.getName());
			board.setTitle("[답글]" + board.getTitle());
			board.setContent("\r\n\r\n========================<원문>========================\r\n" + board.getContent());
			model.addAttribute("board", board);
		} else if(action.equals("update")) {
			Board board = service.getBoardByNo(no);
			if(board.getId().equals(authentication.getName())) {
				model.addAttribute("title", "편집");
				model.addAttribute("board", board);
			} else {
				throw new RuntimeException("작성자가 아닙니다.");
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
			throw new RuntimeException("비정상적인 접근입니다.");
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
			throw new RuntimeException("작성자가 아닙니다.");
		}
	}
}
