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
//管理员查看拜访总结详情
@SuppressWarnings("serial")
public class WGetVisitConclusionServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int VisitPlanId=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanId));
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(VisitPlanId).build();
		CEntityVisitConclusion cEntityVisitConclusion=iBllFrame.getVisitConclusionDetail(cEntityVisitPlan);
		
		JSONObject outjson=new CEntityVisitConclusion.BuildJsonObject().ToSingle(cEntityVisitConclusion).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
		
	}


}
