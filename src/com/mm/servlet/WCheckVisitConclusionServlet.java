package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员审核客户拜访
@SuppressWarnings("serial")
public class WCheckVisitConclusionServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		int VisitPlanId=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanId));
		int VisitConclusionId=Integer.parseInt(request.getParameter(MyOpcode.VisitConclusion.VisitConclusionId));
		int OperateState=Integer.parseInt(request.getParameter(MyOpcode.VisitConclusion.VisitCheck));
		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(VisitPlanId).build();
		CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion.Builder().VisitConclusionId(VisitConclusionId).build();
		
		boolean bisClick=iBllFrame.checkVisitConclusion(cEntityVisitPlan, cEntityVisitConclusion, OperateState);

		
		JSONObject outjson=new CEntityVisitConclusion.BuildJsonObject().Check(bisClick).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}



}
