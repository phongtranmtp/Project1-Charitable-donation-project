package com.spring.Assignment1.dao;

import com.spring.Assignment1.dao.impl.DonationDAO;
import com.spring.Assignment1.entity.Donation;
import com.spring.Assignment1.model.DonationDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DonationDAOImpl implements DonationDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    EntityManager entityManager;

    // lấy ra danh sách quyên góp
    @Override
    public List<Donation> getdonations() {
        TypedQuery<Donation> theQuery = entityManager.createQuery("from Donation",Donation.class);
        List<Donation> donations = theQuery.getResultList();
        return donations;
    }

    // thêm mới 1 đợt quyên góp
    @Override
    public void addDonation(Donation donation) {
        entityManager.persist(donation);
    }

    // cập nhập 1 đợt quyên góp
    @Override
    public void updateDonation(Donation donation) {
        entityManager.merge(donation);
    }

    // hiển thị chi tiết 1 đợt quyên góp
    @Override
    public Donation detail(int theId) {
        Query theQuery = entityManager.createQuery(" from Donation where id=:donationId");

        theQuery.setParameter("donationId",theId);

        Donation donation = (Donation) theQuery.getSingleResult();

        return donation;
    }

    @Override
    public Donation getDonationId(int theId) {
        Donation donation = entityManager.find(Donation.class,theId);
        return donation;
    }

    ///* Trả ra danh sách quyên góp theo phân trang*
    @Override
    public List<Donation> getDonationByPage(int pageId, int total) {
        List<Donation> donations = entityManager.createQuery("from Donation ",Donation.class)
                .setFirstResult(pageId)
                .setMaxResults(total)
                .getResultList();

        return donations;
    }
}
