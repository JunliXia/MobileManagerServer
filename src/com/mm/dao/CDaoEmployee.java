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
	 * ��ţ�employee:1
	 *  ���ܣ�����һ��Ա��
	 * ������cEntityEmployee(EmployeeAccount,EmployeePassword
	 * ,EmployeeName,EmployeePhone
	 * EmployeeSex,EmployeeDepartment,EmployeeJob,EmployeeType) ����ֵ:boolean
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
	 * ��ţ�employee:2 
	 * ���ܣ���ȡ����δɾԱ�� 
	 * �������� 
	 * ����ֵ:CEntityEmployeeArray(�����ֶΣ����漰������)
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
	 * ��ţ�employee:3 ���ܣ��޸�Ա����� ������cEntityEmployee(EmployeeId,EmployeeType)
	 * ����ֵ:boolean
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
	 * ��ţ�employee:4 ���ܣ����ʺ��������Ա��
	 * ������cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * ����ֵ:CEntityEmployee(�����ֶ�)
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
	 * ��ţ�employee:5 ���ܣ����ʺŲ���Ա�� ������cEntityEmployee(EmployeeAccount)
	 * ����ֵ:CEntityEmployee�������ֶΣ�
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
	 * ��ţ�employee:6 
	 * ���ܣ�����������е�Ա�� 
	 * ������cEntityEmployee(EmployeeType)
	 * ����ֵ:CEntityEmployee�������ֶΣ�
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
	 * ��ţ�employee:7 
	 * ���ܣ���Ա�����޸�Ա��
	 * ������cEntityEmployee(�����ֶ�)
	 * ����ֵ:boolean
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
	 * ��ţ�employee:8 
	 * ���ܣ���ȡ����δɾԱ����
	 * ������
	 * ����ֵ:List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryAllEmployeeName(){
		String hql="select employee.m_sEmployeeName from com.mm.entity.CEntityEmployee as employee where EmployeeType!=4";
		List<String> result=this.getHibernateTemplate().find(hql);
		return result;
	}
	
	
	/**
	 * ��ţ�employee:9 
	 * ���ܣ���Ա�����������δɾԱ��
	 * ������CEntityEmployee(EmployeeName)
	 * ����ֵ:CEntityEmployeeArray
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
	 * ��ţ�employee:10 
	 * ���ܣ���Ա�����Ż������δɾԱ��
	 * ������CEntityEmployee(EmployeeDepartment)
	 * ����ֵ:CEntityEmployeeArray
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
