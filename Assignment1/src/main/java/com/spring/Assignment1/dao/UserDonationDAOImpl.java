package com.spring.Assignment1.dao;

import com.spring.Assignment1.dao.impl.UserDonationDAO;
import com.spring.Assignment1.entity.UserDonation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDonationDAOImpl implements UserDonationDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    EntityManager entityManager;

    ///* Lấy ra danh sách người quyên góp theo id của mỗi quyên góp để thực hiện xem chi tiết*
    @Override
    public  List<UserDonation> detail(int theId) {
        Query theQuery = entityManager.createQuery("select sd from UserDonation sd " +
                "inner join sd.user " +
                "where( sd.donation.id =:id)");

        theQuery.setParameter("id", theId);
        List<UserDonation> userDonationDTOs =  theQuery.getResultList();

        return userDonationDTOs;
    }

    @Override
    public UserDonation getUserDonationId(int id) {
        UserDonation userDonation = entityManager.find(UserDonation.class, id);
        return userDonation;
    }

    ///* thêm mới người quyên góp *
    @Override
    public void add(UserDonation userDonation) {
        entityManager.persist(userDonation);
    }

    ///* Lấy ra danh sách người quyên góp *
    @Override
    public List<UserDonation> getUserDonations() {
        Query theQuery = entityManager.createQuery("from UserDonation", UserDonation.class);
        List<UserDonation> userDonations = theQuery.getResultList();
        return userDonations;
    }

}
