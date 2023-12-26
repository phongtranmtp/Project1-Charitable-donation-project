package com.spring.Assignment1.controller;

import com.spring.Assignment1.model.DonationDTO;
import com.spring.Assignment1.model.UserDTO;
import com.spring.Assignment1.model.UserDonationDTO;
import com.spring.Assignment1.service.impl.DonationService;
import com.spring.Assignment1.service.impl.UserDonationService;
import com.spring.Assignment1.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserDonationController {

    @Autowired
    UserService userService;

    @Autowired
    UserDonationService userDonationService;

    @Autowired
    DonationService donationService;

    //Hàm hiển thị danh sách các đợt quyên góp và phân trang
    @GetMapping("/home")
    public String getUserDonations(@RequestParam(value = "page_id", required = false) Integer page_id, Model theModel) {
        int total = 5;

        page_id = (page_id == null) ? 0 : page_id;
        Integer start = page_id;
        if (start >= 1) {
            start = start * total;
        }

        List<DonationDTO> donationDTOs = donationService.getDonationByPage(start, total);
        theModel.addAttribute("donationList", donationDTOs);

        List<UserDTO> theUsers = userService.getUsers();
        theModel.addAttribute("userList", theUsers);

        List<UserDonationDTO> userDonationDTO = userDonationService.getDonations();
        theModel.addAttribute("userDonationList", userDonationDTO);


        theModel.addAttribute("page", page_id);
        return "public/home";

    }

    // Hàm xem chi tiết 1 đợt quyên góp
    @GetMapping("/detail")
    public String userDetailDonation(@RequestParam("id") int theId, Model theModel) {
        // id của dnation
        List<UserDonationDTO> userDonationDTO = userDonationService.detail(theId);
        theModel.addAttribute("userDonationList", userDonationDTO);

        DonationDTO donationDTO = donationService.detail(theId);
        theModel.addAttribute("donation", donationDTO);

        List<UserDTO> theUsers = userService.getUsers();
        theModel.addAttribute("userList", theUsers);

        return "public/detail";
    }

    //hàm thực hiện quyên góp nhanh
    @PostMapping("/donation")
    public String donation(@ModelAttribute("userDonationDTO") UserDonationDTO userDonationDTO) {
        userDonationService.donation(userDonationDTO);
        return "redirect:/user/home";
    }

    ///* hàm quyên góp trong form xem chi tiết*
    @PostMapping("/detail")
    public String donationInTheDetailForm(@ModelAttribute("userDonationDTO") UserDonationDTO userDonationDTO,Model theModel) {
        userDonationService.donation(userDonationDTO);

        return "redirect:/user/home";
    }
}
