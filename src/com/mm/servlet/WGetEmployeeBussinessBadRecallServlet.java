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
import com.mm.entity.CEntityBussinessBadrecord;
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员获取员工其出差不良记录
@SuppressWarnings("serial")
public class WGetEmployeeBussinessBadRecallServlet extends HttpServlet {

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
	
		LinkedMap findResult=iBllFrame.getEmployeeBussinessBadrecall(cEntityEmployee);
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			
			CEntityBussinessBadrecord cEntityBussinessBadrecord=(CEntityBussinessBadrecord)map.get(MyOpcode.BussinessBadrecord.BussinessBadrecord);
			CEntityBussiness cEntityBussiness=(CEntityBussiness)map.get(MyOpcode.Bussiness.Bussiness);
			JSONObject js=new JSONObject();
			js.put(MyOpcode.BussinessBadrecord.BussinessBadrecordId, cEntityBussinessBadrecord.getM_iBussinessBadrecordId());
			js.put(MyOpcode.BussinessBadrecord.BussinessBadrecordReason, cEntityBussinessBadrecord.getM_sBussinessBadrecordReason());
			js.put(MyOpcode.BussinessBadrecord.BussinessBadrecordTime, cEntityBussinessBadrecord.getM_sBussinessBadrecordTime());
			js.put(MyOpcode.Bussiness.BussinessId, cEntityBussiness.getM_iBussinessId());
			jsonArray.add(js);
			
		}
		outjson.put(MyOpcode.BussinessBadrecord.BussinessBadrecordList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	

}
