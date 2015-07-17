package com.wallet.shop.web.base;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.common.util.FileUtil;
import com.wallet.common.util.Log;
import com.wallet.common.util.Paging;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

import com.wallet.membership.model.custom.Image;
import com.wallet.membership.service.custom.ImageService;

import com.wallet.shop.model.custom.ShopCategory;
import com.wallet.shop.service.custom.ShopCategoryService;

@Controller
public class ShopCategoryController extends CommonController {
	private final String PAGE_CODE = "CATEGORY_LIST";
	private Logger log = Log.getLogger("logs");

	/**
	 * @Method Name : ShopCategoryList
	 * @Description : ī�װ�(��з�/�ߺз� ��) �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@RequestMapping(value="/shop/category_list.ms")
	public String ShopCategoryList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ShopCategory> categoryList = null;
		ShopCategoryService categoryService = new ShopCategoryService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
		
		params.put("upperCateId", upperCateId); //-- ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
		
		params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ����¿� ���� �⺻�� ����
		params.put("cateName", checkStr(request.getParameter("cateName"), "")); //-- ���õ� ī�װ��� ī�װ���
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		
		
			params.put("sdate", checkStr(request.getParameter("sdate")));
			params.put("edate", checkStr(request.getParameter("edate")));
		
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 

		params.put("cateId", checkStr(request.getParameter("cateId"), "")); //-- ���õ� ��ī�� ī�װ��� ���� ��

		log.debug("@@@@@@@@@@ ShopCategoryList params : "+ params); //##
		Integer categoryTotCnt = categoryService.selectTotCategoryCnt(params); //-- �� ���� ��(��ȸ���� ��� ����)
		categoryList = categoryService.selectShopCategoryList(params); //-- ��з� �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("categoryTotCnt", categoryTotCnt);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("categoryListCnt", categoryList.size());
		request.setAttribute("cateName", params.get("cateName"));
		request.setAttribute("showYN", params.get("showYN"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt", params.get("se_termOpt"));
		
		params.clear();
		
		return "shop/category_list";
	}
	
	/**
	 * @Method Name : ShopCategoryRegister
	 * @Description : ��ī�� ī�װ� ���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/category_register.ms")
	public String ShopCategoryRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{

		//String memberNotiUrl = PropertiesUtil.get("MEMBERSHIP_NOTI_URL"); //-- ������� html url ���
		
		request.setAttribute("today", today());
		//request.setAttribute("memberNotiUrl", memberNotiUrl);
		return "shop/category_register";
	}
	

	
	/**
	 * @Method Name : ShopCategoryRegisterAct
	 * @Description : ��ī�� ī�װ��� ��� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/category_registerAct.ms")
	public String ShopCategoryRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0; //-- �̹��� ����� ������ 0:����, 1:����
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String regUser = getSessionName(request);
		
		params.put("upperCateId", checkStr(request.getParameter("upperCateId"), "CAT000000000"));
		int dispOrder = categoryService.selectShowCategoryListCnt(params)+1;
		
		try{
			//-- ���� ó���� ���� ����� ���� setting
			params.put("regUser", regUser); //-- �����ID
			params.put("upperCateId", checkStr(request.getParameter("upperCateId"), "CAT000000000")); //-- ��ī�� ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
			params.put("cateName", checkStr(request.getParameter("cateName"), ""));
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- ��������
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ���⿩��
			params.put("linkURL", checkStr(request.getParameter("linkURL"), ""));
			params.put("useMarketYN", checkStr(request.getParameter("useMarketYN"), "N"));
			params.put("dispOrder", dispOrder); //-- �ű� ��� ��, ���ļ��� ���� ������ ������
			params.put("marketInfo", checkStr(request.getParameter("marketInfo"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));
			/*
			String cateImg_i3GS = checkStr(request.getParameter("cateImg_i3GS"), "");
			String cateImg_i4S = checkStr(request.getParameter("cateImg_i4S"), "");
			String cateImg_android = checkStr(request.getParameter("cateImg_android"), "");
			*/
			String cateImg_commOs = checkStr(request.getParameter("cateImg_commOs"), "");
			
			log.debug("@@@@@@@@@@ ShopCategoryRegisterAct params : "+ params); //##
			
