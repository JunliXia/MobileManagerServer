package com.mm.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//出差表
@Entity
@Table(name = "Bussiness")
public class CEntityBussiness {
	private int m_iBussinessId; // 出差号,主键，自增
	private String m_sBussinessSideAddress; // 出差目的地
	private String m_sBussinessContent; // 出差内容
	private String m_sBussinessRegisterTime;// 出差登记时间
	private String m_sBussinessInAddress; // 出差抵达地址
	private String m_sBussinessInTime; // 出差抵达时间
	private String m_sBussinessOutAddress; // 出差离开地址
	private String m_sBussinessOutTime; // 出差离开时间
	private String m_sBussinessReturnTime; // 出差归来时间
	private int m_iBussinessState; // 出差状态 (0为未登记,1为执行中，2为未审核，3审核通过，4审核不通过,5已撤销)

	private Set<CEntityBussinessActivity> cEntityBussinessActivities;
	private CEntityBussinessBadrecord cEntityBussinessBadrecord;
	private CEntityEmployee cEntityEmployee;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeId") 
	public CEntityEmployee getcEntityEmployee() {
		return cEntityEmployee;
	}

	public void setcEntityEmployee(CEntityEmployee cEntityEmployee) {
		this.cEntityEmployee = cEntityEmployee;
	}

	public CEntityBussiness() {

	}

	public CEntityBussiness(int mIBussinessId, String mSBussinessSideAddress,
			String mSBussinessContent, String mSBussinessRegisterTime,
			String mSBussinessInAddress, String mSBussinessInTime,
			String mSBussinessOutAddress, String mSBussinessOutTime,
			String mSBussinessReturnTime, int mIBussinessState) {
		m_iBussinessId = mIBussinessId;
		m_sBussinessSideAddress = mSBussinessSideAddress;
		m_sBussinessContent = mSBussinessContent;
		m_sBussinessRegisterTime = mSBussinessRegisterTime;
		m_sBussinessInAddress = mSBussinessInAddress;
		m_sBussinessInTime = mSBussinessInTime;
		m_sBussinessOutAddress = mSBussinessOutAddress;
		m_sBussinessOutTime = mSBussinessOutTime;
		m_sBussinessReturnTime = mSBussinessReturnTime;
		m_iBussinessState = mIBussinessState;
	}

	public static class BuildJsonObject {
		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}

		public BuildJsonObject BussinessId(int m_iBussinessId) {
			outjson.put(MyOpcode.Bussiness.BussinessId, m_iBussinessId);
			return this;
		}

		public BuildJsonObject BussinessSideAddress(
				String m_sBussinessSideAddress) {
			outjson.put(MyOpcode.Bussiness.BussinessContent,
					m_sBussinessSideAddress);
			return this;
		}

		public BuildJsonObject BussinessContent(String m_sBussinessContent) {
			outjson.put(MyOpcode.Bussiness.BussinessContent,
					m_sBussinessContent);
			return this;
		}

		public BuildJsonObject BussinessRegisterTime(
				String m_sBussinessRegisterTime) {
			outjson.put(MyOpcode.Bussiness.BussinessRegisterTime,
					m_sBussinessRegisterTime);
			return this;
		}

		public BuildJsonObject BussinessInAddress(String m_sBussinessInAddress) {
			outjson.put(MyOpcode.Bussiness.BussinessInAddress,
					m_sBussinessInAddress);
			return this;
		}

		public BuildJsonObject BussinessInTime(String m_sBussinessInTime) {
			outjson.put(MyOpcode.Bussiness.BussinessInTime, m_sBussinessInTime);
			return this;
		}

		public BuildJsonObject BussinessOutAddress(String m_sBussinessOutAddress) {
			outjson.put(MyOpcode.Bussiness.BussinessOutAddress,
					m_sBussinessOutAddress);
			return this;
		}

		public BuildJsonObject BussinessOutTime(String m_sBussinessOutTime) {
			outjson.put(MyOpcode.Bussiness.BussinessOutTime,
					m_sBussinessOutTime);
			return this;
		}

		public BuildJsonObject BussinessReturnTime(String m_sBussinessReturnTime) {
			outjson.put(MyOpcode.Bussiness.BussinessReturnTime,
					m_sBussinessReturnTime);
			return this;
		}

		public BuildJsonObject BussinessState(int m_iBussinessState) {
			outjson.put(MyOpcode.Bussiness.BussinessState, m_iBussinessState);
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
			outjson.put(MyOpcode.Bussiness.BussinessList, array);
			return this;
		}

