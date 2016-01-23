package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityClient;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员添加客户
@SuppressWarnings("serial")
public class WAddClientServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		String ClientName=new String(request.getParameter(MyOpcode.Client.ClientName).getBytes("ISO-8859-1"),"utf-8");
		String ClientCompany=new String(request.getParameter(MyOpcode.Client.ClientCompany).getBytes("ISO-8859-1"),"utf-8");
		String ClientPhone=new String(request.getParameter(MyOpcode.Client.ClientPhone).getBytes("ISO-8859-1"),"utf-8");
		String ClientArea=new String(request.getParameter(MyOpcode.Client.ClientArea).getBytes("ISO-8859-1"),"utf-8");
		String ClientAddress=new String(request.getParameter(MyOpcode.Client.ClientAddress).getBytes("ISO-8859-1"),"utf-8");
		int ClientState=MyConstant.Client.CLIENT_UNDISTRIBUTED;
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityClient cEntityClient=new CEntityClient.Builder().ClientName(ClientName).ClientCompany(ClientCompany).ClientPhone(ClientPhone).ClientArea(ClientArea).ClientAddress(ClientAddress).ClientState(ClientState).build();
		
		boolean bisSubmit=iBllFrame.createClient(cEntityClient);
		
		JSONObject outjson=new CEntityClient.BuildJsonObject().Check(bisSubmit).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	
}
