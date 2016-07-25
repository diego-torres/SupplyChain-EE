/**
 * Copyright 2016 https://github.com/diego-torres
 * 
 * Licensed under the MIT License (MIT).
 *  
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sub-license, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.nowgroup.scsee.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.nowgroup.scsee.model.Model;

/**
 * 
 * @author https://github.com/diego-torres
 *		
 * @param <T>
 *            The Model Type
 * @param <U>
 *            The Model ID type.
 */
public abstract class HibernateReadOnlyRepository<T extends Model<U>, U extends Serializable>
													extends HibernateDaoSupport implements ReadOnlyRepository<T, U> {
	private Class<T> type;
	
	/**
	 * 
	 */
	public HibernateReadOnlyRepository(Class<T> type, SessionFactory sessionFactory) {
		this.type = type;
		setSessionFactory(sessionFactory);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowgroup.scsee.repository.ReadOnlyRepository#getById(java.io.
	 * Serializable)
	 */
	@Override
	public T getById(U id) {
		return getHibernateTemplate().get(type, id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nowgroup.scsee.repository.ReadOnlyRepository#getAll()
	 */
	@Override
	public List<T> getAll() {
		return getHibernateTemplate().loadAll(type);
	}
}
