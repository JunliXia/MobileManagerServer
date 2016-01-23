package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BussinessUndo")
public class CEntityBussinessUndo {
	private int m_iBussinessUndoId;//出差撤销号,主键，自增
	private String m_sBussinessUndoReason;//出差撤销原因
	private String m_sBussinessUndoTime;//出差撤销时间
	private CEntityBussiness cEntityBussiness;
	
	public static class Builder{
		private int m_iBussinessUndoId;//出差撤销号,主键，自增
		private String m_sBussinessUndoReason;//出差撤销原因
		private String m_sBussinessUndoTime;//出差撤销时间
		
		public Builder(){
			
		}
		
		public Builder BussinessUndoId(int m_iBussinessUndoId){
			this.m_iBussinessUndoId=m_iBussinessUndoId;
			return this;
		}
		public Builder BussinessUndoReason(String m_sBussinessUndoReason){
			this.m_sBussinessUndoReason=m_sBussinessUndoReason;
			return this;
		}
		public Builder BussinessUndoTime(String m_sBussinessUndoTime){
			this.m_sBussinessUndoTime=m_sBussinessUndoTime;
			return this;
		}
		public CEntityBussinessUndo build(){
			return new CEntityBussinessUndo(this);
		}
		
	}
	
	private CEntityBussinessUndo(Builder b){
		this.m_iBussinessUndoId=b.m_iBussinessUndoId;
		this.m_sBussinessUndoReason=b.m_sBussinessUndoReason;
		this.m_sBussinessUndoTime=b.m_sBussinessUndoTime;
	}
	@Id
	@GeneratedValue
	@Column(name="BussinessUndoId")
	public int getM_iBussinessUndoId() {
		return m_iBussinessUndoId;
	}
	public void setM_iBussinessUndoId(int mIBussinessUndoId) {
		m_iBussinessUndoId = mIBussinessUndoId;
	}
	@Column(name="BussinessUndoReason")
	public String getM_sBussinessUndoReason() {
		return m_sBussinessUndoReason;
	}

	public void setM_sBussinessUndoReason(String mSBussinessUndoReason) {
		m_sBussinessUndoReason = mSBussinessUndoReason;
	}
	@Column(name="BussinessUndoTime")
	public String getM_sBussinessUndoTime() {
		return m_sBussinessUndoTime;
	}

	public void setM_sBussinessUndoTime(String mSBussinessUndoTime) {
		m_sBussinessUndoTime = mSBussinessUndoTime;
	}
	@OneToOne
	@JoinColumn(name = "BussinessId")
	public CEntityBussiness getcEntityBussiness() {
		return cEntityBussiness;
	}
	public void setcEntityBussiness(CEntityBussiness cEntityBussiness) {
		this.cEntityBussiness = cEntityBussiness;
	}
	
	
	
}
