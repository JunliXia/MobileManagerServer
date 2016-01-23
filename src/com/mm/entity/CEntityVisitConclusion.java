package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//拜访总结
@Entity
@Table(name = "VisitConclusion")
public class CEntityVisitConclusion {
	private int m_iVisitConclusionId; // 拜访总结好，主键，自增
	private int m_iVisitCheck; // 拜访审核结果(0为默认，1为审核通过，2为审核不通过)
	private String m_sVisitSubmitTime; // 拜访提交时间
	private String m_sVisitSummary; // 拜访总结
	private String m_sVisitCommand; // 客户需求
	private String m_sVisitAccessoryPath; // 客户附件路径
	
	//方便查询
	private int m_iVisitPlanId;
	private int m_iEmployeeId;
	@Column(name = "VisitPlanId")
	public int getM_iVisitPlanId() {
		return m_iVisitPlanId;
	}

	public void setM_iVisitPlanId(int mIVisitPlanId) {
		m_iVisitPlanId = mIVisitPlanId;
	}
	@Column(name = "EmployeeId")
	public int getM_iEmployeeId() {
		return m_iEmployeeId;
	}
	

	public void setM_iEmployeeId(int mIEmployeeId) {
		m_iEmployeeId = mIEmployeeId;
	}
	public CEntityVisitConclusion(int mIVisitConclusionId, int mIVisitCheck,
			String mSVisitSubmitTime, String mSVisitSummary,
			String mSVisitCommand, String mSVisitAccessoryPath,int mIVisitPlanId) {
		m_iVisitConclusionId = mIVisitConclusionId;
		m_iVisitCheck = mIVisitCheck;
		m_sVisitSubmitTime = mSVisitSubmitTime;
		m_sVisitSummary = mSVisitSummary;
		m_sVisitCommand = mSVisitCommand;
		m_sVisitAccessoryPath = mSVisitAccessoryPath;
		m_iVisitPlanId=mIVisitPlanId;
	}

	public CEntityVisitConclusion() {
	}

	public static class Builder {
		private int m_iVisitConclusionId; // 拜访总结好，主键，自增
		private int m_iVisitCheck; // 拜访审核结果(0为默认，1为审核通过，2为审核不通过)
		private String m_sVisitSubmitTime; // 拜访提交时间
		private String m_sVisitSummary; // 拜访总结
		private String m_sVisitCommand; // 客户需求
		private String m_sVisitAccessoryPath; // 客户附件路径

		public Builder() {
		}

		public Builder VisitConclusionId(int m_iVisitConclusionId) {
			this.m_iVisitConclusionId = m_iVisitConclusionId;
			return this;
		}

		public Builder VisitCheck(int m_iVisitCheck) {
			this.m_iVisitCheck = m_iVisitCheck;
			return this;
		}

		public Builder VisitSubmitTime(String m_sVisitSubmitTime) {
			this.m_sVisitSubmitTime = m_sVisitSubmitTime;
			return this;
		}

		public Builder VisitSummary(String m_sVisitSummary) {
			this.m_sVisitSummary = m_sVisitSummary;
			return this;
		}

		public Builder VisitCommand(String m_sVisitCommand) {
			this.m_sVisitCommand = m_sVisitCommand;
			return this;
		}

		public Builder VisitAccessoryPath(String m_sVisitAccessoryPath) {
			this.m_sVisitAccessoryPath = m_sVisitAccessoryPath;
			return this;
		}

		public CEntityVisitConclusion build() {
			return new CEntityVisitConclusion(this);
		}
	}

	private CEntityVisitConclusion(Builder b) {
		m_iVisitConclusionId = b.m_iVisitConclusionId;
		m_iVisitCheck = b.m_iVisitCheck;
		m_sVisitSubmitTime = b.m_sVisitSubmitTime;
		m_sVisitSummary = b.m_sVisitSummary;
		m_sVisitCommand = b.m_sVisitCommand;
		m_sVisitAccessoryPath = b.m_sVisitAccessoryPath;
	}

	public static class BuildJsonObject {

		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}

		public BuildJsonObject VisitConclusionId(int m_iVisitConclusionId) {
			outjson.put(MyOpcode.VisitConclusion.VisitConclusionId,
					m_iVisitConclusionId);
			return this;
		}

		public BuildJsonObject VisitCheck(int m_iVisitCheck) {
			outjson.put(MyOpcode.VisitConclusion.VisitCheck, m_iVisitCheck);
			return this;
		}

