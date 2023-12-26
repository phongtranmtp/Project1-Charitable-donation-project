package com.spring.Assignment1.service;

import com.spring.Assignment1.dao.impl.DonationDAO;
import com.spring.Assignment1.dao.impl.UserDAO;
import com.spring.Assignment1.dao.impl.UserDonationDAO;
import com.spring.Assignment1.entity.Donation;
import com.spring.Assignment1.entity.User;
import com.spring.Assignment1.entity.UserDonation;
import com.spring.Assignment1.model.UserDonationDTO;
import com.spring.Assignment1.service.impl.UserDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDonationServiceImpl implements UserDonationService {
    @Autowired
    UserDonationDAO userDonationDAO;

    @Autowired
    DonationDAO donationDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public List<UserDonationDTO> detail(int theId) {
        List<UserDonation> userDonations = userDonationDAO.detail(theId);
        List<UserDonationDTO> userDonationDTOs = new ArrayList<>();
        for (UserDonation userDonation:userDonations) {
            userDonationDTOs.add(convertUserDonationDTO(userDonation));
        }
        return userDonationDTOs;
    }

    ///* Thực hiện quyên góp và cập nhập tổng tiền ở quản lý đợt quyên góp*
    @Override
    public void donation(UserDonationDTO userDonationDTO) {
        UserDonation userDonation = new UserDonation();
        User user = userDAO.getUserId(userDonationDTO.getUser().getId());
        if (user.getStatus() == 0){
            return;
        } else if (user.getStatus() == 1) {
            userDonation.setName(userDonationDTO.getName());
            userDonation.setMoney(userDonationDTO.getMoney());
            userDonation.setCreated(userDonationDTO.getCreated());
            userDonation.setText(userDonationDTO.getText());
            userDonation.setUser(userDonationDTO.getUser());
            Donation donation = donationDAO.getDonationId(userDonationDTO.getDonation().getId());
            donation.setMoney(donation.getMoney() + userDonationDTO.getMoney());
            donationDAO.updateDonation(donation);
            userDonation.setDonation(donation);
            userDonationDAO.add(userDonation);
        }

    }

    ///* lấy ra 1 danh sách người quyên góp*
    @Override
    public List<UserDonationDTO> getDonations() {
        List<UserDonation> userDonations = userDonationDAO.getUserDonations();
        List<UserDonationDTO> userDonationDTOs = new ArrayList<>();
        for (UserDonation userDonation : userDonations) {
            userDonationDTOs.add(convertUserDonationDTO(userDonation));
        }
        return userDonationDTOs;
    }

    ///* convert từ UserDonation sang UserDonationDTO*
    private UserDonationDTO convertUserDonationDTO(UserDonation userDonation) {
        UserDonationDTO userDonationDTO = new UserDonationDTO();
        userDonationDTO.setId(userDonation.getId());
        userDonationDTO.setName(userDonation.getName());
        userDonationDTO.setCreated(userDonation.getCreated());
        userDonationDTO.setMoney(userDonation.getMoney());
        userDonationDTO.setStatus(userDonation.getStatus());
        userDonationDTO.setText(userDonation.getText());
        userDonationDTO.setUser(userDonation.getUser());
        userDonationDTO.setDonation(userDonation.getDonation());
        return userDonationDTO;
    }

}
