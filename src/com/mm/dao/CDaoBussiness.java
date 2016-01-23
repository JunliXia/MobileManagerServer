package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityBussinessArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoBussiness")
public class CDaoBussiness extends SuperDAO {

	/**
	 * ��ţ�bussiness:1 ���ܣ�����һ������
	 * ������cEntityEmployee(EmployeeId),cEntityBussiness(�����ֶ�) ����ֵ:boolean
	 */
	public boolean saveBussiness(CEntityEmployee cEntityEmployee,
			CEntityBussiness cEntityBussiness) {
		CEntityEmployee findResult = (CEntityEmployee) this
				.getHibernateTemplate().get(CEntityEmployee.class,
						cEntityEmployee.getM_iEmployeeId());

		boolean bisSave = false;

		try {
			this.getHibernateTemplate().save(cEntityBussiness);
			findResult.getcEntityBussinesses().add(cEntityBussiness);
			this.getHibernateTemplate().update(findResult);
			bisSave = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisSave;
	}

	/**
	 * ��ţ�bussiness:2 ���ܣ���Ա���Ż�����еĳ���(�����ֶ�) ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityBussinessArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityBussinessArray queryAllBussinessByEmployeeId(
			CEntityEmployee cEntityEmployee) {
		String hql = "select new CEntityBussiness(bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent,bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime,bussiness.m_iBussinessState) from com.mm.entity.CEntityBussiness as bussiness where EmployeeId=? order by bussiness.m_sBussinessReturnTime desc";
		List<?> findResult = this.getHibernateTemplate().find(hql,
				cEntityEmployee.getM_iEmployeeId());
		CEntityBussinessArray cEntityBussinessArray = new CEntityBussinessArray(
				(List<CEntityBussiness>) findResult);
		return cEntityBussinessArray;
	}

	/**
	 * ��ţ�bussiness:3 ���ܣ�����Ǽ��޸ĵǼ�ʱ�䲢������״̬�޸�Ϊִ����
	 * ������cEntityBussiness(BussinessId,BussinessRegisterTime) ����ֵ:boolean
	 */
	public boolean updateBussinessRegisterTime(CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessRegisterTime=? , BussinessState=? where BussinessId=? ";

		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							cEntityBussiness.getM_sBussinessRegisterTime(),
							MyConstant.Bussiness.BUSSINESS_UNDERWAY,
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * ��ţ�bussiness:4 ���ܣ��ִ�Ŀ�ĵصǼ��޸ĵִ��ַ��ʱ��
	 * ������cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessInTimeAndInAddress(
			CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessInAddress=?,BussinessInTime=? where BussinessId=?";
		System.out.println(hql);
		System.out.println(cEntityBussiness.toString());
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] { cEntityBussiness.getM_sBussinessInAddress(),
							cEntityBussiness.getM_sBussinessInTime(),
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * ��ţ�bussiness:5 ���ܣ��뿪Ŀ�ĵصǼ��޸��뿪��ַ��ʱ��
	 * ������cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessOutTimeAndOutAddress(
			CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessOutAddress=?,BussinessOutTime=? where BussinessId=?";

		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							cEntityBussiness.getM_sBussinessOutAddress(),
							cEntityBussiness.getM_sBussinessOutTime(),
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}

	/**
	 * ��ţ�bussiness:6 ���ܣ���������Ǽ�ʱ�䲢�޸ĳ���״̬Ϊδ���
	 * ������cEntityBussiness(BussinessId,BussinessReturnTime) ����ֵ:boolean
	 */
	public boolean updateBussinessReturn(CEntityBussiness cEntityBussiness) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessReturnTime=?,BussinessState=? where BussinessId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] {
							cEntityBussiness.getM_sBussinessReturnTime(),
							MyConstant.Bussiness.BUSSINESS_WAITCHECK,
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bisUpdate;
	}

	/**
	 * ��ţ�bussiness:7 
	 * ���ܣ���������޸ĳ���״̬ 
	 * ������cEntityBussiness(BussinessId) 
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessStateByBussinessId(
			CEntityBussiness cEntityBussiness, int OperateState) {
		String hql = "update com.mm.entity.CEntityBussiness as bussiness set BussinessState=? where BussinessId=?";
		boolean bisUpdate = false;
		try {
			this.getHibernateTemplate().bulkUpdate(
					hql,
					new Object[] { OperateState,
							cEntityBussiness.getM_iBussinessId() });
			bisUpdate = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;

	}

	
	
	/**
	 * ��ţ�bussiness:8
	 * ���ܣ���Ա���Ż�ȡ����̬�ĳ���(δ�Ǽ�,ִ����)
	 * ������cEntityEmployee(EmployeeId) 
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql = "select new CEntityBussiness(bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent,bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime,bussiness.m_iBussinessState) from com.mm.entity.CEntityBussiness as bussiness where EmployeeId=? and BussinessState in(?,?)";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY});
		Iterator<?> iterator = findResult.listIterator();
		CEntityBussiness result = null;
		if (iterator.hasNext()) {
			result = (CEntityBussiness) iterator.next();
		}
		return result; 
		
	}
	/**
	 * ��ţ�bussiness:9
	 * ���ܣ�������Ż�ó�������(�����ֶ�)
	 * ������CEntityBussiness(BussinessId) 
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessByBussinessId(CEntityBussiness cEntityBussiness){
		String hql = "select new CEntityBussiness(bussiness.BussinessId, bussiness.BussinessSideAddress,bussiness.BussinessContent,bussiness.BussinessRegisterTime,bussiness.BussinessInAddress, bussiness.BussinessInTime,bussiness.BussinessOutAddress, bussiness.BussinessOutTime,bussiness.BussinessReturnTime,bussiness.BussinessState) from com.mm.entity.CEntityBussiness as bussiness where BussinessId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityBussiness.getM_iBussinessId());
		Iterator<?> iterator = findResult.listIterator();
		CEntityBussiness result = null;
		if (iterator.hasNext()) {
			result = (CEntityBussiness) iterator.next();
		}
		return result; 
	}
		
	
	
	/**
	 * ��ţ�bussiness:10
	 * ���ܣ���ȡ����δɾԱ����ִ���г�����Ϣ(δ�Ǽǣ�ִ����)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessRuning(){
		String hql = "select bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent," +
				"bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, " +
				"bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, " +
				"bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime," +
				"bussiness.m_iBussinessState,employee.m_iEmployeeId," +
				"employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityBussiness as bussiness,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussiness.m_iBussinessId and bussiness.m_iBussinessState in(?,?) and employee.m_iEmployeeType!=?";
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY,MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder()
		    .BussinessId((Integer)tuple[0])
		    .BussinessSideAddress((String)tuple[1])
		    .BussinessContent((String)tuple[2])
		    .BussinessRegisterTime((String)tuple[3])
		    .BussinessInAddress((String)tuple[4])
		    .BussinessInTime((String)tuple[5])
		    .BussinessOutAddress((String)tuple[6])
		    .BussinessOutTime((String)tuple[7])
		    .BussinessReturnTime((String)tuple[8])
		    .BussinessState((Integer)tuple[9])
		    .build();
		    
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[10]).EmployeeAccount((String)tuple[11]).EmployeeName((String)tuple[12]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	/**
	 * ��ţ�bussiness:11
	 * ���ܣ���ȡ���������Ա����Ϣ����Ա��û��δ�Ǽǣ�ִ���У�δ��˵ĳ��
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryBussinessAddOkEmployeeInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeeName," +
				"sum(case when employee.m_iEmployeeId=bussiness.cEntityEmployee.m_iEmployeeId " +
				"and bb.m_iBussinessId=bussiness.m_iBussinessId " +
				"and bussiness.m_iBussinessState not in(?,?,?) then 1 else 0 end), " +
				"sum(case when employee.m_iEmployeeId=bussiness.cEntityEmployee.m_iEmployeeId " +
				"and bb.m_iBussinessId=bussiness.m_iBussinessId " +
				"and bussiness.m_iBussinessState  in(?,?,?) then 1 else 0 end)  " +
				"from com.mm.entity.CEntityBussiness as bussiness ," +
				"com.mm.entity.CEntityEmployee as employee "+
				"left join employee.cEntityBussinesses as bb "+
				"group by employee.m_iEmployeeId" ;
//				"or ( employee.m_iEmployeeId not in" +
//				"(select bussiness.cEntityEmployee.m_iEmployeeId from com.mm.entity.CEntityBussiness as bussiness where employee.m_iEmployeeId=bussiness.cEntityEmployee.m_iEmployeeId and bussiness.m_iBussinessState not in(?,?,?)))" +
//				"and employee.m_iEmployeeType!=? " ;
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY,MyConstant.Bussiness.BUSSINESS_WAITCHECK,MyConstant.Bussiness.BUSSINESS_REGISTRATION,MyConstant.Bussiness.BUSSINESS_UNDERWAY,MyConstant.Bussiness.BUSSINESS_WAITCHECK});
		Iterator it=findResult.iterator();
		while (it.hasNext()) {       
			Object[] tuple = (Object[]) it.next();
			JSONObject outjson=new JSONObject();
			outjson.put(MyOpcode.Employee.EmployeeId, (Integer)tuple[0]);
			outjson.put(MyOpcode.Employee.EmployeeAccount, (String)tuple[1]);
			outjson.put(MyOpcode.Employee.EmployeeName,  (String)tuple[2]);
			
//			System.out.println((Integer)tuple[0]+"--"+ (String)tuple[1]+"--"+(String)tuple[2]+"--"+(Long)tuple[3]+"---"+(Long)tuple[4]);
			
//			jsonArray.add(outjson);
		}   
		return findResult;
	}
	
	
	/**
	 * ��ţ�bussiness:12
	 * ���ܣ���ȡ����δɾԱ���Ĵ����������Ϣ(δ���)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessWaitDeal(){
		String hql = "select bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent," +
				"bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, " +
				"bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, " +
				"bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime," +
				"bussiness.m_iBussinessState,employee.m_iEmployeeId," +
				"employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityBussiness as bussiness,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussiness.m_iBussinessId and bussiness.m_iBussinessState=? and employee.m_iEmployeeType!=?";
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_WAITCHECK,MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder()
		    .BussinessId((Integer)tuple[0])
		    .BussinessSideAddress((String)tuple[1])
		    .BussinessContent((String)tuple[2])
		    .BussinessRegisterTime((String)tuple[3])
		    .BussinessInAddress((String)tuple[4])
		    .BussinessInTime((String)tuple[5])
		    .BussinessOutAddress((String)tuple[6])
		    .BussinessOutTime((String)tuple[7])
		    .BussinessReturnTime((String)tuple[8])
		    .BussinessState((Integer)tuple[9])
		    .build();
		    
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[10]).EmployeeAccount((String)tuple[11]).EmployeeName((String)tuple[12]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	
	/**
	 * ��ţ�bussiness:13
	 * ���ܣ�.��ȡ����δɾԱ�����ѽ���������Ϣ�����ͨ������˲�ͨ����
	 * ������
	 * ����ֵ:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllBussinessComplete(){
		String hql = "select bussiness.m_iBussinessId, bussiness.m_sBussinessSideAddress,bussiness.m_sBussinessContent," +
				"bussiness.m_sBussinessRegisterTime,bussiness.m_sBussinessInAddress, " +
				"bussiness.m_sBussinessInTime,bussiness.m_sBussinessOutAddress, " +
				"bussiness.m_sBussinessOutTime,bussiness.m_sBussinessReturnTime," +
				"bussiness.m_iBussinessState,employee.m_iEmployeeId," +
				"employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityBussiness as bussiness,com.mm.entity.CEntityEmployee as employee left join employee.cEntityBussinesses as bb where bb.m_iBussinessId=bussiness.m_iBussinessId and bussiness.m_iBussinessState in(?,?) and employee.m_iEmployeeType!=?";
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Bussiness.BUSSINESS_CHECKPASS,MyConstant.Bussiness.BUSSINESS_CHECKNOPASS,MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder()
		    .BussinessId((Integer)tuple[0])
		    .BussinessSideAddress((String)tuple[1])
		    .BussinessContent((String)tuple[2])
		    .BussinessRegisterTime((String)tuple[3])
		    .BussinessInAddress((String)tuple[4])
		    .BussinessInTime((String)tuple[5])
		    .BussinessOutAddress((String)tuple[6])
		    .BussinessOutTime((String)tuple[7])
		    .BussinessReturnTime((String)tuple[8])
		    .BussinessState((Integer)tuple[9])
		    .build();
		    
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[10]).EmployeeAccount((String)tuple[11]).EmployeeName((String)tuple[12]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Bussiness.Bussiness, cEntityBussiness);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	
}
