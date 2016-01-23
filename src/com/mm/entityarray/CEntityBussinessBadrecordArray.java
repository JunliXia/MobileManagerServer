package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import com.mm.entity.CEntityBussinessBadrecord;

public class CEntityBussinessBadrecordArray {

	private List<CEntityBussinessBadrecord> cEntityBussinessBadrecords=new ArrayList<CEntityBussinessBadrecord>();

	public CEntityBussinessBadrecordArray(
			List<CEntityBussinessBadrecord> cEntityBussinessBadrecords) {
		
		this.cEntityBussinessBadrecords = cEntityBussinessBadrecords;
	}

	public int getSize(){
		return cEntityBussinessBadrecords.size();
	}
	
	public CEntityBussinessBadrecord getItem(int index){
		return cEntityBussinessBadrecords.get(index);
		
	}
	
	@Override
	public String toString() {
		return "CEntityBussinessBadrecord [cEntityBussinessBadrecords="
				+ cEntityBussinessBadrecords + "]";
	}
	
	
}
