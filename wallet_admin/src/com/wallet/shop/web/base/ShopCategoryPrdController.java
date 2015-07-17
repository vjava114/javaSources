package com.wallet.shop.web.base;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;
import com.wallet.membership.service.custom.ImageService;
import com.wallet.shop.model.custom.ShopCategory;
import com.wallet.shop.model.custom.ShopCategoryPrd;
import com.wallet.shop.service.custom.ShopCategoryPrdService;
import com.wallet.shop.service.custom.ShopCategoryService;



@Controller
public class ShopCategoryPrdController extends CommonController {
	private final String PAGE_CODE = "CATEGORY_PRD_LIST";
	private Logger log = Log.getLogger("logs");

	/**
	 * @Method Name : ShopCategoryPrdList
	 * @Description : ��ǰ �˻�/���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@RequestMapping(value="/shop/product_list.ms")
	public String ShopCategoryPrdList(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ShopCategoryPrd> PrdCategoryList = null;
		
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
				
		HashMap<String, Object> params = new HashMap<String,Object>(); //-- ��ǰ�� ���� ī�װ� ���� ��ȸ��
		HashMap<String, Object> prdParams = new HashMap<String,Object>(); //-- ��ǰ ��ȸ��

		String cateId = checkStr(request.getParameter("cateId"), "");
		
		params.put("cateId", cateId);
		
		ShopCategory upperCateInfo = categoryService.selectAShopCategory(params);
		
		log.debug("@@@@@@@@@@ ShopCategoryPrdList params : "+ params); //##
		
		prdParams.put("cateId", cateId);
		
		prdParams.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ����¿� ���� �⺻�� ����
		prdParams.put("prdName", checkStr(request.getParameter("prdName"), "")); //-- ���õ� ī�װ��� ī�װ���
		prdParams.put("ra_searchTerm", checkStr(request.getParameter("ra_searchTerm"), "all")); //-- ��ȸ�Ⱓ����		//-- ��ȸ�Ⱓ �⺻����, list �������� ó������ �ε� �� ���� parameter�� �Ѿ���� �ʾ� �ʱ� �� ������ �ʿ���.
		
		
			prdParams.put("sdate", checkStr(request.getParameter("sdate")));
			prdParams.put("edate", checkStr(request.getParameter("edate")));
		
		
		prdParams.put("se_termOpt", checkStr(request.getParameter("se_termOpt"), "reg_date")); //-- �Ⱓ������ ���� ���ذ� ����(�����/������). 

		
		log.debug("@@@@@@@@@@ ShopCategoryPrdList prdParams : "+ prdParams); //##
		Integer productTotCnt = categoryPrdService.selectShopCategoryPrdListCnt(prdParams); //-- �� ��ǰ ��(��ȸ���� ��� ����)
		
		PrdCategoryList = categoryPrdService.selectShopPrdList(prdParams); 
		
		
		/* SET ATTRIBUTEs */
		request.setAttribute("pageCode", PAGE_CODE);
		request.setAttribute("prdName", prdParams.get("prdName"));
		request.setAttribute("productTotCnt", productTotCnt);
		request.setAttribute("upperCateInfo", upperCateInfo);
		request.setAttribute("PrdCategoryList", PrdCategoryList);
		request.setAttribute("PrdCategoryListCnt", PrdCategoryList.size());
		//request.setAttribute("cateName", params.get("cateName"));
		request.setAttribute("showYN", prdParams.get("showYN"));
		request.setAttribute("ra_searchTerm", prdParams.get("ra_searchTerm"));
		request.setAttribute("sdate", prdParams.get("sdate"));
		request.setAttribute("edate", prdParams.get("edate"));
		request.setAttribute("se_termOpt", prdParams.get("se_termOpt"));
		
		params.clear();
		prdParams.clear();
		
