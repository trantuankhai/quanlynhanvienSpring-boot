package com.java5.quanlynhanvien.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTS")
public class Departs {

	@Id
	@Column(name = "ID_DEPARTS", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_departs;
	@Column(name = "NAME_DEPARTS", nullable = false)
	public String name_departs;

	public int getId_departs() {
		return id_departs;
	}

	public void setId_departs(int id_departs) {
		this.id_departs = id_departs;
	}

	public String getName_departs() {
		return name_departs;
	}

	public void setName_departs(String name_departs) {
		this.name_departs = name_departs;
	}

	public Departs(int id_departs, String name_departs) {
		super();
		this.id_departs = id_departs;
		this.name_departs = name_departs;
	}

	public Departs() {
		// TODO Auto-generated constructor stub
	}
}
