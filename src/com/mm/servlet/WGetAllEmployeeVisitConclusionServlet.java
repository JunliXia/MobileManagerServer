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
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
@SuppressWarnings("serial")
public class WGetAllEmployeeVisitConclusionServlet extends HttpServlet {

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
		LinkedMap findResult =iBllFrame.getEmployeeVisitConclusion(cEntityEmployee);
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			CEntityVisitConclusion cEntityVisitConclusion=(CEntityVisitConclusion)map.get(MyOpcode.VisitConclusion.VisitConclusion);
			CEntityClient cEntityClient=(CEntityClient)map.get(MyOpcode.Client.Client);
			
			jsonArray.add(toJSON(cEntityVisitConclusion,cEntityClient));
		}
		outjson.put(MyOpcode.VisitConclusion.VisitConclusionList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
		
	}

	private JSONObject toJSON(CEntityVisitConclusion cEntityVisitConclusion,CEntityClient cEntityClient){
		JSONObject outjson = new CEntityVisitConclusion.BuildJsonObject().ToSingle(cEntityVisitConclusion).build();
		outjson.put(MyOpcode.VisitPlan.VisitPlanId, cEntityVisitConclusion.getM_iVisitPlanId());
		outjson.put(MyOpcode.Client.ClientName, cEntityClient.getM_sClientName());
		return outjson;
	}
	

}
