package com.mm.tool;



public class MyOpcode {
	public static final class Operation {
		public static final String OPERATION = "operation";		//������
		public static final String SIGN = "sign";				//��־
		public static final String TRUE = "true";				//��,��ȷ
		public static final String FALSE = "false";				//��,����
		public static final String LIST = "list";				//��ʾ�б�
		public static final String TYPE = "type";				//����
		public static final String CHECK="check";				//���
		
		
		
		// ��¼
		public static final int LOGIN = 1;

		// ���ڹ���
		public static final int ENTER_ATTENDANCE = 2; // ���뿼�ڹ���
		public static final int SEND_ATTENDANCE = 3; // ǩ��/ǩ��

		// ����
		public static final int ENTER__MISSION = 4; // ��������
//		public static final int ENTER_WAITTAKE_MISSION = 4; // �������������
		public static final int GET_WAITTAKE_MISSION = 5; // ��������
//		public static final int ENTER_UNDERWAY_MISSION = 6; // �������������
		public static final int SENT_MISSION = 7; // �ύ����
//		public static final int ENTER_COMPLETE_MISSION = 8; // �������������

		// ����
		public static final int ENTER_BUSSINESS = 9; // �������
		public static final int SEND_BUSSINESS=10;	//�ύ����
//		public static final int SEND_BUSSINESS_REGISTER = 10; // �ύ����Ǽ�
//		public static final int SEND_BUSSINESS_IN = 11; // �ύ����ִ�
//		public static final int SEND_BUSSINESS_OUT = 12; // �ύ�����뿪
//		public static final int SEND_BUSSINESS_RETURN = 13; // �ύ�������

		// �ͻ�����
		public static final int ENTER_CLIENT_MANAGER = 14; // ����ͻ�����
		public static final int ENTER_CLIENT_VISIT = 15; // �ύ�ͻ��ݷ�
		public static final int ADD_CLIENT = 16; // ���ӿͻ�
		public static final int UPDATA_CLIENT = 17; // �޸Ŀͻ�
		public static final int ENTER_VISIT = 18; // ����ݷü�¼

		// ͨ��¼
		public static final int ENTER_COMMINITION = 19; // ����ͨѶ¼

		// ֪ͨ����
		public static final int ENTER_NOTICE = 20;// ����֪ͨͨ��
		
		
		
		public static final int SEND_SUGGEST=21;//�ύͶ�߽���
		
		public static final int UPLOADIMG=22;//ͼƬ�ϴ�
		
		public static final int SENDADDRESS=23;//�������λ����Ϣ
	}
	
	/**
	 * 
	 * ���ܣ�Ա���ֶ�
	 *
	 */
	public static final class Employee {
		public static final String Employee="Employee";
		public static final String EmployeeList="EmployeeList";
		public static final String EmployeeId = "EmployeeId";
		public static final String EmployeeAccount="EmployeeAccount"; 
		public static final String EmployeePassword = "EmployeePassword";
		public static final String EmployeeName = "EmployeeName";
		public static final String EmployeePhone = "EmployeePhone";
		public static final String EmployeeSex = "EmployeeSex";
		public static final String EmployeeDepartment = "EmployeeDepartment";
		public static final String EmployeeJob = "EmployeeJob";
		public static final String EmployeeType = "EmployeeType";
	}
	
	/**
	 * 
	 * ���ܣ�����Ա
	 *
	 */
	public static final class Administrator{
		public static final String AdministratorId="AdministratorId";
		public static final String AdministratorAccount="AdministratorAccount";
		public static final String AdministratorPassword="AdministratorPassword";
	}
	
	
	/**
	 * 
	 * ���ܣ������ֶ�
	 *
	 */
	public static final class Mission {
		public static final String Mission="Mission";
		public static final String MissionList="MissionList";
		public static final String MissionId = "MissionId";
		public static final String MissionPubdate = "MissionPubdate";
		public static final String MissionContent = "MissionContent";
		public static final String MissionDeadline = "MissionDeadline";
		public static final String MissionState = "MissionState";
		public static final String MissionDelayState="MissionDelayState";
		public static final String MissionBussinessBandState="MissionBussinessBandState";
	
	}
	
