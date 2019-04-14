package org.sienkiewicz.api;


import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.sienkiewcz.utils.*;

public abstract class CRUDOperations<T> {
	
	public Integer save(T objectToSave) {

		Session session = null;
		Optional<T> optionalObject = null;
		Integer savedObjectID = 0;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			savedObjectID = (Integer) session.save(objectToSave);
			session.getTransaction().commit();
		} catch (Exception sqlException) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				sqlException.printStackTrace();
			}
		} finally {
			session.close();
		}

		return savedObjectID;
	}

	public Optional<T> get(Class<T> classType, Integer id) {
		Session session = null;
		Optional<T> objectToGet = Optional.ofNullable(null);

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();			
			objectToGet = session.byId(classType).loadOptional(id);
		} catch (Exception sqlException) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				sqlException.printStackTrace();
			}
		} finally {
			session.close();
		}

		return objectToGet;
	}
	
	public Optional<List<T>> getAll(Class<T> classType) {
		Session session = null;
		List<T> resultList = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			System.out.println(session);
			session.beginTransaction();
			resultList = getAllData(session, classType);
			session.getTransaction().commit();
		}catch(Exception sqlException) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				sqlException.printStackTrace();
			}
		}finally {
			session.close();
		}
		
		return Optional.ofNullable(resultList);
	}
	
	private List<T> getAllData(Session session, Class<T> type){
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		criteria.from(type);
		List<T> resultList = session.createQuery(criteria).getResultList();
		
		return resultList;
	}
	
	
	public void remove(Class<T> classType, Integer id) {
		Session session = null;
		Optional<T> objectToRemove = null;
		
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			objectToRemove = get(classType, id);
			session.remove(objectToRemove);
			session.getTransaction().commit();
			
		}catch(Exception sqlException) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				sqlException.printStackTrace();
			}
		}finally {
			session.close();
		}
	}

	public abstract void update(Long id, T object);
	
}
