package com.mm.test;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.entity.CEntityMissionDelay;
import com.mm.entity.CEntityMissionUndo;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.tool.MyConstant;

@Component("bllTest")
public class BllTest {
	private IBllFrame iBllFrame;

	@Resource(name="cBllFrameImpl")
	public void setiBllFrame(IBllFrame iBllFrame) {
		this.iBllFrame = iBllFrame;
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
		"applicationContext.xml");
		BllTest tt=(BllTest)ctx.getBean("bllTest");
		
		
		/*
		 *------------------------------ 任务模块 missionmodule----------------------------
		 */
		//missionmodule:1创建外勤任务
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionContent("blltestone").MissionDeadline("blltestone").MissionDelayState(0).MissionPubdate("blltestone").build();
//		System.out.println(tt.createMission(cEntityEmployee, cEntityMission));
		
		//missionmodule:2外勤人员接受任务
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		System.out.println(tt.acceptMission(cEntityMission));
		
		//missionmodule:3外勤人员提交任务及总结
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		CEntityMissionConclusion cEntityMissionConclusion=new CEntityMissionConclusion.Builder().MissionAccessoryPath("pathone").MissionCheck(0).MissionSubmitTime("timeone").MissionSummary("summaryOne").build();
//		System.out.println(tt.submitMission(cEntityMission, cEntityMissionConclusion));
		
		
		//missionmodule:4审核任务
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		CEntityMissionConclusion cEntityMissionConclusion=new CEntityMissionConclusion.Builder().MissionConclusionId(2).build();
//		System.out.println(tt.checkMissionConclusion(cEntityMission, cEntityMissionConclusion, MyConstant.MissionConclusion.MISSIONCONCLUSION_PASS));
		
		//missionmodule:5撤销任务
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		CEntityMissionUndo cEntityMissionUndo=new CEntityMissionUndo.Builder().MissionUndoReason("reasonone").MissionUndoTime("timeone").build();
//		System.out.println(tt.undoMission(cEntityMission, cEntityMissionUndo));

		//missionmodule:6撤回任务
//		CEntityMissionUndo cEntityMissionUndo=new CEntityMissionUndo.Builder().MissionUndoId(2).build();
//		System.out.println(tt.recallMission(cEntityMissionUndo));
		
		//missionmodule:7延期任务
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).MissionDeadline("deadline").build();
//		CEntityMissionDelay cEntityMissionDelay=new CEntityMissionDelay.Builder().MissionDelayDeadline("deadline").MissionDelayTime("time").build();
//		System.out.println(tt.delayMission(cEntityMission, cEntityMissionDelay));

		//missionmodule:8废弃任务
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(5).build();
//		System.out.println(tt.abandonMission(cEntityMission));

	
		/*
		 *------------------------------ 客户模块 clientmodule----------------------------
		 */
		//clientmodule:1创建客户
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientAddress("addressbll").ClientArea("areabll").ClientCompany("companybll").ClientName("namebll").ClientPhone("phonebll").build();
//		System.out.println(tt.createClient(cEntityClient));
	
		//clientmodule:2分配客户
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(4).build();
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanPubdate("pubtime").VisitPlanStartTime("starttime").VisitPlanEndTime("endtime").build();
//		System.out.println(tt.distributionClient(cEntityClient, cEntityEmployee, cEntityVisitPlan));
		
		//clientmodule:3提交拜访
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(2).build();
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion.Builder().VisitAccessoryPath("path").VisitCommand("conmmd").VisitSubmitTime("time").VisitSummary("summ").build();
//		System.out.println(tt.submitVisit(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion));
		
		
		//clientmodule:4审核客户拜访
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(2).build();
//		CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion.Builder().VisitConclusionId(3).build();
//		System.out.println(tt.checkVisitConclusion(cEntityVisitPlan, cEntityVisitConclusion, MyConstant.VisitConclusion.VISITCONCLUSION_PASS));

		//clientmodule:5提交客户
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(2).build();
//		CEntityClientSubmit cEntityClientSubmit=new CEntityClientSubmit.Builder().ClientSubmitState(0).ClientSubmitTime("timeone").build();
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientAddress("addressOne").ClientArea("areaone").ClientCompany("companyone").ClientName("nameone").ClientPhone("phoneone").ClientState(0).build();
//		System.out.println(tt.submitCliet(cEntityEmployee, cEntityClient, cEntityClientSubmit));
		
		//clientmodule:6客户考察
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(5).build();
//		System.out.println(tt.checkClient(cEntityClient, MyConstant.ClientSubmit.CLIENTSUBMIT_PASS));
	
		//clientmodule:7循环拜访
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanCycleType(MyConstant.VisitPlanCycleType.VISITPLANCYCLETYPE_MONTH).VisitPlanCycleNumber(2).VisitPlanDays(3).VisitPlanId(2).build();
//		System.out.println(tt.setupVisitCycle(cEntityVisitPlan));
		
		//clientmodule:8延期拜访计划
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(2).VisitPlanEndTime("2015/10/16").build();
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		System.out.println(tt.delayVisitPlan(cEntityVisitPlan, cEntityEmployee));
	
		//clientmodule:9废弃拜访计划
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(2).build();
//		System.out.println(tt.abandonVisitPlan(cEntityVisitPlan));
		
		//clientmodule:10撤销拜访计划
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(2).build();
//		CEntityVisitUndo cEntityVisitUndo=new CEntityVisitUndo.Builder().VisitUndoReason("reson").VisitUndoTime("time").build();
//		System.out.println(tt.undoVisitPlan(cEntityVisitPlan, cEntityVisitUndo));
	
		//clientmodule:11撤回拜访计划
