package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//任务
@Entity
@Table(name = "Mission")
public class CEntityMission {
	private int m_iMissionId; // 任务号,主键，自增
	private String m_sMissionPubdate; // 任务发布时间
	private String m_sMissionContent; // 任务内容
	private String m_sMissionDeadline;// 任务期限
	private int m_iMissionState; // 任务状态 (0为未接受,1为执行中,2未审核,3为已审核，4已撤销,5已过期，6已失败)
	private int m_iMissionDelayState; // 任务延期记录(0为未延期，1未延期过)
	private int m_iMissionBussinessBandState;//任务出差绑定状态（0为未绑定，1为已绑定）

	public CEntityMission() {
	}


	public CEntityMission(int mIMissionId, String mSMissionPubdate,
			String mSMissionContent, String mSMissionDeadline,
			int mIMissionState, int mIMissionDelayState) {
		m_iMissionId = mIMissionId;
		m_sMissionPubdate = mSMissionPubdate;
		m_sMissionContent = mSMissionContent;
		m_sMissionDeadline = mSMissionDeadline;
		m_iMissionState = mIMissionState;
		m_iMissionDelayState = mIMissionDelayState;
	}

	public static class Builder{
		private int m_iMissionId; // 任务号,主键，自增
		private String m_sMissionPubdate; // 任务发布时间
		private String m_sMissionContent; // 任务内容
		private String m_sMissionDeadline;// 任务期限
		private int m_iMissionState; // 任务状态 (0为未接受,1为执行中,2未审核,3为已审核，4已撤销,5已过期，6已失败)
		private int m_iMissionDelayState; // 任务延期记录(0为未延期，1未延期过)
		private int m_iMissionBussinessBandState;//任务出差绑定状态（0为未绑定，1为已绑定）

		public Builder(){}
		
		public Builder MissionId(int m_iMissionId){
			this.m_iMissionId=m_iMissionId;
			return this;
		}
		public Builder MissionPubdate(String m_sMissionPubdate){
			this.m_sMissionPubdate=m_sMissionPubdate;
			return this;
		}
		
		public Builder MissionContent(String m_sMissionContent){
			this.m_sMissionContent=m_sMissionContent;
			return this;
		}
		public Builder MissionDeadline(String m_sMissionDeadline){
			this.m_sMissionDeadline=m_sMissionDeadline;
			return this;
		}
		public Builder MissionState(int m_iMissionState){
			this.m_iMissionState=m_iMissionState;
			return this;
		}
		
		public Builder MissionDelayState(int m_iMissionDelayState){
			this.m_iMissionDelayState=m_iMissionDelayState;
			return this;
		}
		public Builder MissionBussinessBandState(int m_iMissionBussinessBandState){
			this.m_iMissionBussinessBandState=m_iMissionBussinessBandState;
			return this;
		}
		
		public CEntityMission build(){
			return new CEntityMission(this);
		}
		
	}
	
	private CEntityMission(Builder b){
		m_iMissionId=b.m_iMissionId;
		m_sMissionPubdate=b.m_sMissionPubdate;
		m_sMissionContent=b.m_sMissionContent;
		m_sMissionDeadline=b.m_sMissionDeadline;
		m_iMissionState=b.m_iMissionState;
		m_iMissionDelayState=b.m_iMissionDelayState;
		m_iMissionBussinessBandState=b.m_iMissionBussinessBandState;
	}
	
	
	

	public static class BuildJsonObject{

		private JSONObject outjson = new JSONObject();

		public BuildJsonObject(){}
		
		public BuildJsonObject MissionId(int m_iMissionId){
			outjson.put(MyOpcode.Mission.MissionId, m_iMissionId);
			return this;
		}
		
		public BuildJsonObject MissionPubdate(String m_sMissionPubdate){
			outjson.put(MyOpcode.Mission.MissionPubdate, m_sMissionPubdate);
			return this;
		}
		public BuildJsonObject MissionContent(String m_sMissionContent){
			outjson.put(MyOpcode.Mission.MissionContent, m_sMissionContent);
			return this;
		}
		
		public BuildJsonObject MissionDeadline(String m_sMissionDeadline){
			outjson.put(MyOpcode.Mission.MissionDeadline, m_sMissionDeadline);
			return this;
		}
		
