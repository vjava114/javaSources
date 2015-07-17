/**
 * @author �̰���
 * @Date 2012-08-14
 * */
package com.wallet.membership.web.base;

import java.io.PrintWriter;
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
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.common.DateTime;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.custom.Image;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.custom.ImageService;

@Controller
public class MemberFranchiseListController extends CommonController {
	private final String PAGE_CODE = "MEMBER_FRANCHISE_LIST";
	private Logger log = Log.getLogger("logs");
	
	/**
	 * �귣�� ��� ��ȸ
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return"member/member_franchise_list.ms"
	 */ 
	@RequestMapping(value="/member/member_franchise_list.ms")
	public String TermsList(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		//����Ÿ ���̽� ȣ�� ���� ����
		List<MwCmCompany> MwCmCompanyList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = null;
		
		//����¡ ó���� ���� �Ķ��Ÿ
		String pageNo = checkStr(request.getParameter("nowPage"), "1");
		String rowsPerPage = "15";
//		String rowsPerPage = checkStr(request.getParameter("rowsPerPage"), "15");
		System.out.println("pageNo : " + pageNo);
		System.out.println("rowsPerPage : " + rowsPerPage);
		String Today = DateTime.format("yyyy-MM-dd");
		int page = Integer.parseInt(pageNo);
		int row = Integer.parseInt(rowsPerPage);
		//�Ķ��Ÿ �ޱ�
			String MemberName = checkStr(request.getParameter("partner_name"), null);
			String MemberStat = checkStr(request.getParameter("partner_stat"),null);
			String Sdate = checkStr(request.getParameter("Sdate"), "");
			String Edate = checkStr(request.getParameter("Edate"), "");
			String DateType = checkStr(request.getParameter("sh_type"), "01");
			String PartnerTerm = checkStr(request.getParameter("partner_date"), "01");
			String FranchiseName = checkStr(request.getParameter("franchise_name"), null);
			if(PartnerTerm.equals("01")){
				if(Sdate.equals("") && Edate.equals("")){
					Sdate = Today;
					Edate = Today;
				}
			}
			int count = 0;//��ü ���ڵ� ����
						
				mwCmCompanyExample = new MwCmCompanyExample();
				mwCmCompanyExample.or().andACompIdLike("BRD%");
				mwCmCompanyExample.setOrderByClause("A.REG_DTM DESC, A.COMP_NAME DESC");
				if(MemberName != null && !MemberName.equals("")){
					mwCmCompanyExample.or().andAStoreNameLike("%"+MemberName+"%");
				}
				
				if(DateType.equals("01")){
					mwCmCompanyExample.or().andARegDtmIsNotNull();
				}else if(DateType.equals("02")){
					mwCmCompanyExample.or().andAChgDtmIsNotNull();
				}
				
				if(FranchiseName != null && !FranchiseName.equals("")){
					mwCmCompanyExample.or().andACompNameLike("%"+FranchiseName+"%");
				}
				if(Sdate != null&&!Sdate.equals("")&&Edate != null&&!Edate.equals("")){
					if(DateType.equals("01")){
						try{
							mwCmCompanyExample.or().					
							andARegDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
						}catch (Exception e) {
						}					
					}else if(DateType.equals("02")){
						try{
							mwCmCompanyExample.or().
							andAChgDtmBetween(DateTime.parse(Sdate, "yyyy-MM-dd"), new Date(DateTime.parse(Edate, "yyyy-MM-dd").getTime()+ (1000 * 60 * 60 * 24)));
						}catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			
			MwCmCompanyList = mwCmCompanyService.getByFranchiseExampleOnly(mwCmCompanyExample,page,row);
			count = mwCmCompanyService.getFranchiseSelectByCount(mwCmCompanyExample);
			
			/******* paging start *********/
			Paging pageing = new Paging();
			pageing.makeWebPaging(page, count, row);
			request.setAttribute("nowPage", row);
			request.setAttribute("paging", pageing.getSb());
			request.setAttribute("rowsPerPage", rowsPerPage);
			/******* paging end *********/
			
			for(int i=0;i<MwCmCompanyList.size();i++){
				System.out.println(MwCmCompanyList.get(i).getComplexPaymentYn());
			}
			
			//�˻� ����Ÿ
			request.setAttribute("s_MemberName", MemberName);
			request.setAttribute("s_MemberStat", MemberStat);
			request.setAttribute("s_Sdate", Sdate);
			request.setAttribute("s_Edate", Edate);
			request.setAttribute("s_DateType", DateType);
			
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", page);
			request.setAttribute("rows", row);
			
			request.setAttribute("pagesPerPage", row);
			request.setAttribute("mwCmCompanyList", MwCmCompanyList);
			request.setAttribute("partnerTerm", PartnerTerm);
			request.setAttribute("franchiseName", FranchiseName);
			
			return "member/member_franchise_list";
	}
	
	/**
	 * �귣�� ���
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/member_franchise_register.ms"
	 */

	@RequestMapping(value="/member/member_franchise_add.ms")
	public String MemberFranchiseRegister(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		List<MwCmCompany> PartnerList = null;
		List<MwCmCompany> FranchiseList = null;
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andCompIdLike("CMP%");		
		PartnerList = mwCmCompanyService.getByExample(mwCmCompanyExample);
		
		mwCmCompanyExample = new MwCmCompanyExample();
		mwCmCompanyExample.or().andCompIdLike("BRD%");		
		FranchiseList = mwCmCompanyService.getByExample(mwCmCompanyExample);
		
		String regDay = checkStr(request.getParameter("regDay"), "");
		String regUser = checkStr(request.getParameter("regUser"), "");

		MwCmCompany mwCmCompany= new MwCmCompany();
		if(!regDay.equals("")){
			//��¥ �������� ��ȭ�ϴ� �κ�
			Date dateRegDay = DateTime.parseDate(regDay,"yyyy-MM-dd");
			if(!regDay.equals("")){mwCmCompany.setRegDtm(dateRegDay);}
		}
		if(!regUser.equals("")){
			
		}

		//��¥ ������ ���ڿ��� ��ȭ�ϴ� �κ�
		request.setAttribute("regDtm", DateTime.format("yyyy-MM-dd"));
		request.setAttribute("regUser", getSessionMgrId(request));

		request.setAttribute("PartnerList", PartnerList);//���޻� ����Ʈ
		request.setAttribute("FranchiseList", FranchiseList);//�귣�� ����Ʈ

		return "member/member_franchise_register";
	}
	
	/**
	 *�귣�� ���ε� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/member_franchise_register.ms"
	 */
	@RequestMapping(value="/member/member_franchise_up.ms")
	public String MemberFranchiseUplode(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		int imgResult = 0; //-- �̹��� ����� ������ 0:����, 1:����
		String result = "";
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String franchise_name = checkStr(request.getParameter("franchise_name"),null);
		String compId = checkStr(request.getParameter("compId"), null);
//		String compName = checkStr(request.getParameter("compName"), null);
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���

		String BrandId = checkStr(request.getParameter("brandId"), null);
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
//		MwCmCompany mwCmCompanyid = mwCmCompanyService.getByBrandExampleOnly(mwCmCompanyExample);
//		String BrandId = mwCmCompanyid.getCompId();/**�귣�� ���̵� ����*/
		mwCmCompanyExample.or().andCompIdEqualTo(compId);
		
		MwCmCompany getByComplexYn = mwCmCompanyService.getByExampleOnly(mwCmCompanyExample);
		String ComplexYn = getByComplexYn.getComplexPaymentYn();
		String AllyStat = getByComplexYn.getAllyStat();
		String CompType = getByComplexYn.getCompType();


		//�̹��� ���õ� 
		String ios3_img = checkStr(request.getParameter("ios3_img"), "");
		String ios4s_img = checkStr(request.getParameter("ios4s_img"), "");
		String android_img = checkStr(request.getParameter("android_img"), "");
		String ios3_card_img = checkStr(request.getParameter("ios3_card_img"), "");
		String ios4s_card_img = checkStr(request.getParameter("ios4s_card_img"), "");
		String android_card_img = checkStr(request.getParameter("android_card_img"), "");
		String memo = checkStr(request.getParameter("memo"), "");
		
		try{
			MwCmCompany mwCmCompany = new MwCmCompany();
			mwCmCompany.setRegDtm(new Date());
			mwCmCompany.setRegUser(getSessionMgrId(request));
			mwCmCompany.setCompId(BrandId);
			//�귣�� ��
			mwCmCompany.setCompName(franchise_name);
			mwCmCompany.setComplexPaymentYn(ComplexYn);
			mwCmCompany.setAllyStat(AllyStat);
			mwCmCompany.setCompType(CompType);
			//���޻� ���̵�
			mwCmCompany.setUpperCompId(compId);
			mwCmCompany.setMemo(memo);
			mwCmCompany.setCompLevelType("02");
			System.out.println("franchiseID : " + BrandId);
			System.out.println(BrandId);
			System.out.println(compId);
			//���޻� ��
//			mwCmCompany.setStorePartnerName(compName);
			mwCmCompanyService.FranchiseInsert(mwCmCompany);
			
//			imgParams.put("imgId", checkStr(request.getParameter("compId"), ""));
			mwCmCompanyService.commit();			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			mwCmCompanyService.rollback();
			JsonErrMsg("err",response);
			return null;
			
		}
		try{
			imgParams.put("level", "");
			imgParams.put("id", BrandId);
			imgParams.put("useType", "01");
			imgParams.put("osType", "01");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios3_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.put("level", "");
			imgParams.put("id", BrandId);
			imgParams.put("useType", "01");
			imgParams.put("osType", "02");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios4s_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.put("level", "");
			imgParams.put("id", BrandId);
			imgParams.put("useType", "01");
			imgParams.put("osType", "11");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", android_img);
			imgResult = imgService.insertImage(imgParams);
	
			
			if(!"".equals(ios3_card_img)){
				imgParams.put("level", "");
				imgParams.put("id", BrandId);
				imgParams.put("useType", "13");
				imgParams.put("osType", "01");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("imageHost", imgHost);
				imgParams.put("imageUrl", ios3_card_img);
				imgResult = imgService.insertImage(imgParams);
			}
			if(!"".equals(ios4s_card_img)){
				imgParams.put("level", "");
				imgParams.put("id", BrandId);
				imgParams.put("useType", "13");
				imgParams.put("osType", "02");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("imageHost", imgHost);
				imgParams.put("imageUrl", ios4s_card_img);
				imgResult = imgService.insertImage(imgParams);
			}
			if(!"".equals(android_card_img)){
				imgParams.put("level", "");
				imgParams.put("id", BrandId);
				imgParams.put("useType", "13");
				imgParams.put("osType", "11");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("imageHost", imgHost);
				imgParams.put("imageUrl", android_card_img);
				imgResult = imgService.insertImage(imgParams);
			}
			if(imgResult == 0){
				imgService.rollback();
			}
			else{
				imgService.commit();
			}			

		}catch (Exception e) {
			// TODO: handle exception
			imgService.rollback();
			result = "err";
		}
		JsonErrMsg(result,response);
		return null;
	}
	
	/**
	 * �귣�� ����
	 * 
	 * @param local
	 * @param model
	 * @param request
	 * @param response
	 * @return member/member_franchise_edit
	 */
	
	@RequestMapping(value="/member/member_franchise_edit.ms")
	public String MemberFranchiseInfo(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
//		String partnerID = checkStr(request.getParameter("partnerID"), null);
		String franchiseID = checkStr(request.getParameter("franchiseID"), null);		
		String franchiseName = checkStr(request.getParameter("franchiseName"),null);

		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
		ImageService imgService = new ImageService();

		HashMap<String, Object> imgParams = new HashMap<String, Object>();

		if(franchiseID != null && !franchiseID.equals("")){
//			mwCmCompanyExample.or().andACompIdEqualTo(partnerID);	
			mwCmCompanyExample.or().andACompIdEqualTo(franchiseID);

			MwCmCompany mwCmCompany = mwCmCompanyService.getByFranchiseExampleOnly(mwCmCompanyExample);
			String RegDtm = DateTime.format(mwCmCompany.getRegDtm(), "yyyy-MM-dd");
			String ChgDtm = DateTime.format(mwCmCompany.getChgDtm(), "yyyy-MM-dd");	

			imgParams.put("id", franchiseID); 
			List<Image> imgList = imgService.selectImageList(imgParams);

			request.setAttribute("partner_compName", mwCmCompany.getStorePartnerName());//���޻� �̸�
			request.setAttribute("franchise_compName", mwCmCompany.getCompName());//�귣�� �̸�
			request.setAttribute("franchise_id", franchiseID); //�귣�� ���̵�
			request.setAttribute("regDtm", RegDtm);//�����
			request.setAttribute("regUser", mwCmCompany.getRegUser());//�����
			request.setAttribute("chgDtm", ChgDtm);//������
			request.setAttribute("chgUser", mwCmCompany.getChgUser());//������
//			request.setAttribute("imgList", imgList);
			request.setAttribute("ios3_img", mwCmCompany.getIos3_img());//������ 3S �̹���
			request.setAttribute("ios4s_img", mwCmCompany.getIos4s_img());//������ 4GS �̹���
			request.setAttribute("android_img", mwCmCompany.getAndroid_img());//�ȵ���̵� �̹���
			request.setAttribute("complex_yn", mwCmCompany.getComplexPaymentYn()); //���հ��� ���� ����
			request.setAttribute("ios3_card_img", mwCmCompany.getIos3_img());//������ 3S �̹���
			request.setAttribute("ios4s_card_img", mwCmCompany.getIos4s_img());//������ 4GS �̹���
			request.setAttribute("android_card_img", mwCmCompany.getAndroid_img());//�ȵ���̵� �̹���
			request.setAttribute("memo", mwCmCompany.getMemo());
			
			System.out.println(franchiseName);
		}
		
			return "member/member_franchise_edit";			
	}

	/**
	 *�귣�� ���� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return member/member_franchise_edit_up.ms"
	 */
	
	@RequestMapping(value="/member/member_franchise_edit_up.ms")
	public String MemberFranchiseEdit(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		int imgResult = 0;
		String result = "";
		ImageService imgService = new ImageService();

		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String partner_name = checkStr(request.getParameter("partner_compName"), null);
		String franchise_compName = checkStr(request.getParameter("franchise_compName"),null);
		String franchise_id = checkStr(request.getParameter("franchise_id"),null);
//		String complexPaymentYn = checkStr(request.getParameter("complexPaymentYn"),null);
		String complex_yn = checkStr(request.getParameter("complex_yn"), null);
		//�̹��� ���õ� 
		String ios3_img = checkStr(request.getParameter("ios3_img"), "");
		String ios4s_img = checkStr(request.getParameter("ios4s_img"), "");
		String android_img = checkStr(request.getParameter("android_img"), "");
		String multyYn = checkStr(request.getParameter("multyYN"), "");
		String ios3_card_img = checkStr(request.getParameter("ios3_card_img"), "");
		String ios4s_card_img = checkStr(request.getParameter("ios4s_card_img"), "");
		String android_card_img = checkStr(request.getParameter("android_card_img"), "");
		String memo = checkStr(request.getParameter("memo"), "");
		
		System.out.println("partner_name : " + partner_name);
		System.out.println("franchise_compName : " + franchise_compName);
		System.out.println("franchise_id : " + franchise_id);
		
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		try{
			MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
			
			if(franchise_id != null && !franchise_id.equals("")){
				mwCmCompanyExample.or().andCompIdEqualTo(franchise_id);
			
				MwCmCompany mwCmCompany = new MwCmCompany();
				String allyStat = mwCmCompany.getAllyStat();
				String compType = mwCmCompany.getCompType();
				mwCmCompany.setChgDtm(new Date());
				mwCmCompany.setChgUser(getSessionMgrId(request));
				mwCmCompany.setCompName(franchise_compName);
				mwCmCompany.setCompId(franchise_id);
				mwCmCompany.setStorePartnerName(partner_name);
//				mwCmCompany.setComplexPaymentYn(complexPaymentYn);
				mwCmCompany.setComplexPaymentYn(complex_yn);
				mwCmCompany.setAllyStat(allyStat);
				mwCmCompany.setCompType(compType);
				mwCmCompany.setMemo(memo);
				mwCmCompanyService.update(mwCmCompany, mwCmCompanyExample);
				mwCmCompanyService.commit();

			}
			}catch (Exception e) {
				// TODO: handle exception
				mwCmCompanyService.rollback();
				JsonErrMsg("err",response);
				return null;
			} 
		
		try{
			
			imgParams.put("level", "");
			imgParams.put("id", franchise_id);
			imgParams.put("useType", "01");
			imgParams.put("osType", "01");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios3_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", franchise_id);
			imgParams.put("useType", "01");
			imgParams.put("osType", "02");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", ios4s_img);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", franchise_id);
			imgParams.put("useType", "01");
			imgParams.put("osType", "11");
			imgResult = imgResult * imgService.deleteImage(imgParams);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", android_img);
			imgResult = imgService.insertImage(imgParams);
			
			if(!"".equals(ios3_card_img)){
				imgParams.put("level", "");
				imgParams.put("id", franchise_id);
				imgParams.put("useType", "13");
				imgParams.put("osType", "01");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("imageHost", imgHost);
				imgParams.put("imageUrl", ios3_card_img);
				imgResult = imgService.insertImage(imgParams);
			}
			if(!"".equals(ios4s_card_img)){
				imgParams.put("level", "");
				imgParams.put("id", franchise_id);
				imgParams.put("useType", "13");
				imgParams.put("osType", "02");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("imageHost", imgHost);
				imgParams.put("imageUrl", ios4s_card_img);
				imgResult = imgService.insertImage(imgParams);
			}
			if(!"".equals(android_card_img)){
				imgParams.put("level", "");
				imgParams.put("id", franchise_id);
				imgParams.put("useType", "13");
				imgParams.put("osType", "11");
				imgResult = imgResult * imgService.deleteImage(imgParams);
				imgParams.put("imageHost", imgHost);
				imgParams.put("imageUrl", android_card_img);
				imgResult = imgService.insertImage(imgParams);
			}
			
			if(imgResult == 0){
				imgService.rollback();
			}
			else{
				imgService.commit();
			}			
			
		}catch (Exception e) {
			// TODO: handle exception
			imgService.rollback();
			result = "err";
		}
		JsonErrMsg(result,response);
		return null;	
	}

	/**
	 *�귣�� ����
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @return null
	 */
	
	@RequestMapping(value="/member/member_franchise_delete.ms")
	public String MemberPartnerDelete(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String result = "";		
		String partnerID = checkStr(request.getParameter("franchise_id"), null);
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		ImageService imgService = new ImageService();
		try{
			if(partnerID != null && !partnerID.equals("")){			
			MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();			
			mwCmCompanyExample.or().andCompIdEqualTo(partnerID);			
			mwCmCompanyExample.or().andUpperCompIdEqualTo(partnerID);
			mwCmCompanyService.delete(mwCmCompanyExample);
			mwCmCompanyService.commit();
			imgParams.put("id", partnerID);
			imgService.deleteImage(imgParams);
			imgService.commit();
			}
		}catch (Exception e) {
			// TODO: handle exception		
			mwCmCompanyService.rollback();
			result = "err";
		}
		JsonErrMsg(result,response);
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
