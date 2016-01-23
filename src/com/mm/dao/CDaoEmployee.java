package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.tool.MyConstant;

@Component("cDaoEmployee")
public class CDaoEmployee extends SuperDAO {
	/**
	 * 序号：employee:1
	 *  功能：增加一个员工
	 * 参数：cEntityEmployee(EmployeeAccount,EmployeePassword
	 * ,EmployeeName,EmployeePhone
	 * EmployeeSex,EmployeeDepartment,EmployeeJob,EmployeeType) 返回值:boolean
	 */
	public boolean saveEmployee(CEntityEmployee cEntityEmployee) {
		boolean bisSave = false;
		try {
			this.getHibernateTemplate().save(cEntityEmployee);
			bisSave = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisSave;
	}

	/**
	 * 序号：employee:2 
	 * 功能：获取所有未删员工 
	 * 参数：无 
	 * 返回值:CEntityEmployeeArray(本表字段，不涉及关联表)
	 */
	@SuppressWarnings("unchecked")
	public CEntityEmployeeArray queryAllEmployee() {

		String hql = "select new CEntityEmployee(employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeePassword,employee.m_sEmployeeName, "
				+ "employee.m_sEmployeePhone,employee.m_sEmployeeSex,employee.m_sEmployeeDepartment,employee.m_sEmployeeJob,employee.m_iEmployeeType) from com.mm.entity.CEntityEmployee as employee where EmployeeType!=?";
		List<?> findResult = (this.getHibernateTemplate().find(hql,MyConstant.Employee.EMPLOYEE_DEL));

		CEntityEmployeeArray cEntityEmployees = new CEntityEmployeeArray(
				(List<CEntityEmployee>) findResult);
		return cEntityEmployees;
	}

	/**
	 * 序号：employee:3 功能：修改员工类别 参数：cEntityEmployee(EmployeeId,EmployeeType)
	 * 返回值:boolean
	 */
	public boolean updateEmployeeType(CEntityEmployee cEntityEmployee) {

		String hql="update com.mm.entity.CEntityEmployee as employee set EmployeeType=? where EmployeeId=?";
		boolean bisUpdate = false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityEmployee.getM_iEmployeeType(),cEntityEmployee.getM_iEmployeeId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}

	/**
	 * 序号：employee:4 功能：按帐号密码查找员工
	 * 参数：cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * 返回值:CEntityEmployee(本表字段)
	 */
	public CEntityEmployee queryEmployeeByAccountAndPassword(
			CEntityEmployee cEntityEmployee) {
		String hql = "select new CEntityEmployee(employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeePassword,employee.m_sEmployeeName, "
				+ "employee.m_sEmployeePhone,employee.m_sEmployeeSex,employee.m_sEmployeeDepartment,employee.m_sEmployeeJob,employee.m_iEmployeeType) from com.mm.entity.CEntityEmployee as employee where EmployeeAccount=? and EmployeePassword=?";
		List<?> findResult = this.getHibernateTemplate().find(
				hql,
				new String[] { cEntityEmployee.getM_sEmployeeAccount(),
						cEntityEmployee.getM_sEmployeePassword() });

		Iterator<?> iterator = findResult.listIterator();
		CEntityEmployee result = null;
		if (iterator.hasNext()) {
			result = (CEntityEmployee) iterator.next();
		}
		return result;
	}

	/**
	 * 序号：employee:5 功能：按帐号查找员工 参数：cEntityEmployee(EmployeeAccount)
	 * 返回值:CEntityEmployee（本表字段）
	 */
	public CEntityEmployee queryEmployeeByAccount(
			CEntityEmployee cEntityEmployee) {
		String hql = "select new CEntityEmployee(employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeePassword,employee.m_sEmployeeName, "
				+ "employee.m_sEmployeePhone,employee.m_sEmployeeSex,employee.m_sEmployeeDepartment,employee.m_sEmployeeJob,employee.m_iEmployeeType) from com.mm.entity.CEntityEmployee as employee where EmployeeAccount=?";
		List<?> findResult = this.getHibernateTemplate().find(hql,
				cEntityEmployee.getM_sEmployeeAccount());

		Iterator<?> iterator = findResult.listIterator();
		CEntityEmployee result = null;
		if (iterator.hasNext()) {
			result = (CEntityEmployee) iterator.next();
		}
		return result;
	}

	/**
	 * 序号：employee:6 
	 * 功能：按类别获得所有的员工 
	 * 参数：cEntityEmployee(EmployeeType)
	 * 返回值:CEntityEmployee（本表字段）
	 */
	@SuppressWarnings("unchecked")
	public CEntityEmployeeArray queryAllEmployeeByType(
			CEntityEmployee cEntityEmployee) {
		String hql = "select new CEntityEmployee(employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeePassword,employee.m_sEmployeeName, "
				+ "employee.m_sEmployeePhone,employee.m_sEmployeeSex,employee.m_sEmployeeDepartment,employee.m_sEmployeeJob,employee.m_iEmployeeType) from com.mm.entity.CEntityEmployee as employee where EmployeeType=?";
		List<?> findResult = (this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeType()));

		CEntityEmployeeArray cEntityEmployees = new CEntityEmployeeArray(
				(List<CEntityEmployee>) findResult);
		return cEntityEmployees;

	}

