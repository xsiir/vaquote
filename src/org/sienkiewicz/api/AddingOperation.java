package org.sienkiewicz.api;

import org.hibernate.Session;

public class AddingOperation<T, D, R> implements IOperations<D, R>{
	
	@Override
	public R excecute(Session session, D data) {
		return (R) session.save(data);	
	}
}