	/**
	 * 
	 * ���ܣ����������ֶ�
	 *
	 */
	public static final class MissionDelay{
		public static final String MissionDelay="MissionDelay";
		public static final String MissionDelayList="MissionDelayList";
		public static final String MissionDelayId="MissionDelayId";
		public static final String MissionDelayTime="MissionDelayTime";
		public static final String MissionDelayDeadline="MissionDelayDeadline";
	}
	
	/**
	 * 
	 * ���ܣ������ܽ��ֶ�
	 *
	 */

	public static final class MissionConclusion{
		public static final String MissionConclusion="MissionConclusion";
		public static final String MissionConclusionList="MissionConclusionList";
		public static final String MissionConclusionId="MissionConclusionId";
		public static final String MissionCheck="MissionCheck";
		public static final String MissionSummary="MissionSummary";
		public static final String MissionSubmitTime="MissionSubmitTime";
		public static final String MissionAccessoryPath="MissionAccessoryPath";

	}
	
	
	/**
	 * 
	 * ���ܣ��������ֶ�
	 *
	 */
	public static final class MissionUndo{
		public static final String MissionUndo="MissionUndo";
		public static final String MissionUndoList="MissionUndoList";
		public static final String MissionUndoId="MissionUndoId";
		public static final String MissionUndoReason="MissionUndoReason";
		public static final String MissionUndoTime="MissionUndoTime";
		public static final String MissionUndoRecallType="MissionUndoRecallType";
		public static final String MissionUndoType="MissionUndoType";
	}
	
	
	/**
	 * 
	 * ���ܣ��ͻ��ֶ�
	 *
	 */
	public static final class Client {
		public static final String Client="Client";
		public static final String ClientList="ClientList";
		public static final String ClientId = "ClientId";
		public static final String ClientName = "ClientName";
		public static final String ClientCompany = "ClientCompany";
		public static final String ClientPhone = "ClientPhone";
		public static final String ClientArea = "ClientArea";
		public static final String ClientAddress = "ClientAddress";
		public static final String ClientState="ClientState";
	}
	
	/**
	 * 
	 * ���ܣ��ͻ��ύ��¼
	 *
	 */
	public static final class ClientSubmit{
		public static final String ClientSubmit="ClientSubmit";
		public static final String ClientSubmitList="ClientSubmitList";
		public static final String ClientSubmitId="ClientSubmitId";
		public static final String ClientSubmitState="ClientSubmitState";
		public static final String ClientSubmitTime="ClientSubmitTime";
	}
	
	/**
	 * 
	 * ���ܣ��ݷüƻ�
	 *
	 */
	public static final class VisitPlan{
		public static final String VisitPlan="VisitPlan";
		public static final String VisitPlanList="VisitPlanList";
		public static final String VisitPlanId="VisitPlanId";
		public static final String VisitPlanPubdate="VisitPlanPubdate";
		public static final String VisitPlanStartTime="VisitPlanStartTime";
		public static final String VisitPlanEndTime="VisitPlanEndTime";
		public static final String VisitPlanState="VisitPlanState";
		public static final String VisitPlanCycle="VisitPlanCycle";
		public static final String VisitPlanCycleType="VisitPlanCycleType";
		public static final String VisitPlanCycleNumber="VisitPlanCycleNumber";
		public static final String VisitPlanDays="VisitPlanDays";
		public static final String VisitBussinessBandState="VisitBussinessBandState";
		
		
	}
	
	
	/**
	 * 
	 * ���ܣ��ݷ��ܽ�
	 *
	 */
	public static final class VisitConclusion{
		public static final String VisitConclusion="VisitConclusion";
		public static final String VisitConclusionList="VisitConclusionList";
		public static final String VisitConclusionId="VisitConclusionId";
		public static final String VisitCheck="VisitCheck";
		public static final String VisitSubmitTime="VisitSubmitTime";
		public static final String VisitSummary="VisitSummary";
		public static final String VisitCommand="VisitCommand";
		public static final String VisitAccessoryPath="VisitAccessoryPath";
	}
	
	
	
	/**
	 * 
	 * ���ܣ��ݷó���
	 *
	 */
	public static final class VisitUndo{
		public static final String VisitUndo="VisitUndo";
		public static final String VisitUndoList="VisitUndoList";
		public static final String VisitUndoId="VisitUndoId";
		public static final String VisitUndoReason="VisitUndoReason";
		public static final String VisitUndoTime="VisitUndoTime";
		public static final String VisitUndoRecallType="VisitUndoRecallType";
		public static final String VisitUndoType="VisitUndoType";
	}
	
