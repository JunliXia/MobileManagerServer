package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessUndo;
import com.mm.entity.CEntityEmployee;
import com.mm.tool.MyOpcode;

@Component("cDaoBussinessUndo")
public class CDaoBussinessUndo extends SuperDAO{
	/**
	 * 序号：bussinessundo:1
	 * 功能：增加一条出差撤销
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean savaBussinessUndo(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		boolean bisSave=false;
		CEntityBussiness findResult=(CEntityBussiness)this.getHibernateTemplate().get(CEntityBussiness.class, cEntityBussiness.getM_iBussinessId());
		cEntityBussinessUndo.setcEntityBussiness(findResult);
		try {
			this.getHibernateTemplate().save(cEntityBussinessUndo);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
		
	}
	
	
	/**
	 * 序号：bussinessundo:2
	 * 功能：查询所有未删员工出差撤销信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessUndoInfo(){
		String hql="select bussinessundo.m_iBussinessUndoId,bussinessundo.m_sBussinessUndoReason,bussinessundo.m_sBussinessUndoTime," +
				"bussinessundo.cEntityBussiness.m_iBussinessId," +
				"employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeeName " +
				"from com.mm.entity.CEntityBussinessUndo as bussinessundo," +
				"com.mm.entity.CEntityEmployee as employee " +
				"left join employee.cEntityBussinesses as bb " +
				"where bb.m_iBussinessId=bussinessundo.cEntityBussiness.m_iBussinessId " ;
//				"order by bussinessundo.m_sBussinessUndoTime desc";
		List findResult=this.getHibernateTemplate().find(hql);
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while(it.hasNext()){
			Object[] tuple=(Object[])it.next();
			LinkedMap map=new LinkedMap();
			CEntityBussinessUndo cEntityBussinessUndo=new CEntityBussinessUndo.Builder().BussinessUndoId((Integer)tuple[0]).BussinessUndoReason((String)tuple[1]).BussinessUndoTime((String)tuple[2]).build();
			CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId((Integer)tuple[3]).build();
			CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[4]).EmployeeAccount((String)tuple[5]).EmployeeName((String)tuple[6]).build();
			
			map.put(MyOpcode.BussinessUndo.BussinessUndo, cEntityBussinessUndo);
			map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
			map.put(MyOpcode.Employee.Employee, cEntityEmployee);
			maps.put(i, map);
			i++;
		}
		
		return maps;
	}
	
	
}
