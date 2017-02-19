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

import org.hibernate.SessionFactory;

import com.nowgroup.scsee.model.Model;

/**
 * @author https://github.com/diego-torres
 * 		
 */
public abstract class HibernateRepository<T extends Model<U>, U extends Serializable>
											extends HibernateReadOnlyRepository<T, U>implements Repository<T, U> {
											
	/**
	 * @param type
	 * @param sessionFactory
	 */
	public HibernateRepository(Class<T> type, SessionFactory sessionFactory) {
		super(type, sessionFactory);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nowgroup.scsee.repository.Repository#add(com.nowgroup.scsee.model.
	 * Model)
	 */
	@Override
	public void add(T entity) {
		try {
			getHibernateTemplate().save(entity);
			getHibernateTemplate().flush();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nowgroup.scsee.repository.Repository#update(com.nowgroup.scsee.model.
	 * Model)
	 */
	@Override
	public void update(T entity) {
		try {
			getHibernateTemplate().merge(entity);
			getHibernateTemplate().flush();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nowgroup.scsee.repository.Repository#delete(com.nowgroup.scsee.model.
	 * Model)
	 */
	@Override
	public void delete(T entity) {
		try {
			getHibernateTemplate().delete(entity);
			getHibernateTemplate().flush();
		} catch (Exception e) {
			throw e;
		}
	}
}
