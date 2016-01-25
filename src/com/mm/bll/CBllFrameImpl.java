package com.mm.bll;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityAdministrator;
import com.mm.entity.CEntityAttendance;
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.entity.CEntityBussinessBadrecord;
import com.mm.entity.CEntityBussinessUndo;
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.entity.CEntityMissionDelay;
import com.mm.entity.CEntityMissionUndo;
import com.mm.entity.CEntitySuggest;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.entityarray.CEntityBussinessArray;
import com.mm.entityarray.CEntityClientArray;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.entityarray.CEntityNoticeArray;
import com.mm.entityarray.CEntityVisitPlanArray;

@Component("cBllFrameImpl")
public class CBllFrameImpl implements IBllFrame{

	/*
	 *------------------------------ ����ģ�� missionmodule----------------------------
	 */
	
	private CBllMission cBllMission;
	
	@Resource
	public void setcBllMission(CBllMission cBllMission) {
		this.cBllMission = cBllMission;
	}


	/**
	 * ��ţ�missionmodule:1
	 * ���ܣ�������������
	 * ������cEntityEmployee(EmployeeId),cEntityMission(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean createMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission) {
		boolean bisCreate=cBllMission.createMission(cEntityEmployee, cEntityMission);
		return bisCreate;
	}
	
	/**
	 * ��ţ�missionmodule:2
	 * ���ܣ�������Ա��������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:boolean
	 */
	public boolean acceptMission(CEntityMission cEntityMission) {
		boolean bisAccept=cBllMission.acceptMission(cEntityMission);
		return bisAccept;
	}


	/**
	 * ��ţ�missionmodule:3
	 * ���ܣ�������Ա�ύ�����ܽ�
	 * ������cEntityMission(MissionId),cEntityMissionConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean submitMission(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion) {
		boolean bisSubmit=cBllMission.submitMission(cEntityMission, cEntityMissionConclusion);
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
		boolean bisCheck=cBllMission.checkMissionConclusion(cEntityMission, cEntityMissionConclusion, OperateState);
		
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
		boolean bisUndo=cBllMission.undoMission(cEntityMission, cEntityMissionUndo);
		
		return bisUndo;
	}
	
	/**
	 * ��ţ�missionmodule:6
	 * ���ܣ���������
	 * ������  cEntityMissionUndo(MissionUndoId)
	 * ����ֵ:boolean
	 */
	public boolean recallMission(CEntityMissionUndo cEntityMissionUndo) {
		boolean bisRecall=cBllMission.recallMission(cEntityMissionUndo);
		return bisRecall;
	}

	/**
	 * ��ţ�missionmodule:7
	 * ���ܣ���������
	 * ������cEntityMission(MissionId,MissionDeadline),cEntityMissionDelay(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean delayMission(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay) {
		boolean bisDelay=cBllMission.delayMission(cEntityMission, cEntityMissionDelay);
		return bisDelay;
	}

	/**
	 * ��ţ�missionmodule:8
	 * ���ܣ���������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:boolean
	 */
	public boolean abandonMission(CEntityMission cEntityMission){
		boolean bisAbandon=cBllMission.abandonMission(cEntityMission);
		return bisAbandon;
		
	}
	
