package com.mm.bll.clock;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.mm.dao.IDaoFrame;
import com.mm.tool.MySpring;
@Component("cBllVisitplanStartClock")
public class CBllVisitplanStartClock extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		MySpring context=MySpring.getInstance();
		IDaoFrame iDaoFrame=(IDaoFrame)context.getContext().getBean("cDaoFrameImpl");
		iDaoFrame.updateVisitPlanStateForStart();
		
		
	}

	
}
