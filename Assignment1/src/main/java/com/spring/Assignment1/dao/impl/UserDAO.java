package com.spring.Assignment1.dao.impl;


import com.spring.Assignment1.entity.User;

import java.util.List;


public interface UserDAO {

	List<User> getUsers();

	void addUser(User theUser);

	void updateUser(User theUser);

	User getUserId(int theId);

	void deleteUser(int theId);


}
