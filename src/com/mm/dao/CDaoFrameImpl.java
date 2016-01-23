package com.mm.dao;


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
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.entity.CEntityMissionDelay;
import com.mm.entity.CEntityMissionUndo;
import com.mm.entity.CEntityNotice;
import com.mm.entity.CEntitySuggest;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitDelay;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.entityarray.CEntityBussinessActivityArray;
import com.mm.entityarray.CEntityBussinessArray;
import com.mm.entityarray.CEntityBussinessBadrecordArray;
import com.mm.entityarray.CEntityClientArray;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.entityarray.CEntityMissionDelayArray;
import com.mm.entityarray.CEntityMissionUndoArray;
import com.mm.entityarray.CEntityNoticeArray;
import com.mm.entityarray.CEntitySuggestArray;
import com.mm.entityarray.CEntityVisitConclusionArray;
import com.mm.entityarray.CEntityVisitDealyArray;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.entityarray.CEntityVisitUndoArray;

@Component("cDaoFrameImpl")
public class CDaoFrameImpl implements IDaoFrame {
	/*
	 *------------------------------ ����Ա administrator----------------------------
	 */
	private CDaoAdministrator cDaoAdministrator;

	@Resource
	public void setcDaoAdministrator(CDaoAdministrator cDaoAdministrator) {
		this.cDaoAdministrator = cDaoAdministrator;
	}

	/**
	 * ��ţ�administrator:1
	 * ���ܣ�����һ������Ա�ʺ�
	 * ������cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * ����ֵ:boolean
	 */
	public boolean saveAdministrator(CEntityAdministrator cEntityAdministrator) {
		// TODO Auto-generated method stub
		boolean bisSave=cDaoAdministrator.saveAdministrator(cEntityAdministrator);
		return bisSave;
	}

	/**
	 * ��ţ�administrator:2
	 * ���ܣ����ʺ������ѯ����Ա
	 * ������cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * ����ֵ:CEntityAdministrator(*)
	 */
	public CEntityAdministrator queryAdministrator(CEntityAdministrator cEntityAdministrator) {
		// TODO Auto-generated method stub
		CEntityAdministrator result=cDaoAdministrator.queryAdministrator(cEntityAdministrator);
		return result;
	}

	
	/*
	 * -------------------------------Ա��employee----------------------------------
	 */	
	
	private CDaoEmployee cDaoEmployee;
	
	@Resource
	public void setcDaoEmployee(CDaoEmployee cDaoEmployee) {
		this.cDaoEmployee = cDaoEmployee;
	}

	/**
	 * ��ţ�employee:1
	 * ���ܣ�����һ��Ա��
	 * ������cEntityEmployee(EmployeeAccount,EmployeePassword,EmployeeName,EmployeePhone
	 * 					   EmployeeSex,EmployeeDepartment,EmployeeJob,EmployeeType)
	 * ����ֵ:boolean
	 */
	public boolean saveEmployee(CEntityEmployee cEntityEmployee) {
		// TODO Auto-generated method stub
		boolean bisSave=cDaoEmployee.saveEmployee(cEntityEmployee);
		return bisSave;
	}
	
	
	/**
	 * ��ţ�employee:2
	 * ���ܣ���ȡ����δɾԱ��
	 * ��������
	 * ����ֵ:CEntityEmployeeArray�������ֶΣ����漰������
	 */
	public CEntityEmployeeArray queryAllEmployee() {
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployee();
		return cEntityEmployeeArray;
	}

	/**
	 * ��ţ�employee:3
	 * ���ܣ��޸�Ա�����
	 * ������cEntityEmployee(EmployeeId,EmployeeType)
	 * ����ֵ:boolean
	 */
	public boolean updateEmployeeType(CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=cDaoEmployee.updateEmployeeType(cEntityEmployee);
		return bisUpdate;
	}
	/**
	 * ��ţ�employee:4
	 * ���ܣ����ʺ��������Ա��
	 * ������cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * ����ֵ:CEntityEmployee(�����ֶ�)
	 */
	public CEntityEmployee queryEmployeeByAccountAndPassword(CEntityEmployee cEntityEmployee) {
		CEntityEmployee result=cDaoEmployee.queryEmployeeByAccountAndPassword(cEntityEmployee);
		return result;
	}
	
	/**
	 * ��ţ�employee:5
	 * ���ܣ����ʺŲ���Ա��
	 * ������cEntityEmployee(EmployeeAccount)
	 * ����ֵ:CEntityEmployee�������ֶΣ�
	 */
	public CEntityEmployee queryEmployeeByAccount(CEntityEmployee cEntityEmployee) {
		CEntityEmployee result=cDaoEmployee.queryEmployeeByAccount(cEntityEmployee);
		
		return result;
	}
	
