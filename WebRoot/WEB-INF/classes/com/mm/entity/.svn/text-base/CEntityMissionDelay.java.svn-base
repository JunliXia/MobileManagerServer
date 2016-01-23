package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONObject;

//任务延期
@Entity
@Table(name = "MissionDelay")
public class CEntityMissionDelay {

	private int m_iMissionDelayId; // 任务延期号，主键，自增
	private String m_sMissionDelayTime; // 任务延期时间
	private String m_sMissionDelayDeadline; // 任务延期期限

	private CEntityMission cEntityMission;

	public CEntityMissionDelay() {
	}

	public static class BuildJsonObject {
		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}

		public BuildJsonObject MissionDelayId(int m_iMissionDelayId) {
			outjson
					.put(MyOpcode.MissionDelay.MissionDelayId,
							m_iMissionDelayId);
			return this;
		}

		public BuildJsonObject MissionDelayTime(String m_sMissionDelayTime) {
			outjson.put(MyOpcode.MissionDelay.MissionDelayTime,
					m_sMissionDelayTime);
			return this;
		}

		public BuildJsonObject MissionDelayDeadline(
				String m_sMissionDelayDeadline) {
			outjson.put(MyOpcode.MissionDelay.MissionDelayDeadline,
					m_sMissionDelayDeadline);
			return this;
		}

		public BuildJsonObject Operation(int operation) {
			outjson.put(MyOpcode.Operation.OPERATION, operation);
			return this;
		}

		public BuildJsonObject Check(boolean check) {
			outjson.put(MyOpcode.Operation.CHECK, check);
			return this;
		}

		public BuildJsonObject ToSingle(CEntityMissionDelay cEntityMissionDelay) {
			if (cEntityMissionDelay == null) {
				return this;
			}
			outjson.put(MyOpcode.MissionDelay.MissionDelayId,
					cEntityMissionDelay.m_iMissionDelayId);
			outjson.put(MyOpcode.MissionDelay.MissionDelayTime,
					cEntityMissionDelay.m_sMissionDelayTime);
			outjson.put(MyOpcode.MissionDelay.MissionDelayDeadline,
					cEntityMissionDelay.m_sMissionDelayDeadline);
			return this;
		}

		public JSONObject build() {
			return outjson;
		}
	}

	public static class Builder {
		private int m_iMissionDelayId; // 任务延期号，主键，自增
		private String m_sMissionDelayTime; // 任务延期时间
		private String m_sMissionDelayDeadline; // 任务延期期限

		public Builder() {
		}

		public Builder MissionDelayId(int m_iMissionDelayId) {
			this.m_iMissionDelayId = m_iMissionDelayId;
			return this;
		}

		public Builder MissionDelayTime(String m_sMissionDelayTime) {
			this.m_sMissionDelayTime = m_sMissionDelayTime;
			return this;
		}

		public Builder MissionDelayDeadline(String m_sMissionDelayDeadline) {
			this.m_sMissionDelayDeadline = m_sMissionDelayDeadline;
			return this;
		}

		public CEntityMissionDelay build() {
			return new CEntityMissionDelay(this);
		}

	}

	private CEntityMissionDelay(Builder b) {
		m_iMissionDelayId = b.m_iMissionDelayId;
		m_sMissionDelayTime = b.m_sMissionDelayTime;
		m_sMissionDelayDeadline = b.m_sMissionDelayDeadline;
	}

	@Id
	@GeneratedValue
	@Column(name = "MissionDelayId")
	public int getM_iMissionDelayId() {
		return m_iMissionDelayId;
	}

	@Column(name = "MissionDelayDeadline")
	public String getM_sMissionDelayDeadline() {
		return m_sMissionDelayDeadline;
	}

	@Column(name = "MissionDelayTime")
	public String getM_sMissionDelayTime() {
		return m_sMissionDelayTime;
	}

	@OneToOne
	@JoinColumn(name = "MissionId")
	public CEntityMission getcEntityMission() {
		return cEntityMission;
	}

	public void setcEntityMission(CEntityMission cEntityMission) {
		this.cEntityMission = cEntityMission;
	}

	public void setM_iMissionDelayId(int mIMissionDelayId) {
		m_iMissionDelayId = mIMissionDelayId;
	}

	public void setM_sMissionDelayDeadline(String mSMissionDelayDeadline) {
		m_sMissionDelayDeadline = mSMissionDelayDeadline;
	}

	public void setM_sMissionDelayTime(String mSMissionDelayTime) {
		m_sMissionDelayTime = mSMissionDelayTime;
	}
}
