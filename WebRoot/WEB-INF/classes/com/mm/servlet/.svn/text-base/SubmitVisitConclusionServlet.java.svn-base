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
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//提交拜访总结
@SuppressWarnings("serial")
public class SubmitVisitConclusionServlet extends HttpServlet {

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		int VisitPlanId=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanId));
		String VisitSummary=new String(request.getParameter(MyOpcode.VisitConclusion.VisitSummary).getBytes("ISO-8859-1"),"utf-8");
		String VisitCommand=new String(request.getParameter(MyOpcode.VisitConclusion.VisitCommand).getBytes("ISO-8859-1"),"utf-8");
		String VisitAccessoryPath=new String(request.getParameter(MyOpcode.VisitConclusion.VisitAccessoryPath).getBytes("ISO-8859-1"),"utf-8");
		String VisitSubmitTime=new String(request.getParameter(MyOpcode.VisitConclusion.VisitSubmitTime).getBytes("ISO-8859-1"),"utf-8");
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(VisitPlanId).build();
		CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion.Builder().VisitSummary(VisitSummary).VisitCommand(VisitCommand).VisitAccessoryPath(VisitAccessoryPath).VisitSubmitTime(VisitSubmitTime).build();
	
		boolean bisSubmit=iBllFrame.submitVisit(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		
		JSONObject outjson=new CEntityVisitConclusion.BuildJsonObject().Operation(operation).Check(bisSubmit).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	

}
