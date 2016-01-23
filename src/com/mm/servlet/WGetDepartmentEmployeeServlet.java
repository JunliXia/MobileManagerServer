package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//获取部门所有员工信息
@SuppressWarnings("serial")
public class WGetDepartmentEmployeeServlet extends HttpServlet {


	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		String EmployeeDepartment=new String(request.getParameter(MyOpcode.Employee.EmployeeDepartment).getBytes("ISO-8859-1"),"utf-8");
		
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeDepartment(EmployeeDepartment).build();
		CEntityEmployeeArray cEntityEmployeeArray=iBllFrame.getDepartmentEmployee(cEntityEmployee);
		
		JSONObject outjson=new CEntityEmployee.BuildJsonObject().MyJSONArray(cEntityEmployeeArray.toJsonArray()).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}



}
