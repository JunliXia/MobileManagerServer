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
@SuppressWarnings("serial")
public class GetMissionConclusionServlet extends HttpServlet {

	
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
		CEntityMissionConclusion cEntityMissionConclusion=iBllFrame.GetMissionConclusion(cEntityMission);
		boolean bisGet=false;
		if(cEntityMissionConclusion!=null){
			bisGet=true;
		}
		
		JSONObject outjson=new CEntityMissionConclusion.BuildJsonObject().Operation(operation).Check(bisGet).ToSingle(cEntityMissionConclusion).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}


}
