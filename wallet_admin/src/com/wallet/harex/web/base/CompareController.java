package com.wallet.harex.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.harex.common.JDate;
import com.wallet.harex.model.Compare;
import com.wallet.harex.service.CompareService;
import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;

@Controller
public class CompareController extends CommonController {

	private final String PAGE_CODE = "COMPARE_PAGE";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * ������
	 * @param 	
	 * @param 	
	 * @return		list			���������
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/harex/compare_list.hx")
	public String selectCompareList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.debug("### Compare selectCompareList START ###");
		
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<Compare> list = null;
		String target = "harex/compare_list";  // ��� ������ url
		CompareService service = new CompareService();
		HashMap<String,Object> params = new HashMap<String,Object>();
		String today = "";
		String fDate = "";
		
		JDate jDate = new JDate();
		today =  jDate.getDate().substring(0, 10).replaceAll(":","");
		fDate = today.substring(0, 6) + "01";
		
		
		String pSdate		= request.getParameter("sdate"); // ������
		String pEdate	 	= request.getParameter("edate"); // ������
		
		pSdate = pSdate == null || "".equals(pSdate) ? fDate : pSdate.replace("-", "");
		pEdate = pEdate == null || "".equals(pEdate) ? today : pEdate.replace("-", "");
		
		params.put("sdate", pSdate);
		params.put("edate", pEdate);
		
		String excel		= checkStr(request.getParameter("excel"), "N");  // ������� ����
		
		String payType	= checkStr(request.getParameter("pay_type"), "C"); // ���հ����׸� (C:����/M:�����)
		String pstatus	= checkStr(request.getParameter("pstatus"), "PS");  // PS:���ο�û / SC:��ҿ�û
		
		String pCompId 	= checkStr(request.getParameter("comp_id"), "");  // ���޻�
		String pBrandId 	= checkStr(request.getParameter("brand_id"), ""); // �귣��
		String pRgType  	= checkStr(request.getParameter("region_type"), "");  // ����
		String pKShopId 	= checkStr(request.getParameter("shopSel"), ""); // ������
		
		params.put("compId", pCompId 	== null || "".equals(pCompId)  ? "none":pCompId);
		params.put("brandId", pBrandId 	== null || "".equals(pBrandId)  ? "none":pBrandId);
		params.put("shopSel", pKShopId 	== null || "".equals(pKShopId) ? "none":pKShopId);
		params.put("region_type", pRgType == null || "".equals(pRgType) ? "none":pRgType);
		
		params.put("pstatus"   , pstatus);
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;	
		params.put("startRow", String.valueOf(startRow));		
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int listCnt = 0; 
		
		//��ȸ
		
		//excel
		if("Y".equals(excel)){
			target = "harex/compare_list_excel";
			if("C".equals(payType)){
				list = service.selectCompareListExcel(params);
				request.setAttribute("fileName", "������-����");
			}		
			if("M".equals(payType)){
				list = service.selectCompareMemListExcel(params);
				request.setAttribute("fileName", "������-�����");
			}

		} else {
			if("C".equals(payType)){
				list = service.selectCompareList(params);
				listCnt = service.selectCompareListCnt(params);
			}
			if("M".equals(payType)){
				list = service.selectCompareMemList(params);
				listCnt = service.selectCompareMemListCnt(params);
			}
		}
		
		////����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		params.put("payType", payType);
		
		request.setAttribute("pageCode", PAGE_CODE);
		
		request.setAttribute("List", list);	
		request.setAttribute("params", params);
		
		request.setAttribute("sdate", pSdate.substring(0, 4) + "-" + pSdate.substring(4, 6) + "-" + pSdate.substring(6, 8));
		request.setAttribute("edate", pEdate.substring(0, 4) + "-" + pEdate.substring(4, 6) + "-" + pEdate.substring(6, 8));
		
		//�α�ó��/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		HashMap<String,Object> logParams = new HashMap<String,Object>();
		MwAdAccessLogService logSvr = new MwAdAccessLogService();

		try {

			params.put("PageURL", request.getRequestURL());
			
			logParams.put("part", "HAREX");
			logParams.put("admin_id", getSessionMgrId(request));
			logParams.put("msg", params.toString());
			
			logSvr.insertAccessLogReg(logParams);
			
			logSvr.commit();
		} catch (Exception e) {
			logSvr.rollback();
		}
		//�α�ó��/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		log.debug("### Compare selectCompareList  END ###");
		
		return target;
	}
}
