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
@Table(name = "RECORDS")
public class Records {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="RECORDS_ID")
	private int records_id;
	@ManyToOne()
	@JoinColumn(name="STAFFS_ID_STAFFS")
	private Staffs staffs;
	@Column(name="TYPE_RECORDS")
	private int records_Type;
	@Column(name = "REASON")
	private java.lang.String reason;
	@Column(name = "DATE")
	private java.lang.String date;
	public int getRecords_id() {
		return records_id;
	}
	public void setRecords_id(int records_id) {
		this.records_id = records_id;
	}
	public Staffs getStaffs() {
		return staffs;
	}
	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}

	public int getRecords_Type() {
		return records_Type;
	}
	public void setRecords_Type(int records_Type) {
		this.records_Type = records_Type;
	}
	public Records(int records_id, Staffs staffs, int records_Type) {
		super();
		this.records_id = records_id;
		this.staffs = staffs;
		this.records_Type = records_Type;
	}
	public Records( Staffs staffs, int records_Type) {
		super();

		this.staffs = staffs;
		this.records_Type = records_Type;
	}
	public Records() {
		// TODO Auto-generated constructor stub
	}
	public java.lang.String getReason() {
		return reason;
	}
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}
	public java.lang.String getDate() {
		return date;
	}
	public void setDate(java.lang.String date) {
		this.date = date;
	}
	public Records( Staffs staffs, int records_Type, String reason, String date) {
		super();
		this.staffs = staffs;
		this.records_Type = records_Type;
		this.reason = reason;
		this.date = date;
	}
	
	

}
