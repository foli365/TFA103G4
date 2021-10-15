package com.Product.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;



public class TestJDBCProductDAO {

	
	public static void main(String[] args) throws IOException {
		
		Product_interface dao = new ProductJDBCDAO();
		String s1[] = {"316露營杯","316露營杯","316露營杯"};
		
		// 新增
		String path = "C:\\Users\\Tibame_T14\\OneDrive\\桌面\\campbak";
		File f = new File(path);
		String s[] = f.list();
		int k = 0;
		for (int j = 0; j < s.length; j+=3) {
			ProductVO product1 = new ProductVO();
			product1.setPname(s1[k]);
			product1.setPsort("露營杯");
			product1.setPrice(new Integer(2000));
			product1.setInventory(3);
			product1.setAdmin_id(new Integer(1001));
			product1.setSituation(new Integer(1));
			product1.setDescript("復古煤油燈造型，質感日雜風格\r\n" + 
					"馬口鐵材質，堅固又耐用\r\n" + 
					"上方手提式設計，可吊掛於帳篷、家中\r\n" + 
					"附亮度調節鈕，可多段式調節亮度");
			System.out.println(k);
			for (int i = 0; i < s.length; i++) {
				product1.setPicture1(getPictureByteArray(path + "\\" + s[j]));
				product1.setPicture2(getPictureByteArray(path + "\\" + s[j+1]));
				product1.setPicture3(getPictureByteArray(path + "\\" + s[j+2]));
				
			}
			
			dao.insert(product1);
			++k;
		}
			
		
		
		// 修改
//		ProductVO product2 = new ProductVO();
//		byte[] pic1 = getPictureByteArray("C:\\Users\\Tibame_T14\\OneDrive\\桌面\\imgs\\1881696-1_xxl.jpg");
//		byte[] pic2 = getPictureByteArray("C:\\Users\\Tibame_T14\\OneDrive\\桌面\\imgs\\1881696-1_xxl.jpg");
//		byte[] pic3 = getPictureByteArray("C:\\Users\\Tibame_T14\\OneDrive\\桌面\\imgs\\1881696-1_xxl.jpg");
//		
//		product2.setPname("五人帳篷");		
//		product2.setPsort("帳篷");
//		product2.setPrice(new Integer(7000));
//		product2.setInventory(2);
//		product2.setSituation(new Integer(0)); // 1是有上架, 0是沒有
//		product2.setDescript("防水牛津布 防潮隔寒 加大空間 好寬敞 2窗2門 好透氣" );
//		product2.setPicture1(pic1);
//		product2.setPicture2(pic2);
//		product2.setPicture3(pic3);
//		product2.setProductno(7003);
//		dao.update(product2);
		
		// 刪除
//		dao.delete(6);
		
		// 查詢		
//		ProductVO product3 = dao.findByPrimaryKey(2);
//		System.out.println(product3.toString());
//		System.out.print(product3.getProductno() + ",");
//		System.out.print(product3.getPname() + ",");
//		System.out.print(product3.getPsort() + ",");
//		System.out.print(product3.getPrice() + ",");
//		System.out.print(product3.getInventory() + ",");
//		System.out.print(product3.getDescript() + ",");
//		System.out.println("---------------------");
		
//		
//		List<ProductVO> list = dao.getAll();
//		for(ProductVO pvo : list) {
//			System.out.println(pvo.toString());
//		}
							
	
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
