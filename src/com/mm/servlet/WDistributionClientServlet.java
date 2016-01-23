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
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员分配客户给员工并创建拜访计划
@SuppressWarnings("serial")
public class WDistributionClientServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		int ClientId=Integer.parseInt(request.getParameter(MyOpcode.Client.ClientId));
		
		String VisitPlanPubdate=new String(request.getParameter(MyOpcode.VisitPlan.VisitPlanPubdate).getBytes("ISO-8859-1"),"utf-8");
		String VisitPlanStartTime=new String(request.getParameter(MyOpcode.VisitPlan.VisitPlanStartTime).getBytes("ISO-8859-1"),"utf-8");
		String VisitPlanEndTime=new String(request.getParameter(MyOpcode.VisitPlan.VisitPlanEndTime).getBytes("ISO-8859-1"),"utf-8");
		int VisitPlanState=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanState));
		int VisitPlanCycle=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanCycle));
		int VisitPlanCycleType=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanCycleType));
		int VisitPlanCycleNumber=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanCycleNumber));
		int VisitPlanDays=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanDays));

		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(ClientId).build();
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanPubdate(VisitPlanPubdate).
		VisitPlanStartTime(VisitPlanStartTime).VisitPlanEndTime(VisitPlanEndTime).VisitPlanState(VisitPlanState).
		VisitPlanCycle(VisitPlanCycle).VisitPlanCycleType(VisitPlanCycleType).VisitPlanCycleNumber(VisitPlanCycleNumber)
		.VisitPlanDays(VisitPlanDays).build();
		
		boolean bisDis=iBllFrame.distributionClient(cEntityClient, cEntityEmployee, cEntityVisitPlan);
		JSONObject outjson=new CEntityClient.BuildJsonObject().Check(bisDis).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
		
	}

}
