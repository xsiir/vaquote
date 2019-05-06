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
import org.sienkiewicz.api.AddingOperation;
import org.sienkiewicz.api.CRUDOperations;
import org.sienkiewicz.api.CRUDStrategy;
import org.sienkiewicz.api.IOperations;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepository<T> extends CRUDOperations<Quote>{

	/**
	 * 
	 * @return liczba wszystkich krotek dla tabeli Quote
	 */
	Long rowCount(){

		Session session = null;
		Long rows = 0L;

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

	/**
	 * 
	 * @return losowy cytat z zakresu od pierwszej do ostatniej krotki w tabeli Quotes.
	 */
	public Optional<Quote> getRandom() {
		
		Session session = null;
		Optional<Quote> quote = Optional.ofNullable(null);
		Random random = new Random();
		
		try {
			
			session = HibernateUtils.getSessionFactory().openSession();
			session.beginTransaction();
			quote = Optional.of(session.get(Quote.class, random.nextInt(rowCount().intValue())));
			session.getTransaction().commit();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return quote;
	}

	/**
	 * 
	 */
	@Override
	public void update(Long id, Quote object) {
		// TODO Auto-generated method stub
		
	}
	
	public Integer save(Quote quote) {
		CRUDStrategy<Quote, Integer> strategy = new CRUDStrategy<>();
		Integer x = strategy.execute(new AddingOperation<>(), quote);
		System.out.println(x);
		return 10;
	}

}
