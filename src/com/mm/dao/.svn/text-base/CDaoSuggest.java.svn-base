package com.mm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntitySuggest;
import com.mm.entityarray.CEntitySuggestArray;

@Component("cDaoSuggest")
public class CDaoSuggest extends SuperDAO{


	/**
	 * 序号：suggest:1
	 * 功能：增加一条建议
	 * 参数：cEntityEmployee(EmployeeId),cEntitySuggest(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest) {
		CEntityEmployee findResult=(CEntityEmployee)this.getHibernateTemplate().get(CEntityEmployee.class, cEntityEmployee.getM_iEmployeeId());
		
		boolean bisSave=false;
		try {
			this.getHibernateTemplate().save(cEntitySuggest);
			findResult.getcEntitySuggests().add(cEntitySuggest);
			this.getHibernateTemplate().update(findResult);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bisSave;
	}
	/**
	 * 序号：suggest:2
	 * 功能：获得所有的建议
	 * 参数：无
	 * 返回值:CEntitySuggestArray
	 */
	@SuppressWarnings("unchecked")
	public CEntitySuggestArray queryAllSuggest() {
		String hql="from com.mm.entity.CEntitySuggest as suggest";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntitySuggestArray cEntitySuggestArray=new CEntitySuggestArray((List<CEntitySuggest>) findResult);
		return cEntitySuggestArray;
	}

}
