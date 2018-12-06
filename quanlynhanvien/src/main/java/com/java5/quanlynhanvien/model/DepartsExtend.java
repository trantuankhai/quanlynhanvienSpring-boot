package com.java5.quanlynhanvien.model;


public class DepartsExtend extends Departs  {
	int thanhtich;
	public DepartsExtend() {
	}
	public int getThanhtich() {
		return thanhtich;
	}
	public void setThanhtich(int thanhtich) {
		this.thanhtich = thanhtich;
	}
	public DepartsExtend(int id_departs, String name_departs, int thanhtich) {
		super(id_departs, name_departs);
		this.thanhtich = thanhtich;
	}
	
	



}
