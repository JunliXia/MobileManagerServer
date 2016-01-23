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
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//Õ‚«⁄»À‘±µ«¬º
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		String EmployeeAccount=new String(request.getParameter(MyOpcode.Employee.EmployeeAccount).getBytes("ISO-8859-1"),"utf-8");
		String EmployeePassword=new String(request.getParameter(MyOpcode.Employee.EmployeePassword).getBytes("ISO-8859-1"),"utf-8");
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeAccount(EmployeeAccount).EmployeePassword(EmployeePassword).build();
		CEntityEmployee findResult=iBllFrame.loginEmployee(cEntityEmployee);
		boolean bisFind=false;
		if(findResult!=null){
			bisFind=true;
		}else{
			findResult=new CEntityEmployee();
		}
		JSONObject outjson=new CEntityEmployee.BuildJsonObject().Operation(operation).Check(bisFind).EmployeeId(findResult.getM_iEmployeeId()).EmployeePassword(findResult.getM_sEmployeePassword()).build();
		
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}


}
