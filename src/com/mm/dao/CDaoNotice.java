package com.mm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityNotice;
import com.mm.entityarray.CEntityNoticeArray;

@Component("cDaoNotice")
public class CDaoNotice extends SuperDAO{

	/**
	 * ��ţ�notice:1
	 * ���ܣ�����һ��֪ͨ
	 * ������cEntityNotice(�����ֶ�)
	 * ����ֵ:boolean
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
	 * ��ţ�notice:2
	 * ���ܣ����ȫ��֪ͨ
	 * ��������
	 * ����ֵ:CEntityNoticeArray
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
