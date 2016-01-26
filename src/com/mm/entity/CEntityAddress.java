package com.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//��ַ   ���¼ӱ�����������
@Entity
@Table(name="Address")
public class CEntityAddress {
	private int AddressId;		//��ַ�ţ�����������
	private String AddressLong;	//��ַ����
	private String AddressLat;	//��ַγ��
	private String AddressTime;	//��ַʱ��
	private int EmployeeId;		//Ա����
	
	
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
