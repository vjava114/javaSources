package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.admin.service.MwAdAccessLogService;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.BARCODE_GEN;
import com.wallet.membership.common.BarcodeUtil;
import com.wallet.membership.common.ExcelContoller;
import com.wallet.membership.common.UpFile;
import com.wallet.membership.model.CpnListAll;
import com.wallet.membership.model.MwCmImage;
import com.wallet.membership.model.MwCmImageExample;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsCpnregHist;
import com.wallet.membership.model.UserInfo;
import com.wallet.membership.model.custom.Coupon;
import com.wallet.membership.model.custom.CouponIssue;
import com.wallet.membership.service.MwCmImageService;
import com.wallet.membership.service.custom.ComplexCouponService;
import com.wallet.membership.service.custom.CouponService;
import com.wallet.membership.service.custom.MemberService;

@Controller
public class CouponController extends CommonController {
	private final String PAGE_CODE = "COUPON_PROCESS";
	private Logger log = Log.getLogger("logs");
	private MwAdAccessLogService logSVC = new MwAdAccessLogService();
	KTDBCipher ktService = new KTDBCipher();
	
	/**
	 * ���� ����Ʈ 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_list.ms")
	public String couponList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponList ##########");

		// �Ķ���� �ʱ�ȭ 
		String partV = checkStr(request.getParameter("partV"), "%");
		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		String barIssueType = checkStr(request.getParameter("barIssueType"), "%");
		String barConfType = checkStr(request.getParameter("barConfType"), "%");
		String cpnValidYn = checkStr(request.getParameter("cpnValidYn"), "%");
		String cpnIssueStat = checkStr(request.getParameter("cpnIssueStat"), "%");
		String mainDisplay = checkStr(request.getParameter("mainDisplay"), "%");
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("partV", partV);									//��������
		params.put("compName", compName);						//���޻��
		params.put("cpnName", cpnName);							//������
		params.put("barIssueType", barIssueType);		//������ȣ����
		params.put("barConfType", barConfType);			//�������ο���
		params.put("cpnValidYn", cpnValidYn);				//��ȿ����
		params.put("cpnIssueStat", cpnIssueStat);		//�����������
		params.put("mainDisplay", mainDisplay);			//�������û���
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d"));
		
		String today = today();
		String sdate = checkStr(request.getParameter("sdate"), today);
		String edate = checkStr(request.getParameter("edate"), today);

		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", sdate.replace("-", ""));
			params.put("edate", edate.replace("-", ""));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// ��� ��ȸ //
		CouponService couponService = new CouponService();
		List<Coupon> couponList = couponService.selectCouponList(params); 	// �����ȸ
		int couponListCnt = couponService.selectCouponListCnt(params); 		// �� ��� ��
		System.out.println(">> couponList : "+couponList);
		

		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, couponListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("partV", partV);
		request.setAttribute("compName", compName);
		request.setAttribute("cpnName", cpnName);
		request.setAttribute("barIssueType", barIssueType);
		request.setAttribute("barConfType", barConfType);
		request.setAttribute("cpnValidYn", cpnValidYn);
		request.setAttribute("cpnIssueStat", cpnIssueStat);
		request.setAttribute("mainDisplay", mainDisplay);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		
		// ��� �ѱ��
		request.setAttribute("dataList", couponList);
		
		
		return "member/coupon_list";
	}
	

	/**
	 * ������������ [�˾�]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_issue_pop.ms")
	public String couponIssuePop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponIssuePop ##########");

		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		String excel = checkStr(request.getParameter("excel"), "N");
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO ���� /////////////////////////////////////////////////////////////
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);									//����Id

		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1;
		// ���� ȣ���� ��� ����¡ ó�� ����
		if(!"Y".equals(excel)) {
			params.put("startRow", String.valueOf(startRow));
			params.put("endRow", String.valueOf(endRow));
			params.put("rowsPerPage", String.valueOf(rowsPerPage));
		}
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		// ��� ��ȸ //
		CouponService couponService = new CouponService();
		List<CouponIssue> dataList = couponService.selectCouponIssueList(params); 	// ������������ ��ȸ
		for(int i=0; i<dataList.size(); i++){
			String userNm = dataList.get(i).getUserName();
			userNm = ktService.decoding(userNm);
			dataList.get(i).setUserName(userNm);
		}
		int listCnt = couponService.selectCouponIssueListCnt(params); 		// �� ����
		System.out.println(">> dataList : "+dataList);
		

		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("cpnId", cpnId);
		
		// ��� �ѱ��
		request.setAttribute("dataList", dataList);
		
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		params.put("pageURL", "/member/coupon_issue_pop.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		
		
		String target = "member/coupon_issue_pop";
		// ���� ������ ���.. ��� ���� 
		if("Y".equals(excel))
			target = "member/coupon_issue_excel";
		return target;
	}
	

	/**
	 * ����� ���� ���
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_regist.ms")
	public String couponRegist(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponRegist ##########");

		// ����� - TODO ���� ó��
		String regUser = getSessionMgrId(request);
		String regUserNm = getSessionName(request);
		Date regDtm = new Date();
		String regDtmStr = new SimpleDateFormat("yyyy-MM-dd").format(regDtm);

		String insert = checkStr(request.getParameter("insert"), "");
		if("Y".equals(insert)) {	//�����
			// �Ķ���� �ʱ�ȭ 
			String partV = checkStr(request.getParameter("partV"), "");	// ���� �뱸��
			String compId = checkStr(request.getParameter("compId"), "");	// ���޻�ID
			String branId = checkStr(request.getParameter("branId"), "");	// �귣��ID
			String membId = checkStr(request.getParameter("membId"), "");	// �����ID

			String manualCpnId = checkStr(request.getParameter("manualCpnId"), "");	// �����Է� ���� ID
			String isDupCpnId = checkStr(request.getParameter("isDupCpnId"), "");	// �����Է� ���� ID
			
			String cpnName = checkStr(request.getParameter("cpnName"), "");	// ������
			String useName = checkStr(request.getParameter("useName"), "");	// ���ó
			String payFreeYn = checkStr(request.getParameter("rpayFreeYn"), "");	// �������� (�����ᱸ��)
			String usimMode = checkStr(request.getParameter("usimMode"), "");	// USIM��������
			String usimId = checkStr(request.getParameter("usimId"), "");	// USIM ID
			String barConfType = checkStr(request.getParameter("rbarConfType"), "");	// �������α���
			String cpnIssueType = checkStr(request.getParameter("cpnIssueType"), "");	// ������������
			String autoIssueTarget = checkStr(request.getParameter("autoIssueTarget"), "");	// Ÿ�ٹ�����������
			
			String autoIssueSday = "";
			String autoIssueEday = "";
			if(cpnIssueType.equals("04")){ //--���� ���� ����(04:�̺�Ʈ ����_
				autoIssueSday = checkStr(request.getParameter("eventIssueSday"), "").replaceAll("-", "");	// �ٿ�ε� ���� �ű԰��ԱⰣ ������
				autoIssueEday = checkStr(request.getParameter("eventIssueEday"), "").replaceAll("-", "");	// �ٿ�ε� ���� �ű԰��ԱⰣ ������
				autoIssueTarget = "";
			}else{
				autoIssueSday = checkStr(request.getParameter("autoIssueSday"), "").replaceAll("-", "");	// �ڵ���ϱⰣ ������
				autoIssueEday = checkStr(request.getParameter("autoIssueEday"), "").replaceAll("-", "");	// �ڵ���ϱⰣ ������
			}
			
			String barIssueType = checkStr(request.getParameter("barIssueType"), "");	// ������ȣ ���౸��
			String cmConfBarcd = checkStr(request.getParameter("cmConfBarcd"), "");	// ��������������ȣ
			String compPayYn = checkStr(request.getParameter("compPayYn"), "");	// ���հ��� ��뱸��
			String mainDisplay = checkStr(request.getParameter("mainDisplay"), "");	// �������ñ���
			if(cpnIssueType.equals("02") || cpnIssueType.equals("01")){  //--���� ���� ����(01:Admin ���� ����, 02:�ڵ� ����)
				mainDisplay = "N";
			}
			String sday = checkStr(request.getParameter("sday"), "").replaceAll("-", "");	// ���������Ⱓ ������
			String eday = checkStr(request.getParameter("eday"), "").replaceAll("-", "");	// ���������Ⱓ ������
			String valSday = checkStr(request.getParameter("valSday"), "").replaceAll("-", "");	// ������ȿ�Ⱓ ������
			String valEday = checkStr(request.getParameter("valEday"), "").replaceAll("-", "");	// ������ȿ�Ⱓ ������
			String info = checkStr(request.getParameter("info"), "");	// �����󼼼���
			String cpnMaxIssueCnt = checkStr(request.getParameter("cpnMaxIssueCnt"), "");	// �����ִ������
			String cpnMaxUseCnt = checkStr(request.getParameter("cpnMaxUseCnt"), "");	//�����ִ���ȸ��
			String usableCntYn = checkStr(request.getParameter("usableCntYn"), "");	// ���ȸ�� ��뱸��
			String yearUsableCnt = checkStr(request.getParameter("yearUsableCnt"), "");	// �� ��� ����Ƚ��
			String monthUsableCnt = checkStr(request.getParameter("monthUsableCnt"), "");	// �� ��� ����Ƚ��
			String dayUsableCnt = checkStr(request.getParameter("dayUsableCnt"), "");	// �� ��� ����Ƚ��
			String dayIssueYn = checkStr(request.getParameter("dayIssueYn"), "");	// �Ϲ������� ��뱸��
			String dayIssueMaxCnt = checkStr(request.getParameter("dayIssueMaxCnt"), "");	// �Ϲ�������
			String dayIssueStime = checkStr(request.getParameter("dayIssueStime"), "");	// �Ϲ��� ���۹��� ����(�ð�)
			String dayIssueSmin = checkStr(request.getParameter("dayIssueSmin"), "");	// �Ϲ��� ���۹��� ����(��)
			String dayIssueEtime = checkStr(request.getParameter("dayIssueEtime"), "");	// �Ϲ��� ���۹��� ����(�ð�)
			String dayIssueEmin = checkStr(request.getParameter("dayIssueEmin"), "");	// �Ϲ��� ���۹��� ����(��)
			String onoffType = checkStr(request.getParameter("onoffType"), "");	// ���� ��/�������� ��뱸��
			String onlineUrl = checkStr(request.getParameter("onlineUrl"), "");	//�¶���URL
			String usimCompId = checkStr(request.getParameter("usimCompId"), ""); //USIM ���޻�ID
			String usimBranId = checkStr(request.getParameter("usimBranId"), ""); //USIM �迭��ID
			String membCompulsoryYn = checkStr(request.getParameter("membCompulsoryYn"), ""); // ����� ���� �������
			// �̹��� START
//			String i3gsList = checkStr(request.getParameter("i3gsList"), "");	// �����̹��� ����Ʈ �̹���(3GS)
//			String i4sList = checkStr(request.getParameter("i4sList"), "");	// �����̹��� ����Ʈ �̹���(4S)
//			String a1List = checkStr(request.getParameter("a1List"), "");	// �����̹��� ����Ʈ �̹���(�ȵ���̵�)
//			String i3gsDetail = checkStr(request.getParameter("i3gsDetail"), "");	// �����̹��� �� �̹���(3GS)
//			String i4sDetail = checkStr(request.getParameter("i4sDetail"), "");	// �����̹��� �� �̹���(4S)
//			String a1Detail = checkStr(request.getParameter("a1Detail"), "");	// �����̹��� �� �̹���(�ȵ���̵�)
//			String i3gsIntro = checkStr(request.getParameter("i3gsIntro"), "");	// �����̹��� �����Ұ� �̹���(3GS)
//			String i4sIntro = checkStr(request.getParameter("i4sIntro"), "");	// �����̹��� �����Ұ� �̹���(4S)
//			String a1Intro = checkStr(request.getParameter("a1Intro"), "");	// �����̹��� �����Ұ� �̹���(�ȵ���̵�)
			// �̹��� END
			String calculYn = checkStr(request.getParameter("calculYn"), "");	// �������꿩��
			String memo = checkStr(request.getParameter("memo"), "");	// �޸�
			String recoverYn = checkStr(request.getParameter("recoverYn"), "");	// �������ɿ���
			
			String isTargetCpn = checkStr(request.getParameter("isTargetCpn"), "N"); // Ÿ������ ���� , �߰��Ǹ鼭 �⺻ ������ ��� N ���� ������ ������.
			
			// TODO ���� /////////////////////////////////////////////////////////////
			System.out.println("partV : "+ partV);	// �뱸��
			System.out.println("compId : "+ compId);	// ���޻�ID
			System.out.println("branId : "+ branId);	// �귣��ID
			System.out.println("membId : "+ membId);	// �����ID
			System.out.println("cpnName : "+ cpnName);	// ������
			
			System.out.println("manualCpnId : "+ manualCpnId);	// �����Է�����ID
			System.out.println("isDupCpnId : "+ isDupCpnId);	//�����ߺ�����  0:�ߺ��ƴ�, 1: �ߺ�, "" : ��������id�Է����� �ƴ�
			
			System.out.println("useName : "+ useName);	// ���ó
			System.out.println("payFreeYn : "+ payFreeYn);	// �������� (�����ᱸ��)
			System.out.println("usimMode : "+ usimMode);	// USIM��������
			System.out.println("usimId : "+ usimId);	// USIM ID
			System.out.println("barConfType : "+ barConfType);	// �������α���
			System.out.println("cpnIssueType : "+ cpnIssueType);	// ������������
			System.out.println("autoIssueTarget : "+ autoIssueTarget);	// Ÿ�ٹ�����������
			System.out.println("autoIssueSday : "+ autoIssueSday);	// �ڵ���ϱⰣ ������
			System.out.println("autoIssueEday : "+ autoIssueEday);	// �ڵ���ϱⰣ ������
			System.out.println("barIssueType : "+ barIssueType);	// ������ȣ ���౸��
			System.out.println("cmConfBarcd : "+ cmConfBarcd);	// ��������������ȣ
			System.out.println("compPayYn : "+ compPayYn);	// ���հ��� ��뱸��
			System.out.println("mainDisplay : "+ mainDisplay);	// �������ñ���
			System.out.println("sday : "+ sday);	// ���������Ⱓ ������
			System.out.println("eday : "+ eday);	// ���������Ⱓ ������
			System.out.println("valSday : "+ valSday);	// ������ȿ�Ⱓ ������
			System.out.println("valEday : "+ valEday);	// ������ȿ�Ⱓ ������
			System.out.println("info : "+ info);	// �����󼼼���
			System.out.println("cpnMaxIssueCnt : "+ cpnMaxIssueCnt);	// �����ִ������
			System.out.println("usableCntYn : "+ usableCntYn);	// ���ȸ�� ��뱸��
			System.out.println("yearUsableCnt : "+ yearUsableCnt);	// �� ��� ����Ƚ��
			System.out.println("monthUsableCnt : "+ monthUsableCnt);	// �� ��� ����Ƚ��
			System.out.println("dayUsableCnt : "+ dayUsableCnt);	// �� ��� ����Ƚ��
			System.out.println("dayIssueYn : "+ dayIssueYn);	// �Ϲ������� ��뱸��
			System.out.println("dayIssueMaxCnt : "+ dayIssueMaxCnt);	// �Ϲ�������
			System.out.println("dayIssueStime : "+ dayIssueStime);	// �Ϲ��� ���۹��� ����(�ð�)
			System.out.println("dayIssueSmin : "+ dayIssueSmin);	// �Ϲ��� ���۹��� ����(��)
			System.out.println("dayIssueEtime : "+ dayIssueEtime);	// �Ϲ��� ���۹��� ����(�ð�)
			System.out.println("dayIssueEmin : "+ dayIssueEmin);	// �Ϲ��� ���۹��� ����(��)
			System.out.println("onoffType : "+ onoffType);	// ���� ��/�������� ��뱸��
			System.out.println("onlineUrl : "+ onlineUrl);	// �¶���URL
//			System.out.println("i3gsList : "+ i3gsList);	// �����̹��� ����Ʈ �̹���(3GS)
//			System.out.println("i4sList : "+ i4sList);	// �����̹��� ����Ʈ �̹���(4S)
//			System.out.println("a1List : "+ a1List);	// �����̹��� ����Ʈ �̹���(�ȵ���̵�)
//			System.out.println("i3gsDetail : "+ i3gsDetail);	// �����̹��� �� �̹���(3GS)
//			System.out.println("i4sDetail : "+ i4sDetail);	// �����̹��� �� �̹���(4S)
//			System.out.println("a1Detail : "+ a1Detail);	// �����̹��� �� �̹���(�ȵ���̵�)
//			System.out.println("i3gsIntro : "+ i3gsIntro);	// �����̹��� �����Ұ� �̹���(3GS)
//			System.out.println("i4sIntro : "+ i4sIntro);	// �����̹��� �����Ұ� �̹���(4S)
//			System.out.println("a1Intro : "+ a1Intro);	// �����̹��� �����Ұ� �̹���(�ȵ���̵�)
			System.out.println("calculYn : "+ calculYn);	// �������꿩��
			System.out.println("memo : "+ memo);	// �޸�
			System.out.println("recoverYn : "+ recoverYn);	// �������ɿ���
			System.out.println("isTargetCpn : "+ isTargetCpn);	// Ÿ����������
			// TODO ���� /////////////////////////////////////////////////////////////
			
			
			// �����&���հ��� �Ǵ� �Ϲ�/�̺�Ʈ���� ��Ͻ� �����ID
			if( "R".equals(partV)){ //-- �ؿ������� ���
				membId = "prix";
			}
			else if( !"M".equals(partV) ) { //-- �̺�Ʈ�����ΰ��, 
				membId = "wallet";
			}
			/* ==> [2012.11.23 trkim] ���հ������� �ʵ忡 A �ڵ尡 �߰��Ǹ鼭 ubpay�� �����ϴ� ���� ��
			if( "M".equals(partV) && "Y".equals(compPayYn) ) {
				membId = "ubpay";
			}
			if( "E".equals(partV) && "Y".equals(compPayYn) ) {
				membId = "ubpay";
			}
			*/
			
