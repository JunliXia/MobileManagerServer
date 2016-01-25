package com.mm.bll;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.entity.CEntityBussinessBadrecord;
import com.mm.entity.CEntityBussinessUndo;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entityarray.CEntityBussinessArray;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cBllBussiness")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CBllBussiness {
	private IDaoFrame iDaoFrame;

	@Resource(name = "cDaoFrameImpl")
	public void setiDaoFrame(IDaoFrame iDaoFrame) {
		this.iDaoFrame = iDaoFrame;
	}
	
	/**
	 * 序号：bussinessmodule:1 
	 * 功能：创建出差
	 * 参数：cEntityEmployee(EmployeeId),cEntityBussiness(本表字段) 
	 * 返回值:boolean
	 */
	public boolean createBussiness(CEntityBussiness cEntityBussiness, CEntityEmployee cEntityEmployee){
		//创建出差，出差状态为未登记,成功将出差分配给外勤人员
		boolean bisCreate=iDaoFrame.saveBussiness(cEntityEmployee, cEntityBussiness);
		return bisCreate;
	}
	
	/**
	 * 序号：bussinessmodule:2
	 * 功能：邦定出差活动
	 * 参数：
	 * 返回值:boolean
	 */
	public boolean bindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness){
		//将出差活动记录创建
		boolean bisUndoActivity=iDaoFrame.saveBussinessActivity(cEntityBussinessActivity, cEntityBussiness);
		boolean bisUndo=false;
		//将对应的绑定
		if(cEntityBussinessActivity.getM_iBussinessActivityType()==MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE){
			CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(cEntityBussinessActivity.getM_iBussinessObjectId()).MissionBussinessBandState(MyConstant.Mission.MISSION_BUSSINESSBAND).build();
			bisUndo=iDaoFrame.updateMissionBindType(cEntityMission);
		}else if(cEntityBussinessActivity.getM_iBussinessActivityType()==MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE){
			CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(cEntityBussinessActivity.getM_iBussinessObjectId()).VisitBussinessBandState(MyConstant.VisitPlan.VISITPLAN_BUSSINESSBAND).build();
			bisUndo=iDaoFrame.updateVisitBindType(cEntityVisitPlan);
		}
		
		return bisUndoActivity&&bisUndo;
	}
	
	
	/**
	 * 序号：bussinessmodule:3
	 * 功能：解绑出差活动
	 * 参数：
	 * 返回值:boolean
	 */
	public boolean unbindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity){
		//将出差活动记录解绑
		boolean bisUndoActivity=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		boolean bisUndo=false;
		//将对应的解绑
		if(cEntityBussinessActivity.getM_iBussinessActivityType()==MyConstant.BussinessActivity.BUSSINESSACTIVITY_MISSIONTYPE){
			CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(cEntityBussinessActivity.getM_iBussinessObjectId()).MissionBussinessBandState(MyConstant.Mission.MISSION_BUSSINESSNOBAND).build();
			bisUndo=iDaoFrame.updateMissionBindType(cEntityMission);
		}else if(cEntityBussinessActivity.getM_iBussinessActivityType()==MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE){
			CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(cEntityBussinessActivity.getM_iBussinessObjectId()).VisitBussinessBandState(MyConstant.VisitPlan.VISITPLAN_BUSSINESSNOBAND).build();
			bisUndo=iDaoFrame.updateVisitBindType(cEntityVisitPlan);
		}
		
		return bisUndoActivity&&bisUndo;
	}
	
	/**
	 * 序号：bussinessmodule:4
	 * 功能：出差登记
	 * 参数：cEntityBussiness(BussinessId,BussinessRegisterTime)
	 * 返回值:boolean
	 */
	public boolean registerBussiness(CEntityBussiness cEntityBussiness){
		//出差状态转为执行中
		boolean bisRegister=iDaoFrame.updateBussinessRegisterTime(cEntityBussiness);
		return bisRegister;
	}
	

	/**
	 * 序号：bussinessmodule:5
	 * 功能：抵达目的地登记
	 * 参数：cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * 返回值:boolean
	 */
	public boolean inregisterBussiness(CEntityBussiness cEntityBussiness){
		boolean bisIn=iDaoFrame.updateBussinessInTimeAndInAddress(cEntityBussiness);
		
		return bisIn;
	}
	/**
	 * 序号：bussinessmodule:6
	 * 功能：离开目的地登记
	 * 参数：cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * 返回值:boolean
	 */
	public boolean outregisterBussiness(CEntityBussiness cEntityBussiness){
		boolean bisOut=iDaoFrame.updateBussinessOutTimeAndOutAddress(cEntityBussiness);
		return bisOut;
	}
	
	/**
	 * 序号：bussinessmodule:7
	 * 功能：归来登记
	 * 参数：cEntityBussiness(BussinessId,BussinessReturnTime)
	 * 返回值:boolean
	 */
	public boolean returnregisterBussiness(CEntityBussiness cEntityBussiness){
		boolean bisReturn=iDaoFrame.updateBussinessReturn(cEntityBussiness);
		return bisReturn;
	}
	
	
	/**
	 * 序号：bussinessmodule:8
	 * 功能：审核出差通过
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean checkBussinessPass(CEntityBussiness cEntityBussiness){
		boolean bisCheck=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_CHECKPASS);
		return bisCheck;
	}
	/**
	 * 序号：bussinessmodule:9
	 * 功能：审核出差不通过
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean checkBussinessNopass(CEntityBussiness cEntityBussiness ){
		boolean bisupdateBussinessState=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_CHECKNOPASS);
//		boolean bisSave=iDaoFrame.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord);
		
//		boolean bisCheck=bisupdateBussinessState;
		return bisupdateBussinessState;
	}
	
	
	/**
	 * 序号：bussinessmodule:10
	 * 功能：外勤人员进入出差(当前仅有一个未登记或执行中出差)
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessRunningState(CEntityEmployee cEntityEmployee){
		CEntityBussiness cEntityBussiness=iDaoFrame.queryBussinessRunningStateByEmployeeId(cEntityEmployee);
		return cEntityBussiness;
	}
	
	
	/**
	 * 序号：bussinessmodule:11
	 * 功能：外勤人员进入出差纪录
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityBussinessArray
	 */
	public CEntityBussinessArray GetBussinessRecall(CEntityEmployee cEntityEmployee){
		CEntityBussinessArray cEntityBussinessArray=iDaoFrame.queryAllBussinessByEmployeeId(cEntityEmployee);
		return cEntityBussinessArray;
	}
	
	

	/**
	 * 序号：bussinessmodule:12
	 * 功能：查看出差纪录详情
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessDetail(CEntityBussiness cEntityBussiness){
		CEntityBussiness finResult=iDaoFrame.queryBussinessByBussinessId(cEntityBussiness);
		return finResult;
	}
	
	
	/**
	 * 序号：bussinessmodule:13
	 * 功能：获取出差活动
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:HashMap<String, Object>
	 */
	public HashMap<String, Object> GetGetBussinessActivity(CEntityBussiness cEntityBussiness){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanByBussienssId(cEntityBussiness);
		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMisssionByBussinessId(cEntityBussiness);
		HashMap<String, Object> activityMap=new HashMap<String,Object>();
		activityMap.put(MyOpcode.VisitPlan.VisitPlanList, cEntityVisitPlanArray);
		activityMap.put(MyOpcode.Mission.MissionList, cEntityMissionArray);
		return activityMap;
		
	}
	/**
	 * 序号：bussinessmodule:14
	 * 功能：判断出差是否有绑定的出差活动
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean checkBussinesHavaBussinessActivity(CEntityBussiness cEntityBussiness){
		boolean bishavs=false;
		int bindnumber=iDaoFrame.queryBussinessActivityBindNumberByBussinessId(cEntityBussiness);
		if(bindnumber!=0){
			bishavs=true;
		}
		return bishavs;
		
		
	}
	
	/**
	 * 序号：bussinessmodule:15
	 * 功能：撤销出差，不设计出差活动
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoBussiness(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		//修改出差状态为撤销
		boolean bisUpdate=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_UNDO);
		//增加出差撤销记录
		boolean bisUndo=iDaoFrame.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo&bisUpdate;
	}
	
	/**
	 * 序号：bussinessmodule:15
	 * 功能：撤销出差，并撤销出差活动
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoBussinessWithUndoBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		//修改出差状态为撤销
		boolean bisUpdate=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_UNDO);
		//增加出差撤销记录
		boolean bisUndo=iDaoFrame.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		//撤销任务
		boolean bisUndoMission=iDaoFrame.updateMissionStateUndoWithSaveMissionUndoByBussinessId(cEntityBussiness);
		//撤销拜访计划
		boolean bisUndoVisit=iDaoFrame.updateVisitPlanUndoWithSaveVisitUndoByBussinessId(cEntityBussiness);
		
		
		return bisUpdate&&bisUndo&&bisUndoMission&&bisUndoVisit;
	}
	
	/**
	 * 序号：bussinessmodule:16
	 * 功能：撤销出差，解绑出差活动
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoBussinessWithUnbindBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		//修改出差状态为撤销
		boolean bisUpdate=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_UNDO);
		//增加出差撤销记录
		boolean bisUndo=iDaoFrame.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		//解绑出差活动
		boolean bisUnband=iDaoFrame.updateBussinessActivityBindTypeUnbindByBussinessId(cEntityBussiness);
		//解绑任务
		boolean bisUnbandMission=iDaoFrame.updateMissionBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		//解绑拜访计划
		boolean bisUnbandVisitplan=iDaoFrame.updateVisitPlanBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		
		
		return bisUpdate&&bisUndo&&bisUnband&&bisUnbandMission&&bisUnbandVisitplan;
	}
	
	
	/**
	 * 序号：bussinessmodule:17
	 * 功能：审核出差通过，但要添加一条出差不良记录
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessBadrecord(本表字段)
	 * 返回值:boolean
	 */
	public boolean checkBussinessPassWithBadrecall(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord){
		boolean bisupdateBussinessState=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_CHECKPASS);
		boolean bisSave=iDaoFrame.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord);
		boolean bisCheck=bisupdateBussinessState;
		return bisSave&&bisCheck;

	}
	
	
	
	/**
	 * 序号：bussinessmodule:18
	 * 功能：得到该员工的所有出差不良记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	public LinkedMap getEmployeeBussinessBadrecall(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllBussinessBadrecordByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:19
	 * 功能：获取所有未删员工的执行中出差信息(未登记，执行中)
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessRunning(){
		LinkedMap findResultg=iDaoFrame.queryAllBussinessRuning();
		return findResultg;
	}
	
	
	/**
	 * 序号：bussinessmodule:20
	 * 功能：获取可增出差的员工信息（即员工没有未登记，执行中，未审核的出差）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getBussinessAddOkEmployee(){
		List findResult=iDaoFrame.queryBussinessAddOkEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:21
	 * 功能：获取所有未删员工的待处理出差信息(未审核)
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessWaitDeal(){
		LinkedMap findResult=iDaoFrame.queryAllBussinessWaitDeal();
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:22
	 * 功能：获取所有未删员工的已结束出差信息（审核通过，审核不通过）
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessComplete(){
		LinkedMap findResult=iDaoFrame.queryAllBussinessComplete();
		return findResult;
	}
	
	
	/**
	 * 序号：bussinessmodule:23
	 * 功能：获取所有未删员工出差撤销记录
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessUndoInfo(){
		LinkedMap findResult=iDaoFrame.queryAllBussinessUndoInfo();
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:24
	 * 功能：获取所有未删员工出差撤销记录
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessBadrecordInfo(){
		LinkedMap findReult=iDaoFrame.queryAllBussinessBadrecordInfo();
		return findReult;
	}
	
	/**
	 * 序号：bussinessmodule:25
	 * 功能：判断是否还有未处理的出差记录
	 * 参数：
	 * 返回值:boolean
	 */
	public boolean hasWaitDealBussiness(){
		boolean bisHas=false;
		int number=iDaoFrame.queryBussinessWaitDealNumber();
		if(number==0){
			bisHas=false;
		}else{
			bisHas=true;
		}
		return bisHas;
	}
	
	
	
	
	
}