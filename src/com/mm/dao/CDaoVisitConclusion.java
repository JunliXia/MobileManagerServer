package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entityarray.CEntityVisitConclusionArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoVisitConclusion")
public class CDaoVisitConclusion extends SuperDAO{

	/**
	 * ��ţ�visitconclusion:1
	 * ���ܣ����Ӱݷ��ܽ�
	 * ������cEntityVisitPlan(VisitPlanId),cEntityEmployee(EmployeeId),cEntityVisitConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitConclusion(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion) {
//		CEntityEmployee findEmployee=(CEntityEmployee)this.getHibernateTemplate().get(CEntityEmployee.class, cEntityEmployee.getM_iEmployeeId());
//		CEntityVisitPlan findVisitPlan=(CEntityVisitPlan)this.getHibernateTemplate().get(CEntityVisitPlan.class, cEntityVisitPlan.getM_iVisitPlanId());
		
		boolean bisSave=false;
		
		try {
			cEntityVisitConclusion.setM_iEmployeeId(cEntityEmployee.getM_iEmployeeId());
			cEntityVisitConclusion.setM_iVisitPlanId(cEntityVisitPlan.getM_iVisitPlanId());
			this.getHibernateTemplate().save(cEntityVisitConclusion);
			
//			findVisitPlan.getcEntityVisitConclusions().add(cEntityVisitConclusion);
//			findEmployee.getcEntityVisitConclusions().add(cEntityVisitConclusion);
//			this.getHibernateTemplate().update(findVisitPlan);
//			this.getHibernateTemplate().update(findEmployee);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}
	
	
	/**
	 * ��ţ�visitconclusion:2
	 * ���ܣ���Ա���Ż�����еİݷ��ܽ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitConclusionArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitConclusionArray queryAllVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee) {
		String hql="from com.mm.entity.CEntityVisitConclusion as visitconclusion where EmployeeId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityVisitConclusionArray cEntityVisitConclusionArray=new CEntityVisitConclusionArray((List<CEntityVisitConclusion>) findResult);
		return cEntityVisitConclusionArray;
	}
	
	/**
	 * ��ţ�visitconclusion:3
	 * ���ܣ��޸İݷ��ܽ����˽��
	 * ������cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionConclusionVisitCheck(CEntityVisitConclusion cEntityVisitConclusion,int OperateState){
		String hql="update com.mm.entity.CEntityVisitConclusion as visitconclusion set VisitCheck=? where VisitConclusionId=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{OperateState,cEntityVisitConclusion.getM_iVisitConclusionId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}
	
	
	
	/**
	 * ��ţ�visitconclusion:4
	 * ���ܣ����ݷüƻ��Ż����δ��˵İݷ��ܽ�
	 * ������CEntityVisitPlan(VisitPlanId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionNocheckByVisitPlanId(CEntityVisitPlan cEntityVisitPlan){
		String hql="from com.mm.entity.CEntityVisitConclusion as visitconclusion where VisitPlanId=? and VisitCheck=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityVisitPlan.getM_iVisitPlanId(),MyConstant.VisitConclusion.VISITCONCLUSION_WAITCHECK});
		Iterator<?> iterator=findResult.iterator();
		CEntityVisitConclusion result=null;
		if(iterator.hasNext()){
			result=(CEntityVisitConclusion) iterator.next();
		}
//		System.out.println(result.toString());
		return result;
		
	}
	
	/**
	 * ��ţ�visitconclusion:5
	 * ���ܣ���Ա���Ż�������аݷ��ܽ��Լ��ͻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:HashedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryVisitConclusionWithClientInfoByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select visitconclusion.m_iVisitConclusionId, visitconclusion.m_iVisitCheck,visitconclusion.m_sVisitSubmitTime, visitconclusion.m_sVisitSummary,visitconclusion.m_sVisitCommand, visitconclusion.m_sVisitAccessoryPath ,visitconclusion.m_iVisitPlanId,visitplan.cEntityClient.m_sClientName from com.mm.entity.CEntityVisitConclusion as visitconclusion,com.mm.entity.CEntityVisitPlan as visitplan where visitconclusion.m_iVisitPlanId=visitplan.m_iVisitPlanId and visitconclusion.m_iEmployeeId=? order by VisitConclusionId desc";
		List findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
//		    CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion.Builder().VisitConclusionId((Integer)tuple[0]).VisitCheck((Integer)tuple[1]).VisitSubmitTime((String)tuple[2]).VisitSummary((String)tuple[3]).VisitCommand((String)tuple[4]).VisitAccessoryPath((String)tuple[5]).v.build();
		    CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion((Integer)tuple[0], (Integer)tuple[1], (String)tuple[2], (String)tuple[3], (String)tuple[4], (String)tuple[5], (Integer)tuple[6]);
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientName((String)tuple[7]).build();
		    map.put(MyOpcode.VisitConclusion.VisitConclusion, cEntityVisitConclusion);
		    map.put(MyOpcode.Client.Client,cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    
			 

		 return maps;
	}
	
	
	/**
	 * ��ţ�visitconclusion:6
	 * ���ܣ����ݷ��ܽ�Ż�ðݷ��ܽ�
	 * ������CEntityVisitConclusion(VisitConclusionId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionByVisitConclusionId(CEntityVisitConclusion cEntityVisitConclusion){
		CEntityVisitConclusion findResult=(CEntityVisitConclusion) this.getHibernateTemplate().get(CEntityVisitConclusion.class, cEntityVisitConclusion.getM_iVisitConclusionId());
		return findResult;
	}
	
	
	
//	public static void main(String[] args) {
////	CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceDate("2015/10/30").build();
//	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//	"applicationContext.xml");
//	CDaoVisitConclusion tt = (CDaoVisitConclusion) ctx.getBean("cDaoVisitConclusion");
//	tt.queryAllVisitConclusionInfo();
//}
	
	/**
	 * ��ţ�visitconclusion:7
	 * ���ܣ���ȡ����δɾԱ���İݷ��ܽἰ��Ϣ
	 * ������
	 * ����ֵ:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllVisitConclusionInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount," +
				"visitplan.cEntityClient.m_iClientId," +
				"visitplan.m_iVisitPlanId," +
				"visitconclusion.m_iVisitConclusionId, visitconclusion.m_iVisitCheck,visitconclusion.m_sVisitSubmitTime, visitconclusion.m_sVisitSummary,visitconclusion.m_sVisitCommand, visitconclusion.m_sVisitAccessoryPath ,employee.m_sEmployeeName,visitplan.cEntityClient.m_sClientName " +
				"from com.mm.entity.CEntityVisitConclusion as visitconclusion ," +
				"com.mm.entity.CEntityVisitPlan as visitplan ," +
				"com.mm.entity.CEntityEmployee as employee " +
				"left join visitplan.cEntityVisitConclusions as bb " +
				"left join employee.cEntityVisitConclusions as aa " +
				"where bb.m_iVisitConclusionId = visitconclusion.m_iVisitConclusionId  and  " +
				"bb.m_iVisitConclusionId=aa.m_iVisitConclusionId and employee.m_iEmployeeType!=? " +
				"order by visitconclusion.m_sVisitSubmitTime desc";
		
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{MyConstant.Employee.EMPLOYEE_DEL});
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[10]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[2]).ClientName((String)tuple[11]).build();
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[3]).build();
		    CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion.Builder().VisitConclusionId((Integer)tuple[4]).VisitCheck((Integer)tuple[5]).VisitSubmitTime((String)tuple[6]).VisitSummary((String)tuple[7]).VisitCommand((String)tuple[8]).VisitAccessoryPath((String)tuple[9]).build();
		   
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    map.put(MyOpcode.VisitConclusion.VisitConclusion, cEntityVisitConclusion);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
