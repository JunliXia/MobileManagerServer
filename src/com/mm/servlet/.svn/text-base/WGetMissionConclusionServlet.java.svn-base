package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员查看任务总结详情
@SuppressWarnings("serial")
public class WGetMissionConclusionServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		int MissionConclusionId=Integer.parseInt(request.getParameter(MyOpcode.MissionConclusion.MissionConclusionId));
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityMissionConclusion cEntityMissionConclusion=new CEntityMissionConclusion.Builder().MissionConclusionId(MissionConclusionId).build();
		CEntityMissionConclusion findReuslt=iBllFrame.GetMissionConclusionDetail(cEntityMissionConclusion);
		
		JSONObject outjson=new CEntityMissionConclusion.BuildJsonObject().ToSingle(findReuslt).build();

		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}

	

}
