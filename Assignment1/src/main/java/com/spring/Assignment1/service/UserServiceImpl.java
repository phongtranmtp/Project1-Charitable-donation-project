package com.spring.Assignment1.service;

import com.spring.Assignment1.dao.impl.UserDAO;
import com.spring.Assignment1.entity.User;
import com.spring.Assignment1.model.UserDTO;
import com.spring.Assignment1.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	///* lấy ra danh sách người dùng*
	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userDAO.getUsers();
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User user : users) {
			userDTOs.add(convertDTO(user));
		}
		return userDTOs;
	}

	///* thêm mới 1 người dùng*
	@Override
	public void addUser(UserDTO theUserDTO) {
		User user = new User();
		user.setId(theUserDTO.getId());
		user.setFullName(theUserDTO.getFullName());
		user.setAddress(theUserDTO.getAddress());
		user.setEmail(theUserDTO.getEmail());
		user.setPassword(theUserDTO.getPassword());
		user.setPhoneNumber(theUserDTO.getPhoneNumber());
		user.setStatus(theUserDTO.getStatus());
		user.setUserName(theUserDTO.getUserName());
		user.setCreated(theUserDTO.getCreated());
		user.setNote(theUserDTO.getNote());
		user.setRole(theUserDTO.getRole());
		user.setUserDonations(theUserDTO.getUserDonations());
		userDAO.addUser(user);
	}

	/////* Cập nhập 1 người dùng*
	@Override
	public void updateUser(UserDTO theUserDTO) {
		User user = userDAO.getUserId(theUserDTO.getId());
		if (user != null){
			user.setId(theUserDTO.getId());
			user.setFullName(theUserDTO.getFullName());
			user.setAddress(theUserDTO.getAddress());
			user.setEmail(theUserDTO.getEmail());
			user.setPassword(theUserDTO.getPassword());
			user.setPhoneNumber(theUserDTO.getPhoneNumber());
			user.setStatus(theUserDTO.getStatus());
			user.setUserName(theUserDTO.getUserName());
			user.setCreated(theUserDTO.getCreated());
			user.setNote(theUserDTO.getNote());
			user.setRole(theUserDTO.getRole());
			user.setUserDonations(theUserDTO.getUserDonations());
			userDAO.updateUser(user);
		}
	}

	@Override
	public UserDTO getUserId(int theId) {
		User user = userDAO.getUserId(theId);

		UserDTO userDTO = new UserDTO();
		userDTO = convertDTO(user);
		return userDTO;
	}

	///* Xóa 1 người dùng*
	@Override
	public void deleteUser(int theId) {
		userDAO.deleteUser(theId);
	}

	///* Thực hiện cho phép/không cho phép người dùng sử dụng hệ thống*
	@Override
	public void lockAndUnLockUser(int theId) {
		User user = userDAO.getUserId(theId);
		if (user != null){
			if (user.getStatus() == 1){
				user.setStatus(0);
			} else if (user.getStatus() == 0) {
				user.setStatus(1);
			}
		}
	}

	///* convert User sang UserDTO*
	public UserDTO convertDTO(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFullName(user.getFullName());
		userDTO.setAddress(user.getAddress());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		userDTO.setStatus(user.getStatus());
		userDTO.setUserName(user.getUserName());
		userDTO.setCreated(user.getCreated());
		userDTO.setNote(user.getNote());
		userDTO.setRole(user.getRole());
		userDTO.setUserDonations(user.getUserDonations());
		return userDTO;
	}
}
