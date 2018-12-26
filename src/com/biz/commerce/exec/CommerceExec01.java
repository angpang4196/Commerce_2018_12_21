package com.biz.commerce.exec;

import com.biz.commerce.service.CommerceService_01;

/*
 * 지금부터 CommerceExec01 이라는 클래스를 시작한다.
 */
public class CommerceExec01 {

	/*
	 * 만약 JVM에서 이 프로젝트를 실행할 때 CommerceExec01 클래스에서 진입점 method를 호출
	 */
	public static void main(String[] args) {

		/*
		 * 매입매출 정보를 저장하고 있는 파일 이름(경로)를 문자열 변수에 저장
		 */
		String ioFile = "src/com/biz/commerce/vo/매입매출데이터.txt";

		/*
		 * 상품정보를 저장하고 있는 파일이름(경로)를 문자열 변수에 저장
		 */
		String pFile = "src/com/biz/commerce/vo/상품정보.txt";
		
		String strFile = "src/com/biz/commerce/exec/매입매출정보.txt";

		/*
		 * CommerceService_01 클래스를 객체로 선언하여 매입매출 업무를 수행할 준비
		 * 
		 * ioFile, pFile을 매개변수로 전달 == 매입매출정보를 저장하고 있는 파일이름과 상품정보를 저장하고 있는 파일이름을 매개변수로
		 * 전달하는 것과 같다.
		 */

		CommerceService_01 cs = new CommerceService_01(ioFile, pFile, strFile);

		/*
		 * 매입매출정보.txt 파일에서 데이터를 읽어서 ioList에 저장하는 method 호출(실행)
		 */
		cs.ioRead();

		/*
		 * 위에서 cs.ioRead() method를 실행했으므로 cs의 ioList에 매입매출정보가 저장되어 있을 것이다. 그 정보를 확인 해보자.
		 * 
		 * cs.ioView() method를 호출(실행)해서 정보 확인
		 */
//		cs.ioView();

		/*
		 * 상품정보.txt 파일에서 데이터를 읽어서 pList에 저장하는 method 호출(실행)
		 */
		cs.pRead();

		/*
		 * cs.pView() method를 호출해서 pList에 저장되어 있는 정보를 확인
		 */
//		cs.pViewe();

		cs.viewIoInfo2();
		
		cs.makeString();
		

	}

}
