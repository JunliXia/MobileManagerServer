 package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoClientSubmit")
public class CDaoClientSubmit extends SuperDAO{
	
	/**
	 * ��ţ�clientsubmit:1
	 * ���ܣ�����һ���ͻ��ύ��ͬʱ������һ���ͻ���
	 * ������cEntityClient(�����ֶ�),cEntityEmployee(EmployeeId),cEntityClientSubmit(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveClientSubmit(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityClientSubmit cEntityClientSubmit) {
		CEntityEmployee findEmployee=(CEntityEmployee)this.getHibernateTemplate().get(CEntityEmployee.class, cEntityEmployee.getM_iEmployeeId());
		cEntityClientSubmit.setcEntityClient(cEntityClient);
		

		boolean bisSave=false;
		try {
			this.getHibernateTemplate().save(cEntityClient);
			this.getHibernateTemplate().save(cEntityClientSubmit);
			findEmployee.getcEntityClientSubmits().add(cEntityClientSubmit);
			this.getHibernateTemplate().update(findEmployee);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisSave;
	}
	
	/**
	 * ��ţ�clientsubmit:2
	 * ���ܣ����ύ״̬������еĿͻ��ύ
	 * ������cEntityClientSubmit(ClientSubmitState)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientSubmitArray queryAllClientSubmitByClientSubmitState(CEntityClientSubmit cEntityClientSubmit) {
		String hql="from com.mm.entity.CEntityClientSubmit as clientsubmit where ClientSubmitState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityClientSubmit.getM_iClientSubmitState());
		CEntityClientSubmitArray cEntityClientSubmitArray=new CEntityClientSubmitArray((List<CEntityClientSubmit>) findResult);
		
		return cEntityClientSubmitArray;
	}
	

	/**
	 * ��ţ�clientsubmit:3
	 * ���ܣ����ͻ����޸Ŀͻ��ύ״̬
	 * ������cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.ClientSubmit.*)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	public boolean updateClientSubmitStateByClientId(CEntityClient cEntityClient,int OperateState){
		String hql="update com.mm.entity.CEntityClientSubmit as clientsubmit set ClientSubmitState=? where ClientId=?";
		
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{OperateState,cEntityClient.getM_iClientId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}
	
	/**
	 * ��ţ�clientsubmit:4
	 * ���ܣ���Ա���Ż�����еĿͻ��ύ��¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientSubmitArray queryAllClientSubmitForEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityClientSubmit as clientsubmit where EmployeeId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityClientSubmitArray cEntityClientSubmitArray=new CEntityClientSubmitArray((List<CEntityClientSubmit>) findResult);
		
		return cEntityClientSubmitArray;
	}
	
	/**
	 * ��ţ�clientsubmit:5
	 * ���ܣ�������е�δ����ύ��¼����ͻ���ϢԱ����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllNocheckClientWithClientInfoAndEmployeeInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeName,employee.m_sEmployeeAccount ,clientsubmit.m_iClientSubmitId,clientsubmit.m_sClientSubmitTime,client.m_iClientId,client.m_sClientName,client.m_sClientCompany,client.m_sClientAddress,client.m_sClientPhone,client.m_sClientArea,client.m_iClientState from com.mm.entity.CEntityEmployee as employee,com.mm.entity.CEntityClientSubmit as clientsubmit ,com.mm.entity.CEntityClient as client left join employee.cEntityClientSubmits as bb where bb.m_iClientSubmitId=clientsubmit.m_iClientSubmitId and clientsubmit.cEntityClient.m_iClientId=client.m_iClientId and clientsubmit.m_iClientSubmitState=? order by clientsubmit.m_sClientSubmitTime desc";
		List findResult=this.getHibernateTemplate().find(hql,MyConstant.ClientSubmit.CLIENTSUBMIT_WAITCHECK);
		Iterator it=findResult.iterator();
		LinkedMap maps=new LinkedMap();
		int i=0;
		while(it.hasNext()){
			Object []tuple=(Object[])it.next();
			LinkedMap map=new LinkedMap();
			CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeName((String)tuple[1]).EmployeeAccount((String)tuple[2]).build();
			CEntityClientSubmit cEntityClientSubmit=new CEntityClientSubmit.Builder().ClientSubmitId((Integer)tuple[3]).ClientSubmitTime((String)tuple[4]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[5]).ClientName((String)tuple[6]).ClientCompany((String)tuple[7]).ClientAddress((String)tuple[8]).ClientPhone((String)tuple[9]).ClientArea((String)tuple[10]).ClientState((Integer)tuple[11]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.ClientSubmit.ClientSubmit, cEntityClientSubmit);
		    map.put(MyOpcode.Client.Client,cEntityClient);
		    maps.put(i, map);
		    i++;
		}
		return maps;
	}
	
	
	/**
	 * ��ţ�clientsubmit:6
	 * ���ܣ����δ��˿ͻ��ύ����
	 * ������
	 * ����ֵ:int
	 */
	public int queryClientSubmitNocheckNumber(){
		String hql="select count(*) from CEntityClientSubmit where m_iClientSubmitState=0";
//		 count=((Long)session.createQuery("select count(RoomId) from Room where RoomLockState!=-1").iterate().next()).intValue(); 
		int count=((Long) this.getHibernateTemplate().find(hql).listIterator().next()).intValue();
		return count;
	}
	
	
	
}
