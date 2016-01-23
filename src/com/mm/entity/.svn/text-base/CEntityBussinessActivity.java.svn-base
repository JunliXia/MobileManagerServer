package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



//出差活动
@Entity
@Table(name="BussinessActivity")
public class CEntityBussinessActivity {
	private int m_iBussinessActivityId;		//活动自增号
	private int	m_iBussinessActivityType;	//活动类型	0为任务，1为拜访计划
	private int	m_iBussinessObjectId;		//活动对象的Id
	private int	m_iBussinessBindType;		//活动绑定状态	0为绑定中，1为已解绑
	
	
	
	public CEntityBussinessActivity() {
	
	}

	public static class Builder{
		private int m_iBussinessActivityId;		//活动自增号
		private int	m_iBussinessActivityType;	//活动类型	0为任务，1为拜访计划
		private int	m_iBussinessObjectId;		//活动对象的Id
		private int	m_iBussinessBindType;		//活动绑定状态	0为绑定中，1为已解绑
		
		public Builder(){}
		
		public Builder BussinessActivityId(int m_iBussinessActivityId){
			this.m_iBussinessActivityId=m_iBussinessActivityId;
			return this;
		}
		
		public Builder BussinessActivityType(int m_iBussinessActivityType){
			this.m_iBussinessActivityType=m_iBussinessActivityType;
			return this;
		}
		
		public Builder BussinessObjectId(int m_iBussinessObjectId){
			this.m_iBussinessObjectId=m_iBussinessObjectId;
			return this;
		}
		
		public Builder BussinessBindType(int m_iBussinessBindType){
			this.m_iBussinessBindType=m_iBussinessBindType;
			return this;
		}
		
		public CEntityBussinessActivity build(){
			return new CEntityBussinessActivity(this);
		}
		
	}
	
	private CEntityBussinessActivity(Builder b){
		m_iBussinessActivityId=b.m_iBussinessActivityId;
		m_iBussinessActivityType=b.m_iBussinessActivityType;
		m_iBussinessObjectId=b.m_iBussinessObjectId;
		m_iBussinessBindType=b.m_iBussinessBindType;
	}
	
	@Id
	@GeneratedValue
	@Column(name="BussinessActivityId")
	public int getM_iBussinessActivityId() {
		return m_iBussinessActivityId;
	}
	
	@Column(name="BussinessActivityType")
	public int getM_iBussinessActivityType() {
		return m_iBussinessActivityType;
	}
	@Column(name="BussinessBindType")
	public int getM_iBussinessBindType() {
		return m_iBussinessBindType;
	}
	@Column(name="BussinessObjectId")
	public int getM_iBussinessObjectId() {
		return m_iBussinessObjectId;
	}
	public void setM_iBussinessActivityType(int mIBussinessActivityType) {
		m_iBussinessActivityType = mIBussinessActivityType;
	}
	public void setM_iBussinessBindType(int mIBussinessBindType) {
		m_iBussinessBindType = mIBussinessBindType;
	}
	public void setM_iBussinessObjectId(int mIBussinessObjectId) {
		m_iBussinessObjectId = mIBussinessObjectId;
	}

	public void setM_iBussinessActivityId(int mIBussinessActivityId) {
		m_iBussinessActivityId = mIBussinessActivityId;
	}
	
}
