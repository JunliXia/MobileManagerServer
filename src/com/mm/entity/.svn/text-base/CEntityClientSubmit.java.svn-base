package com.mm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
//客户提交记录
import javax.persistence.Table;

@Entity
@Table(name="ClientSubmit")
public class CEntityClientSubmit {
	private int m_iClientSubmitId;		//客户提交号，主键，自增
	private int m_iClientSubmitState;	//提交状态(0为未审核，1为审核通过，2为审核不通过)

	private String m_sClientSubmitTime;	//客户提交时间

	private CEntityClient cEntityClient;
	
	public CEntityClientSubmit(){}
	
//	private int m_iSameClient;
//	@Column(name="SameClient")
//	public int getM_iSameClient() {
//		return m_iSameClient;
//	}
//	public void setM_iSameClient(int mISameClient) {
//		m_iSameClient = mISameClient;
//	}
//
//
//	public CEntityClientSubmit(int mISameClient) {
//		m_iSameClient = mISameClient;
//	}


	public static class Builder{
		private int m_iClientSubmitId;		//客户提交号，主键，自增
		private int m_iClientSubmitState;	//提交状态(0为未审核，1为审核通过，2为审核不通过)
		private String m_sClientSubmitTime;	//客户提交时间
		
		public Builder(){}
		
		public Builder ClientSubmitId(int m_iClientSubmitId){
			this.m_iClientSubmitId=m_iClientSubmitId;
			return this;
		}
		public Builder ClientSubmitState(int m_iClientSubmitState){
			this.m_iClientSubmitState=m_iClientSubmitState;
			return this;
		}
		
		public Builder ClientSubmitTime(String m_sClientSubmitTime){
			this.m_sClientSubmitTime=m_sClientSubmitTime;
			return this;
		}
		
		public CEntityClientSubmit build(){
			return new CEntityClientSubmit(this);
		}
		
	}
	private CEntityClientSubmit(Builder b){
		m_iClientSubmitId=b.m_iClientSubmitId;
		m_iClientSubmitState=b.m_iClientSubmitState;
		m_sClientSubmitTime=b.m_sClientSubmitTime;
	}
	@Id
	@GeneratedValue
	@Column(name="ClientSubmitId")
	public int getM_iClientSubmitId() {
		return m_iClientSubmitId;
	}
	@Column(name="ClientSubmitState")
	public int getM_iClientSubmitState() {
		return m_iClientSubmitState;
	}
	@Column(name="ClientSubmitTime")
	public String getM_sClientSubmitTime() {
		return m_sClientSubmitTime;
	}
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.MERGE,CascadeType.REMOVE})
	@JoinColumn(name="ClientId")
	public CEntityClient getcEntityClient() {
		return cEntityClient;
	}
	public void setcEntityClient(CEntityClient cEntityClient) {
		this.cEntityClient = cEntityClient;
	}
	public void setM_iClientSubmitId(int mIClientSubmitId) {
		m_iClientSubmitId = mIClientSubmitId;
	}
	public void setM_iClientSubmitState(int mIClientSubmitState) {
		m_iClientSubmitState = mIClientSubmitState;
	}
	public void setM_sClientSubmitTime(String mSClientSubmitTime) {
		m_sClientSubmitTime = mSClientSubmitTime;
	}


}
