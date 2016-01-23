package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityVisitPlan;

public class CEntityVisitPlanArray {
	private List<CEntityVisitPlan> cEntityVisitPlans=new ArrayList<CEntityVisitPlan>();

	public CEntityVisitPlanArray(List<CEntityVisitPlan> cEntityVisitPlans) {
	
		this.cEntityVisitPlans = cEntityVisitPlans;
	}

	public int getSize(){
		return cEntityVisitPlans.size();
	}
	
	public CEntityVisitPlan getItem(int index){
		return cEntityVisitPlans.get(index);
	}
	
	
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityVisitPlan.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	
	@Override
	public String toString() {
		return "CEntityVisitPlanArray [cEntityVisitPlans=" + cEntityVisitPlans
				+ "]";
	}
	
	
	
	
	
}
