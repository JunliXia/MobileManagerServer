package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityBussinessArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoBussiness")
public class CDaoBussiness extends SuperDAO {

	/**
	 * 序号：bussiness:1 功能：增加一条出差
	 * 参数：cEntityEmployee(EmployeeId),cEntityBussiness(本表字段) 返回值:boolean
	 */
	public boolean saveBussiness(CEntityEmployee cEntityEmployee,
			CEntityBussiness cEntityBussiness) {
		CEntityEmployee findResult = (CEntityEmployee) this
				.getHibernateTemplate().get(CEntityEmployee.class,
						cEntityEmployee.getM_iEmployeeId());

		boolean bisSave = false;

		try {
			this.getHibernateTemplate().save(cEntityBussiness);
			findResult.getcEntityBussinesses().add(cEntityBussiness);
			this.getHibernateTemplate().update(findResult);
			bisSave = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisSave;
	}

	/**
	 * 序号：bussiness:2 功能：按员工号获得所有的出差(本表字段) 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityBussinessArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityBussinessArray queryAllBussinessByEmployeeId(
			CEntityEmployee cEntityEmployee) {
		String hql = "select new CEntityBussiness(bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent,bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime,bussiness.m_iBussinessState) from com.mm.entity.CEntityBussiness as bussiness where EmployeeId=? order by bussiness.m_sBussinessReturnTime desc";
		List<?> findResult = this.getHibernateTemplate().find(hql,
				cEntityEmployee.getM_iEmployeeId());
		CEntityBussinessArray cEntityBussinessArray = new CEntityBussinessArray(
				(List<CEntityBussiness>) findResult);
		return cEntityBussinessArray;
	}

	/**
	 * 序号：bussiness:3 功能：出差登记修改登记时间并将出差状态修改为执行中
	 * 参数：cEntityBussiness(BussinessId,BussinessRegisterTime) 返回值:boolean
	 */
	public boolean updateBussinessRegisterTime(CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessRegisterTime=? , BussinessState=? where BussinessId=? ";

		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							cEntityBussiness.getM_sBussinessRegisterTime(),
							MyConstant.Bussiness.BUSSINESS_UNDERWAY,
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * 序号：bussiness:4 功能：抵达目的地登记修改抵达地址与时间
	 * 参数：cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * 返回值:boolean
	 */
	public boolean updateBussinessInTimeAndInAddress(
			CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessInAddress=?,BussinessInTime=? where BussinessId=?";
		System.out.println(hql);
		System.out.println(cEntityBussiness.toString());
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] { cEntityBussiness.getM_sBussinessInAddress(),
							cEntityBussiness.getM_sBussinessInTime(),
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * 序号：bussiness:5 功能：离开目的地登记修改离开地址与时间
	 * 参数：cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * 返回值:boolean
	 */
	public boolean updateBussinessOutTimeAndOutAddress(
			CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessOutAddress=?,BussinessOutTime=? where BussinessId=?";

		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							cEntityBussiness.getM_sBussinessOutAddress(),
							cEntityBussiness.getM_sBussinessOutTime(),
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}

	/**
	 * 序号：bussiness:6 功能：出差归来登记时间并修改出差状态为未审核
	 * 参数：cEntityBussiness(BussinessId,BussinessReturnTime) 返回值:boolean
	 */
	public boolean updateBussinessReturn(CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessReturnTime=?,BussinessState=? where BussinessId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							cEntityBussiness.getM_sBussinessReturnTime(),
							MyConstant.Bussiness.BUSSINESS_WAITCHECK,
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * 序号：bussiness:7 
	 * 功能：按出差号修改出差状态 
	 * 参数：cEntityBussiness(BussinessId) 
	 * 返回值:boolean
	 */
	public boolean updateBussinessStateByBussinessId(
			CEntityBussiness cEntityBussiness, int OperateState) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessState=? where BussinessId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] { OperateState,
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;

	}

	
	
	/**
	 * 序号：bussiness:8
	 * 功能：按员工号获取运行态的出差(未登记,执行中)
	 * 参数：cEntityEmployee(EmployeeId) 
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql = "select new CEntityBussiness(bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent,bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime,bussiness.m_iBussinessState) from com.mm.entity.CEntityBussiness as bussiness where EmployeeId=? and BussinessState in(?,?)";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY});
		Iterator<?> iterator = findResult.listIterator();
		CEntityBussiness result = null;
		if (iterator.hasNext()) {
			result = (CEntityBussiness) iterator.next();
		}
		return result; 
		
	}
	/**
	 * 序号：bussiness:9
	 * 功能：按出差号获得出差详情(本表字段)
	 * 参数：CEntityBussiness(BussinessId) 
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessByBussinessId(CEntityBussiness cEntityBussiness){
		String hql = "select new CEntityBussiness(bussiness.BussinessId, bussiness.BussinessSideAddress,bussiness.BussinessContent,bussiness.BussinessRegisterTime,bussiness.BussinessInAddress, bussiness.BussinessInTime,bussiness.BussinessOutAddress, bussiness.BussinessOutTime,bussiness.BussinessReturnTime,bussiness.BussinessState) from com.mm.entity.CEntityBussiness as bussiness where BussinessId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityBussiness.getM_iBussinessId());
		Iterator<?> iterator = findResult.listIterator();
		CEntityBussiness result = null;
		if (iterator.hasNext()) {
			result = (CEntityBussiness) iterator.next();
		}
		return result; 
	}
		
	
	
	/**
	 * 序号：bussiness:10
	 * 功能：获取所有未删员工的执行中出差信息(未登记，执行中)
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessRuning(){
		String hql = "select bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent," +
				"bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, " +
				"bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, " +
				"bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime," +
				"bussiness.m_iBussinessState,employee.m_iEmployeeId," +
				"employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityBussiness as bussiness,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussiness.m_iBussinessId and bussiness.m_iBussinessState in(?,?) and employee.m_iEmployeeType!=?";
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY,MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder()
		    .BussinessId((Integer)tuple[0])
		    .BussinessSideAddress((String)tuple[1])
		    .BussinessContent((String)tuple[2])
		    .BussinessRegisterTime((String)tuple[3])
		    .BussinessInAddress((String)tuple[4])
		    .BussinessInTime((String)tuple[5])
		    .BussinessOutAddress((String)tuple[6])
		    .BussinessOutTime((String)tuple[7])
		    .BussinessReturnTime((String)tuple[8])
		    .BussinessState((Integer)tuple[9])
		    .build();
		    
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[10]).EmployeeAccount((String)tuple[11]).EmployeeName((String)tuple[12]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	/**
	 * 序号：bussiness:11
	 * 功能：获取可增出差的员工信息（即员工没有未登记，执行中，未审核的出差）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryBussinessAddOkEmployeeInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeeName," +
				"sum(case when employee.m_iEmployeeId=bussiness.cEntityEmployee.m_iEmployeeId " +
				"and bb.m_iBussinessId=bussiness.m_iBussinessId " +
				"and bussiness.m_iBussinessState not in(?,?,?) then 1 else 0 end), " +
				"sum(case when employee.m_iEmployeeId=bussiness.cEntityEmployee.m_iEmployeeId " +
				"and bb.m_iBussinessId=bussiness.m_iBussinessId " +
				"and bussiness.m_iBussinessState  in(?,?,?) then 1 else 0 end)  " +
				"from com.mm.entity.CEntityBussiness as bussiness ," +
				"com.mm.entity.CEntityEmployee as employee "+
				"left join employee.cEntityBussinesses as bb "+
				"group by employee.m_iEmployeeId" ;
//				"or ( employee.m_iEmployeeId not in" +
//				"(select bussiness.cEntityEmployee.m_iEmployeeId from com.mm.entity.CEntityBussiness as bussiness where employee.m_iEmployeeId=bussiness.cEntityEmployee.m_iEmployeeId and bussiness.m_iBussinessState not in(?,?,?)))" +
//				"and employee.m_iEmployeeType!=? " ;
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY,MyConstant.Bussiness.BUSSINESS_WAITCHECK,MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY,MyConstant.Bussiness.BUSSINESS_WAITCHECK});
		Iterator it=findResult.iterator();
		while (it.hasNext()) {       
			Object[] tuple = (Object[]) it.next();
			JSONObject outjson=new JSONObject();
			outjson.put(MyOpcode.Employee.EmployeeId, (Integer)tuple[0]);
			outjson.put(MyOpcode.Employee.EmployeeAccount, (String)tuple[1]);
			outjson.put(MyOpcode.Employee.EmployeeName,  (String)tuple[2]);
			
//			System.out.println((Integer)tuple[0]+"--"+ (String)tuple[1]+"--"+(String)tuple[2]+"--"+(Long)tuple[3]+"---"+(Long)tuple[4]);
			
//			jsonArray.add(outjson);
		}   
		return findResult;
	}
	
	
	/**
	 * 序号：bussiness:12
	 * 功能：获取所有未删员工的待处理出差信息(未审核)
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessWaitDeal(){
		String hql = "select bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent," +
				"bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, " +
				"bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, " +
				"bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime," +
				"bussiness.m_iBussinessState,employee.m_iEmployeeId," +
				"employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityBussiness as bussiness,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussiness.m_iBussinessId and bussiness.m_iBussinessState=? and employee.m_iEmployeeType!=?";
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_WAITCHECK,MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder()
		    .BussinessId((Integer)tuple[0])
		    .BussinessSideAddress((String)tuple[1])
		    .BussinessContent((String)tuple[2])
		    .BussinessRegisterTime((String)tuple[3])
		    .BussinessInAddress((String)tuple[4])
		    .BussinessInTime((String)tuple[5])
		    .BussinessOutAddress((String)tuple[6])
		    .BussinessOutTime((String)tuple[7])
		    .BussinessReturnTime((String)tuple[8])
		    .BussinessState((Integer)tuple[9])
		    .build();
		    
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[10]).EmployeeAccount((String)tuple[11]).EmployeeName((String)tuple[12]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	
	/**
	 * 序号：bussiness:13
	 * 功能：.获取所有未删员工的已结束出差信息（审核通过，审核不通过）
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessComplete(){
		String hql = "select bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent," +
				"bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, " +
				"bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, " +
				"bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime," +
				"bussiness.m_iBussinessState,employee.m_iEmployeeId," +
				"employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityBussiness as bussiness,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussiness.m_iBussinessId and bussiness.m_iBussinessState in(?,?) and employee.m_iEmployeeType!=?";
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_CHECKPASS,MyConstant.Bussiness.BUSSINESS_CHECKNOPASS,MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder()
		    .BussinessId((Integer)tuple[0])
		    .BussinessSideAddress((String)tuple[1])
		    .BussinessContent((String)tuple[2])
		    .BussinessRegisterTime((String)tuple[3])
		    .BussinessInAddress((String)tuple[4])
		    .BussinessInTime((String)tuple[5])
		    .BussinessOutAddress((String)tuple[6])
		    .BussinessOutTime((String)tuple[7])
		    .BussinessReturnTime((String)tuple[8])
		    .BussinessState((Integer)tuple[9])
		    .build();
		    
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[10]).EmployeeAccount((String)tuple[11]).EmployeeName((String)tuple[12]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	
}
