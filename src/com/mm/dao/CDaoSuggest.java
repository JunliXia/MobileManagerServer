package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntitySuggest;
import com.mm.entityarray.CEntitySuggestArray;
import com.mm.tool.MyOpcode;

@Component("cDaoSuggest")
public class CDaoSuggest extends SuperDAO{


	/**
	 * ��ţ�suggest:1
	 * ���ܣ�����һ������
	 * ������cEntityEmployee(EmployeeId),cEntitySuggest(�����ֶ�)
	 * ����ֵ:boolean
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
	 * ��ţ�suggest:2
	 * ���ܣ�������еĽ���
	 * ��������
	 * ����ֵ:CEntitySuggestArray
	 */
	@SuppressWarnings("unchecked")
	public CEntitySuggestArray queryAllSuggest() {
		String hql="from com.mm.entity.CEntitySuggest as suggest";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntitySuggestArray cEntitySuggestArray=new CEntitySuggestArray((List<CEntitySuggest>) findResult);
		return cEntitySuggestArray;
	}

	/**
	 * ��ţ�suggest:3
	 * ���ܣ���ȡ���еĽ�����Ϣ
	 * ��������
	 * ����ֵ:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllSuggestInfo(){
		String hql="select suggest.m_iSuggestId,suggest.m_sSuggestContent,suggest.m_sSuggestTime,employee.m_iEmployeeId,employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntitySuggest as suggest ,com.mm.entity.CEntityEmployee as employee left join employee.cEntitySuggests as bb where bb.m_iSuggestId=suggest.m_iSuggestId order by suggest.m_sSuggestTime desc";
		List findResult=this.getHibernateTemplate().find(hql);
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntitySuggest cEntitySuggest=new CEntitySuggest.Builder().SuggestId((Integer)tuple[0]).SuggestContent((String)tuple[1]).SuggestTime((String)tuple[2]).build();
		    
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[3]).EmployeeAccount((String)tuple[4]).EmployeeName((String)tuple[5]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Suggest.Suggest, cEntitySuggest);
		    maps.put(i, map);
		    i++;
		 }    

		return maps;
	}
	
}
