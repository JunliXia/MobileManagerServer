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
	 *------------------------------ 管理员 administrator----------------------------
	 */
	private CDaoAdministrator cDaoAdministrator;

	@Resource
	public void setcDaoAdministrator(CDaoAdministrator cDaoAdministrator) {
		this.cDaoAdministrator = cDaoAdministrator;
	}

	/**
	 * 序号：administrator:1
	 * 功能：增加一个管理员帐号
	 * 参数：cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * 返回值:boolean
	 */
	public boolean saveAdministrator(CEntityAdministrator cEntityAdministrator) {
		// TODO Auto-generated method stub
		boolean bisSave=cDaoAdministrator.saveAdministrator(cEntityAdministrator);
		return bisSave;
	}

	/**
	 * 序号：administrator:2
	 * 功能：按帐号密码查询管理员
	 * 参数：cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * 返回值:CEntityAdministrator(*)
	 */
	public CEntityAdministrator queryAdministrator(CEntityAdministrator cEntityAdministrator) {
		// TODO Auto-generated method stub
		CEntityAdministrator result=cDaoAdministrator.queryAdministrator(cEntityAdministrator);
		return result;
	}

	
	/*
	 * -------------------------------员工employee----------------------------------
	 */	
	
	private CDaoEmployee cDaoEmployee;
	
	@Resource
	public void setcDaoEmployee(CDaoEmployee cDaoEmployee) {
		this.cDaoEmployee = cDaoEmployee;
	}

	/**
	 * 序号：employee:1
	 * 功能：增加一个员工
	 * 参数：cEntityEmployee(EmployeeAccount,EmployeePassword,EmployeeName,EmployeePhone
	 * 					   EmployeeSex,EmployeeDepartment,EmployeeJob,EmployeeType)
	 * 返回值:boolean
	 */
	public boolean saveEmployee(CEntityEmployee cEntityEmployee) {
		// TODO Auto-generated method stub
		boolean bisSave=cDaoEmployee.saveEmployee(cEntityEmployee);
		return bisSave;
	}
	
	
	/**
	 * 序号：employee:2
	 * 功能：获取所有未删员工
	 * 参数：无
	 * 返回值:CEntityEmployeeArray（本表字段，不涉及关联表）
	 */
	public CEntityEmployeeArray queryAllEmployee() {
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployee();
		return cEntityEmployeeArray;
	}

	/**
	 * 序号：employee:3
	 * 功能：修改员工类别
	 * 参数：cEntityEmployee(EmployeeId,EmployeeType)
	 * 返回值:boolean
	 */
	public boolean updateEmployeeType(CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=cDaoEmployee.updateEmployeeType(cEntityEmployee);
		return bisUpdate;
	}
	/**
	 * 序号：employee:4
	 * 功能：按帐号密码查找员工
	 * 参数：cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * 返回值:CEntityEmployee(本表字段)
	 */
	public CEntityEmployee queryEmployeeByAccountAndPassword(CEntityEmployee cEntityEmployee) {
		CEntityEmployee result=cDaoEmployee.queryEmployeeByAccountAndPassword(cEntityEmployee);
		return result;
	}
	
	/**
	 * 序号：employee:5
	 * 功能：按帐号查找员工
	 * 参数：cEntityEmployee(EmployeeAccount)
	 * 返回值:CEntityEmployee（本表字段）
	 */
	public CEntityEmployee queryEmployeeByAccount(CEntityEmployee cEntityEmployee) {
		CEntityEmployee result=cDaoEmployee.queryEmployeeByAccount(cEntityEmployee);
		
		return result;
	}
	
	/**
	 * 序号：employee:6
	 * 功能：按类别获得所有的员工
	 * 参数：cEntityEmployee(EmployeeType)
	 * 返回值:CEntityEmployee（本表字段）
	 */
	public CEntityEmployeeArray queryAllEmployeeByType(CEntityEmployee cEntityEmployee) {
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployeeByType(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	/**
	 * 序号：employee:7 
	 * 功能：按员工号修改员工
	 * 参数：cEntityEmployee(本表字段)
	 * 返回值:boolean
	 */
	public boolean updateEmployeeByEmployeeId(CEntityEmployee cEntityEmployee){
		boolean bisUpdate=cDaoEmployee.updateEmployeeByEmployeeId(cEntityEmployee);
		return bisUpdate;
	}
	/**
	 * 序号：employee:8 
	 * 功能：获取所有未删员工名
	 * 参数：
	 * 返回值:List<String>
	 */
	public List<String> queryAllEmployeeName(){
		List<String> allname=cDaoEmployee.queryAllEmployeeName();
		return allname;
	}
	
	/**
	 * 序号：employee:9 
	 * 功能：按员工名获得所有未删员工
	 * 参数：CEntityEmployee(EmployeeName)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray queryAllEmployeeByEmployeeName(CEntityEmployee cEntityEmployee) {
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployeeByEmployeeName(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	/**
	 * 序号：employee:10 
	 * 功能：按员工部门获得所有未删员工
	 * 参数：CEntityEmployee(EmployeeDepartment)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray queryAllEmployeeByEmployeeDepartment(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=cDaoEmployee.queryAllEmployeeByEmployeeDepartment(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	/*
	 * -------------------------------任务mission----------------------------------
	 */
	private CDaoMission cDaoMission;
	
	@Resource
	public void setcDaoMission(CDaoMission cDaoMission) {
		this.cDaoMission = cDaoMission;
	}

	/**
	 * 序号：mission:1
	 * 功能：增加任务
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission) {
		boolean bisSave=cDaoMission.saveMission(cEntityEmployee, cEntityMission);
		
		return bisSave;
	}
	
	/**
	 * 序号：mission:2
	 * 功能：修改任务状态
	 * 参数：cEntityMission(MissionId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * 返回值:boolean
	 */
	public boolean updateMissionState(CEntityMission cEntityMission,int OperateState) {
		boolean bisUpdate=cDaoMission.updateMissionState(cEntityMission,OperateState);
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:3
	 * 功能：修改延期状态
	 * 参数：EntityMission(MissionId,MissionDelayState)
	 * 返回值:boolean
	 */
	public boolean updateMissionDelayState(CEntityMission cEntityMission) {
		boolean bisUpdate=cDaoMission.updateMissionDelayState(cEntityMission);
		
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:4
	 * 功能：根据员工号获得所有任务
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	/**
	 * 序号：mission:5
	 * 功能：按任务状态获得所有任务
	 * 参数：OperateState(MyConstant.Mission.*)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByMissionType(int OperateState) {
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionByMissionType(OperateState);
		return cEntityMissionArray;
	}
	/**
	 * 序号：mission:6
	 * 功能：按任务号获得任务详情
	 * 参数：cEntityMission(MissionId)
	 * 返回值:CEntityMission
	 */
	public CEntityMission queryMissionByMissionId(int MissionId){
		CEntityMission findResult=cDaoMission.queryMissionByMissionId(MissionId);
		return findResult;
		
	}
	
	/**
	 * 序号：mission:7
	 * 功能：修改任务期限（修改期限就判定为已延期）
	 * 参数：cEntityMission(MissionId,MissionDeadline)
	 * 返回值:boolean
	 */
	public boolean updateMissionDeadline(CEntityMission cEntityMission) {
		boolean bisUpdate=cDaoMission.updateMissionDeadline(cEntityMission);
		return bisUpdate;
	}

	
	/**
	 * 序号：mission:8
	 * 功能：按员工号及状态获得所有任务
	 * 参数：cEntityEmployee(EmployeeId)
	 * 	   OperateState(MyConstant.Mission.*)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionByEmployeeIdAndMissionState(CEntityEmployee cEntityEmployee,int OperateState){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionByEmployeeIdAndMissionState(cEntityEmployee, OperateState);
		return cEntityMissionArray;
	}
	/**
	 * 序号：mission:9
	 * 功能：按员工号获取终止态的任务（未审核,已审核,已撤销,已失败）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionCompleteStateByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionCompleteStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：mission:10
	 * 功能按员工号获取执行态的任务（执行中，已过期）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMissionRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMissionRunningStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：mission:11
	 * 功能通过当前日期修改任务状态(未接受,执行中)为已过期	
	 * 参数：CEntityMission(MissionDeadline)
	 * 返回值:boolean
	 */
	public boolean updateMissionStateForOutTimeState(CEntityMission cEntityMission) {
		boolean bisUpdate=cDaoMission.updateMissionStateForOutTimeState(cEntityMission);
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:12
	 * 功能:按出差号将任务出差绑定状态解绑	
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateMissionBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoMission.updateMissionBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	/**
	 * 序号：mission:13
	 * 功能:按出差号将任务撤销并创建任务撤销记录
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateMissionStateUndoWithSaveMissionUndoByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoMission.updateMissionStateUndoWithSaveMissionUndoByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * 序号：mission:14
	 * 功能:按出差号得到其绑定的任务
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray queryAllMisssionByBussinessId(CEntityBussiness cEntityBussiness){
		CEntityMissionArray cEntityMissionArray=cDaoMission.queryAllMisssionByBussinessId(cEntityBussiness);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：mission:15
	 * 功能:获取所有员工的未接受，执行中任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllMissionRuning(){
		LinkedMap findResult=cDaoMission.queryAllMissionRuning();
		return findResult;
	}
	/**
	 * 序号：mission:16
	 * 功能:统计员工号，员工账号，员工名，任务需执行数量（未接受，执行中）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryMissionRunningNumberOfEmployeeInfo(){
		List findReuslt=cDaoMission.queryMissionRunningNumberOfEmployeeInfo();
		return findReuslt;
	}
	
	/**
	 * 序号：mission:17
	 * 功能:获取所有员工的未审核，已过期任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllMissionWaitDeal(){
		LinkedMap findResult=cDaoMission.queryAllMissionWaitDeal();
		return findResult;
	}
	
	/**
	 * 序号：mission:18
	 * 功能:获取所有员工的已失败，已审核任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllMissionComplete() {
		LinkedMap findResult=cDaoMission.queryAllMissionComplete();
		return findResult;
	}
	
	/**
	 * 序号：mission:19
	 * 功能:获取所有员工的未审核，已过期任务数量
	 * 参数：
	 * 返回值:int
	 */
	public int queryMissionWaitDealNumber() {
		int number=cDaoMission.queryMissionWaitDealNumber();
		return number;
	}
	
	/**
	 * 序号：mission:20
	 * 功能:根据员工号获取其绑定的任务信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray queryMissionBindByEmployee(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findReuslt=cDaoMission.queryMissionBindByEmployee(cEntityEmployee);
		return findReuslt;
	}
	
	/**
	 * 序号：mission:21
	 * 功能:根据任务号修改出差绑定状态
	 * 参数：CEntityMission(MissionId,MissionBussinessBandState)
	 * 返回值:boolean
	 */
	public boolean updateMissionBindType(CEntityMission cEntityMission){
		boolean bisupdate=cDaoMission.updateMissionBindType(cEntityMission);
		return bisupdate;
	}
	/*
	 * -------------------------------任务延期 missiondelay----------------------------------
	 */	
	
	private CDaoMissionDelay cDaoMissionDelay;
	
	
	@Resource
	public void setcDaoMissionDelay(CDaoMissionDelay cDaoMissionDelay) {
		this.cDaoMissionDelay = cDaoMissionDelay;
	}

	/**
	 * 序号：missiondealy:1
	 * 功能：增加一条任务延期
	 * 参数：cEntityMission(MissionId),cEntityMissionDelay(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMissionDealy(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay) {
		boolean bisSave=cDaoMissionDelay.saveMissionDealy(cEntityMission, cEntityMissionDelay);
		return bisSave;
	}
	/**
	 * 序号：missiondealy:2
	 * 功能：按任务号获得所有任务延期
	 * 参数：cEntityMission(MissionId)
	 * 返回值:CEntityMissionDelayArray
	 */
	
	public CEntityMissionDelayArray queryAllMissionDelayByMissionId(CEntityMission cEntityMission) {
		CEntityMissionDelayArray cEntityMissionDelayArray=cDaoMissionDelay.queryAllMissionDelayByMissionId(cEntityMission);
		
		return cEntityMissionDelayArray;
	}
	/**
	 * 序号：missiondealy:3
	 * 功能：按员工号获得所有的任务延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllMissionDelayByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoMissionDelay.queryAllMissionDelayByEmployeeId(cEntityEmployee);
		
		return findResult;
	}
	
	/**
	 * 序号：missiondealy:4
	 * 功能：获得所有的延期记录及其任务信息已员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllMissionDelayWithMissionInfoAndEmployeeInfo() {
		LinkedMap findResult=cDaoMissionDelay.queryAllMissionDelayWithMissionInfoAndEmployeeInfo();
		return findResult;
	}
	
	/*
	 * -------------------------------任务总结 missionconclusion----------------------------------
	 */	
	
	private CDaoMissionConclusion cDaoMissionConclusion;
	
	@Resource
	public void setcDaoMissionConclusion(CDaoMissionConclusion cDaoMissionConclusion) {
		this.cDaoMissionConclusion = cDaoMissionConclusion;
	}

	/**
	 * 序号：missionconclusion:1
	 * 功能：增加一条任务总结
	 * 参数：cEntityMission(MissionId),cEntityMissionConclusion(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMissionConclusion(CEntityMission cEntityMission, CEntityMissionConclusion cEntityMissionConclusion) {
		boolean bisSave=cDaoMissionConclusion.saveMissionConclusion(cEntityMission, cEntityMissionConclusion);
		return bisSave;
	}
	/**
	 * 序号：missionconclusion:2
	 * 功能：按任务号获得任务总结
	 * 参数：cEntityMission(MissionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissonConclusionByMissionId(CEntityMission cEntityMission) {
		CEntityMissionConclusion cEntityMissionConclusion=cDaoMissionConclusion.queryMissonConclusionByMissionId(cEntityMission);
		return cEntityMissionConclusion;
	}
	
	/**
	 * 序号：missionconclusion:3
	 * 功能：修改任务总结审核结果
	 * 参数：cEntityMissionConclusion(MissionConclusionId)
	 * 	   OperateState(MyConstant.MissionConclusion.*)
	 * 返回值:boolean
	 */
	public boolean updateMissionCheck(CEntityMissionConclusion cEntityMissionConclusion,int OperateState) {
		boolean bisUpdate=cDaoMissionConclusion.updateMissionCheck(cEntityMissionConclusion, OperateState);
		
		return bisUpdate;
	}
	
	
	/**
	 * 序号：missionconclusion:4
	 * 功能：按员工号获取其所有任务总结
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionConclusionArray
	 */
	public LinkedMap queryAllMissionConclusionByEmployeeId(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cDaoMissionConclusion.queryAllMissionConclusionByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：missionconclusion:5
	 * 功能：按任务总结号获取其任务总结
	 * 参数：CEntityMissionConclusion(MissionConclusionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion queryMissionConclusionByMissionConclusionId(CEntityMissionConclusion cEntityMissionConclusion){
		CEntityMissionConclusion findResult=cDaoMissionConclusion.queryMissionConclusionByMissionConclusionId(cEntityMissionConclusion);
		return findResult;
	}
	
	/**
	 * 序号：missionconclusion:6
	 * 功能：获取所有任务总结及其任务员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllMissionConclusionWithEmployeeInfo(){
		LinkedMap findResult=cDaoMissionConclusion.queryAllMissionConclusionWithEmployeeInfo();
		return findResult;
	}
	/*
	 * -------------------------------任务撤销missionundo----------------------------------
	 */	
	private CDaoMissionUndo cDaoMissionUndo;
	
	
	@Resource
	public void setcDaoMissionUndo(CDaoMissionUndo cDaoMissionUndo) {
		this.cDaoMissionUndo = cDaoMissionUndo;
	}

	/**
	 * 序号：missionundo:1
	 * 功能：增加一条任务撤销
	 * 参数：cEntityMission(MissionId),cEntityMissionUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveMissionUndo(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		boolean bisSave=cDaoMissionUndo.saveMissionUndo(cEntityMission, cEntityMissionUndo);
		
		return bisSave;
	}
	
	/**
	 * 序号：missionundo:2
	 * 功能：获得所有的任务撤销
	 * 参数：无
	 * 返回值:CEntityMissionUndoArray
	 */
	public CEntityMissionUndoArray queryAllMissionUndo() {
		CEntityMissionUndoArray cEntityMissionUndoArray=cDaoMissionUndo.queryAllMissionUndo();
		return cEntityMissionUndoArray;
	}
	
	/**
	 * 序号：missionundo:3
	 * 功能：修改撤销类型
	 * 参数：cEntityMissionUndo(MissionUndoId)
	 * 	   OperateState(Myconstant.MissionUndo.*)
	 * 返回值:boolean
	 */
	public boolean updateMissionUndoType(CEntityMissionUndo cEntityMissionUndo,int OperateState) {
		boolean bisUpdate=cDaoMissionUndo.updateMissionUndoType(cEntityMissionUndo,OperateState);
		return bisUpdate;
	}
	
	/**
	 * 序号：missionundo:4
	 * 功能：按撤销号获得任务
	 * 参数：cEntityMissionUndo(MissionUndoId)
	 * 返回值:CEntityMission
	 */
	public CEntityMission queryMissionByMissionUndoId(CEntityMissionUndo cEntityMissionUndo) {
		CEntityMission cEntityMission=cDaoMissionUndo.queryMissionByMissionUndoId(cEntityMissionUndo);
		
		return cEntityMission;
	}
	/**
	 * 序号：missionundo:5
	 * 功能：按撤销号获得撤销
	 * 参数：cEntityMissionUndo(MissionUndoId)
	 * 返回值:CEntityMissionUndo
	 */
	public CEntityMissionUndo queryMissionUndoByMissionUndoId(CEntityMissionUndo cEntityMissionUndo){
		CEntityMissionUndo findResult=cDaoMissionUndo.queryMissionUndoByMissionUndoId(cEntityMissionUndo);
		return findResult;
		
	}
	
	/**
	 * 序号：missionundo:6
	 * 功能：获得所有的任务撤销及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllMissionUndoWithEmployeeInfo() {
		LinkedMap findResult=cDaoMissionUndo.queryAllMissionUndoWithEmployeeInfo();
		return findResult;
	}
	/*
	 * -------------------------------客户client----------------------------------
	 */	
	private CDaoClient cDaoClient;
	
	@Resource
	public void setcDaoClient(CDaoClient cDaoClient) {
		this.cDaoClient = cDaoClient;
	}

	/**
	 * 序号：client:1
	 * 功能：增加客户
	 * 参数：cEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public  boolean saveClient(CEntityClient cEntityClient) {
		boolean bisSave=cDaoClient.saveClient(cEntityClient);
		return bisSave;
	}
	
	/**
	 * 序号：client:2
	 * 功能：按员工号获得所有的客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClientByEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	/**
	 * 序号：client:3
	 * 功能：修改客户员工号(并修改客户状态为已分配)
	 * 参数：cEntityEmployee(EmployeeId),cEntityClient(ClientId)
	 * 返回值:CEntityClientArray
	 */
	public boolean updateClientEmployeeIdAndClientState(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=cDaoClient.updateClientEmployeeId(cEntityClient, cEntityEmployee);
		return bisUpdate;
	}
	
	/**
	 * 序号：client:4
	 * 功能：按客户状态获得所有的客户
	 * 参数：cEntityClient(ClientState)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByClientState(CEntityClient cEntityClient) {
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClientByClientState(cEntityClient);
		return cEntityClientArray;
	}

	/**
	 * 序号：client:5
	 * 功能：按客户号修改客户状态
	 * 参数：cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.Client.*)
	 * 返回值:CEntityClientArray
	 */
	public boolean updateClientStateByClientId(CEntityClient cEntityClient,int OperateState) {
		boolean bisUpdate=cDaoClient.updateClientStateByClientId(cEntityClient, OperateState);
		return bisUpdate;
	}
	

	/**
	 * 序号：client:6
	 * 功能：按客户号获得客户详情
	 * 参数：cEntityClient(ClientId)
	 * 返回值:CEntityClient
	 */
	public CEntityClient queryClientByClientId(CEntityClient cEntityClient){
		CEntityClient findResult=cDaoClient.queryClientByClientId(cEntityClient);
		return findResult;
	}
	
	/**
	 * 序号：client:7
	 * 功能：按员工号获取其在客户提交中的所有客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray queryClientForSubmitEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=cDaoClient.queryClientForSubmitEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	
	
	/**
	 * 序号：client:8
	 * 功能：查询所有类型的客户
	 * 参数：无
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray queryAllClient(){
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClient();
		return cEntityClientArray;
	}
	
	/**
	 * 序号：client:9
	 * 功能：修改客户详情
	 * 参数：CEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public boolean updateClient(CEntityClient cEntityClient){
		boolean bisUpdate=cDaoClient.updateClient(cEntityClient);
		return bisUpdate;
	}
	/**
	 * 序号：client:10
	 * 功能：按员工号获取其已分配客户名
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:List<String>
	 */
	public List<String> queryAllClientNameByEmployeeId(CEntityEmployee cEntityEmployee){
		List<String> reslt=cDaoClient.queryAllClientNameByEmployeeId(cEntityEmployee);
		return reslt;
	}
	/**
	 * 序号：client:11
	 * 功能：按客户名获取客户详情
	 * 参数：CEntityClient(ClientName)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray queryAllClientByClientName(CEntityClient cEntityClient) {
		CEntityClientArray cEntityClientArray=cDaoClient.queryAllClientByClientName(cEntityClient);
		return cEntityClientArray;
	}
	
	
	/**
	 * 序号：client:12
	 * 功能：按客户号将员工号设空，并修改客户状态为未分配
	 * 参数：CEntityClient(ClientId)
	 * 返回值:boolean
	 */
	public boolean updateClientEmployeeIdNUll(CEntityClient cEntityClient){
		boolean bisUpdate=cDaoClient.updateClientEmployeeIdNUll(cEntityClient);
		return bisUpdate;
	}
	
	/**
	 * 序号：client:13
	 * 功能：统计员工号，员工账号，员工名，客户拥有数量
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryClientNumberOfEmployeeInfo() {
		List findResult=cDaoClient.queryClientNumberOfEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：client:14
	 * 功能：查询出已分配客户信息以及其员工信息
	 * 参数：
	 * 返回值:HashedMap(CEntityEmployee,CEntityClient)
	 */
	public HashedMap queryClientDistributionWithEmployeeInfo() {
		HashedMap findResult=cDaoClient.queryClientDistributionWithEmployeeInfo();
		return findResult;
	}
	

	/**
	 * 序号：client:15
	 * 功能：查询未分配客户的客户号，客户名，客户公司
	 * 参数：
	 * 返回值:List(ClientId,ClientName,ClientCompany)
	 */
	@SuppressWarnings("unchecked")
	public List queryClientUnDistributionInfo(){
		List findResult=cDaoClient.queryClientUnDistributionInfo();
		return findResult;
	}
	
	/*
	 * -------------------------------客户提交clientsubmit----------------------------------
	 */	
	private CDaoClientSubmit cDaoClientSubmit;
	
	@Resource
	public void setcDaoClientSubmit(CDaoClientSubmit cDaoClientSubmit) {
		this.cDaoClientSubmit = cDaoClientSubmit;
	}
	
	/**
	 * 序号：clientsubmit:1
	 * 功能：增加一条客户提交（同时增加了一条客户）
	 * 参数：cEntityClient(本表字段),cEntityEmployee(EmployeeId),cEntityClientSubmit(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveClientSubmit(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityClientSubmit cEntityClientSubmit) {
		boolean bisSave=cDaoClientSubmit.saveClientSubmit(cEntityClient, cEntityEmployee, cEntityClientSubmit);
		return bisSave;
	}
	
	/**
	 * 序号：clientsubmit:2
	 * 功能：按提交状态获得所有的客户提交
	 * 参数：cEntityClientSubmit(ClientSubmitState)
	 * 返回值:CEntityClientSubmitArray
	 */
	public CEntityClientSubmitArray queryAllClientSubmitByClientSubmitState(CEntityClientSubmit cEntityClientSubmit) {
		CEntityClientSubmitArray cEntityClientSubmitArray=cDaoClientSubmit.queryAllClientSubmitByClientSubmitState(cEntityClientSubmit);
		return cEntityClientSubmitArray;
	}
	/**
	 * 序号：clientsubmit:3
	 * 功能：按客户号修改客户提交状态
	 * 参数：cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.ClientSubmit.*)
	 * 返回值:CEntityClientSubmitArray
	 */
	public boolean updateClientSubmitStateByClientId(CEntityClient cEntityClient,int OperateState) {
		boolean bisUpdate=cDaoClientSubmit.updateClientSubmitStateByClientId(cEntityClient, OperateState);
		return bisUpdate;
	}
	/**
	 * 序号：clientsubmit:4
	 * 功能：按员工号获得所有的客户提交记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientSubmitArray
	 */
	public CEntityClientSubmitArray queryAllClientSubmitForEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityClientSubmitArray cEntityClientSubmitArray=cDaoClientSubmit.queryAllClientSubmitForEmployeeId(cEntityEmployee);
		return cEntityClientSubmitArray;
	}
	
	
	/**
	 * 序号：clientsubmit:5
	 * 功能：获得所有的未审核提交记录及其客户信息员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllNocheckClientWithClientInfoAndEmployeeInfo(){
		LinkedMap findResult=cDaoClientSubmit.queryAllNocheckClientWithClientInfoAndEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientsubmit:6
	 * 功能：获得未审核客户提交数量
	 * 参数：
	 * 返回值:int
	 */
	public int queryClientSubmitNocheckNumber() {
		int findResutl=cDaoClientSubmit.queryClientSubmitNocheckNumber();
		return findResutl;
	}
	/*
	 * -------------------------------拜访计划visitplan----------------------------------
	 */	
	private CDaoVisitPlan cDaoVisitPlan;
	
	@Resource
	public void setcDaoVisitPlan(CDaoVisitPlan cDaoVisitPlan) {
		this.cDaoVisitPlan = cDaoVisitPlan;
	}

	/**
	 * 序号：visitplan:1
	 * 功能：增加拜访计划
	 * 参数：cEntityClient(ClientId),cEntityEmployee(EmployeeId),cEntityVisitPlan(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveVisitPlan(CEntityClient cEntityClient ,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan) {
		boolean bisSave=cDaoVisitPlan.saveVisitPlan(cEntityClient, cEntityEmployee, cEntityVisitPlan);
		return bisSave;
	}

	/**
	 * 序号：visitplan:2
	 * 功能：按员工号获得所有的拜访计划
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanByEmployeeId(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	/**
	 * 序号：visitplan:3
	 * 功能：按拜访计划号修改拜访状态
	 * 参数：cEntityVisitPlan(VisitPlanId)
	 * 	   OperateState(MyConstant.VisitPlan.*)
	 * 返回值:CEntityVisitPlanArray
	 */
	public boolean updateVisitPlanStateByVisitPlanId(CEntityVisitPlan cEntityVisitPlan,int OperateState){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, OperateState);
		return bisUpdate;
	}
	/**
	 * 序号：visitplan:4
	 * 功能：按拜访计划号获得拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId)
	 * 返回值:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanByVisitPlanId(int VisitPlanId){
		CEntityVisitPlan findResult=cDaoVisitPlan.queryVisitPlanByVisitPlanId(VisitPlanId);
		return findResult;
	}
	/**
	 * 序号：visitplan:5
	 * 功能：修改拜访计划的时间并将状态改为未开始
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanStartTime,VisitPlanEndTime,VisitPlanPubdate)
	 * 返回值:boolean
	 */
	public boolean updateVisitTime(CEntityVisitPlan cEntityVisitPlan) {
		boolean bisUpdate=cDaoVisitPlan.updateVisitTime(cEntityVisitPlan);
		return bisUpdate;
	}
	
	/**
	 * 序号：visitplan:6
	 * 功能：按拜访计划号修改拜访循环（拜访循环，循环类型，循环数，拜访限定天数）
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanCycle,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * 返回值:boolean
	 */
	public boolean updateVisitPlanCyclesByVisitPlanId(CEntityVisitPlan cEntityVisitPlan) {
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanCyclesByVisitPlanId(cEntityVisitPlan);
		return bisUpdate;
	}
	
	/**
	 * 序号：visitplan:7
	 * 功能：按拜访计划号修改拜访期限并将状态改为执行中
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanEndTime)
	 * 返回值:boolean
	 */
	public boolean updateVisitPlanEndTime(CEntityVisitPlan cEntityVisitPlan){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanEndTime(cEntityVisitPlan);
		return bisUpdate;
	}
	/**
	 * 序号：visitplan:8
	 * 功能：按客户号查询出运行态的拜访计划（运行态为未开始，执行中，未审核，已过期）
	 * 参数：cEntityClient(ClientId)
	 * 返回值:CEntityVisitPlan
	 */
	public CEntityVisitPlan queryVisitPlanRunningStateByClientId(CEntityClient cEntityClient) {
		CEntityVisitPlan findResult=cDaoVisitPlan.queryVisitPlanRunningStateByClientId(cEntityClient);
		return findResult;
	}
	/**
	 * 序号：visitplan:9 功能：按员工号以及拜访状态获得所有拜访计划(本表字段) 参数：cEntityEmployee(EmployeeId)
	 * OperateState(MyConstant.VisitPlan.*) 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeIdAndVisitPlanState(CEntityEmployee cEntityEmployee, int OperateState) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanByEmployeeIdAndVisitPlanState(cEntityEmployee, OperateState);
		return cEntityVisitPlanArray;
	}
	/**
	 * 序号：visitplan:10 
	 * 功能：按员工号获取运行态的拜访计划(执行中,已过期)  (本表字段)
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanRunningStateByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanRunningStateByEmployeeId(cEntityEmployee);
		
		return cEntityVisitPlanArray;
	}
	/**
	 * 序号：visitplan:11
	 * 功能：按员工号获取终止态的拜访计划（未审核,已审核,已撤销,已失败）（本表字段）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanCompleteByEmployeeIs(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanCompleteByEmployeeIs(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	/**
	 * 序号：visitplan:12
	 * 功能：按员工号获取其所有的拜访计划含客户信息
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:HashedMap
	 */
	public HashedMap queryAllVisitPlanAndClientInfoByEmployeeId(CEntityEmployee cEntityEmployee){
		HashedMap findResult=cDaoVisitPlan.queryAllVisitPlanAndClientInfoByEmployeeId(cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：visitplan:13
	 * 功能：按员工号获得其拜访计划（含客户与拜访总结）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanWithClientAndVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanWithClientAndVisitConclusionByEmployeeId(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	/**
	 * 序号：visitplan:14
	 * 功能：按出差号将拜访计划撤销并创建拜访撤销记录
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateVisitPlanUndoWithSaveVisitUndoByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanUndoWithSaveVisitUndoByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	/**
	 * 序号：visitplan:15
	 * 功能：按出差号将拜访计划出差绑定状态解绑
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateVisitPlanBussinessBandStateUnbandByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoVisitPlan.updateVisitPlanBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * 序号：visitplan:16
	 * 功能：按出差号得到其绑定的拜访计划
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryAllVisitPlanByBussienssId(CEntityBussiness cEntityBussiness) {
		CEntityVisitPlanArray cEntityVisitPlanArray=cDaoVisitPlan.queryAllVisitPlanByBussienssId(cEntityBussiness);
		return cEntityVisitPlanArray;
	}
	

	/**
	 * 序号：visitplan:17
	 * 功能：获取所有员工的未开始，已执行拜访信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanAllRunning() {
		LinkedMap findResult=cDaoVisitPlan.queryAllVisitPlanAllRunning();
		return findResult;
	}
	/**
	 * 序号：visitplan:18
	 * 功能：获取所有员工的未审核，已过期拜访信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanAllWaitDeal() {
		LinkedMap findResult=cDaoVisitPlan.queryAllVisitPlanAllWaitDeal();
		return findResult;
	}
	
	/**
	 * 序号：visitplan:19
	 * 功能：获取所有员工的已审核，已失败拜访信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllVisitPlanComplete(){
		LinkedMap findResult=cDaoVisitPlan.queryAllVisitPlanComplete();
		return findResult;
	}
	
	/**
	 * 序号：visitplan:20
	 * 功能：获取所有未删员工的未审核，已过期的拜访数量
	 * 参数：
	 * 返回值:int
	 */
	public int queryVisitPlanWaitDealNumber(){
		int findResult=cDaoVisitPlan.queryVisitPlanWaitDealNumber();
		return findResult;
	}
	
	/**
	 * 序号：visitplan:21
	 * 功能：根据拜访计划号修改出差绑定状态
	 * 参数：CEntityVisitPlan(VisitPlanId,VisitPlanBussinessBindType)
	 * 返回值:boolean
	 */
	public boolean updateVisitBindType(CEntityVisitPlan cEntityVisitPlan){
		boolean bisUpdate=cDaoVisitPlan.updateVisitBindType(cEntityVisitPlan);
		return bisUpdate;
	}
	
	
	/**
	 * 序号：visitplan:22
	 * 功能：根据员工号获取其绑定的拜访计划信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray queryVisitPlanBindByEmployee(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray findResult=cDaoVisitPlan.queryVisitPlanBindByEmployee(cEntityEmployee);
		return findResult;
	}
	/*
	 * ------------------------------拜访总结 visitconclusion-------------------------------------
	 */	
	private CDaoVisitConclusion cDaoVisitConclusion;
	
	@Resource
	public void setcDaoVisitConclusion(CDaoVisitConclusion cDaoVisitConclusion) {
		this.cDaoVisitConclusion = cDaoVisitConclusion;
	}

	/**
	 * 序号：visitconclusion:1
	 * 功能：增加拜访总结
	 * 参数：cEntityVisitPlan(VisitPlanId),cEntityEmployee(EmployeeId),cEntityVisitConclusion(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveVisitConclusion(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion) {
		boolean bisSave=cDaoVisitConclusion.saveVisitConclusion(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		return bisSave;
	}
	
	/**
	 * 序号：visitconclusion:2
	 * 功能：按员工号获得所有的拜访总结
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitConclusionArray
	 */
	public CEntityVisitConclusionArray queryAllVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitConclusionArray cEntityVisitConclusionArray=cDaoVisitConclusion.queryAllVisitConclusionByEmployeeId(cEntityEmployee);
		return cEntityVisitConclusionArray;
	}
	
	/**
	 * 序号：visitconclusion:3
	 * 功能：修改拜访总结的审核结果
	 * 参数：cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * 返回值:boolean
	 */
	public boolean updateMissionConclusionVisitCheck(CEntityVisitConclusion cEntityVisitConclusion,int OperateState) {
		boolean bisUpdate=cDaoVisitConclusion.updateMissionConclusionVisitCheck(cEntityVisitConclusion, OperateState);
		
		return bisUpdate;
	}
	/**
	 * 序号：visitconclusion:4
	 * 功能：按拜访计划号获得其未审核的拜访总结
	 * 参数：CEntityVisitPlan(VisitPlanId)
	 * 返回值:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionNocheckByVisitPlanId(CEntityVisitPlan cEntityVisitPlan) {
		CEntityVisitConclusion reult=cDaoVisitConclusion.queryVisitConclusionNocheckByVisitPlanId(cEntityVisitPlan);
		return reult;
	}
	/**
	 * 序号：visitconclusion:5
	 * 功能：按员工号获得其所有拜访总结以及客户信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:HashedMap
	 */
	public LinkedMap queryVisitConclusionWithClientInfoByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoVisitConclusion.queryVisitConclusionWithClientInfoByEmployeeId(cEntityEmployee);
		return findResult;
	}

	/**
	 * 序号：visitconclusion:6
	 * 功能：按拜访总结号获得拜访总结
	 * 参数：CEntityVisitConclusion(VisitConclusionId)
	 * 返回值:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion queryVisitConclusionByVisitConclusionId(CEntityVisitConclusion cEntityVisitConclusion){
		CEntityVisitConclusion findResult=cDaoVisitConclusion.queryVisitConclusionByVisitConclusionId(cEntityVisitConclusion);
		return findResult;
	}
	
	/**
	 * 序号：visitconclusion:7
	 * 功能：获取所有未删员工的拜访总结及信息
	 * 参数：
	 * 返回值:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	public LinkedMap queryAllVisitConclusionInfo(){
		LinkedMap findResult = cDaoVisitConclusion.queryAllVisitConclusionInfo();
		return findResult;
	}
	/*
	 * ------------------------------拜访撤销 visitundo-------------------------------------
	 */	
	private CDaoVisitUndo cDaoVisitUndo;
	@Resource
	public void setcDaoVisitUndo(CDaoVisitUndo cDaoVisitUndo) {
		this.cDaoVisitUndo = cDaoVisitUndo;
	}

	/**
	 * 序号：visitundo:1
	 * 功能：增加一条拜访撤销
	 * 参数：cEntityVisitPlan(VisitPlanId),cEntityVisitUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveVisitUndo(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo) {
		boolean bisSave=cDaoVisitUndo.saveVisitUndo(cEntityVisitPlan, cEntityVisitUndo);
		return bisSave;
	}
	/**
	 * 序号：visitundo:2
	 * 功能：获得所有的拜访撤销
	 * 参数：无
	 * 返回值:CEntityVisitUndoArray
	 */
	public CEntityVisitUndoArray queryAllVisitUndo() {
		CEntityVisitUndoArray cEntityVisitUndoArray=cDaoVisitUndo.queryAllVisitUndo();
		return cEntityVisitUndoArray;
	}
	
	/**
	 * 序号：visitundo:3
	 * 功能：按拜访撤销号修改拜访撤销的状态
	 * 参数：cEntityVisitUndo(VisitUndoId)
	 * 	   OperateState(MyConstant.VisitUndo.*)
	 * 返回值:CEntityVisitUndoArray
	 */
	public boolean updateVisitUndoStateByVisitUndoId(CEntityVisitUndo cEntityVisitUndo,int OperateState) {
		boolean bisUpdate=cDaoVisitUndo.updateVisitUndoStateByVisitUndoId(cEntityVisitUndo, OperateState);
		return bisUpdate;
	}

	/**
	 * 序号：visitundo:4
	 * 功能：根据任务撤销号获得任务撤销
	 * 参数：cEntityVisitUndo(VisitUndoId)
	 * 返回值:CEntityVisitUndo
	 */
	public CEntityVisitUndo queryVisitUndoByVisitUndoId(CEntityVisitUndo cEntityVisitUndo){
		CEntityVisitUndo findResult=cDaoVisitUndo.queryVisitUndoByVisitUndoId(cEntityVisitUndo);
		return findResult;
	}
	
	/**
	 * 序号：visitundo:5
	 * 功能：获取所有未删员工的拜访撤销记录信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllVisitUndoWithInfo(){
		LinkedMap findResult=cDaoVisitUndo.queryAllVisitUndoWithInfo();
		return findResult;
	}
	/*
	 * -----------------------------拜访延期visitdelay-------------------------------------
	 */

	private CDaoVisitDelay cDaoVisitDelay;
	
	@Resource
	public void setcDaoVisitDelay(CDaoVisitDelay cDaoVisitDelay) {
		this.cDaoVisitDelay = cDaoVisitDelay;
	}

	/**
	 * 序号：visitdealy:1
	 * 功能：增加一条拜访延期
	 * 参数：cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitDelay(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveVisitDealy(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitDelay cEntityVisitDelay){
		boolean bisSave=cDaoVisitDelay.saveVisitDealy(cEntityEmployee, cEntityVisitPlan, cEntityVisitDelay);
		return bisSave;
	}
	
	/**
	 * 序号：visitdealy:2
	 * 功能：获得所有拜访延期
	 * 参数：无
	 * 返回值:CEntityVisitDealyArray
	 */
	public CEntityVisitDealyArray queryAllVisitDelay() {
		CEntityVisitDealyArray cEntityVisitDealyArray=cDaoVisitDelay.queryAllVisitDelay();
		return cEntityVisitDealyArray;
	}

	/**
	 * 序号：visitdealy:3
	 * 功能：按员工号获得所有拜访延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitDealyArray
	 */
	public LinkedMap queryAllVisitDelayByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoVisitDelay.queryAllVisitDelayByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：visitdealy:4
	 * 功能：获取所有未删员工的拜访延期信息
	 * 参数：
	 * 返回值:LinkedMap(CEntityVisitDelay,CEntityVisitPlan,CEntityEmployee,CEntityClient)
	 */
	public LinkedMap queryAllVisitDelayInfo(){
		LinkedMap findResult=cDaoVisitDelay.queryAllVisitDelayInfo();
		return findResult;
	}
	
	/*
	 * -----------------------------出差bussiness------------------------------------
	 */
	private CDaoBussiness cDaoBussiness;
	
	@Resource
	public void setcDaoBussiness(CDaoBussiness cDaoBussiness) {
		this.cDaoBussiness = cDaoBussiness;
	}

	/**
	 * 序号：bussiness:1
	 * 功能：增加一条出差
	 * 参数：cEntityEmployee(EmployeeId),cEntityBussiness(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveBussiness(CEntityEmployee cEntityEmployee,CEntityBussiness cEntityBussiness) {
		boolean bisSave=cDaoBussiness.saveBussiness(cEntityEmployee, cEntityBussiness);
		return bisSave;
	}
	/**
	 * 序号：bussiness:2
	 * 功能：按员工号获得所有的出差
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityBussinessArray
	 */
	public CEntityBussinessArray queryAllBussinessByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityBussinessArray cEntityBussinessArray=cDaoBussiness.queryAllBussinessByEmployeeId(cEntityEmployee);
		return cEntityBussinessArray;
	}
	
	/**
	 * 序号：bussiness:3
	 * 功能：出差登记修改登记时间并将出差状态修改为执行中
	 * 参数：cEntityBussiness(BussinessId,BussinessRegisterTime)
	 * 返回值:boolean
	 */
	public boolean updateBussinessRegisterTime(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessRegisterTime(cEntityBussiness);
		return bisUpdate;
	}
	/**
	 * 序号：bussiness:4
	 * 功能：抵达目的地登记修改抵达地址与时间
	 * 参数：cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * 返回值:boolean
	 */
	public boolean updateBussinessInTimeAndInAddress(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessInTimeAndInAddress(cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * 序号：bussiness:5
	 * 功能：离开目的地登记修改离开地址与时间
	 * 参数：cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * 返回值:boolean
	 */
	public boolean updateBussinessOutTimeAndOutAddress(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessOutTimeAndOutAddress(cEntityBussiness);
		return bisUpdate;
	}

	/**
	 * 序号：bussiness:6
	 * 功能：出差归来登记时间并修改出差状态为未审核
	 * 参数：cEntityBussiness(BussinessId,BussinessReturnTime)
	 * 返回值:boolean
	 */
	public boolean updateBussinessReturn(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussiness.updateBussinessReturn(cEntityBussiness);
		return bisUpdate;
	}
	
	
	/**
	 * 序号：bussiness:7
	 * 功能：按出差号修改出差状态
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateBussinessStateByBussinessId(CEntityBussiness cEntityBussiness,int OperateState){
		boolean bisUpdate=cDaoBussiness.updateBussinessStateByBussinessId(cEntityBussiness, OperateState);
		return bisUpdate;
	}
	
	/**
	 * 序号：bussiness:8
	 * 功能：按员工号获取运行态的出差(未登记,执行中)
	 * 参数：cEntityEmployee(EmployeeId) 
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessRunningStateByEmployeeId(CEntityEmployee cEntityEmployee){
		CEntityBussiness cEntityBussiness=cDaoBussiness.queryBussinessRunningStateByEmployeeId(cEntityEmployee);
		return cEntityBussiness;
	}
	/**
	 * 序号：bussiness:9
	 * 功能：按出差号获得出差详情(本表字段)
	 * 参数：CEntityBussiness(BussinessId) 
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness queryBussinessByBussinessId(CEntityBussiness cEntityBussiness){
		CEntityBussiness findReuslt=cDaoBussiness.queryBussinessByBussinessId(cEntityBussiness);
		return findReuslt;
	}
	
	/**
	 * 序号：bussiness:10
	 * 功能：获取所有未删员工的执行中出差信息(未登记，执行中)
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllBussinessRuning(){
		LinkedMap findResult=cDaoBussiness.queryAllBussinessRuning();
		return findResult;
	}
	
	/**
	 * 序号：bussiness:11
	 * 功能：获取可增出差的员工信息（即员工没有未登记，执行中，未审核的出差）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryBussinessAddOkEmployeeInfo(){
		List findResult=cDaoBussiness.queryBussinessAddOkEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：bussiness:12
	 * 功能：获取所有未删员工的待处理出差信息(未审核)
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllBussinessWaitDeal(){
		LinkedMap findResult=cDaoBussiness.queryAllBussinessWaitDeal();
		return findResult;
	}
	
	/**
	 * 序号：bussiness:13
	 * 功能：.获取所有未删员工的已结束出差信息（审核通过，审核不通过）
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllBussinessComplete(){
		LinkedMap findResult=cDaoBussiness.queryAllBussinessComplete();
		return findResult;
	}
	/*
	 * -----------------------------出差活动bussinessactivity------------------------------------
	 */
	private CDaoBussinessActivity cDaoBussinessActivity;

	@Resource
	public void setcDaoBussinessActivity(CDaoBussinessActivity cDaoBussinessActivity) {
		this.cDaoBussinessActivity = cDaoBussinessActivity;
	}
	/**
	 * 序号：bussinessactivity:1
	 * 功能：增加一条出差活动
	 * 参数：cEntityBussinessActivity(本表字段),cEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean saveBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness) {
		boolean bisSave=cDaoBussinessActivity.saveBussinessActivity(cEntityBussinessActivity, cEntityBussiness);
		return bisSave;
	}
	
	/**
	 * 序号：bussinessactivity:2
	 * 功能：按出差号获得所有的出差活动
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:CEntityBussinessActivityArray
	 */
	public CEntityBussinessActivityArray queryAllBussinessActivityByBussinessId(CEntityBussiness cEntityBussiness) {
		CEntityBussinessActivityArray cEntityBussinessActivityArray=cDaoBussinessActivity.queryAllBussinessActivityByBussinessId(cEntityBussiness);
		return cEntityBussinessActivityArray;
	}
	
	
	/**
	 * 序号：bussinessactivity:3
	 * 功能：按活动对象的Id与活动类型进行修改绑定状态
	 * 参数：cEntityBussinessActivity(BussinessActivityType,BussinessObjectId,BussinessBindType)
	 * 返回值:boolean
	 */
	public boolean updateBussinessBandTypeByObjectIdAndActivityType(CEntityBussinessActivity cEntityBussinessActivity) {
		boolean bisUpdate=cDaoBussinessActivity.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		return bisUpdate;
	}
	
	/**
	 * 序号：bussinessactivity:4
	 * 功能：批量插入出差活动
	 * 参数：cEntityBussinessActivityArray
	 * 返回值:无
	 */
	public void saveBatchBussinessActiviy(CEntityBussinessActivityArray cEntityBussinessActivityArray){
		cDaoBussinessActivity.saveBatchBussinessActiviy(cEntityBussinessActivityArray);
	}
	/**
	 * 序号：bussinessactivity:5
	 * 功能：按出差号获得绑定出差活动的个数
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:int
	 */
	public int queryBussinessActivityBindNumberByBussinessId(CEntityBussiness cEntityBussiness){
		int result=cDaoBussinessActivity.queryBussinessActivityBindNumberByBussinessId(cEntityBussiness);
		return result;
	}
	
	/**
	 * 序号：bussinessactivity:6
	 * 功能：按出差号解绑所有出差活动
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean updateBussinessActivityBindTypeUnbindByBussinessId(CEntityBussiness cEntityBussiness){
		boolean bisUpdate=cDaoBussinessActivity.updateBussinessActivityBindTypeUnbindByBussinessId(cEntityBussiness);
		return bisUpdate;
	}
	/*
	 * -----------------------------出差不良记录bussinessbadrecord------------------------------------
	 */
	
	private CDaoBussinessBadrecord cDaoBussinessBadrecord;
	
	@Resource
	public void setcDaoBussinessBadrecord(
			CDaoBussinessBadrecord cDaoBussinessBadrecord) {
		this.cDaoBussinessBadrecord = cDaoBussinessBadrecord;
	}

	/**
	 * 序号：bussinessbadrecord:1
	 * 功能：增加一条出差不良记录
	 * 参数：cEntityBussiness(BussinessId),cEntityBussinessBadrecord(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveBussinessBadrecord(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord) {
		boolean bisSave=cDaoBussinessBadrecord.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord);
		return bisSave;
	}
	
	/**
	 * 序号：bussinessbadrecord:2
	 * 功能：获得所有的出差不良记录
	 * 参数：无
	 * 返回值:CEntityBussinessBadrecordArray
	 */
	public CEntityBussinessBadrecordArray queryAllBussinessBadrecord() {
		CEntityBussinessBadrecordArray cEntityBussinessBadrecordArray=cDaoBussinessBadrecord.queryAllBussinessBadrecord();
		
		return cEntityBussinessBadrecordArray;
	}
	/**
	 * 序号：bussinessbadrecord:3
	 * 功能：按员工号获取其所有出差不良记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllBussinessBadrecordByEmployeeId(CEntityEmployee cEntityEmployee) {
		LinkedMap findResult=cDaoBussinessBadrecord.queryAllBussinessBadrecordByEmployeeId(cEntityEmployee);
		return findResult;
	}
	

	/**
	 * 序号：bussinessbadrecord:4
	 * 功能：获取所有未删员工的出差不良记录信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllBussinessBadrecordInfo(){
		LinkedMap findResult=cDaoBussinessBadrecord.queryAllBussinessBadrecordInfo();
		return findResult;
	}

	/*
	 * --------------------------出差撤销bussinessundo-------------------------------------------
	 */
	private CDaoBussinessUndo cDaoBussinessUndo;
	
	@Resource
	public void setcDaoBussinessUndo(CDaoBussinessUndo cDaoBussinessUndo) {
		this.cDaoBussinessUndo = cDaoBussinessUndo;
	}

	/**
	 * 序号：bussinessundo:1
	 * 功能：增加一条出差撤销
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean savaBussinessUndo(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo) {
		boolean bisSave=cDaoBussinessUndo.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		return bisSave;
	}
	
	
	/**
	 * 序号：bussinessundo:2
	 * 功能：查询所有未删员工出差撤销信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap queryAllBussinessUndoInfo(){
		LinkedMap findResult=cDaoBussinessUndo.queryAllBussinessUndoInfo();
		return findResult;
	}
	
	/*
	 * ----------------------------考勤attendance------------------------------------
	 */
	private CDaoAttendance cDaoAttendance;
	
	@Resource
	public void setcDaoAttendance(CDaoAttendance cDaoAttendance) {
		this.cDaoAttendance = cDaoAttendance;
	}

	/**
	 * 序号：attendance:1
	 * 功能：批量插入考勤表
	 * 参数：cEntityAttendanceArray
	 * 返回值:无
	 */
	public void saveAttendances(CEntityAttendanceArray cEntityAttendanceArray) {
		cDaoAttendance.saveAttendances(cEntityAttendanceArray);
	}
	/**
	 * 序号：attendance:2
	 * 功能：按员工号及当日日期进行签到
	 * 参数：cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceRegisterTime,AttendanceDate)
	 * 返回值:无
	 */
	public boolean updateAttendanceRegisterTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=cDaoAttendance.updateAttendanceRegisterTimeByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		
		return bisUpdate;
	}
	/**
	 * 序号：attendance:3
	 * 功能：按员工号及当日日期进行签退
	 * 参数：cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceSignoutTime,AttendanceDate)
	 * 返回值:boolean
	 */
	public boolean updateAttendanceSignoutTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee){
		boolean bisUpdate=cDaoAttendance.updateAttendanceSignoutTimeByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		return bisUpdate;
	}
	/**
	 * 序号：attendance:4
	 * 功能：按日期获得所有的考勤
	 * 参数：cEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAllAttendanceByAttendanceDate(CEntityAttendance cEntityAttendance) {
		CEntityAttendanceArray cEntityAttendanceArray=cDaoAttendance.queryAllAttendanceByAttendanceDate(cEntityAttendance);
		return cEntityAttendanceArray;
	}
	/**
	 * 序号：attendance:5
	 * 功能：按员工号和当日日期查询考勤
	 * 参数：cEntityAttendance(AttendanceDate),CEntityEmployee(EmployeeId)
	 * 返回值:CEntityAttendance
	 */
	public CEntityAttendance queryAttendanceByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee){
		CEntityAttendance findReulst=cDaoAttendance.queryAttendanceByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		return findReulst;
	}
	
	/**
	 * 序号：attendance:6
	 * 功能：按日期获得所有的考勤数据，含员工号，员工账号，员工名
	 * 参数：cEntityAttendance(AttendanceDate)
	 * 返回值:HashedMap
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByAttendaceData(CEntityAttendance cEntityAttendance){
		HashedMap map=cDaoAttendance.queryAllAttendanceWithEmployeeDateByAttendaceData(cEntityAttendance);
		return map;
	}
	
	/**
	 * 序号：attendance:7
	 * 功能：按员工号查询所有考勤记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAllEmployeeAttendance(CEntityEmployee cEntityEmployee) {
		CEntityAttendanceArray cEntityAttendanceArray=cDaoAttendance.queryAllEmployeeAttendance(cEntityEmployee);
		return cEntityAttendanceArray;
	}
	

	/**
	 * 序号：attendance:8
	 * 功能：按员工号和日期查询考勤记录
	 * 参数：CEntityEmployee(EmployeeId),CEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray queryAtteandenByEmployeeIdandAttendanceOldDate(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		CEntityAttendanceArray cEntityAttendanceArray=cDaoAttendance.queryAtteandenByEmployeeIdandAttendanceOldDate(cEntityEmployee,cEntityAttendance);
		return cEntityAttendanceArray;
	}
	
	/**
	 * 序号：attendance:9
	 * 功能：按员工名和日期查询考勤记录
	 * 参数：CEntityEmployee(EmployeeName),CEntityAttendance(AttendanceDate)
	 * 返回值:HashedMap
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByEmployeeName(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance) {
		HashedMap findResult=cDaoAttendance.queryAllAttendanceWithEmployeeDateByEmployeeName(cEntityEmployee, cEntityAttendance);
		return findResult;
	}
	/**
	 * 序号：attendance:10
	 * 功能：按年份获取所有未删员工的员工号，员工账号，员工名，考勤次数(签到次数，签退次数)，迟到次数（签到迟到次数，签退迟到次数），漏签次数（签到迟到漏签次数，签退漏签次数）
	 * 参数：year(yyyy)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYear(String year) {
		List findResult=cDaoAttendance.queryStatisticalByYear(year);
		return findResult;
	}
	
	/**
	 * 序号：attendance:11
	 * 功能：按年份，月份获取所有未删员工的员工号，员工账号，员工名，考勤次数(签到次数，签退次数)，迟到次数（签到迟到次数，签退迟到次数），漏签次数（签到迟到漏签次数，签退漏签次数）
	 * 参数：year(yyyy)，month(mm)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearAndMonth(String year,String month){
		List findResult=cDaoAttendance.queryStatisticalByYearAndMonth(year,month);
		return findResult;
	}
	
	/**
	 * 序号：attendance:12
	 * 功能：按员工名，年份，月份获取所有未删员工的员工号，员工账号，员工名，考勤次数(签到次数，签退次数)，迟到次数（签到迟到次数，签退迟到次数），漏签次数（签到迟到漏签次数，签退漏签次数）
	 * 参数：year(yyyy)，month(mm)，cEntityEmployee(EmployeeName)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearMonthAndEmployeeName(CEntityEmployee cEntityEmployee,String year,String month){
		List findResult=cDaoAttendance.queryStatisticalByYearMonthAndEmployeeName(cEntityEmployee, year, month);
		return findResult;
	}
	/*
	 * ----------------------------通知 notice------------------------------------
	 */
	
	private CDaoNotice cDaoNotice;
	
	@Resource
	public void setcDaoNotice(CDaoNotice cDaoNotice) {
		this.cDaoNotice = cDaoNotice;
	}

	/**
	 * 序号：notice:1
	 * 功能：增加一条通知
	 * 参数：cEntityNotice(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveNotice(CEntityNotice cEntityNotice) {
		boolean bisSave=cDaoNotice.saveNotice(cEntityNotice);
		return bisSave;
	}
	
	/**
	 * 序号：notice:2
	 * 功能：获得全部通知
	 * 参数：无
	 * 返回值:CEntityNoticeArray
	 */
	public CEntityNoticeArray queryAllNotice() {
		CEntityNoticeArray cEntityNoticeArray=cDaoNotice.queryAllNotice();
		return cEntityNoticeArray;
	}

	/*
	 * ----------------------------建议 suggest----------------------------------
	 */
	private CDaoSuggest cDaoSuggest;

	@Resource
	public void setcDaoSuggest(CDaoSuggest cDaoSuggest) {
		this.cDaoSuggest = cDaoSuggest;
	}
	

	/**
	 * 序号：suggest:1
	 * 功能：增加一条建议
	 * 参数：cEntityEmployee(EmployeeId),cEntitySuggest(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest) {
		boolean bisSave=cDaoSuggest.saveSuggest(cEntityEmployee, cEntitySuggest);
		
		return bisSave;
	}
	
	
	/**
	 * 序号：suggest:2
	 * 功能：获得所有的建议
	 * 参数：无
	 * 返回值:CEntitySuggestArray
	 */
	public CEntitySuggestArray queryAllSuggest() {
		CEntitySuggestArray cEntitySuggestArray=cDaoSuggest.queryAllSuggest();
		return cEntitySuggestArray;
	}

	
}
