package com.amore.common.util;

import org.apache.log4j.Logger;

public class Paging {
	private Logger logger = Logger.getLogger(this.getClass());
	private boolean isPrevPage;
	private boolean isNextPage;
	protected int nowPage;
	protected int rowTotal;
	protected int blockList = 10;
	protected int blockPage = 10;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int startRow;
	private int endRow;
	
	private StringBuilder sb;

	public int getBlockList() {
		return blockList;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getNowPage() {
		return nowPage;
	}

	public int getRowTotal() {
		return rowTotal;
	}

	public StringBuilder getSb() {
		return sb;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getTotalPage() {
		return totalPage;
	}
 
	public Paging() {}
	
 
	public String makeWebPaging(int nowPage, int rowTotal) {
		return makeWebPaging(nowPage, rowTotal, this.blockList, this.blockPage);
	}
	
 
	public String makeWebPaging(int nowPage, int rowTotal, int blockList) {
		return makeWebPaging(nowPage, rowTotal, blockList, this.blockPage);
	}
 
	public String makeWebPaging(int nowPage, int rowTotal, int blockList, int blockPage) {

 
		isPrevPage = false;
		isNextPage = false;
 
		this.totalPage	= (int) Math.ceil((double)rowTotal / (double)blockList);
 
		if(nowPage > this.totalPage){
				nowPage = this.totalPage;
		}
 
		this.startRow	= (int) (nowPage - 1) * blockList + 1;
		this.startRow = (this.startRow < 0) ? 1 : this.startRow;
		this.endRow		= (int) this.startRow + blockList - 1;
 
		this.startPage	= (int) ((nowPage - 1) / blockPage) * blockPage + 1;
		this.endPage	= (int) this.startPage + blockPage - 1;
 
		if(this.endPage > this.totalPage){
				this.endPage = totalPage;
		}
 
		if(this.startPage > 1){
				this.isPrevPage = true;
		}
 
		if(this.endPage < this.totalPage){
				this.isNextPage = true;
		}

	 
		this.nowPage = nowPage;
		this.rowTotal = rowTotal;
		this.blockList = blockList;
		this.blockPage = blockPage;
		/*
		
		logger.debug("### Paging ###");

		logger.debug("### blockPage : " + blockPage);
		logger.debug("### startPage : " + startPage);
		logger.debug("### endPage : " + endPage);
		logger.debug("### totalPage : " + totalPage);
		logger.debug("### nowPage : " + nowPage);
		logger.debug("### isPrevPage : " + isPrevPage);
		logger.debug("### isNextPage : " + isNextPage);
		*/
		sb = new StringBuilder();
		
		if(startPage < blockPage) {
			
			if(nowPage > 1) {

				sb.append("<a href=\"javascript:goPage(");
				sb.append(1);
				sb.append(")\">");
				sb.append("<span class='btn_head_page'><<</span>");
				sb.append("</a>");
				
				sb.append("<a href=\"javascript:goPage(");
				sb.append(1);
				sb.append(")\">");
				sb.append("<span class='btn_prev_page'><</span>");
				sb.append("</a>");
			} else {
				sb.append("<span class='btn_head_page'><<</span>");
				sb.append("<span class='btn_prev_page'><</span>");
			}
			
		} else {
			/* ó�� */
			sb.append("<a href=\"javascript:goPage(");
			sb.append(1);
			sb.append(")\">");
			sb.append("<span class='btn_head_page'><<</span>");
			sb.append("</a>");
			
			sb.append("<a href=\"javascript:goPage(");
			sb.append(startPage -1);
			sb.append(")\">");
			sb.append("<span class='btn_prev_page'><</span>");
			sb.append("</a>");
		}
		for(int i = startPage ; i <= endPage ; i++){
			if(i > totalPage)
				break;
			if(i == nowPage){
				sb.append("<span class='num'>");
				sb.append("<strong>");
				sb.append(i);
				sb.append("</strong>");
				sb.append("</span>");
			}else{
				sb.append("<a href=\"javascript:goPage(");
				sb.append(i);
				sb.append(")\">");
				sb.append("<span class='num'>");
				sb.append(i);
				sb.append("</span>");
				sb.append("</a>");
			}
		}//end for
		if(endPage >= totalPage) {
			if(nowPage < totalPage) {
				sb.append("<a href=\"javascript:goPage(");
				sb.append(this.totalPage);
				sb.append(")\">");
				sb.append("<span class='btn_next_page'>></span></a>");
				
				sb.append("<a href=\"javascript:goPage(");
				sb.append(this.totalPage);
				sb.append(")\">");
				sb.append("<span class='btn_next_page'>>></span></a>");
				
			} else {
				sb.append("<span class='btn_next_page'>></span>");
				sb.append("<span class='btn_next_page'>>></span>");
			}
			
		} else {
		 
			sb.append("<a href=\"javascript:goPage(");
			sb.append(endPage + 1);
			sb.append(")\">");
			sb.append("<span class='btn_next_page'>></span></a>");
			
			sb.append("<a href=\"javascript:goPage(");
			sb.append(this.totalPage);
			sb.append(")\">");
			sb.append("<span class='btn_next_page'>>></span></a>");
		}
		
		return sb.toString();
	}

}
