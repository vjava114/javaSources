/**
 * @author �̰���
 * @Date 2012-08-14
 * */
package com.wallet.membership.web.base;

import java.io.PrintWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.common.ExcelContoller;
import com.wallet.membership.common.UpFile;
import com.wallet.membership.common.gpsAddressGetter;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwCsBulkCpn;
import com.wallet.membership.model.MwCsBulkCpnExample;
import com.wallet.membership.model.custom.BulkCoupon;
import com.wallet.membership.service.MwCsBulkCpnService;
import com.wallet.membership.service.custom.BulkCouponService;

@Controller
public class BulkCouponListController extends CommonController {
	private final String PAGE_CODE = "BULK_COUPON_LIST";
	private Logger log = Log.getLogger("logs");

	/**
	 * bulk ���� ��ȸ ������
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/bulk_coupon_list"
	 */
	@RequestMapping(value="/member/bulk_coupon_list.ms")
	public String BulkCouponList(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<BulkCoupon> BulkCouponList = null;
		List<BulkCoupon> BulkCouponMemberList = null;
		BulkCouponService bulkCouponService = new BulkCouponService();

		int count = 0;

			//����¡ ó���� ���� �Ķ��Ÿ
			String pageNo = checkStr(request.getParameter("nowPage"), "1");
			String rowsPerPage = checkStr(request.getParameter("rowsPerPage"), "15");;

			int page = Integer.parseInt(pageNo);
			int rows = Integer.parseInt(rowsPerPage);
			String Today = DateTime.format("yyyy-MM-dd");
			//�Ķ��Ÿ �ޱ�
			String CuponPart = checkStr(request.getParameter("part"), "");
			String MembId = checkStr(request.getParameter("membid"), "");
			String CoponName = checkStr(request.getParameter("name"), "");
			String ShStartDays = checkStr(request.getParameter("sdate"), Today);
			String ShEndDays = checkStr(request.getParameter("edate"), Today);
			String PartnerTerm = checkStr(request.getParameter("bulkdate"), "01");
			String CpnName = checkStr(request.getParameter("membName"), "");
			String partnerName = checkStr(request.getParameter("partnerName"),"");

			if(PartnerTerm.equals("04")){
				ShStartDays = "";
				ShEndDays = "";
			}

			/*��� ��������*/
			String sdate = "";
			String edate = "";
			try{
				Date Dates = DateTime.parse(ShStartDays, "yyyy-MM-dd");
				sdate = DateTime.format(Dates, "yyyyMMdd");
				Dates = new Date(DateTime.parse(ShEndDays, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24));
				edate = DateTime.format(Dates, "yyyyMMdd");

				System.out.println(sdate);
				System.out.println(edate);

			}catch (Exception e){

			}

			BulkCoupon bulkCoupon = new BulkCoupon();
			if(!CuponPart.equals("")){bulkCoupon.setCoponPart(CuponPart);}
			if(!MembId.equals("")){bulkCoupon.setMembId(MembId);}
			if(!CoponName.equals("")){bulkCoupon.setCoponName(CoponName);}
			if(!CpnName.equals("")){bulkCoupon.setCpnName(CpnName);}
			if(!ShStartDays.equals("")){bulkCoupon.setShStartDays(sdate);}
			if(!ShEndDays.equals("")){bulkCoupon.setShEndDays(edate);}
			if(!partnerName.equals("")){bulkCoupon.setPartnerName(partnerName);}

			BulkCouponList = bulkCouponService.getByExamplePage(bulkCoupon, page, rows);
			BulkCouponMemberList = bulkCouponService.getMemberListByExample(bulkCoupon);
			/*��� ���� ���� ��������*/
			count = bulkCouponService.getCountByExample(bulkCoupon);

				for(int i=0;i<BulkCouponList.size();i++){
					try{

					Date Dates = new Date(DateTime.parse( BulkCouponList.get(i).getValEndDay(), "yyyyMMdd").getTime()+ (1000 * 60 * 60 * 24));
					BulkCouponList.get(i).setValEndDay(DateTime.format(Dates, "yyyy-MM-dd"));

					Dates = DateTime.parse( BulkCouponList.get(i).getValStartDay(), "yyyyMMdd");
					BulkCouponList.get(i).setValStartDay(DateTime.format(Dates, "yyyy-MM-dd"));

					Dates = DateTime.parse( BulkCouponList.get(i).getStartDay(), "yyyyMMdd");
					BulkCouponList.get(i).setStartDay(DateTime.format(Dates, "yyyy-MM-dd"));

					Dates = new Date(DateTime.parse( BulkCouponList.get(i).getEndDay(), "yyyyMMdd").getTime()+ (1000 * 60 * 60 * 24));
					BulkCouponList.get(i).setEndDay(DateTime.format(Dates, "yyyy-MM-dd"));
					}catch (Exception e) {
						// TODO: handle exception
					}
				}

				/******* paging start *********/
				Paging pageing = new Paging();
				pageing.makeWebPaging(Integer.parseInt(pageNo), count, Integer.parseInt(rowsPerPage));
				request.setAttribute("nowPage", Integer.parseInt(pageNo));
				request.setAttribute("paging", pageing.getSb());
				/******* paging end *********/

			/* SET ATTRIBUTE */
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("BulkCouponList", BulkCouponList);
			request.setAttribute("ShStartDays", ShStartDays);
			request.setAttribute("ShEndDays", ShEndDays);
			request.setAttribute("rowsPerPage", rowsPerPage);
			request.setAttribute("BulkCouponMemberList", BulkCouponMemberList);
			request.setAttribute("partnerTerm", PartnerTerm);
			request.setAttribute("membName", CpnName);
			request.setAttribute("coponName", CoponName);
			request.setAttribute("partnerName", partnerName);

			return "member/bulk_coupon_list";
	}


