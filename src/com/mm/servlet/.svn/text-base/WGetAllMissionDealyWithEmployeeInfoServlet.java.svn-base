package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.LinkedMap;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionDelay;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员得到所有的任务延期与员工信息
@SuppressWarnings("serial")
public class WGetAllMissionDealyWithEmployeeInfoServlet extends HttpServlet {

	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
//		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		LinkedMap findResult=iBllFrame.GetAllMissionDelayWithEmployeeInfo();
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			CEntityMissionDelay cEntityMissionDelay=(CEntityMissionDelay)map.get(MyOpcode.MissionDelay.MissionDelay);
			CEntityMission cEntityMission=(CEntityMission)map.get(MyOpcode.Mission.Mission);
			CEntityEmployee cEntityEmployee=(CEntityEmployee)map.get(MyOpcode.Employee.Employee);
			jsonArray.add(toJSON(cEntityMissionDelay,cEntityMission,cEntityEmployee));
		}
		outjson.put(MyOpcode.MissionDelay.MissionDelayList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}
	private JSONObject toJSON(CEntityMissionDelay cEntityMissionDelay,CEntityMission cEntityMission,CEntityEmployee cEntityEmployee){
		JSONObject outjson=new CEntityMissionDelay.BuildJsonObject().ToSingle(cEntityMissionDelay).build();
		outjson.put(MyOpcode.Mission.MissionId, cEntityMission.getM_iMissionId());
		outjson.put(MyOpcode.Mission.MissionContent, cEntityMission.getM_sMissionContent());
		outjson.put(MyOpcode.Employee.EmployeeId, cEntityEmployee.getM_iEmployeeId());
		outjson.put(MyOpcode.Employee.EmployeeAccount, cEntityEmployee.getM_sEmployeeAccount());
		outjson.put(MyOpcode.Employee.EmployeeName, cEntityEmployee.getM_sEmployeeName());
		return outjson;
	}

}
