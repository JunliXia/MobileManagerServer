package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityNotice;
import com.mm.entityarray.CEntityNoticeArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//进入通知公告
@SuppressWarnings("serial")
public class EnterNoticeServlet extends HttpServlet {


	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		int operation=Integer.parseInt(request.getParameter(MyOpcode.Operation.OPERATION));
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		
		CEntityNoticeArray cEntityNoticeArray=iBllFrame.enterNotice();
		boolean bisGet=false;
		if(cEntityNoticeArray!=null){
			bisGet=true;
		}
		JSONObject outjson=new CEntityNotice.BuildJsonObject().Operation(operation).Check(bisGet).MyJSONArray(cEntityNoticeArray.toJsonArray()).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
		
	}

	
}
