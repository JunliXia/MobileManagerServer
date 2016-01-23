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
import com.mm.entity.CEntityBussinessBadrecord;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员审核出差并添加一条出差不良记录
@SuppressWarnings("serial")
public class WCheckBussinessWithBadRecallServlet extends HttpServlet {


	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		String BussinessBadrecordTime=new String(request.getParameter(MyOpcode.BussinessBadrecord.BussinessBadrecordTime).getBytes("ISO-8859-1"),"utf-8");
		String BussinessBadrecordReason=new String(request.getParameter(MyOpcode.BussinessBadrecord.BussinessBadrecordReason).getBytes("ISO-8859-1"),"utf-8");
		int BussinessId=Integer.parseInt(request.getParameter(MyOpcode.Bussiness.BussinessId));
		
		CEntityBussiness cEntityBussiness=new  CEntityBussiness.Builder().BussinessId(BussinessId).build();
		CEntityBussinessBadrecord cEntityBussinessBadrecord=new CEntityBussinessBadrecord.Builder().BussinessBadrecordReason(BussinessBadrecordReason).BussinessBadrecordTime(BussinessBadrecordTime).build();
		
		boolean bischceck=iBllFrame.checkBussinessPassWithBadrecall(cEntityBussiness, cEntityBussinessBadrecord);
		JSONObject outjson=new CEntityBussiness.BuildJsonObject().Check(bischceck).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
		
	}

	

}
