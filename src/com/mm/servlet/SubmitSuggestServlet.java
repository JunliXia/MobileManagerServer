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
import com.mm.entity.CEntitySuggest;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//提交投诉建议
@SuppressWarnings("serial")
public class SubmitSuggestServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		String SuggestContent=new String(request.getParameter(MyOpcode.Suggest.SuggestContent).getBytes("ISO-8859-1"),"utf-8");
		String SuggestTime=new String(request.getParameter(MyOpcode.Suggest.SuggestTime).getBytes("ISO-8859-1"),"utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		CEntitySuggest cEntitySuggest=new CEntitySuggest.Builder().SuggestContent(SuggestContent).SuggestTime(SuggestTime).build();
		
		boolean bisSubmit=iBllFrame.submitSuggest(cEntityEmployee, cEntitySuggest);
		
		JSONObject outjson=new CEntityEmployee.BuildJsonObject().Operation(operation).Check(bisSubmit).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
		
		
	}

}
