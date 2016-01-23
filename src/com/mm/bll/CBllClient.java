package com.mm.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitDelay;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.entityarray.CEntityClientArray;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.tool.MyConstant;

@Component("cBllClient")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CBllClient {
	private IDaoFrame iDaoFrame;

	@Resource(name = "cDaoFrameImpl")
	public void setiDaoFrame(IDaoFrame iDaoFrame) {
		this.iDaoFrame = iDaoFrame;
	}
	
	
	/**
	 * ��ţ�clientmodule:1 
	 * ���ܣ������ͻ�
	 * ������cEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean createClient(CEntityClient cEntityClient){
		//�����ͻ�
		boolean bisCreate=iDaoFrame.saveClient(cEntityClient);
		return bisCreate;
		
	}
	
	/**
	 * ��ţ�clientmodule:2 
	 * ���ܣ�����ͻ�
	 * ������cEntityClient(ClientId),cEntityEmployee(EmployeeId),cEntityVisitPlan(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean distributionClient(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan){
		//�ͻ������������Ա,�ͻ���״̬��Ϊδ������תΪ�ѷ���
		boolean bisUpdate=iDaoFrame.updateClientEmployeeIdAndClientState(cEntityClient, cEntityEmployee);
		//���ɰݷüƻ���¼
		boolean bisSave=iDaoFrame.saveVisitPlan(cEntityClient, cEntityEmployee, cEntityVisitPlan);

		boolean bisDistribution=bisUpdate&&bisSave;
		return bisDistribution;
		
	}

	
	/**
	 * ��ţ�clientmodule:3 
	 * ���ܣ��ύ�ݷ�
	 * ������cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean submitVisit(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion){
		//�ݷüƻ�״̬��Ϊδ���
		boolean bisUpdatePlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_WAITCHECK);
		//�ɹ����ɰݷ��ܽ��¼
		boolean bisSave=iDaoFrame.saveVisitConclusion(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		
		boolean bisSubmot=bisUpdatePlanState&&bisSave;
		return bisSubmot;
		
	}
	
	
	/**
	 * ��ţ�clientmodule:4 
	 * ���ܣ���˿ͻ��ݷ�
	 * ������ccEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean checkVisitConclusion(CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion,int OperateState){
		//���ݷüƻ�������ѭ���ݷã���״̬��Ϊδ��ʼ�����޸İݷ�ʱ��
		CEntityVisitPlan findPlan=iDaoFrame.queryVisitPlanByVisitPlanId(cEntityVisitPlan.getM_iVisitPlanId());
		boolean bisUpdatePlanState=false;
		if(findPlan.getM_iVisitPlanCycle()==MyConstant.VisitPlanCycle.VISITPLANCYCLE_CYCLE){
			String newStarttime=getCycleStartTime(findPlan);
			String newEndtime=getCycleEndTime(newStarttime, findPlan.getM_iVisitPlanDays());
			String newPubdate=getNewPubdate();
			CEntityVisitPlan updatePlan=new CEntityVisitPlan.Builder().VisitPlanId(findPlan.getM_iVisitPlanId()).VisitPlanStartTime(newStarttime).VisitPlanEndTime(newEndtime).VisitPlanPubdate(newPubdate).build();
			bisUpdatePlanState=iDaoFrame.updateVisitTime(updatePlan);
		}else if(findPlan.getM_iVisitPlanCycle()==MyConstant.VisitPlanCycle.VISITPLANCYCLE_NOCYCLE){
			//�ݷüƻ�δ���״̬��Ϊ�����״̬
			bisUpdatePlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(findPlan, MyConstant.VisitPlan.VISITPLAN_CHECK);
		}
		//�ݷ��ܽ����˽��תΪͨ����ͨ��
		boolean bisUpdateCheck=iDaoFrame.updateMissionConclusionVisitCheck(cEntityVisitConclusion, OperateState);
		
		
		boolean bisCheck=bisUpdatePlanState&&bisUpdateCheck;
		return bisCheck;
		
	}
	
	
	/**
	 * ��ţ�clientmodule:5
	 * ���ܣ��ύ�ͻ�
	 * ������cEntityClient(�����ֶ�),cEntityEmployee(EmployeeId)
	 * ����ֵ:boolean
	 */
	public boolean submitCliet(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient){
		//�ɹ��ύ�ͻ����ͻ�״̬Ϊδ���,�ɹ����ɿͻ��ύ��¼
		CEntityClientSubmit cEntityClientSubmit=new CEntityClientSubmit.Builder().ClientSubmitTime(getNewPubdate()).build();
		boolean bisSubmit=iDaoFrame.saveClientSubmit(cEntityClient, cEntityEmployee, cEntityClientSubmit);
		return bisSubmit;
	}
	
	
	
	/**
	 * ��ţ�clientmodule:6
	 * ���ܣ��ͻ�����
	 * ������cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.ClientSubmit.*)
	 * ����ֵ:boolean
	 */
	public boolean checkClient(CEntityClient cEntityClient,int OperateState){
	
		//�ͻ��ύ��¼����˽��תΪͨ����ͨ��
		boolean bisUpdateClientSubmitState=iDaoFrame.updateClientSubmitStateByClientId(cEntityClient, OperateState);
		//�ͻ�״̬תΪδ�������ɾ��
		int m_iClientStateOper = 0;
		if(OperateState==MyConstant.ClientSubmit.CLIENTSUBMIT_PASS){
			m_iClientStateOper=MyConstant.Client.CLIENT_UNDISTRIBUTED;
		}else if(OperateState==MyConstant.ClientSubmit.CLIENTSUBMIT_NOPASS){
			m_iClientStateOper=MyConstant.Client.CLIENT_DEL;
		}
		boolean bisUpdateClientState=iDaoFrame.updateClientStateByClientId(cEntityClient, m_iClientStateOper);
		
		boolean bisCheck=bisUpdateClientSubmitState&&bisUpdateClientState;
		return bisCheck;
		
	}
	
	/**
	 * ��ţ�clientmodule:7
	 * ���ܣ�ѭ���ݷ�
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * ����ֵ:boolean
	 */
	public boolean setupVisitCycle(CEntityVisitPlan cEntityVisitPlan){
		//�ݷüƻ�ѭ��״̬��Ϊ��ѭ��,�Լ�����ѭ������
		boolean bisSetup=iDaoFrame.updateVisitPlanCyclesByVisitPlanId(cEntityVisitPlan);
		
		return bisSetup;
	}
	
	/**
	 * ��ţ�clientmodule:8
	 * ���ܣ����ڰݷüƻ�
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanEndTime)
	 * 	   cEntityEmployee(EmployeeId)
	 * ����ֵ:boolean
	 */
	public boolean delayVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityEmployee cEntityEmployee){
		//���ں�İݷüƻ��ı�����ݷ�����.�ѹ��ڵ�״̬תΪִ����״̬
		boolean bisUpdateVisitEndTime=iDaoFrame.updateVisitPlanEndTime(cEntityVisitPlan);
		//����һ�����ڼ�¼
		CEntityVisitDelay cEntityVisitDelay=new CEntityVisitDelay.Builder().VisitDelayTime(getNewPubdate()).VisitDelayDeadline(cEntityVisitPlan.getM_sVisitPlanEndTime()).build();
		boolean bisSave=iDaoFrame.saveVisitDealy(cEntityEmployee, cEntityVisitPlan, cEntityVisitDelay);
	
		boolean bisDelay=bisUpdateVisitEndTime&&bisSave;
		return bisDelay;
	}
	
	
	
	
	/**
	 * ��ţ�clientmodule:9
	 * ���ܣ������ݷüƻ�
	 * ������cEntityVisitPlan(VisitPlanId)
	 * ����ֵ:boolean
	 */
	public boolean abandonVisitPlan(CEntityVisitPlan cEntityVisitPlan){
		boolean bisAbandon=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_FAILURE);
		return bisAbandon;
	}
	
	
	
	/**
	 * ��ţ�clientmodule:10
	 * ���ܣ������ݷüƻ�
	 * ������cEntityVisitPlan(VisitPlanId)
	 * 	   cEntityVisitUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo){
		//�ݷüƻ�״̬תΪ�ѳ���
		boolean bisUpdateVisitPlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_UNDO);
		//����һ���ݷüƻ�������¼
		boolean bisSaveVisitUndo=iDaoFrame.saveVisitUndo(cEntityVisitPlan, cEntityVisitUndo);
		//���������й��󶨵Ļ��������
		CEntityVisitPlan findResult=iDaoFrame.queryVisitPlanByVisitPlanId(cEntityVisitPlan.getM_iVisitPlanId());
		boolean bisUpdateBandType=false;
		if(findResult.getM_iVisitBussinessBandState()==MyConstant.VisitPlan.VISITPLAN_BUSSINESSBAND){
			CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE).BussinessObjectId(cEntityVisitPlan.getM_iVisitPlanId()).BussinessBindType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_NOBAND).build();
			bisUpdateBandType=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		}else if(findResult.getM_iVisitBussinessBandState()==MyConstant.Mission.MISSION_BUSSINESSNOBAND){
			bisUpdateBandType=true;
		}
		
		boolean bisUndo=bisUpdateVisitPlanState&&bisSaveVisitUndo&&bisUpdateBandType;
		
		return bisUndo;
	}
	
	/**
	 * ��ţ�clientmodule:11
	 * ���ܣ����ذݷüƻ�
	 * ������cEntityVisitUndo(VisitUndoId)
	 * ����ֵ:boolean
	 */
	public boolean recallVisitPlan(CEntityVisitUndo cEntityVisitUndo){
		//�����ݷüƻ�״̬���ѳ���תΪ�ѳ���
		boolean bisUpdateUndoType=iDaoFrame.updateVisitUndoStateByVisitUndoId(cEntityVisitUndo, MyConstant.VisitUndo.VISITUNDO_RECALL);
		//���صİݷüƻ�תΪԭ����ǰ��״̬
		CEntityVisitUndo findUndo=iDaoFrame.queryVisitUndoByVisitUndoId(cEntityVisitUndo);
		CEntityVisitPlan cEntityVisitPlan=findUndo.getcEntityVisitPlan();
		boolean bisUpdateVisitState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, findUndo.getM_iVisitUndoRecallType());
		//���������й��󶨵Ļ���������
		boolean bisBand=false;
		if(cEntityVisitPlan.getM_iVisitBussinessBandState()==MyConstant.VisitPlan.VISITPLAN_BUSSINESSNOBAND){
			CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE).BussinessObjectId(cEntityVisitPlan.getM_iVisitPlanId()).BussinessBindType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_BAND).build();
			bisBand=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		}else if(cEntityVisitPlan.getM_iVisitBussinessBandState()==MyConstant.VisitPlan.VISITPLAN_BUSSINESSBAND){
			bisBand=true;
		}
		
		boolean bisRecall=bisUpdateUndoType&&bisUpdateVisitState&&bisBand;
		return bisRecall;
	}
	
	
	/**
	 * ��ţ�clientmodule:12
	 * ���ܣ�ɾ���ͻ�
	 * ������cEntityClient(ClientId)
	 * ����ֵ:boolean
	 */
	public boolean delClient(CEntityClient cEntityClient){
		//�ͻ�״̬�޸�Ϊ��ɾ��
		boolean bisUpdateClientState=iDaoFrame.updateClientStateByClientId(cEntityClient, MyConstant.Client.CLIENT_DEL);
		//�ݷüƻ�״̬תΪ�ѳ���������̬��״̬��δ��ʼ��ִ���С�δ��ˡ��ѹ��ڣ�
		CEntityVisitPlan cEntityVisitPlan=iDaoFrame.queryVisitPlanRunningStateByClientId(cEntityClient);
		boolean bisUpdateVisitPlanState=false;
		boolean bisSaveUndo=false;
		boolean bisUpdateBandType=false;
		if(cEntityVisitPlan!=null){
			bisUpdateVisitPlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_UNDO);
			
			//��������󶨵Ļ������
			if(cEntityVisitPlan.getM_iVisitBussinessBandState()==MyConstant.VisitPlan.VISITPLAN_BUSSINESSBAND){
				CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE).BussinessObjectId(cEntityVisitPlan.getM_iVisitPlanId()).BussinessBindType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_NOBAND).build();
				bisUpdateBandType=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
			}else if(cEntityVisitPlan.getM_iVisitBussinessBandState()==MyConstant.Mission.MISSION_BUSSINESSNOBAND){
				bisUpdateBandType=true;
			}
			
			//����ϵͳ�Դ������ͻ��ѱ�ɾ������ԭ��ĳ�����¼
			CEntityVisitUndo cEntityVisitUndo=new CEntityVisitUndo.Builder().VisitUndoReason(MyConstant.Reason.REASON_CLIENTDEL).VisitUndoTime(getNewPubdate()).VisitUndoRecallType(cEntityVisitPlan.getM_iVisitPlanState()).VisitUndoType(MyConstant.VisitUndo.VISITUNDO_UNDO).build();
			bisSaveUndo=iDaoFrame.saveVisitUndo(cEntityVisitPlan, cEntityVisitUndo);
		
		}else{
			bisUpdateVisitPlanState=true;
			bisUpdateBandType=true;
			bisSaveUndo=true;
		}
		
		boolean bisDel=bisUpdateClientState&&bisUpdateBandType&&bisUpdateVisitPlanState&&bisSaveUndo;
		return bisDel;
		
	}
	
	
	
	/**
	 * ��ţ�clientmodule:13
	 * ���ܣ�����δ��ʼ�ݷüƻ� 
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetNoStartVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanByEmployeeIdAndVisitPlanState(cEntityEmployee, MyConstant.VisitPlan.VISITPLAN_NOTSTART);
		return cEntityVisitPlanArray;
	}
	
	/**
	 * ��ţ�clientmodule:14
	 * ���ܣ�����ִ���аݷüƻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetUnderwayVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanRunningStateByEmployeeId(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	/**
	 * ��ţ�clientmodule:15
	 * ���ܣ�������ֹ̬�ݷüƻ���δ���,�����,�ѳ���,��ʧ�ܣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetCompleteVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanCompleteByEmployeeIs(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	
	
	/**
	 * ��ţ�clientmodule:16
	 * ���ܣ��鿴�ͻ�����
	 * ������cEntityClient(ClientId)
	 * ����ֵ:CEntityClient
	 */
	public CEntityClient GetClientInfo(CEntityClient cEntityClient){
		CEntityClient findResult=iDaoFrame.queryClientByClientId(cEntityClient);
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:17
	 * ���ܣ��鿴�ͻ��ύ��¼
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientSubmitArray GetClientSubmit(CEntityEmployee cEntityEmployee){
		CEntityClientSubmitArray cEntityClientSubmitArray=iDaoFrame.queryAllClientSubmitForEmployeeId(cEntityEmployee);
		return cEntityClientSubmitArray;
	}
	/**
	 * ��ţ�clientmodule:18
	 * ���ܣ�����ݷÿͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�clientmodule:19
	 * ���ܣ��õ����еĿͻ�
	 * ��������
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetAllClient(){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClient();
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�clientmodule:20
	 * ���ܣ���Ա���Ż�����пͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetAllEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	/**
	 * ��ţ�clientmodule:21
	 * ���ܣ��޸Ŀͻ�����
	 * ������CEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean UpdateClient(CEntityClient cEntityClient){
		boolean bisUpdate=iDaoFrame.updateClient(cEntityClient);
		return bisUpdate;
	}
	/**
	 * ��ţ�clientmodule:22
	 * ���ܣ�����Ա�õ�Ա������Ŀͻ���
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:List<String>
	 */
	public List<String> GetAllEmployeeClientName(CEntityEmployee cEntityEmployee){
		List<String> result=iDaoFrame.queryAllClientNameByEmployeeId(cEntityEmployee);
		return result;
	}
	/**
	 * ��ţ�clientmodule:23
	 * ���ܣ�����Ա���ͻ����������еĿͻ���Ϣ
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetAllClientByClientName(CEntityClient cEntityClient){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByClientName(cEntityClient);
		return cEntityClientArray;
	}
	/**
	 * ��ţ�clientmodule:24
	 * ���ܣ�����Աȡ������
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean cancleClientDistuibution(CEntityClient cEntityClient){
		boolean bisUpdate=iDaoFrame.updateClientEmployeeIdNUll(cEntityClient);
		return bisUpdate;
	}
	/**
	 * ��ţ�clientmodule:25
	 * ���ܣ�����Ա���Ա�������аݷüƻ����ͻ���Ϣ
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	public HashedMap getAllEmployeeVisitPlan(CEntityEmployee cEntityEmployee){
		HashedMap findResult=iDaoFrame.queryAllVisitPlanAndClientInfoByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:26
	 * ���ܣ�����Ա�鿴�ݷüƻ�����
	 * ������CEntityVisitPlan(VisitPlanId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusionDetail(CEntityVisitPlan cEntityVisitPlan){
		CEntityVisitConclusion cEntityVisitConclusion=iDaoFrame.queryVisitConclusionNocheckByVisitPlanId(cEntityVisitPlan);
		return cEntityVisitConclusion;
	}
	/**
	 * ��ţ�clientmodule:27
	 * ���ܣ�����Ա�鿴��Ա�����еİݷ��ܽ�
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public LinkedMap getEmployeeVisitConclusion(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryVisitConclusionWithClientInfoByEmployeeId(cEntityEmployee);
		
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:28
	 * ���ܣ�����Ա�鿴�ݷüƻ�����
	 * ������CEntityVisitConclusion(VisitConclusionId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusion(CEntityVisitConclusion cEntityVisitConclusion){
		CEntityVisitConclusion findReuslt=iDaoFrame.queryVisitConclusionByVisitConclusionId(cEntityVisitConclusion);
		return findReuslt;
	}
	/**
	 * ��ţ�clientmodule:29
	 * ���ܣ�����Ա��ø�Ա�����еİݷ�����
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitDealyArray
	 */
	public LinkedMap getEmployeeVisitDealy(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllVisitDelayByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:30
	 * ���ܣ�����Ա�������δ����Ŀͻ�
	 * ��������
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray getNodistributionClient(){
		CEntityClient cEntityClient=new CEntityClient.Builder().ClientState(MyConstant.Client.CLIENT_UNDISTRIBUTED).build();
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByClientState(cEntityClient);
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�clientmodule:31
	 * ���ܣ�����Ա���Ա������ͻ����
	 * ��������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getClientStatisticalNumber(){
		List findResult=iDaoFrame.queryClientNumberOfEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:32
	 * ���ܣ�����Ա��÷���ͻ���Ϣ
	 * ��������
	 * ����ֵ:HashedMap
	 */
	public HashedMap getClientDistribution() {
		HashedMap findResult=iDaoFrame.queryClientDistributionWithEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:33
	 * ���ܣ�����Ա���δ��˿ͻ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getNoCheckClient(){
		LinkedMap findResult=iDaoFrame.queryAllNocheckClientWithClientInfoAndEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:34
	 * ���ܣ�����Ա���������ɾ�ͻ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public CEntityClientArray queryDelClient(){
		CEntityClient cEntityClient=new CEntityClient.Builder().ClientState(MyConstant.Client.CLIENT_DEL).build();
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByClientState(cEntityClient);
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�clientmodule:35
	 * ���ܣ��ж��Ƿ���δ��˵Ŀͻ�
	 * ��������
	 * ����ֵ:boolean(true:��δ��˵�,false:û��)
	 */
	public boolean hasNocheckClient(){
		boolean bisHas=false;
		int number=iDaoFrame.queryClientSubmitNocheckNumber();
		if(number==0){
			bisHas=false;
		}else{
			bisHas=true;
		}
		return bisHas;
	}
	
	/**
	 * ��ţ�clientmodule:36
	 * ���ܣ��õ�����δ��ʼ��ִ���еİݷüƻ�
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitPlanAllRunning(){
		LinkedMap findResult=iDaoFrame.queryAllVisitPlanAllRunning();
		return findResult;
	}
	
	
	/**
	 * ��ţ�clientmodule:37
	 * ���ܣ��õ�δ����ͻ��Ŀͻ��ţ��ͻ������ͻ���˾
	 * ��������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getClientUndistriInfo(){
		List findResult=iDaoFrame.queryClientUnDistributionInfo();
		return findResult;
	}
	
	
		
	/**
	 * ��ţ�clientmodule:38
	 * ���ܣ��õ�����δ��ˣ��ѹ��ڵİݷüƻ�
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitPlanWaitDeal(){
		LinkedMap findResult=iDaoFrame.queryAllVisitPlanAllWaitDeal();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:39
	 * ���ܣ���ȡ����Ա��������ˣ���ʧ�ܰݷ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitPlanComplete(){
		LinkedMap findResult=iDaoFrame.queryAllVisitPlanComplete();
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:40
	 * ���ܣ���ȡ����δɾԱ���ĳ����ݷ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitUndoInfo(){
		LinkedMap findResult=iDaoFrame.queryAllVisitUndoWithInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:41
	 * ���ܣ���ȡ����δɾԱ���İݷ��ܽἰ��Ϣ
	 * ��������
	 * ����ֵ:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	public LinkedMap getAllVisitConclusionInfo(){
		LinkedMap findResult=iDaoFrame.queryAllVisitConclusionInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:42
	 * ���ܣ���ȡ����δɾԱ���İݷ�������Ϣ
	 * ��������
	 * ����ֵ:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitDelay)
	 */
	public LinkedMap getAllVisitDelayInfo(){
		LinkedMap findResult=iDaoFrame.queryAllVisitDelayInfo();
		return findResult;
	}
	
	
	/**
	 * ��ţ�clientmodule:43
	 * ���ܣ��ж��Ƿ���δ����İݷüƻ�
	 * ��������
	 * ����ֵ:boolean 
	 */
	public boolean hasWaitDealVisit(){
		boolean bisHas=false;
		int number=iDaoFrame.queryVisitPlanWaitDealNumber();
		if(number==0){
			bisHas=false;
		}else{
			bisHas=true;
		}
		return bisHas;
	}
	
	/**
	 * ��ţ�clientmodule:44
	 * ���ܣ�����Ա���Ż�ȡ��󶨵İݷüƻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray 
	 */
	public CEntityVisitPlanArray getBindVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryVisitPlanBindByEmployee(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	
	//������ѭ���ݷõĻ��������һ�εİݷÿ�ʼʱ�䣬���ڱ�����ʹ��
	@SuppressWarnings("deprecation")
	private static String getCycleStartTime(CEntityVisitPlan cEntityVisitPlan){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		String m_sOldStartTime=cEntityVisitPlan.getM_sVisitPlanStartTime();
		Date m_date=null;
		int weekdays=7;//һ���ڵ�����
		try {
			m_date=sFormat.parse(m_sOldStartTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(m_date);
		switch (cEntityVisitPlan.getM_iVisitPlanCycleType()) {
		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_DAY:
			calendar.add(Calendar.DAY_OF_MONTH, cEntityVisitPlan.getM_iVisitPlanCycleNumber());
			break;
		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_WEEK:
			calendar.add(Calendar.DAY_OF_MONTH, weekdays+cEntityVisitPlan.getM_iVisitPlanCycleNumber()-calendar.get(Calendar.DAY_OF_WEEK));
			break;
		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_MONTH:
			 calendar.set(Calendar.DAY_OF_MONTH, cEntityVisitPlan.getM_iVisitPlanCycleNumber());  
		     calendar.set(Calendar.MONTH, m_date.getMonth()+1 );  
			break;
		
		case MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_USERDEFINED:
			calendar.add(Calendar.DAY_OF_MONTH, cEntityVisitPlan.getM_iVisitPlanCycleNumber());
			break;
		default:
			break;
		}
		Date m_resultDate=calendar.getTime();
		String result=sFormat.format(m_resultDate);
		return result;
	}
	
	//������ѭ���ݷõĻ��������һ�εİݷý���ʱ�䣬���ڱ�����ʹ��
	private static String getCycleEndTime(String startTime,int planDays){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date=null;
		try {
			date=sFormat.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,planDays);
		Date resultDate=calendar.getTime();
		String result=sFormat.format(resultDate);
		return result;
		
	}
	//��õ������ڣ����ڱ�����ʹ��
	private static String getNewPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result=sFormat.format(resultDate);
		return result;
		
	}
}
