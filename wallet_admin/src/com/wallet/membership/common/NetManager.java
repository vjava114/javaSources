package com.wallet.membership.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.print.attribute.standard.Finishings;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NetManager {
	private HttpURLConnection  con;
	private String result = "";
	private Element mElement = null;
	private JSONObject mJSONObject = null;
	private String Url="";
	private String Parameter = null;
	private String ChipSet = "UTF-8";
	
	public NetManager(String Url) {
		// TODO Auto-generated constructor stub
		this.Url = Url;
	}
	
	public NetManager(String Url,String Parameter) {
		// TODO Auto-generated constructor stub
		this.Url = Url;
		this.Parameter = Parameter;
	}
	
	public NetManager(String Url,String Parameter, String ChipSet) {
		// TODO Auto-generated constructor stub
		this.Url = Url;
		this.Parameter = Parameter;
		this.ChipSet = ChipSet;
	}	
	
//	String parameter = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode("dimdol", "UTF-8");
//	parameter += "&" + URLEncoder.encode("age", "UTF-8") + "=" +URLEncoder.encode("35", "UTF-8");
	
	public void getPOSTConnention(){
		try{
			URL url = new URL(Url);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestMethod("POST");			
			con.setDoOutput(true);
			
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			if(Parameter != null){
				wr.write(Parameter);				
			}
			wr.flush();  
			
			BufferedReader rd = null;

			rd = new BufferedReader(new InputStreamReader(con.getInputStream(), ChipSet));
			StringBuilder sb = new StringBuilder();  
			String line = null;
			while ((line = rd.readLine()) != null) {
				sb.append(line + "\n");  
			}
			result = sb.toString();
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			con.disconnect();
		}
	}
	
	public void getGETConnention(){
		try{
			URL url = new URL(Url);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestMethod("GET");			
			
			BufferedReader rd = null;

			rd = new BufferedReader(new InputStreamReader(con.getInputStream(), ChipSet));
			StringBuilder sb = new StringBuilder();  
			String line = null;
			while ((line = rd.readLine()) != null) {
				sb.append(line + "\n");  
			}
			result = sb.toString();
						
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			con.disconnect();
		}
	}
	
	public String getString(){
		return result;
	}
		
	//=========================================================
		//	TODO XmlData
		//=========================================================
		/**
		 *  xml(Element)
		 * mGetUrlData.getData();
		 * mGetUrlData.getXml();
		 * @return Element
		 * */	
		public Element getXml(){
			try{  
		    	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    	 DocumentBuilder builder = factory.newDocumentBuilder();
		    	 InputStream inputStrim = new ByteArrayInputStream(result.getBytes(ChipSet));
		    	 Document doc = builder.parse(inputStrim);
		    	 mElement = doc.getDocumentElement();
		    	 inputStrim.close();
		     }catch(Exception e){
		     } 	     
			return mElement;
		}
		
		/**	
		 * mGetUrlData.getData();
		 * mGetUrlData.getXml();
		 * String result = mGetUrlData.getXmlString(TagName,index);
		 * @return String
		 * */	
		public String getXmlString(String TagName, int index){
			String text = "";
			try{
				NodeList  IDList= mElement.getElementsByTagName(TagName);
				Node items = IDList.item(index);
				Node itemtext = items.getFirstChild();
				text = itemtext.getNodeValue();				
			}catch (Exception e) {
				// TODO: handle exception
				text = "";
			}
			if(text.equals("null"))text = "";
			return text;
		}
		
		/**
		 * mGetUrlData.getData();
		 * mGetUrlData.getXml();
		 * int result = mGetUrlData.getXmlInt(TagName,index);
		 * @return Integer
		 * */	
		public int getXmlInt(String TagName, int index){
			String text = "";
			int result = 0;
			try{
				NodeList  IDList= mElement.getElementsByTagName(TagName);
				Node items = IDList.item(index);
				Node itemtext = items.getFirstChild();
				text = itemtext.getNodeValue();				
			}catch (Exception e) {
				// TODO: handle exception
				text = "";
			}
			if(text.equals("null"))result = 0;
			else result = Integer.parseInt(text);
			return result;
		}
		
		/**		
		 * mGetUrlData.getData();
		 * mGetUrlData.getXml();
		 * double result = mGetUrlData.getXmlDouble(TagName,index);
		 * @return Double
		 * */	
		public double getXmlDouble(String TagName, int index){
			String text = "";
			double result = 0.0;
			try{
				NodeList  IDList= mElement.getElementsByTagName(TagName);
				Node items = IDList.item(index);
				Node itemtext = items.getFirstChild();
				text = itemtext.getNodeValue();				
			}catch (Exception e) {
				// TODO: handle exception
				text = "";
			}
			if(text.equals("null"))result = 0.0;
			else result = Double.parseDouble(text);
			return result;
		}
		
		public int getXmlCnt(String TagName){
			if(mElement != null){
				NodeList  IDList= mElement.getElementsByTagName(TagName);
				return IDList.getLength();
			}else{
				return 0;
			}
		}
		
		//=========================================================
		//	TODO JSONData
		//=========================================================
		/**		 
		 * mGetUrlData.getData();
		 * JSONObject mJsonObject = mGetUrlData.getJSON();
		 * @return JSONObject
		 * */	
		public JSONObject getJSON(){
			try{
				mJSONObject = new JSONObject(result);
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return mJSONObject;		 
		}
		
		/**		
		 * mGetUrlData.getData();
		 * JSONObject mJsonObject = mGetUrlData.getJSON();
		 * String Result = mGetUrlData.getJsonString(mJsonObject,Param);
		 * @param jObject - JSONObject
		 * @param param - JSONObject
		 * @return String
		 * */	
		public String getJsonString(JSONObject jObject,String param){
			String Text = "";
			try{
				Text = jObject.getString(param);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return Text;
		}
		
		/**		
		 * mGetUrlData.getData();
		 * JSONObject mJsonObject = mGetUrlData.getJSON();
		 * double Result = mGetUrlData.getJsonDouble(mJsonObject,Param);
		 * @param jObject - JSONObject
		 * @param param - JSONObject 
		 * @return Double
		 * */	
		public Double getJsonDouble(JSONObject jObject,String param){
			Double Text = null;
			try{
				Text = jObject.getDouble(param);				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return Text;
		}
		
		/**
		 * @author lkhuns		 
		 * mGetUrlData.getData();
		 * JSONObject mJsonObject = mGetUrlData.getJSON();
		 * int Result = mGetUrlData.getJsonInt(mJsonObject,Param);
		 * @param jObject - JSONObject
		 * @param param - JSONObject
		 * @return Integer
		 * */	
		public int getJsonInt(JSONObject jObject,String param){
			int Text = 0;
			try{
				Text = jObject.getInt(param);				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return Text;
		}
	
}
