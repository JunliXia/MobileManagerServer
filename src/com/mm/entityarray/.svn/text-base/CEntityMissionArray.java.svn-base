package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityMission;

public class CEntityMissionArray {

	private List<CEntityMission> cEntityMissions=new ArrayList<CEntityMission>();

	public CEntityMissionArray(List<CEntityMission> cEntityMissions) {
	
		this.cEntityMissions = cEntityMissions;
	}

	public int getSize(){
		return cEntityMissions.size();
	}
	
	public CEntityMission getItem(int index){
		return cEntityMissions.get(index);
	}
	
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityMission.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	
	@Override
	public String toString() {
		return "CEntityMissionArray [cEntityMissions=" + cEntityMissions + "]";
	}
	
	
	
}

