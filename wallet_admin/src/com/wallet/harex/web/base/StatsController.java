package com.wallet.harex.web.base;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.harex.common.JDate;
import com.wallet.harex.common.JPercent;
import com.wallet.harex.model.StatsListComp;
import com.wallet.harex.model.StatsReport;
import com.wallet.harex.service.StatsService;

@Controller
public class StatsController extends CommonController {

	private Logger log = Log.getLogger("logs");
	
	/**
	 * ��� > ��� ���� > �����纰 ���
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/stats_list_comp.st")
	public String selectStatsListCompSearch(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### StatsController selectStatsListComp START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<StatsListComp> list = null;
		StatsService service = new StatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String chk_date		= request.getParameter("chk_day");
		String ra_stat		= checkStr(request.getParameter("ra_stat"), "");
		
		String pSdate		= request.getParameter("sDate"); // ������
		String pEdate	 	= request.getParameter("eDate"); // ������
		
		String compId 			= checkStr(request.getParameter("compId"), "");  //���޻�ID
		String brandId 			= checkStr(request.getParameter("brandId"), "");  // �귣�� ID
		String regionType 		= checkStr(request.getParameter("regionType"), ""); // ����
		String shopId 			= checkStr(request.getParameter("shopId"), ""); // ������ID
		
		if(pSdate == null || "".equals(pSdate)){
			pSdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pSdate = pSdate.toString().replaceAll("-", "");
		}
		
		if(pEdate == null || "".equals(pEdate)){
			pEdate = JDate.getFormattedDate("yyyyMMdd");
		} else {
			pEdate = pEdate.toString().replaceAll("-", "");
		}
		
		if("%".equals(compId)){
			compId = "";
		}
		if("%".equals(brandId)){
			brandId = "";
		}
		if("%".equals(regionType)){
			regionType = "";
		}
		if("%".equals(shopId)){
			shopId = "";
		}
		
		params.put("tCode", ra_stat);

		params.put("sDate"    , checkStr(pSdate, ""));
		params.put("eDate"    , checkStr(pEdate, ""));
		
		params.put("compId", compId);					//���޻�ID
		params.put("brandId", brandId);					//�귣��ID
		params.put("shopId", shopId);					//������ID
		params.put("regionType", regionType);			//����ID
		
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int ListCnt = 0;
		if(!"".equals(compId) && compId != null){
			
			//��ȸ
			list = service.selectStatsListComp(params);
			ListCnt = service.selectStatsListCompCnt(params);
		}
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, ListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		request.setAttribute("StatsList", list);
		
		params.put("chk_date", chk_date == null || chk_date.equals("") ? "1d":chk_date);
		params.put("ra_stat", ra_stat == null || ra_stat.equals("") ? "":ra_stat);
		params.put("compId", compId == null || compId.equals("") ? "none":compId);							
		params.put("brandId", brandId == null || brandId.equals("") ? "none":brandId);
		params.put("regionType", regionType);
		params.put("shopId", shopId == null || shopId.equals("") ? "none":shopId);

		request.setAttribute("params", params);
		
		request.setAttribute("sDate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("eDate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		log.debug("### StatsController selectStatsListComp END ###");
		
		return "/harex/stats_list_comp";
	}
	
	/**
	 * ��� > ������ ���޻�
	 * @param 	
	 * @param 	
	 * @param 	
	 * @param 	
	 * @return	list
	 */
	@RequestMapping(value="/harex/stats_report.st")
	public String selectStatsReport(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("### StatsController selectStatsReport START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		StatsService service = new StatsService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		String ra_stat		= checkStr(request.getParameter("ra_stat"), "");
		
		String sMonth 		= request.getParameter("month"); 	// ��ȸ��
		String pMonth			= "";
		
		String compId 		= checkStr(request.getParameter("compId"), "");  		// ���޻�ID
		String brandId 		= checkStr(request.getParameter("brandId"), "");  	// �귣�� ID
		String shopId 		= checkStr(request.getParameter("shopId"), ""); 		// ������ID
		String regionType = checkStr(request.getParameter("regionType"), ""); // ����
				
		if(sMonth == null || "".equals(sMonth)){
			sMonth = JDate.getFormattedDate("yyyyMM");
			pMonth = JDate.getFormattedDate(JDate.getMonth(-1), "yyyyMM");
		} else {
			String tDay = sMonth+"01";
			Date tDate = new Date();
			tDate = JDate.toDate(tDay,"yyyyMMdd");
			pMonth = JDate.getFormattedDate(JDate.getMonth(tDate, -1), "yyyyMM");
		}
	
		if("%".equals(compId)){
			compId = "";
		}
		if("%".equals(brandId)){
			brandId = "";
		}
		if("%".equals(regionType)){
			regionType = "";
		}
		if("%".equals(shopId)){
			shopId = "";
		}
		
		// �Ѿ�� parameter �� ���� group by �� �з� : part(comp:���޻�, brd:�귣��, shop:������)
		String part = "comp";
		if (!"".equals(brandId) && !"".equals(shopId)){
			part = "shop";
		}
		if (!"".equals(brandId) && "".equals(shopId)){
			part = "brd";
		}
		
		if(!"".equals(compId) && compId != null) {
						
			StatsReport cMonInfo = null;
			StatsReport pMonInfo = null;
			
			//�ݿ�
			params.put("tCode"			, ra_stat);
			params.put("compId"			, compId);					//���޻�ID
			params.put("brandId"		, brandId);					//�귣��ID
			params.put("shopId"			, shopId);					//������ID
			params.put("regionType"	, regionType);			//����ID
			params.put("sMonth"    	, checkStr(sMonth, ""));
			params.put("pMonth"    	, "");
			params.put("part", part);
			
			try{
				cMonInfo = service.selectStatsReportCmon(params);
			}catch(Exception  e){
				log.debug("[ERROR] : " + e);
			}
			params.clear();
	
			//����
			params.put("tCode"			, ra_stat);
			params.put("compId"			, compId);					//���޻�ID
			params.put("brandId"		, brandId);					//�귣��ID
			params.put("shopId"			, shopId);					//������ID
			params.put("regionType"	, regionType);			//����ID
			params.put("sMonth"			, "");
			params.put("pMonth"			, checkStr(pMonth, ""));
			params.put("part", part);
			
			try{
				pMonInfo = service.selectStatsReportCmon(params);
			}catch(Exception  e){
				log.debug("[ERROR] : " + e);
			}
			request.setAttribute("CMonInfo", cMonInfo);
			request.setAttribute("PMonInfo", pMonInfo);
			
			//��û�Ǽ�
			int cTtl = 0;
			int pTtl = 0;
			
			if (cMonInfo != null) {
				cTtl = Integer.parseInt(cMonInfo.getTtlCnt());
			} else {
				cTtl = 0;
			}
			
			if (pMonInfo != null) {
				pTtl = Integer.parseInt(pMonInfo.getTtlCnt());
			} else {
				pTtl = 0;
			}
			
			String strTtlCnt = JPercent.getPercentage(cTtl, pTtl);
			
			//�����Ǽ�
			int cSuccessCnt = 0;
			int pSuccessCnt = 0;
			
			if (cMonInfo != null) {
				cSuccessCnt = Integer.parseInt(cMonInfo.getSuccessCnt());
			} else {
				cSuccessCnt = 0;
			}
			
			if (pMonInfo != null) {
				pSuccessCnt = Integer.parseInt(pMonInfo.getSuccessCnt());
			} else {
				pSuccessCnt = 0;
			}
			
			String strSuccessCnt = JPercent.getPercentage(cSuccessCnt, pSuccessCnt);
			
			//���аǼ�
			int cFailCnt = 0;
			int pFailCnt = 0;
			
			if (cMonInfo != null) {
				cFailCnt = Integer.parseInt(cMonInfo.getSuccessCnt());
			} else {
				cFailCnt = 0;
			}
			
			if (pMonInfo != null) {
				pFailCnt = Integer.parseInt(pMonInfo.getFailCnt());
			} else {
				pFailCnt = 0;
			}
			
			String strFailCnt = JPercent.getPercentage(cFailCnt, pFailCnt);
			
			//������û�ݾ�
			int cReqTtlPrice = 0;
			int pReqTtlPrice = 0;
			
			if (cMonInfo != null) {
				cReqTtlPrice = Integer.parseInt(cMonInfo.getReqTtlPrice());
			} else {
				cReqTtlPrice = 0;
			}
			
			if (pMonInfo != null) {
				pReqTtlPrice = Integer.parseInt(pMonInfo.getReqTtlPrice());
			} else {
				pReqTtlPrice = 0;
			}
			
			String strReqTtlPrice = JPercent.getPercentage(cReqTtlPrice, pReqTtlPrice);
			
			//�������ν��αݾ�
			int cCpnAmt = 0;
			int pCpnAmt = 0;
			
			if (cMonInfo != null) {
				cCpnAmt = Integer.parseInt(cMonInfo.getCpnAmt());
			} else {
				cCpnAmt = 0;
			}
			
			if (pMonInfo != null) {
				pCpnAmt = Integer.parseInt(pMonInfo.getCpnAmt());
			} else {
				pCpnAmt = 0;
			}
			
			String strCpnAmt = JPercent.getPercentage(cCpnAmt, pCpnAmt);
			
			//��������ν��αݾ�
			int cMsDcAmt = 0;
			int pMsDcAmt = 0;
			
			if (cMonInfo != null) {
				cMsDcAmt = Integer.parseInt(cMonInfo.getMsDcAmt());
			} else {
				cMsDcAmt = 0;
			}
			
			if (pMonInfo != null) {
				pMsDcAmt = Integer.parseInt(pMonInfo.getMsDcAmt());
			} else {
				pMsDcAmt = 0;
			}
			
			String strMsDcAmt = JPercent.getPercentage(cMsDcAmt, pMsDcAmt);
	
			//���������Ʈ ��� ���αݾ�
			int cMsUseAmt = 0;
			int pMsUseAmt = 0;
			
			if (cMonInfo != null) {
				cMsUseAmt = Integer.parseInt(cMonInfo.getMsDcAmt());
			} else {
				cMsUseAmt = 0;
			}
			
			if (pMonInfo != null) {
				pMsUseAmt = Integer.parseInt(pMonInfo.getMsDcAmt());
			} else {
				pMsUseAmt = 0;
			}
			
			String strMsUseAmt = JPercent.getPercentage(cMsUseAmt, pMsUseAmt);
	
			//���������Ʈ ���� ���αݾ�
			int cMsSaveAmt = 0;
			int pMsSaveAmt = 0;
			
			if (cMonInfo != null) {
				cMsSaveAmt = Integer.parseInt(cMonInfo.getMsSaveAmt());
			} else {
				cMsSaveAmt = 0;
			}
			
			if (pMonInfo != null) {
				pMsSaveAmt = Integer.parseInt(pMonInfo.getMsSaveAmt());
			} else {
				pMsSaveAmt = 0;
			}
			
			String strMsSaveAmt = JPercent.getPercentage(cMsSaveAmt, pMsSaveAmt);
	
			//������ ����
			int cMsStampAmt = 0;
			int pMsStampAmt = 0;
			
			if (cMonInfo != null) {
				cMsStampAmt = Integer.parseInt(cMonInfo.getMsStampAmt());
			} else {
				cMsStampAmt = 0;
			}
			
			if (pMonInfo != null) {
				pMsStampAmt = Integer.parseInt(pMonInfo.getMsStampAmt());
			} else {
				pMsStampAmt = 0;
			}
			
			String strMsStampAmt = JPercent.getPercentage(cMsStampAmt, pMsStampAmt);
			
			request.setAttribute("strTtlCnt", strTtlCnt);
			request.setAttribute("strSuccessCnt", strSuccessCnt);
			request.setAttribute("strFailCnt", strFailCnt);
			request.setAttribute("strReqTtlPrice", strReqTtlPrice);
			request.setAttribute("strCpnAmt", strCpnAmt);
			request.setAttribute("strMsDcAmt", strMsDcAmt);
			request.setAttribute("strMsUseAmt", strMsUseAmt);
			request.setAttribute("strMsSaveAmt", strMsSaveAmt);
			request.setAttribute("strMsStampAmt", strMsStampAmt);
		
		}
		params.put("ra_stat", ra_stat == null || ra_stat.equals("") ? "":ra_stat);
		params.put("compId", compId == null || compId.equals("") ? "none":compId);							
		params.put("brandId", brandId == null || brandId.equals("") ? "none":brandId);
		params.put("regionType", regionType);
		params.put("shopId", shopId == null || shopId.equals("") ? "none":shopId);
		
		request.setAttribute("params", params);
		request.setAttribute("sMonth", sMonth);
		
		log.debug("### StatsController selectStatsReport END ###");
		
		return "/harex/stats_report";
	}
}
