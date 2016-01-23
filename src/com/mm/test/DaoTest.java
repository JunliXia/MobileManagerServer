package com.mm.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityAdministrator;
import com.mm.entity.CEntityAttendance;
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.entity.CEntityBussinessBadrecord;
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
import com.mm.entityarray.CEntityBussinessActivityArray;
import com.mm.entityarray.CEntityBussinessArray;
import com.mm.entityarray.CEntityBussinessBadrecordArray;
import com.mm.entityarray.CEntityClientArray;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.entityarray.CEntityMissionConclusionArray;
import com.mm.entityarray.CEntityMissionDelayArray;
import com.mm.entityarray.CEntityMissionUndoArray;
import com.mm.entityarray.CEntityNoticeArray;
import com.mm.entityarray.CEntitySuggestArray;
import com.mm.entityarray.CEntityVisitConclusionArray;
import com.mm.entityarray.CEntityVisitDealyArray;
import com.mm.entityarray.CEntityVisitPlanArray;
import com.mm.entityarray.CEntityVisitUndoArray;
import com.mm.tool.MyConstant;

@Component("daoTest")
public class DaoTest {

	private IDaoFrame iDaoFrame;

	@Resource(name = "cDaoFrameImpl")
	public void setiDaoFrame(IDaoFrame iDaoFrame) {
		this.iDaoFrame = iDaoFrame;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
		"applicationContext.xml");
		DaoTest tt = (DaoTest) ctx.getBean("daoTest");
		
		
		/*
		 *------------------------------ ����Ա administrator----------------------------
		 */
		
		// administrator:1����һ������Ա�ʺ�
//		CEntityAdministrator cEntityAdministrator = new CEntityAdministrator(
//				"accountone", "passwordone");
//		System.out.println(tt.saveAdministrator(cEntityAdministrator));
	
	
		// administrator:2���ʺ������ѯ����Ա
//		CEntityAdministrator cEntityAdministrator = new CEntityAdministrator.Builder().AdministratorAccount("accountone").AdministratorPassword("passwordone").build();
//		System.out.println(tt.queryAdministrator(cEntityAdministrator).toString());
		

		/*
		 * -------------------------------Ա��employee----------------------------------
		 */	
		//employee:1����һ��Ա��
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeName("employeethree").EmployeePassword("passwordthree").build();
//		System.out.println(tt.saveEmployee(cEntityEmployee));
		
		//employee:2��ȡ����δɾԱ��
//		CEntityEmployeeArray cEntityEmployeeArray=tt.queryAllEmployee();
//		for(int i=0;i<cEntityEmployeeArray.getSize();i++){
//			System.out.println(cEntityEmployeeArray.getItem(i).toString());
//		}
		
