package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityNotice;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员增加一条通知公告
@SuppressWarnings("serial")
public class WAddNoticeServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		String NoticeTitle=new String(request.getParameter(MyOpcode.Notice.NoticeTitle).getBytes("ISO-8859-1"),"utf-8");
		String NoticeContent=new String(request.getParameter(MyOpcode.Notice.NoticeContent).getBytes("ISO-8859-1"),"utf-8");
	
		CEntityNotice cEntityNotice=new CEntityNotice.Builder().NoticeContent(NoticeContent).NoticeTitle(NoticeTitle).build();
		
		boolean bisAdd=iBllFrame.createNotice(cEntityNotice);
		
		JSONObject outjson=new CEntityMission.BuildJsonObject().Check(bisAdd).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

}
