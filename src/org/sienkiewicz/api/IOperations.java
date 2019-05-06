package org.sienkiewicz.api;

import org.hibernate.Session;

@FunctionalInterface
public interface IOperations<T, R> {	
	
	R excecute(Session session, T data);
	
}
