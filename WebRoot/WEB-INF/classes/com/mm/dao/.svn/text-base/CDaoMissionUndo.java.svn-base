package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionUndo;
import com.mm.entityarray.CEntityMissionUndoArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoMissionUndo")
public class CDaoMissionUndo extends SuperDAO{

	/**
	 * 序号：missionundo:1
	 * 功能：增加一条任务撤销
	 * 参数：cEntityMission(MissionId),cEntityMissionUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMissionUndo(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		CEntityMission findResult=(CEntityMission)this.getHibernateTemplate().get(CEntityMission.class, cEntityMission.getM_iMissionId());
		cEntityMissionUndo.setcEntityMission(findResult);
		cEntityMissionUndo.setM_iMissionUndoRecallType(findResult.getM_iMissionState());
		boolean bisSave=false;
		
		try {
			this.getHibernateTemplate().save(cEntityMissionUndo);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisSave;
	}
	
	
	/**
	 * 序号：missionundo:2
	 * 功能：获得所有的任务撤销
	 * 参数：无
	 * 返回值:CEntityMissionUndoArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityMissionUndoArray queryAllMissionUndo() {
		
		String hql="from com.mm.entity.CEntityMissionUndo as missionundo";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntityMissionUndoArray cEntityMissionUndoArray=new CEntityMissionUndoArray((List<CEntityMissionUndo>) findResult);
		return cEntityMissionUndoArray;
	}
	
	/**
	 * 序号：missionundo:3
	 * 功能：修改撤销类型
	 * 参数：cEntityMissionUndo(MissionUndoId)
	 * 	   OperateState(Myconstant.MissionUndo.*)
	 * 返回值:boolean
	 */
	public boolean updateMissionUndoType(CEntityMissionUndo cEntityMissionUndo,int OperateState) {
		String hql="update com.mm.entity.CEntityMissionUndo as missionundo set MissionUndoType=? where MissionUndoId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{OperateState,cEntityMissionUndo.getM_iMissionUndoId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	
	/**
	 * 序号：missionundo:4
	 * 功能：按撤销号获得任务
	 * 参数：cEntityMissionUndo(MissionUndoId)
	 * 返回值:CEntityMission
	 */
	public CEntityMission queryMissionByMissionUndoId(CEntityMissionUndo cEntityMissionUndo) {
		CEntityMissionUndo findResult=(CEntityMissionUndo)this.getHibernateTemplate().get(CEntityMissionUndo.class, cEntityMissionUndo.getM_iMissionUndoId());
		CEntityMission cEntityMission=findResult.getcEntityMission();
		return cEntityMission;
	}
	
	
	/**
	 * 序号：missionundo:5
	 * 功能：按撤销号获得撤销
	 * 参数：cEntityMissionUndo(MissionUndoId)
	 * 返回值:CEntityMissionUndo
	 */
	public CEntityMissionUndo queryMissionUndoByMissionUndoId(CEntityMissionUndo cEntityMissionUndo){
		CEntityMissionUndo findResult=(CEntityMissionUndo)this.getHibernateTemplate().get(CEntityMissionUndo.class, cEntityMissionUndo.getM_iMissionUndoId());
		return findResult;
		
	}
//	public static void main(String[] args) {
//	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//	"applicationContext.xml");
//	CDaoMissionUndo tt = (CDaoMissionUndo) ctx.getBean("cDaoMissionUndo");
//	tt.queryAllMissionUndoWithEmployeeInfo();
//}
	
	
	/**
	 * 序号：missionundo:6
	 * 功能：获得所有的任务撤销及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllMissionUndoWithEmployeeInfo(){
//		String hql=" select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState,mission.m_iMissionBussinessBandState  from  com.mm.entity.CEntityMission as mission ,com.mm.entity.CEntityEmployee as employee left join employee.cEntityMissions as bb where bb.m_iMissionId=mission.m_iMissionId and mission.m_iMissionState in(?,?) order by mission.m_sMissionPubdate desc" ;
		String hql=" select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,mission.m_iMissionId,mission.m_sMissionPubdate,mission.m_sMissionContent,mission.m_sMissionDeadline,mission.m_iMissionState,mission.m_iMissionDelayState,mission.m_iMissionBussinessBandState,missionundo.m_iMissionUndoId,missionundo.m_sMissionUndoReason,missionundo.m_sMissionUndoTime  from  com.mm.entity.CEntityMission as mission ,com.mm.entity.CEntityMissionUndo as missionundo,com.mm.entity.CEntityEmployee as employee left join employee.cEntityMissions as bb where bb.m_iMissionId=mission.m_iMissionId and missionundo.cEntityMission.m_iMissionId=mission.m_iMissionId and missionundo.m_iMissionUndoType=0  and mission.m_iMissionState=? order by missionundo.m_sMissionUndoTime desc" ;
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Mission.MISSION_UNDO});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityMission cEntityMission=new CEntityMission.Builder().MissionId((Integer)tuple[3]).MissionPubdate((String)tuple[4]).MissionContent((String)tuple[5]).MissionDeadline((String)tuple[6]).MissionState((Integer)tuple[7]).MissionDelayState((Integer)tuple[8]).MissionBussinessBandState((Integer)tuple[9]).build();
		    CEntityMissionUndo cEntityMissionUndo=new CEntityMissionUndo.Builder().MissionUndoId((Integer)tuple[10]).MissionUndoReason((String)tuple[11]).MissionUndoTime((String)tuple[12]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Mission.Mission, cEntityMission);
		    map.put(MyOpcode.MissionUndo.MissionUndo, cEntityMissionUndo);
		    maps.put(i, map);
		    
//		    System.out.println(cEntityEmployee.toString());
//		    System.out.println(cEntityMission.toString());
//		    System.out.println(cEntityMissionUndo.toString());
//		    System.out.println("--------------------"+i);
		    
		    i++;
		 }    

		return maps;
	}
	
	
	
	
	
	
}
