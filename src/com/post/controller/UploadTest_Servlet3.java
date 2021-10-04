package com.post.controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet("/uploadServlet3.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
public class UploadTest_Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded"; // �W���ɮת��ت��a�ؿ�;
											   // �N�ѩ��U����26~30��� java.io.File �� ContextPath ���U, �۰ʫإߥئa�ؿ�

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // �B�z�����ɦW
		res.setContentType("text/html; charset=UTF-8"); // ���ե�

		String realPath = getServletContext().getRealPath(saveDirectory);
		System.out.println("realPath="+realPath); // ���ե�
		File fsaveDirectory = new File(realPath);
		if (!fsaveDirectory.exists())
			 fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�

		Collection<Part> parts = req.getParts(); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z

		for (Part part : parts) {
			String filename = getFileNameFromPart(part);
			if (filename!= null && part.getContentType()!=null) {

				File f = new File(fsaveDirectory, filename);

				// �Q��File����,�g�J�ئa�ؿ�,�W�Ǧ��\
				part.write(f.toString());

				// �B�~���� InputStream �P byte[] (���N��model��VO�w�@�ǳ�)
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
			}
		}
	}

	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}