		public BuildJsonObject ToSingle(CEntityBussiness cEntityBussiness) {
			if(cEntityBussiness==null){
				return this;
			}
			if(cEntityBussiness.m_sBussinessRegisterTime==null){
				cEntityBussiness.m_sBussinessRegisterTime="";
			}
			if(cEntityBussiness.m_sBussinessInAddress==null){
				cEntityBussiness.m_sBussinessInAddress="";
			}
			if(cEntityBussiness.m_sBussinessInTime==null){
				cEntityBussiness.m_sBussinessInTime="";
			}
			if(cEntityBussiness.m_sBussinessOutAddress==null){
				cEntityBussiness.m_sBussinessOutAddress="";
			}
			if(cEntityBussiness.m_sBussinessOutTime==null){
				cEntityBussiness.m_sBussinessOutTime="";
			}
			if(cEntityBussiness.m_sBussinessReturnTime==null){
				cEntityBussiness.m_sBussinessReturnTime="";
			}
			outjson.put(MyOpcode.Bussiness.BussinessId,
					cEntityBussiness.m_iBussinessId);
			outjson.put(MyOpcode.Bussiness.BussinessSideAddress,
					cEntityBussiness.m_sBussinessSideAddress);
			
			outjson.put(MyOpcode.Bussiness.BussinessContent,
					cEntityBussiness.m_sBussinessContent);
			
			outjson.put(MyOpcode.Bussiness.BussinessRegisterTime,
					cEntityBussiness.m_sBussinessRegisterTime);
			outjson.put(MyOpcode.Bussiness.BussinessInAddress,
					cEntityBussiness.m_sBussinessInAddress);
			outjson.put(MyOpcode.Bussiness.BussinessInTime,
					cEntityBussiness.m_sBussinessInTime);
			outjson.put(MyOpcode.Bussiness.BussinessOutAddress,
					cEntityBussiness.m_sBussinessOutAddress);
			outjson.put(MyOpcode.Bussiness.BussinessOutTime,
					cEntityBussiness.m_sBussinessOutTime);
			outjson.put(MyOpcode.Bussiness.BussinessReturnTime,
					cEntityBussiness.m_sBussinessReturnTime);
			outjson.put(MyOpcode.Bussiness.BussinessState,
					cEntityBussiness.m_iBussinessState);
			
			
			return this;
		}

		
		
