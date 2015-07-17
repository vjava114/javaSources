package com.wallet.admin.web.base;

import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.model.MwAdGift;
import com.wallet.admin.service.MwAdGiftService;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

/*
 * Filename	: GiftController.java
 * Class	: com.wallet.admin.web.base.GiftController
 * History	: 2012/08/23, psj, 작업구분 : 상품권 관리 > 상품권
 * Comment	:
 */
@Controller
public class GiftController extends CommonController {
	private final String PAGE_CODE = "MENU_RIGHT";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * 상품권 정보 등록 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/gift_reg.ad")
	public String giftReg(HttpServletRequest request, HttpServletResponse response) {
		return "/base/gift_reg";
	}
	
	/**
	 * 상품권 정보등록
	 * @param name			상품권명
	 * @param gift_id		상품권 id
	 * @param memo			소개문구
	 * @param operater	운영사			
	 * @param stat			상태				
	 * @param os				제공os			
	 * @param l_img_i4	리스트이미지(아이폰4)
	 * @param l_img_i3	리스트이미지(아이폰3)
	 * @param l_img_r4	리스트이미지(안드로이드)
	 * @param d_img_i4	카드이미지(아이폰4)
	 * @param d_img_i3	카드이미지(아이폰3)
	 * @param d_img_r4	카드이미지(안드로이드)
	 * @return	
	 */
	@RequestMapping(value="/base/gift_reg.ad", method=RequestMethod.POST)
	public String insertGiftReg(HttpServletRequest request, HttpServletResponse response)
			 throws Exception {
		
		log.debug("### GiftController insertGiftReg START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String name = checkStr(request.getParameter("name"), "");					//상품권명
		String gift_id = checkStr(request.getParameter("gift_id"), "");		//상품권ID
		String gift_sid = checkStr(request.getParameter("gift_sid"), "");		//상품권SID
		String memo = checkStr(request.getParameter("memo"), "");					//소개문구
		String operater = checkStr(request.getParameter("operater"), "");	//운영사
		String stat = checkStr(request.getParameter("stat"), "");					//상태
		String os = checkStr(request.getParameter("os"), "");							//제공OS
		String mgr_id = checkStr(getSessionMgrId(request), "");						//로그인id
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//리스트,카드 이미지(아이폰4,아이폰3GS,만드로이드)
	
		try {
			
			params.put("name", name);
			params.put("gift_id", gift_id);
			params.put("gift_sid", gift_sid);
			params.put("memo", memo);
			params.put("operater", operater);
			params.put("stat", stat);
			params.put("os", os);
			params.put("display_yn", "Y");
			params.put("img_host", PropertiesUtil.get("img_host"));
			params.put("admin_id",mgr_id);
			
			service.insertGiftReg(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		} 

    //list화면 이동
		response.sendRedirect("/base/gift_list.ad");
		
		log.debug("### GiftController insertGiftReg END ###");
    
		return "base/gift_list";
	}
	
	/**
	 * 상품권 정보 조회 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/gift_list.ad")
	public String selectGiftList(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### GiftController selectGiftList START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("view", "list");
		
		//조회
		list = service.selectGiftList(params);
		
		request.setAttribute("mwAdGiftList", list);
		
		log.debug("### GiftController selectGiftList END ###");
		
		return "base/gift_list";
	}
	
	
	/**
	 * 상품권 정보 상세 조회 화면 페이지 호출
	 * @return	
	 */
	@RequestMapping(value="/base/gift_list_dtl.ad")
	public String selectGiftListDtl(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### GiftController selectGiftListDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdGift mwAdGift = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String gift_id = checkStr(request.getParameter("gift_id"), "");		//상품권ID
		
		params.put("view", "dtl");
		params.put("gift_id", gift_id);
		params.put("top", 1);
		
		//조회
		mwAdGift = service.selectGiftListDtl(params);
		
		request.setAttribute("mwAdGift", mwAdGift);
		
		log.debug("### GiftController selectGiftListDtl END ###");
		
		return "base/gift_list_dtl";
	}
	
	/**
	 * 카드사 정보 삭제
	 * @param cidx			카드사별 id
	 * @param os				os
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/gift_dtl_delete.ad", method=RequestMethod.POST)
	public String deleteGiftDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### GiftController deleteGiftDtl START ###");

		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String gift_id = checkStr(request.getParameter("gift_id"), "");	//상품권 id
		String main_idx = checkStr(request.getParameter("main_idx"), "");		//main_idx
		
		try {
			
			params.put("gift_id", gift_id);
			params.put("main_idx", main_idx);
			params.put("idxGb", "delete");		//삭제시 순위조정 변경 구분값
			
			//상품권 id가 없을경우 강제 Exception 발생
			if("".equals(gift_id)) {
				throw new Exception("상품권 id가 없습니다. 강제 Exception 발생");
			}

			service.deleteGiftDtl(params);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		log.debug("### GiftController deleteGiftDtl END ###");
		
		return null;
		
	}
	
	/**
	 * 카드사 정보 수정
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/gift_dtl_update.ad", method=RequestMethod.POST)
	public String updateGiftDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### GiftController updateGiftDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();

		String name = checkStr(request.getParameter("name"), "");					//상품권명
		String gift_id = checkStr(request.getParameter("gift_id"), "");		//상품권ID
		String gift_sid = checkStr(request.getParameter("gift_sid"), "");		//상품권ID
		String gift_id_org = checkStr(request.getParameter("gift_id_org"), "");		//상품권ID 오리지널
		String memo = checkStr(request.getParameter("memo"), "");					//소개문구
		String operater = checkStr(request.getParameter("operater"), "");	//운영사
		String stat = checkStr(request.getParameter("stat"), "");					//상태
		String os = checkStr(request.getParameter("os"), "");							//제공OS
		String l_img_i4 = checkStr(request.getParameter("l_img_i4"), "");							//리스트 아이폰4
		String l_img_i3 = checkStr(request.getParameter("l_img_i3"), "");							//리스트 아이폰3
		String l_img_r4 = checkStr(request.getParameter("l_img_r4"), "");							//리스트 안드로이드
		String d_img_i4 = checkStr(request.getParameter("d_img_i4"), "");							//카드 아이폰4
		String d_img_i3 = checkStr(request.getParameter("d_img_i3"), "");							//카드 아이폰3
		String d_img_r4 = checkStr(request.getParameter("d_img_r4"), "");							//카드 안드로이드
		
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//리스트,카드 이미지(아이폰4,아이폰3GS,만드로이드)
	
		try {

			params.put("name", name);
			params.put("gift_id", gift_id);
			params.put("gift_id_org", gift_id_org);
			params.put("gift_sid", gift_sid);
			params.put("memo", memo);
			params.put("operater", operater);
			params.put("stat", stat);
			params.put("os", os);
			params.put("img_host", PropertiesUtil.get("img_host"));

			service.updateGiftDtl(params, mpRequest);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}
		
		//list화면 이동
		response.sendRedirect("/base/gift_list.ad");
		
		log.debug("### GiftController updateGiftDtl END ###");
		
		return "base/gift_list";
		
	}
	
	/**
	 * 상품권 순서 조정
	 * @param gift_id		gift_id
	 * @param os				os
	 * @param thisIdx		순서를 변경할  범위 idx
	 * @param targetidx 순서를 변경할  범위 idx
	 * @return	
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/gift_idx_update.ad", method=RequestMethod.POST)
	public String updateGiftIdx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### GiftController updateGiftDtl START ###");
		
		/* 변수 선언 부 : 컨트롤러에서 사용할 주요 변수를 선언한다.*/
		List<MwAdGift> list = null;
		MwAdGiftService service = new MwAdGiftService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String gift_id = checkStr(request.getParameter("gift_id"), "");
		int thisIdx = Integer.parseInt(checkStr(request.getParameter("thisIdx"), "1"));
		int targetIdx = Integer.parseInt(checkStr(request.getParameter("targetIdx"), "1"));
		
		try {
			
			params.put("thisIdx", thisIdx);
			params.put("targetIdx", targetIdx);
			params.put("idxGb", "update");	//순위조정시 변경 구분값
			
			//순서 조정 update
			service.updateGiftIdx(params, gift_id);
			
			MybatisCilent.getSqlMapper().commit();

		} catch (Exception e) {
			MybatisCilent.getSqlMapper().rollback();
			e.printStackTrace();
		}

		//조회
		params.clear();
		response.sendRedirect("/base/gift_list.ad");
		
		log.debug("### GiftController updateGiftDtl END ###");
		
		return "base/gift_list";
	}
}
