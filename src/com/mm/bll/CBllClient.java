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
	 * 序号：clientmodule:1 
	 * 功能：创建客户
	 * 参数：cEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public boolean createClient(CEntityClient cEntityClient){
		//创建客户
		boolean bisCreate=iDaoFrame.saveClient(cEntityClient);
		return bisCreate;
		
	}
	
	/**
	 * 序号：clientmodule:2 
	 * 功能：分配客户
	 * 参数：cEntityClient(ClientId),cEntityEmployee(EmployeeId),cEntityVisitPlan(本表字段)
	 * 返回值:boolean
	 */
	public boolean distributionClient(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan){
		//客户分配给外勤人员,客户的状态若为未分配则转为已分配
		boolean bisUpdate=iDaoFrame.updateClientEmployeeIdAndClientState(cEntityClient, cEntityEmployee);
		//生成拜访计划记录
		boolean bisSave=iDaoFrame.saveVisitPlan(cEntityClient, cEntityEmployee, cEntityVisitPlan);

		boolean bisDistribution=bisUpdate&&bisSave;
		return bisDistribution;
		
	}

	
	/**
	 * 序号：clientmodule:3 
	 * 功能：提交拜访
	 * 参数：cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(本表字段)
	 * 返回值:boolean
	 */
	public boolean submitVisit(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion){
		//拜访计划状态改为未审核
		boolean bisUpdatePlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_WAITCHECK);
		//成功生成拜访总结记录
		boolean bisSave=iDaoFrame.saveVisitConclusion(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		
		boolean bisSubmot=bisUpdatePlanState&&bisSave;
		return bisSubmot;
		
	}
	
	
	/**
	 * 序号：clientmodule:4 
	 * 功能：审核客户拜访
	 * 参数：ccEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * 返回值:boolean
	 */
	public boolean checkVisitConclusion(CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion,int OperateState){
		//若拜访计划设置了循环拜访，则状态改为未开始，并修改拜访时间
		CEntityVisitPlan findPlan=iDaoFrame.queryVisitPlanByVisitPlanId(cEntityVisitPlan.getM_iVisitPlanId());
		boolean bisUpdatePlanState=false;
		if(findPlan.getM_iVisitPlanCycle()==MyConstant.VisitPlanCycle.VISITPLANCYCLE_CYCLE){
			String newStarttime=getCycleStartTime(findPlan);
			String newEndtime=getCycleEndTime(newStarttime, findPlan.getM_iVisitPlanDays());
			String newPubdate=getNewPubdate();
			CEntityVisitPlan updatePlan=new CEntityVisitPlan.Builder().VisitPlanId(findPlan.getM_iVisitPlanId()).VisitPlanStartTime(newStarttime).VisitPlanEndTime(newEndtime).VisitPlanPubdate(newPubdate).build();
			bisUpdatePlanState=iDaoFrame.updateVisitTime(updatePlan);
		}else if(findPlan.getM_iVisitPlanCycle()==MyConstant.VisitPlanCycle.VISITPLANCYCLE_NOCYCLE){
			//拜访计划未审核状态改为已审核状态
			bisUpdatePlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(findPlan, MyConstant.VisitPlan.VISITPLAN_CHECK);
		}
		//拜访总结的审核结果转为通过或不通过
		boolean bisUpdateCheck=iDaoFrame.updateMissionConclusionVisitCheck(cEntityVisitConclusion, OperateState);
		
		
		boolean bisCheck=bisUpdatePlanState&&bisUpdateCheck;
		return bisCheck;
		
	}
	
	
	/**
	 * 序号：clientmodule:5
	 * 功能：提交客户
	 * 参数：cEntityClient(本表字段),cEntityEmployee(EmployeeId)
	 * 返回值:boolean
	 */
	public boolean submitCliet(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient){
		//成功提交客户，客户状态为未审核,成功生成客户提交记录
		CEntityClientSubmit cEntityClientSubmit=new CEntityClientSubmit.Builder().ClientSubmitTime(getNewPubdate()).build();
		boolean bisSubmit=iDaoFrame.saveClientSubmit(cEntityClient, cEntityEmployee, cEntityClientSubmit);
		return bisSubmit;
	}
	
	
	
	/**
	 * 序号：clientmodule:6
	 * 功能：客户考察
	 * 参数：cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.ClientSubmit.*)
	 * 返回值:boolean
	 */
	public boolean checkClient(CEntityClient cEntityClient,int OperateState){
	
		//客户提交记录的审核结果转为通过或不通过
		boolean bisUpdateClientSubmitState=iDaoFrame.updateClientSubmitStateByClientId(cEntityClient, OperateState);
		//客户状态转为未分配或已删除
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
	 * 序号：clientmodule:7
	 * 功能：循环拜访
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * 返回值:boolean
	 */
	public boolean setupVisitCycle(CEntityVisitPlan cEntityVisitPlan){
		//拜访计划循环状态改为已循环,以及其他循环参数
		boolean bisSetup=iDaoFrame.updateVisitPlanCyclesByVisitPlanId(cEntityVisitPlan);
		
		return bisSetup;
	}
	
	/**
	 * 序号：clientmodule:8
	 * 功能：延期拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanEndTime)
	 * 	   cEntityEmployee(EmployeeId)
	 * 返回值:boolean
	 */
	public boolean delayVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityEmployee cEntityEmployee){
		//延期后的拜访计划改变了其拜访期限.已过期的状态转为执行中状态
		boolean bisUpdateVisitEndTime=iDaoFrame.updateVisitPlanEndTime(cEntityVisitPlan);
		//生成一条延期记录
		CEntityVisitDelay cEntityVisitDelay=new CEntityVisitDelay.Builder().VisitDelayTime(getNewPubdate()).VisitDelayDeadline(cEntityVisitPlan.getM_sVisitPlanEndTime()).build();
		boolean bisSave=iDaoFrame.saveVisitDealy(cEntityEmployee, cEntityVisitPlan, cEntityVisitDelay);
	
		boolean bisDelay=bisUpdateVisitEndTime&&bisSave;
		return bisDelay;
	}
	
	
	
	
	/**
	 * 序号：clientmodule:9
	 * 功能：废弃拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId)
	 * 返回值:boolean
	 */
	public boolean abandonVisitPlan(CEntityVisitPlan cEntityVisitPlan){
		boolean bisAbandon=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_FAILURE);
		return bisAbandon;
	}
	
	
	
	/**
	 * 序号：clientmodule:10
	 * 功能：撤销拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId)
	 * 	   cEntityVisitUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo){
		//拜访计划状态转为已撤销
		boolean bisUpdateVisitPlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_UNDO);
		//创建一条拜访计划撤销记录
		boolean bisSaveVisitUndo=iDaoFrame.saveVisitUndo(cEntityVisitPlan, cEntityVisitUndo);
		//如果与出差活动有过绑定的话，解除绑定
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
	 * 序号：clientmodule:11
	 * 功能：撤回拜访计划
	 * 参数：cEntityVisitUndo(VisitUndoId)
	 * 返回值:boolean
	 */
	public boolean recallVisitPlan(CEntityVisitUndo cEntityVisitUndo){
		//撤销拜访计划状态由已撤销转为已撤回
		boolean bisUpdateUndoType=iDaoFrame.updateVisitUndoStateByVisitUndoId(cEntityVisitUndo, MyConstant.VisitUndo.VISITUNDO_RECALL);
		//撤回的拜访计划转为原撤销前的状态
		CEntityVisitUndo findUndo=iDaoFrame.queryVisitUndoByVisitUndoId(cEntityVisitUndo);
		CEntityVisitPlan cEntityVisitPlan=findUndo.getcEntityVisitPlan();
		boolean bisUpdateVisitState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, findUndo.getM_iVisitUndoRecallType());
		//如果与出差活动有过绑定的话，建立绑定
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
	 * 序号：clientmodule:12
	 * 功能：删除客户
	 * 参数：cEntityClient(ClientId)
	 * 返回值:boolean
	 */
	public boolean delClient(CEntityClient cEntityClient){
		//客户状态修改为已删除
		boolean bisUpdateClientState=iDaoFrame.updateClientStateByClientId(cEntityClient, MyConstant.Client.CLIENT_DEL);
		//拜访计划状态转为已撤销（运行态的状态：未开始、执行中、未审核、已过期）
		CEntityVisitPlan cEntityVisitPlan=iDaoFrame.queryVisitPlanRunningStateByClientId(cEntityClient);
		boolean bisUpdateVisitPlanState=false;
		boolean bisSaveUndo=false;
		boolean bisUpdateBandType=false;
		if(cEntityVisitPlan!=null){
			bisUpdateVisitPlanState=iDaoFrame.updateVisitPlanStateByVisitPlanId(cEntityVisitPlan, MyConstant.VisitPlan.VISITPLAN_UNDO);
			
			//如果与出差绑定的话解除绑定
			if(cEntityVisitPlan.getM_iVisitBussinessBandState()==MyConstant.VisitPlan.VISITPLAN_BUSSINESSBAND){
				CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_VISITPLANTYPE).BussinessObjectId(cEntityVisitPlan.getM_iVisitPlanId()).BussinessBindType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_NOBAND).build();
				bisUpdateBandType=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
			}else if(cEntityVisitPlan.getM_iVisitBussinessBandState()==MyConstant.Mission.MISSION_BUSSINESSNOBAND){
				bisUpdateBandType=true;
			}
			
			//生成系统自带（“客户已被删除”）原因的撤销记录
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
	 * 序号：clientmodule:13
	 * 功能：进入未开始拜访计划 
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetNoStartVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanByEmployeeIdAndVisitPlanState(cEntityEmployee, MyConstant.VisitPlan.VISITPLAN_NOTSTART);
		return cEntityVisitPlanArray;
	}
	
	/**
	 * 序号：clientmodule:14
	 * 功能：进入执行中拜访计划
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetUnderwayVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanRunningStateByEmployeeId(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	/**
	 * 序号：clientmodule:15
	 * 功能：进入终止态拜访计划（未审核,已审核,已撤销,已失败）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetCompleteVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanCompleteByEmployeeIs(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	
	
	/**
	 * 序号：clientmodule:16
	 * 功能：查看客户详情
	 * 参数：cEntityClient(ClientId)
	 * 返回值:CEntityClient
	 */
	public CEntityClient GetClientInfo(CEntityClient cEntityClient){
		CEntityClient findResult=iDaoFrame.queryClientByClientId(cEntityClient);
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:17
	 * 功能：查看客户提交纪录
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientSubmitArray GetClientSubmit(CEntityEmployee cEntityEmployee){
		CEntityClientSubmitArray cEntityClientSubmitArray=iDaoFrame.queryAllClientSubmitForEmployeeId(cEntityEmployee);
		return cEntityClientSubmitArray;
	}
	/**
	 * 序号：clientmodule:18
	 * 功能：进入拜访客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	
	/**
	 * 序号：clientmodule:19
	 * 功能：得到所有的客户
	 * 参数：无
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetAllClient(){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClient();
		return cEntityClientArray;
	}
	
	/**
	 * 序号：clientmodule:20
	 * 功能：按员工号获得所有客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetAllEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	/**
	 * 序号：clientmodule:21
	 * 功能：修改客户详情
	 * 参数：CEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public boolean UpdateClient(CEntityClient cEntityClient){
		boolean bisUpdate=iDaoFrame.updateClient(cEntityClient);
		return bisUpdate;
	}
	/**
	 * 序号：clientmodule:22
	 * 功能：管理员得到员工分配的客户名
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:List<String>
	 */
	public List<String> GetAllEmployeeClientName(CEntityEmployee cEntityEmployee){
		List<String> result=iDaoFrame.queryAllClientNameByEmployeeId(cEntityEmployee);
		return result;
	}
	/**
	 * 序号：clientmodule:23
	 * 功能：管理员按客户名搜索所有的客户信息
	 * 参数：CEntityClient(ClientName)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetAllClientByClientName(CEntityClient cEntityClient){
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByClientName(cEntityClient);
		return cEntityClientArray;
	}
	/**
	 * 序号：clientmodule:24
	 * 功能：管理员取消分配
	 * 参数：CEntityClient(ClientName)
	 * 返回值:CEntityClientArray
	 */
	public boolean cancleClientDistuibution(CEntityClient cEntityClient){
		boolean bisUpdate=iDaoFrame.updateClientEmployeeIdNUll(cEntityClient);
		return bisUpdate;
	}
	/**
	 * 序号：clientmodule:25
	 * 功能：管理员获得员工的所有拜访计划含客户信息
	 * 参数：CEntityClient(ClientName)
	 * 返回值:CEntityClientArray
	 */
	public HashedMap getAllEmployeeVisitPlan(CEntityEmployee cEntityEmployee){
		HashedMap findResult=iDaoFrame.queryAllVisitPlanAndClientInfoByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:26
	 * 功能：管理员查看拜访计划详情
	 * 参数：CEntityVisitPlan(VisitPlanId)
	 * 返回值:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusionDetail(CEntityVisitPlan cEntityVisitPlan){
		CEntityVisitConclusion cEntityVisitConclusion=iDaoFrame.queryVisitConclusionNocheckByVisitPlanId(cEntityVisitPlan);
		return cEntityVisitConclusion;
	}
	/**
	 * 序号：clientmodule:27
	 * 功能：管理员查看该员工所有的拜访总结
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public LinkedMap getEmployeeVisitConclusion(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryVisitConclusionWithClientInfoByEmployeeId(cEntityEmployee);
		
		return findResult;
	}
	/**
	 * 序号：clientmodule:28
	 * 功能：管理员查看拜访计划详情
	 * 参数：CEntityVisitConclusion(VisitConclusionId)
	 * 返回值:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusion(CEntityVisitConclusion cEntityVisitConclusion){
		CEntityVisitConclusion findReuslt=iDaoFrame.queryVisitConclusionByVisitConclusionId(cEntityVisitConclusion);
		return findReuslt;
	}
	/**
	 * 序号：clientmodule:29
	 * 功能：管理员获得该员工所有的拜访延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitDealyArray
	 */
	public LinkedMap getEmployeeVisitDealy(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllVisitDelayByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:30
	 * 功能：管理员获得所有未分配的客户
	 * 参数：无
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray getNodistributionClient(){
		CEntityClient cEntityClient=new CEntityClient.Builder().ClientState(MyConstant.Client.CLIENT_UNDISTRIBUTED).build();
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByClientState(cEntityClient);
		return cEntityClientArray;
	}
	
	/**
	 * 序号：clientmodule:31
	 * 功能：管理员获得员工分配客户情况
	 * 参数：无
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getClientStatisticalNumber(){
		List findResult=iDaoFrame.queryClientNumberOfEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:32
	 * 功能：管理员获得分配客户信息
	 * 参数：无
	 * 返回值:HashedMap
	 */
	public HashedMap getClientDistribution() {
		HashedMap findResult=iDaoFrame.queryClientDistributionWithEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:33
	 * 功能：管理员获得未审核客户信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getNoCheckClient(){
		LinkedMap findResult=iDaoFrame.queryAllNocheckClientWithClientInfoAndEmployeeInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:34
	 * 功能：管理员获得所有已删客户信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public CEntityClientArray queryDelClient(){
		CEntityClient cEntityClient=new CEntityClient.Builder().ClientState(MyConstant.Client.CLIENT_DEL).build();
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByClientState(cEntityClient);
		return cEntityClientArray;
	}
	
	/**
	 * 序号：clientmodule:35
	 * 功能：判断是否有未审核的客户
	 * 参数：无
	 * 返回值:boolean(true:有未审核的,false:没有)
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
	 * 序号：clientmodule:36
	 * 功能：得到所有未开始，执行中的拜访计划
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitPlanAllRunning(){
		LinkedMap findResult=iDaoFrame.queryAllVisitPlanAllRunning();
		return findResult;
	}
	
	
	/**
	 * 序号：clientmodule:37
	 * 功能：得到未分配客户的客户号，客户名，客户公司
	 * 参数：无
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getClientUndistriInfo(){
		List findResult=iDaoFrame.queryClientUnDistributionInfo();
		return findResult;
	}
	
	
		
	/**
	 * 序号：clientmodule:38
	 * 功能：得到所有未审核，已过期的拜访计划
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitPlanWaitDeal(){
		LinkedMap findResult=iDaoFrame.queryAllVisitPlanAllWaitDeal();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:39
	 * 功能：获取所有员工的已审核，已失败拜访信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitPlanComplete(){
		LinkedMap findResult=iDaoFrame.queryAllVisitPlanComplete();
		return findResult;
	}
	/**
	 * 序号：clientmodule:40
	 * 功能：获取所有未删员工的撤销拜访信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitUndoInfo(){
		LinkedMap findResult=iDaoFrame.queryAllVisitUndoWithInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:41
	 * 功能：获取所有未删员工的拜访总结及信息
	 * 参数：无
	 * 返回值:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	public LinkedMap getAllVisitConclusionInfo(){
		LinkedMap findResult=iDaoFrame.queryAllVisitConclusionInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:42
	 * 功能：获取所有未删员工的拜访延期信息
	 * 参数：无
	 * 返回值:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitDelay)
	 */
	public LinkedMap getAllVisitDelayInfo(){
		LinkedMap findResult=iDaoFrame.queryAllVisitDelayInfo();
		return findResult;
	}
	
	
	/**
	 * 序号：clientmodule:43
	 * 功能：判断是否还有未处理的拜访计划
	 * 参数：无
	 * 返回值:boolean 
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
	 * 序号：clientmodule:44
	 * 功能：根据员工号获取其绑定的拜访计划信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray 
	 */
	public CEntityVisitPlanArray getBindVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryVisitPlanBindByEmployee(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	
	//若设置循环拜访的话，获得下一次的拜访开始时间，仅在本类中使用
	@SuppressWarnings("deprecation")
	private static String getCycleStartTime(CEntityVisitPlan cEntityVisitPlan){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		String m_sOldStartTime=cEntityVisitPlan.getM_sVisitPlanStartTime();
		Date m_date=null;
		int weekdays=7;//一星期的天数
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
	
	//若设置循环拜访的话，获得下一次的拜访结束时间，仅在本类中使用
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
	//获得当日日期，仅在本类中使用
	private static String getNewPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result=sFormat.format(resultDate);
		return result;
		
	}
}
