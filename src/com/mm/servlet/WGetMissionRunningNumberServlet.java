package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员获得需执行任务数量
@SuppressWarnings("serial")
public class WGetMissionRunningNumberServlet extends HttpServlet {

	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");

		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		List findResult=iBllFrame.GetMissionRuningNumberOfEmployeeInfo();
		JSONObject js=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		Iterator it=findResult.iterator();
		while (it.hasNext()) {       
			Object[] tuple = (Object[]) it.next();
			JSONObject outjson=new JSONObject();
			outjson.put(MyOpcode.Employee.EmployeeId, (Integer)tuple[0]);
			outjson.put(MyOpcode.Employee.EmployeeAccount, (String)tuple[1]);
			outjson.put(MyOpcode.Employee.EmployeeName,  (String)tuple[2]);
			outjson.put("MissionNumber", (Long)tuple[3]);
			jsonArray.add(outjson);
		}   
		
		js.put(MyOpcode.Employee.EmployeeList,jsonArray);
		System.out.println(js);
		out.println(js);
		out.flush();
		out.close();
	}

}
