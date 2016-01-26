package com.mm.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityAddress;
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;

@Component("cDaoAddress")
public class CDaoAddress extends SuperDAO{
	
	
	/**
	 * 序号：address:1
	 * 功能:增加地址
	 * 参数：cEntityAddress(所有字段)
	 * 返回值:boolean
	 */
	public boolean saveAddress(CEntityAddress cEntityAddress){
		cEntityAddress.setAddressTime(getNewPubdate());
		boolean bisSave=false;
		try {
			this.getHibernateTemplate().save(cEntityAddress);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}
	
	/**
	 * 序号：address:2
	 * 功能: 按员工号及查询天数获取地址
	 * 参数：cEntityEmployee(EmployeeId),days
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAddressEmployeeInfoWithTime(CEntityEmployee cEntityEmployee,int days){
		String queryNowTime=getNewPubdate();
		String queryStartTime=getQueryStartTime(queryNowTime, days);
		String hql="select address.addressId,address.addressLong,address.addressLat,address.addressTime from com.mm.entity.CEntityAddress as address,com.mm.entity.CEntityEmployee as employee where address.employeeId=employee.m_iEmployeeId and employee.m_iEmployeeId=? and address.addressTime between '"+queryStartTime+"' and '"+queryNowTime+"'  order by address.addressTime desc";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId()});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityAddress cEntityAddress=new CEntityAddress((Integer)tuple[0], (String)tuple[1], (String)tuple[2], (String)tuple[3]);
//		    CEntityEmployee ccEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[4]).EmployeeAccount((String)tuple[5]).EmployeeName((String)tuple[6]).build();
//		    System.out.println(cEntityAddress.toString());
//		    map.put(MyOpcode.Employee.Employee, ccEmployee);
		    map.put(MyOpcode.Address.Address, cEntityAddress);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	
//	public static void main(String[] args) {
//	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//	"applicationContext.xml");
//	CDaoAddress tt = (CDaoAddress) ctx.getBean("cDaoAddress");
//	CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(2).build();
//	LinkedMap findResult=tt.queryAddressEmployeeInfoByWeekTime(cEntityEmployee, 1);
//}
	
	
	
	
	
	//获得当日日期，仅在本类中使用
	private static String getNewPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String result=sFormat.format(resultDate);
		return result;
		
	}

	
	private static String getQueryStartTime(String startTime,int planDays){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date=null;
		try {
			date=sFormat.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,-planDays);
		Date resultDate=calendar.getTime();
		String result=sFormat.format(resultDate);
		return result;
		
	}
	
	
	
	
	
	
}
