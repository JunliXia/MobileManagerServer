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
//����Ա��������
@SuppressWarnings("serial")
public class WAbandonMissionServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");

		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		int MissionId=Integer.parseInt(request.getParameter(MyOpcode.Mission.MissionId));
		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(MissionId).build();
		
		boolean bisAbandon=iBllFrame.abandonMission(cEntityMission);
		JSONObject outjson=new CEntityMission.BuildJsonObject().Check(bisAbandon).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

}
