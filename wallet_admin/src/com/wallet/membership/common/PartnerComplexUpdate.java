package com.wallet.membership.common;

import java.util.List;

import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwMsCompPayMent;
import com.wallet.membership.model.MwMsCompPayMentExample;
import com.wallet.membership.model.MwMsStarPayMent;
import com.wallet.membership.model.MwMsStarPayMentExample;
import com.wallet.membership.service.MwCmCompanyService;
import com.wallet.membership.service.MwMsCompPayMentService;
import com.wallet.membership.service.custom.MwMsStarPayMentService;

public class PartnerComplexUpdate {
	
	/**
	 * 복합결제 관련 내용 업데이트
	 * */
	public PartnerComplexUpdate(String CompId, String ComplexYn) {
		// TODO Auto-generated constructor stub
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwMsCompPayMentService mwMsCompPayMentService = new MwMsCompPayMentService();
		MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
		MwCmCompanyExample UpdateExample = new MwCmCompanyExample();
		List<MwCmCompany> compList = null;
		List<MwCmCompany> brdList = null;
		List<MwMsStarPayMent> ollehStar = null;
		UpdateStore updateStore = new UpdateStore();
		
		try{
		//제휴사가 CompId 인 브랜드를 모두 가져온다.
			MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
			MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
			mwCmCompanyExample.or().andUpperCompIdEqualTo(CompId);
			brdList  = mwCmCompanyService.getByExample(mwCmCompanyExample);
			ollehStar = mwMsStarPayMentService.getByExample(mwMsStarPayMentExample);
			if(brdList.size()==0){
				return;
			}else{
			//브랜드 아이디로 가맹점을 모두 가져온다.
				
				mwCmCompanyExample = new MwCmCompanyExample();
				for(int i=0;i<brdList.size();i++){
					mwCmCompanyExample.or().andUpperCompIdEqualTo(brdList.get(i).getCompId());
				}
				compList = mwCmCompanyService.getByExample(mwCmCompanyExample);
				
				
				for(int i=0;i<brdList.size();i++){
					UpdateExample.or().andCompIdEqualTo(brdList.get(i).getCompId());//브랜드 아이디
					
				}
			}
			
			//브랜드및 가맹점 전체를 업데이트 할 조건을 설정한다.
			
			if(compList != null && compList.size()!=0){
				for(int i = 0;i<compList.size();i++){
					UpdateExample.or().andCompIdEqualTo(compList.get(i).getCompId());//가맹점 아이디
					if(ComplexYn.equals("Y")){
						if(compList.get(i).getComplexPaymentYn().equals("N")){
							updateStore.update(compList.get(i).getCompId(), compList.get(i).getUpperCompId(),
									ollehStar.get(i).getStarPointUse(),ollehStar.get(i).getStarPointDc(),ollehStar.get(i).
									getStarPointSave(), "I");
						}
					}else{
						if(compList.get(i).getComplexPaymentYn().equals("Y")){
							updateStore.update(compList.get(i).getCompId(), compList.get(i).getUpperCompId(),
									ollehStar.get(i).getStarPointUse(),ollehStar.get(i).getStarPointDc(),ollehStar.get(i).
									getStarPointSave(),"D");
						}
					}
					
				}
			}
			
			System.out.println("ComplexYn : " + ComplexYn);
			MwCmCompany mwCmCompany = new MwCmCompany();
			mwCmCompany.setComplexPaymentYn(ComplexYn);
			
			mwCmCompanyService.update(mwCmCompany, UpdateExample);
			mwCmCompanyService.commit();
			
			MwMsCompPayMentExample mwMsCompPayMentExample = new MwMsCompPayMentExample();
			//복합결제를 사용하지 않는 경우 하위 브랜드 가맹점의 복합결제 정보를 삭제한다.
			if(ComplexYn.equals("N")){
				for(int i=0;i<brdList.size();i++){
					mwMsCompPayMentExample.or().andCompIdEqualTo(brdList.get(i).getCompId());//가맹점 아이디
					mwMsStarPayMentExample.or().andCompIdEqualTo(brdList.get(i).getCompId());
				}
				
				for(int i = 0;i<compList.size();i++){
					mwMsCompPayMentExample.or().andCompIdEqualTo(compList.get(i).getCompId());//가맹점 아이디
					mwMsStarPayMentExample.or().andCompIdEqualTo(compList.get(i).getCompId());
				}
			
				mwMsCompPayMentService.delete(mwMsCompPayMentExample);
				mwMsCompPayMentService.commit();
				
				mwMsStarPayMentService.delete(mwMsStarPayMentExample);
				mwMsStarPayMentService.commit();
			}
		}catch (Exception e) {
			// TODO: handle exception
			mwCmCompanyService.rollback();
			mwMsCompPayMentService.rollback();
			mwMsStarPayMentService.rollback();
			e.printStackTrace();
			
		}
	}
	
