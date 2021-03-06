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
package com.nowgroup.scsee.geo.state;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nowgroup.scsee.repository.HibernateReadOnlyRepository;

/**
 * @author https://github.com/diego-torres
 *
 */
@Repository
public class HibernateStateRepository extends HibernateReadOnlyRepository<GeoState, Integer>
										implements IStateRepository {

	/**
	 * @param type
	 * @param sessionFactory
	 */
	@Autowired
	public HibernateStateRepository(SessionFactory sessionFactory) {
		super(GeoState.class, sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GeoState> getStatesByCountryId(int countryId) {
		DetachedCriteria dc = DetachedCriteria.forClass(GeoState.class);
		DetachedCriteria countryCriteria = dc.createCriteria("country");
		countryCriteria.add(Restrictions.eq("id", countryId));
		countryCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return (List<GeoState>) getHibernateTemplate().findByCriteria(dc);
	}

}
