package com.biz.commerce.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.commerce.vo.CommerceVO_01;
import com.biz.commerce.vo.ProductVO_01;

/*
 * 매입매출 관련 처리를 위한 클래스를 선언 
 */
public class CommerceService_01 {

	/*
	 * 각 데이터를 관리할 List변수들 선언
	 */

	List<String> sList;
	List<CommerceVO_01> ioList; // 매입매출정보 table
	List<ProductVO_01> pList; // 상품정보 table
	String ioFile; // 매입매출 파일이름
	String pFile; // 상품정보 파일이름
	String strFile;

	/*
	 * CommerceService_01 클래스로 객체를 생성할 떄 호출되는 생성자를 선언
	 * 
	 * 이 클래스는 Text파일을 읽어서 매입매출 관련 업무를 수행할 것이므로 생성자에서는 Text파일의 경로정보를 매개변수로 받아서 변수에
	 * 저장하는 코드를 수행한다.
	 */
	public CommerceService_01(String ioFile, String pFile, String strFile) {
		this.ioList = new ArrayList(); // 매입매출정보의 list를 담을 iolist를 초기화
		this.pList = new ArrayList(); // 상품정보의 list를 담을 pList를 초기화
		this.sList = new ArrayList();

		this.ioFile = ioFile; // 매개변수로 받은 ioFile 내용을 this.ioFile member변수에 저장하여 다른 method에서 사용할 수 있도록 준비
		this.pFile = pFile; // 매개변수로 받은 pFile 내용을 this.pFile member 변수에 저장하여 다른 method에서 사용할 수 있도록 준비
		this.strFile = strFile;
	}

	/*
	 * ioList와 pList에 담긴 데이터를 사용해서 매입매출리스트를 출력하는 method를 선언
	 * 
	 * 1. ioList에 담겨있는 상품코드를 사용해서 pList에 담겨있는 상품정보를 찾아 가져오기
	 */
	public void viewIoInfo() {
		for (CommerceVO_01 iv : ioList) {
			for (ProductVO_01 pv : pList) {
				if (iv.getStrCode().equals(pv.getP_code())) {
					System.out.print(iv.getStrCode());
					System.out.println(pv.getP_name());
				}
			}
		}
	}

	public void viewIoInfo2() {
		for (CommerceVO_01 iv : ioList) {
			/*
			 * searchProduct() method 에게 상품코드를 전달해 주고 pList로부터 상품을 찾으라고 명령
			 */
			ProductVO_01 v = searchProduct(iv.getStrCode());

			/*
			 * 만약 searchProduct() method가 null을 return하면 일단 그 상품은 무시하고 (상품명을 없는 것으로 처리) 그 다음
			 * 매입매출 정보를 처리
			 */
			if (v == null)
				continue;
			// v가 null 아니면 >>> 상품코드를 찾았으면

			// 여기에 오면 매입매출정보의 상품코드에 해당하는 상품정보가 담긴 v가 만들어진다.

			/*
			 * 단가와 금액 매입매출데이터의 단가는 거래구분이 1 이면 매입단가이고 2이면 매출단가
			 */

			int intIprice = 0;
			int intOprice = 0;
			String strIOTag = "";

			String strInout = iv.getStrIO();
			int intInout = Integer.valueOf(strInout);

			if (intInout == 1) {
				intIprice = iv.getIntPrice();
				intOprice = 0;
				strIOTag = "매입";
			}
			if (intInout == 2) {
				intOprice = iv.getIntPrice();
				intIprice = 0;
				strIOTag = "매출";
			}

			String s = iv.getStrDate() + ":" + strIOTag + ":" + iv.getStrCode() + ":" + v.getP_name() + ":"
					+ iv.getIntPrice() + ":" + (intIprice * Integer.valueOf(iv.getIntQuan())) + ":"
					+ (intOprice * Integer.valueOf(iv.getIntQuan()));

			sList.add(s);
		}
	}

