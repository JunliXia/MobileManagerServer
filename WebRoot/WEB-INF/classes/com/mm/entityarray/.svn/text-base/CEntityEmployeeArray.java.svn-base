package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityEmployee;

public class CEntityEmployeeArray {
	private List<CEntityEmployee> cEntityEmployees=new ArrayList<CEntityEmployee>();

	public CEntityEmployeeArray(List<CEntityEmployee> cEntityEmployees) {
		this.cEntityEmployees = cEntityEmployees;
	}
	
	public int getSize(){
		return cEntityEmployees.size();
	}
	
	public CEntityEmployee getItem(int index){
		
		return cEntityEmployees.get(index);
		
	}

	
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityEmployee.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	@Override
	public String toString() {
		return "CEntityEmployeeArray [cEntityEmployees=" + cEntityEmployees
				+ "]";
	}
	
	
}
