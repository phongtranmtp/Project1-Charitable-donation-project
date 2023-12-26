package com.spring.Assignment1.dao.impl;

import com.spring.Assignment1.entity.Donation;
import com.spring.Assignment1.model.DonationDTO;

import java.util.List;

public interface DonationDAO {
    List<Donation> getdonations();

    void addDonation(Donation donation);

    void updateDonation(Donation donation);

    Donation detail(int theId);

    Donation getDonationId(int theId);

    List<Donation> getDonationByPage(int pageId, int total);
}
