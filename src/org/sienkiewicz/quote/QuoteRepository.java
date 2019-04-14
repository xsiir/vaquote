package org.sienkiewicz.quote;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.sienkiewcz.utils.HibernateUtils;
import org.sienkiewicz.api.CRUDOperations;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepository<T> extends CRUDOperations<Quote>{

	Long rowCount(){

		Session session = null;
		Long rows = (long) 0;

		try {
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Quote.class);
			criteria.setProjection(Projections.rowCount());
			List quotes = criteria.list();

			if (quotes != null) {
				rows =  (Long) quotes.get(0);
			}

			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return rows;
	}

	public Optional<Quote> getRandom(Long rows) {
		
		Session session = null;
		Optional<Quote> quote = Optional.ofNullable(null);
		Random random = new Random();
		
		try {
			
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			quote = Optional.of(session.get(Quote.class, random.nextInt(rows.intValue())));
			session.getTransaction().commit();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return quote;
	}

	@Override
	public void update(Long id, Quote object) {
		// TODO Auto-generated method stub
		
	}

}
