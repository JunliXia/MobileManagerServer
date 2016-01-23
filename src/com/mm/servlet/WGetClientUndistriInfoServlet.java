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
//得到未分配客户的基本信息
@SuppressWarnings("serial")
public class WGetClientUndistriInfoServlet extends HttpServlet {

	
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");

		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		List findReuslt=iBllFrame.getClientUndistriInfo();
		JSONObject js=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		Iterator it=findReuslt.iterator();
		while (it.hasNext()) {       
			Object[] tuple = (Object[]) it.next();
			JSONObject outjson=new JSONObject();
			outjson.put(MyOpcode.Client.ClientId, (Integer)tuple[0]);
			outjson.put(MyOpcode.Client.ClientName, (String)tuple[1]);
			outjson.put(MyOpcode.Client.ClientCompany,  (String)tuple[2]);
			jsonArray.add(outjson);
		}   
		
		js.put(MyOpcode.Client.ClientList,jsonArray);
		System.out.println(js);
		out.println(js);
		out.flush();
		out.close();
	}


}