		//employee:3�޸�Ա�����
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).EmployeeType(5).build();
//		System.out.println(tt.updateEmployeeType(cEntityEmployee));

		//employee:4���ʺ��������Ա��
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeAccount("accountone").EmployeePassword("passwordone").build();
//		System.out.println(tt.queryEmployeeByAccountAndPassword(cEntityEmployee).toString());
		
		//employee:5���ʺŲ���Ա��
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeAccount("accountone").build();
//		System.out.println(tt.queryEmployeeByAccount(cEntityEmployee));
		
		//employee:6��Ա�����������Ա��
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeType(1).build();
//		CEntityEmployeeArray cEntityEmployeeArray=tt.queryAllEmployeeByType(cEntityEmployee);
//		System.out.println(cEntityEmployeeArray.toString());

		
		/*
		 * -------------------------------����mission----------------------------------
		 */
		//mission:1 ��������
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionContent("contentOne").MissionPubdate("pubdateone").MissionDeadline("deadlineone").MissionState(0).MissionDelayState(0).build();
//		System.out.println(tt.saveMission(cEntityEmployee, cEntityMission));
		
		//mission:2�޸�����״̬
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(20).MissionState(2).build();
//		System.out.println(tt.updateMissionState(cEntityMission,MyConstant.Mission.MISSION_UNDERWAY));

		//mission:3�޸���������״̬
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).MissionDelayState(2).build();
//		System.out.println(tt.updateMissionDelayState(cEntityMission));

		//mission:4����Ա���Ż�����е�����
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		System.out.println(tt.queryAllMissionByEmployeeId(cEntityEmployee).getSize());
	
		//mission:5������״̬�����������
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionState(1).build();
//		System.out.println(tt.queryAllMissionByMissionType(cEntityMission).getSize());
	
	
		/*
		 * -------------------------------�������� missiondelay----------------------------------
		 */
		
		//missiondealy:1 ����һ����������
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		CEntityMissionDelay cEntityMissionDelay=new CEntityMissionDelay.Builder().MissionDelayDeadline("deadlineone").MissionDelayTime("timeone").build();
//		System.out.println(tt.saveMissionDealy(cEntityMission, cEntityMissionDelay));
	
		//missiondealy:2������Ż��������������
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		System.out.println(tt.queryAllMissionDelayByMissionId(cEntityMission).getSize());
		
		
		/*
		 * -------------------------------�����ܽ� missionconclusion----------------------------------
		 */	
		
		//missionconclusion:1����һ�������ܽ�
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		CEntityMissionConclusion cEntityMissionConclusion=new CEntityMissionConclusion.Builder().MissionAccessoryPath("pathone").MissionCheck(0).MissionSubmitTime("timeone").MissionSummary("summaryOne").build();
//		System.out.println(tt.savaMissionConclusion(cEntityMission, cEntityMissionConclusion));
		
		//missionconclusion:2������Ż�����������ܽ�
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		System.out.println(tt.queryAllMissonConclusionByMissionId(cEntityMission).getSize());
		
		
		/*
		 * -------------------------------������missionundo----------------------------------
		 */	
		
		//missionundo:1����һ��������
//		CEntityMission cEntityMission=new CEntityMission.Builder().MissionId(2).build();
//		CEntityMissionUndo cEntityMissionUndo=new CEntityMissionUndo.Builder().MissionUndoReason("reasonone").MissionUndoTime("undotime").MissionUndoType(0).build();
//		System.out.println(tt.saveMissionUndo(cEntityMission, cEntityMissionUndo));
		
		//missionundo:2������е�������
//		CEntityMissionUndoArray cEntityMissionUndoArray=tt.queryAllMissionUndo();
//		System.out.println(cEntityMissionUndoArray.getSize());
		
		//missionundo:3�޸ĳ�������
//		CEntityMissionUndo cEntityMissionUndo=new CEntityMissionUndo.Builder().MissionUndoId(1).MissionUndoType(2).build();
//		System.out.println(tt.updateMissionUndoType(cEntityMissionUndo));
	
		/*
		 * -------------------------------�ͻ�client----------------------------------
		 */
		
		//client:1���ӿͻ�
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientAddress("addressOne").ClientArea("areaone").ClientCompany("companyone").ClientName("nameone").ClientPhone("phoneone").ClientState(0).build();
//		System.out.println(tt.saveClient(cEntityClient));
	
		//client:2��Ա���Ż�����еĿͻ�
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(2).build();
//		System.out.println(tt.queryAllClientByEmployeeId(cEntityEmployee).getSize());
		
		
		//client:3�޸Ŀͻ�Ա����
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(2).build();
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(1).build();
//		System.out.println(tt.updateClientEmployeeId(cEntityClient, cEntityEmployee));
		
		//client:4���ͻ�״̬������еĿͻ�
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientState(0).build();
//		System.out.println(tt.queryAllClientByClientState(cEntityClient));
		
		
		/*
		 * -------------------------------�ͻ��ύclientsubmit----------------------------------
		 */	
		//clientsubmit:1����һ���ͻ��ύ��ͬʱ������һ���ͻ���
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(2).build();
//		CEntityClientSubmit cEntityClientSubmit=new CEntityClientSubmit.Builder().ClientSubmitState(0).ClientSubmitTime("timeone").build();
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientAddress("addressOne").ClientArea("areaone").ClientCompany("companyone").ClientName("nameone").ClientPhone("phoneone").ClientState(0).build();
//		System.out.println(tt.saveClientSubmit(cEntityClient, cEntityEmployee, cEntityClientSubmit));
		
		//clientsubmit:2���ύ״̬������еĿͻ��ύ
