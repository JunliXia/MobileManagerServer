package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//获取终止态的任务
@SuppressWarnings("serial")
public class GetCompleteMissionServlet extends HttpServlet {

	
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
		CEntityMissionArray cEntityMissionArray=iBllFrame.GetCompleteMissionArray(cEntityEmployee);
		
		boolean bisGet=false;
		if(cEntityMissionArray!=null){
			bisGet=true;
		}
		
		JSONObject outjson=new CEntityMission.BuildJsonObject().Operation(operation).Check(bisGet).MyJSONArray(cEntityMissionArray.toJsonArray()).build();
		
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}


}
