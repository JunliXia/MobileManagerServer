package com.mm.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 
 * 作者：夏俊力
 * 功能：下载图片
 *
 */
@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		String AccessoryPath=new String(request.getParameter("AccessoryPath").getBytes("ISO-8859-1"),"utf-8");
		
		FileInputStream fis = new FileInputStream(
				AccessoryPath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		@SuppressWarnings("unused")
		int count = 0;
		while ((count = fis.read(buffer)) >= 0) {
			baos.write(buffer);
		}
		String str = new String(Base64.encode(baos.toByteArray()));
		PrintWriter pw = response.getWriter();
		pw.write(str);
		pw.flush();
		pw.close();
	
	}

}