//		CEntityClientSubmit cEntityClientSubmit=new CEntityClientSubmit.Builder().ClientSubmitState(0).build();
//		System.out.println(tt.queryAllClientSubmitByClientSubmitState(cEntityClientSubmit));

	
		/*
		 * -------------------------------�ݷüƻ�visitplan----------------------------------
		 */	
		//visitplan:1���Ӱݷüƻ�
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(2).build();
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanCycle(0).VisitPlanCycleNumber(1).build();
//		System.out.println(tt.saveVisitPlan(cEntityClient, cEntityEmployee, cEntityVisitPlan));

		
		//visitplan:2��Ա���Ż�����еİݷüƻ�
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		System.out.println(tt.queryAllVisitPlanByEmployeeId(cEntityEmployee));
		
		/*
		 * ------------------------------�ݷ��ܽ� visitconclusion-------------------------------------
		 */	
		
		//visitconclusion:1���Ӱݷ��ܽ�
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(1).build();
//		CEntityVisitConclusion cEntityVisitConclusion=new CEntityVisitConclusion.Builder().VisitAccessoryPath("pathone").VisitCheck(0).VisitCommand("commandone").VisitSubmitTime("subtimewone").VisitSummary("summaryone").build();
//		System.out.println(tt.saveVisitConclusion(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion));
		
		//visitconclusion:2��Ա���Ż�����еİݷ��ܽ�
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		System.out.println(tt.queryAllVisitConclusionByEmployeeId(cEntityEmployee));
		
		/*
		 * ------------------------------�ݷó��� visitconclusion-------------------------------------
		 */	
		
		//visitundo:1����һ���ݷó���
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(1).build();
//		CEntityVisitUndo cEntityVisitUndo=new CEntityVisitUndo.Builder().VisitUndoReason("ressonone").VisitUndoTime("timeone").VisitUndoType(0).build();
//		System.out.println(tt.saveVisitUndo(cEntityVisitPlan, cEntityVisitUndo));
	
		//visitundo:2������еİݷó���
//		CEntityVisitUndoArray cEntityVisitUndoArray=tt.queryAllVisitUndo();
//		System.out.println(cEntityVisitUndoArray.getSize());

		/*
		 * -----------------------------�ݷ�����visitdelay-------------------------------------
		 */
		//visitdealy:1����һ���ݷ�����
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId(1).build();
//		CEntityVisitDelay cEntityVisitDelay=new CEntityVisitDelay.Builder().VisitDelayDeadline("deadlieone").VisitDelayTime("timeone").build();
//		System.out.println(tt.saveVisitDealy(cEntityEmployee, cEntityVisitPlan, cEntityVisitDelay));
		
		//visitdealy:2������аݷ�����
//		CEntityVisitDealyArray cEntityVisitDealyArray=tt.queryAllVisitDelay();
//		System.out.println(cEntityVisitDealyArray.getSize());

		/*
		 * -----------------------------����bussiness------------------------------------
		 */
		//bussiness:1����һ������
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessContent("contentone").BussinessSideAddress("addressone").BussinessState(0).build();
//		System.out.println(tt.saveBussiness(cEntityEmployee, cEntityBussiness));
		
		//bussiness:2��Ա���Ż�����еĳ���
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntityBussinessArray cEntityBussinessArray=tt.queryAllBussinessByEmployeeId(cEntityEmployee);
//		System.out.println(cEntityBussinessArray.getSize());
	
		/*
		 * -----------------------------����bussinessactivity------------------------------------
		 */
		//bussinessactivity:1����һ������
//		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId(1).build();
//		CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(0).BussinessBindType(0).BussinessObjectId(1).build();
//		System.out.println(tt.saveBussinessActivity(cEntityBussinessActivity, cEntityBussiness));
		
		//bussinessactivity:2������Ż�����еĳ���
//		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId(1).build();
//		System.out.println(tt.queryAllBussinessActivityByBussinessId(cEntityBussiness).getSize());
	
		/*
		 * -----------------------------�������¼bussinessbadrecord------------------------------------
		 */
		//bussinessbadrecord:1����һ���������¼
