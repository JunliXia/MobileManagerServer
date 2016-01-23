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
//管理员按年，月统计全部未删员工的考勤记录
@SuppressWarnings("serial")
public class WGetAttendanceStatisticalYearMonthServlet extends HttpServlet {

	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		String year=new String(request.getParameter("year").getBytes("ISO-8859-1"),"utf-8");
		String month=new String(request.getParameter("month").getBytes("ISO-8859-1"),"utf-8");
		 
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		List findResult=iBllFrame.getAttendanceStatisticalByYearAndMonth(year,month);
		Iterator it = findResult.iterator();  
	
	
		JSONArray jsonArray=new JSONArray();
		while (it.hasNext()) {       
		Object[] tuple = (Object[]) it.next();
//		System.out.println((Integer)tuple[0]+"---"+(String)tuple[1]+"---"+(String)tuple[2]+"---"+(Long)tuple[3]+"---"+(Long)tuple[4]+"---"+(Long)tuple[5]+"---"+(Long)tuple[6]+"---"+(Long)tuple[7]+"---"+(Long)tuple[8]);
		JSONObject outjson=new JSONObject();
		outjson.put(MyOpcode.Employee.EmployeeId, (Integer)tuple[0]);
		outjson.put(MyOpcode.Employee.EmployeeAccount, (String)tuple[1]);
		outjson.put(MyOpcode.Employee.EmployeeName, (String)tuple[2]);
		outjson.put("RegisterTimeTimes", (Long)tuple[3]);	//签到次数
		outjson.put("SignoutTimeTimes", (Long)tuple[4]);	//签退次数
		outjson.put("RegisterOutTimeTimes", (Long)tuple[5]);//签到迟到次数
		outjson.put("SignoutOutTimeTimes", (Long)tuple[6]);	//签退迟到次数
		outjson.put("RegisterNoTimeTimes", (Long)tuple[7]);	//签到漏签次数
		outjson.put("SignoutNoTimeTimes", (Long)tuple[8]);	//签退漏签次数
		jsonArray.add(outjson);
		
		}   
		JSONObject jsonObject=new JSONObject();
		jsonObject.put(MyOpcode.Attendance.AttendanceList, jsonArray);
		System.out.println(jsonObject);
		out.println(jsonObject);
		out.flush();
		out.close();
	}


}
