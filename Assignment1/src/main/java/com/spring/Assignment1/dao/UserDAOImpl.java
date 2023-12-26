package com.spring.Assignment1.dao;

import com.spring.Assignment1.dao.impl.UserDAO;
import com.spring.Assignment1.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    EntityManager entityManager;

    ///* lấy ra danh sách người dùng*
    @Override
    public List<User> getUsers() {

        TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);
        List<User> users = theQuery.getResultList();

        return users;
    }

    ///* thêm mới 1 người dùng*
    @Override
    public void addUser(User theUser) {
        entityManager.persist(theUser);
    }

    ///* Cập nhập thông của 1 người dùng*
    @Override
    public void updateUser(User theUser) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(theUser);
    }

    @Override
    public User getUserId(int theId) {
        User theUser = entityManager.find(User.class, theId);
        return theUser;
    }

    ///* Xóa 1 người dùng*
    @Override
    public void deleteUser(int theId) {

        Query theQuery = (Query) entityManager.createQuery("delete from User where id=:userId");
        theQuery.setParameter("userId", theId);
        theQuery.executeUpdate();
    }

}
