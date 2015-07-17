/**
 * @author �̰���
 * @Date 2012-08-14
 * */
package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.CpnList;
import com.wallet.membership.model.CpnListExample;
import com.wallet.membership.service.CpnListService;

@Controller
public class CouponOrderManagerController extends CommonController {
	private final String PAGE_CODE = "COUPON_ORDER_MANAGER";
	private Logger log = Log.getLogger("logs");
	
	/*�Ķ��Ÿ ����
	 * -----------------------------------------------------
	 * reCommendCpnList : �Ʒ������� ��� ����Ʈ
	 * count : ����Ʈ�� �� ����
	 * -----------------------------------------------------
	 * list.cpnId : �������̵�
	 * list.membId : ������̵�
	 * list.partV : ���� ���� �ڵ�
	 * list.part : ���� ���� �ѱ�
	 * list.compName : ���޻� �̸�
	 * list.membName : ����� �̸�
	 * list.recommYn ��õ ���� ��뿩��
	 * list.recommSeq ��õ ���� ����
	 * list.mainDisplay �����, �ؿ� ���� ��뿩��
	 * list,dispOrder �����, �ؿ� ���� ����
	 * */
	
	/*��û �ּ�����
	 * =====================================
	 * /member/coupon_order_manager.ms : ��õ ����Ʈ ȭ��
	 * /member/coupon_memb_order_manager.ms : �ɹ��� ��ȸ ȭ��
	 * /member/coupon_fore_order_manager.ms : �ؿ� ���� ȭ��
	 * -------------------------------------------------------------------
	 * /member/coupon_order_edit.ms : ��õ ����ȭ�� <----- ��õ���� ����Ʈ �ڽ����� �ɼǺ���� ȣ��
	 * @param beforeNo  :  ���� ���� ��ȯ���� ����
	 * @param afterNo     :  ���� ���� ��ȯ���� ����
	 * @param cpnId        :  �������̵�
	 * @param membId    :  ����� ���̵�
	 * @param part          :  ����
	 * --------------------------------------------------------------------
	 * /member/coupon_memb_order_edit.ms : �ɹ���/�ؿ� ���� <--- �ɹ��� �� �ؿܿ��� ����Ʈ �ڽ� �ɼǺ���� ȣ��
	 * @param beforeNo  :  ���� ���� ��ȯ���� ����
	 * @param afterNo     :  ���� ���� ��ȯ���� ����
	 * @param cpnId        :  �������̵�
	 * @param membId    :  ����� ���̵�
	 * @param part          :  ����
	 * ---------------------------------------------------------------------
	 * /member/coupon_memb_order_yn.ms : �ɹ���/�ؿ� ��뿩�� <--- üũ�ڽ� ���� ����� ȣ��
	 * @param beforeNo : ���� ����
	 * @param displayYn : ��뿩�� üũ�� Y, ������ N
	 * @param cpnId       : �������̵�
	 * @param membId   : ����� ���̵�
	 * @param part         : ����
	 * */
	
	
	
	
	/**
	 * ���� ���� ��ȸ ��õ ��ȸ ȭ��
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_order_manager.ms")
	public String RecommendList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		CpnListService cpnListService = new CpnListService();
		List<CpnList> reCommendCpnList = new ArrayList<CpnList>();
		int count = 0;
		try{
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd")).andCpnStatequalTo("01")
			.andPartVNotEqualTo("R")//-- �ؿ������̾ƴѰ��
			.andMainDisplayEqualTo("Y");
			
			count = cpnListService.CouponOrderByCount(cpnListExample);
			
			cpnListExample.setOrderByClause("RECOMM_SEQ ASC");
			reCommendCpnList = cpnListService.CouponOrderByExample(cpnListExample);
			
		}catch (Exception e) {
			// TODO: handle exception
		}	
		
		request.setAttribute("reCommendCpnList",reCommendCpnList);				
		request.setAttribute("count", count);
			
		return "member/coupon_order_manager";
		}
	
	
	/**
	 * ���� ���� ��ȸ ��õ ���� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_order_edit.ms")
	public String RecommendEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		String beforeNo =checkStr(request.getParameter("beforeNo"),"");
		String afterNo = checkStr(request.getParameter("afterNo"),"");
		
		String cpnId = checkStr(request.getParameter("cpnId"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		String part = checkStr(request.getParameter("part"),"");
		
		log.info("beforeNo --->" + beforeNo);
		log.info("afterNo --->" + afterNo);
		log.info("cpnId --->" + cpnId);
		log.info("membId --->" + membId);
		log.info("part --->" + part);
		
		if(beforeNo.equals("")|| afterNo.equals("")||cpnId.equals("")){
			JsonErrMsg("err",response);
			return null;
		}
		
		int beforeNoInt = Integer.parseInt(beforeNo);
		int afterNoInt = Integer.parseInt(afterNo);
		
		if(beforeNoInt == 4){
			beforeNoInt = 0;
		}
		
		CpnListService cpnListService = new CpnListService();
		try{			
			//�ش� ���� ������Ʈ
			//������Ʈ �� ���� ��
			CpnList updateData = new CpnList();			
			updateData.setRecommSeq(afterNoInt);
			updateData.setRecommYn("Y");
			CpnListExample cpnListExample = new CpnListExample();//������Ʈ �� Example			
			cpnListExample.or().andCpnIdEqualTo(cpnId).andRecommSeqEqualTo(beforeNoInt);
			
			CpnListExample cpnAllExample = null;//��ü ���� ������ Example
			CpnList updateAll = null;
			
			//������ �ö�
			if(beforeNoInt<afterNoInt){				
				//������Ʈ �Ǵ� ������ ���� ������ ��� +1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqGreaterThanOrEqualTo(afterNoInt);
				updateAll.setUpRecommPlus("Auto Add");//---���� Null�� �ƴ϶�� �ڵ����� ä������.
				cpnListService.update(updateAll, cpnAllExample);
				
				//�ش� ���� ����
				cpnListService.update(updateData, cpnListExample);
				
				//��õ�̹Ƿ� 3�� ������ ������ ���� ��� 0���� ����
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqGreaterThan(3);
				updateAll.setRecommSeq(0);
				updateAll.setRecommYn("N");
				cpnListService.update(updateAll, cpnAllExample);
				
			//������ ������
			}else if(beforeNoInt>afterNoInt){				
				//������Ʈ �Ǵ� ������ �� ������ ��� -1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqLessThanOrEqualTo(afterNoInt).andRecommSeqGreaterThan(1);
				updateAll.setUpRecommMu("Auto Add");//---���� Null�� �ƴ϶�� �ڵ����� ä������.
				cpnListService.update(updateAll, cpnAllExample);
				
				//�ش� ���� ����
				cpnListService.update(updateData, cpnListExample);
				
				//��õ�̹Ƿ� 3�� ������ ������ ���� ��� 0���� ����
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andRecommSeqGreaterThan(3);
				updateAll.setRecommSeq(0);
				updateAll.setRecommYn("N");
			}
			cpnListService.commit();
		}catch (Exception e) {
			// TODO: handle exception
			cpnListService.rollback();
			JsonErrMsg("err",response);
			return null;
		}	
		JsonErrMsg("",response);
		return null;
		}
	
	/**
	 * ���� ���� ��ȸ �ɹ��� ��ȸ ȭ�� ==> ��ü ��ȸ�� �����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_memb_order_manager.ms")
	public String MemberShipList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		CpnListService cpnListService = new CpnListService();
		List<CpnList> cpnList = new ArrayList<CpnList>();
		/*
		List<CpnList> reCommendCpnList = new ArrayList<CpnList>();
		List<CpnList> reCommendZeroCpnList = new ArrayList<CpnList>();
		int count = 0;
		int zerocount = 0;
		*/
		int cpnListCnt = 0;
		try{
			/*
			//Zero List
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andPartVNotEqualTo("R") //-- �ؿ������� �ƴѰ͸� ���̵��� ����
			.andDispOrderEqualTo("0")
			.andCpnStatequalTo("01")
			.andMainDisplayEqualTo("Y");
			zerocount = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendZeroCpnList = cpnListService.CouponOrderByExample(cpnListExample);
		
			//Non Zero List
			cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andPartVNotEqualTo("R")//-- �ؿ������� �ƴѰ͸� ���̵��� ����
			.andDispOrderNotEqualTo("0")
			.andCpnStatequalTo("01") 
			.andMainDisplayEqualTo("Y");

			count = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendCpnList = cpnListService.CouponOrderByExample(cpnListExample);
			*/
			
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andPartVNotEqualTo("R") //-- �ؿ������� �ƴѰ͸� ���̵��� ����
			.andCpnStatequalTo("01")
			.andMainDisplayEqualTo("Y");
			cpnListCnt = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			cpnList = cpnListService.CouponOrderByExample(cpnListExample);
			
		}catch (Exception e) {
			// TODO: handle exception
		}	
		/*
		request.setAttribute("reCommendZeroCpnList",reCommendZeroCpnList);		
		request.setAttribute("reCommendCpnList",reCommendCpnList);
		request.setAttribute("count", count);
		request.setAttribute("zerocount", zerocount);
		*/
		request.setAttribute("cpnList", cpnList);
		request.setAttribute("cpnListCnt", cpnListCnt);

