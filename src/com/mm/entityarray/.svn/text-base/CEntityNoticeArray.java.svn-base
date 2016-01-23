package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.mm.entity.CEntityNotice;

public class CEntityNoticeArray {
	private List<CEntityNotice> cEntityNotices=new ArrayList<CEntityNotice>();

	public CEntityNoticeArray(List<CEntityNotice> cEntityNotices) {

		this.cEntityNotices = cEntityNotices;
	}

	public int getSize(){
		return cEntityNotices.size();
	}
	
	public CEntityNotice getItem(int index){
		return cEntityNotices.get(index);
		
	}
	public JSONArray toJsonArray(){
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<getSize();i++){
			jsonArray.add(new CEntityNotice.BuildJsonObject().ToSingle(getItem(i)).build());
		}
		
		return jsonArray;
	}
	
	@Override
	public String toString() {
		return "CEntityNoticeArray [cEntityNotices=" + cEntityNotices + "]";
	}
	
	
	
}
