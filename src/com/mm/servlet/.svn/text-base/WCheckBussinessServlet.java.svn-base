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
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员审核出差
@SuppressWarnings("serial")
public class WCheckBussinessServlet extends HttpServlet {


	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		int BussinessId=Integer.parseInt(request.getParameter(MyOpcode.Bussiness.BussinessId));
		int BussinessState=Integer.parseInt(request.getParameter(MyOpcode.Bussiness.BussinessState));
		
		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId(BussinessId).build();
		boolean bisCheck=false;
		if(BussinessState==MyConstant.Bussiness.BUSSINESS_CHECKPASS){
			bisCheck=iBllFrame.checkBussinessPass(cEntityBussiness);
		}else if(BussinessState==MyConstant.Bussiness.BUSSINESS_CHECKNOPASS){
			bisCheck=iBllFrame.checkBussinessNopass(cEntityBussiness);
		}
		
		JSONObject outjson=new CEntityBussiness.BuildJsonObject().Check(bisCheck).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	

}
