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
import com.mm.entity.CEntityMissionConclusion;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员得到所有的任务总结与其员工信息
@SuppressWarnings("serial")
public class WGetAllMissionConclusionWithEmployeeInfoServlet extends
		HttpServlet {


	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");

		LinkedMap findResult=iBllFrame.GetAllMissionConclusionWithEmployeeInfo();
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			
			CEntityEmployee cEntityEmployee=(CEntityEmployee)map.get(MyOpcode.Employee.Employee);
			CEntityMission cEntityMission=(CEntityMission)map.get(MyOpcode.Mission.Mission);
			CEntityMissionConclusion cEntityMissionConclusion=(CEntityMissionConclusion)map.get(MyOpcode.MissionConclusion.MissionConclusion);
			
			jsonArray.add(toJson(cEntityEmployee, cEntityMission,cEntityMissionConclusion));
			
		}
		outjson.put(MyOpcode.MissionConclusion.MissionConclusionList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}


	private JSONObject toJson(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion){
		JSONObject js=new CEntityMission.BuildJsonObject().ToSingle(cEntityMission).build();
		js.put(MyOpcode.Employee.EmployeeId, cEntityEmployee.getM_iEmployeeId());
		js.put(MyOpcode.Employee.EmployeeAccount, cEntityEmployee.getM_sEmployeeAccount());
		js.put(MyOpcode.Employee.EmployeeName, cEntityEmployee.getM_sEmployeeName());
		js.put(MyOpcode.MissionConclusion.MissionConclusionId, cEntityMissionConclusion.getM_iMissionConclusionId());
		js.put(MyOpcode.MissionConclusion.MissionSubmitTime, cEntityMissionConclusion.getM_sMissionSubmitTime());
		js.put(MyOpcode.MissionConclusion.MissionSummary, cEntityMissionConclusion.getM_sMissionSummary());
		js.put(MyOpcode.MissionConclusion.MissionCheck, cEntityMissionConclusion.getM_iMissionCheck());
		js.put(MyOpcode.MissionConclusion.MissionAccessoryPath, cEntityMissionConclusion.getM_sMissionAccessoryPath());
		return js;
		
	}

	

}
