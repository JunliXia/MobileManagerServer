package com.mm.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoVisitPlan")
public class CDaoVisitPlan extends SuperDAO {

	/**
	 * 序号：visitplan:1 功能：增加拜访计划
	 * 参数：cEntityClient(ClientId),cEntityEmployee(EmployeeId
	 * ),cEntityVisitPlan(本表字段) 返回值:boolean
	 */
	public boolean saveVisitPlan(CEntityClient cEntityClient,
			CEntityEmployee cEntityEmployee, CEntityVisitPlan cEntityVisitPlan) {
//		CEntityEmployee findEmployee = (CEntityEmployee) this
//				.getHibernateTemplate().get(CEntityEmployee.class,
//						cEntityEmployee.getM_iEmployeeId());
//		CEntityClient findClient = (CEntityClient) this.getHibernateTemplate()
//				.get(CEntityClient.class, cEntityClient.getM_iClientId());
//		cEntityVisitPlan.setcEntityClient(findClient);
		boolean bisSave = false;
//		cEntityVisitPlan.setM_iEmployeeId(cEntityEmployee.getM_iEmployeeId());
//		cEntityVisitPlan.setM

		try {
			Serializable visid= this.getHibernateTemplate().save(cEntityVisitPlan);
//			findEmployee.getcEntityVisitPlans().add(cEntityVisitPlan);
//			this.getHibernateTemplate().update(findEmployee);
			String hql="update com.mm.entity.CEntityVisitPlan as visitplan set EmployeeId=?,ClientId=? where VisitPlanId=?";
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),cEntityClient.getM_iClientId(),visid});
			bisSave = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}

	/**
	 * 序号：visitplan:2 功能：按员工号获得所有的拜访计划 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeId(
			CEntityEmployee cEntityEmployee) {
		String hql = "from com.mm.entity.CEntityVisitPlan as visitplan where EmployeeId=?";
		List<?> findResult = this.getHibernateTemplate().find(hql,
				cEntityEmployee.getM_iEmployeeId());
		CEntityVisitPlanArray cEntityVisitPlanArray = new CEntityVisitPlanArray(
				(List<CEntityVisitPlan>) findResult);

		return cEntityVisitPlanArray;
	}

	/**
	 * 序号：visitplan:3 功能：按拜访计划号修改拜访状态 参数：cEntityVisitPlan(VisitPlanId)
	 * OperateState(MyConstant.VisitPlan.*) 返回值:CEntityVisitPlanArray
	 */
	public boolean updateVisitPlanStateByVisitPlanId(
			CEntityVisitPlan cEntityVisitPlan, int OperateState) {
		String hql = "update com.mm.entity.CEntityVisitPlan as visitplan set VisitPlanState=? where VisitPlanId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] { OperateState,
							cEntityVisitPlan.getM_iVisitPlanId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;

	}

	/**
	 * 序号：visitplan:4
	 * 功能：按拜访计划号获得拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId)
	 * 返回值:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanByVisitPlanId(
			int VisitPlanId) {
		CEntityVisitPlan findResult = (CEntityVisitPlan) this
				.getHibernateTemplate().get(CEntityVisitPlan.class,
						VisitPlanId);
		return findResult;
	}

	/**
	 * 序号：visitplan:5 功能：修改拜访计划的时间并将状态改为未开始
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanStartTime
	 * ,VisitPlanEndTime,VisitPlanPubdate) 返回值:boolean
	 */
	public boolean updateVisitTime(CEntityVisitPlan cEntityVisitPlan) {
		String hql = "update com.mm.entity.CEntityVisitPlan as visitplan set VisitPlanStartTime=?,VisitPlanEndTime=?,VisitPlanPubdate=?,VisitPlanState=? where VisitPlanId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] { cEntityVisitPlan.getM_sVisitPlanStartTime(),
							cEntityVisitPlan.getM_sVisitPlanEndTime(),
							cEntityVisitPlan.getM_sVisitPlanPubdate(),
							MyConstant.VisitPlan.VISITPLAN_NOTSTART,
							cEntityVisitPlan.getM_iVisitPlanId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * 序号：visitplan:6 功能：按拜访计划号修改拜访循环（拜访循环，循环类型，循环数，拜访限定天数）
	 * 参数：cEntityVisitPlan(VisitPlanId
	 * ,VisitPlanCycle,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * 返回值:boolean
	 */
	public boolean updateVisitPlanCyclesByVisitPlanId(
			CEntityVisitPlan cEntityVisitPlan) {
		String hql = "update com.mm.entity.CEntityVisitPlan as visitplan set VisitPlanCycle=?,VisitPlanCycleType=?,VisitPlanCycleNumber=?,VisitPlanDays=? where VisitPlanId=?";

		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							MyConstant.VisitPlanCycle.VISITPLANCYCLE_CYCLE,
							cEntityVisitPlan.getM_iVisitPlanCycleType(),
							cEntityVisitPlan.getM_iVisitPlanCycleNumber(),
							cEntityVisitPlan.getM_iVisitPlanDays(),
							cEntityVisitPlan.getM_iVisitPlanId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * 序号：visitplan:7 功能：按拜访计划号修改拜访期限并将状态改为执行中
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanEndTime) 返回值:boolean
	 */
	public boolean updateVisitPlanEndTime(CEntityVisitPlan cEntityVisitPlan) {
		String hql = "update com.mm.entity.CEntityVisitPlan as visitplan set VisitPlanEndTime=?,VisitPlanState=? where VisitPlanId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] { cEntityVisitPlan.getM_sVisitPlanEndTime(),
							MyConstant.VisitPlan.VISITPLAN_UNDERWAY,
							cEntityVisitPlan.getM_iVisitPlanId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}

	/**
	 * 序号：visitplan:8 功能：按客户号查询出运行态的拜访计划（运行态为未开始，执行中，未审核，已过期）
	 * 参数：cEntityClient(ClientId) 返回值:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanRunningStateByClientId(
			CEntityClient cEntityClient) {
		String hql = "select new CEntityVisitPlan(visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState) from com.mm.entity.CEntityVisitPlan as visitplan where ClientId=? and VisitPlanState in(?,?,?,?)";
		List<?> findResult = this.getHibernateTemplate().find(
				hql,
				new Object[] { cEntityClient.getM_iClientId(),
						MyConstant.VisitPlan.VISITPLAN_NOTSTART,
						MyConstant.VisitPlan.VISITPLAN_UNDERWAY,
						MyConstant.VisitPlan.VISITPLAN_WAITCHECK,
						MyConstant.VisitPlan.VISITPLAN_OUTTIME });
		Iterator<?> iterator = findResult.listIterator();
		CEntityVisitPlan result = null;
		if (iterator.hasNext()) {

			result = (CEntityVisitPlan) iterator.next();
		}
		return result;

	}

	/**
	 * 序号：visitplan:9 
	 * 功能：按员工号以及拜访状态获得所有拜访计划 
	 * 参数：cEntityEmployee(EmployeeId)
	 * OperateState(MyConstant.VisitPlan.*) 
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeIdAndVisitPlanState(
			CEntityEmployee cEntityEmployee, int OperateState) {
		String hql = "from com.mm.entity.CEntityVisitPlan as visitplan where EmployeeId=? and VisitPlanState=?";
		List<?> findResult = this.getHibernateTemplate()
				.find(
						hql,
						new Object[] { cEntityEmployee.getM_iEmployeeId(),
								OperateState });
		CEntityVisitPlanArray cEntityVisitPlanArray = new CEntityVisitPlanArray(
				(List<CEntityVisitPlan>) findResult);
		return cEntityVisitPlanArray;

	}

	
	/**
	 * 序号：visitplan:10 
	 * 功能：按员工号获取运行态的拜访计划(执行中,已过期)  (本表字段)
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryAllVisitPlanRunningStateByEmployeeId(
			CEntityEmployee cEntityEmployee) {
		String hql = "from com.mm.entity.CEntityVisitPlan as visitplan where EmployeeId=? and VisitPlanState in(?,?)";
		List<?> findResult = this.getHibernateTemplate()
				.find(
						hql,
						new Object[] { cEntityEmployee.getM_iEmployeeId(),
								MyConstant.VisitPlan.VISITPLAN_UNDERWAY,MyConstant.VisitPlan.VISITPLAN_OUTTIME });
		CEntityVisitPlanArray cEntityVisitPlanArray = new CEntityVisitPlanArray(
				(List<CEntityVisitPlan>) findResult);
		return cEntityVisitPlanArray;

	}
	/**
	 * 序号：visitplan:11
	 * 功能：按员工号获取终止态的拜访计划（未审核,已审核,已撤销,已失败）（本表字段）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryAllVisitPlanCompleteByEmployeeIs(CEntityEmployee cEntityEmployee){
		String hql = "from com.mm.entity.CEntityVisitPlan as visitplan where EmployeeId=? and VisitPlanState in(?,?,?,?)";
		List<?> findResult = this.getHibernateTemplate()
				.find(
						hql,
						new Object[] { cEntityEmployee.getM_iEmployeeId(),
								MyConstant.VisitPlan.VISITPLAN_WAITCHECK,MyConstant.VisitPlan.VISITPLAN_CHECK,MyConstant.VisitPlan.VISITPLAN_FAILURE,MyConstant.VisitPlan.VISITPLAN_UNDO });
		CEntityVisitPlanArray cEntityVisitPlanArray = new CEntityVisitPlanArray(
				(List<CEntityVisitPlan>) findResult);
		return cEntityVisitPlanArray;

	}
	
	
	
	/**
	 * 序号：visitplan:12
	 * 功能：按员工号获取其所有的拜访计划含客户信息
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:HashedMap
	 */
	@SuppressWarnings("unchecked")
	public HashedMap queryAllVisitPlanAndClientInfoByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState,client.m_iClientId,client.m_sClientName from com.mm.entity.CEntityVisitPlan as visitplan ,com.mm.entity.CEntityClient as client ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityVisitPlans as bb where employee.m_iEmployeeId=?  and visitplan.cEntityClient.m_iClientId=client.m_iClientId and bb.m_iVisitPlanId=visitplan.m_iVisitPlanId  order by visitplan.m_sVisitPlanStartTime desc";
		List findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		Iterator it = findResult.iterator();  
		HashedMap maps=new HashedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    HashedMap map=new HashedMap();
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[0]).VisitPlanPubdate((String)tuple[1]).VisitPlanStartTime((String)tuple[2]).
		    VisitPlanEndTime((String)tuple[3]).VisitPlanState((Integer)tuple[4]).VisitPlanCycle((Integer)tuple[5]).VisitPlanCycleType((Integer)tuple[6]).VisitPlanCycleNumber((Integer)tuple[7]).VisitPlanDays((Integer)tuple[8]).VisitBussinessBandState((Integer)tuple[9]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[10]).ClientName((String)tuple[11]).build();
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    map.put(MyOpcode.Client.Client,cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    
			 

		 return maps;
	
	}
	/**
	 * 序号：visitplan:13
	 * 功能：按员工号获得其拜访计划（含客户与拜访总结）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryAllVisitPlanWithClientAndVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityVisitPlan as visitplan where EmployeeId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityVisitPlanArray cEntityVisitPlanArray=new CEntityVisitPlanArray((List<CEntityVisitPlan>) findResult);
		return cEntityVisitPlanArray;
	}

	
	/**
	 * 序号：visitplan:14
	 * 功能：按出差号将拜访计划撤销并创建拜访撤销记录
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean updateVisitPlanUndoWithSaveVisitUndoByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="update com.mm.entity.CEntityVisitPlan as visitplan set visitplan.m_iVisitPlanState=? where visitplan.m_iVisitPlanId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=?)";
		boolean bisUpdate=false;
		String hqlquery="select visitplan.m_iVisitPlanId ,visitplan.m_iVisitPlanState from com.mm.entity.CEntityVisitPlan as visitplan where visitplan.m_iVisitPlanId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=?)";
		List listInfo=this.getHibernateTemplate().find(hqlquery,new Object[]{MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE,cEntityBussiness.getM_iBussinessId()});
	
		Iterator it = listInfo.iterator();  
		while (it.hasNext()) {       
			Object[] tuple = (Object[]) it.next();
			CEntityVisitUndo cEntityVisitUndo=new CEntityVisitUndo.Builder().VisitUndoReason(MyConstant.Reason.REASON_UNDOBUSSINESS).VisitUndoTime(getNewPubdate()).VisitUndoRecallType((Integer)tuple[1]).build();
			Serializable undoid=this.getHibernateTemplate().save(cEntityVisitUndo);
			String updatehql="update com.mm.entity.CEntityVisitUndo set VisitPlanId=? where VisitUndoId=?";
			this.getHibernateTemplate().bulkUpdate(updatehql,new Object[]{(Integer)tuple[0],undoid});
		 }   
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{MyConstant.VisitPlan.VISITPLAN_UNDO,MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE,cEntityBussiness.getM_iBussinessId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
		
	}
	
	/**
	 * 序号：visitplan:15
	 * 功能：按出差号将拜访计划出差绑定状态解绑
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateVisitPlanBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="update com.mm.entity.CEntityVisitPlan as visitplan set visitplan.m_iVisitBussinessBandState=? where visitplan.m_iVisitPlanId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=?)";
		boolean bisupdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{MyConstant.VisitPlan.VISITPLAN_BUSSINESSNOBAND,MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE,cEntityBussiness.getM_iBussinessId()});
			bisupdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisupdate;
	}
	
	/**
	 * 序号：visitplan:16
	 * 功能：按出差号得到其绑定的拜访计划
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryAllVisitPlanByBussienssId(CEntityBussiness cEntityBussiness){
		String hql="select new CEntityVisitPlan(visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState)  from com.mm.entity.CEntityVisitPlan as visitplan where visitplan.m_iVisitPlanId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=? and bussinessactivity.m_iBussinessBindType=0 and visitplan.m_iVisitBussinessBandState=1)";
		List<?> findResult = this.getHibernateTemplate().find(hql,
				new Object[]{MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE,cEntityBussiness.getM_iBussinessId()});
		CEntityVisitPlanArray cEntityVisitPlanArray = new CEntityVisitPlanArray(
				(List<CEntityVisitPlan>) findResult);

		return cEntityVisitPlanArray;
	}
	
	
	/**
	 * 序号：visitplan:17
	 * 功能：获取所有员工的未开始，已执行拜访信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllVisitPlanAllRunning(){
		String hql="select  employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName, visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState,client.m_iClientId,client.m_sClientName from com.mm.entity.CEntityVisitPlan as visitplan ,com.mm.entity.CEntityClient as client ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityVisitPlans as bb  where visitplan.cEntityClient.m_iClientId=client.m_iClientId and bb.m_iVisitPlanId=visitplan.m_iVisitPlanId and visitplan.m_iVisitPlanState in(?,?)  order by visitplan.m_sVisitPlanStartTime desc";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.VisitPlan.VISITPLAN_NOTSTART,MyConstant.VisitPlan.VISITPLAN_UNDERWAY});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[3]).VisitPlanPubdate((String)tuple[4]).VisitPlanStartTime((String)tuple[5]).VisitPlanEndTime((String)tuple[6]).VisitPlanState((Integer)tuple[7]).VisitPlanCycle((Integer)tuple[8]).VisitPlanCycleType((Integer)tuple[9]).VisitPlanCycleNumber((Integer)tuple[10]).VisitPlanDays((Integer)tuple[11]).VisitBussinessBandState((Integer)tuple[12]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[13]).ClientName((String)tuple[14]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
		
		
	}
	
	/**
	 * 序号：visitplan:18
	 * 功能：获取所有员工的未审核，已过期拜访信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllVisitPlanAllWaitDeal(){
		String hql="select  employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName, visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState,client.m_iClientId,client.m_sClientName from com.mm.entity.CEntityVisitPlan as visitplan ,com.mm.entity.CEntityClient as client ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityVisitPlans as bb  where visitplan.cEntityClient.m_iClientId=client.m_iClientId and bb.m_iVisitPlanId=visitplan.m_iVisitPlanId and visitplan.m_iVisitPlanState in(?,?)  order by visitplan.m_sVisitPlanStartTime desc";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.VisitPlan.VISITPLAN_WAITCHECK,MyConstant.VisitPlan.VISITPLAN_OUTTIME});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[3]).VisitPlanPubdate((String)tuple[4]).VisitPlanStartTime((String)tuple[5]).VisitPlanEndTime((String)tuple[6]).VisitPlanState((Integer)tuple[7]).VisitPlanCycle((Integer)tuple[8]).VisitPlanCycleType((Integer)tuple[9]).VisitPlanCycleNumber((Integer)tuple[10]).VisitPlanDays((Integer)tuple[11]).VisitBussinessBandState((Integer)tuple[12]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[13]).ClientName((String)tuple[14]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
		
		
	}
	/**
	 * 序号：visitplan:19
	 * 功能：获取所有员工的已审核，已失败拜访信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllVisitPlanComplete(){
		String hql="select  employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName, visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState,client.m_iClientId,client.m_sClientName from com.mm.entity.CEntityVisitPlan as visitplan ,com.mm.entity.CEntityClient as client ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityVisitPlans as bb  where visitplan.cEntityClient.m_iClientId=client.m_iClientId and bb.m_iVisitPlanId=visitplan.m_iVisitPlanId and visitplan.m_iVisitPlanState in(?,?)  order by visitplan.m_sVisitPlanStartTime desc";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.VisitPlan.VISITPLAN_CHECK,MyConstant.VisitPlan.VISITPLAN_FAILURE});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[3]).VisitPlanPubdate((String)tuple[4]).VisitPlanStartTime((String)tuple[5]).VisitPlanEndTime((String)tuple[6]).VisitPlanState((Integer)tuple[7]).VisitPlanCycle((Integer)tuple[8]).VisitPlanCycleType((Integer)tuple[9]).VisitPlanCycleNumber((Integer)tuple[10]).VisitPlanDays((Integer)tuple[11]).VisitBussinessBandState((Integer)tuple[12]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[13]).ClientName((String)tuple[14]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
		
	}
	
	/**
	 * 序号：visitplan:20
	 * 功能：获取所有未删员工的未审核，已过期的拜访数量
	 * 参数：
	 * 返回值:int
	 */
	public int queryVisitPlanWaitDealNumber(){
		String hql="select count(*) from CEntityVisitPlan where m_iVisitPlanState in(?,?)";
		int count=((Long)this.getHibernateTemplate().find(hql,new Object[]{MyConstant.VisitPlan.VISITPLAN_OUTTIME,MyConstant.VisitPlan.VISITPLAN_WAITCHECK}).listIterator().next()).intValue();
		return count;
	}
	
	/**
	 * 序号：visitplan:21
	 * 功能：根据拜访计划号修改出差绑定状态
	 * 参数：CEntityVisitPlan(VisitPlanId,VisitPlanBussinessBindType)
	 * 返回值:boolean
	 */
	public boolean updateVisitBindType(CEntityVisitPlan cEntityVisitPlan){
		String hql = "update com.mm.entity.CEntityVisitPlan as visitplan set VisitBussinessBandState=? where VisitPlanId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							cEntityVisitPlan.getM_iVisitBussinessBandState(),
							cEntityVisitPlan.getM_iVisitPlanId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}
	
	
	/**
	 * 序号：visitplan:22
	 * 功能：根据员工号获取其绑定的拜访计划信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryVisitPlanBindByEmployee(CEntityEmployee cEntityEmployee){
		String hql="select new CEntityVisitPlan(visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState)  from com.mm.entity.CEntityVisitPlan as visitplan where visitplan.m_iVisitBussinessBandState=? and EmployeeId=?";
		List<?> findResult = this.getHibernateTemplate().find(hql,
				new Object[]{MyConstant.VisitPlan.VISITPLAN_BUSSINESSBAND,cEntityEmployee.getM_iEmployeeId()});
		CEntityVisitPlanArray cEntityVisitPlanArray = new CEntityVisitPlanArray(
				(List<CEntityVisitPlan>) findResult);

		return cEntityVisitPlanArray;
	}
	
	/**
	 * 序号：visitplan:23
	 * 功能：根据员工号获取其绑定的拜访计划信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitPlanArray queryVisitPlanUnBindByEmployee(CEntityEmployee cEntityEmployee){
		String hql="select new CEntityVisitPlan(visitplan.m_iVisitPlanId, visitplan.m_sVisitPlanPubdate,visitplan.m_sVisitPlanStartTime, visitplan.m_sVisitPlanEndTime,visitplan.m_iVisitPlanState, visitplan.m_iVisitPlanCycle,visitplan.m_iVisitPlanCycleType, visitplan.m_iVisitPlanCycleNumber,visitplan.m_iVisitPlanDays, visitplan.m_iVisitBussinessBandState)  from com.mm.entity.CEntityVisitPlan as visitplan where visitplan.m_iVisitBussinessBandState=? and EmployeeId=?";
		List<?> findResult = this.getHibernateTemplate().find(hql,
				new Object[]{MyConstant.VisitPlan.VISITPLAN_BUSSINESSNOBAND,cEntityEmployee.getM_iEmployeeId()});
		CEntityVisitPlanArray cEntityVisitPlanArray = new CEntityVisitPlanArray(
				(List<CEntityVisitPlan>) findResult);

		return cEntityVisitPlanArray;
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
