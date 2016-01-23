package com.mm.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.entityarray.CEntityBussinessActivityArray;
import com.mm.tool.MyConstant;

@Component("cDaoBussinessActivity")
public class CDaoBussinessActivity extends SuperDAO{

	/**
	 * ��ţ�bussinessactivity:1
	 * ���ܣ�����һ������
	 * ������cEntityBussinessActivity(�����ֶ�),cEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean saveBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness) {
		CEntityBussiness findResult=(CEntityBussiness)this.getHibernateTemplate().get(CEntityBussiness.class, cEntityBussiness.getM_iBussinessId());
		
		boolean bisSave=false;
		try {
			this.getHibernateTemplate().save(cEntityBussinessActivity);
			findResult.getcEntityBussinessActivities().add(cEntityBussinessActivity);
			this.getHibernateTemplate().update(findResult);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisSave;
	}
	
	/**
	 * ��ţ�bussinessactivity:2
	 * ���ܣ�������Ż�����еĳ���
	 * ������cEntityBussiness(BussinessId)
	 * ����ֵ:CEntityBussinessActivityArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityBussinessActivityArray queryAllBussinessActivityByBussinessId(CEntityBussiness cEntityBussiness) {
		String hql="from com.mm.entity.CEntityBussinessActivity as bussinessactivity where BussinessId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityBussiness.getM_iBussinessId());
		CEntityBussinessActivityArray cEntityBussinessActivityArray=new CEntityBussinessActivityArray((List<CEntityBussinessActivity>) findResult);
		System.out.println(cEntityBussinessActivityArray.getSize()+"------����dao sige");
		return cEntityBussinessActivityArray;
	}
	
	
	
	/**
	 * ��ţ�bussinessactivity:3
	 * ���ܣ���������Id�����ͽ����޸İ�״̬
	 * ������cEntityBussinessActivity(BussinessActivityType,BussinessObjectId,BussinessBindType)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessBandTypeByObjectIdAndActivityType(CEntityBussinessActivity cEntityBussinessActivity) {
		String hql="update com.mm.entity.CEntityBussinessActivity as bussinessactivity Set BussinessBindType=? where BussinessObjectId=? and BussinessActivityType=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityBussinessActivity.getM_iBussinessBindType(),cEntityBussinessActivity.getM_iBussinessObjectId(),cEntityBussinessActivity.getM_iBussinessActivityType()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;
	}
	
	
	/**
	 * ��ţ�bussinessactivity:4
	 * ���ܣ������������
	 * ������cEntityBussinessActivityArray
	 * ����ֵ:��
	 */
	public void saveBatchBussinessActiviy(final CEntityBussinessActivityArray cEntityBussinessActivityArray){
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				for(int i=0;i<cEntityBussinessActivityArray.getSize();i++){
					session.save(cEntityBussinessActivityArray.getItem(i));
					if(i%10==0){
						session.flush();
						session.clear();
					}
				}
				return true;
			}
		});
	}
	/**
	 * ��ţ�bussinessactivity:5
	 * ���ܣ�������Ż�ð󶨳����ĸ���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:int
	 */
	public int queryBussinessActivityBindNumberByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="select count(BussinessActivityId) from com.mm.entity.CEntityBussinessActivity where BussinessBindType=? and BussinessId=?";
		int count=((Long) this.getHibernateTemplate().find(hql,new Object[]{MyConstant.BussinessActivity.BUSSINESSACTIVITY_BAND,cEntityBussiness.getM_iBussinessId()}).listIterator().next()).intValue();
		return count;
	}
	
	
	
	/**
	 * ��ţ�bussinessactivity:6
	 * ���ܣ�������Ž�����г���
	 * ������CEntityBussiness(BussinessId)
	 * ����ֵ:boolean
	 */
	public boolean updateBussinessActivityBindTypeUnbindByBussinessId(CEntityBussiness cEntityBussiness){
		String hql="update com.mm.entity.CEntityBussinessActivity as bussinessactivity set BussinessBindType=? where BussinessId=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{MyConstant.BussinessActivity.BUSSINESSACTIVITY_NOBAND,cEntityBussiness.getM_iBussinessId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}