		public BuildJsonObject MissionState(int m_iMissionState){
			outjson.put(MyOpcode.Mission.MissionState, m_iMissionState);
			return this;
		}
		public BuildJsonObject MissionDelayState(int m_iMissionDelayState){
			outjson.put(MyOpcode.Mission.MissionDelayState, m_iMissionDelayState);
			return this;
		}
		public BuildJsonObject MissionBussinessBandState(int m_iMissionBussinessBandState){
			outjson.put(MyOpcode.Mission.MissionBussinessBandState, m_iMissionBussinessBandState);
			return this;
		}
		
		
		public BuildJsonObject Operation(int operation){
			outjson.put(MyOpcode.Operation.OPERATION, operation);
			return this;
		}
		
		public BuildJsonObject Check(boolean check){
			outjson.put(MyOpcode.Operation.CHECK, check);
			return this;
		}
		
		public BuildJsonObject MyJSONArray(JSONArray array){
			outjson.put(MyOpcode.Mission.MissionList, array);
			return this;
		}
		
		public BuildJsonObject ToSingle(CEntityMission cEntityMission){
			
			outjson.put(MyOpcode.Mission.MissionId, cEntityMission.m_iMissionId);
			outjson.put(MyOpcode.Mission.MissionPubdate, cEntityMission.m_sMissionPubdate);
			outjson.put(MyOpcode.Mission.MissionContent, cEntityMission.m_sMissionContent);
			outjson.put(MyOpcode.Mission.MissionDeadline, cEntityMission.m_sMissionDeadline);
			outjson.put(MyOpcode.Mission.MissionState, cEntityMission.m_iMissionState);
			outjson.put(MyOpcode.Mission.MissionDelayState, cEntityMission.m_iMissionDelayState);
			outjson.put(MyOpcode.Mission.MissionBussinessBandState, cEntityMission.m_iMissionBussinessBandState);
			
			return this;
		}
		
		public JSONObject build(){
			return outjson;
		}
	}
	
	
	
	@Id
	@GeneratedValue
	@Column(name = "MissionId")
	public int getM_iMissionId() {
		return m_iMissionId;
	}

	@Column(name = "MissionState")
	public int getM_iMissionState() {
		return m_iMissionState;
	}

	@Column(name = "MissionContent")
	public String getM_sMissionContent() {
		return m_sMissionContent;
	}

	@Column(name = "MissionDeadline")
	public String getM_sMissionDeadline() {
		return m_sMissionDeadline;
	}

	@Column(name = "MissionPubdate")
	public String getM_sMissionPubdate() {
		return m_sMissionPubdate;
	}

	@Column(name = "MissionDelayState")
	public int getM_iMissionDelayState() {
		return m_iMissionDelayState;
	}
	@Column(name="MissionBussinessBandState")
	public int getM_iMissionBussinessBandState() {
		return m_iMissionBussinessBandState;
	}


	public void setM_iMissionBussinessBandState(int mIMissionBussinessBandState) {
		m_iMissionBussinessBandState = mIMissionBussinessBandState;
	}

	public void setM_iMissionDelayState(int mIMissionDelayState) {
		m_iMissionDelayState = mIMissionDelayState;
	}

	public void setM_iMissionId(int mIMissionId) {
		m_iMissionId = mIMissionId;
	}

	public void setM_iMissionState(int mIMissionState) {
		m_iMissionState = mIMissionState;
	}

	public void setM_sMissionContent(String mSMissionContent) {
		m_sMissionContent = mSMissionContent;
	}

	public void setM_sMissionDeadline(String mSMissionDeadline) {
		m_sMissionDeadline = mSMissionDeadline;
	}

	public void setM_sMissionPubdate(String mSMissionPubdate) {
		m_sMissionPubdate = mSMissionPubdate;
	}


	@Override
	public String toString() {
		return "CEntityMission [m_iMissionDelayState=" + m_iMissionDelayState
				+ ", m_iMissionId=" + m_iMissionId + ", m_iMissionState="
				+ m_iMissionState + ", m_sMissionContent=" + m_sMissionContent
				+ ", m_sMissionDeadline=" + m_sMissionDeadline
				+ ", m_sMissionPubdate=" + m_sMissionPubdate + "]";
	}
	
	
}