			// ��ȸ���ǰ� ����
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("partV", partV);	// ���� �뱸�� 
			params.put("compId", compId);	// ���޻�ID
			params.put("branId", branId);	// �귣��ID
			params.put("membId", membId);	// �����ID
			
			params.put("manualCpnId", manualCpnId); // ����id�Է�����
			params.put("isDupCpnId", isDupCpnId); // ����id�Է����� �ߺ����� 
			
			params.put("cpnName", cpnName);	// ������
			params.put("useName", useName);	// ���ó
			params.put("payFreeYn", payFreeYn);	// �������� (�����ᱸ��)
			params.put("usimMode", usimMode);	// USIM��������
			params.put("usimId", usimId);	// USIM ID
			params.put("barConfType", barConfType);	// �������α���
			params.put("cpnIssueType", cpnIssueType);	// ������������
			params.put("autoIssueTarget", autoIssueTarget);	// Ÿ�ٹ�����������
			params.put("autoIssueSday", autoIssueSday);	// �ڵ���ϱⰣ ������
			params.put("autoIssueEday", autoIssueEday);	// �ڵ���ϱⰣ ������
			if(partV.equals("R")){
				params.put("barIssueType", "01");	// �ؿ����� ����� �ǽð���ü����(01)�� ����
			}else{
				params.put("barIssueType", barIssueType);	// ������ȣ ���౸��
			}
			params.put("cmConfBarcd", cmConfBarcd);	// ��������������ȣ
			params.put("compPayYn", compPayYn);	// ���հ��� ��뱸��
			params.put("mainDisplay", mainDisplay);	// �������ñ���
			params.put("sday", sday);	// ���������Ⱓ ������
			params.put("eday", eday);	// ���������Ⱓ ������
			params.put("valSday", valSday);	// ������ȿ�Ⱓ ������
			params.put("valEday", valEday);	// ������ȿ�Ⱓ ������
			params.put("info", info);	// �����󼼼���
			params.put("cpnMaxIssueCnt", cpnMaxIssueCnt);	// �����ִ������
			params.put("cpnMaxUseCnt", cpnMaxUseCnt); // �����ִ���ȸ��
			params.put("usableCntYn", usableCntYn);	// ���ȸ�� ��뱸��
			params.put("yearUsableCnt", yearUsableCnt);	// �� ��� ����Ƚ��
			params.put("monthUsableCnt", monthUsableCnt);	// �� ��� ����Ƚ��
			params.put("dayUsableCnt", dayUsableCnt);	// �� ��� ����Ƚ��
			params.put("dayIssueYn", dayIssueYn);	// �Ϲ������� ��뱸��
			params.put("dayIssueMaxCnt", dayIssueMaxCnt);	// �Ϲ�������
			params.put("dayIssueStime", dayIssueStime+dayIssueSmin);	// �Ϲ��� ���۹��� ����(�ð�)
//			params.put("dayIssueSmin", dayIssueSmin);	// �Ϲ��� ���۹��� ����(��)
			params.put("dayIssueEtime", dayIssueEtime+dayIssueEmin);	// �Ϲ��� ���۹��� ����(�ð�)
//			params.put("dayIssueEmin", dayIssueEmin);	// �Ϲ��� ���۹��� ����(��)
			params.put("onoffType", onoffType);	// ���� ��/�������� ��뱸��
			params.put("onlineUrl", onlineUrl);	// �¶���URL
//			params.put("i3gsList", i3gsList);	// �����̹��� ����Ʈ �̹���(3GS)
//			params.put("i4sList", i4sList);	// �����̹��� ����Ʈ �̹���(4S)
//			params.put("a1List", a1List);	// �����̹��� ����Ʈ �̹���(�ȵ���̵�)
//			params.put("i3gsDetail", i3gsDetail);	// �����̹��� �� �̹���(3GS)
//			params.put("i4sDetail", i4sDetail);	// �����̹��� �� �̹���(4S)
//			params.put("a1Detail", a1Detail);	// �����̹��� �� �̹���(�ȵ���̵�)
//			params.put("i3gsIntro", i3gsIntro);	// �����̹��� �����Ұ� �̹���(3GS)
//			params.put("i4sIntro", i4sIntro);	// �����̹��� �����Ұ� �̹���(4S)
//			params.put("a1Intro", a1Intro);	// �����̹��� �����Ұ� �̹���(�ȵ���̵�)
			params.put("calculYn", calculYn);	// �������꿩��
			params.put("memo", memo);	// �޸�
			params.put("regUser", regUser);	//�����
			params.put("recoverYn", recoverYn);	//�������ɿ���
			params.put("usimCompId", usimCompId);
			params.put("usimBranId", usimBranId);
			params.put("membCompulsoryYn", membCompulsoryYn);
			params.put("isTargetCpn", isTargetCpn);
			
			
			CouponService couponService = null;
			try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;
			// cpn_list �Է�
			result = couponService.insertCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// mw_cm_cpn_list �Է� 
			result = couponService.insertMwCsCouponList(params);
			System.out.println(">> 2 step result cnt : "+result);
			
			
			log.debug("@@@@@@@@@@@@@@@@@@@@@@ CouponRegisterAct params : "+ params);
			// ����ID ��ȸ
			String cpnId = "";
			if(manualCpnId.equals("")){
				cpnId = couponService.selectLastCpnId();
			}
			else{ //-- �������� ���� id�� �Է��� ���,
				cpnId = manualCpnId;
			}
			
