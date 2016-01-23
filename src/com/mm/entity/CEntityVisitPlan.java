package com.mm.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//拜访计划表
@Entity
@Table(name = "VisitPlan")
public class CEntityVisitPlan {
	private int m_iVisitPlanId; // 拜访计划自增号，主键，自增
	private String m_sVisitPlanPubdate; // 拜访发布时间
	private String m_sVisitPlanStartTime; // 客户拜访开始时间
	private String m_sVisitPlanEndTime; // 客户拜访截止时间
	private int m_iVisitPlanState; // 拜访状态(0为未开始,1为执行中,2未审核,3为已审核，4已撤销,5已过期，6已失败)
	private int m_iVisitPlanCycle; // 拜访循环(0为不循环，1为循环)
	private int m_iVisitPlanCycleType; // 循环类型(0为按天，1为按星期，2为按月，3为自定义天数)
	private int m_iVisitPlanCycleNumber;// 循环数
	private int m_iVisitPlanDays; // 拜访限定天数
	private int m_iVisitBussinessBandState; // 拜访计划出差绑定状态(0为未绑定，1为绑定)
	
	//方便查询
//	private int m_iEmployeeId;
//	@Column(name = "EmployeeId")
//	public int getM_iEmployeeId() {
//		return m_iEmployeeId;
//	}
//
//	public void setM_iEmployeeId(int mIEmployeeId) {
//		m_iEmployeeId = mIEmployeeId;
//	}

	private CEntityClient cEntityClient;
	private Set<CEntityVisitConclusion> cEntityVisitConclusions;

	public CEntityVisitPlan() {
	}

	public CEntityVisitPlan(int mIVisitPlanId, String mSVisitPlanPubdate,
			String mSVisitPlanStartTime, String mSVisitPlanEndTime,
			int mIVisitPlanState, int mIVisitPlanCycle,
			int mIVisitPlanCycleType, int mIVisitPlanCycleNumber,
			int mIVisitPlanDays, int mIVisitBussinessBandState) {
		m_iVisitPlanId = mIVisitPlanId;
		m_sVisitPlanPubdate = mSVisitPlanPubdate;
		m_sVisitPlanStartTime = mSVisitPlanStartTime;
		m_sVisitPlanEndTime = mSVisitPlanEndTime;
		m_iVisitPlanState = mIVisitPlanState;
		m_iVisitPlanCycle = mIVisitPlanCycle;
		m_iVisitPlanCycleType = mIVisitPlanCycleType;
		m_iVisitPlanCycleNumber = mIVisitPlanCycleNumber;
		m_iVisitPlanDays = mIVisitPlanDays;
		m_iVisitBussinessBandState = mIVisitBussinessBandState;
	}

	public static class BuildJsonObject {
		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}

