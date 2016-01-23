package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//拜访撤销
@Entity
@Table(name="VisitUndo")
public class CEntityVisitUndo {
	private int m_iVisitUndoId;			//拜访撤销号，主键，自增
	private String m_sVisitUndoReason;	//拜访撤销原因
	private String m_sVisitUndoTime;	//拜访撤销时间
	private int m_iVisitUndoRecallType;	//拜访撤回类型
	private int m_iVisitUndoType;		//拜访撤销类型

	private CEntityVisitPlan cEntityVisitPlan;
	
	public CEntityVisitUndo() {
	}
	public static class Builder{
		private int m_iVisitUndoId;			//拜访撤销号，主键，自增
		private String m_sVisitUndoReason;	//拜访撤销原因
		private String m_sVisitUndoTime;	//拜访撤销时间
		private int m_iVisitUndoRecallType;	//拜访撤回类型
		private int m_iVisitUndoType;		//拜访撤销类型

		public Builder(){}
		
		public Builder VisitUndoId(int m_iVisitUndoId){
			this.m_iVisitUndoId=m_iVisitUndoId;
			return this;
		}
		public Builder VisitUndoReason(String m_sVisitUndoReason){
			this.m_sVisitUndoReason=m_sVisitUndoReason;
			return this;
		}
		public Builder VisitUndoTime(String m_sVisitUndoTime){
			this.m_sVisitUndoTime=m_sVisitUndoTime;
			return this;
		}
		
		public Builder VisitUndoRecallType(int m_iVisitUndoRecallType){
			this.m_iVisitUndoRecallType=m_iVisitUndoRecallType;
			return this;
		}
		public Builder VisitUndoType(int m_iVisitUndoType){
			this.m_iVisitUndoType=m_iVisitUndoType;
			return this;
		}
		public CEntityVisitUndo build(){
			return new CEntityVisitUndo(this);
		}
	}
	private CEntityVisitUndo(Builder b){
		m_iVisitUndoId=b.m_iVisitUndoId;
		m_sVisitUndoReason=b.m_sVisitUndoReason;
		m_sVisitUndoTime=b.m_sVisitUndoTime;
		m_iVisitUndoRecallType=b.m_iVisitUndoRecallType;
		m_iVisitUndoType=b.m_iVisitUndoType;
	}
	
	@Id
	@GeneratedValue
	@Column(name="VisitUndoId")
	public int getM_iVisitUndoId() {
		return m_iVisitUndoId;
	}
	@Column(name="VisitUndoRecallType")
	public int getM_iVisitUndoRecallType() {
		return m_iVisitUndoRecallType;
	}
	@Column(name="VisitUndoType")
	public int getM_iVisitUndoType() {
		return m_iVisitUndoType;
	}
	@Column(name="VisitUndoReason")
	public String getM_sVisitUndoReason() {
		return m_sVisitUndoReason;
	}
	@Column(name="VisitUndoTime")
	public String getM_sVisitUndoTime() {
		return m_sVisitUndoTime;
	}
	@OneToOne
	@JoinColumn(name="VisitPlanId")
	public CEntityVisitPlan getcEntityVisitPlan() {
		return cEntityVisitPlan;
	}
	public void setcEntityVisitPlan(CEntityVisitPlan cEntityVisitPlan) {
		this.cEntityVisitPlan = cEntityVisitPlan;
	}
	public void setM_iVisitUndoId(int mIVisitUndoId) {
		m_iVisitUndoId = mIVisitUndoId;
	}
	public void setM_iVisitUndoRecallType(int mIVisitUndoRecallType) {
		m_iVisitUndoRecallType = mIVisitUndoRecallType;
	}
	public void setM_iVisitUndoType(int mIVisitUndoType) {
		m_iVisitUndoType = mIVisitUndoType;
	}
	public void setM_sVisitUndoReason(String mSVisitUndoReason) {
		m_sVisitUndoReason = mSVisitUndoReason;
	}
	public void setM_sVisitUndoTime(String mSVisitUndoTime) {
		m_sVisitUndoTime = mSVisitUndoTime;
	}
	
}
