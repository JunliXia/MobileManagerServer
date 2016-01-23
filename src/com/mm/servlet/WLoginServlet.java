package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityAdministrator;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//Õ¯“≥π‹¿Ì‘±µ«¬º
@SuppressWarnings("serial")
public class WLoginServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		String AdministratorAccount=new String(request.getParameter(MyOpcode.Administrator.AdministratorAccount).getBytes("ISO-8859-1"),"utf-8");
		String AdministratorPassword=new String(request.getParameter(MyOpcode.Administrator.AdministratorPassword).getBytes("ISO-8859-1"),"utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityAdministrator cEntityAdministrator=new CEntityAdministrator.Builder().AdministratorAccount(AdministratorAccount).AdministratorPassword(AdministratorPassword).build();
		boolean bisLogin=iBllFrame.aministorLogin(cEntityAdministrator);
		
		JSONObject outjson=new CEntityAdministrator.BuildJsonObject().Check(bisLogin).build();
		
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}

}