	/**
	 * ��ţ�missionmodule:9
	 * ���ܣ���ȡδ��������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetWaitTakeMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetWaitTakeMissionArray(cEntityEmployee);
		return cEntityMissionArray;
	}

	/**
	 * ��ţ�missionmodule:10
	 * ���ܣ���ȡִ��������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetUnderWayMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetUnderWayMissionArray(cEntityEmployee);
		return cEntityMissionArray;
	}

	/**
	 * ��ţ�missionmodule:11
	 * ���ܣ���ȡ��ֹ̬������(δ���,�����,�ѳ���,��ʧ��)
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetCompleteMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetCompleteMissionArray(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�missionmodule:12
	 * ���ܣ���������ܽ�
	 * ������CEntityMission(MissionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusion(CEntityMission cEntityMission) {
		CEntityMissionConclusion cEntityMissionConclusion=cBllMission.GetMissionConclusion(cEntityMission);
		
		return cEntityMissionConclusion;
	}
	/**
	 * ��ţ�missionmodule:13
	 * ���ܣ�����Ա������д���������
	 * ��������
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllWaitTakeMissionArray(){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetAllWaitTakeMissionArray();
		return cEntityMissionArray;
	}
	/**
	 * ��ţ�missionmodule:14
	 * ���ܣ�����Ա��ø�Ա����������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllEmployeeMission(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetAllEmployeeMission(cEntityEmployee);
		return cEntityMissionArray;
	}
	/**
	 * ��ţ�missionmodule:15
	 * ���ܣ�����Ա��ø�Ա�����������ܽ�
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionConclusionArray
	 */
	public LinkedMap GetAllEmployeeMissionConclusion(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllMission.GetAllEmployeeMissionConclusion(cEntityEmployee);
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:16
	 * ���ܣ�����Ա��������ܽ�����
	 * ������CEntityMissionConclusion(MissionConclusionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusionDetail(CEntityMissionConclusion cEntityMissionConclusion){
		CEntityMissionConclusion findResult=cBllMission.GetMissionConclusionDetail(cEntityMissionConclusion);
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:17
	 * ���ܣ�����Ա��ø�Ա�����е���������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetEmployeeMissionDelay(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllMission.GetEmployeeMissionDelay(cEntityEmployee);
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:18
	 * ���ܣ�����Ա�������δ��ʼ��ִ���е�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionRunning(){
		LinkedMap findResult=cBllMission.GetAllMissionRunning();
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
		List findResult=cBllMission.GetMissionRuningNumberOfEmployeeInfo();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:20
	 * ���ܣ�����Ա�������δ������ѹ��ڵ�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionDeal(){
		LinkedMap findResult=cBllMission.GetAllMissionDeal();
		return findResult;
	}
	
	/**
	 * ��ţ�missionmodule:21
	 * ���ܣ�����Ա��������ѹ���������˵�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionComplete() {
		LinkedMap findResult=cBllMission.GetAllMissionComplete();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:22
	 * ���ܣ�����Ա�����������������Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionUndoWithEmployeeInfo(){
		LinkedMap findResult=cBllMission.GetAllMissionUndoWithEmployeeInfo();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:23
	 * ���ܣ�����Ա������������ܽἰ��Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionConclusionWithEmployeeInfo(){
		LinkedMap findResult=cBllMission.GetAllMissionConclusionWithEmployeeInfo();
		return findResult;
	}
	/**
	 * ��ţ�missionmodule:24
	 * ���ܣ�����Ա��������������ڼ���Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap GetAllMissionDelayWithEmployeeInfo(){
		LinkedMap findReuslt=cBllMission.GetAllMissionConclusionWithEmployeeInfo();
		return findReuslt;
	}
	
	/**
	 * ��ţ�missionmodule:25
	 * ���ܣ��ж��Ƿ���δ���������
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public boolean hasWaitDealMission(){
		boolean bishave=cBllMission.hasWaitDealMission();
		return bishave;
	}
	
	/**
	 * ��ţ�missionmodule:26
	 * ���ܣ�����Ա���Ż�ȡ��󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=cBllMission.getEmployeeMissionBindInfo(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�missionmodule:27
	 * ���ܣ�����Ա���Ż�ȡ��δ�󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionUnBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=cBllMission.getEmployeeMissionUnBindInfo(cEntityEmployee);
		return findResult;
	}
	/*
	 *------------------------------ �ͻ�ģ�� clientmodule----------------------------
	 */
	private CBllClient cBllClient;
	
	@Resource
	public void setcBllClient(CBllClient cBllClient) {
		this.cBllClient = cBllClient;
	}


	/**
	 * ��ţ�clientmodule:1 
	 * ���ܣ������ͻ�
	 * ������cEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean createClient(CEntityClient cEntityClient){
		boolean bisCreate=cBllClient.createClient(cEntityClient);
		return bisCreate;
		
	}
	
	/**
	 * ��ţ�clientmodule:2 
	 * ���ܣ�����ͻ�
	 * ������cEntityClient(ClientId),cEntityEmployee(EmployeeId),cEntityVisitPlan(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean distributionClient(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan) {
		boolean bisDistribution=cBllClient.distributionClient(cEntityClient, cEntityEmployee, cEntityVisitPlan);
		return bisDistribution;
	}
	/**
	 * ��ţ�clientmodule:3 
	 * ���ܣ��ύ�ݷ�
	 * ������cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean submitVisit(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion){
		boolean bisSubmit=cBllClient.submitVisit(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		return bisSubmit;
	}

	/**
	 * ��ţ�clientmodule:4 
	 * ���ܣ���˿ͻ��ݷ�
	 * ������ccEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean checkVisitConclusion(CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion,int OperateState) {
		boolean bisCheck=cBllClient.checkVisitConclusion(cEntityVisitPlan, cEntityVisitConclusion, OperateState);
		return bisCheck;
	}
	
	/**
	 * ��ţ�clientmodule:5
	 * ���ܣ��ύ�ͻ�
	 * ������cEntityClient(�����ֶ�),cEntityEmployee(EmployeeId),cEntityClientSubmit(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean submitCliet(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient ) {
		boolean bisSubmit=cBllClient.submitCliet(cEntityEmployee, cEntityClient);
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
		boolean bisCheck=cBllClient.checkClient(cEntityClient, OperateState);
		return bisCheck;
	}

	/**
	 * ��ţ�clientmodule:7
	 * ���ܣ�ѭ���ݷ�
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * ����ֵ:boolean
	 */
	public boolean setupVisitCycle(CEntityVisitPlan cEntityVisitPlan) {
		boolean bisSetup=cBllClient.setupVisitCycle(cEntityVisitPlan);
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
		boolean bisDelay=cBllClient.delayVisitPlan(cEntityVisitPlan, cEntityEmployee);
		return bisDelay;
	}

	/**
	 * ��ţ�clientmodule:9
	 * ���ܣ������ݷüƻ�
	 * ������cEntityVisitPlan(VisitPlanId)
	 * ����ֵ:boolean
	 */
	public boolean abandonVisitPlan(CEntityVisitPlan cEntityVisitPlan){
		boolean bisAbandon=cBllClient.abandonVisitPlan(cEntityVisitPlan);
		return bisAbandon;
	}
	/**
	 * ��ţ�clientmodule:10
	 * ���ܣ������ݷüƻ�
	 * ������cEntityVisitPlan(VisitPlanId)
	 * 	   cEntityVisitUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo) {
		boolean bisUndo=cBllClient.undoVisitPlan(cEntityVisitPlan, cEntityVisitUndo);
		return bisUndo;
	}
	
	/**
	 * ��ţ�clientmodule:11
	 * ���ܣ����ذݷüƻ�
	 * ������cEntityVisitUndo(VisitUndoId)
	 * ����ֵ:boolean
	 */
	public boolean recallVisitPlan(CEntityVisitUndo cEntityVisitUndo) {
		boolean bisRecall=cBllClient.recallVisitPlan(cEntityVisitUndo);
		return bisRecall;
	}
	/**
	 * ��ţ�clientmodule:12
	 * ���ܣ�ɾ���ͻ�
	 * ������cEntityClient(ClientId)
	 * ����ֵ:boolean
	 */
	public boolean delClient(CEntityClient cEntityClient){
		boolean bisDel=cBllClient.delClient(cEntityClient);
		return bisDel;
	}
	
	/**
	 * ��ţ�clientmodule:13
	 * ���ܣ�����δ��ʼ�ݷüƻ� 
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:boolean
	 */
	public CEntityVisitPlanArray GetNoStartVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.GetNoStartVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}

	/**
	 * ��ţ�clientmodule:14
	 * ���ܣ�����ִ���аݷüƻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetUnderwayVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.GetUnderwayVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}

	/**
	 * ��ţ�clientmodule:15
	 * ���ܣ�������ֹ̬�ݷüƻ���δ���,�����,�ѳ���,��ʧ�ܣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetCompleteVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.GetCompleteVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}

	/**
	 * ��ţ�clientmodule:16
	 * ���ܣ��鿴�ͻ�����
	 * ������cEntityClient(ClientId)
	 * ����ֵ:CEntityClient
	 */
	public CEntityClient GetClientInfo(CEntityClient cEntityClient){
		CEntityClient findResult=cBllClient.GetClientInfo(cEntityClient);
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:17
	 * ���ܣ��鿴�ͻ��ύ��¼
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientSubmitArray GetClientSubmit(CEntityEmployee cEntityEmployee){
		CEntityClientSubmitArray cEntityClientSubmitArray=cBllClient.GetClientSubmit(cEntityEmployee);
		return cEntityClientSubmitArray;
	}
	/**
	 * ��ţ�clientmodule:18
	 * ���ܣ�����ݷÿͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=cBllClient.GetEmployeeClient(cEntityEmployee);
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�clientmodule:19
	 * ���ܣ��õ����еĿͻ�
	 * ��������
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetAllClient(){
		CEntityClientArray cEntityClientArray=cBllClient.GetAllClient();
		return cEntityClientArray;
	}
	/**
	 * ��ţ�clientmodule:20
	 * ���ܣ���Ա���Ż�����пͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetAllEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=cBllClient.GetAllEmployeeClient(cEntityEmployee);
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�clientmodule:21
	 * ���ܣ��޸Ŀͻ�����
	 * ������CEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean UpdateClient(CEntityClient cEntityClient){
		boolean bisUpdate=cBllClient.UpdateClient(cEntityClient);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�clientmodule:22
	 * ���ܣ�����Ա�õ�Ա������Ŀͻ���
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:List<String>
	 */
	public List<String> GetAllEmployeeClientName(CEntityEmployee cEntityEmployee){
		List<String> result=cBllClient.GetAllEmployeeClientName(cEntityEmployee);
		return result;
	}
	/**
	 * ��ţ�clientmodule:23
	 * ���ܣ�����Ա���ͻ����������еĿͻ���Ϣ
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray GetAllClientByClientName(CEntityClient cEntityClient){
		CEntityClientArray cEntityClientArray=cBllClient.GetAllClientByClientName(cEntityClient);
		return cEntityClientArray;
	}
	/**
	 * ��ţ�clientmodule:24
	 * ���ܣ�����Աȡ������
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean cancleClientDistuibution(CEntityClient cEntityClient){
		boolean bisCancle=cBllClient.cancleClientDistuibution(cEntityClient);
		return bisCancle;
	}
	/**
	 * ��ţ�clientmodule:25
	 * ���ܣ�����Ա���Ա�������аݷüƻ����ͻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:HashedMap
	 */
	public HashedMap getAllEmployeeVisitPlan(CEntityEmployee cEntityEmployee){
		HashedMap findResult=cBllClient.getAllEmployeeVisitPlan(cEntityEmployee);
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:26
	 * ���ܣ�����Ա�鿴�ݷüƻ�����
	 * ������CEntityVisitPlan(VisitPlanId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusionDetail(CEntityVisitPlan cEntityVisitPlan){
		CEntityVisitConclusion cEntityVisitConclusion=cBllClient.getVisitConclusionDetail(cEntityVisitPlan);
		return cEntityVisitConclusion;
	}
	
	/**
	 * ��ţ�clientmodule:27
	 * ���ܣ�����Ա�鿴��Ա�����еİݷ��ܽ�
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public LinkedMap getEmployeeVisitConclusion(CEntityEmployee cEntityEmployee) {
		LinkedMap find=cBllClient.getEmployeeVisitConclusion(cEntityEmployee);
		return find;
	}
	
	/**
	 * ��ţ�clientmodule:28
	 * ���ܣ�����Ա�鿴�ݷüƻ�����
	 * ������CEntityVisitConclusion(VisitConclusionId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusion(CEntityVisitConclusion cEntityVisitConclusion){
		CEntityVisitConclusion findResult=cBllClient.getVisitConclusion(cEntityVisitConclusion);
		return findResult;
		
	}
	/**
	 * ��ţ�clientmodule:29
	 * ���ܣ�����Ա��ø�Ա�����еİݷ�����
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitDealyArray
	 */
	public LinkedMap getEmployeeVisitDealy(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllClient.getEmployeeVisitDealy(cEntityEmployee);
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:30
	 * ���ܣ�����Ա�������δ����Ŀͻ�
	 * ��������
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray getNodistributionClient(){
		CEntityClientArray findResult=cBllClient.getNodistributionClient();
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:31
	 * ���ܣ�����Ա���Ա������ͻ����
	 * ��������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getClientStatisticalNumber(){
		List findResult=cBllClient.getClientStatisticalNumber();
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:32
	 * ���ܣ�����Ա��÷���ͻ���Ϣ
	 * ��������
	 * ����ֵ:HashedMap
	 */
	public HashedMap getClientDistribution() {
		HashedMap findResult=cBllClient.getClientDistribution();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:33
	 * ���ܣ�����Ա���δ��˿ͻ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getNoCheckClient(){
		LinkedMap findResult=cBllClient.getNoCheckClient();
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:34
	 * ���ܣ�����Ա���������ɾ�ͻ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public CEntityClientArray queryDelClient(){
		CEntityClientArray findResult=cBllClient.queryDelClient();
		return findResult;
	}
	/**
	 * ��ţ�clientmodule:35
	 * ���ܣ��ж��Ƿ���δ��˵Ŀͻ�
	 * ��������
	 * ����ֵ:boolean(true:��δ��˵�,false:û��)
	 */
	public boolean hasNocheckClient() {
		boolean bishas=cBllClient.hasNocheckClient();
		return bishas;
	}
	
	/**
	 * ��ţ�clientmodule:36
	 * ���ܣ��õ�����δ��ʼ��ִ���еİݷüƻ�
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitPlanAllRunning(){
		LinkedMap findResult=cBllClient.getAllVisitPlanAllRunning();
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
		List findReuslt=cBllClient.getClientUndistriInfo();
		return findReuslt;
	}
	
		/**
	 * ��ţ�clientmodule:38
	 * ���ܣ��õ�����δ��ˣ��ѹ��ڵİݷüƻ�
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitPlanWaitDeal(){
		LinkedMap findResult=cBllClient.getAllVisitPlanWaitDeal();
		return findResult;
	}
	
	
	/**
	 * ��ţ�clientmodule:39
	 * ���ܣ���ȡ����Ա��������ˣ���ʧ�ܰݷ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitPlanComplete(){
		LinkedMap findResult=cBllClient.getAllVisitPlanComplete();
		return findResult;
	}
	
	
	/**
	 * ��ţ�clientmodule:40
	 * ���ܣ���ȡ����δɾԱ���ĳ����ݷ���Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllVisitUndoInfo(){
		LinkedMap findResult=cBllClient.getAllVisitUndoInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:41
	 * ���ܣ���ȡ����δɾԱ���İݷ��ܽἰ��Ϣ
	 * ��������
	 * ����ֵ:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	public LinkedMap getAllVisitConclusionInfo(){
		LinkedMap findResult=cBllClient.getAllVisitConclusionInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientmodule:42
	 * ���ܣ���ȡ����δɾԱ���İݷ�������Ϣ
	 * ��������
	 * ����ֵ:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitDelay)
	 */
	public LinkedMap getAllVisitDelayInfo(){
		LinkedMap findReuslt=cBllClient.getAllVisitDelayInfo();
		return findReuslt;
	}
	
	
	/**
	 * ��ţ�clientmodule:43
	 * ���ܣ��ж��Ƿ���δ����İݷüƻ�
	 * ��������
	 * ����ֵ:boolean 
	 */
	public boolean hasWaitDealVisit() {
		boolean bishas=cBllClient.hasWaitDealVisit();
		return bishas;
	}
	/**
	 * ��ţ�clientmodule:44
	 * ���ܣ�����Ա���Ż�ȡ��󶨵İݷüƻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray 
	 */
	public CEntityVisitPlanArray getBindVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.getBindVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	/**
	 * ��ţ�clientmodule:45
	 * ���ܣ�����Ա���Ż�ȡ��δ�󶨵İݷüƻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray 
	 */
	public CEntityVisitPlanArray getUnBindVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.getUnBindVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	/*
	 *------------------------------ ����ģ��bussinessmodule----------------------------
	 */

	private CBllBussiness cBllBussiness;
	
	@Resource
	public void setcBllBussiness(CBllBussiness cBllBussiness) {
		this.cBllBussiness = cBllBussiness;
	}


	/**
	 * ��ţ�bussinessmodule:1 
	 * ���ܣ���������
	 * ������cEntityEmployee(EmployeeId),cEntityBussiness(�����ֶ�) 
	 * ����ֵ:boolean
	 */
	public boolean createBussiness(CEntityBussiness cEntityBussiness, CEntityEmployee cEntityEmployee) {
		boolean bisCreate=cBllBussiness.createBussiness(cEntityBussiness, cEntityEmployee);
		
		return bisCreate;
	}
	/**
	 * ��ţ�bussinessmodule:2
	 * ���ܣ������
	 * ������
	 * ����ֵ:boolean
	 */
	public boolean bindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness) {
		boolean bisUpdate=cBllBussiness.bindBussinessActivity(cEntityBussinessActivity,cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�bussinessmodule:3
	 * ���ܣ�������
	 * ������
	 * ����ֵ:boolean
	 */
	public boolean unbindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity) {
		boolean bisupdate=cBllBussiness.unbindBussinessActivity(cEntityBussinessActivity);
		return bisupdate;
	}

	/**
	 * ��ţ�bussinessmodule:4
	 * ���ܣ�����Ǽ�
	 * ������cEntityBussiness(BussinessId,BussinessRegisterTime)
	 * ����ֵ:boolean
	 */
	public boolean registerBussiness(CEntityBussiness cEntityBussiness){
		boolean bisRegister=cBllBussiness.registerBussiness(cEntityBussiness);
		return bisRegister;
	}
	
	/**
	 * ��ţ�bussinessmodule:5
	 * ���ܣ��ִ�Ŀ�ĵصǼ�
	 * ������cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * ����ֵ:boolean
	 */
	public boolean inregisterBussiness(CEntityBussiness cEntityBussiness) {
		boolean bisIn=cBllBussiness.inregisterBussiness(cEntityBussiness);
		return bisIn;
	}
	
	/**
	 * ��ţ�bussinessmodule:6
	 * ���ܣ��뿪Ŀ�ĵصǼ�
	 * ������cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * ����ֵ:boolean
	 */
	public boolean outregisterBussiness(CEntityBussiness cEntityBussiness) {
		boolean bisOut=cBllBussiness.outregisterBussiness(cEntityBussiness);
		
		return bisOut;
	}
		
	/**
	 * ��ţ�bussinessmodule:7
	 * ���ܣ������Ǽ�
	 * ������cEntityBussiness(BussinessId,BussinessReturnTime)
	 * ����ֵ:boolean
	 */
	public boolean returnregisterBussiness(CEntityBussiness cEntityBussiness) {
		boolean bisReturn=cBllBussiness.returnregisterBussiness(cEntityBussiness);
		return bisReturn;
	}
	
	/**
	 * ��ţ�bussinessmodule:8
	 * ���ܣ���˳���ͨ��
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean checkBussinessPass(CEntityBussiness cEntityBussiness) {
		boolean bisCheck=cBllBussiness.checkBussinessPass(cEntityBussiness);
		return bisCheck;
	}
	
	/**
	 * ��ţ�bussinessmodule:9
	 * ���ܣ���˳��ͨ��
	 * ������cEntityBussiness(BussinessId)
	 * 	   cEntityBussinessBadrecord(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean checkBussinessNopass(CEntityBussiness cEntityBussiness ) {
		boolean bisCheck=cBllBussiness.checkBussinessNopass(cEntityBussiness);
		return bisCheck;
	}
	
	/**
	 * ��ţ�bussinessmodule:10
	 * ���ܣ�������Ա�������(��ǰ����һ��δ�Ǽǻ�ִ���г���)
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessRunningState(CEntityEmployee cEntityEmployee) {
		CEntityBussiness cEntityBussiness=cBllBussiness.GetBussinessRunningState(cEntityEmployee);
		return cEntityBussiness;
	}

	/**
	 * ��ţ�bussinessmodule:11
	 * ���ܣ�������Ա��������¼
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityBussinessArray
	 */
	public CEntityBussinessArray GetBussinessRecall(CEntityEmployee cEntityEmployee){
		CEntityBussinessArray cEntityBussinessArray=cBllBussiness.GetBussinessRecall(cEntityEmployee);
		return cEntityBussinessArray;
	}
	/**
	 * ��ţ�bussinessmodule:12
	 * ���ܣ��鿴�����¼����
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessDetail(CEntityBussiness cEntityBussiness){
		CEntityBussiness findResult=cBllBussiness.GetBussinessDetail(cEntityBussiness);
		return findResult;
	}
	/**
	 * ��ţ�bussinessmodule:13
	 * ���ܣ���ȡ����
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:HashMap<String, Object>
	 */
	public HashMap<String, Object> GetGetBussinessActivity(CEntityBussiness cEntityBussiness){
		HashMap<String, Object> result=cBllBussiness.GetGetBussinessActivity(cEntityBussiness);
		return result;
	}
	/**
	 * ��ţ�bussinessmodule:14
	 * ���ܣ��жϳ����Ƿ��а󶨵ĳ���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean checkBussinesHavaBussinessActivity(CEntityBussiness cEntityBussiness){
		boolean bishave=cBllBussiness.checkBussinesHavaBussinessActivity(cEntityBussiness);
		return bishave;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:15
	 * ���ܣ������������Ƴ���
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoBussiness(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo) {
		boolean bisUndo=cBllBussiness.undoBussiness(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo;
	}
	
	/**
	 * ��ţ�bussinessmodule:15
	 * ���ܣ������������������
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoBussinessWithUndoBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		boolean bisUndo=cBllBussiness.undoBussinessWithUndoBussinessActivity(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo;
	}
	
	/**
	 * ��ţ�bussinessmodule:16
	 * ���ܣ��������������
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoBussinessWithUnbindBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		boolean bisUndo=cBllBussiness.undoBussinessWithUnbindBussinessActivity(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo;
	}
	

	/**
	 * ��ţ�bussinessmodule:17
	 * ���ܣ���˳���ͨ������Ҫ���һ���������¼
	 * ������CEntityBussiness(BussinessId),CEntityBussinessBadrecord(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean checkBussinessPassWithBadrecall(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord){
		boolean bischeck=cBllBussiness.checkBussinessPassWithBadrecall(cEntityBussiness, cEntityBussinessBadrecord);
		return bischeck;
	}
	/**
	 * ��ţ�bussinessmodule:18
	 * ���ܣ��õ���Ա�������г������¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getEmployeeBussinessBadrecall(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllBussiness.getEmployeeBussinessBadrecall(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:19
	 * ���ܣ���ȡ����δɾԱ���ĳ�����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessRunning(){
		LinkedMap findReuslt=cBllBussiness.getAllBussinessRunning();
		return findReuslt;
	}
	
	/**
	 * ��ţ�bussinessmodule:20
	 * ���ܣ���ȡ���������Ա����Ϣ����Ա��û��δ�Ǽǣ�ִ���У�δ��˵ĳ��
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getBussinessAddOkEmployee(){
		List findReuslt=cBllBussiness.getBussinessAddOkEmployee();
		return findReuslt;
	}
	
	/**
	 * ��ţ�bussinessmodule:21
	 * ���ܣ���ȡ����δɾԱ���Ĵ����������Ϣ(δ���)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessWaitDeal(){
		LinkedMap findResult=cBllBussiness.getAllBussinessWaitDeal();
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:22
	 * ���ܣ���ȡ����δɾԱ�����ѽ���������Ϣ�����ͨ������˲�ͨ����
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessComplete(){
		LinkedMap findResult=cBllBussiness.getAllBussinessComplete();
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:23
	 * ���ܣ���ȡ����δɾԱ���������¼
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessUndoInfo(){
		LinkedMap findResult=cBllBussiness.getAllBussinessUndoInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:24
	 * ���ܣ���ȡ����δɾԱ���ĳ������¼��Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessBadrecordInfo(){
		LinkedMap findResult=cBllBussiness.getAllBussinessBadrecordInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:25
	 * ���ܣ��ж��Ƿ���δ����ĳ����¼
	 * ������
	 * ����ֵ:boolean
	 */
	public boolean hasWaitDealBussiness(){
		boolean bishas=cBllBussiness.hasWaitDealBussiness();
		return bishas;
	}
	/*
	 *------------------------------ ����ģ��othermodule----------------------------
	 */
	private CBllOther cBllOther;
	
	
	@Resource
	public void setcBllOther(CBllOther cBllOther) {
		this.cBllOther = cBllOther;
	}


	/**
	 * ��ţ�othermodule:1 
	 * ���ܣ�������Աǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityMission(AttendanceRegisterTime) 
	 * ����ֵ:boolean
	 */
	public boolean registerAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance) {
		boolean bisRegister=cBllOther.registerAttendance(cEntityEmployee, cEntityAttendance);
		return bisRegister;
	}
	
	/**
	 * ��ţ�othermodule:2
	 * ���ܣ�������Աǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityMission(AttendanceSignoutTime) 
	 * ����ֵ:boolean
	 */
	public boolean signoutAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance) {
		boolean bisSignout=cBllOther.signoutAttendance(cEntityEmployee, cEntityAttendance);
		return bisSignout;
	}

	/**
	 * ��ţ�othermodule:3
	 * ���ܣ�������Ա��¼
	 * ������cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * ����ֵ:CEntityEmployee
	 */
	public CEntityEmployee loginEmployee(CEntityEmployee cEntityEmployee) {
		CEntityEmployee findResult=cBllOther.loginEmployee(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�othermodule:4
	 * ���ܣ�������Ա���뿼��
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityAttendance
	 */
	public CEntityAttendance enterAttendanceForEmployee(CEntityEmployee cEntityEmployee){
		CEntityAttendance findResult=cBllOther.enterAttendanceForEmployee(cEntityEmployee);
		return findResult;
	}
	/**
	 * ��ţ�othermodule:5
	 * ���ܣ�������Ա����ͨѶ¼
	 * ��������
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray enterCommunition(){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.enterCommunition();
		return cEntityEmployeeArray;
	}
	/**
	 * ��ţ�othermodule:6
	 * ���ܣ�����֪ͨ����
	 * ��������
	 * ����ֵ:CEntityNoticeArray
	 */
	public CEntityNoticeArray enterNotice(){
		CEntityNoticeArray cEntityNoticeArray=cBllOther.enterNotice();
		return cEntityNoticeArray;
	}
	/**
	 * ��ţ�othermodule:7
	 * ���ܣ��ύͶ�߽���
	 * ������CEntityEmployee(EmployeeId),cEntitySuggest(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean submitSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest){
		boolean bisSubmit=cBllOther.submitSuggest(cEntityEmployee, cEntitySuggest);
		return bisSubmit;
	}
	/**
	 * ��ţ�othermodule:8
	 * ���ܣ�����Ա��¼
	 * ������CEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * ����ֵ:boolean
	 */
	public boolean aministorLogin(CEntityAdministrator cEntityAdministrator){
		boolean bisLogin=cBllOther.aministorLogin(cEntityAdministrator);
		return bisLogin;
	}
	
	/**
	 * ��ţ�othermodule:9
	 * ���ܣ�����Աɾ��Ա��
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:boolean
	 */
	public boolean deleteEmployee(CEntityEmployee cEntityEmployee){
		boolean bisDelete=cBllOther.deleteEmployee(cEntityEmployee);
		return bisDelete;
	}

	/**
	 * ��ţ�othermodule:10
	 * ���ܣ�����Ա�޸�Ա��
	 * ������CEntityEmployee(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean updateEmployee(CEntityEmployee cEntityEmployee){
		boolean bisUpdate=cBllOther.updateEmployee(cEntityEmployee);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�othermodule:11
	 * ���ܣ�����Ա����Ա��
	 * ������CEntityEmployee(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean addEmployee(CEntityEmployee cEntityEmployee){
		boolean bisAdd=cBllOther.addEmployee(cEntityEmployee);
		return bisAdd;
	}
	
	/**
	 * ��ţ�othermodule:12
	 * ���ܣ���õ������еĿ��ڼ�¼
	 * ������CEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByAttendaceData(CEntityAttendance cEntityAttendance){
		HashedMap findResult=cBllOther.queryAllAttendanceWithEmployeeDateByAttendaceData(cEntityAttendance);
		return findResult;
	}
	
	/**
	 * ��ţ�othermodule:13
	 * ���ܣ�����Ա�������ɾ����Ա��
	 * ��������
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityEmployeeArray getAllDelEmployee(){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.getAllDelEmployee();
		return cEntityEmployeeArray;
	}
	/**
	 * ��ţ�othermodule:14
	 * ���ܣ�����Ա�������δɾԱ��������
	 * ��������
	 * ����ֵ:List<String>
	 */
	public List<String> getAllEmployeeName(){
		List<String> result=cBllOther.getAllEmployeeName();
		return result;
	}
	
	/**
	 * ��ţ�othermodule:15
	 * ���ܣ�����Ա��Ա������������
	 * ������CEntityEmployee(EmployeeName)
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray getEmployeeSearchResult(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.getEmployeeSearchResult(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	/**
	 * ��ţ�othermodule:16
	 * ���ܣ�����Ա��Ա�����Ž�������
	 * ������CEntityEmployee(EmployeeDepartment)
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray getDepartmentEmployee(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.getDepartmentEmployee(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	
	/**
	 * ��ţ�othermodule:17
	 * ���ܣ�����Ա��ȡԱ�����еĿ��ڼ�¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityAttendanceArray getAllEmployeeAttendance(CEntityEmployee cEntityEmployee) {
		CEntityAttendanceArray cEntityAttendanceArray=cBllOther.getAllEmployeeAttendance(cEntityEmployee);
		return cEntityAttendanceArray;
	}
	
	/**
	 * ��ţ�othermodule:18
	 * ���ܣ�����Ա�����ڲ�ѯԱ�����տ��ڼ�¼
	 * ������CEntityEmployee(EmployeeId)��CEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray getEmployeeDateAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		CEntityAttendanceArray cEntityAttendanceArray=cBllOther.getEmployeeDateAttendance(cEntityEmployee, cEntityAttendance);
		return cEntityAttendanceArray;
	}
	/**
	 * ��ţ�othermodule:19
	 * ���ܣ�����Ա�����ں�Ա������ѯԱ�����տ��ڼ�¼
	 * ������CEntityEmployee(EmployeeId)��CEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public HashedMap getSearchEmployeeDate(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		HashedMap findResult=cBllOther.getSearchEmployeeDate(cEntityEmployee, cEntityAttendance);
		return findResult;
	}
	
	/**
	 * ��ţ�othermodule:20
	 * ���ܣ�����Ա����ͳ������Ա���Ŀ��ڼ�¼
	 * ������year(yyyy)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalYear(String year){
		List findReuslt=cBllOther.getAttendanceStatisticalYear(year);
		return findReuslt;
	}
	/**
	 * ��ţ�othermodule:21
	 * ���ܣ�����Ա����,��ͳ������Ա���Ŀ��ڼ�¼
	 * ������year(yyyy),month(mm)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalByYearAndMonth(String year,String month){
		List findReuslt=cBllOther.getAttendanceStatisticalByYearAndMonth(year,month);
		return findReuslt;
	}
	/**
	 * ��ţ�othermodule:22
	 * ���ܣ�����Ա����,��,Ա����ͳ��Ա���Ŀ��ڼ�¼
	 * ������year(yyyy),month(mm)��CEntityEmployee(EmployeeName)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalByYearMonthAndEmployeeName(String year,String month,CEntityEmployee cEntityEmployee){
		List findResult=cBllOther.getAttendanceStatisticalByYearMonthAndEmployeeName(year, month, cEntityEmployee);
		return findResult;
	}
}
