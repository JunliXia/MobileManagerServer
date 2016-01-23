package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import com.mm.entity.CEntitySuggest;

public class CEntitySuggestArray {
	private List<CEntitySuggest> cEntitySuggests=new ArrayList<CEntitySuggest>();

	public CEntitySuggestArray(List<CEntitySuggest> cEntitySuggests) {

		this.cEntitySuggests = cEntitySuggests;
	}

	public int getSize(){
		return cEntitySuggests.size();
	}
	
	public CEntitySuggest getItem(int index){
		return cEntitySuggests.get(index);
	}
	
	@Override
	public String toString() {
		return "CEntitySuggestArray [cEntitySuggests=" + cEntitySuggests + "]";
	}
	
	
	
	
}
