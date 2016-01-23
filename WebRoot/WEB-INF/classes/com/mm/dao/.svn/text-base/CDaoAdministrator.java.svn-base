package com.mm.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityAdministrator;

@Component("cDaoAdministrator")
public class CDaoAdministrator extends SuperDAO {

	/**
	 * 序号：administrator:1
	 * 功能:增加管理员
	 * 参数：cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * 返回值:boolean
	 */
	public boolean saveAdministrator(CEntityAdministrator cEntityAdministrator) {
		boolean bisSave = false;
		try {
			this.getHibernateTemplate().save(cEntityAdministrator);
			bisSave = true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}

	/**
	 * 序号：administrator:2
	 * 功能：按帐号密码查询管理员
	 * 参数：cEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * 返回值:CEntityAdministrator(本表字段)
	 */
	public CEntityAdministrator queryAdministrator(
			CEntityAdministrator cEntityAdministrator) {
		// TODO Auto-generated method stub
		String hql = "from com.mm.entity.CEntityAdministrator as administrator where AdministratorAccount=? and AdministratorPassword=?";

		List<?> findResult = this.getHibernateTemplate().find(
				hql,
				new String[] {
						cEntityAdministrator.getM_sAdministratorAccount(),
						cEntityAdministrator.getM_sAdministratorPassword() });

		Iterator<?> iterator = findResult.listIterator();
		CEntityAdministrator result = null;
		if (iterator.hasNext()) {
			result = (CEntityAdministrator) iterator.next();
		}
		return result; 
	}

}
