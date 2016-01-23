package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionDelay;
import com.mm.entityarray.CEntityMissionDelayArray;
import com.mm.tool.MyOpcode;

@Component("cDaoMissionDelay")
public class CDaoMissionDelay extends SuperDAO {

	/**
	 * 序号：missiondealy:1
	 * 功能：增加一条任务延期
	 * 参数：cEntityMission(MissionId),cEntityMissionDelay(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMissionDealy(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay) {
		CEntityMission findResult=(CEntityMission)this.getHibernateTemplate().get(CEntityMission.class, cEntityMission.getM_iMissionId());
		cEntityMissionDelay.setcEntityMission(findResult);
		
		
		boolean bisSave=false;
		try {
			this.getHibernateTemplate().save(cEntityMissionDelay);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisSave;
	}
	
	
	/**
	 * 序号：missiondealy:2
	 * 功能：按任务号获得所有任务延期
	 * 参数：cEntityMission(MissionId)
	 * 返回值:CEntityMissionDelayArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionDelayArray queryAllMissionDelayByMissionId(CEntityMission cEntityMission) {
		
		String hql="from com.mm.entity.CEntityMissionDelay as missiondelay where MissionId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityMission.getM_iMissionId());
		CEntityMissionDelayArray cEntityMissionDelayArray=new CEntityMissionDelayArray((List<CEntityMissionDelay>) findResult);
		return cEntityMissionDelayArray;
	}
	
	/**
	 * 序号：missiondealy:3
	 * 功能：按员工号获得所有的任务延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionDelayByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select missiondelay.m_iMissionDelayId,missiondelay.m_sMissionDelayTime,missiondelay.m_sMissionDelayDeadline,mission.m_iMissionId from com.mm.entity.CEntityMissionDelay as missiondelay ,com.mm.entity.CEntityMission as mission,com.mm.entity.CEntityEmployee as employee inner join employee.cEntityMissions as s where missiondelay.cEntityMission.m_iMissionId=mission.m_iMissionId and mission.m_iMissionId=s.m_iMissionId and employee.m_iEmployeeId=? order by MissionDelayTime desc";
		List findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityMissionDelay cEntityMissionDelay=new CEntityMissionDelay.Builder().MissionDelayId((Integer)tuple[0]).MissionDelayTime((String)tuple[1]).MissionDelayDeadline((String)tuple[2]).build();
		    System.out.println(cEntityMissionDelay.toString());
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionId((Integer)tuple[3]).build();
		    map.put(MyOpcode.MissionDelay.MissionDelay, cEntityMissionDelay);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    maps.put(i, map);
		    i++;
		}
		 return maps;
		
	}
	
	/**
	 * 序号：missiondealy:4
	 * 功能：获得所有的延期记录及其任务信息已员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionDelayWithMissionInfoAndEmployeeInfo(){
		String hql="select missiondelay.m_iMissionDelayId,missiondelay.m_sMissionDelayTime,missiondelay.m_sMissionDelayDeadline,mission.m_iMissionId,mission.m_sMissionContent,employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName from com.mm.entity.CEntityMissionDelay as missiondelay ,com.mm.entity.CEntityMission as mission,com.mm.entity.CEntityEmployee as employee inner join employee.cEntityMissions as s where missiondelay.cEntityMission.m_iMissionId=mission.m_iMissionId and mission.m_iMissionId=s.m_iMissionId  order by missiondelay.m_sMissionDelayTime desc";
		List findResult=this.getHibernateTemplate().find(hql);
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityMissionDelay cEntityMissionDelay=new CEntityMissionDelay.Builder().MissionDelayId((Integer)tuple[0]).MissionDelayTime((String)tuple[1]).MissionDelayDeadline((String)tuple[2]).build();
		    System.out.println(cEntityMissionDelay.toString());
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionId((Integer)tuple[3]).MissionContent((String)tuple[4]).build();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[5]).EmployeeAccount((String)tuple[6]).EmployeeName((String)tuple[7]).build();
		    map.put(MyOpcode.MissionDelay.MissionDelay, cEntityMissionDelay);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    maps.put(i, map);
		    i++;
		}
		 return maps;
		
	}
	
	
	
	
	
	
	
	
}
