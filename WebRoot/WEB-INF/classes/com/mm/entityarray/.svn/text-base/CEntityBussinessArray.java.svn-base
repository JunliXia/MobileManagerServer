package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityBussiness;

public class CEntityBussinessArray {

	private List<CEntityBussiness> cEntityBussinesses=new ArrayList<CEntityBussiness>();

	public CEntityBussinessArray(List<CEntityBussiness> cEntityBussinesses) {
		this.cEntityBussinesses = cEntityBussinesses;
	}

	public int getSize(){
		return cEntityBussinesses.size();
	}
	
	public CEntityBussiness getItem(int index){
		return cEntityBussinesses.get(index);
	}
	
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityBussiness.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	
	
	@Override
	public String toString() {
		return "CEntityBussinessArray [cEntityBussinesses="
				+ cEntityBussinesses + "]";
	}
	
	
	
}
