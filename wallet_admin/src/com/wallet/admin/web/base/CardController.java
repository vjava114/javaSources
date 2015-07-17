package com.wallet.admin.web.base;

import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdCard;
import com.wallet.admin.service.MwAdCardService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

/*
 * Filename	: CardController.java
 * Class	: com.wallet.admin.web.base.CardController
 * History	: 2012/08/23, psj, 작업구분 : 카드사 관리 > 카드
 * Comment	:
 */
@Controller
public class CardController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 신규 카드사 등록 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/card_reg.ad")
	public String cardReg(HttpServletRequest request, HttpServletResponse response) {
		return "base/card_reg";
	}
	
	/**
	 * 신규 카드사 등록
	 *@param cname			카드사명
	 * @param cidx			카드사별 id
	 * @param os				os
	 * @param market		제공 마켓
	 * @param app_id		app_id
	 * @param app_pkg		app_pkg
	 * @param app_class	app_class
	 * @param app_url		다운로드 url
	 * @param img_i4		목록이미지(아이폰4)
	 * @param img_i3		목록이미지(아이폰3)
	 * @param img_r4		목록이미지(안드로이드)
	 * @return	
	 */
	@RequestMapping(value="/base/card_reg.ad", method=RequestMethod.POST)
	public String insertCardReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### CardController insert_card_reg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/

		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Object> android_params = new HashMap<String,Object>();
		HashMap<String,Object> iphone_params = new HashMap<String,Object>();
		String os = "A";
		int result = 1;
		
		//공통
		String cname = checkStr(request.getParameter("cname"), null);					//카드사명
		String cidx = checkStr(request.getParameter("cidx"), null);						//카드사별 id
		String stat = checkStr(request.getParameter("stat"), null);						//상태
		String info_url = checkStr(request.getParameter("info_url"), null);		//상세 url
		String phone = checkStr(request.getParameter("phone"), null);					//콜센터 전화번호
		String img_host = PropertiesUtil.get("img_host");											//img_host

		//안드로이드
		String os_G = checkStr(request.getParameter("os_G"), null);														//App 제공  os
		String android_market = checkStr(request.getParameter("android_market"), null);				//안드로이드 마켓
		String android_app_id = checkStr(request.getParameter("android_app_id"), null);				//호출정보(app_id)
		String android_app_pkg = checkStr(request.getParameter("android_app_pkg"), null);			//호출정보(package명)
		String android_app_class = checkStr(request.getParameter("android_app_class"), null);	//호출정보(실행 class 몇)
		String android_app_url = checkStr(request.getParameter("android_app_url"), null);			//다운로드 url

		//아이폰
		String os_A = checkStr(request.getParameter("os_A"), null);													//App 제공  os
		String iphone_market = checkStr(request.getParameter("iphone_market"), null);				//아이폰 마켓
		String iphone_app_id = checkStr(request.getParameter("iphone_app_id"), null);				//호출정보(app_id)
		String iphone_app_class = checkStr(request.getParameter("iphone_app_class"), null);	//호출정보(실행 class 몇)
		String iphone_app_url = checkStr(request.getParameter("iphone_app_url"), null);			//다운로드 url
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//목록 이미지(아이폰4,아이폰3GS,만드로이드)
		
		try {
			
			android_params.put("cname", cname);
			android_params.put("cidx", cidx);
			android_params.put("stat", stat);
			android_params.put("info_url", info_url);
			android_params.put("os", os_G);
			android_params.put("market", android_market);
			android_params.put("app_id", android_app_id);
			android_params.put("app_pkg", android_app_pkg);
			android_params.put("app_class", android_app_class);
			android_params.put("app_url", android_app_url);
			android_params.put("img_host", img_host);
			android_params.put("phone", phone);
			
			iphone_params.put("cname", cname);
			iphone_params.put("cidx", cidx);
			iphone_params.put("stat", stat);
			iphone_params.put("info_url", info_url);
			iphone_params.put("os", os_A);
			iphone_params.put("market", iphone_market);
			iphone_params.put("app_id", iphone_app_id);
			iphone_params.put("app_class", iphone_app_class);
			iphone_params.put("app_url", iphone_app_url);		
			iphone_params.put("img_host", img_host);
			iphone_params.put("phone", phone);
			
			//신규 카드 등록
			service.insertCardReg(android_params, iphone_params, mpRequest);
			
			//카드사 정보조회 페이지로 이동(os:IOS default)
			if("G".equals(os_G)) {
				os = "G";
			}
			if("A".equals(os_A)) {
				os = "A";
			}
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		response.sendRedirect("/base/card_list.ad?os="+os);
		
		return "base/card_list";
	}
	
	/**
	 * 카드사 정보 조회
	 * @param 	os 			os
	 * @return	cname		카드사명
	 * @return	cidx		cidx
	 * @return  imgUrl	이미지 경로
	 * @return  idx 		조정 순서
	 */
	@RequestMapping(value="/base/card_list.ad")
	public String selectCardList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CardController cardList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String os = checkStr(request.getParameter("os"), "A");
		
		params.put("view", "list");
		params.put("os", os);
		
		//조회
		list = service.selectCardList(params);
		
		request.setAttribute("os", os);
		request.setAttribute("mwAdCardList", list);

		log.debug("### CardController cardList END ###");
		
		return "base/card_list";
	}
	
	/**
	 * 카드사 정보 상세 조회
	 * @param 	os 			os
	 * @return	cidx		cidx
	 */
	@RequestMapping(value="/base/card_list_dtl.ad")
	public String selectCardListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### CardController selectCardListDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdCard mwAdCard = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String cidx = checkStr(request.getParameter("cidx"), "");
		String os = checkStr(request.getParameter("os"), "A");
		
		params.put("view", "dtl");
		params.put("cidx", cidx);
		params.put("os", os);
		params.put("top", 1);
		
		//조회
		mwAdCard = service.selectCardListDtl(params);

		
		request.setAttribute("mwAdCard", mwAdCard);

		log.debug("### CardController selectCardListDtl END ###");
		
		return "base/card_list_dtl";
	}
	
	/**
	 * 카드사 정보 수정
	 * @param cname			카드사명
	 * @param cidx			카드사별 id
	 * @param os				os
	 * @param 					제공 마켓
	 * @param app_id		app_id
	 * @param app_pkg		app_pkg
	 * @param app_class	app_class
	 * @param 					다운로드 url
	 * @param 					목록이미지(아이폰4)
	 * @param 					목록이미지(아이폰3)
	 * @param 					목록이미지(안드로이드)
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/card_dtl_update.ad", method=RequestMethod.POST)
	public String updateCardDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CardController updateCardDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		//공통
		String cname = checkStr(request.getParameter("cname"), null);					//카드사명
		String cidx = checkStr(request.getParameter("cidx"), null);						//카드사별 id
		String stat = checkStr(request.getParameter("stat"), null);						//상태
		String info_url = checkStr(request.getParameter("info_url"), null);		//상세 url
		String cidx_org = checkStr(request.getParameter("cidx_org"), "");			//카드사별 id 오리지널
		String os = checkStr(request.getParameter("os"), null);								//제공  os
		String phone = checkStr(request.getParameter("phone"), "");						//콜센터 전화번호
		String img_host = PropertiesUtil.get("img_host");											//img_host

		//안드로이드
		String android_market = checkStr(request.getParameter("android_market"), null);				//안드로이드 마켓
		String android_app_id = checkStr(request.getParameter("android_app_id"), null);				//호출정보(app_id)
		String android_app_pkg = checkStr(request.getParameter("android_app_pkg"), null);			//호출정보(package명)
		String android_app_class = checkStr(request.getParameter("android_app_class"), null);	//호출정보(실행 class 몇)
		String android_app_url = checkStr(request.getParameter("android_app_url"), null);			//다운로드 url

		//아이폰
		String iphone_market = checkStr(request.getParameter("iphone_market"), null);				//아이폰 마켓
		String iphone_app_id = checkStr(request.getParameter("iphone_app_id"), null);				//호출정보(app_id)
		String iphone_app_class = checkStr(request.getParameter("iphone_app_class"), null);	//호출정보(실행 class 몇)
		String iphone_app_url = checkStr(request.getParameter("iphone_app_url"), null);			//다운로드 url
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//목록 이미지(아이폰4,아이폰3GS,만드로이드)
		
		try {
			
			params.put("cname", cname);
			params.put("cidx", cidx);
			params.put("cidx_org", cidx_org);
			params.put("stat", stat);
			params.put("info_url", info_url);
			params.put("os", os);
			params.put("img_host", img_host);
			params.put("phone", phone);
			
			if("A".equals(os)) {
				params.put("market", iphone_market);
				params.put("app_id", iphone_app_id);
				params.put("app_class", iphone_app_class);
				params.put("app_url", iphone_app_url);		
			} else {
				params.put("market", android_market);
				params.put("app_id", android_app_id);
				params.put("app_pkg", android_app_pkg);
				params.put("app_class", android_app_class);
				params.put("app_url", android_app_url);
			}

			service.updateCardDtl(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

		response.sendRedirect("/base/card_list.ad?os="+os);
		
		log.debug("### CardController updateCardDtl END ###");
		
		return "base/card_list";
		
	}
	
	/**
	 * 카드사 순서 조정
	 * @param cidx			cidx
	 * @param os				os
	 * @param thisIdx		순서를 변경할  범위 idx
	 * @param targetidx 순서를 변경할  범위 idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/card_idx_update.ad", method=RequestMethod.POST)
	public String updateCardIdx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CardController updateCardIdx START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdCard> list = null;
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String cidx = checkStr(request.getParameter("cidx"), "1");
		String os = checkStr(request.getParameter("os"), "A");
		int thisIdx = Integer.parseInt(checkStr(request.getParameter("thisIdx"), "1"));
		int targetIdx = Integer.parseInt(checkStr(request.getParameter("targetIdx"), "1"));
		
		try {
			
			params.put("os", os);
			params.put("thisIdx", thisIdx);
			params.put("targetIdx", targetIdx);
			params.put("idxGb", "update");			//순위조정시 변경 구분값
			
			//순서 조정 update
			service.updateCardIdx(params, cidx);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

		//초기화 후, os(IOS,Android) 조회
		params.clear();
		response.sendRedirect("/base/card_list.ad?os="+os);

		log.debug("### CardController updateCardIdx END ###");
		
		return "base/card_list";
	}
	
	/**
	 * 카드사 정보 삭제
	 * @param cidx			카드사별 id
	 * @param os				os
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/card_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteCardDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### CardController deleteCardDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdCardService service = new MwAdCardService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String cidx = checkStr(request.getParameter("cidx"), "");		//카드사별 id
		String os = checkStr(request.getParameter("os"), "");				//os
		String idx = checkStr(request.getParameter("idx"), "");			//idx
		
		try {
			
			params.put("cidx", cidx);
			params.put("os", os);
			params.put("idx", idx);
			params.put("idxGb", "delete");	//삭제시 순위조정 변경 구분값

			//cidx가 없을경우 강제 Exception 발생
			if("".equals(cidx)) {
				throw new Exception("cidx가 없습니다. 강제 Exception 발생");
			}
			//os가 없을경우 강제 Exception 발생
			if("".equals(os)) {
				throw new Exception("os가 없습니다. 강제 Exception 발생");
			}
			
			service.deleteCardDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

		log.debug("### CardController deleteCardDtl END ###");
		return null;
		
	}
	
}
