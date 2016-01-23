package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityAttendance;

public class CEntityAttendanceArray {
	private List<CEntityAttendance> cEntityAttendances=new ArrayList<CEntityAttendance>();

	public CEntityAttendanceArray(List<CEntityAttendance> cEntityAttendances) {
		this.cEntityAttendances = cEntityAttendances;
	}

	public int getSize(){
		return cEntityAttendances.size();
	}
	
	public CEntityAttendance getItem(int index){
		return cEntityAttendances.get(index);
	}
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityAttendance.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	
	@Override
	public String toString() {
		return "CEntityAttendanceArray [cEntityAttendances="
				+ cEntityAttendances + "]";
	}
	
	
	
}
