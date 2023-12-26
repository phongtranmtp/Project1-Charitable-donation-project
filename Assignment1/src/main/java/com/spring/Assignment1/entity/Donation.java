package com.spring.Assignment1.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "donation")
public class Donation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "created")
	private String created;
	
	@Column(name = "decription")
	private String decription;
	
	@Column(name = "end_date")
	private String endDate;
	
	@Column(name = "money")
	private int money;
	
	@Column(name = "organization_name")
	private String organizationName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "status")
	private int status;

	private boolean active;
	
	@OneToMany(mappedBy = "donation",cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH ,CascadeType.REMOVE} ,
			fetch = FetchType.EAGER)
	private List<UserDonation> donations;

	public Donation() {
	}

	public Donation(String name, String code, String created, String decription, String endDate, int money,
			String organizationName, String phoneNumber, String startDate, int status,boolean active) {
		this.name = name;
		this.code = code;
		this.created = created;
		this.decription = decription;
		this.endDate = endDate;
		this.money = money;
		this.organizationName = organizationName;
		this.phoneNumber = phoneNumber;
		this.startDate = startDate;
		this.status = status;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<UserDonation> getDonations() {
		return donations;
	}

	public void setDonations(List<UserDonation> donations) {
		this.donations = donations;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
