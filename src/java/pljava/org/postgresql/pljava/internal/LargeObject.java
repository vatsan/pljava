/*
 * Copyright (c) 2004, 2005 TADA AB - Taby Sweden
 * Distributed under the terms shown in the file COPYRIGHT
 * found in the root folder of this project or at
 * http://eng.tada.se/osprojects/COPYRIGHT.html
 */
package org.postgresql.pljava.internal;

import java.sql.SQLException;

/**
 * The <code>LargeObject</code> correspons to the internal PostgreSQL
 * <code>LargeObjectDesc</code>.
 * 
 * @author Thomas Hallgren
 */
public class LargeObject extends JavaHandle
{
	/**
	 *	Write mode flag to be passed to {@link #create} and {@link #open}
	 */
	public static final int INV_WRITE = 0x00020000;

	/**
	 *	Read mode flag to be passed to {@link #create} and {@link #open}
	 */
	public static final int INV_READ  = 0x00040000;

	/**
	 * Flag returned by {@link #create} and {@link #open}
	 */
	public static final int IFS_RDLOCK = (1 << 0);

	/**
	 * Flag returned by {@link #create} and {@link #open}
	 */
	public static final int IFS_WRLOCK = (1 << 1);

	/**
	 * Flag to be passed to {@link #seek} denoting that the
	 * offset parameter should be treated as an absolute address.
	 */
	public static final int SEEK_SET = 0;

	/**
	 * Flag to be passed to {@link #seek} denoting that the
	 * offset parameter should be treated relative to the current
	 * address.
	 */
	public static final int SEEK_CUR = 1;

	/**
	 * Flag to be passed to {@link #seek} denoting that the
	 * offset parameter should be treated relative to the end
	 * of the data.
	 */
	public static final int SEEK_END = 2;

	public static LargeObject create(int flags)
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return _create(flags);
		}
	}

	public static LargeObject open(Oid lobjId, int flags)
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return _open(lobjId, flags);
		}
	}

	public void close()
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			this._close();
		}
	}
	
	public static int drop(Oid lobjId)
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return _drop(lobjId);
		}
	}

	public Oid getId()
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return this._getId();
		}
	}
	
	public long length()
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return this._length();
		}
	}

	public long seek(long offset, int whence)
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return this._seek(offset, whence);
		}
	}
	
	public long tell()
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return this._tell();
		}
	}
	
	public int read(byte[] buf)
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return this._read(buf);
		}
	}
	
	public int write(byte[] buf)
	throws SQLException
	{
		synchronized(Backend.THREADLOCK)
		{
			return this._write(buf);
		}
	}

	private static native LargeObject _create(int flags)
	throws SQLException;

	private static native int _drop(Oid lobjId)
	throws SQLException;

	private static native LargeObject _open(Oid lobjId, int flags)
	throws SQLException;

	private native void _close()
	throws SQLException;

	private native Oid _getId()
	throws SQLException;

	private native long _length()
	throws SQLException;

	private native long _seek(long offset, int whence)
	throws SQLException;

	private native long _tell()
	throws SQLException;

	private native int _read(byte[] buf)
	throws SQLException;

	private native int _write(byte[] buf)
	throws SQLException;
}