	public static final class VisitDelay{
		public static final String VisitDelay="VisitDelay";
		public static final String VisitDelayList="VisitDelayList";
		public static final String VisitDelayId="VisitDelayId";
		public static final String VisitDelayTime="VisitDelayTime";
		public static final String VisitDelayDeadline="VisitDelayDeadline";
	}
	
	/**
	 * 
	 * ���ܣ������ֶ�
	 *
	 */
	public static final class Bussiness {
		public static final String Bussiness="Bussiness";
		public static final String BussinessList="BussinessList";
		public static final String BussinessId = "BussinessId";
		public static final String BussinessSideAddress = "BussinessSideAddress";
		public static final String BussinessContent = "BussinessContent";
		public static final String BussinessRegisterTime = "BussinessRegisterTime";
		public static final String BussinessInAddress = "BussinessInAddress";
		public static final String BussinessInTime = "BussinessInTime";
		public static final String BussinessOutAddress = "BussinessOutAddress";
		public static final String BussinessOutTime = "BussinessOutTime";
		public static final String BussinessReturnTime = "BussinessReturnTime";
		public static final String BussinessState="BussinessState";
	}

	
	/**
	 * 
	 * ���ܣ�����
	 *
	 */
	public static final class BussinessActivity{
		public static final String BussinessActivityList="BussinessActivityList";
		public static final String BussinessActivityId="BussinessActivityId";
		public static final String BussinessActivityType="BussinessActivityType";
		public static final String BussinessObjectId="BussinessObjectId";
		public static final String BussinessBindType="BussinessBindType";
		
	}
	
	/**
	 * 
	 * ���ܣ��������¼
	 *
	 */
	public static final class BussinessBadrecord{
		public static final String BussinessBadrecord="BussinessBadrecord";
		public static final String BussinessBadrecordList="BussinessBadrecordList";
		public static final String BussinessBadrecordId="BussinessBadrecordId";
		public static final String BussinessBadrecordTime="BussinessBadrecordTime";
		public static final String BussinessBadrecordReason="BussinessBadrecordReason";
	}
	
	/**
	 * 
	 * ���ܣ������
	 * 
	 */
	
	public static final class BussinessUndo{
		public static final String BussinessUndo="BussinessUndo";
		public static final String BussinessUndoList="BussinessUndoList";
		public static final String BussinessUndoId="BussinessUndoId";
		public static final String BussinessUndoReason="BussinessUndoReason";
		public static final String BussinessUndoTime="BussinessUndoTime";
	}
	
	/**
	 * 
	 * ���ܣ������ֶ�
	 *
	 */
	public static final class Attendance {
		public static final String Attendance="Attendance";
		public static final String AttendanceList="AttendanceList";
		public static final String AttendanceId = "AttendanceId";
		public static final String AttendanceRegisterTime = "AttendanceRegisterTime";
		public static final String AttendanceSignoutTime = "AttendanceSignoutTime";
		public static final String AttendanceDate = "AttendanceDate";
	}

	


	/**
	 * 
	 * ���ܣ�֪ͨ�ֶ�
	 *
	 */
	public static final class Notice {
		public static final String NoticeList="NoticeList";
		public static final String NoticeId = "NoticeId";
		public static final String NoticeTime = "NoticeTime";
		public static final String NoticeTitle = "NoticeTitle";
		public static final String NoticeContent = "NoticeContent";
	}

	

	/**
	 * 
	 * ���ܣ������ֶ�
	 *
	 */
	public static final class Suggest{
		public static final String Suggest="Suggest";
		public static final String SuggestList="SuggestList";
		public static final String SuggestId="SuggestId";
		public static final String SuggestContent="SuggestContent";
		public static final String SuggestTime="SuggestTime";
	}
	
	/**
	 * 
	 *���ܣ���ַ
	 *
	 */
	public static final class Address{
		public static final String Address="Address";
		public static final String AddressList="AddressList";
		public static final String AddressId="AddressId";
		public static final String AddressLong="AddressLong";
		public static final String AddressLat="AddressLat";
		public static final String AddressTime="AddressTime";
	}
	
	
}