//		CEntityVisitUndo cEntityVisitUndo=new CEntityVisitUndo.Builder().VisitUndoId(2).build();
//		System.out.println(tt.recallVisitPlan(cEntityVisitUndo));
		
		//clientmodule:12删除客户
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(4).build();
//		System.out.println(tt.delClient(cEntityClient));
	}
	
	
	/*
	 *------------------------------ 任务模块 missionmodule----------------------------
	 */
	
	//missionmodule:1创建外勤任务
	public boolean createMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission) {
		boolean bisCreate=iBllFrame.createMission(cEntityEmployee, cEntityMission);
		return bisCreate;
	}
	
	//missionmodule:2外勤人员接受任务
	public boolean acceptMission(CEntityMission cEntityMission) {
		boolean bisAccept=iBllFrame.acceptMission(cEntityMission);
		return bisAccept;
	}
	
	//missionmodule:3外勤人员提交任务及总结
	public boolean submitMission(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion) {
		boolean bisSubmit=iBllFrame.submitMission(cEntityMission, cEntityMissionConclusion);
		return bisSubmit;
	}
	
	//missionmodule:4审核任务
	public boolean checkMissionConclusion(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion,int OperateState ) {
		boolean bisCheck=iBllFrame.checkMissionConclusion(cEntityMission, cEntityMissionConclusion, OperateState);
		return bisCheck;
	}
	//missionmodule:5撤销任务
	public boolean undoMission(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		boolean bisUndo=iBllFrame.undoMission(cEntityMission, cEntityMissionUndo);
		
		return bisUndo;
	}
	
	//missionmodule:6撤回任务
	public boolean recallMission(CEntityMissionUndo cEntityMissionUndo) {
		boolean bisRecall=iBllFrame.recallMission(cEntityMissionUndo);
		return bisRecall;
	}
	
	//missionmodule:7延期任务
	public boolean delayMission(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay) {
		boolean bisDelay=iBllFrame.delayMission(cEntityMission, cEntityMissionDelay);
		return bisDelay;
	}
	
	//missionmodule:8废弃任务
	public boolean abandonMission(CEntityMission cEntityMission){
		boolean bisAbandon=iBllFrame.abandonMission(cEntityMission);
		return bisAbandon;
		
	}
	
	/*
	 *------------------------------ 客户模块 clientmodule----------------------------
	 */
	
	//clientmodule:1创建客户
	public boolean createClient(CEntityClient cEntityClient){
		boolean bisCreate=iBllFrame.createClient(cEntityClient);
		return bisCreate;
		
	}
	
	//clientmodule:2分配客户
	public boolean distributionClient(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan) {
		boolean bisDistribution=iBllFrame.distributionClient(cEntityClient, cEntityEmployee, cEntityVisitPlan);
		return bisDistribution;
	}
	
	
	//clientmodule:3提交拜访
	public boolean submitVisit(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion){
		boolean bisSubmit=iBllFrame.submitVisit(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		return bisSubmit;
	}
	 
	//clientmodule:4审核客户拜访
	public boolean checkVisitConclusion(CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion,int OperateState) {
		boolean bisCheck=iBllFrame.checkVisitConclusion(cEntityVisitPlan, cEntityVisitConclusion, OperateState);
		return bisCheck;
	}
	//clientmodule:5提交客户
	public boolean submitCliet(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient) {
		boolean bisSubmit=iBllFrame.submitCliet(cEntityEmployee, cEntityClient );
		return bisSubmit;
	}
	
	//clientmodule:6客户考察
	public boolean checkClient(CEntityClient cEntityClient,int OperateState){
		boolean bisCheck=iBllFrame.checkClient(cEntityClient, OperateState);
		return bisCheck;
	}
	
	//clientmodule:7循环拜访
	public boolean setupVisitCycle(CEntityVisitPlan cEntityVisitPlan) {
		boolean bisSetup=iBllFrame.setupVisitCycle(cEntityVisitPlan);
		return bisSetup;
	}
	
	//clientmodule:8延期拜访计划
	public boolean delayVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityEmployee cEntityEmployee){
		boolean bisDelay=iBllFrame.delayVisitPlan(cEntityVisitPlan, cEntityEmployee);
		return bisDelay;
	}
	
	//clientmodule:9废弃拜访计划
	public boolean abandonVisitPlan(CEntityVisitPlan cEntityVisitPlan){
		boolean bisAbandon=iBllFrame.abandonVisitPlan(cEntityVisitPlan);
		return bisAbandon;
	}
	
	//clientmodule:10撤销拜访计划
	public boolean undoVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo) {
		boolean bisUndo=iBllFrame.undoVisitPlan(cEntityVisitPlan, cEntityVisitUndo);
		return bisUndo;
	}
	
	//clientmodule:11撤回拜访计划
	public boolean recallVisitPlan(CEntityVisitUndo cEntityVisitUndo) {
		boolean bisRecall=iBllFrame.recallVisitPlan(cEntityVisitUndo);
		return bisRecall;
	}
	
	//clientmodule:12删除客户
	public boolean delClient(CEntityClient cEntityClient){
		boolean bisDel=iBllFrame.delClient(cEntityClient);
		return bisDel;
	}
}
