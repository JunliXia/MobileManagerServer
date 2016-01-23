package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.entityarray.CEntityVisitUndoArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoVisitUndo")
public class CDaoVisitUndo extends SuperDAO{

	/**
	 * 序号：visitundo:1
	 * 功能：增加一条拜访撤销
	 * 参数：cEntityVisitPlan(VisitPlanId),cEntityVisitUndo(本表字段)
	 * 返回值:CEntityVisitConclusionArray
	 */
	public boolean saveVisitUndo(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo) {
		CEntityVisitPlan findResult=(CEntityVisitPlan)this.getHibernateTemplate().get(CEntityVisitPlan.class, cEntityVisitPlan.getM_iVisitPlanId());
		cEntityVisitUndo.setcEntityVisitPlan(findResult);
		cEntityVisitUndo.setM_iVisitUndoRecallType(findResult.getM_iVisitPlanState());
		boolean bisSave=false;
		try {
			this.getHibernateTemplate().save(cEntityVisitUndo);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisSave;
	}
	
	/**
	 * 序号：visitundo:2
	 * 功能：获得所有的拜访撤销
	 * 参数：无
	 * 返回值:CEntityVisitUndoArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitUndoArray queryAllVisitUndo() {
		String hql="from com.mm.entity.CEntityVisitUndo as visitundo";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntityVisitUndoArray cEntityVisitUndoArray=new CEntityVisitUndoArray((List<CEntityVisitUndo>) findResult);
 		return cEntityVisitUndoArray;
	}

	/**
	 * 序号：visitundo:3
	 * 功能：按拜访撤销号修改拜访撤销的状态
	 * 参数：cEntityVisitUndo(VisitUndoId)
	 * 	   OperateState(MyConstant.VisitUndo.*)
	 * 返回值:CEntityVisitUndoArray
	 */
	public boolean updateVisitUndoStateByVisitUndoId(CEntityVisitUndo cEntityVisitUndo,int OperateState){
		String hql="update com.mm.entity.CEntityVisitUndo as visitundo set VisitUndoType=? where VisitUndoId=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{OperateState,cEntityVisitUndo.getM_iVisitUndoId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}
	
	
	/**
	 * 序号：visitundo:4
	 * 功能：根据任务撤销号获得任务撤销
	 * 参数：cEntityVisitUndo(VisitUndoId)
	 * 返回值:CEntityVisitUndo
	 */
	public CEntityVisitUndo queryVisitUndoByVisitUndoId(CEntityVisitUndo cEntityVisitUndo){
		CEntityVisitUndo findRusult=(CEntityVisitUndo)this.getHibernateTemplate().get(CEntityVisitUndo.class,cEntityVisitUndo.getM_iVisitUndoId());
		return findRusult;
	}
	
	
	/**
	 * 序号：visitundo:5
	 * 功能：获取所有未删员工的拜访撤销记录信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllVisitUndoWithInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,visitundo.cEntityVisitPlan.m_iVisitPlanId,client.m_iClientId,client.m_sClientName,visitundo.m_iVisitUndoId,visitundo.m_sVisitUndoTime,visitundo.m_sVisitUndoReason from com.mm.entity.CEntityEmployee as employee,com.mm.entity.CEntityVisitUndo as visitundo,com.mm.entity.CEntityClient as client left join employee.cEntityVisitPlans as bb where visitundo.cEntityVisitPlan.cEntityClient.m_iClientId=client.m_iClientId and bb.m_iVisitPlanId=visitundo.cEntityVisitPlan.m_iVisitPlanId and visitundo.m_iVisitUndoType=? and employee.m_iEmployeeType!=? order by visitundo.m_sVisitUndoTime desc";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.VisitUndo.VISITUNDO_UNDO,MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[3]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[4]).ClientName((String)tuple[5]).build();
		    CEntityVisitUndo cEntityVisitUndo=new CEntityVisitUndo.Builder().VisitUndoId((Integer)tuple[6]).VisitUndoTime((String)tuple[7]).VisitUndoReason((String)tuple[8]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    map.put(MyOpcode.VisitUndo.VisitUndo, cEntityVisitUndo);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	
	
	
	
}
