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
	 * ��ţ�bussinessmodule:1 
	 * ���ܣ���������
	 * ������cEntityEmployee(EmployeeId),cEntityBussiness(�����ֶ�) 
	 * ����ֵ:boolean
	 */
	public boolean createBussiness(CEntityBussiness cEntityBussiness, CEntityEmployee cEntityEmployee){
		//�����������״̬Ϊδ�Ǽ�,�ɹ�����������������Ա
		boolean bisCreate=iDaoFrame.saveBussiness(cEntityEmployee, cEntityBussiness);
		return bisCreate;
	}
	
	/**
	 * ��ţ�bussinessmodule:2
	 * ���ܣ������
	 * ������
	 * ����ֵ:boolean
	 */
	public boolean bindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity){
		//��������¼��
		boolean bisUndoActivity=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		boolean bisUndo=false;
		//����Ӧ�İ�
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
	 * ��ţ�bussinessmodule:3
	 * ���ܣ�������
	 * ������
	 * ����ֵ:boolean
	 */
	public boolean unbindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity){
		//��������¼���
		boolean bisUndoActivity=iDaoFrame.updateBussinessBandTypeByObjectIdAndActivityType(cEntityBussinessActivity);
		boolean bisUndo=false;
		//����Ӧ�Ľ��
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
	 * ��ţ�bussinessmodule:4
	 * ���ܣ�����Ǽ�
	 * ������cEntityBussiness(BussinessId,BussinessRegisterTime)
	 * ����ֵ:boolean
	 */
	public boolean registerBussiness(CEntityBussiness cEntityBussiness){
		//����״̬תΪִ����
		boolean bisRegister=iDaoFrame.updateBussinessRegisterTime(cEntityBussiness);
		return bisRegister;
	}
	

	/**
	 * ��ţ�bussinessmodule:5
	 * ���ܣ��ִ�Ŀ�ĵصǼ�
	 * ������cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * ����ֵ:boolean
	 */
	public boolean inregisterBussiness(CEntityBussiness cEntityBussiness){
		boolean bisIn=iDaoFrame.updateBussinessInTimeAndInAddress(cEntityBussiness);
		
		return bisIn;
	}
	/**
	 * ��ţ�bussinessmodule:6
	 * ���ܣ��뿪Ŀ�ĵصǼ�
	 * ������cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * ����ֵ:boolean
	 */
	public boolean outregisterBussiness(CEntityBussiness cEntityBussiness){
		boolean bisOut=iDaoFrame.updateBussinessOutTimeAndOutAddress(cEntityBussiness);
		return bisOut;
	}
	
	/**
	 * ��ţ�bussinessmodule:7
	 * ���ܣ������Ǽ�
	 * ������cEntityBussiness(BussinessId,BussinessReturnTime)
	 * ����ֵ:boolean
	 */
	public boolean returnregisterBussiness(CEntityBussiness cEntityBussiness){
		boolean bisReturn=iDaoFrame.updateBussinessReturn(cEntityBussiness);
		return bisReturn;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:8
	 * ���ܣ���˳���ͨ��
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean checkBussinessPass(CEntityBussiness cEntityBussiness){
		boolean bisCheck=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_CHECKPASS);
		return bisCheck;
	}
	/**
	 * ��ţ�bussinessmodule:9
	 * ���ܣ���˳��ͨ��
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean checkBussinessNopass(CEntityBussiness cEntityBussiness ){
		boolean bisupdateBussinessState=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_CHECKNOPASS);
//		boolean bisSave=iDaoFrame.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord);
		
//		boolean bisCheck=bisupdateBussinessState;
		return bisupdateBussinessState;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:10
	 * ���ܣ�������Ա�������(��ǰ����һ��δ�Ǽǻ�ִ���г���)
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessRunningState(CEntityEmployee cEntityEmployee){
		CEntityBussiness cEntityBussiness=iDaoFrame.queryBussinessRunningStateByEmployeeId(cEntityEmployee);
		return cEntityBussiness;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:11
	 * ���ܣ�������Ա��������¼
	 * ������cEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityBussinessArray
	 */
	public CEntityBussinessArray GetBussinessRecall(CEntityEmployee cEntityEmployee){
		CEntityBussinessArray cEntityBussinessArray=iDaoFrame.queryAllBussinessByEmployeeId(cEntityEmployee);
		return cEntityBussinessArray;
	}
	
	

	/**
	 * ��ţ�bussinessmodule:12
	 * ���ܣ��鿴�����¼����
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessDetail(CEntityBussiness cEntityBussiness){
		CEntityBussiness finResult=iDaoFrame.queryBussinessByBussinessId(cEntityBussiness);
		return finResult;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:13
	 * ���ܣ���ȡ����
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:HashMap<String, Object>
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
	 * ��ţ�bussinessmodule:14
	 * ���ܣ��жϳ����Ƿ��а󶨵ĳ���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
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
	 * ��ţ�bussinessmodule:15
	 * ���ܣ������������Ƴ���
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoBussiness(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		//�޸ĳ���״̬Ϊ����
		boolean bisUpdate=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_UNDO);
		//���ӳ������¼
		boolean bisUndo=iDaoFrame.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo&bisUpdate;
	}
	
	/**
	 * ��ţ�bussinessmodule:15
	 * ���ܣ������������������
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoBussinessWithUndoBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		//�޸ĳ���״̬Ϊ����
		boolean bisUpdate=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_UNDO);
		//���ӳ������¼
		boolean bisUndo=iDaoFrame.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		//��������
		boolean bisUndoMission=iDaoFrame.updateMissionStateUndoWithSaveMissionUndoByBussinessId(cEntityBussiness);
		//�����ݷüƻ�
		boolean bisUndoVisit=iDaoFrame.updateVisitPlanUndoWithSaveVisitUndoByBussinessId(cEntityBussiness);
		
		
		return bisUpdate&&bisUndo&&bisUndoMission&&bisUndoVisit;
	}
	
	/**
	 * ��ţ�bussinessmodule:16
	 * ���ܣ��������������
	 * ������CEntityBussiness(BussinessId),CEntityBussinessUndo(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean undoBussinessWithUnbindBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		//�޸ĳ���״̬Ϊ����
		boolean bisUpdate=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_UNDO);
		//���ӳ������¼
		boolean bisUndo=iDaoFrame.savaBussinessUndo(cEntityBussiness, cEntityBussinessUndo);
		//������
		boolean bisUnband=iDaoFrame.updateBussinessActivityBindTypeUnbindByBussinessId(cEntityBussiness);
		//�������
		boolean bisUnbandMission=iDaoFrame.updateMissionBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		//���ݷüƻ�
		boolean bisUnbandVisitplan=iDaoFrame.updateVisitPlanBussinessBandStateUnbandByBussinessId(cEntityBussiness);
		
		
		return bisUpdate&&bisUndo&&bisUnband&&bisUnbandMission&&bisUnbandVisitplan;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:17
	 * ���ܣ���˳���ͨ������Ҫ����һ���������¼
	 * ������CEntityBussiness(BussinessId),CEntityBussinessBadrecord(�����ֶ�)
	 * ����ֵ:boolean
	 */
	public boolean checkBussinessPassWithBadrecall(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord){
		boolean bisupdateBussinessState=iDaoFrame.updateBussinessStateByBussinessId(cEntityBussiness, MyConstant.Bussiness.BUSSINESS_CHECKPASS);
		boolean bisSave=iDaoFrame.saveBussinessBadrecord(cEntityBussiness, cEntityBussinessBadrecord);
		boolean bisCheck=bisupdateBussinessState;
		return bisSave&&bisCheck;

	}
	
	
	
	/**
	 * ��ţ�bussinessmodule:18
	 * ���ܣ��õ���Ա�������г������¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getEmployeeBussinessBadrecall(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=iDaoFrame.queryAllBussinessBadrecordByEmployeeId(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:19
	 * ���ܣ���ȡ����δɾԱ����ִ���г�����Ϣ(δ�Ǽǣ�ִ����)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessRunning(){
		LinkedMap findResultg=iDaoFrame.queryAllBussinessRuning();
		return findResultg;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:20
	 * ���ܣ���ȡ���������Ա����Ϣ����Ա��û��δ�Ǽǣ�ִ���У�δ��˵ĳ��
	 * ������
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List getBussinessAddOkEmployee(){
		List findResult=iDaoFrame.queryBussinessAddOkEmployeeInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:21
	 * ���ܣ���ȡ����δɾԱ���Ĵ�����������Ϣ(δ���)
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessWaitDeal(){
		LinkedMap findResult=iDaoFrame.queryAllBussinessWaitDeal();
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:22
	 * ���ܣ���ȡ����δɾԱ�����ѽ���������Ϣ�����ͨ������˲�ͨ����
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessComplete(){
		LinkedMap findResult=iDaoFrame.queryAllBussinessComplete();
		return findResult;
	}
	
	
	/**
	 * ��ţ�bussinessmodule:23
	 * ���ܣ���ȡ����δɾԱ���������¼
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessUndoInfo(){
		LinkedMap findResult=iDaoFrame.queryAllBussinessUndoInfo();
		return findResult;
	}
	
	/**
	 * ��ţ�bussinessmodule:24
	 * ���ܣ���ȡ����δɾԱ���������¼
	 * ������
	 * ����ֵ:LinkedMap
	 */
	public LinkedMap getAllBussinessBadrecordInfo(){
		LinkedMap findReult=iDaoFrame.queryAllBussinessBadrecordInfo();
		return findReult;
	}
	
	
	
	
	
}