			result = categoryService.insertShopCategory(params); //-- ����� ���
			
			String cateId = categoryService.selectLastCateId(params); //-- ��ϵ� ��ī�� ī�װ��� ID�� DB���� ��ȸ�Ѵ�.
			
			params.put("cateId", checkStr(request.getParameter("cateId"), ""));
			
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "00"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/shop/category_register.ms");
				categoryService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/category_list.ms");
				categoryService.commit();
				imgService.commit();
			}
			
			params.clear();
		}
		catch(Exception e){
			categoryService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/category_register.ms");
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : ShopCategoryEditor
	 * @Description : ��ī�� ī�װ� ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/category_editor.ms")
	public String ShopCategoryEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		
		try{
			String memberNotiUrl = PropertiesUtil.get("MOCA_SHOP_CATEGORY_IMG"); //-- ��ī�� ��з� �̹��� ���
			String today = today();
			
			params.put("cateId", cateId); //--���õ� ��ī�� ī�װ� ID

			log.debug("@@@@@@@@@@ ShopCategoryEditor params : "+ params); //##
			
			ShopCategory aShopCategory = categoryService.selectAShopCategory(params); //-- �������ȸ
			
			request.setAttribute("today", today);
			request.setAttribute("aShopCategory", aShopCategory);
			
			params.clear();
			return "shop/category_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/shop/category_list.ms");
			
			e.printStackTrace();
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : ShopCategoryEditorAct
	 * @Description : ��ī�� ī�װ��� ���� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/category_editorAct.ms")
	public String ShopCategoryEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> ordParams = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String chgUser = getSessionName(request);
		String dispOrder = checkStr(request.getParameter("dispOrder"), "0");
		
		try{
			
			//-- ���� ó���� ���� ����� ���� setting 
			params.put("chgUser", chgUser); //-- ������ID
			params.put("cateId", cateId);
			params.put("upperCateId", upperCateId); //-- ��ī�� ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
			params.put("cateName", checkStr(request.getParameter("cateName"), ""));
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- ��������
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ���⿩��
			
			int showCateCnt = categoryService.selectShowCategoryListCnt(params);
			
			System.out.println("####showCateCnt : " + showCateCnt);//##
			
			//-- ���⿩�θ� ������ ���� ��, dispOrder���� 0���� ����
			if("N".equals(params.get("showYN"))){
				params.put("dispOrder", "0");
				//-- ������ ����Ǵ� ī�װ��� ������ order�� �ƴ� ���,
				if(!dispOrder.equals(""+showCateCnt)){
					ordParams.put("upperCateId", upperCateId);
					//ordParams.put("cateId", cateId);
					ordParams.put("fromOrder", dispOrder + 1);
					ordParams.put("toOrder", showCateCnt);
					ordParams.put("calulateOpt", "DOWN");
					
					categoryService.updateShopCatogoryOrderOpt(ordParams);
				}
			}
			else if("Y".equals(params.get("showYN")) && "0".equals(dispOrder)){
				params.put("dispOrder", showCateCnt+1);
			}
			
			params.put("linkURL", checkStr(request.getParameter("linkURL"), ""));
			params.put("useMarketYN", checkStr(request.getParameter("useMarketYN"), "N"));
			params.put("marketInfo", checkStr(request.getParameter("marketInfo"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));
			if("N".equals(params.get("useMarketYN")) && "".equals(params.get("linkURL"))){
				params.put("linkURL", "");
				params.put("marketInfo", "");
			}


			String cateImg_commOs = checkStr(request.getParameter("cateImg_commOs"), "");

			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "00"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			/*
			String cateImg_i3GS = checkStr(request.getParameter("cateImg_i3GS"), "");
			String cateImg_i4S = checkStr(request.getParameter("cateImg_i4S"), "");
			String cateImg_android = checkStr(request.getParameter("cateImg_android"), "");
			
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "01"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			*/

			log.debug("@@@@@@@@@@ ShopCategoryEditorAct params : "+ params); //##
			
			result = categoryService.updateShopCategory(params);

			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/shop/category_editor.ms?cateId=" + cateId);
				categoryService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/category_list.ms");
				categoryService.commit();
				imgService.commit();
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/category_editor.ms?cateId=" + cateId);

			categoryService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : ShopCategoryDeleteAct
	 * @Description : ��ī�� ī�װ��� �����Ѵ�.(��������)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/category_deleteAct.ms")
	public String ShopCategoryDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
		String cateId = checkStr(request.getParameter("cateId"), "");
		
		try{
			params.put("cateId", cateId);
			//params.put("upperCateId", upperCateId); //-- ���⼭ �� �ڵ��� �ּ��� Ǯ�� ��� ��з��� �����.
			
			imgParams.put("id", cateId);
			imgParams.put("level", "");

			log.debug("@@@@@@@@@@ ShopCategoryDeleteAct params : "+ params); //##
			
			result = categoryService.deleteShopCategory(params); //-- ���� DB���� ������ ��
			
			/*
			if(upperCateId.equals("CAT000000000")){
				//-- ���� ī�װ� ����
				params.clear();
				params.put("upperCateId", cateId);
				categoryService.deleteShopCategory(params);
			}
			*/
			imgResult = imgService.deleteImage(imgParams);
			
			request.setAttribute("cateId", cateId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				categoryService.rollback();
				imgService.rollback();
				
				request.setAttribute("targetUrl", "/shop/category_editor.ms?cateId=" + cateId);
			}
			else{
				categoryService.commit();
				imgService.commit();
				
				request.setAttribute("targetUrl", "/shop/category_list.ms");
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/category_editor.ms?cateId=" + cateId);
			
			imgService.rollback();
			categoryService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	

	/**
	 * @Method Name : ShopSecondCategoryList
	 * @Description : �ߺз�  �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.10
	 */
	@RequestMapping(value="/shop/secondCategory_list.ms")
	public String ShopSecondCategoryList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ShopCategory> secondCategoryList = null;
		ShopCategoryService categoryService = new ShopCategoryService();
		HashMap<String, Object> params = new HashMap<String,Object>();

		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
		params.put("cateId", upperCateId);
		ShopCategory upperCateInfo = categoryService.selectAShopCategory(params); //-- ��з������� ���� ��ȸ
		
		params.put("upperCateId", upperCateId);
		Integer secondCategoryTotCnt = categoryService.selectTotSecondCategoryCnt(params);
		
		
		params.put("upperCateId", upperCateId); //-- ��ī�� ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
		
		params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ����¿� ���� �⺻�� ����
		params.put("cateName", checkStr(request.getParameter("cateName"), "")); //-- ���õ� ��ī�� ī�װ� �ߺз���
		params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- ��ȸ�Ⱓ����
		
		params.put("sdate", checkStr(request.getParameter("sdate")));
		params.put("edate", checkStr(request.getParameter("edate")));
		
		params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 

		params.put("cateId", checkStr(request.getParameter("cateId"), "")); //-- ���õ� ī�װ��� ���� ��

		log.debug("@@@@@@@@@@ ShopCategoryList params : "+ params); //##
		
		secondCategoryList = categoryService.selectShopSecondCategoryList(params); //-- �����ȸ
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("secondCategoryTotCnt", secondCategoryTotCnt);
		request.setAttribute("categoryList", secondCategoryList);
		request.setAttribute("categoryListCnt", secondCategoryList.size());
		request.setAttribute("upperCateId", upperCateId);
		request.setAttribute("upperCateInfo", upperCateInfo);
		request.setAttribute("cateName", params.get("cateName"));
		request.setAttribute("showYN", params.get("showYN"));
		request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
		request.setAttribute("sdate", params.get("sdate"));
		request.setAttribute("edate", params.get("edate"));
		request.setAttribute("se_termOpt", params.get("se_termOpt"));
		
		params.clear();
		
		return "shop/secondCategory_list";
	}
	

	
	/**
	 * @Method Name : ShopSecondCategoryRegister
	 * @Description : ī�װ� �ߺз� ���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.140
	 */
	@RequestMapping(value="/shop/secondCategory_register.ms")
	public String ShopSecondCategoryRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ShopCategoryService categoryService = new ShopCategoryService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		ShopCategory upperCateInfo = null;

		String cateId = checkStr(request.getParameter("cateId"), "");
		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");

		try{
			params.put("cateId", upperCateId);
			upperCateInfo = categoryService.selectAShopCategory(params); //-- ��з������� ���� ��ȸ
			
			request.setAttribute("upperCateInfo", upperCateInfo);
			request.setAttribute("today", today());

			return "shop/secondCategory_register";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + upperCateId);
			
			e.printStackTrace();
			return "common/result_message";
		}
	}

	/**
	 * @Method Name : ShopSecondCategoryRegisterAct
	 * @Description : ī�װ��� ��� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/secondCategory_registerAct.ms")
	public String ShopSecondCategoryRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0; //-- �̹��� ����� ������ 0:����, 1:����
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String regUser = getSessionName(request);
		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
		params.put("upperCateId", upperCateId);
		
		int dispOrder = categoryService.selectShowCategoryListCnt(params)+1;
		
		try{
			//-- ���� ó���� ���� ����� ���� setting
			
			params.put("regUser", regUser); //-- �����ID
			params.put("upperCateId", upperCateId); //-- ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
			params.put("cateName", checkStr(request.getParameter("cateName"), ""));
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- ��������
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ���⿩��
			params.put("linkURL", checkStr(request.getParameter("linkURL"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));
			params.put("dispOrder", dispOrder);

			String cateImg_commOs = checkStr(request.getParameter("cateImg_commOs"), "");
			/*
			String cateImg_i3GS = checkStr(request.getParameter("cateImg_i3GS"), "");
			String cateImg_i4S = checkStr(request.getParameter("cateImg_i4S"), "");
			String cateImg_android = checkStr(request.getParameter("cateImg_android"), "");
			*/
			log.debug("@@@@@@@@@@ ShopCategoryRegisterAct params : "+ params); //##
			
			result = categoryService.insertShopCategory(params); //-- ����� ���
			
			String cateId = categoryService.selectLastCateId(params); //-- ��ϵ� ī�װ��� ID�� DB���� ��ȸ�Ѵ�.
			
			params.put("cateId", cateId);
			
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "00"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			
			
			/*
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "01"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14");
			imgParams.put("osType", "02");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14");
			imgParams.put("osType", "11");

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			*/
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/shop/secondCategory_register.ms?upperCateId=" + upperCateId);
				categoryService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + upperCateId);
				categoryService.commit();
				imgService.commit();
			}
			
			params.clear();
		}
		catch(Exception e){
			categoryService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/secondCategory_register.ms?upperCateId=" + upperCateId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}

	
	/**
	 * @Method Name : ShopSecondCategoryEditor
	 * @Description : ī�װ� ���з� ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.10
	 */
	@RequestMapping(value="/shop/secondCategory_editor.ms")
	public String ShopSecondCategoryEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		ShopCategory aShopCategory = null;
		ShopCategory upperCateInfo = null;
		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
		String cateId = checkStr(request.getParameter("cateId"), "");
		
		try{
			String today = today();
			
			params.put("cateId", upperCateId);
			upperCateInfo = categoryService.selectAShopCategory(params);
			
			params.put("cateId", cateId); //--���õ� ī�װ� ID
			params.put("upperCateId", upperCateId);

			log.debug("@@@@@@@@@@ ShopCategoryEditor params : "+ params); //##
			
			aShopCategory = categoryService.selectAShopCategory(params); //-- �������ȸ
			
			request.setAttribute("today", today);
			request.setAttribute("cateId", cateId);
			request.setAttribute("upperCateInfo", upperCateInfo);
			request.setAttribute("aShopCategory", aShopCategory);
			
			params.clear();
			return "shop/secondCategory_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + aShopCategory.getUpperCateId());
			
			e.printStackTrace();
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : ShopSecondCategoryEditorAct
	 * @Description : ī�װ��� ���� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/secondCategory_editorAct.ms")
	public String ShopSecondCategoryEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> ordParams = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String chgUser = getSessionName(request);
		String dispOrder = checkStr(request.getParameter("dispOrder"), "0");
		
		try{
			
			//-- ���� ó���� ���� ����� ���� setting 
			params.put("chgUser", chgUser); //-- ������ID
			params.put("cateId", cateId);
			params.put("upperCateId", upperCateId); //-- ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
			params.put("cateName", checkStr(request.getParameter("cateName"), ""));
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- ��������
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ���⿩��
			
			int showCateCnt = categoryService.selectShowCategoryListCnt(params);
			
			//-- ���⿩�ΰ� ������ ����Ǵ� ���, dispOrder ���� 0���μ�������
			if(params.get("showYN").equals("N")){
				params.put("dispOrder", "0");
				//-- ������ ����Ǵ� ī�װ��� ������ order�� �ƴ� ���,
				if(!dispOrder.equals(""+showCateCnt)){
					ordParams.put("upperCateId", upperCateId);
					//ordParams.put("cateId", cateId);
					ordParams.put("fromOrder", dispOrder + 1);
					ordParams.put("toOrder", showCateCnt);
					ordParams.put("calulateOpt", "DOWN");
					
					categoryService.updateShopCatogoryOrderOpt(ordParams);
				}
			}
			else if("Y".equals(params.get("showYN")) && "0".equals(dispOrder)){
				params.put("dispOrder", showCateCnt+1);
			}
			params.put("linkURL", checkStr(request.getParameter("linkURL"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));
			/*
			String cateImg_i3GS = checkStr(request.getParameter("cateImg_i3GS"), "");
			String cateImg_i4S = checkStr(request.getParameter("cateImg_i4S"), "");
			String cateImg_android = checkStr(request.getParameter("cateImg_android"), "");
			*/
			String cateImg_commOs = checkStr(request.getParameter("cateImg_commOs"), "");
			
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "00"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			/*
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "01"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_i3GS);
			imgResult = imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14");
			imgParams.put("osType", "02");
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_i4S);
			imgResult = imgResult * imgService.insertImage(imgParams);
			
			imgParams.clear();
			imgParams.put("level", "");
			imgParams.put("id", cateId);
			imgParams.put("useType", "14");
			imgParams.put("osType", "11");
			imgService.deleteImage(imgParams);

			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_android);
			imgResult = imgResult * imgService.insertImage(imgParams);
			*/

			log.debug("@@@@@@@@@@ ShopCategoryEditorAct params : "+ params); //##
			
			result = categoryService.updateShopCategory(params);

			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/shop/secondCategory_editor.ms?cateId=" + cateId);
				categoryService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId="+upperCateId);
				categoryService.commit();
				imgService.commit();
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/secondCategory_editor.ms?cateId=" + cateId);

			categoryService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : updateCategoryDispOrder
	 * @Description : ��з� �� ���ü����� �����Ѵ�.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2013.04.11
	 */
	@RequestMapping(value="/shop/updateCategoryDispOrder.ms")
	public String updateCategoryDispOrder(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result1 = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int result2 = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		
		List<ShopCategory> categoryList = null;
		ShopCategoryService categoryService = new ShopCategoryService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		try{
			params.put("calulateOpt", checkStr(request.getParameter("calulateOpt"), ""));
			params.put("upperCateId", checkStr(request.getParameter("upperCateId"), "CAT000000000"));
			// , 3�� �Խù��� 1�� ������ �� UP
			int fromOrder = Integer.parseInt(checkStr(request.getParameter("fromOrder"), "0"));
			int toOrder =  Integer.parseInt(checkStr(request.getParameter("toOrder"), "1"));
			
			if(fromOrder < toOrder){ //-- 1�� �Խù��� 3������ ������ �� : DOWN
				params.put("calulateOpt", "DOWN");
			}
			else if(fromOrder > toOrder){
				params.put("calulateOpt", "UP");
			}
			//params.put("dispOrder", fromOrder);
			params.put("fromOrder",fromOrder);
			params.put("toOrder", toOrder);
			
			params.put("cateId", checkStr(request.getParameter("cateId"), ""));
			
			String chgUser = getSessionName(request);
			
			if(fromOrder != 0){
				result1 = categoryService.updateShopCatogoryOrderOpt(params);
			}
			result2 = categoryService.updateShopCatogoryOrder(params);
		
			if(fromOrder != 0){
				if((result1 * result2) == 0){
					categoryService.rollback();
					request.setAttribute("actResult", "0");
					request.setAttribute("targetUrl", "/shop/category_list.ms");
				}
				else{
					request.setAttribute("actResult", "1");
					request.setAttribute("targetUrl", "/shop/category_list.ms");
					categoryService.commit();
				}
			}
			else{
				if(result2 == 0){
					categoryService.rollback();
					request.setAttribute("actResult", "0");
					request.setAttribute("targetUrl", "/shop/category_list.ms");
				}
				else{
					request.setAttribute("actResult", "1");
					request.setAttribute("targetUrl", "/shop/category_list.ms");
					categoryService.commit();
				}
				
			}

			String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
			
			params.put("upperCateId", upperCateId); //-- ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
			
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ����¿� ���� �⺻�� ����
			params.put("cateName", checkStr(request.getParameter("cateName"), "")); //-- ���õ� ī�װ��� ī�װ���
			params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
			
			
			params.put("sdate", checkStr(request.getParameter("sdate")));
			params.put("edate", checkStr(request.getParameter("edate")));
						
			params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 

			params.put("cateId",""); //-- ���õ� ��ī�� ī�װ��� ���� ��

			log.debug("@@@@@@@@@@ ShopCategoryList params : "+ params); //##
			Integer categoryTotCnt = categoryService.selectTotCategoryCnt(params); //-- �� ���� ��(��ȸ���� ��� ����)
			categoryList = categoryService.selectShopCategoryList(params); //-- ��з� �����ȸ
			
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("categoryTotCnt", categoryTotCnt);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("categoryListCnt", categoryList.size());
			request.setAttribute("cateName", params.get("cateName"));
			request.setAttribute("showYN", params.get("showYN"));
			request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
			request.setAttribute("sdate", params.get("sdate"));
			request.setAttribute("edate", params.get("edate"));
			request.setAttribute("se_termOpt", params.get("se_termOpt"));
			
			params.clear();
			
		}
		catch(Exception e){
			categoryService.rollback();
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/shop/category_list.ms");
			
			e.printStackTrace();
		}
		
		return "shop/category_list";
	}

	
	/**
	 * @Method Name : updateSecondCategoryDispOrder
	 * @Description : ��з� �� ���ü����� �����Ѵ�.
	 * @param : 
	 * @return : String 'YYYY-MM-DD'
	 * @author trkim
	 * @since 2013.04.11
	 */
	@RequestMapping(value="/shop/updateSecondCategoryDispOrder.ms")
	public String updateSecondCategoryDispOrder(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result1 = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int result2 = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		
		ShopCategoryService categoryService = new ShopCategoryService();
		HashMap<String, Object> params = new HashMap<String,Object>();
		
		try{
			String upperCateId = checkStr(request.getParameter("upperCateId"), "CAT000000000");
			params.put("cateId", upperCateId);
			ShopCategory upperCateInfo = categoryService.selectShopSecondCategoryInfo(params); //-- ��з������� ���� ��ȸ
			
			params.put("calulateOpt", checkStr(request.getParameter("calulateOpt"), ""));
			int fromOrder = Integer.parseInt(checkStr(request.getParameter("fromOrder"), "0"));
			int toOrder =  Integer.parseInt(checkStr(request.getParameter("toOrder"), "1"));
			
			if(fromOrder < toOrder){ //-- 1�� �Խù��� 3������ ������ �� : DOWN
				params.put("calulateOpt", "DOWN");
			}
			else if(fromOrder > toOrder){
				params.put("calulateOpt", "UP");
			}
			//params.put("dispOrder", fromOrder);
			params.put("fromOrder",fromOrder);
			params.put("toOrder", toOrder);
			
			params.put("cateId", checkStr(request.getParameter("cateId"), ""));
			params.put("upperCateId", upperCateId);
			String chgUser = getSessionName(request);
			
			if(fromOrder != 0){ //-- �űԵ���� �༮�� �ƴ� ��, 
				result1 = categoryService.updateShopCatogoryOrderOpt(params);
			}
			else{ 
				
			}
			result2 = categoryService.updateShopCatogoryOrder(params);
		
			if(fromOrder != 0){
				if((result1 * result2) == 0){
					categoryService.rollback();
					//request.setAttribute("actResult", "0");
					//request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + upperCateId");
				}
				else{
					//request.setAttribute("actResult", "1");
					//request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + upperCateId");
					categoryService.commit();
				}
			}
			else{
				if(result2 == 0){
					categoryService.rollback();
					//request.setAttribute("actResult", "0");
					//request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + upperCateId");
				}
				else{
					//request.setAttribute("actResult", "1");
					//request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + upperCateId");
					categoryService.commit();
				}
				
			}

			List<ShopCategory> secondCategoryList = null;
			
			Integer secondCategoryTotCnt = categoryService.selectTotSecondCategoryCnt(params);
			
			
			params.put("upperCateId", upperCateId); //-- ��ī�� ī�װ� ���а�  CAT00000000:��з�, CATxxxxxxxxx:�ߺз�
			
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ����¿� ���� �⺻�� ����
			params.put("cateName", checkStr(request.getParameter("cateName"), "")); //-- ���õ� ��ī�� ī�װ� �ߺз���
			params.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- ��ȸ�Ⱓ����
			
			
			params.put("sdate", checkStr(request.getParameter("sdate")));
			params.put("edate", checkStr(request.getParameter("edate")));
			
			
			params.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 


			log.debug("@@@@@@@@@@ ShopCategoryList params : "+ params); //##
			
			secondCategoryList = categoryService.selectShopSecondCategoryList(params); //-- �����ȸ
			
			/* SET ATTRIBUTEs */
			request.setAttribute("pageCode", PAGE_CODE);
			request.setAttribute("secondCategoryTotCnt", secondCategoryTotCnt);
			request.setAttribute("categoryList", secondCategoryList);
			request.setAttribute("categoryListCnt", secondCategoryList.size());
			request.setAttribute("upperCateId", upperCateId);
			request.setAttribute("upperCateInfo", upperCateInfo);
			request.setAttribute("cateName", params.get("cateName"));
			request.setAttribute("showYN", params.get("showYN"));
			request.setAttribute("ra_searchTerm", params.get("ra_searchTerm"));
			request.setAttribute("sdate", params.get("sdate"));
			request.setAttribute("edate", params.get("edate"));
			request.setAttribute("se_termOpt", params.get("se_termOpt"));
			
			
		}
		catch(Exception e){
			categoryService.rollback();
			e.printStackTrace();
		}
		
		return "shop/secondCategory_list";
	}
	
	/**
	 * @Method Name : ShopSecondCategoryDeleteAct
	 * @Description : ��ī��  �ߺз� ī�װ��� �����Ѵ�.(��������)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.12
	 */
	@RequestMapping(value="/shop/secondCategory_deleteAct.ms")
	public String ShopSecondCategoryDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		String upperCateId = checkStr(request.getParameter("upperCateId"), "");
		
		try{
			params.put("cateId", cateId);
			
			imgParams.put("id", cateId);
			imgParams.put("level", "");

			log.debug("@@@@@@@@@@ ShopCategoryDeleteAct params : "+ params); //##
			
			//-- ���õ� ī�װ��� ���� DB���� ������.
			result = categoryService.deleteShopCategory(params); 
			imgResult = imgService.deleteImage(imgParams);
			
			/* ==> ���� �̹����� �� ã�Ƽ� �����־�� �ϴ� ���� �߻�, ���� ī�װ��� �ִ� ���, ������ �ȵǵ��� ȭ�鿡�� ó����.
			if(upperCateId.equals("CAT000000000")){ //-- ������ ī�װ��� ��з� �� ��, ������ �ߺз� ī�װ��� ����ó��
			}
			*/
			request.setAttribute("cateId", cateId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				categoryService.rollback();
				imgService.rollback();
				
				request.setAttribute("targetUrl", "/shop/secondCategory_editor.ms?cateId=" + cateId);
			}
			else{
				categoryService.commit();
				imgService.commit();
				
				request.setAttribute("targetUrl", "/shop/secondCategory_list.ms?upperCateId=" + upperCateId);
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/secondCategory_editor.ms?cateId=" + cateId);
			
			imgService.rollback();
			categoryService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
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
