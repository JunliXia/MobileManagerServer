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
	 * ��ţ�mission:1
	 * ���ܣ���������
	 * ������cEntityEmployee(EmployeeId),cEntityMission(�����ֶ�)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:2
	 * ���ܣ��޸�����״̬
	 * ������cEntityMission(MissionId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:3
	 * ���ܣ��޸�����״̬
	 * ������EntityMission(MissionId,MissionDelayState)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:4
	 * ���ܣ�����Ա���Ż����������
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionByEmployeeId(CEntityEmployee cEntityEmployee) {
		
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? order by MissionPubdate desc";
		
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�mission:5
	 * ���ܣ�������״̬�����������
	 * ������OperateState(MyConstant.Mission.*)
	 * ����ֵ:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionByMissionType(int  OperateState) {
		String hql="select new CEntityMission(mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState) from com.mm.entity.CEntityMission as mission where MissionState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,OperateState);
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	
	/**
	 * ��ţ�mission:6
	 * ���ܣ�������Ż����������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:CEntityMission
	 */
	public CEntityMission queryMissionByMissionId(int  MissionId){
		CEntityMission findResult=(CEntityMission)this.getHibernateTemplate().get(CEntityMission.class, MissionId);
		return findResult;
		
	}
	
	
	/**
	 * ��ţ�mission:7
	 * ���ܣ��޸��������ޣ��޸����޾��ж�Ϊ�����ڣ�
	 * ������cEntityMission(MissionId,MissionDeadline)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:8
	 * ���ܣ���Ա���ż�״̬�����������
	 * ������cEntityEmployee(EmployeeId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * ����ֵ:boolean
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionByEmployeeIdAndMissionState(CEntityEmployee cEntityEmployee,int OperateState){
		String hql="from com.mm.entity.CEntityMission as mission where MissionState=? and EmployeeId=? order by MissionPubdate desc";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{OperateState,cEntityEmployee.getM_iEmployeeId()});
		
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
		
	}
	
	
	/**
	 * ��ţ�mission:9
	 * ���ܣ���Ա���Ż�ȡ��ֹ̬������δ���,�����,�ѳ���,��ʧ�ܣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionCompleteStateByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionState in(?,?,?,?)";
		
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_NOCHECK,MyConstant.Mission.MISSION_CHECK,MyConstant.Mission.MISSION_UNDO,MyConstant.Mission.MISSION_FAILURE});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	
	
	
	
	/**
	 * ��ţ�mission:10
	 * ���ܰ�Ա���Ż�ȡִ��̬������ִ���У��ѹ��ڣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMissionRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionState in(?,?)";
		
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_UNDERWAY,MyConstant.Mission.MISSION_OUTTIME});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�mission:11
	 * ����ͨ����ǰ�����޸�����״̬(δ����,ִ����)Ϊ�ѹ���	
	 * ������CEntityMission(MissionDeadline)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:12
	 * ����:������Ž���������״̬���	
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:13
	 * ����:������Ž���������������������¼
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:14
	 * ����:������ŵõ���󶨵�����
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryAllMisssionByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="select new CEntityMission(mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState) from com.mm.entity.CEntityMission as mission where mission.m_iMissionId in (select bussinessactivity.m_iBussinessObjectId  from com.mm.entity.CEntityBussinessActivity as bussinessactivity,com.mm.entity.CEntityBussiness as bussiness left join bussiness.cEntityBussinessActivities as bb where bussinessactivity.m_iBussinessActivityType=? and bussinessactivity.m_iBussinessActivityId=bb.m_iBussinessActivityId and bussiness.m_iBussinessId=? and bussinessactivity.m_iBussinessBindType=0 and mission.m_iMissionBussinessBandState=1)";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE,cEntityBussiness.getM_iBussinessId()});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	
	/**
	 * ��ţ�mission:15
	 * ����:��ȡ����Ա����δ���ܣ�ִ����������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
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
	 * ��ţ�mission:16
	 * ����:ͳ��Ա���ţ�Ա���˺ţ�Ա������������ִ��������δ���ܣ�ִ���У�
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryMissionRunningNumberOfEmployeeInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,sum(case when mission.m_iMissionState in(?,?) and bb.m_iMissionId=mission.m_iMissionId then 1 else 0  end)  from com.mm.entity.CEntityEmployee as employee ,com.mm.entity.CEntityMission as mission left join employee.cEntityMissions as bb where employee.m_iEmployeeType!=4  group by employee.m_iEmployeeId order by sum(case when  mission.m_iMissionState in(?,?) and bb.m_iMissionId=mission.m_iMissionId then 1 else 0  end) desc ,employee.m_iEmployeeId asc";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_WAITTAKE,MyConstant.Mission.MISSION_UNDERWAY,MyConstant.Mission.MISSION_WAITTAKE,MyConstant.Mission.MISSION_UNDERWAY});
		return findResult;
	}
	
	/**
	 * ��ţ�mission:17
	 * ����:��ȡ����Ա����δ��ˣ��ѹ���������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
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
	 * ��ţ�mission:18
	 * ����:��ȡ����Ա������ʧ�ܣ������������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
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
	 * ��ţ�mission:19
	 * ����:��ȡ����Ա����δ��ˣ��ѹ�����������
	 * ������
	 * ����ֵ:int
	 */
	public int queryMissionWaitDealNumber(){
		String hql="select count(*) from CEntityMission where m_iMissionState in(?,?)";
		int count=((Long) this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_OUTTIME,MyConstant.Mission.MISSION_NOCHECK}).listIterator().next()).intValue();
		return count;
	}
	
	/**
	 * ��ţ�mission:20
	 * ����:����Ա���Ż�ȡ��󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryMissionBindByEmployee(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionBussinessBandState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_BUSSINESSBAND});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�mission:21
	 * ����:����������޸ĳ����״̬
	 * ������CEntityMission(MissionId,MissionBussinessBandState)
	 * ����ֵ:boolean
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
	 * ��ţ�mission:22
	 * ����:����Ա���Ż�ȡ��δ�󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionArray queryMissionUnBindByEmployee(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityMission as mission where EmployeeId=? and MissionBussinessBandState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Mission.MISSION_BUSSINESSNOBAND});
		CEntityMissionArray cEntityMissionArray=new CEntityMissionArray((List<CEntityMission>) findResult);
		return cEntityMissionArray;
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
