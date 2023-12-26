package com.spring.Assignment1.service.impl;

import com.spring.Assignment1.entity.User;
import com.spring.Assignment1.model.UserDTO;

import java.util.List;

public interface UserService {

	List<UserDTO> getUsers();

	void addUser(UserDTO theUserDTO);

	void updateUser(UserDTO theUser);

	UserDTO getUserId(int theId);

	void deleteUser(int theId);

	void lockAndUnLockUser(int theId);
}
