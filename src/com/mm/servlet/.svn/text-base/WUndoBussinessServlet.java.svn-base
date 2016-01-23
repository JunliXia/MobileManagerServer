package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessUndo;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员撤销出差（不设计出差活动）
@SuppressWarnings("serial")
public class WUndoBussinessServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		int BussinessId=Integer.parseInt(request.getParameter(MyOpcode.Bussiness.BussinessId));
		String BussinessUndoReason=new String(request.getParameter(MyOpcode.BussinessUndo.BussinessUndoReason).getBytes("ISO-8859-1"),"utf-8");
		String BussinessUndoTime=new String(request.getParameter(MyOpcode.BussinessUndo.BussinessUndoTime).getBytes("ISO-8859-1"),"utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId(BussinessId).build();
		CEntityBussinessUndo cEntityBussinessUndo=new CEntityBussinessUndo.Builder().BussinessUndoReason(BussinessUndoReason).BussinessUndoTime(BussinessUndoTime).build();
		
		boolean bisUndo=iBllFrame.undoBussiness(cEntityBussiness, cEntityBussinessUndo);
		
		JSONObject outjson=new CEntityBussiness.BuildJsonObject().Check(bisUndo).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
		
	
	
	}



}
