/*
 * Copyright (c) 2004, 2005 TADA AB - Taby Sweden
 * Distributed under the terms shown in the file COPYRIGHT
 * found in the root folder of this project or at
 * http://eng.tada.se/osprojects/COPYRIGHT.html
 */
package org.postgresql.pljava;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A Session maintains transaction coordinated in-memory data. The data
 * added since the last commit will be lost on a transaction rollback, i.e.
 * the Session state is synchronized with the transaction.
 * 
 * Please note that if nested objects (such as lists and maps) are stored
 * in the session, changes internal to those objects are not subject to
 * the session semantics since the session is unaware of them.
 * 
 * @author Thomas Hallgren
 */
public interface Session
{
	/**
	 * Obtain an attribute from the current session.
	 * @param attributeName The name of the attribute
	 * @return The value of the attribute
	 */
	Object getAttribute(String attributeName);

	/**
	 * Return the name of the effective user. If the currently
	 * executing funciton is declared with <code>SECURITY DEFINER</code>,
	 * then this method returns the name of the user that defined
	 * the function, otherwise, this method will return the same
	 * as {@link #getSessionUserName()}.
	 */
	String getUserName();

	/**
	 * Return the name of the user that owns the current session.
	 */
	String getSessionUserName();

	/**
	 * Execute a statement as a session user rather then the effective
	 * user. This is useful when functions declared using
	 * <code>SECURITY DEFINER</code> wants to give up the definer
	 * rights.
	 * @param conn The connection used for the execution
	 * @param statement The statement to execute
	 * @return The result of the execution.
	 * @throws SQLException if something goes wrong when executing.
	 * @see java.sql.Statement#execute(java.lang.String)
	 */
	boolean executeAsSessionUser(Connection conn, String statement)
	throws SQLException;

	/**
	 * Remove an attribute previously stored in the session. If
	 * no attribute is found, nothing happens.
	 * @param attributeName The name of the attribute.
	 */
	void removeAttribute(String attributeName);

	/**
	 * Set an attribute to a value in the current session.
	 * @param attributeName
	 * @param value
	 */
	void setAttribute(String attributeName, Object value);
}
