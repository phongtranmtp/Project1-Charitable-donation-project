package com.spring.Assignment1.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "role_name")
	private String roleName;
	
	@OneToMany(mappedBy = "role",
			cascade = {CascadeType.MERGE,
					CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
	private List<User> users;

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
