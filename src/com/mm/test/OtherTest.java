package com.mm.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyConstant;

public class OtherTest {
	private static String str="";
	private static int colortag=0;
	public static void main(String[] args) {
		// CEntityVisitPlan cEntityVisitPlan=new
		// CEntityVisitPlan.Builder().VisitPlanStartTime("2015/12/22").VisitPlanCycleType(MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_WEEK).VisitPlanCycleNumber(3).VisitPlanDays(3).build();
		// System.out.println(getCycleStartTime(cEntityVisitPlan));
		// System.out.println(getCycleEndTime("2015/10/13", 308));

		// System.out.println(getNewPubdate());

		// CEntityVisitPlan cEntityVisitPlan=null;
		// JSONObject jsonObject=new
		// CEntityVisitPlan.BuildJsonObject().ToSingle(cEntityVisitPlan).build();
		// System.out.println(jsonObject);
		// System.out.println(getNewPubdate());
		
//		String date="16:39";
//		System.out.println(compareSignouttime(date));
//		System.out.println(colortag+"---"+str);
		
//		int num=(int)(Math.random()*11);
//		System.out.println(num);
//		int num2=(int)(Math.random()*8);
//		System.out.println(num2);
		
		
//		int j=0;
//		for(int i=0;i<100;i++){
//			j=j++;
//		}
//		System.out.println(j);
//		Short m=99S;
//		int i=021;
//		System.out.println(i);
		
		
		
	}
	

	

	private static String getNewPubdate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date resultDate = calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result = sFormat.format(resultDate);
		return result;

	}

	private static String getCycleEndTime(String startTime, int planDays) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = sFormat.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, planDays);
		Date resultDate = calendar.getTime();
		String result = sFormat.format(resultDate);
		return result;

	}

	private static String getCycleStartTime(CEntityVisitPlan cEntityVisitPlan) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");

		String m_sOldStartTime = cEntityVisitPlan.getM_sVisitPlanStartTime();
		Date m_date = null;
		int weekdays = 7;
		try {
			m_date = sFormat.parse(m_sOldStartTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(m_date);
		switch (cEntityVisitPlan.getM_iVisitPlanCycleType()) {
		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_DAY:
			calendar.add(Calendar.DAY_OF_MONTH, cEntityVisitPlan
					.getM_iVisitPlanCycleNumber());
			break;
		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_WEEK:
			calendar.add(Calendar.DAY_OF_MONTH, weekdays
					+ cEntityVisitPlan.getM_iVisitPlanCycleNumber()
					- calendar.get(Calendar.DAY_OF_WEEK));
			break;
		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_MONTH:
			calendar.set(Calendar.DAY_OF_MONTH, cEntityVisitPlan
					.getM_iVisitPlanCycleNumber());
			calendar.set(Calendar.MONTH, m_date.getMonth() + 1);
			break;

		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_USERDEFINED:
			calendar.add(Calendar.DAY_OF_MONTH, cEntityVisitPlan
					.getM_iVisitPlanCycleNumber());
			break;
		default:
			break;
		}
		Date m_resultDate = calendar.getTime();
		String result = sFormat.format(m_resultDate);
		return result;
	}

}
