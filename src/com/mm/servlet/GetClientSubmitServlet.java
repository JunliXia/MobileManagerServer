package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;

//查看客户提交纪录
@SuppressWarnings("serial")
public class GetClientSubmitServlet extends HttpServlet {

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
		CEntityClientSubmitArray cEntityClientSubmitArray=iBllFrame.GetClientSubmit(cEntityEmployee);
		
		boolean bisGet=false;
		if(cEntityClientSubmitArray!=null){
			bisGet=true;
		}
		
		JSONObject outjson=new CEntityEmployee.BuildJsonObject().Operation(operation).Check(bisGet).build();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<cEntityClientSubmitArray.getSize();i++){
			jsonArray.add(toJsonObject(cEntityClientSubmitArray.getItem(i)));
		}
		
		outjson.put(MyOpcode.ClientSubmit.ClientSubmitList, jsonArray);
			
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}
	
	public JSONObject toJsonObject(CEntityClientSubmit cEntityClientSubmit){
		JSONObject outjson = new JSONObject();
		outjson.put(MyOpcode.ClientSubmit.ClientSubmitId, cEntityClientSubmit.getM_iClientSubmitId());
		outjson.put(MyOpcode.ClientSubmit.ClientSubmitTime, cEntityClientSubmit.getM_sClientSubmitTime());
		outjson.put(MyOpcode.ClientSubmit.ClientSubmitState, cEntityClientSubmit.getM_iClientSubmitState());
		
		outjson.put(MyOpcode.Client.ClientId, cEntityClientSubmit.getcEntityClient().getM_iClientId());
		outjson.put(MyOpcode.Client.ClientName,cEntityClientSubmit.getcEntityClient().getM_sClientName());
		outjson.put(MyOpcode.Client.ClientCompany, cEntityClientSubmit.getcEntityClient().getM_sClientCompany());
		outjson.put(MyOpcode.Client.ClientPhone, cEntityClientSubmit.getcEntityClient().getM_sClientPhone());
		outjson.put(MyOpcode.Client.ClientArea,cEntityClientSubmit.getcEntityClient().getM_sClientArea());
		outjson.put(MyOpcode.Client.ClientAddress, cEntityClientSubmit.getcEntityClient().getM_sClientAddress());
		outjson.put(MyOpcode.Client.ClientState, cEntityClientSubmit.getcEntityClient().getM_iClientState());

		
		
		
		return outjson;
	}
	

	
}
