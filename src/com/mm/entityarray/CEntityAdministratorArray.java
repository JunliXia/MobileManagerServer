package com.mm.entityarray;

import java.util.ArrayList;
import java.util.List;

import com.mm.entity.CEntityAdministrator;

public class CEntityAdministratorArray {
	private List<CEntityAdministrator> cEntityAdministrators=new ArrayList<CEntityAdministrator>();

	public CEntityAdministratorArray(
			List<CEntityAdministrator> cEntityAdministrators) {
		this.cEntityAdministrators = cEntityAdministrators;
	}

	public int getSize(){
		return cEntityAdministrators.size();
	}
	
	public CEntityAdministrator getItem(int index){
		return cEntityAdministrators.get(index);
	}

	@Override
	public String toString() {
		return "CEntityAdministratorArray [cEntityAdministrators="
				+ cEntityAdministrators + "]";
	}
	
	
	
	
}
