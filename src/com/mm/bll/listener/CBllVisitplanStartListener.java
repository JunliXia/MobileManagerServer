package com.mm.bll.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mm.dao.IDaoFrame;
import com.mm.tool.MySpring;

public class CBllVisitplanStartListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		MySpring context=MySpring.getInstance();
		IDaoFrame iDaoFrame=(IDaoFrame)context.getContext().getBean("cDaoFrameImpl");
		iDaoFrame.updateVisitPlanStateForStart();
		
	}

}
