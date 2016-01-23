package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityClient;

public class CEntityClientArray {
	private List<CEntityClient> cEntityClients=new ArrayList<CEntityClient>();

	public CEntityClientArray(List<CEntityClient> cEntityClients) {

		this.cEntityClients = cEntityClients;
	}
	public int getSize(){
		return cEntityClients.size();
	}
	
	public CEntityClient getItem(int index){
		return cEntityClients.get(index);
	}
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityClient.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	
	@Override
	public String toString() {
		return "CEntityClientArray [cEntityClients=" + cEntityClients + "]";
	}
	
	
	
}
