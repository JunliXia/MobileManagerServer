package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityMission;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员获取出差的出差活动
@SuppressWarnings("serial")
public class WGetBussinessActivityServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		int BussinessId=Integer.parseInt(request.getParameter(MyOpcode.Bussiness.BussinessId));
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId(BussinessId).build();
		
		HashMap<String, Object> result=iBllFrame.GetGetBussinessActivity(cEntityBussiness);
		
		CEntityMissionArray cEntityMissionArray=(CEntityMissionArray) result.get(MyOpcode.Mission.MissionList);
		CEntityVisitPlanArray cEntityVisitPlanArray=(CEntityVisitPlanArray)result.get(MyOpcode.VisitPlan.VisitPlanList);
		
		JSONObject outjson=new CEntityMission.BuildJsonObject().MyJSONArray(cEntityMissionArray.toJsonArray()).build();
		outjson.put(MyOpcode.VisitPlan.VisitPlanList, cEntityVisitPlanArray.toJsonArray());
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

}
