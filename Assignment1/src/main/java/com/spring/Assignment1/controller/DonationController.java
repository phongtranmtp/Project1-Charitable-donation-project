package com.spring.Assignment1.controller;

import com.spring.Assignment1.model.DonationDTO;
import com.spring.Assignment1.model.UserDonationDTO;
import com.spring.Assignment1.service.impl.DonationService;
import com.spring.Assignment1.service.impl.UserDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/donation")
public class DonationController {

    @Autowired
    DonationService donationService;

    @Autowired
    UserDonationService userDonationService;

    // Hàm hiển thị danh sách quyên góp
    @GetMapping("/list")
    public String getDonations(Model theModel,UserDonationDTO userDonationDTO){
        List<DonationDTO> donationDTOs = donationService.getDonations();
        theModel.addAttribute("donationList", donationDTOs);

        return "admin/donation";
    }

    // Thêm mới 1 đợt quyên góp
    @PostMapping("/addDonation")
    public String addDonation(@ModelAttribute("donationDTO") DonationDTO donationDTO){
        donationService.addDonation(donationDTO);

        return "redirect:/admin/donation/list";
    }

    // Cập nhật thông tin của một đợt quyên góp khi chưa ở trạng thái ‘đóng quyên góp’
    @PostMapping("/updateDonation")
    public String updateDonation(@ModelAttribute("donationDTO") DonationDTO donationDTO){
            donationService.updateDonation(donationDTO);
        return "redirect:/admin/donation/list";
    }

    // Xem chi tiết thông tin của đợt quyên góp
    @GetMapping("/detail")
    public String detailDonation(@RequestParam("donationId") int theId,Model theModel){
        DonationDTO donationDTO = donationService.detail(theId);
        theModel.addAttribute("donation",donationDTO);

        List<UserDonationDTO> userDonationDTO = userDonationService.getDonations();
        theModel.addAttribute("userDonationList",userDonationDTO);
        return "admin/detail";
    }

    // Thay đổi trạng thái của một đợt quyên góp
    @PostMapping("/stateChange")
    public String stateChange(@RequestParam("donationId") int theId){
        donationService.stateChange(theId);
        return "redirect:/admin/donation/list";
    }

    // Xóa một đợt quyên góp ở trạng thái ‘mới tạo’ (chỉ ẩn thông tin bên client user)
    @GetMapping("/deleteDonation")
    public String deleteDonation(@RequestParam("donationId") int theID){
        donationService.deleteDonation(theID);

        return "redirect:/admin/donation/list";
    }
}