	/**
	 * Bulk ������ �������� ����� �����ϴ�.
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */
	@RequestMapping(value="/member/bulk_coupon_insert.ms")
	public String bulkCouponReg(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		String cpnID = checkStr(request.getParameter("cpnID"), null);

		BulkCoupon bulkCoupon = new BulkCoupon();
//		model.put("CouponShare", couponShare);

		/*��� ��������*/
		List<BulkCoupon> BulkCouponList = null;
		BulkCouponService bulkCouponService = new BulkCouponService();

		bulkCoupon.setCpnID(cpnID);
		BulkCouponList = bulkCouponService.getByExample(bulkCoupon);

		/*SET ATTRIBUTE*/
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("BulkCouponList", BulkCouponList);
		request.setAttribute("part", BulkCouponList.get(0).getPart());
		request.setAttribute("membership", BulkCouponList.get(0).getCpnName());
		request.setAttribute("cpnName", BulkCouponList.get(0).getName());
		request.setAttribute("cpnID", BulkCouponList.get(0).getCpnID());

		request.setAttribute("partnerName",BulkCouponList.get(0).getPartnerName());
		request.setAttribute("brandName",BulkCouponList.get(0).getBrandName());

		//��¥ ������ ���ڿ��� ��ȭ�ϴ� �κ�
		request.setAttribute("regDtm", DateTime.format("yyyy-MM-dd"));
		request.setAttribute("regUser", getSessionMgrId(request));

		return "/member/bulk_coupon_detail_reg";
	}


	/**
	 * bulk ������ �������� ������ �����ϴ�.
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */

	@RequestMapping(value="/member/bulk_coupon_edit.ms")
	public String BulkCouponEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

			/* GET PARAMETERS */
			String CpnID = checkStr(request.getParameter("cpnID"), "");

			/*��� ��������*/
			List<BulkCoupon> BulkCouponList = null;
			BulkCouponService bulkCouponService = new BulkCouponService();

			BulkCoupon bulkCoupon = new BulkCoupon();
			if(!CpnID.equals("")){bulkCoupon.setCpnID(CpnID);}
			BulkCouponList = bulkCouponService.getByExample(bulkCoupon);

