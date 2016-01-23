package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityVisitConclusion;

public class CEntityVisitConclusionArray {
	private List<CEntityVisitConclusion> cEntityVisitConclusions=new ArrayList<CEntityVisitConclusion>();

	public CEntityVisitConclusionArray(
			List<CEntityVisitConclusion> cEntityVisitConclusions) {

		this.cEntityVisitConclusions = cEntityVisitConclusions;
	}

	public int getSize(){
		return cEntityVisitConclusions.size();
	}
	
	public CEntityVisitConclusion getItem(int index){
		return cEntityVisitConclusions.get(index);
	}
	
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityVisitConclusion.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	@Override
	public String toString() {
		return "CEntityVisitConclusionArray [cEntityVisitConclusions="
				+ cEntityVisitConclusions + "]";
	}
	
	
	
	
}	
