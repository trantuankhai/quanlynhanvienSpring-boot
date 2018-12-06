package com.java5.quanlynhanvien.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STAFFS")
public class Staffs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_STAFFS" , nullable = false)
	private int id_staffs;
	@ManyToOne()
	@JoinColumn(name = "DEPARTS_ID_DEPARTS",nullable = false)
	private Departs departs;
	@Column(name = "NAME",nullable= false)
	private String name;
	@Column(name = "GENDER",nullable= false)
	private int gender;
	@Column(name = "BIRTHDAY",nullable= false)
	private String birthday;
	@Column(name = "PHOTO",nullable= false)
	private String photo;
	@Column(name = "EMAIL",nullable= false)
	private String email;
	@Column(name = "PHONE",nullable= false)
	private String phone;
	@Column(name = "SALARY",nullable= false)
	private float salary;
	@Column(name = "RANK_STAFF")
	private int rank_staff;
	@Column(name = "NOTE")
	private String note;

	public int getId_staffs() {
		return id_staffs;
	}

	public void setId_staffs(int id_staffs) {
		this.id_staffs = id_staffs;
	}

	public int getRank_staff() {
		return rank_staff;
	}

	public void setRank_staff(int rank_staff) {
		this.rank_staff = rank_staff;
	}

	public Departs getDeparts() {
		return departs;
	}

	public void setDeparts(Departs departs) {
		this.departs = departs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Staffs(int id_staffs, Departs departs, String name, int gender, String birthday, String photo, String email,
			String phone, float salary, int rank_staff, String note) {
		super();
		this.id_staffs = id_staffs;
		this.departs = departs;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.photo = photo;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.rank_staff = rank_staff;
		this.note = note;
	}

	public Staffs() {
	}

}
