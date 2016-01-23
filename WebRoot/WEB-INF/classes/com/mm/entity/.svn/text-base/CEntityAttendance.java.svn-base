package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//考勤
@Entity
@Table(name = "Attendance")
public class CEntityAttendance {
	private int m_iAttendanceId; // 考勤自增号,主键
	private String m_sAttendanceRegisterTime; // 签到时间
	private String m_sAttendanceSignoutTime; // 签退时间
	private String m_sAttendanceDate;//考勤日期
	private CEntityEmployee cEntityEmployee;

	
	


	public CEntityAttendance(int mIAttendanceId,
			String mSAttendanceRegisterTime, String mSAttendanceSignoutTime,
			String mSAttendanceDate) {
		m_iAttendanceId = mIAttendanceId;
		m_sAttendanceRegisterTime = mSAttendanceRegisterTime;
		m_sAttendanceSignoutTime = mSAttendanceSignoutTime;
		m_sAttendanceDate = mSAttendanceDate;
	}

	public static class BuildJsonObject{
		
		private JSONObject outjson = new JSONObject();

		
		public BuildJsonObject(){}
		
		public BuildJsonObject AttendanceId(int m_iAttendanceId){
			outjson.put(MyOpcode.Attendance.AttendanceId, m_iAttendanceId);
			return this;
		}
		public BuildJsonObject AttendanceRegisterTime(String m_sAttendanceRegisterTime){
			if(m_sAttendanceRegisterTime==null){
				m_sAttendanceRegisterTime="";
			}
			outjson.put(MyOpcode.Attendance.AttendanceRegisterTime, m_sAttendanceRegisterTime);
			return this;
		}
		
		public BuildJsonObject AttendanceSignoutTime(String m_sAttendanceSignoutTime){
			if(m_sAttendanceSignoutTime==null){
				m_sAttendanceSignoutTime="";
			}
			outjson.put(MyOpcode.Attendance.AttendanceSignoutTime, m_sAttendanceSignoutTime);
			return this;
		}
		
		public BuildJsonObject AttendanceDate(String m_sAttendanceDate){
			outjson.put(MyOpcode.Attendance.AttendanceDate, m_sAttendanceDate);
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
			outjson.put(MyOpcode.Attendance.AttendanceList, array);
			return this;
		}
		
		public BuildJsonObject ToSingle(CEntityAttendance cEntityAttendance){
			outjson.put(MyOpcode.Attendance.AttendanceId, cEntityAttendance.m_iAttendanceId);
			outjson.put(MyOpcode.Attendance.AttendanceRegisterTime, cEntityAttendance.m_sAttendanceRegisterTime);
			outjson.put(MyOpcode.Attendance.AttendanceSignoutTime, cEntityAttendance.m_sAttendanceSignoutTime);
			outjson.put(MyOpcode.Attendance.AttendanceDate, cEntityAttendance.m_sAttendanceDate);
			return this;
		}
		
		
		public JSONObject build(){
			return outjson;
		}
	}
	
	
	
	public CEntityAttendance() {
	}
	
	public static class Builder{
		private int m_iAttendanceId; // 考勤自增号,主键
		private String m_sAttendanceRegisterTime; // 签到时间
		private String m_sAttendanceSignoutTime; // 签退时间
		private String m_sAttendanceDate;//考勤日期
		
		public Builder(){}
		
		public Builder AttendanceId(int m_iAttendanceId){
			this.m_iAttendanceId=m_iAttendanceId;
			return this;
		}
		public Builder AttendanceRegisterTime(String m_sAttendanceRegisterTime){
			this.m_sAttendanceRegisterTime=m_sAttendanceRegisterTime;
			return this;
		}
		
		public Builder AttendanceSignoutTime(String m_sAttendanceSignoutTime){
			this.m_sAttendanceSignoutTime=m_sAttendanceSignoutTime;
			return this;
		}
		
		public Builder AttendanceDate(String m_sAttendanceDate){
			this.m_sAttendanceDate=m_sAttendanceDate;
			return this;
		}
		
		public CEntityAttendance build(){
			return new CEntityAttendance(this);
		}
	}
	
	private CEntityAttendance(Builder b){
		m_iAttendanceId=b.m_iAttendanceId;
		m_sAttendanceRegisterTime=b.m_sAttendanceRegisterTime;
		m_sAttendanceSignoutTime=b.m_sAttendanceSignoutTime;
		m_sAttendanceDate=b.m_sAttendanceDate;
	}

	@Id
	@GeneratedValue
	@Column(name = "AttendanceId")
	public int getM_iAttendanceId() {
		return m_iAttendanceId;
	}

	@Column(name = "AttendanceRegisterTime")
	public String getM_sAttendanceRegisterTime() {
		return m_sAttendanceRegisterTime;
	}

	@Column(name = "AttendanceSignoutTime")
	public String getM_sAttendanceSignoutTime() {
		return m_sAttendanceSignoutTime;
	}
	@Column(name="AttendanceDate")
	public String getM_sAttendanceDate() {
		return m_sAttendanceDate;
	}
	@ManyToOne
	@JoinColumn(name = "EmployeeId") 
	public CEntityEmployee getcEntityEmployee() {
		return cEntityEmployee;
	}

	public void setcEntityEmployee(CEntityEmployee cEntityEmployee) {
		this.cEntityEmployee = cEntityEmployee;
	}
	public void setM_sAttendanceDate(String mSAttendanceDate) {
		m_sAttendanceDate = mSAttendanceDate;
	}

	public void setM_iAttendanceId(int mIAttendanceId) {
		m_iAttendanceId = mIAttendanceId;
	}

	public void setM_sAttendanceRegisterTime(String mSAttendanceRegisterTime) {
		m_sAttendanceRegisterTime = mSAttendanceRegisterTime;
	}

	public void setM_sAttendanceSignoutTime(String mSAttendanceSignoutTime) {
		m_sAttendanceSignoutTime = mSAttendanceSignoutTime;
	}

	@Override
	public String toString() {
		return "CEntityAttendance [m_iAttendanceId=" + m_iAttendanceId
				+ ", m_sAttendanceDate=" + m_sAttendanceDate
				+ ", m_sAttendanceRegisterTime=" + m_sAttendanceRegisterTime
				+ ", m_sAttendanceSignoutTime=" + m_sAttendanceSignoutTime
				+ "]";
	}

	
}
