package com.mm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityNotice;
import com.mm.entityarray.CEntityNoticeArray;

@Component("cDaoNotice")
public class CDaoNotice extends SuperDAO{

	/**
	 * 序号：notice:1
	 * 功能：增加一条通知
	 * 参数：cEntityNotice(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveNotice(CEntityNotice cEntityNotice) {
		boolean bisSave=false;
		
		try {
			this.getHibernateTemplate().save(cEntityNotice);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}
	
	
	/**
	 * 序号：notice:2
	 * 功能：获得全部通知
	 * 参数：无
	 * 返回值:CEntityNoticeArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityNoticeArray queryAllNotice() {
		String hql="from com.mm.entity.CEntityNotice as notice order by notice.m_sNoticeTime desc";
		this.getHibernateTemplate().setCacheQueries(true);
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntityNoticeArray cEntityNoticeArray=new CEntityNoticeArray((List<CEntityNotice>) findResult);
		return cEntityNoticeArray;
	}
}