	/**
	 * 복합결제 사용여부 업데이트 
	 * */
	public PartnerComplexUpdate(MwMsCompPayMent mwCmCompPayMent, String CompId) {
		//TODO Auto-generated constructor stub
		MwCmCompanyService mwCmCompanyService = new MwCmCompanyService();
		MwMsCompPayMentService mwMsCompPayMentService = new MwMsCompPayMentService();
		MwMsStarPayMentService mwMsStarPayMentService = new MwMsStarPayMentService();
		UpdateStore updateStore = new UpdateStore();
		List<MwCmCompany> brdList = null;
		List<MwCmCompany> compList = null;
		List<MwMsStarPayMent> ollehStar = null;
		
		try{
			
		//제휴사가 CompId 인 브랜드를 모두 가져온다.
			MwCmCompanyExample mwCmCompanyExample = new MwCmCompanyExample();
			MwMsStarPayMentExample mwMsStarPayMentExample = new MwMsStarPayMentExample();
			
			mwCmCompanyExample.or().andUpperCompIdEqualTo(CompId);
			brdList  = mwCmCompanyService.getByExample(mwCmCompanyExample);
			ollehStar = mwMsStarPayMentService.getByExample(mwMsStarPayMentExample);
			
			//브랜드 아이디로 가맹점을 모두 가져온다.
			mwCmCompanyExample = new MwCmCompanyExample();
			for(int i=0;i<brdList.size();i++){
				mwCmCompanyExample.or().andUpperCompIdEqualTo(brdList.get(i).getCompId());
			}
			compList = mwCmCompanyService.getByExample(mwCmCompanyExample);
			
			
			MwMsCompPayMentExample mwMsCompPayMentExample = new MwMsCompPayMentExample();
			if(brdList.size()==0){
				return;
			}else{
				for(int i=0;i<brdList.size();i++){
					mwMsCompPayMentExample.or().andCompIdEqualTo(brdList.get(i).getCompId());//브랜드 아이디
					mwMsStarPayMentExample.or().andCompIdEqualTo(brdList.get(i).getCompId());
				}
			}
			
			if(compList != null && compList.size()!=0){
				for(int i = 0;i<compList.size();i++){
					updateStore.update(compList.get(i).getCompId(), compList.get(i).getUpperCompId(), 
							ollehStar.get(i).getStarPointUse(),ollehStar.get(i).getStarPointDc(),
							ollehStar.get(i).getStarPointSave() ,"U");
					mwMsCompPayMentExample.or().andCompIdEqualTo(compList.get(i).getCompId());//가맹점 아이디
					mwMsStarPayMentExample.or().andCompIdEqualTo(compList.get(i).getCompId());
				}
			}
			
			mwMsCompPayMentService.delete(mwMsCompPayMentExample);
			mwMsCompPayMentService.commit();
			
			mwMsStarPayMentService.delete(mwMsStarPayMentExample);
			mwMsCompPayMentService.commit();
		}catch (Exception e) {
			// TODO: handle exception
			mwMsCompPayMentService.rollback();
			mwMsStarPayMentService.rollback();
		}
		
		
	}
}










