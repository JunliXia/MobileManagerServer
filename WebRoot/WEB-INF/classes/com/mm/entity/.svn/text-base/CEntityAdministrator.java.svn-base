package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONObject;

//管理员
@Entity
@Table(name = "Administrator")
public class CEntityAdministrator {
	private int m_iAdministratorId; // 管理员号，主键，自增
	private String m_sAdministratorAccount; // 管理员帐号
	private String m_sAdministratorPassword; // 管理员密码

	public CEntityAdministrator() {
	}
	
	
	public static class Builder{
		private int m_iAdministratorId; // 管理员号，主键，自增
		private String m_sAdministratorAccount; // 管理员帐号
		private String m_sAdministratorPassword; // 管理员密码

		public Builder(){
			
		}
		public Builder AdministratorId(int m_iAdministratorId){
			this.m_iAdministratorId=m_iAdministratorId;
			return this;
		}
		public Builder AdministratorAccount(String m_sAdministratorAccount){
			this.m_sAdministratorAccount=m_sAdministratorAccount;
			return this;
		}
		
		public Builder AdministratorPassword(String m_sAdministratorPassword){
			this.m_sAdministratorPassword=m_sAdministratorPassword;
			return this;
		}
		
		public CEntityAdministrator build(){
			return new CEntityAdministrator(this);
		}
		
	}
	private CEntityAdministrator(Builder b){
		m_iAdministratorId=b.m_iAdministratorId;
		m_sAdministratorAccount=b.m_sAdministratorAccount;
		m_sAdministratorPassword=b.m_sAdministratorPassword;
	}


	public static class BuildJsonObject{
		private JSONObject outjson=new JSONObject();
		public BuildJsonObject(){
			
		}
		public BuildJsonObject AdministratorId(int m_iAdministratorId) {
			outjson.put(MyOpcode.Administrator.AdministratorId, m_iAdministratorId);
			return this;
		}
		public BuildJsonObject AdministratorAccount(String m_sAdministratorAccount) {
			outjson.put(MyOpcode.Administrator.AdministratorAccount, m_sAdministratorAccount);
			return this;
		}
		
		public BuildJsonObject AdministratorPassword(String m_sAdministratorPassword) {
			outjson.put(MyOpcode.Administrator.AdministratorPassword, m_sAdministratorPassword);
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
		
		public JSONObject build() {
			return outjson;
		}
	}
	
	
	@Id
	@GeneratedValue
	@Column(name = "AdministratorId")
	public int getM_iAdministratorId() {
		return m_iAdministratorId;
	}

	@Column(name = "AdministratorAccount")
	public String getM_sAdministratorAccount() {
		return m_sAdministratorAccount;
	}

	@Column(name = "AdministratorPassword")
	public String getM_sAdministratorPassword() {
		return m_sAdministratorPassword;
	}

	public void setM_iAdministratorId(int mIAdministratorId) {
		m_iAdministratorId = mIAdministratorId;
	}

	public void setM_sAdministratorAccount(String mSAdministratorAccount) {
		m_sAdministratorAccount = mSAdministratorAccount;
	}

	public void setM_sAdministratorPassword(String mSAdministratorPassword) {
		m_sAdministratorPassword = mSAdministratorPassword;
	}



	@Override
	public String toString() {
		return "CEntityAdministrator [m_iAdministratorId=" + m_iAdministratorId
				+ ", m_sAdministratorAccount=" + m_sAdministratorAccount
				+ ", m_sAdministratorPassword=" + m_sAdministratorPassword
				+ "]";
	}

}
