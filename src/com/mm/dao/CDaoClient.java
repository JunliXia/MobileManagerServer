package com.mm.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityClientArray;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoClient")
public class CDaoClient extends SuperDAO {

	/**
	 * ��ţ�client:1
	 * ���ܣ����ӿͻ�
	 * ������cEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public  boolean saveClient(CEntityClient cEntityClient) {
	
		boolean bisSave=false;
		
		try {
			this.getHibernateTemplate().save(cEntityClient);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}
	
	/**
	 * ��ţ�client:2
	 * ���ܣ���Ա���Ż�����еĿͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClientByEmployeeId(CEntityEmployee cEntityEmployee) {
		String hql="from com.mm.entity.CEntityClient as client where EmployeeId=? and ClientState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Client.CLIENT_DISTRIBUTED});
		
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�client:3
	 * ���ܣ��޸Ŀͻ�Ա����(���޸Ŀͻ�״̬Ϊ�ѷ���)
	 * ������cEntityEmployee(EmployeeId),cEntityClient(ClientId)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean updateClientEmployeeId(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee) {
		String hql="update com.mm.entity.CEntityClient as client set EmployeeId=?,ClientState=? where clientId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Client.CLIENT_DISTRIBUTED,cEntityClient.getM_iClientId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	/**
	 * ��ţ�client:4
	 * ���ܣ����ͻ�״̬������еĿͻ�
	 * ������cEntityClient(ClientState)
	 * ����ֵ:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClientByClientState(CEntityClient cEntityClient) {
		String hql="from com.mm.entity.CEntityClient as client where ClientState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityClient.getM_iClientState());
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�client:5
	 * ���ܣ����ͻ����޸Ŀͻ�״̬
	 * ������cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.Client.*)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean updateClientStateByClientId(CEntityClient cEntityClient,int OperateState){
		String hql="update com.mm.entity.CEntityClient as client set ClientState=? where ClientId=?";
		
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
	 * ��ţ�client:6
	 * ���ܣ����ͻ��Ż�ÿͻ�����
	 * ������cEntityClient(ClientId)
	 * ����ֵ:CEntityClient
	 */
	public CEntityClient queryClientByClientId(CEntityClient cEntityClient){
		CEntityClient findResult=(CEntityClient)this.getHibernateTemplate().get(CEntityClient.class, cEntityClient.getM_iClientId());
		return findResult;
	}
	
