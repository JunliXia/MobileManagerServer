package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//进入终止态拜访计划
@SuppressWarnings("serial")
public class GetCompleteVisitPlanServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		CEntityVisitPlanArray cEntityVisitPlanArray=iBllFrame.GetCompleteVisitPlan(cEntityEmployee);
		
//		boolean bisGet=false;
//		if(cEntityVisitPlanArray!=null){
//			bisGet=true;
//		}
		
//		JSONObject outjson=new CEntityVisitPlan.BuildJsonObject().Operation(operation).Check(bisGet).MyJSONArray(cEntityVisitPlanArray.toJsonArray()).build();
		JSONObject outjson = new JSONObject();
		if (cEntityVisitPlanArray != null) {
			outjson.put(MyOpcode.Operation.CHECK, true);
		} else {
			outjson.put(MyOpcode.Operation.CHECK, false);
		}
		outjson.put(MyOpcode.Operation.OPERATION, operation);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < cEntityVisitPlanArray.getSize(); i++) {
			jsonArray.add(toJSONObject(cEntityVisitPlanArray.getItem(i)));
		}
		outjson.put(MyOpcode.VisitPlan.VisitPlanList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}
	
	
	public JSONObject toJSONObject(CEntityVisitPlan cEntityVisitPlan) {
		JSONObject outjson = new JSONObject();
		outjson.put(MyOpcode.VisitPlan.VisitPlanId, cEntityVisitPlan
				.getM_iVisitPlanId());
		outjson.put(MyOpcode.VisitPlan.VisitPlanPubdate, cEntityVisitPlan
				.getM_sVisitPlanPubdate());
		outjson.put(MyOpcode.VisitPlan.VisitPlanStartTime, cEntityVisitPlan
				.getM_sVisitPlanStartTime());
		outjson.put(MyOpcode.VisitPlan.VisitPlanEndTime, cEntityVisitPlan
				.getM_sVisitPlanEndTime());
		outjson.put(MyOpcode.VisitPlan.VisitPlanState, cEntityVisitPlan
				.getM_iVisitPlanState());
		outjson.put(MyOpcode.Client.ClientId, cEntityVisitPlan
				.getcEntityClient().getM_iClientId());
		outjson.put(MyOpcode.Client.ClientName,cEntityVisitPlan
				.getcEntityClient().getM_sClientName());
		outjson.put(MyOpcode.Client.ClientCompany, cEntityVisitPlan
				.getcEntityClient().getM_sClientCompany());
		outjson.put(MyOpcode.Client.ClientPhone, cEntityVisitPlan
				.getcEntityClient().getM_sClientPhone());
		outjson.put(MyOpcode.Client.ClientArea, cEntityVisitPlan
				.getcEntityClient().getM_sClientArea());
		outjson.put(MyOpcode.Client.ClientAddress, cEntityVisitPlan
				.getcEntityClient().getM_sClientAddress());
		outjson.put(MyOpcode.Client.ClientState, cEntityVisitPlan
				.getcEntityClient().getM_iClientState());
	
		
		JSONArray jsonArray = new JSONArray();
		Iterator<CEntityVisitConclusion>it=cEntityVisitPlan.getcEntityVisitConclusions().iterator();
		while(it.hasNext()){
			CEntityVisitConclusion cEntityVisitConclusion=it.next();
			jsonArray.add(new CEntityVisitConclusion.BuildJsonObject().ToSingle(cEntityVisitConclusion).build());
		
		}
		outjson.put(MyOpcode.VisitConclusion.VisitConclusionList, jsonArray);
		return outjson;
	}

	
}