		return "shop/product_list";
	}
	

	/**
	 * @Method Name : ShopProductRegister
	 * @Description : ��ǰ ���ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.14
	 */
	@RequestMapping(value="/shop/product_register.ms")
	public String ShopProductRegister(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();

		HashMap<String, Object> params = new HashMap<String,Object>();
		
		ShopCategory upperCateInfo = null;

		String cateId = checkStr(request.getParameter("cateId"), "");
				
			params.put("cateId", cateId); //--���Ⱑ upperCateId�� ����.. CAT00000000�� ��ȸ�� �Ǵϱ� �׷��� �Ǹ� �ȵ�. cateId�� ���� �� ����.
			Integer prdShowCnt = categoryPrdService.selectShowCategoryPrdListCnt(params);
			
			upperCateInfo = categoryService.selectAShopCategory(params); //-- ��з������� ���� ��ȸ
			
			request.setAttribute("prdShowCnt", prdShowCnt);
			request.setAttribute("upperCateInfo", upperCateInfo);
			request.setAttribute("today", today());
			
			return "shop/product_register";
		
	}

	/**
	 * @Method Name : productCategoryRegisterAct
	 * @Description : ��ǰ�� ��� �� ����� �����Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@RequestMapping(value="/shop/product_registerAct.ms")
	public String productCategoryRegisterAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0; //-- �̹��� ����� ������ 0:����, 1:����
		
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String regUser = getSessionName(request);
		String cateId = checkStr(request.getParameter("cateId"), "");
		try{
			//-- ���� ó���� ���� ����� ���� setting
			
			params.put("regUser", regUser); //-- �����ID
			params.put("cateId", cateId); 
			params.put("prdName", checkStr(request.getParameter("prdName"), ""));
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- ��������
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ���⿩��
			params.put("prdURL", checkStr(request.getParameter("prdURL"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));

			String cateImg_commOs = checkStr(request.getParameter("prdImg_commOs"), "");
			/*
			String cateImg_i3GS = checkStr(request.getParameter("cateImg_i3GS"), "");
			String cateImg_i4S = checkStr(request.getParameter("cateImg_i4S"), "");
			String cateImg_android = checkStr(request.getParameter("cateImg_android"), "");
			*/
			log.debug("@@@@@@@@@@ ShopCategoryPrdRegisterAct params : "+ params); //##
			
			result = categoryPrdService.insertShopCategoryPrd(params); //-- ��ǰ ���
			
			String prdId = categoryPrdService.selectLastPrdId(params);
			
			params.put("prdId", checkStr(request.getParameter("prdId"), ""));

			imgParams.put("level", "");
			imgParams.put("id", prdId);
			imgParams.put("useType", "15"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "00"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)

			imgParams.put("regUser", regUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", cateImg_commOs);
			imgResult = imgService.insertImage(imgParams);
			
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/shop/product_register.ms?cateId=" + cateId);
				categoryPrdService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
				categoryPrdService.commit();
				imgService.commit();
			}
			
			params.clear();
		}
		catch(Exception e){
			categoryPrdService.rollback();
			imgService.rollback();
			
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/product_register.ms?cateId=" + cateId);
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	////////////////////////ShopSecondCategoryEditor������� prduct editor�� �����Ұ�
	
	/**
	 * @Method Name : ProductEditor
	 * @Description : ��ǰ ���� ����ȭ���� �ε��Ѵ�.
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.10
	 */
	@RequestMapping(value="/shop/product_editor.ms")
	public String productEditor(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		//ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> prdParams = new HashMap<String,Object>();
		
		ShopCategoryPrd PrdInfo = null;
		ShopCategory upperCateInfo = null;
		
		String prdId = checkStr(request.getParameter("prdId"), "");
		String cateId = checkStr(request.getParameter("cateId"), "");
		params.put("cateId", cateId);
		Integer prdShowCnt = categoryPrdService.selectShowCategoryPrdListCnt(params);
		request.setAttribute("prdShowCnt", prdShowCnt);
		
		try{
			String today = today();
			
			prdParams.put("prdId", prdId);
			PrdInfo = categoryPrdService.selectAShopCategoryPrdInfo(prdParams);
			
			log.debug("@@@@@@@@@@ ProductEditor params : "+ prdParams); //##
			params.put("cateId", cateId);
			upperCateInfo = categoryService.selectAShopCategory(params);
			
			request.setAttribute("today", today);
			request.setAttribute("prdId", prdId);
			request.setAttribute("upperCateInfo", upperCateInfo);
			request.setAttribute("PrdInfo", PrdInfo);
			
			
			params.clear();
			prdParams.clear();
			return "shop/product_editor";
		}
		catch(Exception e){
			request.setAttribute("actResult", "0");
			request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
			
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
	@RequestMapping(value="/shop/product_editorAct.ms")
	public String productEditorAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {
		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;
		
		ShopCategoryService categoryService = new ShopCategoryService();
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		String prdId = checkStr(request.getParameter("prdId"), "");
		String imgHost = PropertiesUtil.get("img_host"); //-- ������/��������� �̹��� ���
		String chgUser = getSessionName(request);
		
		
		try{
			
			//-- ���� ó���� ���� ����� ���� setting 
			params.put("chgUser", chgUser); //-- ������ID
			params.put("prdId", prdId);
			params.put("delYN", checkStr(request.getParameter("delYN"), "N")); //-- ��������
			params.put("showYN", checkStr(request.getParameter("showYN"), "Y")); //-- ���⿩��
			
			//-- ���⿩�ΰ� ������ ����Ǵ� ���, dispOrder ���� 0���μ�������
			params.put("prdName", checkStr(request.getParameter("prdName"), ""));
			params.put("prdURL", checkStr(request.getParameter("prdURL"), ""));
			params.put("bigo", checkStr(request.getParameter("bigo"), ""));
			
			String prdImg_commOs = checkStr(request.getParameter("prdImg_commOs"), "");
			
			imgParams.put("level", "");
			imgParams.put("id", prdId);
			imgParams.put("useType", "15"); //-- �����̹��� ��뱸�� (����׷��ڵ� 0016)
			imgParams.put("osType", "00"); //-- �ܸ��ⱸ�� (����׷��ڵ� 0015)
			imgService.deleteImage(imgParams);
			
			imgParams.put("regUser", chgUser);
			imgParams.put("imageHost", imgHost);
			imgParams.put("imageUrl", prdImg_commOs);
			imgResult = imgService.insertImage(imgParams);

			log.debug("@@@@@@@@@@ ShopCategoryPrdEditorAct params : "+ params); //##
			
			result = categoryPrdService.updateShopCategoryPrd(params);

			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);
				categoryPrdService.rollback();
				imgService.rollback();
			}
			else{
				request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
				categoryPrdService.commit();
				imgService.commit();
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);

			categoryPrdService.rollback();
			imgService.rollback();
			
			e.printStackTrace();
		}
		finally{
			return "common/result_message";
		}
	}
	
	/**
	 * @Method Name : ShopSecondCategoryDeleteAct
	 * @Description : ��ī��  �ߺз� ī�װ��� �����Ѵ�.(��������)
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2013.04.12
	 */
	@RequestMapping(value="/shop/product_deleteAct.ms")
	public String productDeleteAct(Map model, Locale locale, HttpServletRequest request, HttpServletResponse response) {

		int result = 0; //--�� �޼ҵ��� ������ 0:����, 1:����
		int imgResult = 0;
		
		ShopCategoryPrdService categoryPrdService = new ShopCategoryPrdService();
		ImageService imgService = new ImageService();
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		HashMap<String, Object> imgParams = new HashMap<String, Object>();
		
		
		String cateId = checkStr(request.getParameter("cateId"), "");
		String prdId = checkStr(request.getParameter("prdId"), "");
		try{
			params.put("cateId", cateId);
			params.put("prdId", prdId);
			
			imgParams.put("id", prdId);
			imgParams.put("level", "");

			log.debug("@@@@@@@@@@ ShopProductDeleteAct params : "+ params); //##
			
			result = categoryPrdService.deleteShopCategoryPrd(params); //-- ���� DB���� ������ ��
			params.clear();
			
			imgResult = imgService.deleteImage(imgParams);
			
			request.setAttribute("cateId", cateId);
			request.setAttribute("prdId", prdId);
			request.setAttribute("actResult", (result * imgResult) + "");
			
			if((result * imgResult) == 0){//-- ���� �����̸�,
				categoryPrdService.rollback();
				imgService.rollback();
				
				request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);
			}
			else{
				categoryPrdService.commit();
				imgService.commit();
				
				request.setAttribute("targetUrl", "/shop/product_list.ms?cateId=" + cateId);
			}
		}
		catch(Exception e){
			request.setAttribute("actResult", (result * imgResult) + "");
			request.setAttribute("targetUrl", "/shop/product_editor.ms?cateId=" + cateId);
			
			imgService.rollback();
			categoryPrdService.rollback();
			
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
