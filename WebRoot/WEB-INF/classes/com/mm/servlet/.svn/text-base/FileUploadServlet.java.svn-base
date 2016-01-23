package com.mm.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {

	public FileUploadServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final long MAX_SIZE = 30 * 1024 * 1024;
		final String[] allowedExt = new String[] { "jpg", "jpeg", "gif", "txt",
				"doc", "docx", "mp3", "wma", "m4a", "png" };
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);
		dfif.setRepository(new File("F:/ImagesUploadTemp"));

		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setSizeMax(MAX_SIZE);

		PrintWriter out = response.getWriter();
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			if (e instanceof SizeLimitExceededException) {
				 out.println("�ļ��ߴ糬��涨��С:"+ MAX_SIZE +"�ֽ�<p />");   
				out.println("<a href=\"upload.html\" target=\"_top\">����</a>");
				return;
			}
			e.printStackTrace();
		}
		if (fileList == null || fileList.size() == 0) {
			out.println("��ѡ���ϴ��ļ�<p />");
			out.println("<a href=\"upload.html\" target=\"_top\">����</a>");
			return;
		}
		Iterator fileItr = fileList.iterator();
		while (fileItr.hasNext()) {
			FileItem fileItem = null;
			String path = null;
			long size = 0;
			fileItem = (FileItem) fileItr.next();
			if (fileItem == null || fileItem.isFormField()) {
				continue;
			}
			path = fileItem.getName();
			size = fileItem.getSize();
			if ("".equals(path) || size == 0) {
				out.println("��ѡ���ϴ��ļ�<p />");
				out.println("<a href=\"upload.html\" target=\"_top\">����</a>");
				return;
			}

			String t_name = path.substring(path.lastIndexOf("\\") + 1);
			System.out.println("t_name:-------"+t_name);
			String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
			int allowFlag = 0;
			int allowedExtCount = allowedExt.length;
			for (; allowFlag < allowedExtCount; allowFlag++) {
				if (allowedExt[allowFlag].equals(t_ext))
					break;
			}
			if (allowFlag == allowedExtCount) {
				out.println("���ϴ��������͵��ļ�<p />");
				for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
					out.println("*." + allowedExt[allowFlag]
							+ "&nbsp;&nbsp;&nbsp;");
				out
						.println("<p /><a href=\"upload.html\" target=\"_top\">����</a>");
				return;
			}

			long now = System.currentTimeMillis();
			String prefix = String.valueOf(now);
			String u_name = "F:/ImagesUploaded/" + t_name;
//			IBLLFrame ibllFrame=new CBLLFrameImpl();
//			CAccessoryEntity cAccessoryEntity=new CAccessoryEntity(u_name);
//			ibllFrame.InsertAccessory(cAccessoryEntity);
			
			try {
				fileItem.write(new File(u_name));
				out.println("�ļ��ϴ��ɹ�. �ѱ���Ϊ: " + prefix + "." + t_ext
						+ " &nbsp;&nbsp;�ļ���С: " + size + "�ֽ�<p />");
				out.println("<a href=\"upload.html\" target=\"_top\">�����ϴ�</a>");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
