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
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员添加出差
@SuppressWarnings("serial")
public class WAddBussinessServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		String BussinessSideAddress=new String(request.getParameter(MyOpcode.Bussiness.BussinessSideAddress).getBytes("ISO-8859-1"),"utf-8");
		String BussinessContent=new String(request.getParameter(MyOpcode.Bussiness.BussinessContent).getBytes("ISO-8859-1"),"utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessContent(BussinessContent).BussinessSideAddress(BussinessSideAddress).build();
		
		boolean bisAdd=iBllFrame.createBussiness(cEntityBussiness, cEntityEmployee);
		JSONObject outjson=new CEntityBussiness.BuildJsonObject().Check(bisAdd).build();
		
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

}
