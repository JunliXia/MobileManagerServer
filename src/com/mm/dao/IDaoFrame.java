package com.mm.dao;


import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;

import com.mm.entity.CEntityAddress;
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



public interface IDaoFrame {

/*
 *------------------------------ ����Ա administrator----------------------------
 */
	/**
	 * ��ţ�administrator:1
	 * ���ܣ�����һ������Ա�ʺ�
	 * ������cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * ����ֵ:boolean
	 */
	public boolean saveAdministrator(CEntityAdministrator cEntityAdministrator);
	
	/**
	 * ��ţ�administrator:2
	 * ���ܣ����ʺ������ѯ����Ա
	 * ������cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * ����ֵ:CEntityAdministrator�������ֶΣ�
	 */
	public CEntityAdministrator queryAdministrator(CEntityAdministrator cEntityAdministrator);

/*
 * -------------------------------Ա��employee----------------------------------
 */	
	/**
	 * ��ţ�employee:1
	 * ���ܣ�����һ��Ա��
	 * ������cEntityEmployee(EmployeeAccount,EmployeePassword,EmployeeName,EmployeePhone
	 * 					   EmployeeSex,EmployeeDepartment,EmployeeJob,EmployeeType)
	 * ����ֵ:boolean
	 */
	public boolean saveEmployee(CEntityEmployee cEntityEmployee);
	
	
	/**
	 * ��ţ�employee:2
	 * ���ܣ���ȡ����δɾԱ��
	 * ��������
	 * ����ֵ:CEntityEmployeeArray�������ֶΣ����漰������
	 */
	public CEntityEmployeeArray queryAllEmployee();

	
	/**
	 * ��ţ�employee:3
	 * ���ܣ��޸�Ա�����
	 * ������cEntityEmployee(EmployeeId,EmployeeType)
	 * ����ֵ:boolean
	 */
	public boolean updateEmployeeType(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�employee:4
	 * ���ܣ����ʺ��������Ա��
	 * ������cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * ����ֵ:CEntityEmployee�������ֶΣ�
	 */
	public CEntityEmployee queryEmployeeByAccountAndPassword(CEntityEmployee cEntityEmployee);

	
	/**
	 * ��ţ�employee:5
	 * ���ܣ����ʺŲ���Ա��
	 * ������cEntityEmployee(EmployeeAccount)
	 * ����ֵ:CEntityEmployee�������ֶΣ�
	 */
	public CEntityEmployee queryEmployeeByAccount(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�employee:6
	 * ���ܣ�����������е�Ա��
	 * ������cEntityEmployee(EmployeeType)
	 * ����ֵ:CEntityEmployee�������ֶΣ�
	 */
	public CEntityEmployeeArray queryAllEmployeeByType(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�employee:7 
	 * ���ܣ���Ա�����޸�Ա��
	 * ������cEntityEmployee(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean updateEmployeeByEmployeeId(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�employee:8 
	 * ���ܣ���ȡ����δɾԱ����
	 * ������
	 * ����ֵ:List<String>
	 */
	public List<String> queryAllEmployeeName();
	/**
	 * ��ţ�employee:9 
	 * ���ܣ���Ա�����������δɾԱ��
	 * ������CEntityEmployee(EmployeeName)
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray queryAllEmployeeByEmployeeName(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�employee:10 
	 * ���ܣ���Ա�����Ż������δɾԱ��
	 * ������CEntityEmployee(EmployeeDepartment)
	 * ����ֵ:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray queryAllEmployeeByEmployeeDepartment(CEntityEmployee cEntityEmployee);
	/*
	 * -------------------------------���� mission----------------------------------
	 */	
	
	/**
	 * ��ţ�mission:1
	 * ���ܣ���������
	 * ������cEntityEmployee(EmployeeId),cEntityMission(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission);
	

	/**
	 * ��ţ�mission:2
	 * ���ܣ��޸�����״̬
	 * ������EntityMission(MissionId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionState(CEntityMission cEntityMission,int OperateState);
	
	
	/**
	 * ��ţ�mission:3
	 * ���ܣ��޸�����״̬
	 * ������EntityMission(MissionId,MissionDelayState)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionDelayState(CEntityMission cEntityMission);
	
	
	/**
	 * ��ţ�mission:4
	 * ���ܣ�����Ա���Ż����������
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByEmployeeId(CEntityEmployee cEntityEmployee);


	/**
	 * ��ţ�mission:5
	 * ���ܣ�������״̬�����������
	 * ������OperateState(MyConstant.Mission.*)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByMissionType(int OperateState);

	/**
	 * ��ţ�mission:6
	 * ���ܣ�������Ż����������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:CEntityMission
	 */
	public CEntityMission queryMissionByMissionId(int MissionId);
	
	/**
	 * ��ţ�mission:7
	 * ���ܣ��޸��������ޣ��޸����޾��ж�Ϊ�����ڣ�
	 * ������cEntityMission(MissionId,MissionDeadline)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionDeadline(CEntityMission cEntityMission);
	
	
	/**
	 * ��ţ�mission:8
	 * ���ܣ���Ա���ż�״̬�����������
	 * ������cEntityEmployee(EmployeeId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByEmployeeIdAndMissionState(CEntityEmployee cEntityEmployee,int OperateState);
	/**
	 * ��ţ�mission:9
	 * ���ܣ���Ա���Ż�ȡ��ֹ̬������δ���,�����,�ѳ���,��ʧ�ܣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionCompleteStateByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�mission:10
	 * ���ܰ�Ա���Ż�ȡִ��̬������ִ���У��ѹ��ڣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionRunningStateByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�mission:11
	 * ����ͨ����ǰ�����޸�����״̬(δ����,ִ����)Ϊ�ѹ���	
	 * ������CEntityMission(MissionDeadline)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionStateForOutTimeState(CEntityMission cEntityMission);
	
	/**
	 * ��ţ�mission:12
	 * ����:������Ž���������״̬���	
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness);
	/**
	 * ��ţ�mission:13
	 * ����:������Ž���������������������¼
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionStateUndoWithSaveMissionUndoByBussinessId(CEntityBussiness cEntityBussiness);
	/**
	 * ��ţ�mission:14
	 * ����:������ŵõ���󶨵�����
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMisssionByBussinessId(CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�mission:15
	 * ����:��ȡ����Ա����δ���ܣ�ִ����������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionRuning();
	/**
	 * ��ţ�mission:16
	 * ����:ͳ��Ա���ţ�Ա���˺ţ�Ա������������ִ��������δ���ܣ�ִ���У�
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryMissionRunningNumberOfEmployeeInfo();
	
	/**
	 * ��ţ�mission:17
	 * ����:��ȡ����Ա����δ��ˣ��ѹ���������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionWaitDeal();
	
	
	/**
	 * ��ţ�mission:18
	 * ����:��ȡ����Ա������ʧ�ܣ������������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionComplete();
	
	/**
	 * ��ţ�mission:19
	 * ����:��ȡ����Ա����δ��ˣ��ѹ�����������
	 * ������
	 * ����ֵ:int
	 */
	public int queryMissionWaitDealNumber();
	
	/**
	 * ��ţ�mission:20
	 * ����:����Ա���Ż�ȡ��󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryMissionBindByEmployee(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�mission:21
	 * ����:����������޸ĳ����״̬
	 * ������CEntityMission(MissionId,MissionBussinessBandState)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionBindType(CEntityMission cEntityMission);
	
	
	/**
	 * ��ţ�mission:22
	 * ����:����Ա���Ż�ȡ��δ�󶨵�������Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionArray
	 */
	public CEntityMissionArray queryMissionUnBindByEmployee(CEntityEmployee cEntityEmployee);
	

	
	/*
	 * -------------------------------�������� missiondelay----------------------------------
	 */	
	
	/**
	 * ��ţ�missiondealy:1
	 * ���ܣ�����һ����������
	 * ������cEntityMission(MissionId),cEntityMissionDelay(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMissionDealy(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay);
	
	
	/**
	 * ��ţ�missiondealy:2
	 * ���ܣ�������Ż��������������
	 * ������cEntityMission(MissionId)
	 * ����ֵ:CEntityMissionDelayArray
	 */
	
	public CEntityMissionDelayArray queryAllMissionDelayByMissionId(CEntityMission cEntityMission);
	
	/**
	 * ��ţ�missiondealy:3
	 * ���ܣ���Ա���Ż�����е���������
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionDelayByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�missiondealy:4
	 * ���ܣ�������е����ڼ�¼����������Ϣ��Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionDelayWithMissionInfoAndEmployeeInfo();
	
	/*
	 * -------------------------------�����ܽ� missionconclusion----------------------------------
	 */	
	
	/**
	 * ��ţ�missionconclusion:1
	 * ���ܣ�����һ�������ܽ�
	 * ������cEntityMission(MissionId),cEntityMissionConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMissionConclusion(CEntityMission cEntityMission, CEntityMissionConclusion cEntityMissionConclusion);
	
	/**
	 * ��ţ�missionconclusion:2
	 * ���ܣ�������Ż�������ܽ�
	 * ������cEntityMission(MissionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissonConclusionByMissionId(CEntityMission cEntityMission);
	
	/**
	 * ��ţ�missionconclusion:3
	 * ���ܣ��޸������ܽ���˽��
	 * ������cEntityMissionConclusion(MissionConclusionId)
	 * 	   OperateState(MyConstant.MissionConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionCheck(CEntityMissionConclusion cEntityMissionConclusion,int OperateState);
	
	
	/**
	 * ��ţ�missionconclusion:4
	 * ���ܣ���Ա���Ż�ȡ�����������ܽ�
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityMissionConclusionArray
	 */
	public LinkedMap queryAllMissionConclusionByEmployeeId(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�missionconclusion:5
	 * ���ܣ��������ܽ�Ż�ȡ�������ܽ�
	 * ������CEntityMissionConclusion(MissionConclusionId)
	 * ����ֵ:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissionConclusionByMissionConclusionId(CEntityMissionConclusion cEntityMissionConclusion);
	
	/**
	 * ��ţ�missionconclusion:6
	 * ���ܣ���ȡ���������ܽἰ������Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionConclusionWithEmployeeInfo();
	/*
	 * -------------------------------������missionundo----------------------------------
	 */	
	/**
	 * ��ţ�missionundo:1
	 * ���ܣ�����һ��������
	 * ������cEntityMission(MissionId),cEntityMissionUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveMissionUndo(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo);
	
	
	/**
	 * ��ţ�missionundo:2
	 * ���ܣ�������е�������
	 * ��������
	 * ����ֵ:CEntityMissionUndoArray
	 */
	public CEntityMissionUndoArray queryAllMissionUndo();
	
	
	/**
	 * ��ţ�missionundo:3
	 * ���ܣ��޸ĳ�������
	 * ������cEntityMissionUndo(MissionUndoId)
	 * 	   OperateState(Myconstant.MissionUndo.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionUndoType(CEntityMissionUndo cEntityMissionUndo,int OperateState);
	
	
	/**
	 * ��ţ�missionundo:4
	 * ���ܣ��������Ż������
	 * ������cEntityMissionUndo(MissionUndoId)
	 * ����ֵ:CEntityMission
	 */
	public CEntityMission queryMissionByMissionUndoId(CEntityMissionUndo cEntityMissionUndo);

	
	/**
	 * ��ţ�missionundo:5
	 * ���ܣ��������Ż�ó���
	 * ������cEntityMissionUndo(MissionUndoId)
	 * ����ֵ:CEntityMissionUndo
	 */
	public CEntityMissionUndo queryMissionUndoByMissionUndoId(CEntityMissionUndo cEntityMissionUndo);
	
	/**
	 * ��ţ�missionundo:6
	 * ���ܣ�������е�����������Ա����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllMissionUndoWithEmployeeInfo();
	/*
	 * -------------------------------�ͻ�client---------------------------------
	 */	
	
	/**
	 * ��ţ�client:1
	 * ���ܣ����ӿͻ�
	 * ������cEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveClient(CEntityClient cEntityClient);
	
	/**
	 * ��ţ�client:2
	 * ���ܣ���Ա���Ż�����еĿͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�client:3
	 * ���ܣ��޸Ŀͻ�Ա����(���޸Ŀͻ�״̬Ϊ�ѷ���)
	 * ������cEntityEmployee(EmployeeId),cEntityClient(ClientId)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean updateClientEmployeeIdAndClientState(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee);
	
	
	/**
	 * ��ţ�client:4
	 * ���ܣ����ͻ�״̬������еĿͻ�
	 * ������cEntityClient(ClientState)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByClientState(CEntityClient cEntityClient);

	

	/**
	 * ��ţ�client:5
	 * ���ܣ����ͻ����޸Ŀͻ�״̬
	 * ������cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.Client.*)
	 * ����ֵ:CEntityClientArray
	 */
	public boolean updateClientStateByClientId(CEntityClient cEntityClient,int OperateState);
	

	/**
	 * ��ţ�client:6
	 * ���ܣ����ͻ��Ż�ÿͻ�����
	 * ������cEntityClient(ClientId)
	 * ����ֵ:CEntityClient
	 */
	public CEntityClient queryClientByClientId(CEntityClient cEntityClient);
	
	/**
	 * ��ţ�client:7
	 * ���ܣ���Ա���Ż�ȡ���ڿͻ��ύ�е����пͻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryClientForSubmitEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�client:8
	 * ���ܣ���ѯ�������͵Ŀͻ�
	 * ��������
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClient();
	/**
	 * ��ţ�client:9
	 * ���ܣ��޸Ŀͻ�����
	 * ������CEntityClient(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean updateClient(CEntityClient cEntityClient);
	/**
	 * ��ţ�client:10
	 * ���ܣ���Ա���Ż�ȡ���ѷ���ͻ���
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:List<String>
	 */
	public List<String> queryAllClientNameByEmployeeId(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�client:11
	 * ���ܣ����ͻ�����ȡ�ͻ�����
	 * ������CEntityClient(ClientName)
	 * ����ֵ:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByClientName(CEntityClient cEntityClient);
	
	
	/**
	 * ��ţ�client:12
	 * ���ܣ����ͻ��Ž�Ա������գ����޸Ŀͻ�״̬Ϊδ����
	 * ������CEntityClient(ClientId)
	 * ����ֵ:boolean
	 */
	public boolean updateClientEmployeeIdNUll(CEntityClient cEntityClient);
	
	/**
	 * ��ţ�client:13
	 * ���ܣ�ͳ��Ա���ţ�Ա���˺ţ�Ա�������ͻ�ӵ������
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryClientNumberOfEmployeeInfo();
	/**
	 * ��ţ�client:14
	 * ���ܣ���ѯ���ѷ���ͻ���Ϣ�Լ���Ա����Ϣ
	 * ������
	 * ����ֵ:HashedMap(CEntityEmployee,CEntityClient)
	 */
	public HashedMap queryClientDistributionWithEmployeeInfo();
	
	

	/**
	 * ��ţ�client:15
	 * ���ܣ���ѯδ����ͻ��Ŀͻ��ţ��ͻ������ͻ���˾
	 * ������
	 * ����ֵ:List(ClientId,ClientName,ClientCompany)
	 */
	@SuppressWarnings("unchecked")
	public List queryClientUnDistributionInfo();
	/*
	 * -------------------------------�ͻ��ύclientsubmit----------------------------------
	 */	
	
	/**
	 * ��ţ�clientsubmit:1
	 * ���ܣ�����һ���ͻ��ύ��ͬʱ������һ���ͻ���
	 * ������cEntityClient(�����ֶ�),cEntityEmployee(EmployeeId),cEntityClientSubmit(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveClientSubmit(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityClientSubmit cEntityClientSubmit);
	

	/**
	 * ��ţ�clientsubmit:2
	 * ���ܣ����ύ״̬������еĿͻ��ύ
	 * ������cEntityClientSubmit(ClientSubmitState)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	public CEntityClientSubmitArray queryAllClientSubmitByClientSubmitState(CEntityClientSubmit cEntityClientSubmit);
	


	/**
	 * ��ţ�clientsubmit:3
	 * ���ܣ����ͻ����޸Ŀͻ��ύ״̬
	 * ������cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.ClientSubmit.*)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	public boolean updateClientSubmitStateByClientId(CEntityClient cEntityClient,int OperateState);
	/**
	 * ��ţ�clientsubmit:4
	 * ���ܣ���Ա���Ż�����еĿͻ��ύ��¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityClientSubmitArray
	 */
	public CEntityClientSubmitArray queryAllClientSubmitForEmployeeId(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�clientsubmit:5
	 * ���ܣ�������е�δ����ύ��¼����ͻ���ϢԱ����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllNocheckClientWithClientInfoAndEmployeeInfo();
	
	/**
	 * ��ţ�clientsubmit:6
	 * ���ܣ����δ��˿ͻ��ύ����
	 * ������
	 * ����ֵ:int
	 */
	public int queryClientSubmitNocheckNumber();
	/*
	 * -------------------------------�ݷüƻ�visitplan----------------------------------
	 */	
	
	/**
	 * ��ţ�visitplan:1
	 * ���ܣ����Ӱݷüƻ�
	 * ������cEntityClient(ClientId),cEntityEmployee(EmployeeId),cEntityVisitPlan(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitPlan(CEntityClient cEntityClient ,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan);

	
	/**
	 * ��ţ�visitplan:2
	 * ���ܣ���Ա���Ż�����еİݷüƻ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�visitplan:3
	 * ���ܣ����ݷüƻ����޸İݷ�״̬
	 * ������cEntityVisitPlan(VisitPlanId)
	 * 	   OperateState(MyConstant.VisitPlan.*)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public boolean updateVisitPlanStateByVisitPlanId(CEntityVisitPlan cEntityVisitPlan,int OperateState);
	
	/**
	 * ��ţ�visitplan:4
	 * ���ܣ����ݷüƻ��Ż�ðݷüƻ�
	 * ������cEntityVisitPlan(VisitPlanId)
	 * ����ֵ:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanByVisitPlanId(int VisitPlanId);

	/**
	 * ��ţ�visitplan:5
	 * ���ܣ��޸İݷüƻ���ʱ�䲢��״̬��Ϊδ��ʼ
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanStartTime,VisitPlanEndTime,VisitPlanPubdate)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitTime(CEntityVisitPlan cEntityVisitPlan);
	
	/**
	 * ��ţ�visitplan:6
	 * ���ܣ����ݷüƻ����޸İݷ�ѭ�����ݷ�ѭ����ѭ�����ͣ�ѭ�������ݷ��޶�������
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanCycle,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanCyclesByVisitPlanId(CEntityVisitPlan cEntityVisitPlan);

	/**
	 * ��ţ�visitplan:7
	 * ���ܣ����ݷüƻ����޸İݷ����޲���״̬��Ϊִ����
	 * ������cEntityVisitPlan(VisitPlanId,VisitPlanEndTime)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanEndTime(CEntityVisitPlan cEntityVisitPlan);
	
	/**
	 * ��ţ�visitplan:8
	 * ���ܣ����ͻ��Ų�ѯ������̬�İݷüƻ�������̬Ϊδ��ʼ��ִ���У�δ��ˣ��ѹ��ڣ�
	 * ������cEntityClient(ClientId)
	 * ����ֵ:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanRunningStateByClientId(CEntityClient cEntityClient);
	
	/**
	 * ��ţ�visitplan:9 ���ܣ���Ա�����Լ��ݷ�״̬������аݷüƻ�(�����ֶ�) ������cEntityEmployee(EmployeeId)
	 * OperateState(MyConstant.VisitPlan.*) ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeIdAndVisitPlanState(CEntityEmployee cEntityEmployee, int OperateState);


	/**
	 * ��ţ�visitplan:10 
	 * ���ܣ���Ա���Ż�ȡ����̬�İݷüƻ�(ִ����,�ѹ���)  (�����ֶ�)
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanRunningStateByEmployeeId(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�visitplan:11
	 * ���ܣ���Ա���Ż�ȡ��ֹ̬�İݷüƻ���δ���,�����,�ѳ���,��ʧ�ܣ��������ֶΣ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanCompleteByEmployeeIs(CEntityEmployee cEntityEmployee);

	/**
	 * ��ţ�visitplan:12
	 * ���ܣ���Ա���Ż�ȡ�����еİݷüƻ����ͻ���Ϣ
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:HashedMap
	 */
	public HashedMap queryAllVisitPlanAndClientInfoByEmployeeId(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�visitplan:13
	 * ���ܣ���Ա���Ż����ݷüƻ������ͻ���ݷ��ܽᣩ
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanWithClientAndVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee);
	
	
	/**
	 * ��ţ�visitplan:14
	 * ���ܣ�������Ž��ݷüƻ������������ݷó�����¼
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanUndoWithSaveVisitUndoByBussinessId(CEntityBussiness cEntityBussiness);
	/**
	 * ��ţ�visitplan:15
	 * ���ܣ�������Ž��ݷüƻ������״̬���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�visitplan:16
	 * ���ܣ�������ŵõ���󶨵İݷüƻ�
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByBussienssId(CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�visitplan:17
	 * ���ܣ���ȡ����Ա����δ��ʼ����ִ�аݷ���Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanAllRunning();
	
	/**
	 * ��ţ�visitplan:18
	 * ���ܣ���ȡ����Ա����δ��ˣ��ѹ��ڰݷ���Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanAllWaitDeal();
	
	
	/**
	 * ��ţ�visitplan:19
	 * ���ܣ���ȡ����Ա��������ˣ���ʧ�ܰݷ���Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanComplete();
	
	/**
	 * ��ţ�visitplan:20
	 * ���ܣ���ȡ����δɾԱ����δ��ˣ��ѹ��ڵİݷ�����
	 * ������
	 * ����ֵ:int
	 */
	public int queryVisitPlanWaitDealNumber();

	/**
	 * ��ţ�visitplan:21
	 * ���ܣ����ݰݷüƻ����޸ĳ����״̬
	 * ������CEntityVisitPlan(VisitPlanId,VisitPlanBussinessBindType)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitBindType(CEntityVisitPlan cEntityVisitPlan);
	
	/**
	 * ��ţ�visitplan:22
	 * ���ܣ�����Ա���Ż�ȡ��󶨵İݷüƻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryVisitPlanBindByEmployee(CEntityEmployee cEntityEmployee);

	
	/**
	 * ��ţ�visitplan:23
	 * ���ܣ�����Ա���Ż�ȡ��󶨵İݷüƻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryVisitPlanUnBindByEmployee(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�visitplan:24
	 * ���ܣ�ͨ����ǰ�����޸İݷ�״̬(δ����,ִ����)Ϊ�ѹ���
	 * ������cEntityVisitPlan(VisitPlanEndTime)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanStateForOutTimeState(CEntityVisitPlan cEntityVisitPlan);

	/**
	 * ��ţ�visitplan:25
	 * ���ܣ�ͨ����ǰ�����޸İݷ�״̬δ��ʼΪִ����
	 * ������cEntityVisitPlan(VisitPlanEndTime)
	 * ����ֵ:boolean
	 */
	public boolean updateVisitPlanStateForStart();
	/*
	 * ------------------------------�ݷ��ܽ� visitconclusion-------------------------------------
	 */	
	
	/**
	 * ��ţ�visitconclusion:1
	 * ���ܣ����Ӱݷ��ܽ�
	 * ������cEntityVisitPlan(VisitPlanId),cEntityEmployee(EmployeeId),cEntityVisitConclusion(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitConclusion(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion);
	
	/**
	 * ��ţ�visitconclusion:2
	 * ���ܣ���Ա���Ż�����еİݷ��ܽ�
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitConclusionArray
	 */
	public CEntityVisitConclusionArray queryAllVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�visitconclusion:3
	 * ���ܣ��޸İݷ��ܽ����˽��
	 * ������cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * ����ֵ:boolean
	 */
	public boolean updateMissionConclusionVisitCheck(CEntityVisitConclusion cEntityVisitConclusion,int OperateState);
	/**
	 * ��ţ�visitconclusion:4
	 * ���ܣ����ݷüƻ��Ż����δ��˵İݷ��ܽ�
	 * ������CEntityVisitPlan(VisitPlanId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionNocheckByVisitPlanId(CEntityVisitPlan cEntityVisitPlan);
	
	/**
	 * ��ţ�visitconclusion:5
	 * ���ܣ���Ա���Ż�������аݷ��ܽ��Լ��ͻ���Ϣ
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:HashedMap
	 */
	public LinkedMap queryVisitConclusionWithClientInfoByEmployeeId(CEntityEmployee cEntityEmployee);

	/**
	 * ��ţ�visitconclusion:6
	 * ���ܣ����ݷ��ܽ�Ż�ðݷ��ܽ�
	 * ������CEntityVisitConclusion(VisitConclusionId)
	 * ����ֵ:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionByVisitConclusionId(CEntityVisitConclusion cEntityVisitConclusion);
	
	/**
	 * ��ţ�visitconclusion:7
	 * ���ܣ���ȡ����δɾԱ���İݷ��ܽἰ��Ϣ
	 * ������
	 * ����ֵ:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	public LinkedMap queryAllVisitConclusionInfo();
	/*
	 * ------------------------------�ݷó��� visitundo-------------------------------------
	 */	
	

	/**
	 * ��ţ�visitundo:1
	 * ���ܣ�����һ���ݷó���
	 * ������cEntityVisitPlan(VisitPlanId),cEntityVisitUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitUndo(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo);
	
	

	/**
	 * ��ţ�visitundo:2
	 * ���ܣ�������еİݷó���
	 * ��������
	 * ����ֵ:CEntityVisitUndoArray
	 */
	public CEntityVisitUndoArray queryAllVisitUndo();

	/**
	 * ��ţ�visitundo:3
	 * ���ܣ����ݷó������޸İݷó�����״̬
	 * ������cEntityVisitUndo(VisitUndoId)
	 * 	   OperateState(MyConstant.VisitUndo.*)
	 * ����ֵ:CEntityVisitUndoArray
	 */
	public boolean updateVisitUndoStateByVisitUndoId(CEntityVisitUndo cEntityVisitUndo,int OperateState);

	/**
	 * ��ţ�visitundo:4
	 * ���ܣ������������Ż��������
	 * ������cEntityVisitUndo(VisitUndoId)
	 * ����ֵ:CEntityVisitUndo
	 */
	public CEntityVisitUndo queryVisitUndoByVisitUndoId(CEntityVisitUndo cEntityVisitUndo);
	
	
	/**
	 * ��ţ�visitundo:5
	 * ���ܣ���ȡ����δɾԱ���İݷó�����¼��Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllVisitUndoWithInfo();
	/*
	 * -----------------------------�ݷ�����visitdelay-------------------------------------
	 */
	
	/**
	 * ��ţ�visitdealy:1
	 * ���ܣ�����һ���ݷ�����
	 * ������cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitDelay(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveVisitDealy(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitDelay cEntityVisitDelay);
	

	/**
	 * ��ţ�visitdealy:2
	 * ���ܣ�������аݷ�����
	 * ��������
	 * ����ֵ:CEntityVisitDealyArray
	 */
	public CEntityVisitDealyArray queryAllVisitDelay();
	
	/**
	 * ��ţ�visitdealy:3
	 * ���ܣ���Ա���Ż�����аݷ�����
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityVisitDealyArray
	 */
	public LinkedMap queryAllVisitDelayByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�visitdealy:4
	 * ���ܣ���ȡ����δɾԱ���İݷ�������Ϣ
	 * ������
	 * ����ֵ:LinkedMap(CEntityVisitDelay,CEntityVisitPlan,CEntityEmployee,CEntityClient)
	 */
	public LinkedMap queryAllVisitDelayInfo();


	/*
	 * -----------------------------����bussiness-------------------------------------
	 */
	
	/**
	 * ��ţ�bussiness:1
	 * ���ܣ�����һ������
	 * ������cEntityEmployee(EmployeeId),cEntityBussiness(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveBussiness(CEntityEmployee cEntityEmployee,CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�bussiness:2
	 * ���ܣ���Ա���Ż�����еĳ���
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityBussinessArray
	 */
	public CEntityBussinessArray queryAllBussinessByEmployeeId(CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�bussiness:3
	 * ���ܣ�����Ǽ��޸ĵǼ�ʱ�䲢������״̬�޸�Ϊִ����
	 * ������cEntityBussiness(BussinessId,BussinessRegisterTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessRegisterTime(CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�bussiness:4
	 * ���ܣ��ִ�Ŀ�ĵصǼ��޸ĵִ��ַ��ʱ��
	 * ������cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessInTimeAndInAddress(CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�bussiness:5
	 * ���ܣ��뿪Ŀ�ĵصǼ��޸��뿪��ַ��ʱ��
	 * ������cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessOutTimeAndOutAddress(CEntityBussiness cEntityBussiness);
	/**
	 * ��ţ�bussiness:6
	 * ���ܣ���������Ǽ�ʱ�䲢�޸ĳ���״̬Ϊδ���
	 * ������cEntityBussiness(BussinessId,BussinessReturnTime)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessReturn(CEntityBussiness cEntityBussiness);

	/**
	 * ��ţ�bussiness:7
	 * ���ܣ���������޸ĳ���״̬
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessStateByBussinessId(CEntityBussiness cEntityBussiness,int OperateState);
	
	/**
	 * ��ţ�bussiness:8
	 * ���ܣ���Ա���Ż�ȡ����̬�ĳ���(δ�Ǽ�,ִ����)
	 * ������cEntityEmployee(EmployeeId) 
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessRunningStateByEmployeeId(CEntityEmployee cEntityEmployee);
	/**
	 * ��ţ�bussiness:9
	 * ���ܣ�������Ż�ó�������(�����ֶ�)
	 * ������CEntityBussiness(BussinessId) 
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessByBussinessId(CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�bussiness:10
	 * ���ܣ���ȡ����δɾԱ����ִ���г�����Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessRuning();
	
	/**
	 * ��ţ�bussiness:11
	 * ���ܣ���ȡ���������Ա����Ϣ����Ա��û��δ�Ǽǣ�ִ���У�δ��˵ĳ��
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryBussinessAddOkEmployeeInfo();
	
	/**
	 * ��ţ�bussiness:12
	 * ���ܣ���ȡ����δɾԱ���Ĵ����������Ϣ(δ���)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessWaitDeal();
	
	/**
	 * ��ţ�bussiness:13
	 * ���ܣ�.��ȡ����δɾԱ�����ѽ���������Ϣ�����ͨ������˲�ͨ����
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessComplete();
	
	/**
	 * ��ţ�bussiness:14
	 * ���ܣ���ȡ����δɾԱ����δ��˵ĳ�������
	 * ������
	 * ����ֵ:int
	 */
	public int queryBussinessWaitDealNumber();
	
	/*
	 * -----------------------------����bussinessactivity------------------------------------
	 */
	/**
	 * ��ţ�bussinessactivity:1
	 * ���ܣ�����һ������
	 * ������cEntityBussinessActivity(�����ֶ�),cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean saveBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness);

	
	/**
	 * ��ţ�bussinessactivity:2
	 * ���ܣ�������Ż�����еĳ���
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:CEntityBussinessActivityArray
	 */
	public CEntityBussinessActivityArray queryAllBussinessActivityByBussinessId(CEntityBussiness cEntityBussiness);
	
	

	/**
	 * ��ţ�bussinessactivity:3
	 * ���ܣ���������Id�����ͽ����޸İ�״̬
	 * ������cEntityBussinessActivity(BussinessActivityType,BussinessObjectId,BussinessBindType)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessBandTypeByObjectIdAndActivityType(CEntityBussinessActivity cEntityBussinessActivity);
	
	
	/**
	 * ��ţ�bussinessactivity:4
	 * ���ܣ������������
	 * ������cEntityBussinessActivityArray
	 * ����ֵ:��
	 */
	public void saveBatchBussinessActiviy(final CEntityBussinessActivityArray cEntityBussinessActivityArray);
	
	/**
	 * ��ţ�bussinessactivity:5
	 * ���ܣ�������Ż�ð󶨳����ĸ���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:int
	 */
	public int queryBussinessActivityBindNumberByBussinessId(CEntityBussiness cEntityBussiness);
	
	/**
	 * ��ţ�bussinessactivity:6
	 * ���ܣ�������Ž�����г���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessActivityBindTypeUnbindByBussinessId(CEntityBussiness cEntityBussiness);
	/*
	 * -----------------------------�������¼bussinessbadrecord------------------------------------
	 */
	
	/**
	 * ��ţ�bussinessbadrecord:1
	 * ���ܣ�����һ���������¼
	 * ������cEntityBussiness(BussinessId),cEntityBussinessBadrecord(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveBussinessBadrecord(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord);
	
	
	/**
	 * ��ţ�bussinessbadrecord:2
	 * ���ܣ�������еĳ������¼
	 * ��������
	 * ����ֵ:CEntityBussinessBadrecordArray
	 */
	public CEntityBussinessBadrecordArray queryAllBussinessBadrecord();
	
	/**
	 * ��ţ�bussinessbadrecord:3
	 * ���ܣ���Ա���Ż�ȡ�����г������¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessBadrecordByEmployeeId(CEntityEmployee cEntityEmployee);
	

	/**
	 * ��ţ�bussinessbadrecord:4
	 * ���ܣ���ȡ����δɾԱ���ĳ������¼��Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessBadrecordInfo();
	
	/*
	 * --------------------------�����bussinessundo-------------------------------------------
	 */

	/**
	 * ��ţ�bussinessundo:1
	 * ���ܣ�����һ�������
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean savaBussinessUndo(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo);
	
	/**
	 * ��ţ�bussinessundo:2
	 * ���ܣ���ѯ����δɾԱ���������Ϣ
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllBussinessUndoInfo();
	
	/*
	 * ----------------------------����attendance------------------------------------
	 */
	
	
	/**
	 * ��ţ�attendance:1
	 * ���ܣ��������뿼�ڱ�
	 * ������cEntityAttendanceArray
	 * ����ֵ:��
	 */
	public void saveAttendances(CEntityAttendanceArray cEntityAttendanceArray);
	
	
	/**
	 * ��ţ�attendance:2
	 * ���ܣ���Ա���ż��������ڽ���ǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceRegisterTime,AttendanceDate)
	 * ����ֵ:boolean
	 */
	public boolean updateAttendanceRegisterTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee);

	/**
	 * ��ţ�attendance:3
	 * ���ܣ���Ա���ż��������ڽ���ǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceSignoutTime,AttendanceDate)
	 * ����ֵ:boolean
	 */
	public boolean updateAttendanceSignoutTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee);
	
	/**
	 * ��ţ�attendance:4
	 * ���ܣ������ڻ�����еĿ���
	 * ������cEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAllAttendanceByAttendanceDate(CEntityAttendance cEntityAttendance);

	/**
	 * ��ţ�attendance:5
	 * ���ܣ���Ա���ź͵������ڲ�ѯ����
	 * ������cEntityAttendance(AttendanceDate),CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityAttendance
	 */
	public CEntityAttendance queryAttendanceByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee);

	/**
	 * ��ţ�attendance:6
	 * ���ܣ������ڻ�����еĿ������ݣ���Ա���ţ�Ա���˺ţ�Ա����
	 * ������cEntityAttendance(AttendanceDate)
	 * ����ֵ:HashedMap
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByAttendaceData(CEntityAttendance cEntityAttendance);
	
	/**
	 * ��ţ�attendance:7
	 * ���ܣ���Ա���Ų�ѯ���п��ڼ�¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAllEmployeeAttendance(CEntityEmployee cEntityEmployee);

	/**
	 * ��ţ�attendance:8
	 * ���ܣ���Ա���ź����ڲ�ѯ���ڼ�¼
	 * ������CEntityEmployee(EmployeeId),CEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAtteandenByEmployeeIdandAttendanceOldDate(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance);
	
	/**
	 * ��ţ�attendance:9
	 * ���ܣ���Ա���������ڲ�ѯ���ڼ�¼
	 * ������CEntityEmployee(EmployeeName),CEntityAttendance(AttendanceDate)
	 * ����ֵ:HashedMap
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByEmployeeName(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance);
	
	/**
	 * ��ţ�attendance:10
	 * ���ܣ�����ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYear(String year);
	
	/**
	 * ��ţ�attendance:11
	 * ���ܣ�����ݣ��·ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)��month(mm)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearAndMonth(String year,String month);
	/**
	 * ��ţ�attendance:12
	 * ���ܣ���Ա��������ݣ��·ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)��month(mm)��cEntityEmployee(EmployeeName)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearMonthAndEmployeeName(CEntityEmployee cEntityEmployee,String year,String month);

	/**
	 * ��ţ�attendance:13
	 * ���ܣ�ȡ�����һ����¼������
	 * ������
	 * ����ֵ:String(yyyy/mm/dd)
	 */
	public String queryLastAttendance();
	/*
	 * ----------------------------֪ͨ notice------------------------------------
	 */
	
	/**
	 * ��ţ�notice:1
	 * ���ܣ�����һ��֪ͨ
	 * ������cEntityNotice(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveNotice(CEntityNotice cEntityNotice);
	
	
	/**
	 * ��ţ�notice:2
	 * ���ܣ����ȫ��֪ͨ
	 * ��������
	 * ����ֵ:CEntityNoticeArray
	 */
	public CEntityNoticeArray queryAllNotice();

	/*
	 * ----------------------------���� suggest----------------------------------
	 */
	
	/**
	 * ��ţ�suggest:1
	 * ���ܣ�����һ������
	 * ������cEntityEmployee(EmployeeId),cEntitySuggest(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest);
	
	/**
	 * ��ţ�suggest:2
	 * ���ܣ�������еĽ���
	 * ��������
	 * ����ֵ:CEntitySuggestArray
	 */
	public CEntitySuggestArray queryAllSuggest();
	
	/**
	 * ��ţ�suggest:3
	 * ���ܣ���ȡ���еĽ�����Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAllSuggestInfo();

	
	/*
	 * ----------------------------��ַ address----------------------------------
	 */
	
	/**
	 * ��ţ�address:1
	 * ����:���ӵ�ַ
	 * ������cEntityAddress(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean saveAddress(CEntityAddress cEntityAddress);
	
	/**
	 * ��ţ�address:2
	 * ����: ��Ա���ż���ѯ������ȡ��ַ
	 * ������cEntityEmployee(EmployeeId),days
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap queryAddressEmployeeInfoWithTime(CEntityEmployee cEntityEmployee,int days);
	
	
	
	
}

