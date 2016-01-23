package com.mm.bll.clock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityAttendance;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.tool.MySpring;

/**
 * 功能：每日凌晨为所有员工生成考勤表
 */
@Component("cBllAttendanceClock")
public class CBllAttendanceClock extends QuartzJobBean{

	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		MySpring context=MySpring.getInstance();
		IDaoFrame iDaoFrame=(IDaoFrame)context.getContext().getBean("cDaoFrameImpl");
		System.out.println("attendanceclock=="+new Date());
		//获取所有未删员工的员工号
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployee();
		String m_date=getNewPubdate();
		List<CEntityAttendance> cEntityAttendances=new ArrayList<CEntityAttendance>();
		for(int i=0;i<cEntityEmployeeArray.getSize();i++){
			CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceDate(m_date).build();
			cEntityAttendance.setcEntityEmployee(cEntityEmployeeArray.getItem(i));
			cEntityAttendances.add(cEntityAttendance);
		}
		CEntityAttendanceArray cEntityAttendanceArray=new CEntityAttendanceArray(cEntityAttendances);
		iDaoFrame.saveAttendances(cEntityAttendanceArray);
		
	
	}
 
	
	//获得当日日期，仅在本类中使用
	private static String getNewPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result=sFormat.format(resultDate);
		return result;
		
	}
}
