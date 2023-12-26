package com.spring.Assignment1.service.impl;

import com.spring.Assignment1.model.DonationDTO;
import com.spring.Assignment1.model.UserDonationDTO;

import java.util.List;

public interface DonationService {
    List<DonationDTO> getDonations();

    List<DonationDTO> getDonationActive();

    void addDonation(DonationDTO donationDTO );

    void updateDonation(DonationDTO donationDTO);

    void deleteDonation(int theId);

    DonationDTO detail(int theId);

    void stateChange(int theId);

    List<DonationDTO> getDonationByPage(int pageId, int total);


//    DonationDTO getDonationId(int theId);
}
