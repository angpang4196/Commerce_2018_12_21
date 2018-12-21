package com.biz.commerce.exec;

import com.biz.commerce.service.CommerceService;

public class CommerceExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strIOfile = "src/com/biz/commerce/vo/매입매출데이터.txt";
		String strGoodFile = "src/com/biz/commerce/vo/상품정보.txt";
		String strInfoFile = "src/com/biz/commerce/exec/매입매출정보.txt";

		CommerceService cs = new CommerceService(strIOfile, strGoodFile);

		cs.readIO();
		cs.readGood();

		cs.saveFile(strInfoFile);

	}

}
