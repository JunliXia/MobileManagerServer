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
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessUndo;
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员获取所有出差撤销信息
@SuppressWarnings("serial")
public class WGetAllBussinessUndoServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		LinkedMap findResult=iBllFrame.getAllBussinessUndoInfo();
		
		JSONObject outjson=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			
			CEntityBussinessUndo cEntityBussinessUndo=(CEntityBussinessUndo)map.get(MyOpcode.BussinessUndo.BussinessUndo);
			CEntityBussiness cEntityBussiness=(CEntityBussiness)map.get(MyOpcode.Bussiness.Bussiness);
			CEntityEmployee cEntityEmployee=(CEntityEmployee)map.get(MyOpcode.Employee.Employee);
			jsonArray.add(toJson(cEntityEmployee, cEntityBussiness, cEntityBussinessUndo));
			
		}
		
		outjson.put(MyOpcode.BussinessUndo.BussinessUndoList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}
	
	
	private JSONObject toJson(CEntityEmployee cEntityEmployee,CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		JSONObject js=new CEntityBussiness.BuildJsonObject().ToSingle(cEntityBussiness).build();
		js.put(MyOpcode.Employee.EmployeeId, cEntityEmployee.getM_iEmployeeId());
		js.put(MyOpcode.Employee.EmployeeAccount, cEntityEmployee.getM_sEmployeeAccount());
		js.put(MyOpcode.Employee.EmployeeName, cEntityEmployee.getM_sEmployeeName());
		js.put(MyOpcode.BussinessUndo.BussinessUndoId, cEntityBussinessUndo.getM_iBussinessUndoId());
		js.put(MyOpcode.BussinessUndo.BussinessUndoReason, cEntityBussinessUndo.getM_sBussinessUndoReason());
		js.put(MyOpcode.BussinessUndo.BussinessUndoTime, cEntityBussinessUndo.getM_sBussinessUndoTime());
		return js;
		
	}

	
}