			/* SET ATTRIBUTE */
			request.setAttribute("pageCode", PAGE_CODE);
			if(BulkCouponList.size()>0){
				request.setAttribute("part", BulkCouponList.get(0).getPart());
				request.setAttribute("membership", BulkCouponList.get(0).getCpnName());
				request.setAttribute("cpnName", BulkCouponList.get(0).getName());
				request.setAttribute("cpnID", BulkCouponList.get(0).getCpnID());
				request.setAttribute("cpnTotal", BulkCouponList.get(0).getTotCnt());
				request.setAttribute("cpnState", BulkCouponList.get(0).getCpnIssueStat());
				request.setAttribute("cpnTot", BulkCouponList.get(0).getTotCnt());

				request.setAttribute("partnerName",BulkCouponList.get(0).getPartnerName());
				request.setAttribute("brandName",BulkCouponList.get(0).getBrandName());

				request.setAttribute("cpnRegDay", BulkCouponList.get(0).getRegDtmStr());
				request.setAttribute("cpnRegUser", BulkCouponList.get(0).getRegUser());
//				String date = DateTime.format(BulkCouponList.get(0).getChgDtm(), "yyyy-MM-dd");
//				request.setAttribute("cpnChgDtm", date);
				request.setAttribute("cpnChgDtm", DateTime.format("yyyy-MM-dd"));

				request.setAttribute("cpnChgUser", getSessionMgrId(request));
			}
			return "member/bulk_coupon_detail";
	}


	/**
	 * bulk ������ �������� ���� �� ��� ����
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_edit_up"
	 */

	@RequestMapping(value="/member/bulk_coupon_edit_up.ms")
	public String BulkCouponEditMove(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
			/* GET PARAMETERS */
			String result = "1";
			int cpnLastSeqNo = 0; //-- ������ȣ �߻� ������ �ʱ�ȭ
			String CpnID = checkStr(request.getParameter("cpnID"), "");
			System.out.println("�������̵� : " + CpnID);
			if(CpnID.equals("")){
				JsonErrMsg("err", response);
				return null;
			}
			System.out.println("CpnID : " + CpnID);
			
			/*����*/
			String ra_Opt = checkStr(request.getParameter("ra_regOpt"), "Add"); //-- [2012.11.22  by trkim] ������ȣ�� �̾ ����ϵ��� �⺻ ���� ��.
			MwCsBulkCpnService mwCsBulkCpnService = new MwCsBulkCpnService();
			
			if(ra_Opt.equals("New")){//-- ���� ������ȣ�� ����� ���� ����� �ؾ��ϴ� ���
				MwCsBulkCpnExample mwCsBulkCpnExample = new MwCsBulkCpnExample();
				mwCsBulkCpnExample.or().andCpnIdEqualTo(CpnID);
				mwCsBulkCpnService.delete(mwCsBulkCpnExample);
			}
			else{
				MwCsBulkCpnExample mwCsBulkCpnExample = new MwCsBulkCpnExample();
				mwCsBulkCpnExample.or().andCpnIdEqualTo(CpnID);
				cpnLastSeqNo = mwCsBulkCpnService.getCountByExample(mwCsBulkCpnExample);
			}
			
			UpFile upFile = null;
			try{
				upFile = new UpFile(request, "/excelfile/");//���� ���ε�
				ExcelContoller excels = new ExcelContoller(upFile.getFiles(),2);//���� ���� �Ľ�
				excels.setSheet(0);//���������� �ִ� ��Ʈ

				if(excels.ChkTitle("Bulk ���� ����Ʈ")){//������ Ÿ��Ʋ�� üũ
					for(int i = 0; true; i++){
						String[] str = excels.getString(i);
						if(str[0].equals("false")){
							break;
						}
						//���� �Ľ�
						MwCsBulkCpn updateData = new MwCsBulkCpn();
						updateData.setCpnId(CpnID);
						updateData.setBarcode(str[2]);
						updateData.setIssueSeq(cpnLastSeqNo + i);
						updateData.setIssueYn("N");
						updateData.setRegDtm(new Date());
						updateData.setRegUser(getSessionMgrId(request));
						mwCsBulkCpnService.insert(updateData);
						mwCsBulkCpnService.commit();
					}
				}else{
					result = "0";
					mwCsBulkCpnService.rollback();
				}
				}catch (Exception e) {
					// TODO: handle exception
					result = "0";
				}
				request.setAttribute("targetUrl", "/member/bulk_coupon_list.ms");
				request.setAttribute("actResult", result);
				return "common/result_message";
	}

	/**
	 * bulk ������ ���� ���� ���� ����Ʈ�� �� �� �ִ�(popup).
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_list_popup"
	 */

	@RequestMapping(value="/member/bulk_coupon_list_popup.ms")
	public String BulkCouponInfo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		/* ���� ���� �� : ��Ʈ�ѷ����� ����� �ֿ� ������ �����Ѵ�.*/
		List<BulkCoupon> BulkCouponPopup = null;
		BulkCouponService bulkCouponService = new BulkCouponService();


		int count = 0;

		/* GET PARAMETERS */
		String pageNo = checkStr(request.getParameter("nowPage"), "1");
		String rowsPerPage = checkStr(request.getParameter("rowsPerPage"), "15");
		String excel = checkStr(request.getParameter("excel"), "N");

		int page = Integer.parseInt(pageNo);
		int rows = Integer.parseInt(rowsPerPage);

		/* GET PARAMETERS */
		String CpnID = checkStr(request.getParameter("cpnID"), "");

		/*��� ��������*/

		BulkCoupon bulkCoupon = new BulkCoupon();
		if(!CpnID.equals("")){bulkCoupon.setCpnID(CpnID);}
		BulkCouponPopup = bulkCouponService.getPopupByExample(bulkCoupon);
		KTDBCipher cipher = new KTDBCipher();
		
		if(!"Y".equals(excel)){
			BulkCouponPopup = bulkCouponService.getPopupByExamplePage(bulkCoupon, page, rows);
		}
		else{
			BulkCouponPopup = bulkCouponService.getPopupByExample(bulkCoupon);
		}
		
		for(int i=0;i<BulkCouponPopup.size();i++){
			try{
				//��ȣȭ ���ڵ� ����
				String Name = BulkCouponPopup.get(i).getAuthName();
				String s="";
				Name = cipher.decoding(Name);
				for(int j=0; j<Name.length()-2; j++){
					s += "*";
				}
				BulkCouponPopup.get(i).setAuthName(Name.substring(0, Name.length()-1)+"*");
				BulkCouponPopup.get(i).setUserID(BulkCouponPopup.get(i).getUserID().substring(0, BulkCouponPopup.get(i).getUserID().length()-3)+"***");


				Date Dates = DateTime.parse(BulkCouponPopup.get(i).getRegDay(), "yyyyMMdd");
				BulkCouponPopup.get(i).setRegDay(DateTime.format(Dates, "yyyy-MM-dd"));

			}catch (Exception e) {
				// TODO: handle exception
			}
		}


		/*��� ���� ���� ��������*/
		count = bulkCouponService.getPopupcountByExample(bulkCoupon);

		/******* paging start *********/
		Paging pageing = new Paging();
		pageing.makeWebPaging(Integer.parseInt(pageNo), count, Integer.parseInt(rowsPerPage));
		request.setAttribute("nowPage", Integer.parseInt(pageNo));
		request.setAttribute("paging", pageing.getSb());
		/******* paging end *********/

		/* SET ATTRIBUTE */
		request.setAttribute("pageCode", PAGE_CODE);
		if(BulkCouponPopup.size()>0){
			request.setAttribute("count", count);
			request.setAttribute("BulkCouponPopup", BulkCouponPopup);
			request.setAttribute("pageNo", page);
			request.setAttribute("rowsPerPage", rows);
			request.setAttribute("pagesPerPage", page);
			request.setAttribute("rows", rowsPerPage);
			request.setAttribute("cpnID", CpnID);
		}
		
