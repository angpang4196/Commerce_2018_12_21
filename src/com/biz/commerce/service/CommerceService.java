package com.biz.commerce.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.commerce.vo.CommerceVO;

public class CommerceService {

	List<CommerceVO> ioList;
	List<CommerceVO> goodList;
	String strIOfile;
	String strGoodFile;

	public CommerceService(String strIOfile, String strGoodFile) {
		ioList = new ArrayList();
		goodList = new ArrayList();
		this.strIOfile = strIOfile;
		this.strGoodFile = strGoodFile;
	}

	// 매입매출데이터.txt 파일을 읽어서 vo에 저장 및 ioList에 vo 저장
	public void readIO() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(strIOfile);
			buffer = new BufferedReader(fr);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				String[] strSp = reader.split(":");

				CommerceVO vo = new CommerceVO();
				vo.setStrDate(strSp[0]);
				vo.setStrCode(strSp[1]);

				int intIO = Integer.valueOf(strSp[2]);
				if (intIO == 1) {
					vo.setStrIO("매입");
					vo.setIntIprice(Integer.valueOf(strSp[3]));

					vo.setIntInTotal((vo.getIntIprice()) * Integer.valueOf(strSp[4]));
				}
				if (intIO == 2) {
					vo.setStrIO("매출");
					vo.setIntOprice(Integer.valueOf(strSp[3]));

					vo.setIntOutTotal((vo.getIntOprice()) * Integer.valueOf(strSp[4]));
				}
				vo.setIntQuan(Integer.valueOf(strSp[4]));

				ioList.add(vo);
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

	// 상품정보.txt 파일을 읽어서 vo에 저장 및 goodList에 vo 저장
	public void readGood() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(strGoodFile);
			buffer = new BufferedReader(fr);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				String[] strSp = reader.split(":");
				CommerceVO vo = new CommerceVO();

				vo.setStrCode(strSp[0]);
				vo.setStrGname(strSp[1]);

				vo.setIntIprice(Integer.valueOf(strSp[3]));
				vo.setIntOprice(Integer.valueOf(strSp[4]));

				goodList.add(vo);

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

	// 두 개의 리스트에 있는 상품코드를 매치시켜서 상품에 대한 정보를 파일에 저장
	public void saveFile(String strInfoFile) {
		FileWriter fw;
		PrintWriter pw;

		try {
			fw = new FileWriter(strInfoFile);
			pw = new PrintWriter(fw);

			int intSize1 = goodList.size();
			int intSize2 = ioList.size();

			for (int i = 0; i < intSize1; i++) {

				for (int j = 0; j < intSize2; j++) {
					if (ioList.get(j).getStrCode().equals(goodList.get(i).getStrCode())) {
						pw.print(ioList.get(j).getStrDate() + ":" + ioList.get(j).getStrIO() + ":"
								+ ioList.get(j).getStrCode() + ":" + goodList.get(i).getStrGname());

						if (ioList.get(j).getStrIO().equals("매입")) {
							pw.println(":" + goodList.get(i).getIntIprice() + ":" + ioList.get(j).getIntQuan() + ":"
									+ ioList.get(j).getIntInTotal() + ":" + "0");

						}
						if (ioList.get(j).getStrIO().equals("매출")) {
							pw.println(":" + goodList.get(i).getIntOprice() + ":" + ioList.get(j).getIntQuan() + ":"
									+ "0" + ":" + ioList.get(j).getIntOutTotal());

						}
					}
				}

			}
			pw.close();
			fw.close();

			System.out.println("정보 저장 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
