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
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//管理员审核客户
@SuppressWarnings("serial")
public class WCheckClientServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		int ClientId=Integer.parseInt(request.getParameter(MyOpcode.Client.ClientId));
		int ClientSubmitState=Integer.parseInt(request.getParameter(MyOpcode.ClientSubmit.ClientSubmitState));
		
		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(ClientId).build();
		boolean bisCheck=iBllFrame.checkClient(cEntityClient, ClientSubmitState);
		
		
		JSONObject outjson=new CEntityClient.BuildJsonObject().Check(bisCheck).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}


}
