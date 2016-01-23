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

import org.apache.commons.collections.map.HashedMap;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员按员工号获得所有拜访计划
@SuppressWarnings("serial")
public class WGetEmployeeVisitPlanServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");

		int EmploueeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmploueeId).build();
		HashedMap findResult=iBllFrame.getAllEmployeeVisitPlan(cEntityEmployee);
		
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			HashedMap map=(HashedMap)value;
			CEntityVisitPlan cEntityVisitPlan=(CEntityVisitPlan)map.get(MyOpcode.VisitPlan.VisitPlan);
			CEntityClient cEntityClient=(CEntityClient)map.get(MyOpcode.Client.Client);
			jsonArray.add(toJSON(cEntityVisitPlan,cEntityClient));
		}
		outjson.put(MyOpcode.VisitPlan.VisitPlanList, jsonArray);
		System.out.println(outjson);
		
		out.println(outjson);
		out.flush();
		out.close();
	}

	private JSONObject toJSON(CEntityVisitPlan cEntityVisitPlan,CEntityClient cEntityClient){
		JSONObject outjson = new JSONObject();
		outjson.put(MyOpcode.VisitPlan.VisitPlanId,
				cEntityVisitPlan.getM_iVisitPlanId());
		outjson.put(MyOpcode.VisitPlan.VisitPlanPubdate,
				cEntityVisitPlan.getM_sVisitPlanPubdate());
		outjson.put(MyOpcode.VisitPlan.VisitPlanStartTime,
				cEntityVisitPlan.getM_sVisitPlanStartTime());
		outjson.put(MyOpcode.VisitPlan.VisitPlanEndTime,
				cEntityVisitPlan.getM_sVisitPlanEndTime());
		outjson.put(MyOpcode.VisitPlan.VisitPlanState,
				cEntityVisitPlan.getM_iVisitPlanState());
		outjson.put(MyOpcode.VisitPlan.VisitPlanCycle,
				cEntityVisitPlan.getM_iVisitPlanCycle());
		outjson.put(MyOpcode.VisitPlan.VisitPlanCycleType,
				cEntityVisitPlan.getM_iVisitPlanCycleType());
		outjson.put(MyOpcode.VisitPlan.VisitPlanCycleNumber,
				cEntityVisitPlan.getM_iVisitPlanCycleNumber());
		outjson.put(MyOpcode.VisitPlan.VisitPlanDays,
				cEntityVisitPlan.getM_iVisitPlanDays());
		outjson.put(MyOpcode.VisitPlan.VisitBussinessBandState,
				cEntityVisitPlan.getM_iVisitBussinessBandState());
		
		outjson.put(MyOpcode.Client.ClientId, cEntityClient.getM_iClientId());
		outjson.put(MyOpcode.Client.ClientName,cEntityClient.getM_sClientName());

		return outjson;
	}

	
}