			// �̹��� mw_cm_image �Է�
			MwCmImageService mwCmImageService = new MwCmImageService();
			String[] imageArr = request.getParameterValues("_upImgList");
			for(int i=0; imageArr!=null && i<imageArr.length; i++) {
				String one = imageArr[i];
				String oneSplit[] = one.split("[|]");
				
				String os = oneSplit[0];
				String part = oneSplit[1];
				String urlPath = oneSplit[2];
				String id = cpnId;
				String level = "";
				String imageHost = PropertiesUtil.get("img_host");
				
				System.out.println("os : " + os);
				System.out.println("part : " + part);
				System.out.println("urlPath : " + urlPath);
				System.out.println("id : " + id);
				System.out.println("level : " + level);
				System.out.println("imageHost : " + imageHost);
				System.out.println("regUser : " + regUser);
				
				
				MwCmImage mwCmImage = new MwCmImage();
				mwCmImage.setId(id);
				mwCmImage.setOsType(os);
				mwCmImage.setUseType(part);
				mwCmImage.setLevel(level);
				mwCmImage.setImageHost(imageHost);
				mwCmImage.setImageUrl(urlPath);
				mwCmImage.setUseYn("Y");
				mwCmImage.setRegUser(regUser);
				mwCmImage.setRegDtm(regDtm);
				// DB insert
				log.debug("@@@@@@@@@@@@@@@@@@@@@@@@ CouponImageRegist  : "+ mwCmImage.toString());
				mwCmImageService.insert(mwCmImage);
			}
			
			
			// commit
			couponService.commit();
			

			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			
			// �Ķ��Ÿ ���� �״�� �ѱ��
//			request.setAttribute("membId", membId);
			
