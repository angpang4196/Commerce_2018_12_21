package com.biz.commerce.vo;

public class CommerceVO {

	private String strDate; // 거래일자
	private String strCode; // 상품코드
	private String strIO; // 거래구분
	private String strGname; // 상품명

	private int intIprice; // 매입단가
	private int intOprice; // 매출단가
	private int intQuan; // 수량
	private int intInTotal; // 매입금액
	private int intOutTotal; // 매출금액

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getStrCode() {
		return strCode;
	}

	public void setStrCode(String strCode) {
		this.strCode = strCode;
	}

	public String getStrIO() {
		return strIO;
	}

	public void setStrIO(String strIO) {
		this.strIO = strIO;
	}

	public int getIntIprice() {
		return intIprice;
	}

	public void setIntIprice(int intIprice) {
		this.intIprice = intIprice;
	}

	public int getIntOprice() {
		return intOprice;
	}

	public void setIntOprice(int intOprice) {
		this.intOprice = intOprice;
	}

	public int getIntQuan() {
		return intQuan;
	}

	public void setIntQuan(int intQuan) {
		this.intQuan = intQuan;
	}

	public int getIntInTotal() {
		return intInTotal;
	}

	public void setIntInTotal(int intInTotal) {
		this.intInTotal = intInTotal;
	}

	public int getIntOutTotal() {
		return intOutTotal;
	}

	public void setIntOutTotal(int intOutTotal) {
		this.intOutTotal = intOutTotal;
	}

	public String getStrGname() {
		return strGname;
	}

	public void setStrGname(String strGname) {
		this.strGname = strGname;
	}

}
