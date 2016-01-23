package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//任务总结
@Entity
@Table(name = "MissionConclusion")
public class CEntityMissionConclusion {
	private int m_iMissionConclusionId; // 任务附件号,主键，自增
	private int m_iMissionCheck; // 任务审核结果(0为默认，1为审核通过，2为审核不通过)
	private String m_sMissionSummary; // 任务总结
	private String m_sMissionSubmitTime; // 任务提交时间
	private String m_sMissionAccessoryPath; // 任务附件路径

	private CEntityMission cEntityMission;

	public CEntityMissionConclusion() {
	}
	
	

	public CEntityMissionConclusion(int mIMissionConclusionId,
			int mIMissionCheck, String mSMissionSummary,
			String mSMissionSubmitTime, String mSMissionAccessoryPath,
			CEntityMission cEntityMission) {
		m_iMissionConclusionId = mIMissionConclusionId;
		m_iMissionCheck = mIMissionCheck;
		m_sMissionSummary = mSMissionSummary;
		m_sMissionSubmitTime = mSMissionSubmitTime;
		m_sMissionAccessoryPath = mSMissionAccessoryPath;
		this.cEntityMission = cEntityMission;
	}



	public static class BuildJsonObject {

		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}

		public BuildJsonObject MissionConclusionId(int m_iMissionConclusionId) {
			outjson.put(MyOpcode.MissionConclusion.MissionConclusionId,
					m_iMissionConclusionId);
			return this;
		}

		public BuildJsonObject MissionCheck(int m_iMissionCheck) {
			outjson.put(MyOpcode.MissionConclusion.MissionCheck,
					m_iMissionCheck);
			return this;
		}

		public BuildJsonObject MissionSummary(String m_sMissionSummary) {
			outjson.put(MyOpcode.MissionConclusion.MissionSummary,
					m_sMissionSummary);
			return this;
		}

		public BuildJsonObject MissionSubmitTime(String m_sMissionSubmitTime) {
			outjson.put(MyOpcode.MissionConclusion.MissionSubmitTime,
					m_sMissionSubmitTime);
			return this;
		}

		public BuildJsonObject MissionAccessoryPath(
				String m_sMissionAccessoryPath) {
			outjson.put(MyOpcode.MissionConclusion.MissionAccessoryPath,
					m_sMissionAccessoryPath);
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

		public BuildJsonObject MyJSONArray(JSONArray array) {
			outjson
					.put(MyOpcode.MissionConclusion.MissionConclusionList,
							array);
			return this;
		}

		public BuildJsonObject ToSingle(
				CEntityMissionConclusion cEntityMissionConclusion) {
			if(cEntityMissionConclusion==null){
				return this;
			}
			outjson.put(MyOpcode.MissionConclusion.MissionConclusionId,
					cEntityMissionConclusion.m_iMissionConclusionId);
			outjson.put(MyOpcode.MissionConclusion.MissionCheck,
					cEntityMissionConclusion.m_iMissionCheck);
			outjson.put(MyOpcode.MissionConclusion.MissionSummary,
					cEntityMissionConclusion.m_sMissionSummary);
			outjson.put(MyOpcode.MissionConclusion.MissionSubmitTime,
					cEntityMissionConclusion.m_sMissionSubmitTime);
			outjson.put(MyOpcode.MissionConclusion.MissionAccessoryPath,
					cEntityMissionConclusion.m_sMissionAccessoryPath);
			return this;
		}

		public JSONObject build() {
			return outjson;
		}
	}

	public static class Builder {
		private int m_iMissionConclusionId; // 任务附件号,主键，自增
		private int m_iMissionCheck = 0; // 任务审核结果(0为默认，1为审核通过，2为审核不通过)
		private String m_sMissionSummary; // 任务总结
		private String m_sMissionSubmitTime; // 任务提交时间
		private String m_sMissionAccessoryPath; // 任务附件路径

		public Builder() {
		}

		public Builder MissionConclusionId(int m_iMissionConclusionId) {
			this.m_iMissionConclusionId = m_iMissionConclusionId;
			return this;
		}

		public Builder MissionCheck(int m_iMissionCheck) {
			this.m_iMissionCheck = m_iMissionCheck;
			return this;
		}

		public Builder MissionSummary(String m_sMissionSummary) {
			this.m_sMissionSummary = m_sMissionSummary;
			return this;
		}

		public Builder MissionSubmitTime(String m_sMissionSubmitTime) {
			this.m_sMissionSubmitTime = m_sMissionSubmitTime;
			return this;
		}

		public Builder MissionAccessoryPath(String m_sMissionAccessoryPath) {
			this.m_sMissionAccessoryPath = m_sMissionAccessoryPath;
			return this;
		}

		public CEntityMissionConclusion build() {
			return new CEntityMissionConclusion(this);
		}

	}

	private CEntityMissionConclusion(Builder b) {
		m_iMissionConclusionId = b.m_iMissionConclusionId;
		m_iMissionCheck = b.m_iMissionCheck;
		m_sMissionSummary = b.m_sMissionSummary;
		m_sMissionSubmitTime = b.m_sMissionSubmitTime;
		m_sMissionAccessoryPath = b.m_sMissionAccessoryPath;
	}

	@Id
	@GeneratedValue
	@Column(name = "MissionConclusionId")
	public int getM_iMissionConclusionId() {
		return m_iMissionConclusionId;
	}

	@Column(name = "MissionCheck")
	public int getM_iMissionCheck() {
		return m_iMissionCheck;
	}

	@Column(name = "MissionAccessoryPath")
	public String getM_sMissionAccessoryPath() {
		return m_sMissionAccessoryPath;
	}

	@Column(name = "MissionSubmitTime")
	public String getM_sMissionSubmitTime() {
		return m_sMissionSubmitTime;
	}

	@Column(name = "MissionSummary")
	public String getM_sMissionSummary() {
		return m_sMissionSummary;
	}

	@OneToOne
	@JoinColumn(name = "MissionId")
	public CEntityMission getcEntityMission() {
		return cEntityMission;
	}

	public void setcEntityMission(CEntityMission cEntityMission) {
		this.cEntityMission = cEntityMission;
	}

	public void setM_iMissionCheck(int mIMissionCheck) {
		m_iMissionCheck = mIMissionCheck;
	}

	public void setM_iMissionConclusionId(int mIMissionConclusionId) {
		m_iMissionConclusionId = mIMissionConclusionId;
	}

	public void setM_sMissionAccessoryPath(String mSMissionAccessoryPath) {
		m_sMissionAccessoryPath = mSMissionAccessoryPath;
	}

	public void setM_sMissionSubmitTime(String mSMissionSubmitTime) {
		m_sMissionSubmitTime = mSMissionSubmitTime;
	}

	public void setM_sMissionSummary(String mSMissionSummary) {
		m_sMissionSummary = mSMissionSummary;
	}

	@Override
	public String toString() {
		return "CEntityMissionConclusion [cEntityMission=" + cEntityMission
				+ ", m_iMissionCheck=" + m_iMissionCheck
				+ ", m_iMissionConclusionId=" + m_iMissionConclusionId
				+ ", m_sMissionAccessoryPath=" + m_sMissionAccessoryPath
				+ ", m_sMissionSubmitTime=" + m_sMissionSubmitTime
				+ ", m_sMissionSummary=" + m_sMissionSummary + "]";
	}

}
