package com.mm.bll.clock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MySpring;

@Component("cBllVisitplanClock")
public class CBllVisitplanClock extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		MySpring context=MySpring.getInstance();
		IDaoFrame iDaoFrame=(IDaoFrame)context.getContext().getBean("cDaoFrameImpl");
		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanEndTime(getYesterdayPubdate()).build();
		boolean bisUpdate=iDaoFrame.updateVisitPlanStateForOutTimeState(cEntityVisitPlan);
		System.out.println(bisUpdate);
		
	}

	//���ǰһ������ (��Ϊ�������賿�жϵ�������Ϊ���������ڵ�)
	private static String getYesterdayPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result=sFormat.format(resultDate);
		return result;
		
	}
}
