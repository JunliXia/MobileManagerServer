package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityClient;
import com.mm.entityarray.CEntityClientArray;
import com.mm.tool.MySpring;

//管理员查看所有客户
@SuppressWarnings("serial")
public class WQueryAllClientServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityClientArray cEntityClientArray=iBllFrame.GetAllClient();
		
		JSONObject outjson=new CEntityClient.BuildJsonObject().MyJSONArray(cEntityClientArray.toJsonArray()).build();
		
		
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}


}
