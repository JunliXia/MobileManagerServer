package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//任务撤销表
@Entity
@Table(name = "MissionUndo")
public class CEntityMissionUndo {
	private int m_iMissionUndoId; // 任务撤销号，主键，自增
	private String m_sMissionUndoReason; // 任务撤销原因
	private String m_sMissionUndoTime; // 任务撤销时间
	private int m_iMissionUndoRecallType; // 任务撤回类型(撤销前的状态--为了撤回时恢复)
	private int m_iMissionUndoType; // 任务撤销类型(0为已撤销，1为已撤回)

	
	public CEntityMissionUndo() {
	}

	private CEntityMission cEntityMission;

	public static class Builder{
		private int m_iMissionUndoId; // 任务撤销号，主键，自增
		private String m_sMissionUndoReason; // 任务撤销原因
		private String m_sMissionUndoTime; // 任务撤销时间
		private int m_iMissionUndoRecallType; // 任务撤回类型(撤销前的状态--为了撤回时恢复)
		private int m_iMissionUndoType=0; // 任务撤销类型(0为已撤销，1为已撤回)
		
		public Builder(){}
		
		public Builder MissionUndoId(int m_iMissionUndoId){
			this.m_iMissionUndoId=m_iMissionUndoId;
			return this;
		}
		public Builder MissionUndoReason(String m_sMissionUndoReason){
			this.m_sMissionUndoReason=m_sMissionUndoReason;
			return this;
		}
		
		public Builder MissionUndoTime(String m_sMissionUndoTime){
			this.m_sMissionUndoTime=m_sMissionUndoTime;
			return this;
		}
		public Builder MissionUndoRecallType(int m_iMissionUndoRecallType){
			this.m_iMissionUndoRecallType=m_iMissionUndoRecallType;
			return this;
		}
		public Builder MissionUndoType(int m_iMissionUndoType){
			this.m_iMissionUndoType=m_iMissionUndoType;
			return this;
		}
		
		public CEntityMissionUndo build(){
			return new CEntityMissionUndo(this);
		}
		
	}

	private CEntityMissionUndo (Builder b){
		m_iMissionUndoId=b.m_iMissionUndoId;
		m_sMissionUndoReason=b.m_sMissionUndoReason;
		m_sMissionUndoTime=b.m_sMissionUndoTime;
		m_iMissionUndoRecallType=b.m_iMissionUndoRecallType;
		m_iMissionUndoType=b.m_iMissionUndoType;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "MissionUndoId")
	
	public int getM_iMissionUndoId() {
		return m_iMissionUndoId;
	}

	@Column(name = "MissionUndoRecallType")
	public int getM_iMissionUndoRecallType() {
		return m_iMissionUndoRecallType;
	}

	@Column(name = "MissionUndoType")
	public int getM_iMissionUndoType() {
		return m_iMissionUndoType;
	}

	@Column(name = "MissionUndoReason")
	public String getM_sMissionUndoReason() {
		return m_sMissionUndoReason;
	}

	@Column(name = "MissionUndoTime")
	public String getM_sMissionUndoTime() {
		return m_sMissionUndoTime;
	}


	@OneToOne
	@JoinColumn(name="MissionId")
	public CEntityMission getcEntityMission() {
		return cEntityMission;
	}

	public void setcEntityMission(CEntityMission cEntityMission) {
		this.cEntityMission = cEntityMission;
	}
	public void setM_iMissionUndoId(int mIMissionUndoId) {
		m_iMissionUndoId = mIMissionUndoId;
	}

	public void setM_iMissionUndoRecallType(int mIMissionUndoRecallType) {
		m_iMissionUndoRecallType = mIMissionUndoRecallType;
	}

	public void setM_iMissionUndoType(int mIMissionUndoType) {
		m_iMissionUndoType = mIMissionUndoType;
	}

	public void setM_sMissionUndoReason(String mSMissionUndoReason) {
		m_sMissionUndoReason = mSMissionUndoReason;
	}

	public void setM_sMissionUndoTime(String mSMissionUndoTime) {
		m_sMissionUndoTime = mSMissionUndoTime;
	}

	@Override
	public String toString() {
		return "CEntityMissionUndo [m_iMissionUndoId=" + m_iMissionUndoId
				+ ", m_iMissionUndoRecallType=" + m_iMissionUndoRecallType
				+ ", m_iMissionUndoType=" + m_iMissionUndoType
				+ ", m_sMissionUndoReason=" + m_sMissionUndoReason
				+ ", m_sMissionUndoTime=" + m_sMissionUndoTime + "]";
	}

}