			// ��� �ѱ��
//			request.setAttribute("dataList", dataList);
			
			}
			catch(Exception e) {
				// rollback
				couponService.rollback();
				e.printStackTrace();
				log.debug(e);
			}
			finally {
				
			}
			
			return "/common/result_page";//����� �̵� ���.. ����Ʈ?			
		}
		
		
		request.setAttribute("regDtmStr", regDtmStr);
		request.setAttribute("regUser", regUser);
		request.setAttribute("regUserNm", regUserNm);
		
		
		return "member/coupon_regist";
	}
	
	/**
	 * ���������� ��ȸ [����/���� ����ȭ��]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_detail.ms")
	public String couponDetail(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponDetail ##########");

		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO ���� /////////////////////////////////////////////////////////////
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	//����Id

		
		// ��� ��ȸ //
		CouponService couponService = new CouponService();
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// ���������� 
		String regNm = ktService.decoding(cpnListAll.getRegUserNm());
		String chgNm = ktService.decoding(cpnListAll.getChgUserNm());
		cpnListAll.setRegUserNm(regNm);
		cpnListAll.setChgUserNm(chgNm);
		System.out.println(">> cpnListAll : "+cpnListAll);
		
		//-- ���� ���ϸ��� ȭ�鿡 �Ѹ��� ���� �ļ�~!!
		String info1 =  "";
		
		if(cpnListAll.getInfo() != null && !cpnListAll.getInfo().equals("")){
			info1 = cpnListAll.getInfo().substring(cpnListAll.getInfo().lastIndexOf("/") + 1);
		}
		
		request.setAttribute("info1", info1);
		

		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("cpnId", cpnId);
		
		// ��� �ѱ��
		request.setAttribute("cpnListAll", cpnListAll);
		
		return "member/coupon_detail";
	}
	

	/**
	 * ���������� ���� 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_modify.ms")
	public String couponModify(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponModify ##########");

		String chgUser = getSessionMgrId(request);//TODO ����ó�� 
		Date chgDtm = new Date();
		
		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");	// ����ID 
		String compId = checkStr(request.getParameter("compId"), "");	// ���޻�ID
		String branId = checkStr(request.getParameter("branId"), "");	// �귣��ID
		String partV = checkStr(request.getParameter("partV"), "");	// ���� �뱸��
		String membId = checkStr(request.getParameter("membId"), "");	// �����ID
		String cpnName = checkStr(request.getParameter("cpnName"), "");	// ������
		String useName = checkStr(request.getParameter("useName"), "");	// ���ó
		String payFreeYn = checkStr(request.getParameter("rpayFreeYn"), "");	// �������� (�����ᱸ��)
		String usimMode = checkStr(request.getParameter("usimMode"), "");	// USIM��������
		String usimId = checkStr(request.getParameter("usimId"), "");	// USIM ID
		String barConfType = checkStr(request.getParameter("rbarConfType"), "");	// �������α���
		String cpnIssueType = checkStr(request.getParameter("cpnIssueType"), "");	// ������������
		String autoIssueTarget = checkStr(request.getParameter("autoIssueTarget"), "");	// Ÿ�ٹ�����������
		
		String autoIssueSday = "";
		String autoIssueEday = "";
		if(cpnIssueType.equals("04")){
			autoIssueSday = checkStr(request.getParameter("eventIssueSday"), "").replaceAll("-", "");	// �ٿ�ε� ���� �ű԰��ԱⰣ ������
			autoIssueEday = checkStr(request.getParameter("eventIssueEday"), "").replaceAll("-", "");	// �ٿ�ε� ���� �ű԰��ԱⰣ ������
		}else{
			autoIssueSday = checkStr(request.getParameter("autoIssueSday"), "").replaceAll("-", "");	// �ڵ���ϱⰣ ������
			autoIssueEday = checkStr(request.getParameter("autoIssueEday"), "").replaceAll("-", "");	// �ڵ���ϱⰣ ������
		}
		
		String barIssueType = checkStr(request.getParameter("barIssueType"), "");	// ������ȣ ���౸��
		String cmConfBarcd = checkStr(request.getParameter("cmConfBarcd"), "");	// ��������������ȣ
		String compPayYn = checkStr(request.getParameter("compPayYn"), "");	// ���հ��� ��뱸��
		String mainDisplay = checkStr(request.getParameter("mainDisplay"), "");	// �������ñ���
		String sday = checkStr(request.getParameter("sday"), "").replaceAll("-", "");	// ���������Ⱓ ������
		String eday = checkStr(request.getParameter("eday"), "").replaceAll("-", "");	// ���������Ⱓ ������
		String valSday = checkStr(request.getParameter("valSday"), "").replaceAll("-", "");	// ������ȿ�Ⱓ ������
		String valEday = checkStr(request.getParameter("valEday"), "").replaceAll("-", "");	// ������ȿ�Ⱓ ������
		String info = checkStr(request.getParameter("info"), "");	// �����󼼼���
		String cpnMaxIssueCnt = checkStr(request.getParameter("cpnMaxIssueCnt"), "");	// �����ִ������
		String cpnMaxUseCnt = checkStr(request.getParameter("cpnMaxUseCnt"), "");
		String usableCntYn = checkStr(request.getParameter("usableCntYn"), "");	// ���ȸ�� ��뱸��
		String yearUsableCnt = checkStr(request.getParameter("yearUsableCnt"), "");	// �� ��� ����Ƚ��
		String monthUsableCnt = checkStr(request.getParameter("monthUsableCnt"), "");	// �� ��� ����Ƚ��
		String dayUsableCnt = checkStr(request.getParameter("dayUsableCnt"), "");	// �� ��� ����Ƚ��
		String dayIssueYn = checkStr(request.getParameter("dayIssueYn"), "");	// �Ϲ������� ��뱸��
		String dayIssueMaxCnt = checkStr(request.getParameter("dayIssueMaxCnt"), "");	// �Ϲ�������
		String dayIssueStime = checkStr(request.getParameter("dayIssueStime"), "");	// �Ϲ��� ���۹��� ����(�ð�)
		String dayIssueSmin = checkStr(request.getParameter("dayIssueSmin"), "");	// �Ϲ��� ���۹��� ����(��)
		String dayIssueEtime = checkStr(request.getParameter("dayIssueEtime"), "");	// �Ϲ��� ���۹��� ����(�ð�)
		String dayIssueEmin = checkStr(request.getParameter("dayIssueEmin"), "");	// �Ϲ��� ���۹��� ����(��)
		String onoffType = checkStr(request.getParameter("onoffType"), "");	// ���� ��/�������� ��뱸��
		String onlineUrl = checkStr(request.getParameter("onlineUrl"), "");	//�¶���URL
		String calculYn = checkStr(request.getParameter("calculYn"), "");	// �������꿩��
		String memo = checkStr(request.getParameter("memo"), "");	// �޸�
		String cpnIssueStat = checkStr(request.getParameter("cpnIssueStat"), "");	//�������࿩�� 
		String recoverYn = checkStr(request.getParameter("recoverYn"), "");	//�������ɿ���
		String usimCompId = checkStr(request.getParameter("usimCompId"), ""); //USIM ���޻�ID
		String usimBranId = checkStr(request.getParameter("usimBranId"), ""); //USIM �迭��ID
		String membCompulsoryYn = checkStr(request.getParameter("membCompulsoryYn"), ""); // ����� ���� �������
		
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);	// ����ID
		System.out.println("compId : "+ compId);	// ���޻�ID
		System.out.println("branId : "+ branId);	// �귣��ID
		System.out.println("partV : "+ partV);	// �뱸��
		System.out.println("membId : "+ membId);	// �����ID
		System.out.println("cpnName : "+ cpnName);	// ������
		System.out.println("useName : "+ useName);	// ���ó
		System.out.println("payFreeYn : "+ payFreeYn);	// �������� (�����ᱸ��)
		System.out.println("usimMode : "+ usimMode);	// USIM��������
		System.out.println("usimId : "+ usimId);	// USIM ID
		System.out.println("barConfType : "+ barConfType);	// �������α���
		System.out.println("cpnIssueType : "+ cpnIssueType);	// ������������
		System.out.println("autoIssueTarget : "+ autoIssueTarget);	// Ÿ�ٹ�����������
		System.out.println("autoIssueSday : "+ autoIssueSday);	// �ڵ���ϱⰣ ������
		System.out.println("autoIssueEday : "+ autoIssueEday);	// �ڵ���ϱⰣ ������
		System.out.println("barIssueType : "+ barIssueType);	// ������ȣ ���౸��
		System.out.println("cmConfBarcd : "+ cmConfBarcd);	// ��������������ȣ
		System.out.println("compPayYn : "+ compPayYn);	// ���հ��� ��뱸��
		System.out.println("mainDisplay : "+ mainDisplay);	// �������ñ���
		System.out.println("sday : "+ sday);	// ���������Ⱓ ������
		System.out.println("eday : "+ eday);	// ���������Ⱓ ������
		System.out.println("valSday : "+ valSday);	// ������ȿ�Ⱓ ������
		System.out.println("valEday : "+ valEday);	// ������ȿ�Ⱓ ������
		System.out.println("info : "+ info);	// �����󼼼���
		System.out.println("cpnMaxIssueCnt : "+ cpnMaxIssueCnt);	// �����ִ������
		System.out.println("usableCntYn : "+ usableCntYn);	// ���ȸ�� ��뱸��
		System.out.println("yearUsableCnt : "+ yearUsableCnt);	// �� ��� ����Ƚ��
		System.out.println("monthUsableCnt : "+ monthUsableCnt);	// �� ��� ����Ƚ��
		System.out.println("dayUsableCnt : "+ dayUsableCnt);	// �� ��� ����Ƚ��
		System.out.println("dayIssueYn : "+ dayIssueYn);	// �Ϲ������� ��뱸��
		System.out.println("dayIssueMaxCnt : "+ dayIssueMaxCnt);	// �Ϲ�������
		System.out.println("dayIssueStime : "+ dayIssueStime);	// �Ϲ��� ���۹��� ����(�ð�)
		System.out.println("dayIssueSmin : "+ dayIssueSmin);	// �Ϲ��� ���۹��� ����(��)
		System.out.println("dayIssueEtime : "+ dayIssueEtime);	// �Ϲ��� ���۹��� ����(�ð�)
		System.out.println("dayIssueEmin : "+ dayIssueEmin);	// �Ϲ��� ���۹��� ����(��)
		System.out.println("onoffType : "+ onoffType);	// ���� ��/�������� ��뱸��
		System.out.println("onlineUrl : "+ onlineUrl);	// �¶���URL
		System.out.println("calculYn : "+ calculYn);	// �������꿩��
		System.out.println("memo : "+ memo);	// �޸�
		System.out.println("cpnIssueStat : "+ cpnIssueStat);	//�������࿩�� 
		System.out.println("chgUser : "+ chgUser);	// ������  
		System.out.println("chgDtm : "+ chgDtm);	// �����Ͻ�  
		System.out.println("recoverYn : "+ recoverYn);	// �������ɿ���
		// TODO ���� /////////////////////////////////////////////////////////////

		
		// �����&���հ��� �Ǵ� �Ϲ�/�̺�Ʈ���� ��Ͻ� �����ID
		if( "R".equals(partV)){ //-- �ؿ������� ���
			membId = "prix";
		}
		else if( !"M".equals(partV) ) { //-- �̺�Ʈ�����ΰ��, 
			membId = "wallet";
		}
		/* ==> [2012.11.23 trkim] ���հ������� �ʵ忡 A �ڵ尡 �߰��Ǹ鼭 ubpay�� �����ϴ� ���� ��
		if( "M".equals(partV) && "Y".equals(compPayYn) ) {
			membId = "ubpay";
		}
		if( "E".equals(partV) && "Y".equals(compPayYn) ) {
			membId = "ubpay";
		}
		*/
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	// ����ID
		params.put("compId", compId);	// ���޻�ID
		params.put("branId", branId);	// �귣��ID
		params.put("partV", partV);	// ���� �뱸�� 
		params.put("membId", membId);	// �����ID
		params.put("cpnName", cpnName);	// ������
		params.put("useName", useName);	// ���ó
		params.put("payFreeYn", payFreeYn);	// �������� (�����ᱸ��)
		params.put("usimMode", usimMode);	// USIM��������
		params.put("usimId", usimId);	// USIM ID
		params.put("barConfType", barConfType);	// �������α���
		params.put("cpnIssueType", cpnIssueType);	// ������������
		params.put("autoIssueTarget", autoIssueTarget);	// Ÿ�ٹ�����������
		params.put("autoIssueSday", autoIssueSday);	// �ڵ���ϱⰣ ������
		params.put("autoIssueEday", autoIssueEday);	// �ڵ���ϱⰣ ������
		if(partV.equals("R")){
			params.put("barIssueType", "01");	// �ؿ����� ����� �ǽð���ü����(01)�� ����
		}else{
			params.put("barIssueType", barIssueType);	// ������ȣ ���౸��
		}
		params.put("cmConfBarcd", cmConfBarcd);	// ��������������ȣ
		params.put("compPayYn", compPayYn);	// ���հ��� ��뱸��
		params.put("mainDisplay", mainDisplay);	// �������ñ���
		params.put("sday", sday);	// ���������Ⱓ ������
		params.put("eday", eday);	// ���������Ⱓ ������
		params.put("valSday", valSday);	// ������ȿ�Ⱓ ������
		params.put("valEday", valEday);	// ������ȿ�Ⱓ ������
		params.put("info", info);	// �����󼼼���
		params.put("cpnMaxIssueCnt", cpnMaxIssueCnt);	// �����ִ������
		params.put("cpnMaxUseCnt", cpnMaxUseCnt);
		params.put("usableCntYn", usableCntYn);	// ���ȸ�� ��뱸��
		params.put("yearUsableCnt", yearUsableCnt);	// �� ��� ����Ƚ��
		params.put("monthUsableCnt", monthUsableCnt);	// �� ��� ����Ƚ��
		params.put("dayUsableCnt", dayUsableCnt);	// �� ��� ����Ƚ��
		params.put("dayIssueYn", dayIssueYn);	// �Ϲ������� ��뱸��
		params.put("dayIssueMaxCnt", dayIssueMaxCnt);	// �Ϲ�������
		params.put("dayIssueStime", dayIssueStime+dayIssueSmin);	// �Ϲ��� ���۹��� ����(�ð�)
