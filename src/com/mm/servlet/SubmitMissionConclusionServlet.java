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
import com.mm.entity.CEntityMissionConclusion;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//提交任务总结
@SuppressWarnings("serial")
public class SubmitMissionConclusionServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int MissionId=Integer.parseInt(request.getParameter(MyOpcode.Mission.MissionId));
		String MissionSummary=new String(request.getParameter(MyOpcode.MissionConclusion.MissionSummary).getBytes("ISO-8859-1"),"utf-8");
		String MissionSubmitTime=new String(request.getParameter(MyOpcode.MissionConclusion.MissionSubmitTime).getBytes("ISO-8859-1"),"utf-8");
		String MissionAccessoryPath=new String(request.getParameter(MyOpcode.MissionConclusion.MissionAccessoryPath).getBytes("ISO-8859-1"),"utf-8");
	
		System.out.println(MissionSummary+"0---=0-0-0-0-");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(MissionId).build();
		CEntityMissionConclusion cEntityMissionConclusion=new CEntityMissionConclusion.Builder().MissionSummary(MissionSummary).MissionAccessoryPath(MissionAccessoryPath).MissionSubmitTime(MissionSubmitTime).build();
		
		boolean bisSubmit=iBllFrame.submitMission(cEntityMission, cEntityMissionConclusion);
		
		JSONObject outjson=new CEntityMissionConclusion.BuildJsonObject().Check(bisSubmit).Operation(operation).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}


}
