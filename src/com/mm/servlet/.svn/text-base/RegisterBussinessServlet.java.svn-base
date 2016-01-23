package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityBussiness;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//³ö²îµÇ¼Ç
@SuppressWarnings("serial")
public class RegisterBussinessServlet extends HttpServlet {

	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int BussinessId=Integer.parseInt(request.getParameter(MyOpcode.Bussiness.BussinessId));
		String BussinessRegisterTime=new String(request.getParameter(MyOpcode.Bussiness.BussinessRegisterTime).getBytes("ISO-8859-1"),"utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId(BussinessId).BussinessRegisterTime(BussinessRegisterTime).build();
		boolean bisRES=iBllFrame.registerBussiness(cEntityBussiness);
		
		JSONObject outjson=new CEntityBussiness.BuildJsonObject().Operation(operation).Check(bisRES).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}


}
