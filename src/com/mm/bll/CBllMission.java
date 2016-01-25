package com.mm.bll;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.entity.CEntityMissionDelay;
import com.mm.entity.CEntityMissionUndo;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.tool.MyConstant;

@Component("cBllMission")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CBllMission {
	private IDaoFrame iDaoFrame;

	@Resource(name = "cDaoFrameImpl")
	public void setiDaoFrame(IDaoFrame iDaoFrame) {
		this.iDaoFrame = iDaoFrame;
	}

	/**
	 * ��ţ�missionmodule:1 
	 * ���ܣ�������������
	 * ������cEntityEmployee(EmployeeId),cEntityMission(�����ֶ�) 
	 * ����ֵ:boolean
	 */
	public boolean createMission(CEntityEmployee cEntityEmployee,
			CEntityMission cEntityMission) {
		//�����������񲢷���
		boolean bisCreate = iDaoFrame.saveMission(cEntityEmployee,
				cEntityMission);
		return bisCreate;
	}

	/**
	 * ��ţ�missionmodule:2 
	 * ���ܣ�������Ա�������� 
	 * ������cEntityMission(MissionId) ����ֵ:boolean
	 */
	public boolean acceptMission(CEntityMission cEntityMission) {
		//����״̬��Ϊִ����
		boolean bisAccept = iDaoFrame.updateMissionState(cEntityMission,
				MyConstant.Mission.MISSION_UNDERWAY);
		return bisAccept;
	}

	/**
	 * ��ţ�missionmodule:3 
	 * ���ܣ�������Ա�ύ�����ܽ�
	 * ������cEntityMission(MissionId),cEntityMissionConclusion(�����ֶ�) ����ֵ:boolean
	 */

	public boolean submitMission(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion) {
		// TODO Auto-generated method stub
		//���������ܽ��¼
		boolean bisSave = iDaoFrame.saveMissionConclusion(cEntityMission,
				cEntityMissionConclusion);
		//����״̬��Ϊδ���
		boolean bisUpdate = iDaoFrame.updateMissionState(cEntityMission,
				MyConstant.Mission.MISSION_NOCHECK);
		
		boolean bisSubmit=bisSave&bisUpdate;
	
		return bisSubmit;

	}

	

	/**
	 * ��ţ�missionmodule:4
	 * ���ܣ��������
	 * ������  cEntityMission(MissionId)
	 * 		cEntityMissionConclusion(MissionConclusionId)
	 * 	    OperateState(MyConstant.MissionConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean checkMissionConclusion(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion,int OperateState ) {
		//����״̬תΪ�����
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_CHECK);
		//�����ܽ����˽��תΪͨ����ͨ��
		boolean bisUpdateMissionCheck=iDaoFrame.updateMissionCheck(cEntityMissionConclusion, OperateState);
		boolean bisCheck=bisUpdateMissionCheck&&bisUpdateMissionState;
		return bisCheck;
	}

	/**
	 * ��ţ�missionmodule:5
	 * ���ܣ���������
	 * ������  cEntityMission(MissionId)
	 * 		cEntityMissionUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoMission(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		//����״̬תΪ�ѳ���
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_UNDO);
		//����һ����������¼
		boolean bisSaveMissionUndo=iDaoFrame.saveMissionUndo(cEntityMission, cEntityMissionUndo);
	
		//���������й��󶨵Ļ���������
		CEntityMission findResult=iDaoFrame.queryMissionByMissionId(cEntityMission.getM_iMissionId());
		boolean bisUpdateBandType=false;
		
		if(findResult.getM_iMissionBussinessBandState()==MyConstant.Mission.MISSION_BUSSINESSBAND){
			CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE).BussinessObjectId(cEntityMission.getM_iMissionId()).BussinessBindType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_NOBAND).build();
			bisUpdateBandType=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		}else if(findResult.getM_iMissionBussinessBandState()==MyConstant.Mission.MISSION_BUSSINESSNOBAND){
			bisUpdateBandType=true;
		}
		
		boolean bisUndo=bisUpdateMissionState&&bisSaveMissionUndo&&bisUpdateBandType;
		
		return bisUndo;
	}
	
	/**
	 * ��ţ�missionmodule:6
	 * ���ܣ���������
	 * ������  cEntityMissionUndo(MissionUndoId)
	 * ����ֵ:boolean
	 */
	public boolean recallMission(CEntityMissionUndo cEntityMissionUndo) {
		//��������״̬���ѳ���תΪ�ѳ���
		boolean bisUpdateUndoType=iDaoFrame.updateMissionUndoType(cEntityMissionUndo,MyConstant.MissionUndo.MISSIONUNDO_RECORD);
		//���ص�����תΪԭ����ǰ��״̬
		CEntityMissionUndo findUndo=iDaoFrame.queryMissionUndoByMissionUndoId(cEntityMissionUndo);
		CEntityMission cEntityMission=findUndo.getcEntityMission();
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, findUndo.getM_iMissionUndoRecallType());
		//���������й��󶨵Ļ���������
		boolean bisBand=false;
		if(cEntityMission.getM_iMissionBussinessBandState()==MyConstant.Mission.MISSION_BUSSINESSNOBAND){
			CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE).BussinessObjectId(cEntityMission.getM_iMissionId()).BussinessBindType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_BAND).build();
			bisBand=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		}else if(cEntityMission.getM_iMissionBussinessBandState()==MyConstant.Mission.MISSION_BUSSINESSBAND){
			bisBand=true;
		}
		boolean bisRecall=bisUpdateUndoType&&bisUpdateMissionState&&bisBand;
		return bisRecall;
	}
	
	
	
	/**
	 * ��ţ�missionmodule:7
	 * ���ܣ���������
	 * ������cEntityMission(MissionId,MissionDeadline),cEntityMissionDelay(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean delayMission(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay){
		//�ѹ���״̬תΪִ����״̬
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_UNDERWAY);
		//���ں������ı�������������
		boolean bisUpdateMissionDeadline=iDaoFrame.updateMissionDeadline(cEntityMission);
		//����һ�����ڼ�¼
		boolean bisSaveMissionDelay=iDaoFrame.saveMissionDealy(cEntityMission, cEntityMissionDelay);
		
		boolean bisDelay=bisUpdateMissionState&&bisUpdateMissionDeadline&&bisSaveMissionDelay;
		return bisDelay;
		
		
	}
	
	/**
	 * ��ţ�missionmodule:8
	 * ���ܣ���������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:boolean
	 */
	public boolean abandonMission(CEntityMission cEntityMission){
		//�ѹ���״̬תΪ��ʧ��״̬
		boolean bisAbando=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_FAILURE);
		return bisAbando;
		
	}

	
	/**
	 * ��ţ�missionmodule:9
	 * ���ܣ���ȡδ��������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetWaitTakeMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByEmployeeIdAndMissionState(cEntityEmployee, MyConstant.Mission.MISSION_WAITTAKE);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�missionmodule:10
	 * ���ܣ���ȡִ��������(�����ѹ���)
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetUnderWayMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionRunningStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�missionmodule:11
	 * ���ܣ���ȡ��ֹ̬������(δ���,�����,�ѳ���,��ʧ��)
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetCompleteMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionCompleteStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�missionmodule:12
	 * ���ܣ���������ܽ�
	 * ������CEntityMission(MissionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusion(CEntityMission cEntityMission){
		CEntityMissionConclusion cEntityMissionConclusion=iDaoFrame.queryMissonConclusionByMissionId(cEntityMission);
		return cEntityMissionConclusion;
	}

	
	/**
	 * ��ţ�missionmodule:13
	 * ���ܣ�����Ա������д���������
	 * ��������
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllWaitTakeMissionArray(){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByMissionType(MyConstant.Mission.MISSION_WAITTAKE);
		return cEntityMissionArray;
	}
	
	
	/**
	 * ��ţ�missionmodule:14
	 * ���ܣ�����Ա��ø�Ա����������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllEmployeeMission(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�missionmodule:15
	 * ���ܣ�����Ա��ø�Ա�����������ܽ�
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionConclusionArray
	 */
	public LinkedMap GetAllEmployeeMissionConclusion(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllMissionConclusionByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�missionmodule:16
	 * ���ܣ�����Ա��������ܽ�����
	 * ������CEntityMissionConclusion(MissionConclusionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusionDetail(CEntityMissionConclusion cEntityMissionConclusion){
		CEntityMissionConclusion findResult=iDaoFrame.queryMissionConclusionByMissionConclusionId(cEntityMissionConclusion);
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:17
	 * ���ܣ�����Ա��ø�Ա�����е���������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetEmployeeMissionDelay(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllMissionDelayByEmployeeId(cEntityEmployee);
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:18
	 * ���ܣ�����Ա�������δ��ʼ��ִ���е�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionRunning(){
		LinkedMap findResult=iDaoFrame.queryAllMissionRuning();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:19
	 * ���ܣ�ͳ��Ա���ţ�Ա���˺ţ�Ա������������ִ��������δ���ܣ�ִ���У�
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List GetMissionRuningNumberOfEmployeeInfo(){
		List findResult=iDaoFrame.queryMissionRunningNumberOfEmployeeInfo();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:20
	 * ���ܣ�����Ա�������δ������ѹ��ڵ�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionDeal(){
		LinkedMap findResult=iDaoFrame.queryAllMissionWaitDeal();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:21
	 * ���ܣ�����Ա��������ѹ���������˵�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionComplete(){
		LinkedMap findResult=iDaoFrame.queryAllMissionComplete();
		return findResult;
	}
	
	
	/**
	 * ��ţ�missionmodule:22
	 * ���ܣ�����Ա�����������������Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionUndoWithEmployeeInfo(){
		LinkedMap findResult=iDaoFrame.queryAllMissionUndoWithEmployeeInfo();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:23
	 * ���ܣ�����Ա������������ܽἰ��Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionConclusionWithEmployeeInfo(){
		LinkedMap findResult=iDaoFrame.queryAllMissionConclusionWithEmployeeInfo();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:24
	 * ���ܣ�����Ա��������������ڼ���Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionDelayWithEmployeeInfo(){
		LinkedMap findResult=iDaoFrame.queryAllMissionDelayWithMissionInfoAndEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�missionmodule:25
	 * ���ܣ��ж��Ƿ���δ���������
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public boolean hasWaitDealMission(){
		boolean bisHas=false;
		int number=iDaoFrame.queryMissionWaitDealNumber();
		if(number==0){
			bisHas=false;
		}else{
			bisHas=true;
		}
		return bisHas;
	}
	
	/**
	 * ��ţ�missionmodule:26
	 * ���ܣ�����Ա���Ż�ȡ��󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=iDaoFrame.queryMissionBindByEmployee(cEntityEmployee);
		return findResult;
	}
	
	
	/**
	 * ��ţ�missionmodule:27
	 * ���ܣ�����Ա���Ż�ȡ��δ�󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionUnBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=iDaoFrame.queryMissionUnBindByEmployee(cEntityEmployee);
		return findResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
