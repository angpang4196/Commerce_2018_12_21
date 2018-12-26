package com.biz.commerce.vo;

/*
 * 매입매출데이터.txt파일롭터
 * 데이터를 읽어서 List를 만들 때
 * 사용할 데이터클래스(VO) 선언
 */
public class CommerceVO_01 {

	/*
	 * 거래일자:상품코드:거래구분:단가:수량 형식의 데이터를 읽어서 각 member변수에 저장
	 * 
	 * 각 member변수는 정보은닉과 캡슐화를 하기 위해 private으로 선언
	 */
	private String strDate; // 거래일자
	private String strCode; // 상품코드
	private String strIO; // 거래구분

	private int intPrice; // 단가
	private int intQuan; // 수량

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

	public int getIntPrice() {
		return intPrice;
	}

	public void setIntPrice(int intPrice) {
		this.intPrice = intPrice;
	}

	public int getIntQuan() {
		return intQuan;
	}

	public void setIntQuan(int intQuan) {
		this.intQuan = intQuan;
	}

	/*
	 * VO에 데이터를 저장한 후 데이터를 확인하는 디버깅용 메서드를 재 정의한다.
	 */
	@Override
	public String toString() {
		return "CommerceVO_01 [strDate=" + strDate + ", strCode=" + strCode + ", strIO=" + strIO + ", intPrice="
				+ intPrice + ", intQuan=" + intQuan + "]";
	}

}
