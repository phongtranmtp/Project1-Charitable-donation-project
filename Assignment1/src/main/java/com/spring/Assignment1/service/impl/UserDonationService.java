package com.spring.Assignment1.service.impl;

import com.spring.Assignment1.dao.impl.UserDonationDAO;
import com.spring.Assignment1.entity.Donation;
import com.spring.Assignment1.entity.UserDonation;
import com.spring.Assignment1.model.UserDonationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDonationService  {
    List<UserDonationDTO> detail(int theId);

    void donation(UserDonationDTO userDonationDTO);

    List<UserDonationDTO> getDonations();

}
