package com.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//地址   （新加表，不做关联）
@Entity
@Table(name="Address")
public class CEntityAddress {
	private int AddressId;		//地址号，主键，自增
	private String AddressLong;	//地址经度
	private String AddressLat;	//地址纬度
	private String AddressTime;	//地址时间
	private int EmployeeId;		//员工号
	
	
	public CEntityAddress() {
	}

	public CEntityAddress(int addressId, String addressLong, String addressLat,
			String addressTime) {
		AddressId = addressId;
		AddressLong = addressLong;
		AddressLat = addressLat;
		AddressTime = addressTime;
	}

	
	
	
	public CEntityAddress(String addressLong, String addressLat,
			String addressTime, int employeeId) {
		AddressLong = addressLong;
		AddressLat = addressLat;
		AddressTime = addressTime;
		EmployeeId = employeeId;
	}

	@Id
	@GeneratedValue
	public int getAddressId() {
		return AddressId;
	}


	public void setAddressId(int addressId) {
		AddressId = addressId;
	}


	public String getAddressLong() {
		return AddressLong;
	}


	public void setAddressLong(String addressLong) {
		AddressLong = addressLong;
	}


	public String getAddressLat() {
		return AddressLat;
	}


	public void setAddressLat(String addressLat) {
		AddressLat = addressLat;
	}


	public String getAddressTime() {
		return AddressTime;
	}


	public void setAddressTime(String addressTime) {
		AddressTime = addressTime;
	}


	public int getEmployeeId() {
		return EmployeeId;
	}


	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}

	@Override
	public String toString() {
		return "CEntityAddress [AddressId=" + AddressId + ", AddressLat="
				+ AddressLat + ", AddressLong=" + AddressLong
				+ ", AddressTime=" + AddressTime + ", EmployeeId=" + EmployeeId
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
}