//			return "member/bulk_coupon_list_popup";
		String target = "member/bulk_coupon_list_popup";
		
		// ���� ������ ���.. ��� ����
		if("Y".equals(excel)){
			target = "member/bulk_coupon_list_excel";
		}
		
		return target;
	}

	/**
	 * bulk ������ �������� ���� �� ��� ����
	 *
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/bulk_coupon_detail"
	 */

	@RequestMapping(value="/member/bulk_coupon_edit_del.ms")
	public String BulkCouponDelete(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

			/* GET PARAMETERS */
			String CpnID = checkStr(request.getParameter("cpnID"), "");

			MwCsBulkCpnService mwCsBulkCpnService = new MwCsBulkCpnService();
			try{
				MwCsBulkCpnExample mwCsBulkCpnExample = new MwCsBulkCpnExample();
				mwCsBulkCpnExample.or().andCpnIdEqualTo(CpnID);
				mwCsBulkCpnService.delete(mwCsBulkCpnExample);

				mwCsBulkCpnService.commit();
			}catch (Exception e) {
				// TODO: handle exception
				mwCsBulkCpnService.rollback();
				JsonErrMsg("err", response);
				return null;
			}

			JsonErrMsg("", response);
			return null;
	}


	/**���� ������ �Ѱ��� �������� ����*/
	private String convDate(String Date, String Format, String ResultFormat){
		String result = "";
		try{
			SimpleDateFormat format = new SimpleDateFormat(Format);
			Date AccessTime = format.parse(Date, new ParsePosition(0));
			format = new SimpleDateFormat(ResultFormat);//yyyyMMdd HH:mm:ss
			result = format.format(AccessTime);
		}catch (Exception e) {
			// TODO: handle exception
		}

		return result;
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