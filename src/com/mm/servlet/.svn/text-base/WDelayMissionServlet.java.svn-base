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
import com.mm.entity.CEntityMissionDelay;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员延期任务
@SuppressWarnings("serial")
public class WDelayMissionServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		int MissionId=Integer.parseInt(request.getParameter(MyOpcode.Mission.MissionId));
		String MissionDelayDeadline=new String(request.getParameter(MyOpcode.MissionDelay.MissionDelayDeadline).getBytes("ISO-8859-1"),"utf-8");
		String MissionDelayTime=new String(request.getParameter(MyOpcode.MissionDelay.MissionDelayTime).getBytes("ISO-8859-1"),"utf-8");
		
		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(MissionId).MissionDeadline(MissionDelayDeadline).build();
		CEntityMissionDelay cEntityMissionDelay=new CEntityMissionDelay.Builder().MissionDelayTime(MissionDelayTime).MissionDelayDeadline(MissionDelayDeadline).build();
		
		boolean bisDelay=iBllFrame.delayMission(cEntityMission, cEntityMissionDelay);
		JSONObject outjson=new CEntityMission.BuildJsonObject().Check(bisDelay).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}



}