//		CEntityBussiness cEntityBussiness=new CEntityBussiness.Builder().BussinessId(1).build();
//		CEntityBussinessBadrecord cEntityBussinessBadrecord=new CEntityBussinessBadrecord.Builder().BussinessBadrecordReason("reasonone").BussinessBadrecordTime("timeone").build();
//		System.out.println(tt.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord));
		
	
		//bussinessbadrecord:2������еĳ������¼
//		CEntityBussinessBadrecordArray cEntityBussinessBadrecordArra=tt.queryAllBussinessBadrecord();
//		System.out.println(cEntityBussinessBadrecordArra.getSize());
	
		/*
		 * ----------------------------����attendance------------------------------------
		 */
		
		
		//attendance:1����һ������
		
		
		/*
		 * ----------------------------֪ͨ notice------------------------------------
		 */
		//notice:1����һ��֪ͨ
//		for(int i=0;i<100;i++){
//		CEntityNotice cEntityNotice=new CEntityNotice.Builder().NoticeContent("contentone").NoticeTime("timeone").NoticeTitle("titleone").build();
//		System.out.println(tt.saveNotice(cEntityNotice));
//		}
//		//notice:2���ȫ��֪ͨ
//		long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��
//
//		CEntityNoticeArray cEntityNoticeArray=tt.queryAllNotice();
//		System.out.println(cEntityNoticeArray.getSize());
//		
//		long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
//
//		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");
//
//		
//		
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		long start2Time=System.currentTimeMillis();   //��ȡ��ʼʱ��
//		CEntityNoticeArray cEntityNoticeArray2=tt.queryAllNotice();
//		System.out.println(cEntityNoticeArray2.getSize());
//		
//
//		long end2Time=System.currentTimeMillis(); //��ȡ����ʱ��
//
//		System.out.println("��������ʱ�䣺 "+(end2Time-start2Time)+"ms");
//		
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		long start3Time=System.currentTimeMillis();   //��ȡ��ʼʱ��
//		CEntityNoticeArray cEntityNoticeArray3=tt.queryAllNotice();
//		System.out.println(cEntityNoticeArray3.getSize());
//		
//
//		long end3Time=System.currentTimeMillis(); //��ȡ����ʱ��
//
//		System.out.println("��������ʱ�䣺 "+(end3Time-start3Time)+"ms");
//		

		/*
		 * ----------------------------���� suggest----------------------------------
		 */
		//suggest:1����һ������
//		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(1).build();
//		CEntitySuggest cEntitySuggest=new CEntitySuggest.Builder().SuggestContent("contentone").SuggestTime("timeone").build();
//		System.out.println(tt.saveSuggest(cEntityEmployee, cEntitySuggest));

		//uggest:2������еĽ���
//		CEntitySuggestArray cEntitySuggestArray=tt.queryAllSuggest();
//		System.out.println(cEntitySuggestArray.getSize());
		
		
		System.out.println(tt.sd());
		
	}
	
	
	/*
	 *------------------------------ ����Ա administrator----------------------------
	 */
	
	
	
	
	public boolean sd(){
		CEntityClient cEntityClient=new CEntityClient.Builder().ClientId(2).build();
		boolean bis=iDaoFrame.updateClient(cEntityClient);
		return bis;
	}
	
	// administrator:1����һ������Ա�ʺ�
	public boolean saveAdministrator(CEntityAdministrator cEntityAdministrator) {
		boolean bisSave = iDaoFrame.saveAdministrator(cEntityAdministrator);
		return bisSave;
	}
	
	// administrator:2���ʺ������ѯ����Ա
	public CEntityAdministrator queryAdministrator(CEntityAdministrator cEntityAdministrator){
		CEntityAdministrator result=iDaoFrame.queryAdministrator(cEntityAdministrator);
		return result;
	}
	
	
	
	/*
	 * -------------------------------Ա��employee----------------------------------
	 */	
	//employee:1����һ��Ա��
	public boolean saveEmployee(CEntityEmployee cEntityEmployee) {
		boolean bisSave=iDaoFrame.saveEmployee(cEntityEmployee);
		return bisSave;
		
	}
	
	//employee:2��ȡ����δɾԱ��
	public CEntityEmployeeArray queryAllEmployee() {
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployee();
		return cEntityEmployeeArray;
	}
	
	//employee:3�޸�Ա�����
	public boolean updateEmployeeType(CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=iDaoFrame.updateEmployeeType(cEntityEmployee);
		return bisUpdate;
	}
	
	//employee:4���ʺ��������Ա��
	public CEntityEmployee queryEmployeeByAccountAndPassword(CEntityEmployee cEntityEmployee) {
		CEntityEmployee result=iDaoFrame.queryEmployeeByAccountAndPassword(cEntityEmployee);
		return result;
	}
	
	//employee:5���ʺŲ���Ա��
	public CEntityEmployee queryEmployeeByAccount(CEntityEmployee cEntityEmployee) {
		CEntityEmployee result=iDaoFrame.queryEmployeeByAccount(cEntityEmployee);
		return result;
	}
	
	//employee:6��Ա�����������Ա��
	public CEntityEmployeeArray queryAllEmployeeByType(CEntityEmployee cEntityEmployee) {
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployeeByType(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	
	
	/*
	 * -------------------------------����mission----------------------------------
	 */
	
	
	//mission:1 ��������
	public boolean saveMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission) {
		boolean bisSave=iDaoFrame.saveMission(cEntityEmployee, cEntityMission);
		return bisSave;
	}
	
	//mission:2�޸�����״̬
	public boolean updateMissionState(CEntityMission cEntityMission,int OperateState) {
		boolean bisUpdate=iDaoFrame.updateMissionState(cEntityMission,OperateState);
		return bisUpdate;
	}
	
	//mission:3�޸�����״̬
	public boolean updateMissionDelayState(CEntityMission cEntityMission){
		boolean bisUpdate=iDaoFrame.updateMissionDelayState(cEntityMission);
		return bisUpdate;
	}
//	//mission:4����Ա���Ż����������
//	public CEntityMissionArray queryAllMissionByEmployeeId(CEntityEmployee cEntityEmployee) {
//		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByEmployeeId(cEntityEmployee);
//		return cEntityMissionArray;
//	}
//	
	//mission:5������״̬�����������
//	public CEntityMissionArray queryAllMissionByMissionType(CEntityMission cEntityMission) {
//		CEntityMissionArray cEntityMissionArray=iDaoFrame.queryAllMissionByMissionType(cEntityMission);
//		return cEntityMissionArray;
//	}
	
	
	/*
	 * -------------------------------�������� missiondelay----------------------------------
	 */	
	
	//missiondealy:1 ����һ����������
	public boolean saveMissionDealy(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay) {
		boolean bisSave=iDaoFrame.saveMissionDealy(cEntityMission, cEntityMissionDelay);
		return bisSave;
	}
	
	//missiondealy:2������Ż��������������
	public CEntityMissionDelayArray queryAllMissionDelayByMissionId(CEntityMission cEntityMission) {
		CEntityMissionDelayArray cEntityMissionDelayArray=iDaoFrame.queryAllMissionDelayByMissionId(cEntityMission);
		return cEntityMissionDelayArray;
	}
	
	
	/*
	 * -------------------------------�����ܽ� missionconclusion----------------------------------
	 */	
	//missionconclusion:1����һ�������ܽ�
	public boolean saveMissionConclusion(CEntityMission cEntityMission, CEntityMissionConclusion cEntityMissionConclusion) {
		boolean bisSave=iDaoFrame.saveMissionConclusion(cEntityMission, cEntityMissionConclusion);
		return bisSave;
	
	}
	
	//missionconclusion:2������Ż�����������ܽ�
	public CEntityMissionConclusionArray queryAllMissonConclusionByMissionId(CEntityMission cEntityMission) {
//		CEntityMissionConclusionArray cEntityMissionConclusionArray=iDaoFrame.queryAllMissonConclusionByMissionId(cEntityMission);
		return null;
	}
	
	
	/*
	 * -------------------------------������missionundo----------------------------------
	 */	
	
	//missionundo:1����һ��������
	public boolean saveMissionUndo(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		boolean bisSave=iDaoFrame.saveMissionUndo(cEntityMission, cEntityMissionUndo);
		return bisSave;
	}
	
	//missionundo:2������е�������
	public CEntityMissionUndoArray queryAllMissionUndo() {
		CEntityMissionUndoArray cEntityMissionUndoArray=iDaoFrame.queryAllMissionUndo();
		return cEntityMissionUndoArray;
	}
	
	//missionundo:3�޸ĳ�������
	public boolean updateMissionUndoType(CEntityMissionUndo cEntityMissionUndo,int OperateState) {
		boolean bisUpdate=iDaoFrame.updateMissionUndoType(cEntityMissionUndo,OperateState);
		return bisUpdate;
	}
	/*
	 * -------------------------------�ͻ�client----------------------------------
	 */	
	
	
	//client:1���ӿͻ�
	public  boolean saveClient(CEntityClient cEntityClient) {
		boolean bisSave=iDaoFrame.saveClient(cEntityClient);
		return bisSave;
	}
	
	//client:2��Ա���Ż�����еĿͻ�
	public CEntityClientArray queryAllClientByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByEmployeeId(cEntityEmployee);
		return cEntityClientArray;
	}
	
	//client:3�޸Ŀͻ�Ա����
	public boolean updateClientEmployeeId(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee) {
		boolean bisUpdate=iDaoFrame.updateClientEmployeeIdAndClientState(cEntityClient, cEntityEmployee);
		return bisUpdate;
	}
	
	//client:4���ͻ�״̬������еĿͻ�
	public CEntityClientArray queryAllClientByClientState(CEntityClient cEntityClient) {
		CEntityClientArray cEntityClientArray=iDaoFrame.queryAllClientByClientState(cEntityClient);
		return cEntityClientArray;
	}
	
	/*
	 * -------------------------------�ͻ��ύclientsubmit----------------------------------
	 */
	
	//clientsubmit:1����һ���ͻ��ύ��ͬʱ������һ���ͻ���
	public boolean saveClientSubmit(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityClientSubmit cEntityClientSubmit) {
		boolean bisSave=iDaoFrame.saveClientSubmit(cEntityClient, cEntityEmployee, cEntityClientSubmit);
		return bisSave;
	}
	//clientsubmit:2���ύ״̬������еĿͻ��ύ
	public CEntityClientSubmitArray queryAllClientSubmitByClientSubmitState(CEntityClientSubmit cEntityClientSubmit) {
		CEntityClientSubmitArray cEntityClientSubmitArray=iDaoFrame.queryAllClientSubmitByClientSubmitState(cEntityClientSubmit);
		return cEntityClientSubmitArray;
	}
	
	
	/*
	 * -------------------------------�ݷüƻ�visitplan----------------------------------
	 */	
	//visitplan:1���Ӱݷüƻ�
	public boolean saveVisitPlan(CEntityClient cEntityClient ,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan) {
		boolean bisSave=iDaoFrame.saveVisitPlan(cEntityClient, cEntityEmployee, cEntityVisitPlan);
		return bisSave;
	}
	
	//visitplan:2��Ա���Ż�����еİݷüƻ�
	public CEntityVisitPlanArray queryAllVisitPlanByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitPlanArray cEntityVisitPlanArray=iDaoFrame.queryAllVisitPlanByEmployeeId(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	/*
	 * ------------------------------�ݷ��ܽ� visitconclusion-------------------------------------
	 */
	//visitconclusion:1���Ӱݷ��ܽ�
	public boolean saveVisitConclusion(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion) {
		boolean bisSave=iDaoFrame.saveVisitConclusion(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		return bisSave;
	}
	//visitconclusion:2��Ա���Ż�����еİݷ��ܽ�
	public CEntityVisitConclusionArray queryAllVisitConclusionByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityVisitConclusionArray cEntityVisitConclusionArray=iDaoFrame.queryAllVisitConclusionByEmployeeId(cEntityEmployee);
		return cEntityVisitConclusionArray;
	}
	
	/*
	 * ------------------------------�ݷó��� visitconclusion-------------------------------------
	 */	
	
	//visitundo:1����һ���ݷó���
	public boolean saveVisitUndo(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo) {
		boolean bisSave=iDaoFrame.saveVisitUndo(cEntityVisitPlan, cEntityVisitUndo);
		return bisSave;
	}
	
	//visitundo:2������еİݷó���
	public CEntityVisitUndoArray queryAllVisitUndo() {
		CEntityVisitUndoArray cEntityVisitUndoArray=iDaoFrame.queryAllVisitUndo();
		return cEntityVisitUndoArray;
	}
	
	/*
	 * -----------------------------�ݷ�����visitdelay-------------------------------------
	 */
	//visitdealy:1����һ���ݷ�����
	public boolean saveVisitDealy(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitDelay cEntityVisitDelay){
		boolean bisSave=iDaoFrame.saveVisitDealy(cEntityEmployee, cEntityVisitPlan, cEntityVisitDelay);
		return bisSave;
	}
	
	//visitdealy:2������аݷ�����
	public CEntityVisitDealyArray queryAllVisitDelay() {
		CEntityVisitDealyArray cEntityVisitDealyArray=iDaoFrame.queryAllVisitDelay();
		return cEntityVisitDealyArray;
	}

	/*
	 * -----------------------------����bussiness------------------------------------
	 */
	//bussiness:1����һ������
	public boolean saveBussiness(CEntityEmployee cEntityEmployee,CEntityBussiness cEntityBussiness) {
		boolean bisSave=iDaoFrame.saveBussiness(cEntityEmployee, cEntityBussiness);
		return bisSave;
	}
	
	//bussiness:2��Ա���Ż�����еĳ���
	public CEntityBussinessArray queryAllBussinessByEmployeeId(CEntityEmployee cEntityEmployee) {
		CEntityBussinessArray cEntityBussinessArray=iDaoFrame.queryAllBussinessByEmployeeId(cEntityEmployee);
		return cEntityBussinessArray;
	}
	
	/*
	 * -----------------------------����bussinessactivity------------------------------------
	 */
	//bussinessactivity:1����һ������
	public boolean saveBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness) {
		boolean bisSave=iDaoFrame.saveBussinessActivity(cEntityBussinessActivity, cEntityBussiness);
		return bisSave;
	}
	
	//bussinessactivity:2������Ż�����еĳ���
	public CEntityBussinessActivityArray queryAllBussinessActivityByBussinessId(CEntityBussiness cEntityBussiness){
		CEntityBussinessActivityArray cEntityBussinessActivityArray=iDaoFrame.queryAllBussinessActivityByBussinessId(cEntityBussiness);
		return cEntityBussinessActivityArray;
	}
	
	/*
	 * -----------------------------�������¼bussinessbadrecord------------------------------------
	 */
	//bussinessbadrecord:1����һ���������¼
	public boolean saveBussinessBadrecord(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord) {
		boolean bisSave=iDaoFrame.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord);
		return bisSave;
	}
	//bussinessbadrecord:2������еĳ������¼
	public CEntityBussinessBadrecordArray queryAllBussinessBadrecord() {
		CEntityBussinessBadrecordArray cEntityBussinessBadrecordArray=iDaoFrame.queryAllBussinessBadrecord();
		return cEntityBussinessBadrecordArray;
	} 
	/*
	 * ----------------------------����attendance------------------------------------
	 */
	
	//attendance:1����һ������
	public boolean saveAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance) {
		return false;
	}
	
	/*
	 * ----------------------------֪ͨ notice------------------------------------
	 */
	//notice:1����һ��֪ͨ
	public boolean saveNotice(CEntityNotice cEntityNotice) {
		boolean bisSave=iDaoFrame.saveNotice(cEntityNotice);
		return bisSave;
	}
	//notice:2���ȫ��֪ͨ
	public CEntityNoticeArray queryAllNotice() {
		CEntityNoticeArray cEntityNoticeArray=iDaoFrame.queryAllNotice();
		return cEntityNoticeArray;
	}


	/*
	 * ----------------------------���� suggest----------------------------------
	 */
	//suggest:1����һ������
	public boolean saveSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest) {
		boolean bisSave=iDaoFrame.saveSuggest(cEntityEmployee, cEntitySuggest);
		return bisSave;
	}
	
	//suggest:2������еĽ���
	public CEntitySuggestArray queryAllSuggest() {
		CEntitySuggestArray cEntitySuggestArray=iDaoFrame.queryAllSuggest();
		return cEntitySuggestArray;
	}

}
