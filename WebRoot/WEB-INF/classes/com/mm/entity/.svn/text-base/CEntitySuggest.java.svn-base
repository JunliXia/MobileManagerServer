package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//建议
@Entity
@Table(name="Suggest")
public class CEntitySuggest {

	private int	m_iSuggestId;			//建议号，主键，自增
	private String	m_sSuggestContent;	//建议内容
	private String	m_sSuggestTime;		//建议提交时间
	
	public CEntitySuggest(){}
	
	
	public static class Builder{
		private int	m_iSuggestId;			//建议号，主键，自增
		private String	m_sSuggestContent;	//建议内容
		private String	m_sSuggestTime;		//建议提交时间
		
		public Builder(){}
		
		public Builder SuggestId(int m_iSuggestId){
			this.m_iSuggestId=m_iSuggestId;
			return this;
		}
		
		public Builder SuggestContent(String m_sSuggestContent){
			this.m_sSuggestContent=m_sSuggestContent;
			return this;
		}
		
		public Builder SuggestTime(String m_sSuggestTime){
			this.m_sSuggestTime=m_sSuggestTime;
			return this;
		}
		
		public CEntitySuggest build(){
			return new CEntitySuggest(this);
		}
	}
	private CEntitySuggest(Builder b){
		m_iSuggestId=b.m_iSuggestId;
		m_sSuggestContent=b.m_sSuggestContent;
		m_sSuggestTime=b.m_sSuggestTime;
	}
	
	@Id
	@GeneratedValue
	@Column(name="SuggestId")
	public int getM_iSuggestId() {
		return m_iSuggestId;
	}
	@Column(name="SuggestId")
	public void setM_iSuggestId(int mISuggestId) {
		m_iSuggestId = mISuggestId;
	}
	@Column(name="SuggestContent")
	public String getM_sSuggestContent() {
		return m_sSuggestContent;
	}
	public void setM_sSuggestContent(String mSSuggestContent) {
		m_sSuggestContent = mSSuggestContent;
	}
	public String getM_sSuggestTime() {
		return m_sSuggestTime;
	}
	public void setM_sSuggestTime(String mSSuggestTime) {
		m_sSuggestTime = mSSuggestTime;
	}

}
