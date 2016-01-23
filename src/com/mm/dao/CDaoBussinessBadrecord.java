package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessBadrecord;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityBussinessBadrecordArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoBussinessBadrecord")
public class CDaoBussinessBadrecord extends SuperDAO{
	
	/**
	 * 序号：bussinessbadrecord:1
	 * 功能：增加一条出差不良记录
	 * 参数：cEntityBussiness(BussinessId),cEntityBussinessBadrecord(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveBussinessBadrecord(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord) {
		
		boolean bisSave=false;
		try {
			cEntityBussinessBadrecord.setcEntityBussiness(cEntityBussiness);
			this.getHibernateTemplate().save(cEntityBussinessBadrecord);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}
	
	/**
	 * 序号：bussinessbadrecord:2
	 * 功能：获得所有的出差不良记录
	 * 参数：无
	 * 返回值:CEntityBussinessBadrecordArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityBussinessBadrecordArray queryAllBussinessBadrecord() {
		String hql="from com.mm.entity.CEntityBussinessBadrecord as bussinessbadrecord ";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		
		CEntityBussinessBadrecordArray cEntityBussinessBadrecordArray=new CEntityBussinessBadrecordArray((List<CEntityBussinessBadrecord>) findResult);
		
		return cEntityBussinessBadrecordArray;
	}
	
	
	
	/**
	 * 序号：bussinessbadrecord:3
	 * 功能：按员工号获取其所有出差不良记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessBadrecordByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select bussinessbadrecord.m_iBussinessBadrecordId,bussinessbadrecord.m_sBussinessBadrecordTime,bussinessbadrecord.m_sBussinessBadrecordReason,bussinessbadrecord.cEntityBussiness.m_iBussinessId  from com.mm.entity.CEntityBussinessBadrecord as bussinessbadrecord,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussinessbadrecord.cEntityBussiness.m_iBussinessId and employee.m_iEmployeeId=? order by bussinessbadrecord.cEntityBussiness.m_sBussinessReturnTime desc";
		List findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussinessBadrecord cEntityBussinessBadrecord=new CEntityBussinessBadrecord.Builder().BussinessBadrecordId((Integer)tuple[0]).BussinessBadrecordTime((String)tuple[1]).BussinessBadrecordReason((String)tuple[2]).build();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId((Integer)tuple[3]).build();
		    map.put(MyOpcode.BussinessBadrecord.BussinessBadrecord, cEntityBussinessBadrecord);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    
		    maps.put(i, map);
		    i++;
		}
		 return maps;
	
	
	}
	
	/**
	 * 序号：bussinessbadrecord:4
	 * 功能：获取所有未删员工的出差不良记录信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessBadrecordInfo(){
		String hql="select bussinessbadrecord.m_iBussinessBadrecordId,bussinessbadrecord.m_sBussinessBadrecordTime,bussinessbadrecord.m_sBussinessBadrecordReason,bussinessbadrecord.cEntityBussiness.m_iBussinessId,employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeeName  from com.mm.entity.CEntityBussinessBadrecord as bussinessbadrecord,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussinessbadrecord.cEntityBussiness.m_iBussinessId and employee.m_iEmployeeType!=?  order by bussinessbadrecord.cEntityBussiness.m_sBussinessReturnTime desc";
		List findResult=this.getHibernateTemplate().find(hql,MyConstant.Employee.EMPLOYEE_DEL);
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussinessBadrecord cEntityBussinessBadrecord=new CEntityBussinessBadrecord.Builder().BussinessBadrecordId((Integer)tuple[0]).BussinessBadrecordTime((String)tuple[1]).BussinessBadrecordReason((String)tuple[2]).build();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId((Integer)tuple[3]).build();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[4]).EmployeeAccount((String)tuple[5]).EmployeeName((String)tuple[6]).build();
		    map.put(MyOpcode.BussinessBadrecord.BussinessBadrecord, cEntityBussinessBadrecord);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    maps.put(i, map);
		    i++;
		}
		 return maps;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
