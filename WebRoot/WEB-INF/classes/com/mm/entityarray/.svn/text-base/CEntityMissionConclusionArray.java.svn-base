package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityMissionConclusion;

public class CEntityMissionConclusionArray {
	private List<CEntityMissionConclusion> cEntityMissionConclusions=new ArrayList<CEntityMissionConclusion>();

	public CEntityMissionConclusionArray(
			List<CEntityMissionConclusion> cEntityMissionConclusions) {
		this.cEntityMissionConclusions = cEntityMissionConclusions;
	}

	public int getSize(){
		return cEntityMissionConclusions.size();
	}
	
	public CEntityMissionConclusion getItem(int index){
		return cEntityMissionConclusions.get(index);
	}
	
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityMissionConclusion.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	
	@Override
	public String toString() {
		return "CEntityMissionConclusionArray [cEntityMissionConclusions="
				+ cEntityMissionConclusions + "]";
	}
	
	
}