	/*
	 * viewInfo2() method에서 sList에 저장한 문자열들을 지정해준 파일에 저장시키는 method : makeString()
	 */
	public void makeString() {

		FileWriter fw;
		PrintWriter pw;

		try {
			fw = new FileWriter(strFile);
			pw = new PrintWriter(fw);

			for (String s : sList) {
				pw.println(s);
			}
			pw.close();
			fw.close();

			System.out.println("저장 완료");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ProductVO_01 searchProduct(String pCode) {
		for (ProductVO_01 pv : pList) {
			if (pCode.equals(pv.getP_code())) {
				return pv;
			}
		}
		return null;
	}

	/*
	 * ioList에 저장되어 있는 매입매출정보 리스트를 console 에 표시해서 잘 저장되어 있는지 검사하는 method를 선언
	 */
	public void ioView() {
		for (CommerceVO_01 vo : ioList) {
			System.out.println(vo);
		}
	}

	public void pViewe() {
		for (ProductVO_01 v : pList) {
			System.out.println(v);
		}
	}

	/*
	 * 매입매출데이터.txt파일에서 문자열을 읽어서 ioList에 저장하는 method 선언
	 */
	public void ioRead() {
		/*
		 * Text파일을 읽기 위한 객체 선언
		 */
		FileReader fr;
		/*
		 * FileReader로 읽은 내용에서 문자열을 쉽게 추출할 수 있도록 연결되는 Buffer 객체 선언
		 */
		BufferedReader buffer;

		/*
		 * 생성자에서 값이 할당된 strFile의 내용을 참조하여 파일을 읽기 위하여 open하는 코드
		 * 
		 * 이 코드는 작동되는 과정에서 불가항력적인 문제가 발생할 소지가 있으므로 반드시 try..catch문으로 감싸줘야한다.
		 */
		try {
			fr = new FileReader(ioFile);
			/*
			 * FileReader로 open된 파일 정보를 Buffer에 연결하여 준다. 이 코드가 실행되면 BufferedReader는 일단 파일을
			 * 읽어서 메모리의 buffer 영역에 저장 해 둔다.
			 */
			buffer = new BufferedReader(fr);

			/*
			 * 무한 반복문을 이용해서 Buffer에 저장된 파일내용에서 한 줄씩 (문자열로) 읽어서 처리한다.
			 */
			while (true) {
				/*
				 * buffer에서 한 줄을 읽어서 reader 변수에 저장
				 */
				String reader = buffer.readLine();

				/*
				 * 만약 reader에 저장된 값이 null이면 모든 문자열을 다 읽었다는 것이므로 반복문을 종료한다.
				 */
				if (reader == null)
					break;

				/*
				 * reader 문자열을 콜론(:)으로 분리해서 CommerceVO_01에 담고 ioList에 추가하는 부분
				 *
				 * String.split() method를 사용해서 문자열을 분해하고 strSp라는 문자열 배열에 저장
				 */
				String[] strSp = reader.split(":");

				/*
				 * 새로운 vo를 생성 (선언과 초기화)
				 */
				CommerceVO_01 vo = new CommerceVO_01();

				/*
				 * vo의 각 member변수에 값을 저장(할당)
				 */
				vo.setStrDate(strSp[0]);
				vo.setStrCode(strSp[1]);
				vo.setStrIO(strSp[2]);

				/*
				 * vo.intPrice 와 vo.intQuan 는 int형 변수이므로 문자열을 int형으로 변경해준다.
				 */
				int intPrice = Integer.valueOf(strSp[3]);
				int intQuan = Integer.valueOf(strSp[4]);

				/*
				 * 변환된 intPrice 와 intQuan을 vo에 저장
				 */
				vo.setIntPrice(intPrice);
				vo.setIntQuan(intQuan);

				/*
				 * member변수가 setting 된 vo를 ioList에 추가
				 */
				ioList.add(vo);
			}

			/*
			 * 파일을 모두 사용(읽기)했으므로 안전하게 닫아준다.
			 */
			buffer.close();
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 상품정보.txt 파일에서 문자열을 읽어서 pList에 저장하는 method 선언
	 */
	public void pRead() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(pFile);
			buffer = new BufferedReader(fr);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				String[] strSp = reader.split(":");

				ProductVO_01 vo = new ProductVO_01();

				vo.setP_code(strSp[0]);
				vo.setP_name(strSp[1]);
				vo.setP_vat(strSp[2]);

				int iprice = Integer.valueOf(strSp[3]);
				int oprice = Integer.valueOf(strSp[4]);

				vo.setP_iprice(iprice);
				vo.setP_oprice(oprice);

				pList.add(vo);
			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
