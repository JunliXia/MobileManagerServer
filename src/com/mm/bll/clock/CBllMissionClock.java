package com.mm.bll.clock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityMission;
import com.mm.tool.MySpring;

@Component("cBllMissionClock")
public class CBllMissionClock extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		MySpring context=MySpring.getInstance();
		IDaoFrame iDaoFrame=(IDaoFrame)context.getContext().getBean("cDaoFrameImpl");
		System.out.println("cBllMissionClock=="+new Date());
		
		CEntityMission cEntityMission=new CEntityMission.Builder().MissionDeadline(getYesterdayPubdate()).build();
		
		boolean bisUpdate=iDaoFrame.updateMissionStateForOutTimeState(cEntityMission);
		System.out.println(bisUpdate);
		
	
	}

	//获得前一日日期 (因为现在是凌晨判断的是昨天为期限所过期的)
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
