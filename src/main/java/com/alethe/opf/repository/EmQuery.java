package com.alethe.opf.repository;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class EmQuery {

	@PersistenceContext
	private EntityManager em;

	public BigInteger getNextval() {

		Query q = em.createNativeQuery("SELECT nextval('so_number');");
		return (BigInteger) q.getSingleResult();

	}

//	public int updateSoStatus(int status, String remark, int so_id) {
//
//		Query u = em.createNativeQuery("update sale_order o  set o.remark =:remark ,o.so_status =:status ")
//				.setParameter(2, remark);
//		u.executeUpdate();
//
//		return 1;
//	}

}