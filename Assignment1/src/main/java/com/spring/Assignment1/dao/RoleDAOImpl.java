package com.spring.Assignment1.dao;

import com.spring.Assignment1.dao.impl.RoleDAO;
import com.spring.Assignment1.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;



@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	EntityManager entityManager;

	// lấy ra 1 danh sách role
	@Override
	public List<Role> getRoles() {
		TypedQuery<Role> theQuery = entityManager.createQuery("from Role",Role.class);

		List<Role> roles = theQuery.getResultList();

		return roles;
	}

}
