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
import com.mm.entity.CEntityAddress;
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员员工的地址信息
@SuppressWarnings("serial")
public class WGetEmployeeAddressServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		int days=Integer.parseInt(request.getParameter("days"));
		
		
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		
		LinkedMap findResult=iBllFrame.getAddressInfo(cEntityEmployee, days);
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			CEntityAddress cEntityAddress=(CEntityAddress)map.get(MyOpcode.Address.Address);
			JSONObject js=new JSONObject();
			js.put(MyOpcode.Address.AddressId, cEntityAddress.getAddressId());
			js.put(MyOpcode.Address.AddressLong, cEntityAddress.getAddressLong());
			js.put(MyOpcode.Address.AddressLat, cEntityAddress.getAddressLat());
			js.put(MyOpcode.Address.AddressTime, cEntityAddress.getAddressTime());
			jsonArray.add(js);
		}
		outjson.put(MyOpcode.Address.AddressList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

}
