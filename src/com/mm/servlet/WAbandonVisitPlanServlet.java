package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员废弃拜访计划
@SuppressWarnings("serial")
public class WAbandonVisitPlanServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");

		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		int VisitPlanId=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanId));
		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(VisitPlanId).build();
		boolean bosAbandon=iBllFrame.abandonVisitPlan(cEntityVisitPlan);
		JSONObject outjson=new CEntityVisitPlan.BuildJsonObject().Check(bosAbandon).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}



}
