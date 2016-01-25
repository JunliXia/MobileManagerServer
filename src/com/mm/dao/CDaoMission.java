package com.mm.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionUndo;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoMission")
public class CDaoMission extends SuperDAO{
	
	/**
	 * 序号：mission:1
	 * 功能：增加任务
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission) {
//		CEntityEmployee result=(CEntityEmployee) this.getHibernateTemplate().get(CEntityEmployee.class, cEntityEmployee.getM_iEmployeeId());
		
		
		boolean bisSave=false;
		try {
			Serializable id=this.getHibernateTemplate().save(cEntityMission);
//			result.getcEntityMissions().add(cEntityMission);
//			this.getHibernateTemplate().update(result);
			String hql="update com.mm.entity.CEntityMission as mission set EmployeeId=? where MissionId=?";
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),id});
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisSave;
	}
	/**
	 * 序号：mission:2
	 * 功能：修改任务状态
	 * 参数：cEntityMission(MissionId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * 返回值:boolean
	 */
	public boolean updateMissionState(CEntityMission cEntityMission,int OperateState) {
		String hql="update com.mm.entity.CEntityMission as mission set MissionState=? where MissionId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{OperateState,cEntityMission.getM_iMissionId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:3
	 * 功能：修改延期状态
	 * 参数：EntityMission(MissionId,MissionDelayState)
	 * 返回值:boolean
	 */
	public boolean updateMissionDelayState(CEntityMission cEntityMission) {
		String hql="update com.mm.entity.CEntityMission as mission set MissionDelayState=? where MissionId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityMission.getM_iMissionDelayState(),cEntityMission.getM_iMissionId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}
	
	
	/**
	 * 序号：mission:4
	 * 功能：根据员工号获得所有任务
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionByEmployeeId(CEntityEmployee cEntityEmployee) {
		
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? order by MissionPubdate desc";
		
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：mission:5
	 * 功能：按任务状态获得所有任务
	 * 参数：OperateState(MyConstant.Mission.*)
	 * 返回值:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionByMissionType(int  OperateState) {
		String hql="select new CEntityMission(mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState) from com.mm.entity.CEntityMission as mission where MissionState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,OperateState);
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	
	/**
	 * 序号：mission:6
	 * 功能：按任务号获得任务详情
	 * 参数：cEntityMission(MissionId)
	 * 返回值:CEntityMission
	 */
	public CEntityMission queryMissionByMissionId(int  MissionId){
		CEntityMission findResult=(CEntityMission)this.getHibernateTemplate().get(CEntityMission.class, MissionId);
		return findResult;
		
	}
	
	
	/**
	 * 序号：mission:7
	 * 功能：修改任务期限（修改期限就判定为已延期）
	 * 参数：cEntityMission(MissionId,MissionDeadline)
	 * 返回值:boolean
	 */
	public boolean updateMissionDeadline(CEntityMission cEntityMission){
		String hql="update com.mm.entity.CEntityMission as mission set MissionDeadline=?,MissionDelayState=? where MissionId=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityMission.getM_sMissionDeadline(),MyConstant.Mission.MISSION_DELAY,cEntityMission.getM_iMissionId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
		
	}
	
	
	/**
	 * 序号：mission:8
	 * 功能：按员工号及状态获得所有任务
	 * 参数：cEntityEmployee(EmployeeId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * 返回值:boolean
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionByEmployeeIdAndMissionState(CEntityEmployee cEntityEmployee,int OperateState){
		String hql="from com.mm.entity.CEntityMission as mission where MissionState=? and EmployeeId=? order by MissionPubdate desc";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{OperateState,cEntityEmployee.getM_iEmployeeId()});
		
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
		
	}
	
	
	/**
	 * 序号：mission:9
	 * 功能：按员工号获取终止态的任务（未审核,已审核,已撤销,已失败）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionCompleteStateByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionState in(?,?,?,?)";
		
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_NOCHECK,MyConstant.Mission.MISSION_CHECK,MyConstant.Mission.MISSION_UNDO,MyConstant.Mission.MISSION_FAILURE});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	
	
	
	
	/**
	 * 序号：mission:10
	 * 功能按员工号获取执行态的任务（执行中，已过期）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionState in(?,?)";
		
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_UNDERWAY,MyConstant.Mission.MISSION_OUTTIME});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：mission:11
	 * 功能通过当前日期修改任务状态(未接受,执行中)为已过期	
	 * 参数：CEntityMission(MissionDeadline)
	 * 返回值:boolean
	 */
	public boolean updateMissionStateForOutTimeState(CEntityMission cEntityMission){
		String hql="update com.mm.entity.CEntityMission as mission set MissionState=? where MissionState in(?,?) and MissionDeadline=?";
		
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{MyConstant.Mission.MISSION_OUTTIME,MyConstant.Mission.MISSION_WAITTAKE,MyConstant.Mission.MISSION_UNDERWAY,cEntityMission.getM_sMissionDeadline()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:12
	 * 功能:按出差号将任务出差绑定状态解绑	
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateMissionBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="update com.mm.entity.CEntityMission as mission set mission.m_iMissionBussinessBandState=? where mission.m_iMissionId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=?)";
		boolean bisupdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{MyConstant.Mission.MISSION_BUSSINESSNOBAND,MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE,cEntityBussiness.getM_iBussinessId()});
			bisupdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisupdate;
		
	}
	
	/**
	 * 序号：mission:13
	 * 功能:按出差号将任务撤销并创建任务撤销记录
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean updateMissionStateUndoWithSaveMissionUndoByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="update com.mm.entity.CEntityMission as mission set mission.m_iMissionState=? where mission.m_iMissionId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=?)";
		boolean bisUpdate=false;
		String hqlquery="select mission.m_iMissionId from com.mm.entity.CEntityMission as mission where mission.m_iMissionId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=?)";
		List listInfo=this.getHibernateTemplate().find(hqlquery,new Object[]{MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE,cEntityBussiness.getM_iBussinessId()});
		Iterator it = listInfo.iterator();  
		while (it.hasNext()) {       
			Integer tuple = (Integer) it.next();
		    CEntityMission cEntityMission=(CEntityMission) this.getHibernateTemplate().get(CEntityMission.class, (Integer)tuple);
		    CEntityMissionUndo cEntityMissionUndo=new CEntityMissionUndo.Builder().MissionUndoReason(MyConstant.Reason.REASON_UNDOBUSSINESS).MissionUndoTime(getNewPubdate()).MissionUndoRecallType(cEntityMission.getM_iMissionState()).build();
		    cEntityMissionUndo.setcEntityMission(cEntityMission);
		    this.getHibernateTemplate().save(cEntityMissionUndo);
		 }    
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{MyConstant.Mission.MISSION_UNDO,MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE,cEntityBussiness.getM_iBussinessId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:14
	 * 功能:按出差号得到其绑定的任务
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMisssionByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="select new CEntityMission(mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState) from com.mm.entity.CEntityMission as mission where mission.m_iMissionId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=? and bussinessactivity.m_iBussinessBindType=0 and mission.m_iMissionBussinessBandState=1)";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE,cEntityBussiness.getM_iBussinessId()});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	
	/**
	 * 序号：mission:15
	 * 功能:获取所有员工的未接受，执行中任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionRuning(){
		String hql=" select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState,mission.m_iMissionBussinessBandState  from  com.mm.entity.CEntityMission as mission ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityMissions as bb where bb.m_iMissionId=mission.m_iMissionId and mission.m_iMissionState in(?,?) order by mission.m_sMissionPubdate desc" ;
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_UNDERWAY,MyConstant.Mission.MISSION_WAITTAKE});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionId((Integer)tuple[3]).MissionPubdate((String)tuple[4]).MissionContent((String)tuple[5]).MissionDeadline((String)tuple[6]).MissionState((Integer)tuple[7]).MissionDelayState((Integer)tuple[8]).MissionBussinessBandState((Integer)tuple[9]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	/**
	 * 序号：mission:16
	 * 功能:统计员工号，员工账号，员工名，任务需执行数量（未接受，执行中）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryMissionRunningNumberOfEmployeeInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,sum(case when mission.m_iMissionState in(?,?) and bb.m_iMissionId=mission.m_iMissionId then 1 else 0  end)  from com.mm.entity.CEntityEmployee as employee ,com.mm.entity.CEntityMission as mission left join employee.cEntityMissions as bb where employee.m_iEmployeeType!=4  group by employee.m_iEmployeeId order by sum(case when  mission.m_iMissionState in(?,?) and bb.m_iMissionId=mission.m_iMissionId then 1 else 0  end) desc ,employee.m_iEmployeeId asc";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_WAITTAKE,MyConstant.Mission.MISSION_UNDERWAY,MyConstant.Mission.MISSION_WAITTAKE,MyConstant.Mission.MISSION_UNDERWAY});
		return findResult;
	}
	
	/**
	 * 序号：mission:17
	 * 功能:获取所有员工的未审核，已过期任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionWaitDeal(){
	String hql=" select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState,mission.m_iMissionBussinessBandState  from  com.mm.entity.CEntityMission as mission ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityMissions as bb where bb.m_iMissionId=mission.m_iMissionId and mission.m_iMissionState in(?,?) order by mission.m_sMissionPubdate desc" ;
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_NOCHECK,MyConstant.Mission.MISSION_OUTTIME});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionId((Integer)tuple[3]).MissionPubdate((String)tuple[4]).MissionContent((String)tuple[5]).MissionDeadline((String)tuple[6]).MissionState((Integer)tuple[7]).MissionDelayState((Integer)tuple[8]).MissionBussinessBandState((Integer)tuple[9]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	/**
	 * 序号：mission:18
	 * 功能:获取所有员工的已失败，已审核任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionComplete(){
	String hql=" select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState,mission.m_iMissionBussinessBandState  from  com.mm.entity.CEntityMission as mission ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityMissions as bb where bb.m_iMissionId=mission.m_iMissionId and mission.m_iMissionState in(?,?) order by mission.m_sMissionPubdate desc" ;
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_FAILURE,MyConstant.Mission.MISSION_CHECK});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionId((Integer)tuple[3]).MissionPubdate((String)tuple[4]).MissionContent((String)tuple[5]).MissionDeadline((String)tuple[6]).MissionState((Integer)tuple[7]).MissionDelayState((Integer)tuple[8]).MissionBussinessBandState((Integer)tuple[9]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	/**
	 * 序号：mission:19
	 * 功能:获取所有员工的未审核，已过期任务数量
	 * 参数：
	 * 返回值:int
	 */
	public int queryMissionWaitDealNumber(){
		String hql="select count(*) from CEntityMission where m_iMissionState in(?,?)";
		int count=((Long) this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_OUTTIME,MyConstant.Mission.MISSION_NOCHECK}).listIterator().next()).intValue();
		return count;
	}
	
	/**
	 * 序号：mission:20
	 * 功能:根据员工号获取其绑定的任务信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryMissionBindByEmployee(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionBussinessBandState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_BUSSINESSBAND});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：mission:21
	 * 功能:根据任务号修改出差绑定状态
	 * 参数：CEntityMission(MissionId,MissionBussinessBandState)
	 * 返回值:boolean
	 */
	public boolean updateMissionBindType(CEntityMission cEntityMission){
		String hql="update com.mm.entity.CEntityMission as mission set mission.m_iMissionBussinessBandState=? where mission.m_iMissionId=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityMission.getM_iMissionBussinessBandState(),cEntityMission.getM_iMissionId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:22
	 * 功能:根据员工号获取其未绑定的任务信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryMissionUnBindByEmployee(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionBussinessBandState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_BUSSINESSNOBAND});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
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
