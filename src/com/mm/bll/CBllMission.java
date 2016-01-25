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
	 * 序号：missionmodule:1 
	 * 功能：创建外勤任务
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(本表字段) 
	 * 返回值:boolean
	 */
	public boolean createMission(CEntityEmployee cEntityEmployee,
			CEntityMission cEntityMission) {
		//创建外勤任务并分配
		boolean bisCreate = iDaoFrame.saveMission(cEntityEmployee,
				cEntityMission);
		return bisCreate;
	}

	/**
	 * 序号：missionmodule:2 
	 * 功能：外勤人员接受任务 
	 * 参数：cEntityMission(MissionId) 返回值:boolean
	 */
	public boolean acceptMission(CEntityMission cEntityMission) {
		//任务状态改为执行中
		boolean bisAccept = iDaoFrame.updateMissionState(cEntityMission,
				MyConstant.Mission.MISSION_UNDERWAY);
		return bisAccept;
	}

	/**
	 * 序号：missionmodule:3 
	 * 功能：外勤人员提交任务及总结
	 * 参数：cEntityMission(MissionId),cEntityMissionConclusion(本表字段) 返回值:boolean
	 */

	public boolean submitMission(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion) {
		// TODO Auto-generated method stub
		//生成任务总结记录
		boolean bisSave = iDaoFrame.saveMissionConclusion(cEntityMission,
				cEntityMissionConclusion);
		//任务状态改为未审核
		boolean bisUpdate = iDaoFrame.updateMissionState(cEntityMission,
				MyConstant.Mission.MISSION_NOCHECK);
		
		boolean bisSubmit=bisSave&bisUpdate;
	
		return bisSubmit;

	}

	

	/**
	 * 序号：missionmodule:4
	 * 功能：审核任务
	 * 参数：  cEntityMission(MissionId)
	 * 		cEntityMissionConclusion(MissionConclusionId)
	 * 	    OperateState(MyConstant.MissionConclusion.*)
	 * 返回值:boolean
	 */
	public boolean checkMissionConclusion(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion,int OperateState ) {
		//任务状态转为已审核
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_CHECK);
		//任务总结的审核结果转为通过或不通过
		boolean bisUpdateMissionCheck=iDaoFrame.updateMissionCheck(cEntityMissionConclusion, OperateState);
		boolean bisCheck=bisUpdateMissionCheck&&bisUpdateMissionState;
		return bisCheck;
	}

	/**
	 * 序号：missionmodule:5
	 * 功能：撤销任务
	 * 参数：  cEntityMission(MissionId)
	 * 		cEntityMissionUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoMission(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		//任务状态转为已撤销
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_UNDO);
		//创建一条任务撤销记录
		boolean bisSaveMissionUndo=iDaoFrame.saveMissionUndo(cEntityMission, cEntityMissionUndo);
	
		//如果与出差活动有过绑定的话，则解除绑定
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
	 * 序号：missionmodule:6
	 * 功能：撤回任务
	 * 参数：  cEntityMissionUndo(MissionUndoId)
	 * 返回值:boolean
	 */
	public boolean recallMission(CEntityMissionUndo cEntityMissionUndo) {
		//撤销任务状态由已撤销转为已撤回
		boolean bisUpdateUndoType=iDaoFrame.updateMissionUndoType(cEntityMissionUndo,MyConstant.MissionUndo.MISSIONUNDO_RECORD);
		//撤回的任务转为原撤销前的状态
		CEntityMissionUndo findUndo=iDaoFrame.queryMissionUndoByMissionUndoId(cEntityMissionUndo);
		CEntityMission cEntityMission=findUndo.getcEntityMission();
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, findUndo.getM_iMissionUndoRecallType());
		//如果与出差活动有过绑定的话，建立绑定
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
	 * 序号：missionmodule:7
	 * 功能：延期任务
	 * 参数：cEntityMission(MissionId,MissionDeadline),cEntityMissionDelay(本表字段)
	 * 返回值:boolean
	 */
	public boolean delayMission(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay){
		//已过期状态转为执行中状态
		boolean bisUpdateMissionState=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_UNDERWAY);
		//延期后的任务改变了其任务期限
		boolean bisUpdateMissionDeadline=iDaoFrame.updateMissionDeadline(cEntityMission);
		//生成一条延期记录
		boolean bisSaveMissionDelay=iDaoFrame.saveMissionDealy(cEntityMission, cEntityMissionDelay);
		
		boolean bisDelay=bisUpdateMissionState&&bisUpdateMissionDeadline&&bisSaveMissionDelay;
		return bisDelay;
		
		
	}
	
	/**
	 * 序号：missionmodule:8
	 * 功能：废弃任务
	 * 参数：cEntityMission(MissionId)
	 * 返回值:boolean
	 */
	public boolean abandonMission(CEntityMission cEntityMission){
		//已过期状态转为已失败状态
		boolean bisAbando=iDaoFrame.updateMissionState(cEntityMission, MyConstant.Mission.MISSION_FAILURE);
		return bisAbando;
		
	}

	
	/**
	 * 序号：missionmodule:9
	 * 功能：获取未接受任务
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetWaitTakeMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByEmployeeIdAndMissionState(cEntityEmployee, MyConstant.Mission.MISSION_WAITTAKE);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：missionmodule:10
	 * 功能：获取执行中任务(包含已过期)
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetUnderWayMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionRunningStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：missionmodule:11
	 * 功能：获取终止态的任务(未审核,已审核,已撤销,已失败)
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetCompleteMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionCompleteStateByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：missionmodule:12
	 * 功能：获得任务总结
	 * 参数：CEntityMission(MissionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusion(CEntityMission cEntityMission){
		CEntityMissionConclusion cEntityMissionConclusion=iDaoFrame.queryMissonConclusionByMissionId(cEntityMission);
		return cEntityMissionConclusion;
	}

	
	/**
	 * 序号：missionmodule:13
	 * 功能：管理员获得所有待接受任务
	 * 参数：无
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllWaitTakeMissionArray(){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByMissionType(MyConstant.Mission.MISSION_WAITTAKE);
		return cEntityMissionArray;
	}
	
	
	/**
	 * 序号：missionmodule:14
	 * 功能：管理员获得该员工所有任务
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllEmployeeMission(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByEmployeeId(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：missionmodule:15
	 * 功能：管理员获得该员工所有任务总结
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionConclusionArray
	 */
	public LinkedMap GetAllEmployeeMissionConclusion(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllMissionConclusionByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：missionmodule:16
	 * 功能：管理员获得任务总结详情
	 * 参数：CEntityMissionConclusion(MissionConclusionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusionDetail(CEntityMissionConclusion cEntityMissionConclusion){
		CEntityMissionConclusion findResult=iDaoFrame.queryMissionConclusionByMissionConclusionId(cEntityMissionConclusion);
		return findResult;
	}
	/**
	 * 序号：missionmodule:17
	 * 功能：管理员获得该员工所有的任务延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetEmployeeMissionDelay(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllMissionDelayByEmployeeId(cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：missionmodule:18
	 * 功能：管理员获得所有未开始与执行中的任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionRunning(){
		LinkedMap findResult=iDaoFrame.queryAllMissionRuning();
		return findResult;
	}
	/**
	 * 序号：missionmodule:19
	 * 功能：统计员工号，员工账号，员工名，任务需执行数量（未接受，执行中）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List GetMissionRuningNumberOfEmployeeInfo(){
		List findResult=iDaoFrame.queryMissionRunningNumberOfEmployeeInfo();
		return findResult;
	}
	/**
	 * 序号：missionmodule:20
	 * 功能：管理员获得所有未审核与已过期的任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionDeal(){
		LinkedMap findResult=iDaoFrame.queryAllMissionWaitDeal();
		return findResult;
	}
	/**
	 * 序号：missionmodule:21
	 * 功能：管理员获得所有已过期与已审核的任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionComplete(){
		LinkedMap findResult=iDaoFrame.queryAllMissionComplete();
		return findResult;
	}
	
	
	/**
	 * 序号：missionmodule:22
	 * 功能：管理员获得所有任务撤销及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionUndoWithEmployeeInfo(){
		LinkedMap findResult=iDaoFrame.queryAllMissionUndoWithEmployeeInfo();
		return findResult;
	}
	/**
	 * 序号：missionmodule:23
	 * 功能：管理员获得所有任务总结及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionConclusionWithEmployeeInfo(){
		LinkedMap findResult=iDaoFrame.queryAllMissionConclusionWithEmployeeInfo();
		return findResult;
	}
	/**
	 * 序号：missionmodule:24
	 * 功能：管理员获得所有任务延期及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionDelayWithEmployeeInfo(){
		LinkedMap findResult=iDaoFrame.queryAllMissionDelayWithMissionInfoAndEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：missionmodule:25
	 * 功能：判断是否有未处理的任务
	 * 参数：
	 * 返回值:LinkedMap
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
	 * 序号：missionmodule:26
	 * 功能：根据员工号获取其绑定的任务信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=iDaoFrame.queryMissionBindByEmployee(cEntityEmployee);
		return findResult;
	}
	
	
	/**
	 * 序号：missionmodule:27
	 * 功能：根据员工号获取其未绑定的任务信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionUnBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=iDaoFrame.queryMissionUnBindByEmployee(cEntityEmployee);
		return findResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
