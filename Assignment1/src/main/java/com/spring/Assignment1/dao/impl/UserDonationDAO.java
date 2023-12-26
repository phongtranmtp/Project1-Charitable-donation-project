package com.spring.Assignment1.dao.impl;

import com.spring.Assignment1.entity.UserDonation;
import com.spring.Assignment1.model.UserDonationDTO;

import java.util.List;

public interface UserDonationDAO {
    List<UserDonation> detail(int theId);

    UserDonation getUserDonationId(int id);

    void add(UserDonation userDonation);

    List<UserDonation> getUserDonations();

}
