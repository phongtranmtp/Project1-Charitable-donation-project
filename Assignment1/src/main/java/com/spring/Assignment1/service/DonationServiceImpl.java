package com.spring.Assignment1.service;

import com.spring.Assignment1.dao.impl.DonationDAO;
import com.spring.Assignment1.entity.Donation;
import com.spring.Assignment1.model.DonationDTO;
import com.spring.Assignment1.model.UserDonationDTO;
import com.spring.Assignment1.service.impl.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DonationServiceImpl implements DonationService {

    @Autowired
    DonationDAO donationDAO;

    @Override // lấy ra danh sách donationDTO
    public List<DonationDTO> getDonations() {
        List<Donation> donations = donationDAO.getdonations();
        List<DonationDTO> donationDTOs = new ArrayList<>();
        for (Donation donation : donations) {
            donationDTOs.add(convertDonationDTO(donation));
        }
        return donationDTOs;
    }

    // lấy ra danh sách các donation đang hoạt động ( người dùng có thể quyên góp tiền)
    public List<DonationDTO> getDonationActive() {
        List<Donation> donations = donationDAO.getdonations();
        List<DonationDTO> donationDTOs = new ArrayList<>();
        for (Donation donation : donations) {
            if (donation.isActive() == true) {
                break;
            } else if (donation.isActive() == false) {
                donationDTOs.add(convertDonationDTO(donation));
            }
        }
        return donationDTOs;
    }

    // thêm mới 1 donation
    @Override
    public void addDonation(DonationDTO donationDTO) {
        Donation donation = new Donation();
        donation.setId(donationDTO.getId());
        donation.setName(donationDTO.getName());
        donation.setCode(donationDTO.getCode());
        donation.setCreated(donationDTO.getCreated());
        donation.setDecription(donationDTO.getDecription());
        donation.setEndDate(donationDTO.getEndDate());
        donation.setMoney(donationDTO.getMoney());
        donation.setOrganizationName(donationDTO.getOrganizationName());
        donation.setPhoneNumber(donationDTO.getPhoneNumber());
        donation.setStartDate(donationDTO.getStartDate());
        donation.setStatus(donationDTO.getStatus());
        donation.setDonations(donationDTO.getDonations());
        donation.setActive(donationDTO.isActive());
        donationDAO.addDonation(donation);
    }

    // Cập nhập donation
    @Override
    public void updateDonation(DonationDTO donationDTO) {
        Donation donation = donationDAO.getDonationId(donationDTO.getId());
        if (donation != null && donation.getStatus() == 3) {
            return;
        } else if (donation != null) {
            donation.setId(donationDTO.getId());
            donation.setName(donationDTO.getName());
            donation.setCode(donationDTO.getCode());
            donation.setCreated(donationDTO.getCreated());
            donation.setDecription(donationDTO.getDecription());
            donation.setEndDate(donationDTO.getEndDate());
            donation.setMoney(donationDTO.getMoney());
            donation.setOrganizationName(donationDTO.getOrganizationName());
            donation.setPhoneNumber(donationDTO.getPhoneNumber());
            donation.setStartDate(donationDTO.getStartDate());
            donation.setStatus(donationDTO.getStatus());
            donation.setDonations(donationDTO.getDonations());
            donation.setActive(donationDTO.isActive());
            donationDAO.updateDonation(donation);
        }
    }

    // Xóa 1 đợt quyên góp
    @Override
    public void deleteDonation(int theId) {
        Donation donation = donationDAO.getDonationId(theId);
        if (donation != null && donation.getStatus() == 0) {
            donation.setDonations(donation.getDonations());
            donation.setActive(true);
            donationDAO.updateDonation(donation);
        }
    }

    // hiển thị chi tiết 1 đợt quyên góp
    @Override
    public DonationDTO detail(int theId) {
        Donation donation = donationDAO.detail(theId);
        DonationDTO donationDTO = convertDonationDTO(donation);
        return donationDTO;
    }

    // Thay đổi trạng thái của donation
    @Override
    public void stateChange(int theId) {
        Donation donation = donationDAO.getDonationId(theId);
        if (donation != null) {
            if (donation.getStatus() == 0) {
                donation.setStatus(1);
            } else if (donation.getStatus() == 1) {
                donation.setStatus(2);
            } else if (donation.getStatus() == 2) {
                donation.setStatus(3);
            }
        }
    }

    // lấy ra danh sách quyên góp được phép quyên góp theo phân trang
    @Override
    public List<DonationDTO> getDonationByPage(int pageId, int total) {
        List<Donation> donations = donationDAO.getDonationByPage(pageId,total);
        List<DonationDTO> donationDTOs = new ArrayList<>();
        for (Donation donation : donations) {
            if (donation.isActive() == true) {
                break;
            } else if (donation.isActive() == false) {
                donationDTOs.add(convertDonationDTO(donation));
            }
        }
        return donationDTOs;
    }


    // convert donation sang donationDTO
    public DonationDTO convertDonationDTO(Donation donation) {
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setId(donation.getId());
        donationDTO.setName(donation.getName());
        donationDTO.setCode(donation.getCode());
        donationDTO.setCreated(donation.getCreated());
        donationDTO.setDecription(donation.getDecription());
        donationDTO.setEndDate(donation.getEndDate());
        donationDTO.setMoney(donation.getMoney());
        donationDTO.setOrganizationName(donation.getOrganizationName());
        donationDTO.setPhoneNumber(donation.getPhoneNumber());
        donationDTO.setStartDate(donation.getStartDate());
        donationDTO.setStatus(donation.getStatus());
        donationDTO.setDonations(donation.getDonations());
        donationDTO.setActive(donation.isActive());
        return donationDTO;
    }
}
