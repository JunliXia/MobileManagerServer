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
import com.mm.entity.CEntityVisitDelay;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员查看该员工的拜访延期记录
@SuppressWarnings("serial")
public class WGetEmployeeDelayServlet extends HttpServlet {

	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		LinkedMap findResult=iBllFrame.getEmployeeVisitDealy(cEntityEmployee);
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			CEntityVisitDelay cEntityVisitDelay=(CEntityVisitDelay)map.get(MyOpcode.VisitDelay.VisitDelay);
			CEntityVisitPlan cEntityVisitPlan=(CEntityVisitPlan)map.get(MyOpcode.VisitPlan.VisitPlan);
			JSONObject js=new CEntityVisitDelay.BuildJsonObject().ToSingle(cEntityVisitDelay).build();
			js.put(MyOpcode.VisitPlan.VisitPlanId, cEntityVisitPlan.getM_iVisitPlanId());
			jsonArray.add(js);
			
		}
		outjson.put(MyOpcode.VisitDelay.VisitDelayList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	

}