//		params.put("dayIssueSmin", dayIssueSmin);	// �Ϲ��� ���۹��� ����(��)
		params.put("dayIssueEtime", dayIssueEtime+dayIssueEmin);	// �Ϲ��� ���۹��� ����(�ð�)
//		params.put("dayIssueEmin", dayIssueEmin);	// �Ϲ��� ���۹��� ����(��)
		params.put("onoffType", onoffType);	// ���� ��/�������� ��뱸��
		params.put("onlineUrl", onlineUrl);	// �¶���URL
		params.put("calculYn", calculYn);	// �������꿩��
		params.put("memo", memo);	// �޸�
		params.put("cpnIssueStat", cpnIssueStat);	//�������࿩�� 
		params.put("chgUser", chgUser);	//������ 
		params.put("chgDtm", chgDtm);	//������ 
		params.put("recoverYn", recoverYn);	//�������ɿ��� 
		params.put("usimCompId", usimCompId);
		params.put("usimBranId", usimBranId);
		params.put("membCompulsoryYn", membCompulsoryYn);
		
		
		CouponService couponService = null;
		try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;
			// cpn_list ����
			result = couponService.updateCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// mw_cm_cpn_list ����
			result = couponService.updateMwCsCouponList(params);
			System.out.println(">> 2 step result cnt : "+result);
			
			log.debug("@@@@@@@@@@@@@@@@@@@@@@@@ CouponUpdateAct params : "+ params);
			
			// �̹��� mw_cm_image �Է�
			MwCmImageService mwCmImageService = new MwCmImageService();
			String[] imageArr = request.getParameterValues("_upImgList");
			for(int i=0; imageArr!=null && i<imageArr.length; i++) {
				String one = imageArr[i];
				String oneSplit[] = one.split("[|]");
				
				String os = oneSplit[0];
				String part = oneSplit[1];
				String urlPath = oneSplit[2];
				String id = cpnId;
				String level = "";
				String imageHost = PropertiesUtil.get("img_host");
				
				System.out.println("os : " + os);
				System.out.println("part : " + part);
				System.out.println("urlPath : " + urlPath);
				System.out.println("id : " + id);
				System.out.println("level : " + level);
				System.out.println("imageHost : " + imageHost);
				System.out.println("chgUser : " + chgUser);
				
				MwCmImage mwCmImage = new MwCmImage();
				mwCmImage.setId(id);
				mwCmImage.setOsType(os);
				mwCmImage.setUseType(part);
				mwCmImage.setLevel(level);
				mwCmImage.setImageHost(imageHost);
				mwCmImage.setUseYn("Y");
				mwCmImage.setImageUrl(urlPath);
				mwCmImage.setRegUser(chgUser);
				mwCmImage.setRegDtm(chgDtm);
				
				// DB update
				MwCmImageExample mwCmImageExample = new MwCmImageExample();
				mwCmImageExample.or().andIdEqualTo(id).andOsTypeEqualTo(os).andUseTypeEqualTo(part).andLevelEqualTo(level);
			
				log.debug("@@@@@@@@@@@@@@@@@@@@@@@@ CouponImgUpdate params : "+ mwCmImage.toString());
//				mwCmImageService.update(mwCmImage,mwCmImageExample);
				mwCmImageService.delete(mwCmImageExample);
				mwCmImageService.insert(mwCmImage);
			}
			
			
			// commit
			couponService.commit();
			
	
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			

		}
		catch(Exception e) {
			// rollback
			couponService.rollback();
			e.printStackTrace();
			log.debug(e);
		}
		finally {
			
		}
		
		return "/common/result_page";//����� �̵� ���.. ����Ʈ?			
	}
	
	
	
	/**
	 * ���������� ���� 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_delete.ms")
	public String couponDelete(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponDelete ##########");

		String chgUser = getSessionMgrId(request);//TODO ����ó�� 
		Date chgDtm = new Date();
		
		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");	// ����ID
		
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);	// ����ID
		System.out.println("chgUser : "+ chgUser);	// ������  
		System.out.println("chgDtm : "+ chgDtm);	// �����Ͻ�
		// TODO ���� /////////////////////////////////////////////////////////////
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	// ����ID
		params.put("chgUser", chgUser);	//������ 
		params.put("chgDtm", chgDtm);	//������ 
		
		
		CouponService couponService = null;
		ComplexCouponService complexCouponService = null;
		try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;
			// cpn_list ����
			result = couponService.deleteCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// mw_cm_cpn_list ����
			result = couponService.deleteMwCsCouponList(params);
			System.out.println(">> 2 step result cnt : "+result);
			
			//-- ���հ��� ������ ���, ������, ������, ������ ������ ��ϵǾ� �ִ� ���, ���� �ʿ���. //-- by trkim. 2013.03.18
			complexCouponService = new ComplexCouponService();
			complexCouponService.deleteComplexBank(params);
			complexCouponService.deleteComplexPayComp(params);
			complexCouponService.deleteComplexStore(params);
			
			// DB delete (�ش� ���� �̹��� ����)
			MwCmImageService mwCmImageService = new MwCmImageService();
			MwCmImageExample mwCmImageExample = new MwCmImageExample();
			mwCmImageExample.or().andIdEqualTo(cpnId);
			mwCmImageService.delete(mwCmImageExample);

			
			// commit
			couponService.commit();
			complexCouponService.commit();
			
	
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);

		}
		catch(Exception e) {
			// rollback
			couponService.rollback();
			e.printStackTrace();
			log.debug(e);
		}
		finally {
			
		}
		
		return "/common/result_page";//����� �̵� ���.. ����Ʈ?			
	}

	/**
	 * ���� ���� ����Ʈ 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_approval_list.ms")
	public String couponApprovalList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponApprovalList ##########");

		// �Ķ���� �ʱ�ȭ 
		String partV = checkStr(request.getParameter("partV"), "%");
		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		String cpnStat = checkStr(request.getParameter("cpnStat"), "all");
//		String barIssueType = checkStr(request.getParameter("barIssueType"), "%");
//		String barConfType = checkStr(request.getParameter("barConfType"), "%");
//		String cpnValidYn = checkStr(request.getParameter("cpnValidYn"), "%");
//		String cpnIssueStat = checkStr(request.getParameter("cpnIssueStat"), "%");
//		String mainDisplay = checkStr(request.getParameter("mainDisplay"), "%");
		String rSearchTerm = checkStr(request.getParameter("rSearchTerm"), "all");
		String sdate = checkStr(request.getParameter("sdate"), "");
		String edate = checkStr(request.getParameter("edate"), "");
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("partV: "+partV);
		System.out.println("compName: "+compName);
		System.out.println("cpnName: "+cpnName);
		System.out.println("cpnStat: "+cpnStat);
//		System.out.println("barIssueType: "+barIssueType);
//		System.out.println("barConfType: "+barConfType);
//		System.out.println("cpnValidYn: "+cpnValidYn);
//		System.out.println("cpnIssueStat: "+cpnIssueStat);
//		System.out.println("mainDisplay: "+mainDisplay);
		System.out.println("rSearchTerm: "+rSearchTerm);
		System.out.println("sdate: "+sdate);
		System.out.println("edate: "+edate);
		// TODO ���� /////////////////////////////////////////////////////////////

		String sdateNoHyphen = sdate.replaceAll("-","");
		String edateNoHyphen = edate.replaceAll("-","");
		log.debug("sdateNoHyphen: "+sdateNoHyphen);
		log.debug("edateNoHyphen: "+edateNoHyphen);
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("partV", partV);									//��������
		params.put("compName", compName);						//���޻��
		params.put("cpnName", cpnName);							//������
		params.put("cpnStat", cpnStat);							//���λ���
//		params.put("barIssueType", barIssueType);		//������ȣ����
//		params.put("barConfType", barConfType);			//�������ο���
//		params.put("cpnValidYn", cpnValidYn);				//��ȿ����
//		params.put("cpnIssueStat", cpnIssueStat);		//�����������
//		params.put("mainDisplay", mainDisplay);			//�������û���
		params.put("rSearchTerm", rSearchTerm);			//�Ⱓ(������)
		params.put("sdate", sdateNoHyphen);					//�Ⱓ ������
		params.put("edate", edateNoHyphen);					//�Ⱓ ������

		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// ��� ��ȸ //
		CouponService couponService = new CouponService();
		List<Coupon> couponList = couponService.selectCouponApprList(params); 	// �����ȸ
		int couponListCnt = couponService.selectCouponApprCnt(params); 		// �� ��� ��
		System.out.println(">> couponList : "+couponList);
		

		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, couponListCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("partV", partV);
		request.setAttribute("compName", compName);
		request.setAttribute("cpnName", cpnName);
		request.setAttribute("cpnStat", cpnStat);
//		request.setAttribute("barIssueType", barIssueType);
//		request.setAttribute("barConfType", barConfType);
//		request.setAttribute("cpnValidYn", cpnValidYn);
//		request.setAttribute("cpnIssueStat", cpnIssueStat);
//		request.setAttribute("mainDisplay", mainDisplay);
		request.setAttribute("rSearchTerm", rSearchTerm);
		request.setAttribute("sdate", sdate);
		request.setAttribute("edate", edate);
		
		// ��� �ѱ��
		request.setAttribute("dataList", couponList);
		
		
		return "member/coupon_approval_list";
	}


	/**
	 * ����/�ݷ� ���� Ȯ�� [�˾�]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_stat_comment_pop.ms")
	public String couponStatCommentPop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponStatCommentPop ##########");

		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO ���� /////////////////////////////////////////////////////////////
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	//����Id

		
		// ��� ��ȸ //
		CouponService couponService = new CouponService();
		MwCsCpnregHist hist = couponService.selectCpnregHist(params); 	// ���������� 
		String statComment = hist==null?"":hist.getStatComment();
		
		System.out.println(">> statComment : "+statComment);
		

		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("cpnId", cpnId);
		
		// ��� �ѱ��
		request.setAttribute("statComment", statComment);
		
		return "member/coupon_stat_comment_pop";
	}
	
	
	/**
	 * ���������� ��ȸ [����/���� ����ȭ��]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_approval_detail.ms")
	public String couponAppDetail(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponAppDetail ##########");

		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO ���� /////////////////////////////////////////////////////////////
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	//����Id

		
		// ��� ��ȸ //
		CouponService couponService = new CouponService();
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// ���������� 
		System.out.println(">> cpnListAll : "+cpnListAll);
		
		String info1 = "";
		
		if(cpnListAll.getInfo() != null && !cpnListAll.getInfo().equals("")){
			info1 = cpnListAll.getInfo().substring(cpnListAll.getInfo().lastIndexOf("/")+1);
		}
		
		request.setAttribute("info1", info1);
		

		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("cpnId", cpnId);
		
		// ��� �ѱ��
		request.setAttribute("cpnListAll", cpnListAll);
		
		return "member/coupon_approval_detail";
	}

	
	/**
	 * ���������� ���� 
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_approval.ms")
	public String couponApproval(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponDelete ##########");

		String regUser = getSessionMgrId(request);//TODO ����ó�� 
		Date regDtm = new Date();
		
		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");	// ����ID
		String cpnStat = checkStr(request.getParameter("cpnStat"), "");	// �������ο���
		String statComment = checkStr(request.getParameter("statComment"), "");	// ����/�ݷ�����
		
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);	// ����ID
		System.out.println("cpnStat: "+cpnStat);	// �������ο���
		System.out.println("statComment: "+statComment);	// ����/�ݷ�����
		System.out.println("regUser : "+ regUser);	// �����  
		System.out.println("regDtm : "+ regDtm);	// ����Ͻ�
		// TODO ���� /////////////////////////////////////////////////////////////
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("cpnId", cpnId);	// ����ID
		params.put("cpnStat", cpnStat);	// �������ο���
		params.put("statComment", statComment);	// ����/�ݷ�����
		params.put("regUser", regUser);	//����� 
		params.put("regDtm", regDtm);	//����Ͻ�  
		
		
		CouponService couponService = null;
		try {
				
			// DB insert //
			couponService = new CouponService();
			int result = 0;

			// mw_cm_cpn_list ����
			result = couponService.updateMwCsCouponList(params);
			System.out.println(">> 1 step result cnt : "+result);
			
			// cpn_list ����
			result = couponService.insertCouponRegHist(params);
			System.out.println(">> 2 step result cnt : "+result);
						
			// commit
			couponService.commit();
			
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);

		}
		catch(Exception e) {
			// rollback
			couponService.rollback();
			e.printStackTrace();
			log.debug(e);
		}
		finally {
			
		}
		
		return "/common/result_page";//����� �̵� ���.. ����Ʈ?			
	}

	
	/**
	 * ������������ ����Ʈ  
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_list.ms")
	public String couponHandIssueList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssueList ##########");

		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		// �Ķ���� �ʱ�ȭ 
		String partV = checkStr(request.getParameter("partV"), "%");
		String compName = checkStr(request.getParameter("compName"), "");
		String cpnName = checkStr(request.getParameter("cpnName"), "");
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "1d")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		String today = today();
		if(!params.get("ra_searchTerm").equals("all") || !"".equals(params.get("ra_searchTerm"))){
			params.put("sdate", checkStr(request.getParameter("sdate"), today));
			params.put("edate", checkStr(request.getParameter("edate"), today));
		}
		if(params.get("ra_searchTerm").equals("all")){
			params.put("sdate", "");
			params.put("edate", "");
		}
	
		params.put("partV", partV);									//��������
		params.put("compName", compName);						//���޻��
		params.put("cpnName", cpnName);							//������

		params.put("cpnIssueType","01");	// Admin��������(0018)
		
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "10")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		
		CouponService couponService = new CouponService();
		int couponListCnt = couponService.selectCouponListCnt(params); 		// �� ��� ��
		Paging page = new Paging();
		page.makeWebPaging(nowPage, couponListCnt, rowsPerPage);
		
		if(page.getNowPage()>1){
			nowPage =  page.getNowPage();
			request.setAttribute("nowPage", nowPage);
			
		}else{
			nowPage = 1;
			request.setAttribute("nowPage", "1");
		}

		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1 ;
		
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		
		List<Coupon> couponList = couponService.selectCouponList(params); 	// �����ȸ
		System.out.println(">> couponList : "+couponList);
		
		
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("partV", partV);
		request.setAttribute("compName", compName);
		request.setAttribute("cpnName", cpnName);
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		
		// ��� �ѱ��
		request.setAttribute("dataList", couponList);
		
		
		return "member/coupon_hand_issue_list";
	}
	

	/**
	 * ���� ���� ���� [�˾�]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_pop.ms")
	public String couponHandIssuePop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssuePop ##########");

		String regUser = getSessionMgrId(request);//TODO ����ó�� 

		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		String issueStat = checkStr(request.getParameter("issueStat"), "N");
		String phone = checkStr(request.getParameter("phone"), "");
		String optMode = checkStr(request.getParameter("optMode"), "search"); //-- search:��ȸ, issue:����
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		System.out.println("issueStat: "+issueStat);
		System.out.println("phone: "+phone);
		System.out.println("optMode: "+optMode);
		// TODO ���� /////////////////////////////////////////////////////////////
		
		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		String params3 = ""; //-- DB�α׸� ����� ���� �ϴ� params2 �� ���� ������ String
		params.put("cpnId", cpnId);									//����Id
		params.put("issueStat", issueStat);
		params.put("phone", phone);

		
		
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int rowsPerPage = Integer.parseInt(checkStr(request.getParameter("rowsPerPage"), "15")); 	// �� ���������� �������� �Խù� ��� ��
		int nowPage = Integer.parseInt(checkStr(request.getParameter("nowPage"), "1")); 					// ����������
		int startRow = nowPage == 1 ? 1 : (nowPage - 1) * rowsPerPage + 1; 												// ù ���
		int endRow = nowPage == 1 ? rowsPerPage :startRow + rowsPerPage -1;
		params.put("startRow", String.valueOf(startRow));
		params.put("endRow", String.valueOf(endRow));
		params.put("rowsPerPage", String.valueOf(rowsPerPage));
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		// ��� ��ȸ //
		CouponService couponService = new CouponService();
		int result = 0;
		
		// �������� ��ȸ 
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// ���������� 
		
		// ���� ó�� 
		if("issue".equals(optMode)){
			String[] chkSeqArr = request.getParameterValues("chkSeq");
			for(int i=0; chkSeqArr!=null && i<chkSeqArr.length; i++) {
				String userId = chkSeqArr[i];
				System.out.println("userId : " + userId); //##
				
	
				String membId = cpnListAll.getMembId();//�����id -DB
				String cpart = "p";
				String barcode = "";	//��ü������ȣ -���� / ����������ȣ-DB / POOL��������?? -DB
				String regDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
				String stat = "R";
				String sday = cpnListAll.getValSday();//��ȿ������	-DB
				String eday = cpnListAll.getValEday();//��ȿ������	-DB
				String imgHost = "";//�̹��� ��� -DB
				String imgUrl = "";//�̹��� ���ϸ� -DB
				String chgDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
				String useCnt = "";//�����ִ���ȸ�� -DB
				String usimData = "";//??
	
				String regDt = "";//CONVERT(VARCHAR(8),GETDATE(),112)
				String regTm = "";//REPLACE(CONVERT(VARCHAR(8),GETDATE(),108),':','')
				
				try {
	
					// ������ȣ���౸�п� ���� ������ȣ ���� 
					barcode = getBarcode(cpnListAll, couponService);
	
					
					// ����� ���� : os 
					HashMap<String, Object> params2 = new HashMap<String,Object>();
					params2.put("userId", userId);
					UserInfo userInfo = couponService.getUserInfo(params2);
					
					
					// ���ڵ� �̹��� ����
					// BarcodeUtil(String Os,String MembId, String CardLevel, String Name, String ValSday, String ValEday)
					String os = userInfo.getOs();
					String level = "";
					String name = "";
					

					
					/*==>mcserver ����. �̷����� �߰��ʿ� ����, �Ʒ� �ҽ� �߰���.
					if (CompulsoryYn.equals("Y")) {
						barcodeUtil = new BarcodeUtil(Integer.toString(UserID));
					}
					else {
						//[20121108][inoky@2bsolution.com] coupon barcode image widh���� ������������ userid �߰�
						barcodeUtil = new BarcodeUtil(Integer.toString(UserID), Os, "", "", Name, ValSday, ValEday, "");
						barcodeUtil.Make(Barcode, 2);
					}
					*/
					
					BarcodeUtil barcodeUtil = null;
					
					//--���հ����̸�, 
					if (cpnListAll.getCompPayYn().equals("Y")){
						barcodeUtil = new BarcodeUtil(userId);
					}
					else{ //-- ���հ���� + ����� ���� �ʿ��� ���ڵ� ����
						//[20121108][inoky@2bsolution.com] coupon barcode image widh���� ������������ userid �߰�
						barcodeUtil = new BarcodeUtil(userId, os, membId, level, name, sday, eday, "01", true);
						barcodeUtil.Make(barcode, 2);
					}
					
					/*
					BarcodeUtil barcodeUtil = new BarcodeUtil(os, membId, level, name, sday, eday, "01"); // 01 : barcode type
					barcodeUtil.Make(barcode, 2);
					*/
					imgHost = barcodeUtil.getHost();
					//imgUrl = barcodeUtil.getB_BarcodePath(); //-- ū �̹���
					imgUrl = barcodeUtil.getS_BarcodePath(); //-- ���� �̹���
					// ���ڵ� �̹��� ���� END
					
					
					// my_cpn �Է�
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//����Id
					params2.put("userId", userId);	//�����Id
					params2.put("barcode", barcode);
					params2.put("cpart", cpart);
					params2.put("stat", stat);
					params2.put("imgHost", imgHost);
					params2.put("imgUrl", imgUrl);
					result = couponService.insertMyCpn(params2);
					
					
					// cpn_gen_his �Է�
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//����Id
					params2.put("custId", userId);	//�����Id
					params2.put("barcode", barcode);
					params2.put("stat", stat);
					result = couponService.insertCpnGenHis(params2);
					
					
					// MW_CS_ISSUE_CPN �Է�
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//����Id
					params2.put("userId", userId);	//�����Id
					params2.put("barcode", barcode);
					params2.put("cmConfBarcdYn", "02".equals(cpnListAll.getBarIssueType())?"Y":"N");
					params2.put("regUser", regUser);
					result = couponService.insertMwCsIssueCpn(params2);
					
					
					params3 = params3 + params2.toString(); //--DB �α� ����� ���� �ڵ� �Դϴ�.
					
					couponService.commit();
					
				} catch(Exception e) {
					couponService.rollback();
					e.printStackTrace();
				}
			}
		}
		
		List<CouponIssue> dataList = couponService.selectCouponHandIssuePopList(params); 	// ���������������� ��ȸ
		String[] userNm = new String[dataList.size()];
		String[] phoneList = new String[dataList.size()];
		for(int i=0; i<dataList.size(); i++){
			String temp = dataList.get(i).getUserName();
			temp = ktService.decoding(temp);
			if(temp != null && !"".equals(temp)){
				userNm[i] = temp.substring(0, temp.length()-1)+"*";
			} else userNm[i] = "*";
			
			// ��ȭ��ȣ ����Ʈ//list.phone.substring(0,1)}**${list.phone.substring(3,10)}*
			String phone2 = dataList.get(i).getPhone();
			if(phone2 != null && !"".equals(phone2)){
				phoneList[i] = phone2.substring(0, 1)+"**"+phone2.substring(3,phone2.length()-1)+"*";
			} else phoneList[i] = "*";
			
		}
		int listCnt = couponService.selectCouponHandIssuePopListCnt(params); 		// �� ����
		System.out.println(">> dataList : "+dataList);
		

		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Paging page = new Paging();
		page.makeWebPaging(nowPage, listCnt, rowsPerPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", page.getSb());
		request.setAttribute("rowsPerPage", rowsPerPage);
		//// ����¡ ó�� //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("phoneList", phoneList);
		request.setAttribute("userNm", userNm);
		request.setAttribute("cpnId", cpnId);
		request.setAttribute("issueStat", issueStat);
		request.setAttribute("phone", phone);
		
		// ��� �ѱ��
		request.setAttribute("dataList", dataList);
		
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		params.put("pageURL", "/member/coupon_hand_issue_pop.ms");
	
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString() + params3.toString());
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		
		
		return "member/coupon_hand_issue_pop";
	}

	/**
	 * ������ȣ ���� 
	 * 
	 * @param cpnId
	 * @param cpnListAll
	 * @return
	 */
	private String getBarcode(CpnListAll cpnListAll, CouponService couponService) {
		String barcode = "", barIssueType = cpnListAll.getBarIssueType();
		if("00".equals(barIssueType)) {	//������ȣ����
			barcode = "No Coupon Number";
		} else if("01".equals(barIssueType)) {	//�ǽð� ��ü����
			barcode = BARCODE_GEN.LOCAL_BARCODE_NUM(cpnListAll.getCpnId());
		} else if("02".equals(barIssueType)) {	//��ϵ� ��������������ȣ 
			barcode = cpnListAll.getCmConfBarcd();
		} else if("03".equals(barIssueType)) {	//�ǽð� ���޻� ���� ����
			barcode = ""; //TODO �������̽� ȣ�� 
		} else if("04".equals(barIssueType)) {	//BULK �������� ����
			// MW_CS_BULK_CPN : BARCODE ��������
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("cpnId", cpnListAll.getCpnId());
			MwCsBulkCpn mwCsBulkCpn = couponService.getMwCsBulkCpnLast(params);
			// MW_CS_BULK_CPN : set ISSUE_YN='Y'
			params.put("issueSeq", mwCsBulkCpn.getIssueSeq());
			int result = couponService.updateMwCsBulkCpnIssueYn(params);
			barcode = mwCsBulkCpn.getBarcode();
		}
		return barcode;
	}
	
	private String getBarcode(CpnListAll cpnListAll, CouponService couponService, String targetCpnNo) {
		String barcode = "", barIssueType = cpnListAll.getBarIssueType();
		if("04".equals(barIssueType)) {	//BULK �������� ����
			// MW_CS_BULK_CPN : BARCODE ��������
			HashMap<String, Object> params = new HashMap<String,Object>();
			params.put("cpnId", cpnListAll.getCpnId());
			params.put("barcode", targetCpnNo); //-- Ÿ�������� ���, �ش� ���� ��ȣ�� �߱޵��� ���� ��쿡 ���Ͽ� barcode�� �����ϵ��� �ϱ�����.
			
			MwCsBulkCpn mwCsBulkCpn = couponService.getMwCsBulkCpnLast(params);
			// MW_CS_BULK_CPN : set ISSUE_YN='Y'
			params.put("issueSeq", mwCsBulkCpn.getIssueSeq());
			int result = couponService.updateMwCsBulkCpnIssueYn(params);
			barcode = mwCsBulkCpn.getBarcode();
		}
		return barcode;
	}
	/**
	 * ���� �ϰ� ���� [�˾�]
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_file_pop.ms")
	public String couponHandIssueFilePop(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssueFilePop ##########");

		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO ���� /////////////////////////////////////////////////////////////
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		
		// �Ķ��Ÿ ���� �״�� �ѱ��
		request.setAttribute("cpnId", cpnId);
		
		/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("pageURL", "/member/coupon_hand_issue_file_pop.ms");
	
		params.put("cpnId", cpnId);
		params.put("part", "MEMBERSHIP");
		params.put("admin_id", getSessionMgrId(request));
		params.put("ip", request.getRemoteAddr());
		params.put("msg", params.toString() );
		
		logSVC.insertAccessLogReg(params);
		logSVC.commit();
		/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
		
		return "member/coupon_hand_issue_file_pop";
	}
	
	
	/**
	 * ���� �ϰ� ����
	 * 
	 * @param model
	 * @param locale
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/member/coupon_hand_issue_file.ms")
	public String couponHandIssueFile(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		log.debug("########## CouponController.couponHandIssueFile ##########");

		String regUser = getSessionMgrId(request); 
		
		// �Ķ���� �ʱ�ȭ 
		String cpnId = checkStr(request.getParameter("cpnId"), "");
		
		// TODO ���� /////////////////////////////////////////////////////////////
		System.out.println("cpnId: "+cpnId);
		// TODO ���� /////////////////////////////////////////////////////////////
		
		if( "".equals(cpnId) ) { 
			JsonErrMsg("err",response);
			return null;
		}

		
		// ��ȸ���ǰ� ����
		HashMap<String, Object> params = new HashMap<String,Object>();
		String params3 = ""; //-- DB�α׸� ����� ���� �ϴ� params2 �� ���� ������ String
		params.put("cpnId", cpnId);	//����Id
		
		
		CouponService couponService = new CouponService();
		int result = 0;
		String resultMsg = "";
		
		// �������� ��ȸ 
		CpnListAll cpnListAll = couponService.selectCpnListAll(params); 	// ���������� 
		
		
		UpFile upFile = null;
		try{
			upFile = new UpFile(request, "/excelfile/");//���� ���ε�
			ExcelContoller excels = null;
			
			if(cpnListAll.getIsTargetCpn().equals("N")){
				excels = new ExcelContoller(upFile.getFiles(),3);//���� ���� �Ľ�
			}
			else{
				excels = new ExcelContoller(upFile.getFiles(),4);//���� ���� �Ľ�
			}
			excels.setSheet(0);//���������� �ִ� ��Ʈ
		
//		if(excels.ChkTitle("���� �ϰ� ����")){//������ Ÿ��Ʋ�� üũ
			for(int i=0;true;i++){
				/*
				 * excels.getString("������ �� ����");
				 * ��� Str �� str[0]�� ��� �ڵ带 �ǹ��Ѵ�. true/false
				 * str[1]~str[n] ������ �ִ� �׸��� ������� �����´�.
				**/
					String[] str = excels.getString(i); 
					if(str[0].equals("false")){
						break;
					}
					
					//DB =======================================
					String userName = str[2];
					String phone = str[3];
					String couponNo = "";
					if(cpnListAll.getIsTargetCpn().equals("Y")){
						couponNo = str[4];
					}
					
					/*##################### ��ȣȭ S #####################*/
					String authName = ktService.encoding(userName);