	/**
	 * ��ţ�employee:6
	 * ���ܣ�����������е�Ա��
	 * ������cEntityEmployee(EmployeeType)
	 * ����ֵ:CEntityEmployee�������ֶΣ�
	 */
	public CEntityEmployeeArray queryAllEmployeeByType(CEntityEmployee cEntityEmployee) {
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployeeByType(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	/**
	 * ��ţ�employee:7 
	 * ���ܣ���Ա�����޸�Ա��
	 * ������cEntityEmployee(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean updateEmployeeByEmployeeId(CEntityEmployee cEntityEmployee){
		boolean bisUpdate=cDaoEmployee.updateEmployeeByEmployeeId(cEntityEmployee);
		return bisUpdate;
	}
	/**
	 * ��ţ�employee:8 
	 * ���ܣ���ȡ����δɾԱ����
	 * ������
	 * ����ֵ:List<String>
	 */
	public List<String> queryAllEmployeeName(){
		List<String> allname=cDaoEmployee.queryAllEmployeeName();
		return allname;
	}
	
	/**
	 * ��ţ�employee:9 
	 * ���ܣ���Ա�����������δɾԱ��
	 * ������CEntityEmployee(EmployeeName)
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray queryAllEmployeeByEmployeeName(CEntityEmployee cEntityEmployee) {
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployeeByEmployeeName(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	/**
	 * ��ţ�employee:10 
	 * ���ܣ���Ա�����Ż������δɾԱ��
	 * ������CEntityEmployee(EmployeeDepartment)
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray queryAllEmployeeByEmployeeDepartment(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployeeByEmployeeDepartment(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	/*
	 * -------------------------------����mission----------------------------------
	 */
	private CDaoMission cDaoMission;
	
	@Resource
	public void setcDaoMission(CDaoMission cDaoMission) {
		this.cDaoMission = cDaoMission;
	}

	/**
	 * ��ţ�mission:1
	 * ���ܣ���������
	 * ������cEntityEmployee(EmployeeId),cEntityMission(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission) {
		boolean bisSave=cDaoMission.saveMission(cEntityEmployee, cEntityMission);
		
		return bisSave;
	}
	
	/**
	 * ��ţ�mission:2
	 * ���ܣ��޸�����״̬
	 * ������cEntityMission(MissionId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionState(CEntityMission cEntityMission,int OperateState) {
		boolean bisUpdate=cDaoMission.updateMissionState(cEntityMission,OperateState);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�mission:3
	 * ���ܣ��޸�����״̬
	 * ������EntityMission(MissionId,MissionDelayState)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionDelayState(CEntityMission cEntityMission) {
		boolean bisUpdate=cDaoMission.updateMissionDelayState(cEntityMission);
		
		return bisUpdate;
	}
	
	/**
	 * ��ţ�mission:4
	 * ���ܣ�����Ա���Ż����������
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	/**
	 * ��ţ�mission:5
	 * ���ܣ�������״̬�����������
	 * ������OperateState(MyConstant.Mission.*)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByMissionType(int OperateState) {
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionByMissionType(OperateState);
		return cEntityMissionArray;
	}
	/**
	 * ��ţ�mission:6
	 * ���ܣ�������Ż����������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:CEntityMission
	 */
	public CEntityMission queryMissionByMissionId(int MissionId){
		CEntityMission findResult=cDaoMission.queryMissionByMissionId(MissionId);
		return findResult;
		
	}
	
	/**
	 * ��ţ�mission:7
	 * ���ܣ��޸��������ޣ��޸����޾��ж�Ϊ�����ڣ�
	 * ������cEntityMission(MissionId,MissionDeadline)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionDeadline(CEntityMission cEntityMission) {
		boolean bisUpdate=cDaoMission.updateMissionDeadline(cEntityMission);
		return bisUpdate;
	}

	
	/**
	 * ��ţ�mission:8
	 * ���ܣ���Ա���ż�״̬�����������
	 * ������cEntityEmployee(EmployeeId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByEmployeeIdAndMissionState(CEntityEmployee cEntityEmployee,int OperateState){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionByEmployeeIdAndMissionState(cEntityEmployee, OperateState);
		return cEntityMissionArray;
	}
	/**
	 * ��ţ�mission:9
	 * ���ܣ���Ա���Ż�ȡ��ֹ̬������δ���,�����,�ѳ���,��ʧ�ܣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionCompleteStateByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionCompleteStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�mission:10
	 * ���ܰ�Ա���Ż�ȡִ��̬������ִ���У��ѹ��ڣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionRunningStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�mission:11
	 * ����ͨ����ǰ�����޸�����״̬(δ����,ִ����)Ϊ�ѹ���	
	 * ������CEntityMission(MissionDeadline)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionStateForOutTimeState(CEntityMission cEntityMission) {
		boolean bisUpdate=cDaoMission.updateMissionStateForOutTimeState(cEntityMission);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�mission:12
	 * ����:������Ž���������״̬���	
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoMission.updateMissionBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	/**
	 * ��ţ�mission:13
	 * ����:������Ž���������������������¼
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionStateUndoWithSaveMissionUndoByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoMission.updateMissionStateUndoWithSaveMissionUndoByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�mission:14
	 * ����:������ŵõ���󶨵�����
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMisssionByBussinessId(CEntityBussiness cEntityBussiness){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMisssionByBussinessId(cEntityBussiness);
		return cEntityMissionArray;
	}
	
	/**
	 * ��ţ�mission:15
	 * ����:��ȡ����Ա����δ���ܣ�ִ����������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionRuning(){
		LinkedMap findResult=cDaoMission.queryAllMissionRuning();
		return findResult;
	}
	/**
	 * ��ţ�mission:16
	 * ����:ͳ��Ա���ţ�Ա���˺ţ�Ա������������ִ��������δ���ܣ�ִ���У�
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryMissionRunningNumberOfEmployeeInfo(){
		List findReuslt=cDaoMission.queryMissionRunningNumberOfEmployeeInfo();
		return findReuslt;
	}
	
	/**
	 * ��ţ�mission:17
	 * ����:��ȡ����Ա����δ��ˣ��ѹ���������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionWaitDeal(){
		LinkedMap findResult=cDaoMission.queryAllMissionWaitDeal();
		return findResult;
	}
	
	/**
	 * ��ţ�mission:18
	 * ����:��ȡ����Ա������ʧ�ܣ������������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionComplete() {
		LinkedMap findResult=cDaoMission.queryAllMissionComplete();
		return findResult;
	}
	
	/**
	 * ��ţ�mission:19
	 * ����:��ȡ����Ա����δ��ˣ��ѹ�����������
	 * ������
	 * ����ֵ:int
	 */
	public int queryMissionWaitDealNumber() {
		int number=cDaoMission.queryMissionWaitDealNumber();
		return number;
	}
	
	/**
	 * ��ţ�mission:20
	 * ����:����Ա���Ż�ȡ��󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryMissionBindByEmployee(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findReuslt=cDaoMission.queryMissionBindByEmployee(cEntityEmployee);
		return findReuslt;
	}
	
	/**
	 * ��ţ�mission:21
	 * ����:����������޸ĳ����״̬
	 * ������CEntityMission(MissionId,MissionBussinessBandState)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionBindType(CEntityMission cEntityMission){
		boolean bisupdate=cDaoMission.updateMissionBindType(cEntityMission);
		return bisupdate;
	}
	/*
	 * -------------------------------�������� missiondelay----------------------------------
	 */	
	
	private CDaoMissionDelay cDaoMissionDelay;
	
	
	@Resource
	public void setcDaoMissionDelay(CDaoMissionDelay cDaoMissionDelay) {
		this.cDaoMissionDelay = cDaoMissionDelay;
	}

	/**
	 * ��ţ�missiondealy:1
	 * ���ܣ�����һ����������
	 * ������cEntityMission(MissionId),cEntityMissionDelay(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMissionDealy(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay) {
		boolean bisSave=cDaoMissionDelay.saveMissionDealy(cEntityMission, cEntityMissionDelay);
		return bisSave;
	}
	/**
	 * ��ţ�missiondealy:2
	 * ���ܣ�������Ż��������������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:CEntityMissionDelayArray
	 */
	
	public CEntityMissionDelayArray queryAllMissionDelayByMissionId(CEntityMission cEntityMission) {
		CEntityMissionDelayArray cEntityMissionDelayArray=cDaoMissionDelay.queryAllMissionDelayByMissionId(cEntityMission);
		
		return cEntityMissionDelayArray;
	}
	/**
	 * ��ţ�missiondealy:3
	 * ���ܣ���Ա���Ż�����е���������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionDelayByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoMissionDelay.queryAllMissionDelayByEmployeeId(cEntityEmployee);
		
		return findResult;
	}
	
	/**
	 * ��ţ�missiondealy:4
	 * ���ܣ�������е����ڼ�¼����������Ϣ��Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionDelayWithMissionInfoAndEmployeeInfo() {
		LinkedMap findResult=cDaoMissionDelay.queryAllMissionDelayWithMissionInfoAndEmployeeInfo();
		return findResult;
	}
	
	/*
	 * -------------------------------�����ܽ� missionconclusion----------------------------------
	 */	
	
	private CDaoMissionConclusion cDaoMissionConclusion;
	
	@Resource
	public void setcDaoMissionConclusion(CDaoMissionConclusion cDaoMissionConclusion) {
		this.cDaoMissionConclusion = cDaoMissionConclusion;
	}

	/**
	 * ��ţ�missionconclusion:1
	 * ���ܣ�����һ�������ܽ�
	 * ������cEntityMission(MissionId),cEntityMissionConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMissionConclusion(CEntityMission cEntityMission, CEntityMissionConclusion cEntityMissionConclusion) {
		boolean bisSave=cDaoMissionConclusion.saveMissionConclusion(cEntityMission, cEntityMissionConclusion);
		return bisSave;
	}
	/**
	 * ��ţ�missionconclusion:2
	 * ���ܣ�������Ż�������ܽ�
	 * ������cEntityMission(MissionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissonConclusionByMissionId(CEntityMission cEntityMission) {
		CEntityMissionConclusion cEntityMissionConclusion=cDaoMissionConclusion.queryMissonConclusionByMissionId(cEntityMission);
		return cEntityMissionConclusion;
	}
	
	/**
	 * ��ţ�missionconclusion:3
	 * ���ܣ��޸������ܽ���˽��
	 * ������cEntityMissionConclusion(MissionConclusionId)
	 * 	   OperateState(MyConstant.MissionConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionCheck(CEntityMissionConclusion cEntityMissionConclusion,int OperateState) {
		boolean bisUpdate=cDaoMissionConclusion.updateMissionCheck(cEntityMissionConclusion, OperateState);
		
		return bisUpdate;
	}
	
	
	/**
	 * ��ţ�missionconclusion:4
	 * ���ܣ���Ա���Ż�ȡ�����������ܽ�
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionConclusionArray
	 */
	public LinkedMap queryAllMissionConclusionByEmployeeId(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cDaoMissionConclusion.queryAllMissionConclusionByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�missionconclusion:5
	 * ���ܣ��������ܽ�Ż�ȡ�������ܽ�
	 * ������CEntityMissionConclusion(MissionConclusionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissionConclusionByMissionConclusionId(CEntityMissionConclusion cEntityMissionConclusion){
		CEntityMissionConclusion findResult=cDaoMissionConclusion.queryMissionConclusionByMissionConclusionId(cEntityMissionConclusion);
		return findResult;
	}
	
	/**
	 * ��ţ�missionconclusion:6
	 * ���ܣ���ȡ���������ܽἰ������Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionConclusionWithEmployeeInfo(){
		LinkedMap findResult=cDaoMissionConclusion.queryAllMissionConclusionWithEmployeeInfo();
		return findResult;
	}
	/*
	 * -------------------------------������missionundo----------------------------------
	 */	
	private CDaoMissionUndo cDaoMissionUndo;
	
	
	@Resource
	public void setcDaoMissionUndo(CDaoMissionUndo cDaoMissionUndo) {
		this.cDaoMissionUndo = cDaoMissionUndo;
	}

	/**
	 * ��ţ�missionundo:1
	 * ���ܣ�����һ��������
	 * ������cEntityMission(MissionId),cEntityMissionUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMissionUndo(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		boolean bisSave=cDaoMissionUndo.saveMissionUndo(cEntityMission, cEntityMissionUndo);
		
		return bisSave;
	}
	
	/**
	 * ��ţ�missionundo:2
	 * ���ܣ�������е�������
	 * ��������
	 * ����ֵ:CEntityMissionUndoArray
	 */
	public CEntityMissionUndoArray queryAllMissionUndo() {
		CEntityMissionUndoArray cEntityMissionUndoArray=cDaoMissionUndo.queryAllMissionUndo();
		return cEntityMissionUndoArray;
	}
	
	/**
	 * ��ţ�missionundo:3
	 * ���ܣ��޸ĳ�������
	 * ������cEntityMissionUndo(MissionUndoId)
	 * 	   OperateState(Myconstant.MissionUndo.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionUndoType(CEntityMissionUndo cEntityMissionUndo,int OperateState) {
		boolean bisUpdate=cDaoMissionUndo.updateMissionUndoType(cEntityMissionUndo,OperateState);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�missionundo:4
	 * ���ܣ��������Ż������
	 * ������cEntityMissionUndo(MissionUndoId)
	 * ����ֵ:CEntityMission
	 */
	public CEntityMission queryMissionByMissionUndoId(CEntityMissionUndo cEntityMissionUndo) {
		CEntityMission cEntityMission=cDaoMissionUndo.queryMissionByMissionUndoId(cEntityMissionUndo);
		
		return cEntityMission;
	}
	/**
	 * ��ţ�missionundo:5
	 * ���ܣ��������Ż�ó���
	 * ������cEntityMissionUndo(MissionUndoId)
	 * ����ֵ:CEntityMissionUndo
	 */
	public CEntityMissionUndo queryMissionUndoByMissionUndoId(CEntityMissionUndo cEntityMissionUndo){
		CEntityMissionUndo findResult=cDaoMissionUndo.queryMissionUndoByMissionUndoId(cEntityMissionUndo);
		return findResult;
		
	}
	
	/**
	 * ��ţ�missionundo:6
	 * ���ܣ�������е�����������Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionUndoWithEmployeeInfo() {
		LinkedMap findResult=cDaoMissionUndo.queryAllMissionUndoWithEmployeeInfo();
		return findResult;
	}
	/*
	 * -------------------------------�ͻ�client----------------------------------
	 */	
	private CDaoClient cDaoClient;
	
	@Resource
	public void setcDaoClient(CDaoClient cDaoClient) {
		this.cDaoClient = cDaoClient;
	}

	/**
	 * ��ţ�client:1
	 * ���ܣ����ӿͻ�
	 * ������cEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public  boolean saveClient(CEntityClient cEntityClient) {
		boolean bisSave=cDaoClient.saveClient(cEntityClient);
		return bisSave;
	}
	
	/**
	 * ��ţ�client:2
	 * ���ܣ���Ա���Ż�����еĿͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClientByEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	/**
	 * ��ţ�client:3
	 * ���ܣ��޸Ŀͻ�Ա����(���޸Ŀͻ�״̬Ϊ�ѷ���)
	 * ������cEntityEmployee(EmployeeId),cEntityClient(ClientId)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean updateClientEmployeeIdAndClientState(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=cDaoClient.updateClientEmployeeId(cEntityClient, cEntityEmployee);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�client:4
	 * ���ܣ����ͻ�״̬������еĿͻ�
	 * ������cEntityClient(ClientState)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByClientState(CEntityClient cEntityClient) {
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClientByClientState(cEntityClient);
		return cEntityClientArray;
	}

	/**
	 * ��ţ�client:5
	 * ���ܣ����ͻ����޸Ŀͻ�״̬
	 * ������cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.Client.*)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean updateClientStateByClientId(CEntityClient cEntityClient,int OperateState) {
		boolean bisUpdate=cDaoClient.updateClientStateByClientId(cEntityClient, OperateState);
		return bisUpdate;
	}
	

	/**
	 * ��ţ�client:6
	 * ���ܣ����ͻ��Ż�ÿͻ�����
	 * ������cEntityClient(ClientId)
	 * ����ֵ:CEntityClient
	 */
	public CEntityClient queryClientByClientId(CEntityClient cEntityClient){
		CEntityClient findResult=cDaoClient.queryClientByClientId(cEntityClient);
		return findResult;
	}
	
	/**
	 * ��ţ�client:7
	 * ���ܣ���Ա���Ż�ȡ���ڿͻ��ύ�е����пͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryClientForSubmitEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=cDaoClient.queryClientForSubmitEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	
	
	/**
	 * ��ţ�client:8
	 * ���ܣ���ѯ�������͵Ŀͻ�
	 * ��������
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClient(){
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClient();
		return cEntityClientArray;
	}
	
	/**
	 * ��ţ�client:9
	 * ���ܣ��޸Ŀͻ�����
	 * ������CEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean updateClient(CEntityClient cEntityClient){
		boolean bisUpdate=cDaoClient.updateClient(cEntityClient);
		return bisUpdate;
	}
	/**
	 * ��ţ�client:10
	 * ���ܣ���Ա���Ż�ȡ���ѷ���ͻ���
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:List<String>
	 */
	public List<String> queryAllClientNameByEmployeeId(CEntityEmployee cEntityEmployee){
		List<String> reslt=cDaoClient.queryAllClientNameByEmployeeId(cEntityEmployee);
		return reslt;
	}
	/**
	 * ��ţ�client:11
	 * ���ܣ����ͻ�����ȡ�ͻ�����
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByClientName(CEntityClient cEntityClient) {
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClientByClientName(cEntityClient);
		return cEntityClientArray;
	}
	
	
	/**
	 * ��ţ�client:12
	 * ���ܣ����ͻ��Ž�Ա������գ����޸Ŀͻ�״̬Ϊδ����
	 * ������CEntityClient(ClientId)
	 * ����ֵ:boolean
	 */
	public boolean updateClientEmployeeIdNUll(CEntityClient cEntityClient){
		boolean bisUpdate=cDaoClient.updateClientEmployeeIdNUll(cEntityClient);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�client:13
	 * ���ܣ�ͳ��Ա���ţ�Ա���˺ţ�Ա�������ͻ�ӵ������
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryClientNumberOfEmployeeInfo() {
		List findResult=cDaoClient.queryClientNumberOfEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�client:14
	 * ���ܣ���ѯ���ѷ���ͻ���Ϣ�Լ���Ա����Ϣ
	 * ������
	 * ����ֵ:HashedMap(CEntityEmployee,CEntityClient)
	 */
	public HashedMap queryClientDistributionWithEmployeeInfo() {
		HashedMap findResult=cDaoClient.queryClientDistributionWithEmployeeInfo();
		return findResult;
	}
	

	/**
	 * ��ţ�client:15
	 * ���ܣ���ѯδ����ͻ��Ŀͻ��ţ��ͻ������ͻ���˾
	 * ������
	 * ����ֵ:List(ClientId,ClientName,ClientCompany)
	 */
	@SuppressWarnings("unchecked")
	public List queryClientUnDistributionInfo(){
		List findResult=cDaoClient.queryClientUnDistributionInfo();
		return findResult;
	}
	
	/*
	 * -------------------------------�ͻ��ύclientsubmit----------------------------------
	 */	
	private CDaoClientSubmit cDaoClientSubmit;
	
	@Resource
	public void setcDaoClientSubmit(CDaoClientSubmit cDaoClientSubmit) {
		this.cDaoClientSubmit = cDaoClientSubmit;
	}
	
	/**
	 * ��ţ�clientsubmit:1
	 * ���ܣ�����һ���ͻ��ύ��ͬʱ������һ���ͻ���
	 * ������cEntityClient(�����ֶ�),cEntityEmployee(EmployeeId),cEntityClientSubmit(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveClientSubmit(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityClientSubmit cEntityClientSubmit) {
		boolean bisSave=cDaoClientSubmit.saveClientSubmit(cEntityClient, cEntityEmployee, cEntityClientSubmit);
		return bisSave;
	}
	
	/**
	 * ��ţ�clientsubmit:2
	 * ���ܣ����ύ״̬������еĿͻ��ύ
	 * ������cEntityClientSubmit(ClientSubmitState)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	public CEntityClientSubmitArray queryAllClientSubmitByClientSubmitState(CEntityClientSubmit cEntityClientSubmit) {
		CEntityClientSubmitArray cEntityClientSubmitArray=cDaoClientSubmit.queryAllClientSubmitByClientSubmitState(cEntityClientSubmit);
		return cEntityClientSubmitArray;
	}
	/**
	 * ��ţ�clientsubmit:3
	 * ���ܣ����ͻ����޸Ŀͻ��ύ״̬
	 * ������cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.ClientSubmit.*)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	public boolean updateClientSubmitStateByClientId(CEntityClient cEntityClient,int OperateState) {
		boolean bisUpdate=cDaoClientSubmit.updateClientSubmitStateByClientId(cEntityClient, OperateState);
		return bisUpdate;
	}
	/**
	 * ��ţ�clientsubmit:4
	 * ���ܣ���Ա���Ż�����еĿͻ��ύ��¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	public CEntityClientSubmitArray queryAllClientSubmitForEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityClientSubmitArray cEntityClientSubmitArray=cDaoClientSubmit.queryAllClientSubmitForEmployeeId(cEntityEmployee);
		return cEntityClientSubmitArray;
	}
	
	
	/**
	 * ��ţ�clientsubmit:5
	 * ���ܣ�������е�δ����ύ��¼����ͻ���ϢԱ����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllNocheckClientWithClientInfoAndEmployeeInfo(){
		LinkedMap findResult=cDaoClientSubmit.queryAllNocheckClientWithClientInfoAndEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�clientsubmit:6
	 * ���ܣ����δ��˿ͻ��ύ����
	 * ������
	 * ����ֵ:int
	 */
	public int queryClientSubmitNocheckNumber() {
		int findResutl=cDaoClientSubmit.queryClientSubmitNocheckNumber();
		return findResutl;
	}
	/*
	 * -------------------------------�ݷüƻ�visitplan----------------------------------
	 */	
	private CDaoVisitPlan cDaoVisitPlan;
	
	@Resource
	public void setcDaoVisitPlan(CDaoVisitPlan cDaoVisitPlan) {
		this.cDaoVisitPlan = cDaoVisitPlan;
	}

	/**
	 * ��ţ�visitplan:1
	 * ���ܣ����Ӱݷüƻ�
	 * ������cEntityClient(ClientId),cEntityEmployee(EmployeeId),cEntityVisitPlan(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitPlan(CEntityClient cEntityClient ,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan) {
		boolean bisSave=cDaoVisitPlan.saveVisitPlan(cEntityClient, cEntityEmployee, cEntityVisitPlan);
		return bisSave;
	}

	/**
	 * ��ţ�visitplan:2
	 * ���ܣ���Ա���Ż�����еİݷüƻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanByEmployeeId(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	/**
	 * ��ţ�visitplan:3
	 * ���ܣ����ݷüƻ����޸İݷ�״̬
	 * ������cEntityVisitPlan(VisitPlanId)
	 * 	   OperateState(MyConstant.VisitPlan.*)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public boolean updateVisitPlanStateByVisitPlanId(CEntityVisitPlan cEntityVisitPlan,int OperateState){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, OperateState);
		return bisUpdate;
	}
	/**
	 * ��ţ�visitplan:4
	 * ���ܣ����ݷüƻ��Ż�ðݷüƻ�
	 * ������cEntityVisitPlan(VisitPlanId)
	 * ����ֵ:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanByVisitPlanId(int VisitPlanId){
		CEntityVisitPlan findResult=cDaoVisitPlan.queryVisitPlanByVisitPlanId(VisitPlanId);
		return findResult;
	}
	/**
	 * ��ţ�visitplan:5
	 * ���ܣ��޸İݷüƻ���ʱ�䲢��״̬��Ϊδ��ʼ
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanStartTime,VisitPlanEndTime,VisitPlanPubdate)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitTime(CEntityVisitPlan cEntityVisitPlan) {
		boolean bisUpdate=cDaoVisitPlan.updateVisitTime(cEntityVisitPlan);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�visitplan:6
	 * ���ܣ����ݷüƻ����޸İݷ�ѭ�����ݷ�ѭ����ѭ�����ͣ�ѭ�������ݷ��޶�������
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanCycle,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanCyclesByVisitPlanId(CEntityVisitPlan cEntityVisitPlan) {
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanCyclesByVisitPlanId(cEntityVisitPlan);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�visitplan:7
	 * ���ܣ����ݷüƻ����޸İݷ����޲���״̬��Ϊִ����
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanEndTime)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanEndTime(CEntityVisitPlan cEntityVisitPlan){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanEndTime(cEntityVisitPlan);
		return bisUpdate;
	}
	/**
	 * ��ţ�visitplan:8
	 * ���ܣ����ͻ��Ų�ѯ������̬�İݷüƻ�������̬Ϊδ��ʼ��ִ���У�δ��ˣ��ѹ��ڣ�
	 * ������cEntityClient(ClientId)
	 * ����ֵ:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanRunningStateByClientId(CEntityClient cEntityClient) {
		CEntityVisitPlan findResult=cDaoVisitPlan.queryVisitPlanRunningStateByClientId(cEntityClient);
		return findResult;
	}
	/**
	 * ��ţ�visitplan:9 ���ܣ���Ա�����Լ��ݷ�״̬������аݷüƻ�(�����ֶ�) ������cEntityEmployee(EmployeeId)
	 * OperateState(MyConstant.VisitPlan.*) ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeIdAndVisitPlanState(CEntityEmployee cEntityEmployee, int OperateState) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanByEmployeeIdAndVisitPlanState(cEntityEmployee, OperateState);
		return cEntityVisitPlanArray;
	}
	/**
	 * ��ţ�visitplan:10 
	 * ���ܣ���Ա���Ż�ȡ����̬�İݷüƻ�(ִ����,�ѹ���)  (�����ֶ�)
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanRunningStateByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanRunningStateByEmployeeId(cEntityEmployee);
		
		return cEntityVisitPlanArray;
	}
	/**
	 * ��ţ�visitplan:11
	 * ���ܣ���Ա���Ż�ȡ��ֹ̬�İݷüƻ���δ���,�����,�ѳ���,��ʧ�ܣ��������ֶΣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanCompleteByEmployeeIs(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanCompleteByEmployeeIs(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	/**
	 * ��ţ�visitplan:12
	 * ���ܣ���Ա���Ż�ȡ�����еİݷüƻ����ͻ���Ϣ
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:HashedMap
	 */
	public HashedMap queryAllVisitPlanAndClientInfoByEmployeeId(CEntityEmployee cEntityEmployee){
		HashedMap findResult=cDaoVisitPlan.queryAllVisitPlanAndClientInfoByEmployeeId(cEntityEmployee);
		return findResult;
	}
	/**
	 * ��ţ�visitplan:13
	 * ���ܣ���Ա���Ż����ݷüƻ������ͻ���ݷ��ܽᣩ
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanWithClientAndVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanWithClientAndVisitConclusionByEmployeeId(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	/**
	 * ��ţ�visitplan:14
	 * ���ܣ�������Ž��ݷüƻ������������ݷó�����¼
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanUndoWithSaveVisitUndoByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanUndoWithSaveVisitUndoByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	/**
	 * ��ţ�visitplan:15
	 * ���ܣ�������Ž��ݷüƻ������״̬���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�visitplan:16
	 * ���ܣ�������ŵõ���󶨵İݷüƻ�
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByBussienssId(CEntityBussiness cEntityBussiness) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanByBussienssId(cEntityBussiness);
		return cEntityVisitPlanArray;
	}
	

	/**
	 * ��ţ�visitplan:17
	 * ���ܣ���ȡ����Ա����δ��ʼ����ִ�аݷ���Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanAllRunning() {
		LinkedMap findResult=cDaoVisitPlan.queryAllVisitPlanAllRunning();
		return findResult;
	}
	/**
	 * ��ţ�visitplan:18
	 * ���ܣ���ȡ����Ա����δ��ˣ��ѹ��ڰݷ���Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanAllWaitDeal() {
		LinkedMap findResult=cDaoVisitPlan.queryAllVisitPlanAllWaitDeal();
		return findResult;
	}
	
	/**
	 * ��ţ�visitplan:19
	 * ���ܣ���ȡ����Ա��������ˣ���ʧ�ܰݷ���Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanComplete(){
		LinkedMap findResult=cDaoVisitPlan.queryAllVisitPlanComplete();
		return findResult;
	}
	
	/**
	 * ��ţ�visitplan:20
	 * ���ܣ���ȡ����δɾԱ����δ��ˣ��ѹ��ڵİݷ�����
	 * ������
	 * ����ֵ:int
	 */
	public int queryVisitPlanWaitDealNumber(){
		int findResult=cDaoVisitPlan.queryVisitPlanWaitDealNumber();
		return findResult;
	}
	
	/**
	 * ��ţ�visitplan:21
	 * ���ܣ����ݰݷüƻ����޸ĳ����״̬
	 * ������CEntityVisitPlan(VisitPlanId,VisitPlanBussinessBindType)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitBindType(CEntityVisitPlan cEntityVisitPlan){
		boolean bisUpdate=cDaoVisitPlan.updateVisitBindType(cEntityVisitPlan);
		return bisUpdate;
	}
	
	
	/**
	 * ��ţ�visitplan:22
	 * ���ܣ�����Ա���Ż�ȡ��󶨵İݷüƻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryVisitPlanBindByEmployee(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray findResult=cDaoVisitPlan.queryVisitPlanBindByEmployee(cEntityEmployee);
		return findResult;
	}
	/*
	 * ------------------------------�ݷ��ܽ� visitconclusion-------------------------------------
	 */	
	private CDaoVisitConclusion cDaoVisitConclusion;
	
	@Resource
	public void setcDaoVisitConclusion(CDaoVisitConclusion cDaoVisitConclusion) {
		this.cDaoVisitConclusion = cDaoVisitConclusion;
	}

	/**
	 * ��ţ�visitconclusion:1
	 * ���ܣ����Ӱݷ��ܽ�
	 * ������cEntityVisitPlan(VisitPlanId),cEntityEmployee(EmployeeId),cEntityVisitConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitConclusion(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion) {
		boolean bisSave=cDaoVisitConclusion.saveVisitConclusion(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		return bisSave;
	}
	
	/**
	 * ��ţ�visitconclusion:2
	 * ���ܣ���Ա���Ż�����еİݷ��ܽ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitConclusionArray
	 */
	public CEntityVisitConclusionArray queryAllVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitConclusionArray cEntityVisitConclusionArray=cDaoVisitConclusion.queryAllVisitConclusionByEmployeeId(cEntityEmployee);
		return cEntityVisitConclusionArray;
	}
	
	/**
	 * ��ţ�visitconclusion:3
	 * ���ܣ��޸İݷ��ܽ����˽��
	 * ������cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionConclusionVisitCheck(CEntityVisitConclusion cEntityVisitConclusion,int OperateState) {
		boolean bisUpdate=cDaoVisitConclusion.updateMissionConclusionVisitCheck(cEntityVisitConclusion, OperateState);
		
		return bisUpdate;
	}
	/**
	 * ��ţ�visitconclusion:4
	 * ���ܣ����ݷüƻ��Ż����δ��˵İݷ��ܽ�
	 * ������CEntityVisitPlan(VisitPlanId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionNocheckByVisitPlanId(CEntityVisitPlan cEntityVisitPlan) {
		CEntityVisitConclusion reult=cDaoVisitConclusion.queryVisitConclusionNocheckByVisitPlanId(cEntityVisitPlan);
		return reult;
	}
	/**
	 * ��ţ�visitconclusion:5
	 * ���ܣ���Ա���Ż�������аݷ��ܽ��Լ��ͻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:HashedMap
	 */
	public LinkedMap queryVisitConclusionWithClientInfoByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoVisitConclusion.queryVisitConclusionWithClientInfoByEmployeeId(cEntityEmployee);
		return findResult;
	}

	/**
	 * ��ţ�visitconclusion:6
	 * ���ܣ����ݷ��ܽ�Ż�ðݷ��ܽ�
	 * ������CEntityVisitConclusion(VisitConclusionId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionByVisitConclusionId(CEntityVisitConclusion cEntityVisitConclusion){
		CEntityVisitConclusion findResult=cDaoVisitConclusion.queryVisitConclusionByVisitConclusionId(cEntityVisitConclusion);
		return findResult;
	}
	
	/**
	 * ��ţ�visitconclusion:7
	 * ���ܣ���ȡ����δɾԱ���İݷ��ܽἰ��Ϣ
	 * ������
	 * ����ֵ:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	public LinkedMap queryAllVisitConclusionInfo(){
		LinkedMap findResult = cDaoVisitConclusion.queryAllVisitConclusionInfo();
		return findResult;
	}
	/*
	 * ------------------------------�ݷó��� visitundo-------------------------------------
	 */	
	private CDaoVisitUndo cDaoVisitUndo;
	@Resource
	public void setcDaoVisitUndo(CDaoVisitUndo cDaoVisitUndo) {
		this.cDaoVisitUndo = cDaoVisitUndo;
	}

	/**
	 * ��ţ�visitundo:1
	 * ���ܣ�����һ���ݷó���
	 * ������cEntityVisitPlan(VisitPlanId),cEntityVisitUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitUndo(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo) {
		boolean bisSave=cDaoVisitUndo.saveVisitUndo(cEntityVisitPlan, cEntityVisitUndo);
		return bisSave;
	}
	/**
	 * ��ţ�visitundo:2
	 * ���ܣ�������еİݷó���
	 * ��������
	 * ����ֵ:CEntityVisitUndoArray
	 */
	public CEntityVisitUndoArray queryAllVisitUndo() {
		CEntityVisitUndoArray cEntityVisitUndoArray=cDaoVisitUndo.queryAllVisitUndo();
		return cEntityVisitUndoArray;
	}
	
	/**
	 * ��ţ�visitundo:3
	 * ���ܣ����ݷó������޸İݷó�����״̬
	 * ������cEntityVisitUndo(VisitUndoId)
	 * 	   OperateState(MyConstant.VisitUndo.*)
	 * ����ֵ:CEntityVisitUndoArray
	 */
	public boolean updateVisitUndoStateByVisitUndoId(CEntityVisitUndo cEntityVisitUndo,int OperateState) {
		boolean bisUpdate=cDaoVisitUndo.updateVisitUndoStateByVisitUndoId(cEntityVisitUndo, OperateState);
		return bisUpdate;
	}

	/**
	 * ��ţ�visitundo:4
	 * ���ܣ������������Ż��������
	 * ������cEntityVisitUndo(VisitUndoId)
	 * ����ֵ:CEntityVisitUndo
	 */
	public CEntityVisitUndo queryVisitUndoByVisitUndoId(CEntityVisitUndo cEntityVisitUndo){
		CEntityVisitUndo findResult=cDaoVisitUndo.queryVisitUndoByVisitUndoId(cEntityVisitUndo);
		return findResult;
	}
	
	/**
	 * ��ţ�visitundo:5
	 * ���ܣ���ȡ����δɾԱ���İݷó�����¼��Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitUndoWithInfo(){
		LinkedMap findResult=cDaoVisitUndo.queryAllVisitUndoWithInfo();
		return findResult;
	}
	/*
	 * -----------------------------�ݷ�����visitdelay-------------------------------------
	 */

	private CDaoVisitDelay cDaoVisitDelay;
	
	@Resource
	public void setcDaoVisitDelay(CDaoVisitDelay cDaoVisitDelay) {
		this.cDaoVisitDelay = cDaoVisitDelay;
	}

	/**
	 * ��ţ�visitdealy:1
	 * ���ܣ�����һ���ݷ�����
	 * ������cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitDelay(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitDealy(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitDelay cEntityVisitDelay){
		boolean bisSave=cDaoVisitDelay.saveVisitDealy(cEntityEmployee, cEntityVisitPlan, cEntityVisitDelay);
		return bisSave;
	}
	
	/**
	 * ��ţ�visitdealy:2
	 * ���ܣ�������аݷ�����
	 * ��������
	 * ����ֵ:CEntityVisitDealyArray
	 */
	public CEntityVisitDealyArray queryAllVisitDelay() {
		CEntityVisitDealyArray cEntityVisitDealyArray=cDaoVisitDelay.queryAllVisitDelay();
		return cEntityVisitDealyArray;
	}

	/**
	 * ��ţ�visitdealy:3
	 * ���ܣ���Ա���Ż�����аݷ�����
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitDealyArray
	 */
	public LinkedMap queryAllVisitDelayByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoVisitDelay.queryAllVisitDelayByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�visitdealy:4
	 * ���ܣ���ȡ����δɾԱ���İݷ�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap(CEntityVisitDelay,CEntityVisitPlan,CEntityEmployee,CEntityClient)
	 */
	public LinkedMap queryAllVisitDelayInfo(){
		LinkedMap findResult=cDaoVisitDelay.queryAllVisitDelayInfo();
		return findResult;
	}
	
	/*
	 * -----------------------------����bussiness------------------------------------
	 */
	private CDaoBussiness cDaoBussiness;
	
	@Resource
	public void setcDaoBussiness(CDaoBussiness cDaoBussiness) {
		this.cDaoBussiness = cDaoBussiness;
	}

	/**
	 * ��ţ�bussiness:1
	 * ���ܣ�����һ������
	 * ������cEntityEmployee(EmployeeId),cEntityBussiness(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveBussiness(CEntityEmployee cEntityEmployee,CEntityBussiness cEntityBussiness) {
		boolean bisSave=cDaoBussiness.saveBussiness(cEntityEmployee, cEntityBussiness);
		return bisSave;
	}
	/**
	 * ��ţ�bussiness:2
	 * ���ܣ���Ա���Ż�����еĳ���
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityBussinessArray
	 */
	public CEntityBussinessArray queryAllBussinessByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityBussinessArray cEntityBussinessArray=cDaoBussiness.queryAllBussinessByEmployeeId(cEntityEmployee);
		return cEntityBussinessArray;
	}
	
	/**
	 * ��ţ�bussiness:3
	 * ���ܣ�����Ǽ��޸ĵǼ�ʱ�䲢������״̬�޸�Ϊִ����
	 * ������cEntityBussiness(BussinessId,BussinessRegisterTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessRegisterTime(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessRegisterTime(cEntityBussiness);
		return bisUpdate;
	}
	/**
	 * ��ţ�bussiness:4
	 * ���ܣ��ִ�Ŀ�ĵصǼ��޸ĵִ��ַ��ʱ��
	 * ������cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessInTimeAndInAddress(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessInTimeAndInAddress(cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�bussiness:5
	 * ���ܣ��뿪Ŀ�ĵصǼ��޸��뿪��ַ��ʱ��
	 * ������cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessOutTimeAndOutAddress(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessOutTimeAndOutAddress(cEntityBussiness);
		return bisUpdate;
	}

	/**
	 * ��ţ�bussiness:6
	 * ���ܣ���������Ǽ�ʱ�䲢�޸ĳ���״̬Ϊδ���
	 * ������cEntityBussiness(BussinessId,BussinessReturnTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessReturn(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessReturn(cEntityBussiness);
		return bisUpdate;
	}
	
	
	/**
	 * ��ţ�bussiness:7
	 * ���ܣ���������޸ĳ���״̬
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessStateByBussinessId(CEntityBussiness cEntityBussiness,int OperateState){
		boolean bisUpdate=cDaoBussiness.updateBussinessStateByBussinessId(cEntityBussiness, OperateState);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�bussiness:8
	 * ���ܣ���Ա���Ż�ȡ����̬�ĳ���(δ�Ǽ�,ִ����)
	 * ������cEntityEmployee(EmployeeId) 
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityBussiness cEntityBussiness=cDaoBussiness.queryBussinessRunningStateByEmployeeId(cEntityEmployee);
		return cEntityBussiness;
	}
	/**
	 * ��ţ�bussiness:9
	 * ���ܣ�������Ż�ó�������(�����ֶ�)
	 * ������CEntityBussiness(BussinessId) 
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessByBussinessId(CEntityBussiness cEntityBussiness){
		CEntityBussiness findReuslt=cDaoBussiness.queryBussinessByBussinessId(cEntityBussiness);
		return findReuslt;
	}
	
	/**
	 * ��ţ�bussiness:10
	 * ���ܣ���ȡ����δɾԱ����ִ���г�����Ϣ(δ�Ǽǣ�ִ����)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessRuning(){
		LinkedMap findResult=cDaoBussiness.queryAllBussinessRuning();
		return findResult;
	}
	
	/**
	 * ��ţ�bussiness:11
	 * ���ܣ���ȡ���������Ա����Ϣ����Ա��û��δ�Ǽǣ�ִ���У�δ��˵ĳ��
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryBussinessAddOkEmployeeInfo(){
		List findResult=cDaoBussiness.queryBussinessAddOkEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�bussiness:12
	 * ���ܣ���ȡ����δɾԱ���Ĵ����������Ϣ(δ���)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessWaitDeal(){
		LinkedMap findResult=cDaoBussiness.queryAllBussinessWaitDeal();
		return findResult;
	}
	
	/**
	 * ��ţ�bussiness:13
	 * ���ܣ�.��ȡ����δɾԱ�����ѽ���������Ϣ�����ͨ������˲�ͨ����
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessComplete(){
		LinkedMap findResult=cDaoBussiness.queryAllBussinessComplete();
		return findResult;
	}
	/*
	 * -----------------------------����bussinessactivity------------------------------------
	 */
	private CDaoBussinessActivity cDaoBussinessActivity;

	@Resource
	public void setcDaoBussinessActivity(CDaoBussinessActivity cDaoBussinessActivity) {
		this.cDaoBussinessActivity = cDaoBussinessActivity;
	}
	/**
	 * ��ţ�bussinessactivity:1
	 * ���ܣ�����һ������
	 * ������cEntityBussinessActivity(�����ֶ�),cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean saveBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness) {
		boolean bisSave=cDaoBussinessActivity.saveBussinessActivity(cEntityBussinessActivity, cEntityBussiness);
		return bisSave;
	}
	
	/**
	 * ��ţ�bussinessactivity:2
	 * ���ܣ�������Ż�����еĳ���
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:CEntityBussinessActivityArray
	 */
	public CEntityBussinessActivityArray queryAllBussinessActivityByBussinessId(CEntityBussiness cEntityBussiness) {
		CEntityBussinessActivityArray cEntityBussinessActivityArray=cDaoBussinessActivity.queryAllBussinessActivityByBussinessId(cEntityBussiness);
		return cEntityBussinessActivityArray;
	}
	
	
	/**
	 * ��ţ�bussinessactivity:3
	 * ���ܣ���������Id�����ͽ����޸İ�״̬
	 * ������cEntityBussinessActivity(BussinessActivityType,BussinessObjectId,BussinessBindType)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessBandTypeByObjectIdAndActivityType(CEntityBussinessActivity cEntityBussinessActivity) {
		boolean bisUpdate=cDaoBussinessActivity.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		return bisUpdate;
	}
	
	/**
	 * ��ţ�bussinessactivity:4
	 * ���ܣ������������
	 * ������cEntityBussinessActivityArray
	 * ����ֵ:��
	 */
	public void saveBatchBussinessActiviy(CEntityBussinessActivityArray cEntityBussinessActivityArray){
		cDaoBussinessActivity.saveBatchBussinessActiviy(cEntityBussinessActivityArray);
	}
	/**
	 * ��ţ�bussinessactivity:5
	 * ���ܣ�������Ż�ð󶨳����ĸ���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:int
	 */
	public int queryBussinessActivityBindNumberByBussinessId(CEntityBussiness cEntityBussiness){
		int result=cDaoBussinessActivity.queryBussinessActivityBindNumberByBussinessId(cEntityBussiness);
		return result;
	}
	
	/**
	 * ��ţ�bussinessactivity:6
	 * ���ܣ�������Ž�����г���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessActivityBindTypeUnbindByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussinessActivity.updateBussinessActivityBindTypeUnbindByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	/*
	 * -----------------------------�������¼bussinessbadrecord------------------------------------
	 */
	
	private CDaoBussinessBadrecord cDaoBussinessBadrecord;
	
	@Resource
	public void setcDaoBussinessBadrecord(
			CDaoBussinessBadrecord cDaoBussinessBadrecord) {
		this.cDaoBussinessBadrecord = cDaoBussinessBadrecord;
	}

	/**
	 * ��ţ�bussinessbadrecord:1
	 * ���ܣ�����һ���������¼
	 * ������cEntityBussiness(BussinessId),cEntityBussinessBadrecord(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveBussinessBadrecord(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord) {
		boolean bisSave=cDaoBussinessBadrecord.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord);
		return bisSave;
	}
	
	/**
	 * ��ţ�bussinessbadrecord:2
	 * ���ܣ�������еĳ������¼
	 * ��������
	 * ����ֵ:CEntityBussinessBadrecordArray
	 */
	public CEntityBussinessBadrecordArray queryAllBussinessBadrecord() {
		CEntityBussinessBadrecordArray cEntityBussinessBadrecordArray=cDaoBussinessBadrecord.queryAllBussinessBadrecord();
		
		return cEntityBussinessBadrecordArray;
	}
	/**
	 * ��ţ�bussinessbadrecord:3
	 * ���ܣ���Ա���Ż�ȡ�����г������¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessBadrecordByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoBussinessBadrecord.queryAllBussinessBadrecordByEmployeeId(cEntityEmployee);
		return findResult;
	}
	

	/**
	 * ��ţ�bussinessbadrecord:4
	 * ���ܣ���ȡ����δɾԱ���ĳ������¼��Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessBadrecordInfo(){
		LinkedMap findResult=cDaoBussinessBadrecord.queryAllBussinessBadrecordInfo();
		return findResult;
	}

	/*
	 * --------------------------�����bussinessundo-------------------------------------------
	 */
	private CDaoBussinessUndo cDaoBussinessUndo;
	
	@Resource
	public void setcDaoBussinessUndo(CDaoBussinessUndo cDaoBussinessUndo) {
		this.cDaoBussinessUndo = cDaoBussinessUndo;
	}

	/**
	 * ��ţ�bussinessundo:1
	 * ���ܣ�����һ�������
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean savaBussinessUndo(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo) {
		boolean bisSave=cDaoBussinessUndo.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		return bisSave;
	}
	
	
	/**
	 * ��ţ�bussinessundo:2
	 * ���ܣ���ѯ����δɾԱ���������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessUndoInfo(){
		LinkedMap findResult=cDaoBussinessUndo.queryAllBussinessUndoInfo();
		return findResult;
	}
	
	/*
	 * ----------------------------����attendance------------------------------------
	 */
	private CDaoAttendance cDaoAttendance;
	
	@Resource
	public void setcDaoAttendance(CDaoAttendance cDaoAttendance) {
		this.cDaoAttendance = cDaoAttendance;
	}

	/**
	 * ��ţ�attendance:1
	 * ���ܣ��������뿼�ڱ�
	 * ������cEntityAttendanceArray
	 * ����ֵ:��
	 */
	public void saveAttendances(CEntityAttendanceArray cEntityAttendanceArray) {
		cDaoAttendance.saveAttendances(cEntityAttendanceArray);
	}
	/**
	 * ��ţ�attendance:2
	 * ���ܣ���Ա���ż��������ڽ���ǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceRegisterTime,AttendanceDate)
	 * ����ֵ:��
	 */
	public boolean updateAttendanceRegisterTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=cDaoAttendance.updateAttendanceRegisterTimeByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		
		return bisUpdate;
	}
	/**
	 * ��ţ�attendance:3
	 * ���ܣ���Ա���ż��������ڽ���ǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceSignoutTime,AttendanceDate)
	 * ����ֵ:boolean
	 */
	public boolean updateAttendanceSignoutTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee){
		boolean bisUpdate=cDaoAttendance.updateAttendanceSignoutTimeByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		return bisUpdate;
	}
	/**
	 * ��ţ�attendance:4
	 * ���ܣ������ڻ�����еĿ���
	 * ������cEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAllAttendanceByAttendanceDate(CEntityAttendance cEntityAttendance) {
		CEntityAttendanceArray cEntityAttendanceArray=cDaoAttendance.queryAllAttendanceByAttendanceDate(cEntityAttendance);
		return cEntityAttendanceArray;
	}
	/**
	 * ��ţ�attendance:5
	 * ���ܣ���Ա���ź͵������ڲ�ѯ����
	 * ������cEntityAttendance(AttendanceDate),CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityAttendance
	 */
	public CEntityAttendance queryAttendanceByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee){
		CEntityAttendance findReulst=cDaoAttendance.queryAttendanceByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		return findReulst;
	}
	
