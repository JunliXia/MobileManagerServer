package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//出差不良记录
@Entity
@Table(name="BussinessBadrecord")
public class CEntityBussinessBadrecord {
	private int	m_iBussinessBadrecordId;		//出差不良记录号，主键，自增
	private String	m_sBussinessBadrecordTime;	//出差不良记录时间
	private String	m_sBussinessBadrecordReason;//出差不良记录原因
	
	private CEntityBussiness cEntityBussiness;

	
	public CEntityBussinessBadrecord() {

	}
	public static class Builder{
		private int	m_iBussinessBadrecordId;		//出差不良记录号，主键，自增
		private String	m_sBussinessBadrecordTime;	//出差不良记录时间
		private String	m_sBussinessBadrecordReason;//出差不良记录原因
		
		public Builder(){}
		
		public Builder BussinessBadrecordId(int m_iBussinessBadrecordId){
			this.m_iBussinessBadrecordId=m_iBussinessBadrecordId;
			return this;
		}
		public Builder BussinessBadrecordTime(String m_sBussinessBadrecordTime){
			this.m_sBussinessBadrecordTime=m_sBussinessBadrecordTime;
			return this;
		}
		
		public Builder BussinessBadrecordReason(String m_sBussinessBadrecordReason){
			this.m_sBussinessBadrecordReason=m_sBussinessBadrecordReason;
			return this;
		}
		
		public CEntityBussinessBadrecord build(){
			return new CEntityBussinessBadrecord(this);
		}
		
	}
	
	private CEntityBussinessBadrecord(Builder b){
		m_iBussinessBadrecordId=b.m_iBussinessBadrecordId;
		m_sBussinessBadrecordTime=b.m_sBussinessBadrecordTime;
		m_sBussinessBadrecordReason=b.m_sBussinessBadrecordReason;
	}
	
	
	@Id
	@GeneratedValue
	@Column(name="BussinessBadrecordId")
	public int getM_iBussinessBadrecordId() {
		return m_iBussinessBadrecordId;
	}
	@Column(name="BussinessBadrecordReason")
	public String getM_sBussinessBadrecordReason() {
		return m_sBussinessBadrecordReason;
	}
	@Column(name="BussinessBadrecordTime")
	public String getM_sBussinessBadrecordTime() {
		return m_sBussinessBadrecordTime;
	}
	@OneToOne
	@JoinColumn(name="BussinessId")
	public CEntityBussiness getcEntityBussiness() {
		return cEntityBussiness;
	}
	public void setcEntityBussiness(CEntityBussiness cEntityBussiness) {
		this.cEntityBussiness = cEntityBussiness;
	}
	public void setM_iBussinessBadrecordId(int mIBussinessBadrecordId) {
		m_iBussinessBadrecordId = mIBussinessBadrecordId;
	}
	public void setM_sBussinessBadrecordReason(String mSBussinessBadrecordReason) {
		m_sBussinessBadrecordReason = mSBussinessBadrecordReason;
	}
	public void setM_sBussinessBadrecordTime(String mSBussinessBadrecordTime) {
		m_sBussinessBadrecordTime = mSBussinessBadrecordTime;
	}

}