//					phone = ktService.encoding(phone);
					/*##################### ��ȣȭ E #####################*/
					
					HashMap<String, Object> params2 = new HashMap<String,Object>();
//					String authName = userName;//TODO ��ȣȭ ó�� �ʿ�
					params2.put("authName", authName);
					params2.put("phone", phone);
					params2.put("couponNo", couponNo);
					

					// TODO ���� /////////////////////////////////////////////////////////////
					System.out.println("userName : " + userName);
					System.out.println("phone : " + phone);
					System.out.println("couponNo : " + couponNo);
					// TODO ���� /////////////////////////////////////////////////////////////
					
					//DB ���� user_id ��������
					UserInfo userInfo = couponService.getUserInfo(params2);
					if( userInfo==null || "".equals(userInfo.getUserId()) ) {
						String msg = userName+"("+phone+") �� ��ϵǾ��������� ����������Դϴ�.";
						resultMsg += msg;
						log.debug(msg);
						
						// TODO ���� /////////////////////////////////////////////////////////////
						System.out.println(msg);
						// TODO ���� /////////////////////////////////////////////////////////////
						
						continue;
					}

					String userId = userInfo.getUserId();
					String os = userInfo.getOs();

					// TODO ���� /////////////////////////////////////////////////////////////
					System.out.println("userId : " + userId);
					// TODO ���� /////////////////////////////////////////////////////////////

					//���� �ٿ�ε� ���� Ȯ�� my_cpn
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//����Id
					params2.put("userId", userId);	//�����Id
					
					String myCpnId = couponService.getMyCpn(params2);
					if( myCpnId != null ) {	//�̹� ��ϵǾ� �ִ� ��� ����ó��
						resultMsg = userName+"("+phone+") �� �̹� ������ �����Ͽ����ϴ�. Ȯ���ϰ� �ٽ� �������ּ���.";
						throw new Exception(resultMsg);
					}
					

					String membId = cpnListAll.getMembId();//�����id -DB
					String cpart = "p";
					String barcode = "";	//��ü������ȣ -���� / ����������ȣ-DB / POOL��������?? -DB
					String regDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
					String stat = "R";
					String sday = cpnListAll.getValSday();//��ȿ������	-DB
					String eday = cpnListAll.getValEday();//��ȿ������	-DB
					String imgHost = "";//�̹��� ��� -DB
					String imgUrl = "";//�̹��� ���ϸ� -DB
					String chgDay = "";//CONVERT(VARCHAR(8),GETDATE(),112)
					String useCnt = "";//�����ִ���ȸ�� -DB
					String usimData = "";//??
					 
					String regDt = "";//CONVERT(VARCHAR(8),GETDATE(),112)
					String regTm = "";//REPLACE(CONVERT(VARCHAR(8),GETDATE(),108),':','')

					
					if(cpnListAll.getIsTargetCpn().equals("N")){ //-- Ÿ�������� �ƴ� ���,
						// ������ȣ���౸�п� ���� ������ȣ ���� 
						barcode = getBarcode(cpnListAll, couponService);
					}
					else{ //-- Ÿ�������� ���
						barcode = getBarcode(cpnListAll, couponService, couponNo);
					}

					// ���ڵ� �̹��� ����
					// BarcodeUtil(String Os,String MembId, String CardLevel, String Name, String ValSday, String ValEday)
					String level = "";
					String name = "";


					//BarcodeUtil barcodeUtil = new BarcodeUtil(os, membId, level, name, sday, eday, "01"); //-- 01:BARCODE TYPE
					BarcodeUtil barcodeUtil = null;//--���հ����̸�, 
					if (cpnListAll.getCompPayYn().equals("Y")){
						barcodeUtil = new BarcodeUtil(userId);
					}
					else{ //-- ���հ���� + ����� ���� �ʿ��� ���ڵ� ����
						//[20121108][inoky@2bsolution.com] coupon barcode image widh���� ������������ userid �߰�
						barcodeUtil = new BarcodeUtil(userId, os, membId, level, name, sday, eday, "01", true);
						barcodeUtil.Make(barcode, 2);
					}

					imgHost = barcodeUtil.getHost();
					//imgUrl = barcodeUtil.getB_BarcodePath(); //-- ū �̹���
					imgUrl = barcodeUtil.getS_BarcodePath(); //-- ���� �̹���
					
					
					// my_cpn �Է�
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//����Id
					params2.put("userId", userId);	//�����Id
					params2.put("barcode", barcode);
					params2.put("cpart", cpart);
					params2.put("stat", stat);
					params2.put("imgHost", imgHost);
					params2.put("imgUrl", imgUrl);
					result = couponService.insertMyCpn(params2);
					
					
					// cpn_gen_his �Է�
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//����Id
					params2.put("custId", userId);	//�����Id
					params2.put("barcode", barcode);
					params2.put("stat", stat);
					result = couponService.insertCpnGenHis(params2);
					
					
					// MW_CS_ISSUE_CPN �Է�
					params2 = new HashMap<String,Object>();
					params2.put("cpnId", cpnId);	//����Id
					params2.put("userId", userId);	//�����Id
					params2.put("barcode", barcode);
					params2.put("cmConfBarcdYn", "02".equals(cpnListAll.getBarIssueType())?"Y":"N");
					params2.put("regUser", regUser);
					result = couponService.insertMwCsIssueCpn(params2);
					
					
					params3 = params3 + params2.toString(); //--DB �α� ����� ���� �ڵ� �Դϴ�.
						
				}// END for
				
			couponService.commit();
			
			result = 1;
			
			/*##################### DATA ACCESS LOG DB�� ����� S #####################*/
			params.put("pageURL", "/member/coupon_hand_issue_file.ms");
		
			params.put("cpnId", cpnId);
			params.put("part", "MEMBERSHIP");
			params.put("admin_id", getSessionMgrId(request));
			params.put("ip", request.getRemoteAddr());
			params.put("msg", params.toString() + params3 );
			
			logSVC.insertAccessLogReg(params);
			logSVC.commit();
			/*##################### DATA ACCESS LOG DB�� ����� E #####################*/
			
			