	/**
	 * ��ţ�attendance:6
	 * ���ܣ������ڻ�����еĿ������ݣ���Ա���ţ�Ա���˺ţ�Ա����
	 * ������cEntityAttendance(AttendanceDate)
	 * ����ֵ:HashedMap
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByAttendaceData(CEntityAttendance cEntityAttendance){
		HashedMap map=cDaoAttendance.queryAllAttendanceWithEmployeeDateByAttendaceData(cEntityAttendance);
		return map;
	}
	
	/**
	 * ��ţ�attendance:7
	 * ���ܣ���Ա���Ų�ѯ���п��ڼ�¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAllEmployeeAttendance(CEntityEmployee cEntityEmployee) {
		CEntityAttendanceArray cEntityAttendanceArray=cDaoAttendance.queryAllEmployeeAttendance(cEntityEmployee);
		return cEntityAttendanceArray;
	}
	

	/**
	 * ��ţ�attendance:8
	 * ���ܣ���Ա���ź����ڲ�ѯ���ڼ�¼
	 * ������CEntityEmployee(EmployeeId),CEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAtteandenByEmployeeIdandAttendanceOldDate(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		CEntityAttendanceArray cEntityAttendanceArray=cDaoAttendance.queryAtteandenByEmployeeIdandAttendanceOldDate(cEntityEmployee,cEntityAttendance);
		return cEntityAttendanceArray;
	}
	
	/**
	 * ��ţ�attendance:9
	 * ���ܣ���Ա���������ڲ�ѯ���ڼ�¼
	 * ������CEntityEmployee(EmployeeName),CEntityAttendance(AttendanceDate)
	 * ����ֵ:HashedMap
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByEmployeeName(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance) {
		HashedMap findResult=cDaoAttendance.queryAllAttendanceWithEmployeeDateByEmployeeName(cEntityEmployee, cEntityAttendance);
		return findResult;
	}
	/**
	 * ��ţ�attendance:10
	 * ���ܣ�����ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYear(String year) {
		List findResult=cDaoAttendance.queryStatisticalByYear(year);
		return findResult;
	}
	
	/**
	 * ��ţ�attendance:11
	 * ���ܣ�����ݣ��·ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)��month(mm)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearAndMonth(String year,String month){
		List findResult=cDaoAttendance.queryStatisticalByYearAndMonth(year,month);
		return findResult;
	}
	
	/**
	 * ��ţ�attendance:12
	 * ���ܣ���Ա��������ݣ��·ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)��month(mm)��cEntityEmployee(EmployeeName)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearMonthAndEmployeeName(CEntityEmployee cEntityEmployee,String year,String month){
		List findResult=cDaoAttendance.queryStatisticalByYearMonthAndEmployeeName(cEntityEmployee, year, month);
		return findResult;
	}
	/*
	 * ----------------------------֪ͨ notice------------------------------------
	 */
	
