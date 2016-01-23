package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import com.mm.entity.CEntityBussinessActivity;

public class CEntityBussinessActivityArray {

	private List<CEntityBussinessActivity> cEntityBussinessActivities=new ArrayList<CEntityBussinessActivity>();

	public CEntityBussinessActivityArray(
			List<CEntityBussinessActivity> cEntityBussinessActivities) {
		this.cEntityBussinessActivities = cEntityBussinessActivities;
	}

	public int getSize(){
		return cEntityBussinessActivities.size();
	}
	
	public CEntityBussinessActivity getItem(int index){
		return cEntityBussinessActivities.get(index);
	}
	
	@Override
	public String toString() {
		return "CEntityBussinessActivityArray [cEntityBussinessActivities="
				+ cEntityBussinessActivities + "]";
	}
	
	
	
	
	
}
