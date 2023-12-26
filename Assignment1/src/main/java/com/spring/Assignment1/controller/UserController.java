package com.spring.Assignment1.controller;

import com.spring.Assignment1.model.RoleDTO;
import com.spring.Assignment1.model.UserDTO;
import com.spring.Assignment1.service.impl.RoleService;
import com.spring.Assignment1.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@GetMapping("/home")
	public String Home() {

		return "admin/home";
	}

	// Hiển thị danh sách của người dùng
	@GetMapping("/list")
	public String getUsers(Model theModel) {
		List<UserDTO> theUsers = userService.getUsers();

		theModel.addAttribute("userList", theUsers);

		List<RoleDTO> theRoles = roleService.getRoles();

		theModel.addAttribute("roleList", theRoles);
		return "admin/account";
	}

	// Thêm mới một người dùng
	@PostMapping("/addUser")
	public String addUser(@ModelAttribute("userDTO") UserDTO theUserDTO) {

		userService.addUser(theUserDTO);

		return "redirect:/admin/list";
	}

	//Cập nhật thông tin người dùng
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("userDTO") UserDTO theUserDTO) {
		userService.updateUser(theUserDTO);

		return "redirect:/admin/list";
	}

	// Cho phép/không cho phép một người dùng sử dụng hệ thống
	@PostMapping("/lockandunlock")
	public String lockndUnLock(@RequestParam("userId") int theId){
		userService.lockAndUnLockUser(theId);
		return "redirect:/admin/list";
	}

	//Xóa một người dùng
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") int theId) {

		userService.deleteUser(theId);

		return "redirect:/admin/list";
	}


}
