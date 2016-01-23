package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityAttendance;
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//Ç©ÍË
@SuppressWarnings("serial")
public class SignoutAttendanceServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		String AttendanceSignoutTime=new String(request.getParameter(MyOpcode.Attendance.AttendanceSignoutTime).getBytes("ISO-8859-1"),"utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceSignoutTime(AttendanceSignoutTime).build();
		
		boolean bisSignout=iBllFrame.signoutAttendance(cEntityEmployee, cEntityAttendance);
		
		JSONObject outjson=new CEntityAttendance.BuildJsonObject().Operation(operation).Check(bisSignout).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	
}