		System.out.println(cpnListCnt);

		return "member/coupon_memb_order_manager";
		}
	
	
	/**
	 * ���� ���� ��ȸ �ؿ����� ȭ��
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_order_manager"
	 */ 
	@RequestMapping(value="/member/coupon_fore_order_manager.ms")
	public String ForeignList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		CpnListService cpnListService = new CpnListService();
		List<CpnList> reCommendCpnList = new ArrayList<CpnList>();
		List<CpnList> reCommendZeroCpnList = new ArrayList<CpnList>();
		
		int count = 0;
		int zerocount = 0;
		try{
			//Zero List
			CpnListExample cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd")).andPartVEqualTo("R").andDispOrderEqualTo("0").andCpnStatequalTo("01");
			zerocount = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendZeroCpnList = cpnListService.CouponOrderByExample(cpnListExample);
		  
			//Non Zero List
			cpnListExample = new CpnListExample();
			cpnListExample.or().andAEdayGreaterThanOrEqualTo(DateTime.format("yyyyMMdd"))
			.andASdayLessThanOrEqualTo(DateTime.format("yyyyMMdd")).andPartVEqualTo("R").andDispOrderNotEqualTo("0").andCpnStatequalTo("01");
			count = cpnListService.CouponOrderByCount(cpnListExample);
			cpnListExample.setOrderByClause("disp_order ASC");
			reCommendCpnList = cpnListService.CouponOrderByExample(cpnListExample);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}	
		
		request.setAttribute("reCommendZeroCpnList",reCommendZeroCpnList);		
		request.setAttribute("reCommendCpnList",reCommendCpnList);				
		request.setAttribute("count", count);
		request.setAttribute("zerocount", zerocount);

		System.out.println(count);
		System.out.println(zerocount);
			
		return "member/coupon_fore_order_manager";
		}
	
	
	/**
	 * ���� ���� ��ȸ ���� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_memb_order_edit"
	 */ 
	@RequestMapping(value="/member/coupon_memb_order_edit.ms")
	public String MemberShipEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		String beforeNo =checkStr(request.getParameter("beforeNo"),"");
		String afterNo = checkStr(request.getParameter("afterNo"),"");
		
		String cpnId = checkStr(request.getParameter("cpnId"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		String part = checkStr(request.getParameter("partV"),"");
				
		log.info("beforeNo --->" + beforeNo);
		log.info("afterNo --->" + afterNo);
		log.info("cpnId --->" + cpnId);
		log.info("membId --->" + membId);
		log.info("part --->" + part);
		
		if(beforeNo.equals("")|| afterNo.equals("")||cpnId.equals("")){
			JsonErrMsg("err",response);
			return null;
		}
		
		int beforeNoInt = Integer.parseInt(beforeNo);
		int afterNoInt = Integer.parseInt(afterNo);
		
		CpnListService cpnListService = new CpnListService();
		try{			
			//�ش� ���� ������Ʈ
			//������Ʈ �� ���� ��
			CpnList updateData = new CpnList();			
			updateData.setDispOrder(""+afterNoInt);
			CpnListExample cpnListExample = new CpnListExample();//������Ʈ �� Example			
			cpnListExample.or().andCpnIdEqualTo(cpnId);
			
			CpnListExample cpnAllExample = null;//��ü ���� ������ Example
			CpnList updateAll = null;
			
			//������ �ö�
			if(beforeNoInt>afterNoInt || beforeNoInt==0){				
				//������Ʈ �Ǵ� ������ ���� ������ ��� +1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				
				cpnAllExample.or().andDispOrderGreaterThanOrEqualTo(""+afterNoInt).andPartVEqualTo(part);
				updateAll.setUpMainPlus("Auto Add");//---���� Null�� �ƴ϶�� �ڵ����� ä������.
				cpnListService.update(updateAll, cpnAllExample);
				
				//�ش� ���� ����
				cpnListService.update(updateData, cpnListExample);				
				
			//������ ������
			}else if(beforeNoInt<afterNoInt){				
				//������Ʈ �Ǵ� ������ �� ������ ��� -1
				updateAll = new CpnList();
				cpnAllExample = new CpnListExample();
				cpnAllExample.or().andDispOrderLessThanOrEqualTo(""+afterNoInt).andDispOrderGreaterThan(""+beforeNoInt).andPartVEqualTo(part);
				updateAll.setUpMainMu("Auto Add");//---���� Null�� �ƴ϶�� �ڵ����� ä������.
				cpnListService.update(updateAll, cpnAllExample);
				
				//�ش� ���� ����
				cpnListService.update(updateData, cpnListExample);								
			}
			cpnListService.commit();
			System.out.println(beforeNo);
		}catch (Exception e) {
			// TODO: handle exception
			cpnListService.rollback();
			JsonErrMsg("err",response);
			return null;
		}	
		JsonErrMsg("",response);
		return null;
		}
	
	
	/**
	 * ���� ���� ��ȸ ���ÿ��� ���� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/coupon_memb_order_edit"
	 */ 
	@RequestMapping(value="/member/coupon_memb_order_yn.ms")
	public String MemberShipYn(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		
		String beforeNo =checkStr(request.getParameter("beforeNo"),"");
		String displayYn =checkStr(request.getParameter("displayYn"),"false");
		String cpnId = checkStr(request.getParameter("cpnId"),"");
		String membId = checkStr(request.getParameter("membId"),"");
		String part = checkStr(request.getParameter("part"),"");
		
		log.info("beforeNo --->" + beforeNo);
		log.info("displayYn --->" + displayYn);
		log.info("cpnId --->" + cpnId);
		log.info("membId --->" + membId);
		log.info("part --->" + part);
		
		if(displayYn.equals("true")){
			displayYn = "Y";
		}else{
			displayYn = "N";
		}
		
		if(beforeNo.equals("")|| cpnId.equals("")){
			return null;
		}
		
		CpnListService cpnListService = new CpnListService();
		try{			
			//�ش� ���� ������Ʈ
			//������Ʈ �� ���� ��
			CpnList updateData = new CpnList();
			updateData.setMainDisplay(displayYn);
			CpnListExample cpnListExample = new CpnListExample();//������Ʈ �� Example			
			cpnListExample.or().andCpnIdEqualTo(cpnId).andDispOrderEqualTo(beforeNo);
			cpnListService.update(updateData, cpnListExample);
			
			cpnListService.commit();
		}catch (Exception e) {
			// TODO: handle exception
			cpnListService.rollback();
			System.out.println(e.getMessage());
			JsonErrMsg("err",response);
			return null;
		}	
		JsonErrMsg("",response);
		return null;
		}
	
	
	/**
	 * Ajax ��� ����
	 * @MakeBy �̰���
	 * */
	private void JsonErrMsg(String Err, HttpServletResponse response){
		try{
			PrintWriter writer = response.getWriter();
			writer.write("{\"err\":\""+Err+"\"}");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
}