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
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//管理员增加员工
@SuppressWarnings("serial")
public class WAddEmployeeServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		String EmployeeAccount=new String(request.getParameter(MyOpcode.Employee.EmployeeAccount).getBytes("ISO-8859-1"),"utf-8");
		String EmployeeName=new String(request.getParameter(MyOpcode.Employee.EmployeeName).getBytes("ISO-8859-1"),"utf-8");
		String EmployeePassword=new String(request.getParameter(MyOpcode.Employee.EmployeePassword).getBytes("ISO-8859-1"),"utf-8");
		String EmployeePhone=new String(request.getParameter(MyOpcode.Employee.EmployeePhone).getBytes("ISO-8859-1"),"utf-8");
		String EmployeeSex=new String(request.getParameter(MyOpcode.Employee.EmployeeSex).getBytes("ISO-8859-1"),"utf-8");
		String EmployeeDepartment=new String(request.getParameter(MyOpcode.Employee.EmployeeDepartment).getBytes("ISO-8859-1"),"utf-8");
		String EmployeeJob=new String(request.getParameter(MyOpcode.Employee.EmployeeJob).getBytes("ISO-8859-1"),"utf-8");
		int EmployeeType=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeType));
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeAccount(EmployeeAccount).EmployeeName(EmployeeName).EmployeePassword(EmployeePassword).
		EmployeePhone(EmployeePhone).EmployeeSex(EmployeeSex).EmployeeDepartment(EmployeeDepartment).EmployeeJob(EmployeeJob).EmployeeType(EmployeeType).build();
	
		boolean bisAdd=iBllFrame.addEmployee(cEntityEmployee);
		JSONObject outjson=new CEntityAdministrator.BuildJsonObject().Check(bisAdd).build();
		
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	
	}

	
}
