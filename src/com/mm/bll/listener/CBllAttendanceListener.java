package com.mm.bll.listener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityAttendance;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.tool.MySpring;

public class CBllAttendanceListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		MySpring context=MySpring.getInstance();
		IDaoFrame iDaoFrame=(IDaoFrame)context.getContext().getBean("cDaoFrameImpl");
		System.out.println("attendanceclock=="+new Date());
		//��ȡ����δɾԱ����Ա����
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployee();
		String m_date=getNewPubdate();
		
		
//		//��ȡ���յĿ��ڼ�¼
		String date=iDaoFrame.queryLastAttendance();
		
		List<CEntityAttendance> cEntityAttendances=new ArrayList<CEntityAttendance>();
		if(!date.equals(getNewPubdate())){
			for(int i=0;i<cEntityEmployeeArray.getSize();i++){
				//�������δ��¼�������ӽ�ȥ
					CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceDate(m_date).build();
					cEntityAttendance.setcEntityEmployee(cEntityEmployeeArray.getItem(i));
					cEntityAttendances.add(cEntityAttendance);
				
			}
			CEntityAttendanceArray cEntityAttendanceArray=new CEntityAttendanceArray(cEntityAttendances);
			iDaoFrame.saveAttendances(cEntityAttendanceArray);
		}
		
		
	}

 
	
	//��õ������ڣ����ڱ�����ʹ��
	private static String getNewPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result=sFormat.format(resultDate);
		return result;
		
	}
}
