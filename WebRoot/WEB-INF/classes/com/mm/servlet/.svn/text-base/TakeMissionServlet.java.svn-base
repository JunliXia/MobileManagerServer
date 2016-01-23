package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityMission;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//接受任务
@SuppressWarnings("serial")
public class TakeMissionServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int MissionId=Integer.parseInt(request.getParameter(MyOpcode.Mission.MissionId));
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(MissionId).build();
		boolean bisAccept=iBllFrame.acceptMission(cEntityMission);
		
		JSONObject outjson=new CEntityMission.BuildJsonObject().Operation(operation).Check(bisAccept).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	

}
