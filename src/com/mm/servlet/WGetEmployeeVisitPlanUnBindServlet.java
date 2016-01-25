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
import com.mm.entity.CEntityVisitPlan;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//����Ա��ȡԱ��δ�󶨵İݷüƻ���Ϣ
@SuppressWarnings("serial")
public class WGetEmployeeVisitPlanUnBindServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");

		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");

		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();

		CEntityVisitPlanArray cEntityVisitPlanArray=iBllFrame.getUnBindVisitPlan(cEntityEmployee);
		JSONObject outjson=new CEntityVisitPlan.BuildJsonObject().MyJSONArray(cEntityVisitPlanArray.toJsonArray()).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();



	}

}
