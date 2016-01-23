package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.tool.MyOpcode;

@Component("cDaoMissionConclusion")
public class CDaoMissionConclusion extends SuperDAO{

	/**
	 * 序号：missionconclusion:1
	 * 功能：增加一条任务总结
	 * 参数：cEntityMission(MissionId),cEntityMissionConclusion(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMissionConclusion(CEntityMission cEntityMission, CEntityMissionConclusion cEntityMissionConclusion) {
		CEntityMission findResult=(CEntityMission)this.getHibernateTemplate().get(CEntityMission.class, cEntityMission.getM_iMissionId());
		cEntityMissionConclusion.setcEntityMission(findResult);
		
		boolean bisSave=false;
		try {
			this.getHibernateTemplate().save(cEntityMissionConclusion);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisSave;
	}
	
	/**
	 * 序号：missionconclusion:2
	 * 功能：按任务号获得任务总结
	 * 参数：cEntityMission(MissionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissonConclusionByMissionId(CEntityMission cEntityMission) {
		String hql="from com.mm.entity.CEntityMissionConclusion as missionconclusion where MissionId=?";
		
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityMission.getM_iMissionId());
		

		Iterator<?> iterator = findResult.listIterator();
		CEntityMissionConclusion result = null;
		if (iterator.hasNext()) {
			result = (CEntityMissionConclusion) iterator.next();
		}
		return result; 
	}
	
	/**
	 * 序号：missionconclusion:3
	 * 功能：修改任务总结审核结果
	 * 参数：cEntityMissionConclusion(MissionConclusionId)
	 * 	   OperateState(MyConstant.MissionConclusion.*)
	 * 返回值:boolean
	 */
	public boolean updateMissionCheck(CEntityMissionConclusion cEntityMissionConclusion,int OperateState) {
		String hql="update com.mm.entity.CEntityMissionConclusion as missionconclusion set MissionCheck=? where MissionConclusionId=? ";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{OperateState,cEntityMissionConclusion.getM_iMissionConclusionId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}
	
	/**
	 * 序号：missionconclusion:4
	 * 功能：按员工号获取其所有任务总结
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionConclusionArray
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionConclusionByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select missionconclusion.m_iMissionConclusionId,missionconclusion.m_iMissionCheck,missionconclusion.m_sMissionSummary,missionconclusion.m_sMissionSubmitTime,missionconclusion.m_sMissionAccessoryPath,mission.m_sMissionContent,mission.m_iMissionId from com.mm.entity.CEntityMissionConclusion as missionconclusion,com.mm.entity.CEntityMission as mission,com.mm.entity.CEntityEmployee as employee  inner join  employee.cEntityMissions as s where s.m_iMissionId=mission.m_iMissionId and  missionconclusion.cEntityMission.m_iMissionId=mission.m_iMissionId and employee.m_iEmployeeId=? order by missionconclusion.m_sMissionSubmitTime desc";
		List findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityMissionConclusion cEntityMissionConclusion=new CEntityMissionConclusion.Builder().MissionConclusionId((Integer)tuple[0]).MissionCheck((Integer)tuple[1]).MissionSummary((String)tuple[2]).MissionSubmitTime((String)tuple[3]).MissionAccessoryPath((String)tuple[4]).build();
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionContent((String)tuple[5]).MissionId((Integer)tuple[6]).build();
		    map.put(MyOpcode.MissionConclusion.MissionConclusion, cEntityMissionConclusion);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    maps.put(i, map);
		    i++;
		}
		 return maps;
	}
	
	

	/**
	 * 序号：missionconclusion:5
	 * 功能：按任务总结号获取其任务总结
	 * 参数：CEntityMissionConclusion(MissionConclusionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissionConclusionByMissionConclusionId(CEntityMissionConclusion cEntityMissionConclusion){
		CEntityMissionConclusion findResult=(CEntityMissionConclusion) this.getHibernateTemplate().get(CEntityMissionConclusion.class, cEntityMissionConclusion.getM_iMissionConclusionId());
		return findResult;
		
	}
	/**
	 * 序号：missionconclusion:6
	 * 功能：获取所有任务总结及其任务员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionConclusionWithEmployeeInfo(){
		String hql="select missionconclusion.m_iMissionConclusionId,missionconclusion.m_iMissionCheck,missionconclusion.m_sMissionSummary,missionconclusion.m_sMissionSubmitTime,missionconclusion.m_sMissionAccessoryPath,mission.m_sMissionContent,mission.m_iMissionId,employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityMissionConclusion as missionconclusion,com.mm.entity.CEntityMission as mission,com.mm.entity.CEntityEmployee as employee  inner join  employee.cEntityMissions as s where s.m_iMissionId=mission.m_iMissionId and  missionconclusion.cEntityMission.m_iMissionId=mission.m_iMissionId  order by missionconclusion.m_sMissionSubmitTime desc";
		List findResult=this.getHibernateTemplate().find(hql);
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityMissionConclusion cEntityMissionConclusion=new CEntityMissionConclusion.Builder().MissionConclusionId((Integer)tuple[0]).MissionCheck((Integer)tuple[1]).MissionSummary((String)tuple[2]).MissionSubmitTime((String)tuple[3]).MissionAccessoryPath((String)tuple[4]).build();
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionContent((String)tuple[5]).MissionId((Integer)tuple[6]).build();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[7]).EmployeeAccount((String)tuple[8]).EmployeeName((String)tuple[9]).build();
		    map.put(MyOpcode.MissionConclusion.MissionConclusion, cEntityMissionConclusion);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    maps.put(i, map);
		    i++;
		}
		 return maps;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