		public BuildJsonObject VisitPlanId(int m_iVisitPlanId) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanId, m_iVisitPlanId);
			return this;
		}

		public BuildJsonObject VisitPlanPubdate(String m_sVisitPlanPubdate) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanPubdate,
					m_sVisitPlanPubdate);
			return this;
		}

		public BuildJsonObject VisitPlanStartTime(String m_sVisitPlanStartTime) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanStartTime,
					m_sVisitPlanStartTime);
			return this;
		}

		public BuildJsonObject VisitPlanEndTime(String m_sVisitPlanEndTime) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanEndTime,
					m_sVisitPlanEndTime);
			return this;
		}

		public BuildJsonObject VisitPlanState(int m_iVisitPlanState) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanState, m_iVisitPlanState);
			return this;
		}

		public BuildJsonObject VisitPlanCycle(int m_iVisitPlanCycle) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanCycle, m_iVisitPlanCycle);
			return this;
		}

		public BuildJsonObject VisitPlanCycleType(int m_iVisitPlanCycleType) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanCycleType,
					m_iVisitPlanCycleType);
			return this;
		}

		public BuildJsonObject VisitPlanCycleNumber(int m_iVisitPlanCycleNumber) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanCycleNumber,
					m_iVisitPlanCycleNumber);
			return this;
		}

		public BuildJsonObject VisitPlanDays(int m_iVisitPlanDays) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanDays, m_iVisitPlanDays);
			return this;
		}

		public BuildJsonObject VisitBussinessBandState(
				int m_iVisitBussinessBandState) {
			outjson.put(MyOpcode.VisitPlan.VisitBussinessBandState,
					m_iVisitBussinessBandState);
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
			outjson.put(MyOpcode.VisitPlan.VisitPlanList, array);
			return this;
		}

		public BuildJsonObject ToSingle(CEntityVisitPlan cEntityVisitPlan) {
			outjson.put(MyOpcode.VisitPlan.VisitPlanId,
					cEntityVisitPlan.m_iVisitPlanId);
			outjson.put(MyOpcode.VisitPlan.VisitPlanPubdate,
					cEntityVisitPlan.m_sVisitPlanPubdate);
			outjson.put(MyOpcode.VisitPlan.VisitPlanStartTime,
					cEntityVisitPlan.m_sVisitPlanStartTime);
			outjson.put(MyOpcode.VisitPlan.VisitPlanEndTime,
					cEntityVisitPlan.m_sVisitPlanEndTime);
			outjson.put(MyOpcode.VisitPlan.VisitPlanState,
					cEntityVisitPlan.m_iVisitPlanState);
			outjson.put(MyOpcode.VisitPlan.VisitPlanCycle,
					cEntityVisitPlan.m_iVisitPlanCycle);
			outjson.put(MyOpcode.VisitPlan.VisitPlanCycleType,
					cEntityVisitPlan.m_iVisitPlanCycleType);
			outjson.put(MyOpcode.VisitPlan.VisitPlanCycleNumber,
					cEntityVisitPlan.m_iVisitPlanCycleNumber);
			outjson.put(MyOpcode.VisitPlan.VisitPlanDays,
					cEntityVisitPlan.m_iVisitPlanDays);
			outjson.put(MyOpcode.VisitPlan.VisitBussinessBandState,
					cEntityVisitPlan.m_iVisitBussinessBandState);

			return this;
		}

		public JSONObject build() {
			return outjson;
		}
	}
	

	public static class Builder {
		private int m_iVisitPlanId; // 拜访计划自增号，主键，自增
		private String m_sVisitPlanPubdate; // 拜访发布时间
		private String m_sVisitPlanStartTime; // 客户拜访时间
		private String m_sVisitPlanEndTime; // 客户拜访截止时间
		private int m_iVisitPlanState; // 拜访状态(0为未开始,1为执行中,2未审核,3为已审核，4已撤销,5已过期，6已失败)
		private int m_iVisitPlanCycle; // 拜访循环(0为不循环，1为循环)
		private int m_iVisitPlanCycleType; // 循环类型(0为按天，1为按星期，2为按月，3为自定义天数)
		private int m_iVisitPlanCycleNumber;// 循环数
		private int m_iVisitPlanDays; // 拜访限定天数
		private int m_iVisitBussinessBandState; // 拜访计划出差绑定状态(0为未绑定，1为绑定)

		public Builder() {
		}

		public Builder VisitPlanId(int m_iVisitPlanId) {
			this.m_iVisitPlanId = m_iVisitPlanId;
			return this;
		}

		public Builder VisitPlanPubdate(String m_sVisitPlanPubdate) {
			this.m_sVisitPlanPubdate = m_sVisitPlanPubdate;
			return this;
		}

		public Builder VisitPlanStartTime(String m_sVisitPlanStartTime) {
			this.m_sVisitPlanStartTime = m_sVisitPlanStartTime;
			return this;
		}

		public Builder VisitPlanState(int m_iVisitPlanState) {
			this.m_iVisitPlanState = m_iVisitPlanState;
			return this;
		}

		public Builder VisitPlanCycle(int m_iVisitPlanCycle) {
			this.m_iVisitPlanCycle = m_iVisitPlanCycle;
			return this;
		}

		public Builder VisitPlanCycleType(int m_iVisitPlanCycleType) {
			this.m_iVisitPlanCycleType = m_iVisitPlanCycleType;
			return this;
		}

		public Builder VisitPlanCycleNumber(int m_iVisitPlanCycleNumber) {
			this.m_iVisitPlanCycleNumber = m_iVisitPlanCycleNumber;
			return this;
		}

		public Builder VisitPlanDays(int m_iVisitPlanDays) {
			this.m_iVisitPlanDays = m_iVisitPlanDays;
			return this;
		}

		public Builder VisitBussinessBandState(int m_iVisitBussinessBandState) {
			this.m_iVisitBussinessBandState = m_iVisitBussinessBandState;
			return this;
		}

		public Builder VisitPlanEndTime(String m_sVisitPlanEndTime) {
			this.m_sVisitPlanEndTime = m_sVisitPlanEndTime;
			return this;
		}

		public CEntityVisitPlan build() {
			return new CEntityVisitPlan(this);
		}
	}

	private CEntityVisitPlan(Builder b) {
		m_iVisitPlanId = b.m_iVisitPlanId;
		m_sVisitPlanPubdate = b.m_sVisitPlanPubdate;
		m_sVisitPlanStartTime = b.m_sVisitPlanStartTime;
		m_iVisitPlanState = b.m_iVisitPlanState;
		m_iVisitPlanCycle = b.m_iVisitPlanCycle;
		m_iVisitPlanCycleType = b.m_iVisitPlanCycleType;
		m_iVisitPlanCycleNumber = b.m_iVisitPlanCycleNumber;
		m_iVisitPlanDays = b.m_iVisitPlanDays;
		m_iVisitBussinessBandState = b.m_iVisitBussinessBandState;
		m_sVisitPlanEndTime = b.m_sVisitPlanEndTime;
	}

	@Id
	@GeneratedValue
	@Column(name = "VisitPlanId")
	public int getM_iVisitPlanId() {
		return m_iVisitPlanId;
	}

	@Column(name = "VisitPlanCycle")
	public int getM_iVisitPlanCycle() {
		return m_iVisitPlanCycle;
	}

	@Column(name = "VisitPlanCycleNumber")
	public int getM_iVisitPlanCycleNumber() {
		return m_iVisitPlanCycleNumber;
	}

	@Column(name = "VisitPlanCycleType")
	public int getM_iVisitPlanCycleType() {
		return m_iVisitPlanCycleType;
	}

	@Column(name = "VisitPlanDays")
	public int getM_iVisitPlanDays() {
		return m_iVisitPlanDays;
	}

	@Column(name = "VisitPlanState")
	public int getM_iVisitPlanState() {
		return m_iVisitPlanState;
	}

	@Column(name = "VisitPlanPubdate")
	public String getM_sVisitPlanPubdate() {
		return m_sVisitPlanPubdate;
	}

	@Column(name = "VisitPlanStartTime")
	public String getM_sVisitPlanStartTime() {
		return m_sVisitPlanStartTime;
	}

	@OneToOne
	@JoinColumn(name = "ClientId")
	public CEntityClient getcEntityClient() {
		return cEntityClient;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "VisitPlanId")
	public Set<CEntityVisitConclusion> getcEntityVisitConclusions() {
		return cEntityVisitConclusions;
	}

	@Column(name = "VisitBussinessBandState")
	public int getM_iVisitBussinessBandState() {
		return m_iVisitBussinessBandState;
	}

	@Column(name = "VisitPlanEndTime")
	public String getM_sVisitPlanEndTime() {
		return m_sVisitPlanEndTime;
	}

	public void setM_sVisitPlanEndTime(String mSVisitPlanEndTime) {
		m_sVisitPlanEndTime = mSVisitPlanEndTime;
	}

	public void setM_sVisitPlanStartTime(String mSVisitPlanStartTime) {
		m_sVisitPlanStartTime = mSVisitPlanStartTime;
	}

	public void setM_iVisitBussinessBandState(int mIVisitBussinessBandState) {
		m_iVisitBussinessBandState = mIVisitBussinessBandState;
	}

	public void setcEntityVisitConclusions(
			Set<CEntityVisitConclusion> cEntityVisitConclusions) {
		this.cEntityVisitConclusions = cEntityVisitConclusions;
	}

	public void setcEntityClient(CEntityClient cEntityClient) {
		this.cEntityClient = cEntityClient;
	}

	public void setM_iVisitPlanCycle(int mIVisitPlanCycle) {
		m_iVisitPlanCycle = mIVisitPlanCycle;
	}

	public void setM_iVisitPlanCycleNumber(int mIVisitPlanCycleNumber) {
		m_iVisitPlanCycleNumber = mIVisitPlanCycleNumber;
	}

	public void setM_iVisitPlanCycleType(int mIVisitPlanCycleType) {
		m_iVisitPlanCycleType = mIVisitPlanCycleType;
	}

	public void setM_iVisitPlanDays(int mIVisitPlanDays) {
		m_iVisitPlanDays = mIVisitPlanDays;
	}

	public void setM_iVisitPlanId(int mIVisitPlanId) {
		m_iVisitPlanId = mIVisitPlanId;
	}

	public void setM_iVisitPlanState(int mIVisitPlanState) {
		m_iVisitPlanState = mIVisitPlanState;
	}

	public void setM_sVisitPlanPubdate(String mSVisitPlanPubdate) {
		m_sVisitPlanPubdate = mSVisitPlanPubdate;
	}

	@Override
	public String toString() {
		return "CEntityVisitPlan [m_iVisitBussinessBandState="
				+ m_iVisitBussinessBandState + ", m_iVisitPlanCycle="
				+ m_iVisitPlanCycle + ", m_iVisitPlanCycleNumber="
				+ m_iVisitPlanCycleNumber + ", m_iVisitPlanCycleType="
				+ m_iVisitPlanCycleType + ", m_iVisitPlanDays="
				+ m_iVisitPlanDays + ", m_iVisitPlanId=" + m_iVisitPlanId
				+ ", m_iVisitPlanState=" + m_iVisitPlanState
				+ ", m_sVisitPlanEndTime=" + m_sVisitPlanEndTime
				+ ", m_sVisitPlanPubdate=" + m_sVisitPlanPubdate
				+ ", m_sVisitPlanStartTime=" + m_sVisitPlanStartTime + "]";
	}
	
	
	
	
	
	
	
	
	

}