		public JSONObject build() {
			return outjson;
		}
	}

	public static class Builder {
		private int m_iBussinessId; // 出差号,主键，自增
		private String m_sBussinessSideAddress=""; // 出差目的地
		private String m_sBussinessContent=""; // 出差内容
		private String m_sBussinessRegisterTime="";// 出差登记时间
		private String m_sBussinessInAddress=""; // 出差抵达地址
		private String m_sBussinessInTime=""; // 出差抵达时间
		private String m_sBussinessOutAddress=""; // 出差离开地址
		private String m_sBussinessOutTime=""; // 出差离开时间
		private String m_sBussinessReturnTime=""; // 出差归来时间
		private int m_iBussinessState = 0; // 出差状态
											// (0为未登记,1为执行中，2为未审核，3审核通过，4审核不通过,5已撤销)

		public Builder() {
		}

		public Builder BussinessId(int m_iBussinessId) {
			this.m_iBussinessId = m_iBussinessId;
			return this;
		}

		public Builder BussinessSideAddress(String m_sBussinessSideAddress) {
			this.m_sBussinessSideAddress = m_sBussinessSideAddress;
			return this;
		}

		public Builder BussinessContent(String m_sBussinessContent) {
			this.m_sBussinessContent = m_sBussinessContent;
			return this;
		}

		public Builder BussinessRegisterTime(String m_sBussinessRegisterTime) {
			this.m_sBussinessRegisterTime = m_sBussinessRegisterTime;
			return this;
		}

		public Builder BussinessInAddress(String m_sBussinessInAddress) {
			this.m_sBussinessInAddress = m_sBussinessInAddress;
			return this;
		}

		public Builder BussinessInTime(String m_sBussinessInTime) {
			this.m_sBussinessInTime = m_sBussinessInTime;
			return this;
		}

		public Builder BussinessOutAddress(String m_sBussinessOutAddress) {
			this.m_sBussinessOutAddress = m_sBussinessOutAddress;
			return this;
		}

		public Builder BussinessOutTime(String m_sBussinessOutTime) {
			this.m_sBussinessOutTime = m_sBussinessOutTime;
			return this;
		}

		public Builder BussinessReturnTime(String m_sBussinessReturnTime) {
			this.m_sBussinessReturnTime = m_sBussinessReturnTime;
			return this;
		}

		public Builder BussinessState(int m_iBussinessState) {
			this.m_iBussinessState = m_iBussinessState;
			return this;
		}

		public CEntityBussiness build() {
			return new CEntityBussiness(this);
		}

	}

	private CEntityBussiness(Builder b) {
		m_iBussinessId = b.m_iBussinessId;
		m_sBussinessSideAddress = b.m_sBussinessSideAddress;
		m_sBussinessContent = b.m_sBussinessContent;
		m_sBussinessRegisterTime = b.m_sBussinessRegisterTime;
		m_sBussinessInAddress = b.m_sBussinessInAddress;
		m_sBussinessInTime = b.m_sBussinessInTime;
		m_sBussinessOutAddress = b.m_sBussinessOutAddress;
		m_sBussinessOutTime = b.m_sBussinessOutTime;
		m_sBussinessReturnTime = b.m_sBussinessReturnTime;
		m_iBussinessState = b.m_iBussinessState;
	}

	@Id
	@GeneratedValue
	@Column(name = "BussinessId")
	public int getM_iBussinessId() {
		return m_iBussinessId;
	}

	@Column(name = "BussinessState")
	public int getM_iBussinessState() {
		return m_iBussinessState;
	}

	@Column(name = "BussinessContent")
	public String getM_sBussinessContent() {
		return m_sBussinessContent;
	}

	@Column(name = "BussinessInAddress")
	public String getM_sBussinessInAddress() {
		return m_sBussinessInAddress;
	}

	@Column(name = "BussinessInTime")
	public String getM_sBussinessInTime() {
		return m_sBussinessInTime;
	}

	@Column(name = "BussinessOutAddress")
	public String getM_sBussinessOutAddress() {
		return m_sBussinessOutAddress;
	}

	@Column(name = "BussinessOutTime")
	public String getM_sBussinessOutTime() {
		return m_sBussinessOutTime;
	}

	@Column(name = "BussinessRegisterTime")
	public String getM_sBussinessRegisterTime() {
		return m_sBussinessRegisterTime;
	}

	@Column(name = "BussinessReturnTime")
	public String getM_sBussinessReturnTime() {
		return m_sBussinessReturnTime;
	}

	@Column(name = "BussinessSideAddress")
	public String getM_sBussinessSideAddress() {
		return m_sBussinessSideAddress;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "BussinessId")
	public Set<CEntityBussinessActivity> getcEntityBussinessActivities() {
		return cEntityBussinessActivities;
	}

	@OneToOne
	@JoinColumn(name = "BussinessBadrecordId")
	public CEntityBussinessBadrecord getcEntityBussinessBadrecord() {
		return cEntityBussinessBadrecord;
	}

	public void setcEntityBussinessBadrecord(
			CEntityBussinessBadrecord cEntityBussinessBadrecord) {
		this.cEntityBussinessBadrecord = cEntityBussinessBadrecord;
	}

	public void setcEntityBussinessActivities(
			Set<CEntityBussinessActivity> cEntityBussinessActivities) {
		this.cEntityBussinessActivities = cEntityBussinessActivities;
	}

	public void setM_iBussinessId(int mIBussinessId) {
		m_iBussinessId = mIBussinessId;
	}

	public void setM_iBussinessState(int mIBussinessState) {
		m_iBussinessState = mIBussinessState;
	}

	public void setM_sBussinessContent(String mSBussinessContent) {
		m_sBussinessContent = mSBussinessContent;
	}

	public void setM_sBussinessInAddress(String mSBussinessInAddress) {
		m_sBussinessInAddress = mSBussinessInAddress;
	}

	public void setM_sBussinessInTime(String mSBussinessInTime) {
		m_sBussinessInTime = mSBussinessInTime;
	}

	public void setM_sBussinessOutAddress(String mSBussinessOutAddress) {
		m_sBussinessOutAddress = mSBussinessOutAddress;
	}

	public void setM_sBussinessOutTime(String mSBussinessOutTime) {
		m_sBussinessOutTime = mSBussinessOutTime;
	}

	public void setM_sBussinessRegisterTime(String mSBussinessRegisterTime) {
		m_sBussinessRegisterTime = mSBussinessRegisterTime;
	}

	public void setM_sBussinessReturnTime(String mSBussinessReturnTime) {
		m_sBussinessReturnTime = mSBussinessReturnTime;
	}

	public void setM_sBussinessSideAddress(String mSBussinessSideAddress) {
		m_sBussinessSideAddress = mSBussinessSideAddress;
	}

	@Override
	public String toString() {
		return "CEntityBussiness [m_iBussinessId=" + m_iBussinessId
				+ ", m_iBussinessState=" + m_iBussinessState
				+ ", m_sBussinessContent=" + m_sBussinessContent
				+ ", m_sBussinessInAddress=" + m_sBussinessInAddress
				+ ", m_sBussinessInTime=" + m_sBussinessInTime
				+ ", m_sBussinessOutAddress=" + m_sBussinessOutAddress
				+ ", m_sBussinessOutTime=" + m_sBussinessOutTime
				+ ", m_sBussinessRegisterTime=" + m_sBussinessRegisterTime
				+ ", m_sBussinessReturnTime=" + m_sBussinessReturnTime
				+ ", m_sBussinessSideAddress=" + m_sBussinessSideAddress + "]";
	}

	
	
}
