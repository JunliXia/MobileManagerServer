package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.LinkedMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员获得该员工所有的任务总结
@SuppressWarnings("serial")
public class WGetAllEmployeeMissionConclusionServlet extends HttpServlet {

	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));

		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		LinkedMap findResult=iBllFrame.GetAllEmployeeMissionConclusion(cEntityEmployee);
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			CEntityMissionConclusion cEntityMissionConclusion=(CEntityMissionConclusion)map.get(MyOpcode.MissionConclusion.MissionConclusion);
			CEntityMission cEntityMission=(CEntityMission)map.get(MyOpcode.Mission.Mission);
			
			jsonArray.add(toJSON(cEntityMissionConclusion,cEntityMission));
		}
		outjson.put(MyOpcode.MissionConclusion.MissionConclusionList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
		
	}
	

	private JSONObject toJSON(CEntityMissionConclusion cEntityMissionConclusion,CEntityMission cEntityMission){
		JSONObject outjson=new CEntityMissionConclusion.BuildJsonObject().ToSingle(cEntityMissionConclusion).build();
		outjson.put(MyOpcode.Mission.MissionContent, cEntityMission.getM_sMissionContent());
		outjson.put(MyOpcode.Mission.MissionId, cEntityMission.getM_iMissionId());
		return outjson;
	}
}