	/**
	 * ��ţ�client:7
	 * ���ܣ���Ա���Ż�ȡ���ڿͻ��ύ�е����пͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryClientForSubmitEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityClientSubmit as clientsubmit where EmployeeId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityClientSubmitArray cEntityClientSubmitArray=new CEntityClientSubmitArray((List<CEntityClientSubmit>) findResult);
		
		List<CEntityClient> cEntityClients=new ArrayList<CEntityClient>();
		for(int i=0;i<cEntityClientSubmitArray.getSize();i++){
			cEntityClients.add(cEntityClientSubmitArray.getItem(i).getcEntityClient());
		}
		CEntityClientArray cEntityClientArray=new CEntityClientArray(cEntityClients);
		return cEntityClientArray;
	}
	/**
	 * ��ţ�client:8
	 * ���ܣ���ѯ�������͵Ŀͻ�
	 * ��������
	 * ����ֵ:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClient(){
		String hql="from com.mm.entity.CEntityClient as  client";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		return cEntityClientArray;
	}

	
	/**
	 * ��ţ�client:9
	 * ���ܣ��޸Ŀͻ�����
	 * ������CEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean updateClient(CEntityClient cEntityClient){
		String hql="update com.mm.entity.CEntityClient as client set ClientName=?,ClientCompany=?,ClientPhone=?,ClientArea=?,ClientAddress=? where ClientId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityClient.getM_sClientName(),cEntityClient.getM_sClientCompany(),cEntityClient.getM_sClientPhone(),cEntityClient.getM_sClientArea(),cEntityClient.getM_sClientAddress(),cEntityClient.getM_iClientId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;

		
	}
	
	/**
	 * ��ţ�client:10
	 * ���ܣ���Ա���Ż�ȡ���ѷ���ͻ���
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryAllClientNameByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select client.m_sClientName from com.mm.entity.CEntityClient as client where EmployeeId=?";
		List<String> result=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		return result;
	}
	
	/**
	 * ��ţ�client:11
	 * ���ܣ����ͻ�����ȡ�ͻ�����
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClientByClientName(CEntityClient cEntityClient){
		String hql="from com.mm.entity.CEntityClient as client where ClientName=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityClient.getM_sClientName());
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		return cEntityClientArray;
	}
	
	
	/**
	 * ��ţ�client:12
	 * ���ܣ����ͻ��Ž�Ա������գ����޸Ŀͻ�״̬Ϊδ����
	 * ������CEntityClient(ClientId)
	 * ����ֵ:boolean
	 */
	public boolean updateClientEmployeeIdNUll(CEntityClient cEntityClient){
		String hql="update com.mm.entity.CEntityClient as client set EmployeeId=null,ClientState=0 where ClientId=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,cEntityClient.getM_iClientId());
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	
	}
//	public static void main(String[] args) {
//	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//	"applicationContext.xml");
//	CDaoClient tt = (CDaoClient) ctx.getBean("cDaoClient");
//	tt.queryClientNumberOfEmployeeInfo();
//}
	
	
	/**
	 * ��ţ�client:13
	 * ���ܣ�ͳ��Ա���ţ�Ա���˺ţ�Ա�������ͻ�ӵ������
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryClientNumberOfEmployeeInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,sum(case when bb.m_iClientId=client.m_iClientId then 1 else 0  end)  from com.mm.entity.CEntityEmployee as employee ,com.mm.entity.CEntityClient as client left join employee.cEntityClients as bb where employee.m_iEmployeeType!=4  group by employee.m_iEmployeeId order by sum(case when bb.m_iClientId=client.m_iClientId then 1 else 0  end) desc ";
		List findResult=this.getHibernateTemplate().find(hql);
		return findResult;
	}
	
	/**
	 * ��ţ�client:14
	 * ���ܣ���ѯ���ѷ���ͻ���Ϣ�Լ���Ա����Ϣ
	 * ������
	 * ����ֵ:HashedMap(CEntityEmployee,CEntityClient)
	 */
	@SuppressWarnings("unchecked")
	public HashedMap queryClientDistributionWithEmployeeInfo(){
		String hql="select  employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,client.m_iClientId,client.m_sClientName,client.m_sClientCompany,client.m_sClientAddress,client.m_sClientPhone,client.m_sClientArea,client.m_iClientState from com.mm.entity.CEntityEmployee as employee,com.mm.entity.CEntityClient as client left join employee.cEntityClients as bb where bb.m_iClientId=client.m_iClientId and client.m_iClientState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,MyConstant.Client.CLIENT_DISTRIBUTED);
		Iterator it = findResult.iterator();  
		HashedMap maps=new HashedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    HashedMap map=new HashedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[3]).ClientName((String)tuple[4]).ClientCompany((String)tuple[5]).ClientAddress((String)tuple[6]).ClientPhone((String)tuple[7]).ClientArea((String)tuple[8]).ClientState((Integer)tuple[9]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    

		 return maps;
	}
	
	
	/**
	 * ��ţ�client:15
	 * ���ܣ���ѯδ����ͻ��Ŀͻ��ţ��ͻ������ͻ���˾
	 * ������
	 * ����ֵ:List(ClientId,ClientName,ClientCompany)
	 */
	@SuppressWarnings("unchecked")
	public List queryClientUnDistributionInfo(){
		String hql="select client.m_iClientId,client.m_sClientName,client.m_sClientCompany from com.mm.entity.CEntityClient as client where client.m_iClientState=?";
		List findResult=this.getHibernateTemplate().find(hql,MyConstant.Client.CLIENT_UNDISTRIBUTED);
		return findResult;
	}
	
	
	
}