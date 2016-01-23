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
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员获得未审核的客户信息
@SuppressWarnings("serial")
public class WGerNocheckClientServlet extends HttpServlet {

	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");

		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		LinkedMap findResult=iBllFrame.getNoCheckClient();
		
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			CEntityEmployee cEntityEmployee=(CEntityEmployee)map.get(MyOpcode.Employee.Employee);
			CEntityClient cEntityClient=(CEntityClient)map.get(MyOpcode.Client.Client);
			CEntityClientSubmit cEntityClientSubmit=(CEntityClientSubmit)map.get(MyOpcode.ClientSubmit.ClientSubmit);
			jsonArray.add(toJSON(cEntityEmployee,cEntityClient,cEntityClientSubmit));
		}
		outjson.put(MyOpcode.Client.ClientList, jsonArray);
		System.out.println(outjson);
		
		out.println(outjson);
		out.flush();
		out.close();
	}

	private JSONObject toJSON(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient,CEntityClientSubmit cEntityClientSubmit){
		JSONObject outjson=new CEntityClient.BuildJsonObject().ToSingle(cEntityClient).build();
		outjson.put(MyOpcode.Employee.EmployeeId, cEntityEmployee.getM_iEmployeeId());
		outjson.put(MyOpcode.Employee.EmployeeName, cEntityEmployee.getM_sEmployeeName());
		outjson.put(MyOpcode.Employee.EmployeeAccount, cEntityEmployee.getM_sEmployeeAccount());
		outjson.put(MyOpcode.ClientSubmit.ClientSubmitTime, cEntityClientSubmit.getM_sClientSubmitTime());
		outjson.put(MyOpcode.ClientSubmit.ClientSubmitId, cEntityClientSubmit.getM_iClientSubmitId());
		return outjson;
	}

}
