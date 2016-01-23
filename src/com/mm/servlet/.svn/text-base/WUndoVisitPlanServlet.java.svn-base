package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
//³·Ïú°Ý·Ã¼Æ»®
@SuppressWarnings("serial")
public class WUndoVisitPlanServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int VisitPlanId=Integer.parseInt(request.getParameter(MyOpcode.VisitPlan.VisitPlanId));
		String VisitUndoReason=new String(request.getParameter(MyOpcode.VisitUndo.VisitUndoReason).getBytes("ISO-8859-1"),"utf-8");
		String VisitUndoTime=new String(request.getParameter(MyOpcode.VisitUndo.VisitUndoTime).getBytes("ISO-8859-1"),"utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(VisitPlanId).build();
		CEntityVisitUndo cEntityVisitUndo=new CEntityVisitUndo.Builder().VisitUndoReason(VisitUndoReason).VisitUndoTime(VisitUndoTime).build();
//		System.out.println(VisitPlanId+"---"+VisitUndoReason+"---"+VisitUndoTime);
		boolean bisUndo=iBllFrame.undoVisitPlan(cEntityVisitPlan, cEntityVisitUndo);
		
		JSONObject outjson=new CEntityVisitPlan.BuildJsonObject().Check(bisUndo).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}

	
}