	/**
	 * 序号：employee:7 
	 * 功能：按员工号修改员工
	 * 参数：cEntityEmployee(本表字段)
	 * 返回值:boolean
	 */
	public boolean updateEmployeeByEmployeeId(CEntityEmployee cEntityEmployee){
		boolean bisUpdate=false;
		String hql="update com.mm.entity.CEntityEmployee as employee set EmployeeType=?,EmployeePassword=?,EmployeeName=?,EmployeePhone=?,EmployeeSex=?,EmployeeDepartment=?,EmployeeJob=? where EmployeeId=?";		
		try {
			
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityEmployee.getM_iEmployeeType(),cEntityEmployee.getM_sEmployeePassword(),cEntityEmployee.getM_sEmployeeName(),cEntityEmployee.getM_sEmployeePhone(),cEntityEmployee.getM_sEmployeeSex(),cEntityEmployee.getM_sEmployeeDepartment(),cEntityEmployee.getM_sEmployeeJob(),cEntityEmployee.getM_iEmployeeId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	
	/**
	 * 序号：employee:8 
	 * 功能：获取所有未删员工名
	 * 参数：
	 * 返回值:List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryAllEmployeeName(){
		String hql="select employee.m_sEmployeeName from com.mm.entity.CEntityEmployee as employee where EmployeeType!=4";
		List<String> result=this.getHibernateTemplate().find(hql);
		return result;
	}
	
	
	/**
	 * 序号：employee:9 
	 * 功能：按员工名获得所有未删员工
	 * 参数：CEntityEmployee(EmployeeName)
	 * 返回值:CEntityEmployeeArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityEmployeeArray queryAllEmployeeByEmployeeName(CEntityEmployee cEntityEmployee){
		String hql="select new CEntityEmployee(employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeePassword,employee.m_sEmployeeName, "
				+ "employee.m_sEmployeePhone,employee.m_sEmployeeSex,employee.m_sEmployeeDepartment,employee.m_sEmployeeJob,employee.m_iEmployeeType) from com.mm.entity.CEntityEmployee as employee where EmployeeName=? and EmployeeType!=4";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_sEmployeeName());
		
		CEntityEmployeeArray cEntityEmployeeArray=new CEntityEmployeeArray((List<CEntityEmployee>) findResult);
		return cEntityEmployeeArray;
		
	}
	
	
	/**
	 * 序号：employee:10 
	 * 功能：按员工部门获得所有未删员工
	 * 参数：CEntityEmployee(EmployeeDepartment)
	 * 返回值:CEntityEmployeeArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityEmployeeArray queryAllEmployeeByEmployeeDepartment(CEntityEmployee cEntityEmployee){
		String hql="select new CEntityEmployee(employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeePassword,employee.m_sEmployeeName, "
			+ "employee.m_sEmployeePhone,employee.m_sEmployeeSex,employee.m_sEmployeeDepartment,employee.m_sEmployeeJob,employee.m_iEmployeeType) from com.mm.entity.CEntityEmployee as employee where EmployeeDepartment=? and EmployeeType!=4";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_sEmployeeDepartment());
	
		CEntityEmployeeArray cEntityEmployeeArray=new CEntityEmployeeArray((List<CEntityEmployee>) findResult);
		return cEntityEmployeeArray;
	
	}
	
	
	
}