		public BuildJsonObject VisitSubmitTime(String m_sVisitSubmitTime) {
			outjson.put(MyOpcode.VisitConclusion.VisitSubmitTime,
					m_sVisitSubmitTime);
			return this;
		}

		public BuildJsonObject VisitSummary(String m_sVisitSummary) {
			outjson.put(MyOpcode.VisitConclusion.VisitSummary, m_sVisitSummary);
			return this;
		}

		public BuildJsonObject VisitCommand(String m_sVisitCommand) {
			outjson.put(MyOpcode.VisitConclusion.VisitCommand, m_sVisitCommand);
			return this;
		}

		public BuildJsonObject VisitAccessoryPath(String m_sVisitAccessoryPath) {
			outjson.put(MyOpcode.VisitConclusion.VisitAccessoryPath,
					m_sVisitAccessoryPath);
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
			outjson.put(MyOpcode.VisitConclusion.VisitConclusionList, array);
			return this;
		}

		public BuildJsonObject ToSingle(CEntityVisitConclusion cEntityVisitConclusion) {

			outjson.put(MyOpcode.VisitConclusion.VisitConclusionId,
					cEntityVisitConclusion.m_iVisitConclusionId);
			outjson.put(MyOpcode.VisitConclusion.VisitCheck,
					cEntityVisitConclusion.m_iVisitCheck);
			outjson.put(MyOpcode.VisitConclusion.VisitSubmitTime,
					cEntityVisitConclusion.m_sVisitSubmitTime);
			outjson.put(MyOpcode.VisitConclusion.VisitSummary,
					cEntityVisitConclusion.m_sVisitSummary);
			outjson.put(MyOpcode.VisitConclusion.VisitCommand,
					cEntityVisitConclusion.m_sVisitCommand);
			outjson.put(MyOpcode.VisitConclusion.VisitAccessoryPath,
					cEntityVisitConclusion.m_sVisitAccessoryPath);
			return this;
		}

		public JSONObject build() {
			return outjson;
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "VisitConclusionId")
	public int getM_iVisitConclusionId() {
		return m_iVisitConclusionId;
	}

	@Column(name = "VisitCheck")
	public int getM_iVisitCheck() {
		return m_iVisitCheck;
	}

	@Column(name = "VisitAccessoryPath")
	public String getM_sVisitAccessoryPath() {
		return m_sVisitAccessoryPath;
	}

	@Column(name = "VisitCommand")
	public String getM_sVisitCommand() {
		return m_sVisitCommand;
	}

	@Column(name = "VisitSubmitTime")
	public String getM_sVisitSubmitTime() {
		return m_sVisitSubmitTime;
	}

	@Column(name = "VisitSummary")
	public String getM_sVisitSummary() {
		return m_sVisitSummary;
	}

	public void setM_iVisitCheck(int mIVisitCheck) {
		m_iVisitCheck = mIVisitCheck;
	}

	public void setM_iVisitConclusionId(int mIVisitConclusionId) {
		m_iVisitConclusionId = mIVisitConclusionId;
	}

	public void setM_sVisitAccessoryPath(String mSVisitAccessoryPath) {
		m_sVisitAccessoryPath = mSVisitAccessoryPath;
	}

	public void setM_sVisitCommand(String mSVisitCommand) {
		m_sVisitCommand = mSVisitCommand;
	}

	public void setM_sVisitSubmitTime(String mSVisitSubmitTime) {
		m_sVisitSubmitTime = mSVisitSubmitTime;
	}

	public void setM_sVisitSummary(String mSVisitSummary) {
		m_sVisitSummary = mSVisitSummary;
	}

	@Override
	public String toString() {
		return "CEntityVisitConclusion [m_iEmployeeId=" + m_iEmployeeId
				+ ", m_iVisitCheck=" + m_iVisitCheck
				+ ", m_iVisitConclusionId=" + m_iVisitConclusionId
				+ ", m_iVisitPlanId=" + m_iVisitPlanId
				+ ", m_sVisitAccessoryPath=" + m_sVisitAccessoryPath
				+ ", m_sVisitCommand=" + m_sVisitCommand
				+ ", m_sVisitSubmitTime=" + m_sVisitSubmitTime
				+ ", m_sVisitSummary=" + m_sVisitSummary + "]";
	}


	
	
}
