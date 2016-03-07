package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityAddress;
import com.mm.entity.CEntityBussiness;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

@SuppressWarnings("serial")
public class SaveAddressServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		String AddressLong=new String(request.getParameter(MyOpcode.Address.AddressLong).getBytes("ISO-8859-1"),"utf-8");
		String AddressLat=new String(request.getParameter(MyOpcode.Address.AddressLat).getBytes("ISO-8859-1"),"utf-8");

		System.out.println("addresssave");
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityAddress cEntityAddress=new CEntityAddress(AddressLong, AddressLat, EmployeeId);
		boolean bisRES=iBllFrame.createAddress(cEntityAddress);
		JSONObject outjson=new CEntityBussiness.BuildJsonObject().Operation(operation).Check(bisRES).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

}