	private CDaoNotice cDaoNotice;
	
	@Resource
	public void setcDaoNotice(CDaoNotice cDaoNotice) {
		this.cDaoNotice = cDaoNotice;
	}

	/**
	 * ��ţ�notice:1
	 * ���ܣ�����һ��֪ͨ
	 * ������cEntityNotice(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveNotice(CEntityNotice cEntityNotice) {
		boolean bisSave=cDaoNotice.saveNotice(cEntityNotice);
		return bisSave;
	}
	
	/**
	 * ��ţ�notice:2
	 * ���ܣ����ȫ��֪ͨ
	 * ��������
	 * ����ֵ:CEntityNoticeArray
	 */
	public CEntityNoticeArray queryAllNotice() {
		CEntityNoticeArray cEntityNoticeArray=cDaoNotice.queryAllNotice();
		return cEntityNoticeArray;
	}

	/*
	 * ----------------------------���� suggest----------------------------------
	 */
	private CDaoSuggest cDaoSuggest;

	@Resource
	public void setcDaoSuggest(CDaoSuggest cDaoSuggest) {
		this.cDaoSuggest = cDaoSuggest;
	}
	

	/**
	 * ��ţ�suggest:1
	 * ���ܣ�����һ������
	 * ������cEntityEmployee(EmployeeId),cEntitySuggest(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest) {
		boolean bisSave=cDaoSuggest.saveSuggest(cEntityEmployee, cEntitySuggest);
		
		return bisSave;
	}
	
	
	/**
	 * ��ţ�suggest:2
	 * ���ܣ�������еĽ���
	 * ��������
	 * ����ֵ:CEntitySuggestArray
	 */
	public CEntitySuggestArray queryAllSuggest() {
		CEntitySuggestArray cEntitySuggestArray=cDaoSuggest.queryAllSuggest();
		return cEntitySuggestArray;
	}

	
}
