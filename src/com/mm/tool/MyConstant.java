package com.mm.tool;

public class MyConstant {
	public static final class Reason{
		public static final String REASON_CLIENTDEL="客户已被删除";
		public static final String REASON_UNDOBUSSINESS="出差被撤销";
	}
	
	public static final class Employee{
		public static final int EMPLOYEE_XIAOSHOU=0;		//销售人员
		public static final int EMPLOYEE_PEISONG=1;			//配送人员
		public static final int EMPLOYEE_SHOUHOU=2;			//售后人员
		public static final int EMPLOYEE_DUCHA=3;			//巡店人员
		public static final int EMPLOYEE_DEL=4;				//删除人员
		
	}	
	
	public static final class Mission{
		public static final int MISSION_WAITTAKE=0;		//任务未接受
		public static final int MISSION_UNDERWAY=1;		//任务执行中
		public static final int MISSION_NOCHECK=2;		//任务未审核
		public static final int MISSION_CHECK=3;		//任务已审核
		public static final int MISSION_UNDO=4;			//任务已撤销
		public static final int MISSION_OUTTIME=5;		//任务已过期
		public static final int MISSION_FAILURE=6;		//任务已失败
		
		
		public static final int MISSION_NODELAY=0;		//任务未延期
		public static final int MISSION_DELAY=1;		//任务已延期
		
		public static final int MISSION_BUSSINESSNOBAND=0;	//任务未与出差绑定
		public static final int MISSION_BUSSINESSBAND=1;	//任务与出差绑定
		
	}
	
	public static final class MissionConclusion{
		public static final int MISSIONCONCLUSION_WAITCHECK=0;	//总结未审核
		public static final int MISSIONCONCLUSION_PASS=1;		//总结通过
		public static final int MISSIONCONCLUSION_NOPASS=2;		//总结未通过
	}
	
	public static final class MissionUndo{
		public static final int MISSIONUNDO_UNDO=0;		//任务已撤销
		public static final int MISSIONUNDO_RECORD=1;	//任务已撤回
	}
	
	
	public static final class Client{
		public static final int CLIENT_UNDISTRIBUTED=0;	//客户未分配
		public static final int CLIENT_DISTRIBUTED=1;	//客户已分配
		public static final int CLIENT_WAITCHECK=2;		//客户未审核
		public static final int CLIENT_DEL=3;			//客户已删除
		
	}
	
	
	public static final class ClientSubmit{
		public static final int CLIENTSUBMIT_WAITCHECK=0;	//客户提交未审核
		public static final int CLIENTSUBMIT_PASS=1;		//客户提交审核通过
		public static final int CLIENTSUBMIT_NOPASS=2;		//客户提交审核未通过
	}
	
	
	public static final class VisitPlan{
		public static final int VISITPLAN_NOTSTART=0;	//拜访计划未开始
		public static final int VISITPLAN_UNDERWAY=1;	//拜访计划执行中
		public static final int VISITPLAN_WAITCHECK=2;	//拜访计划未审核
		public static final int VISITPLAN_CHECK=3;		//拜访计划已审核
		public static final int VISITPLAN_UNDO=4;		//拜访计划已撤销
		public static final int VISITPLAN_OUTTIME=5;	//拜访计划已过期
		public static final int VISITPLAN_FAILURE=6;	//拜访计划已失败
		
		
		public static final int VISITPLAN_BUSSINESSNOBAND=0;	//拜访计划未与出差绑定
		public static final int VISITPLAN_BUSSINESSBAND=1;		//拜访计划与出差已绑定
	}
	
	public static final class VisitPlanCycle{
		public static final int VISITPLANCYCLE_NOCYCLE=0;	//拜访计划不循环
		public static final int VISITPLANCYCLE_CYCLE=1;		//拜访计划循环
	}

	public static final class VisitPlanCycleType{
		public static final int VISITPLANCYCLETYPE_DAY=0;			//按天
		public static final int VISITPLANCYCLETYPE_WEEK=1;			//按星期
		public static final int VISITPLANCYCLETYPE_MONTH=2;			//按月
		public static final int VISITPLANCYCLETYPE_USERDEFINED=3;	//自定义天数
	}
	
	
	public static final class VisitConclusion{
		public static final int VISITCONCLUSION_WAITCHECK=0;	//拜访总结未审核
		public static final int VISITCONCLUSION_PASS=1;			//拜访总结通过
		public static final int VISITCONCLUSION_NOPASS=2;		//拜访总结未通过
	}
	
	public static final class VisitUndo{
		public static final int VISITUNDO_UNDO=0;	//拜访计划已撤销
		public static final int VISITUNDO_RECALL=1;	//拜访计划已撤回
	}
	
	public static final class Bussiness{
		
		public static final int BUSSINESS_REGISTRATION=0;	//出差未登记
		public static final int BUSSINESS_UNDERWAY=1;		//出差执行中
		public static final int BUSSINESS_WAITCHECK=2;		//出差未审核
		public static final int BUSSINESS_CHECKPASS=3;		//出差审核通过
		public static final int BUSSINESS_CHECKNOPASS=4;	//出差审核未通过
		public static final int BUSSINESS_UNDO=5;			//出差审核已撤销
		
		
		
		
		//----员工操作与上不冲突-----
		public static final int BUSSINESS_NOCOMPLETE=0;		//出差未完成
		public static final int BUSSINESS_COMPLETE=1;		//出差完成
		public static final int BUSSINESS_SENDREGISTER=2;	//出差登记
		public static final int BUSSINESS_SENDIN=3;			//出差抵达
		public static final int BUSSINESS_SENDOUT=4;		//出差离开
		public static final int BUSSINESS_RETURN=5;			//出差归来
		
	}
	
	public static final class BussinessActivity{
		public static final int BUSSINESSACTIVITY_MISSIONTYPE=0;	//活动类型为任务
		public static final int BUSSINESSACTIVITY_VISITPLANTYPE=1;	//活动类型为拜访计划
		
		public static final int BUSSINESSACTIVITY_BAND=0;		//活动绑定中
		public static final int BUSSINESSACTIVITY_NOBAND=1;		//活动已解绑
	}
	
	
	public static final class Attendance{
		public static final int ATTENDANCE_REGISTER=0;	//考勤签到
		public static final int ATTENDANCE_SIGNOUT=1;	//考勤签退
		public static final int ATTENDANCE_NOSTART=2;	//考勤未开始
		
	}
	

	

	
	
	
	
}
