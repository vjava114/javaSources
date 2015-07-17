package com.wallet.common.util;

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

	/**
	 * <p>
	 * 생성자 
	 * </p>
	 */
	public Paging() {}
	
	
	/**
	 * <p>
	 * WEB 페이징을 만든다.
	 * </p>
	 *
	 * @param nowPage 선택된페이지
	 * @param rowTotal 전체로우
	 * 
	 * @return WEB 페이징 문자열
	 */
	public String makeWebPaging(int nowPage, int rowTotal) {
		return makeWebPaging(nowPage, rowTotal, this.blockList, this.blockPage);
	}
	
	/**
	 * <p>
	 * WEB 페이징을 만든다.(MSS 도입 사례는 1페이지에 5개의 리스트)
	 * </p>
	 *
	 * @param nowPage 선택된페이지
	 * @param rowTotal 전체로우
	 * @param blockList 한 페이지에 보여줄 리스트 개수
	 * 
	 * @return WEB 페이징 문자열
	 */
	public String makeWebPaging(int nowPage, int rowTotal, int blockList) {
		return makeWebPaging(nowPage, rowTotal, blockList, this.blockPage);
	}
	/**
	 * <p>
	 * WEB 페이징을 만든다.
	 * </p>
	 *
	 * @param nowPage 선택된페이지
	 * @param rowTotal 전체로우
	 * @param blockLsit 블럭리스트
	 * @param blockPage 블럭페이지
	 * 
	 * @return WEB 페이징 문자열
	 */
	public String makeWebPaging(int nowPage, int rowTotal, int blockList, int blockPage) {

		// 각종 플래그를 초기화
		isPrevPage = false;
		isNextPage = false;

		// 입력된 전체 열의 수를 통해 전체 페이지 수를 계산한다
		this.totalPage	= (int) Math.ceil((double)rowTotal / (double)blockList);
		
		// 현재 페이지가 전체 페이지수보다 클경우 전체 페이지수로 강제로 조정한다
		if(nowPage > this.totalPage){
				nowPage = this.totalPage;
		}

		// DB입력을 위한 시작과 종료값을 구한다
		this.startRow	= (int) (nowPage - 1) * blockList + 1;
		this.startRow = (this.startRow < 0) ? 1 : this.startRow;
		this.endRow		= (int) this.startRow + blockList - 1;

		// 시작페이지와 종료페이지의 값을 구한다
		this.startPage	= (int) ((nowPage - 1) / blockPage) * blockPage + 1;
		this.endPage	= (int) this.startPage + blockPage - 1;

		// 마지막 페이지값이 전체 페이지값보다 클 경우 강제 조정
		if(this.endPage > this.totalPage){
				this.endPage = totalPage;
		}

		// 시작 페이지가 1보다 클 경우 이전 페이징이 가능한것으로 간주한다
		if(this.startPage > 1){
				this.isPrevPage = true;
		}

		// 종료페이지가 전체페이지보다 작을경우 다음 페이징이 가능한것으로 간주한다
		if(this.endPage < this.totalPage){
				this.isNextPage = true;
		}

		// 기타 값을 저장한다
		this.nowPage = nowPage;
		this.rowTotal = rowTotal;
		this.blockList = blockList;
		this.blockPage = blockPage;
		
		
		logger.debug("### Paging ###");

		logger.debug("### blockPage : " + blockPage);
		logger.debug("### startPage : " + startPage);
		logger.debug("### endPage : " + endPage);
		logger.debug("### totalPage : " + totalPage);
		logger.debug("### nowPage : " + nowPage);
		logger.debug("### isPrevPage : " + isPrevPage);
		logger.debug("### isNextPage : " + isNextPage);
		
		sb = new StringBuilder();
		
		if(startPage < blockPage) {
			
			if(nowPage > 1) {

				sb.append("<a href=\"javascript:goPage(");
				sb.append(1);
				sb.append(")\">");
				sb.append("<span class='btn_head_page'><img src='/img/btn_head_page.png' alt=''/></span>");
				sb.append("</a>");
				
				sb.append("<a href=\"javascript:goPage(");
				sb.append(1);
				sb.append(")\">");
				sb.append("<span class='btn_prev_page'><img src='/img/btn_prev_page.png' alt=''/></span>");
				sb.append("</a>");
			} else {
				sb.append("<span class='btn_head_page'><img src='/img/btn_head_page.png' alt=''/></span>");
				sb.append("<span class='btn_prev_page'><img src='/img/btn_prev_page.png' alt=''/></span>");
			}
			
		} else {
			/* 처음 */
			sb.append("<a href=\"javascript:goPage(");
			sb.append(1);
			sb.append(")\">");
			sb.append("<span class='btn_head_page'><img src='/img/btn_head_page.png' alt=''/></span>");
			sb.append("</a>");
			
			sb.append("<a href=\"javascript:goPage(");
			sb.append(startPage -1);
			sb.append(")\">");
			sb.append("<span class='btn_prev_page'><img src='/img/btn_prev_page.png' alt=''/></span>");
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
				sb.append("<span class='btn_next_page'><img src='/img/btn_next_page.png' alt=''/></span></a>");
				
				sb.append("<a href=\"javascript:goPage(");
				sb.append(this.totalPage);
				sb.append(")\">");
				sb.append("<span class='btn_next_page'><img src='/img/btn_end_page.png' alt=''/></span></a>");
				
			} else {
				sb.append("<span class='btn_next_page'><img src='/img/btn_next_page.png' alt=''/></span>");
				sb.append("<span class='btn_next_page'><img src='/img/btn_end_page.png' alt=''/></span>");
			}
			
		} else {
			/* 다음 */
			sb.append("<a href=\"javascript:goPage(");
			sb.append(endPage + 1);
			sb.append(")\">");
			sb.append("<span class='btn_next_page'><img src='/img/btn_next_page.png' alt=''/></span></a>");
			
			sb.append("<a href=\"javascript:goPage(");
			sb.append(this.totalPage);
			sb.append(")\">");
			sb.append("<span class='btn_next_page'><img src='/img/btn_end_page.png' alt=''/></span></a>");
		}
		
		return sb.toString();
	}
}