//		}else{
//			upFile.getFiles().delete();// ���� ����
////			resultMsg = "err";
//		}
		}catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			//rollback
			couponService.rollback();
			// ���� ����
			upFile.getFiles().delete();
			//resultMsg = "err";
			result = 0;
		}

		//JsonErrMsg(resultMsg,response);
		request.setAttribute("targetUrl", "/member/coupon_hand_issue_file_pop.ms");
		request.setAttribute("actResult", result);
		request.setAttribute("actResultMsg", resultMsg);
		return "common/result_message";
	}

	/**
	 * @Method Name : cpnIdDupCheck
	 * @Description : ���� ��� ��, ���� id�� �������� �ִ� ���, ���� id �ߺ����θ� üũ�Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
	 * @author trkim
	 * @since 2012.12.14
	 */
	@RequestMapping(value="/member/cpnId_dupCheck.ms")
	public String cpnIdDupCheck(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		CouponService couponService = new CouponService();
		String manualCpnId = checkStr(request.getParameter("manualCpnId"));
		JSONObject jObj = new JSONObject();
		
		int isDupCpnId = 0;
		isDupCpnId = couponService.cpnIdDupCheck(manualCpnId);
		
		jObj.put("isDupCpnId", String.valueOf(isDupCpnId));
		
		request.setAttribute("JSONObject", jObj);
		
		return "common/result_page";
	}

	
	private void JsonErrMsg(String Err, HttpServletResponse response){
		try{
			PrintWriter writer = response.getWriter();
			writer.write("{\"err\":\""+Err+"\"}");
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	/**
	 * @Method Name : today
	 * @Description : ���� ��¥�� ��ȸ�Ѵ�.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2010.04.23
	 */
	public static String today() {
		java.sql.Date CurrDate = new java.sql.Date((new java.util.Date()).getTime());
		return CurrDate.toString();
	}
}