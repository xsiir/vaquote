package org.sienkiewicz.api;

import java.util.Optional;

import org.hibernate.Session;
import org.sienkiewcz.utils.HibernateUtils;

public class CRUDStrategy<D, R> {
	
	public R execute(IOperations operation, D data) {

		Session session = null;
		R result = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			result = (R) operation.excecute(session, data);
			session.getTransaction().commit();
		} catch (Exception sqlException) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				sqlException.printStackTrace();
			}
		} finally {
			session.close();
		}
		
		return result; 
	}

}
