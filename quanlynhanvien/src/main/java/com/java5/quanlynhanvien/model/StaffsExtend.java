package com.java5.quanlynhanvien.model;

public class StaffsExtend extends Staffs {
	int tongthanhtich;

	public int getTongthanhtich() {
		return tongthanhtich;
	}

	public void setTongthanhtich(int tongthanhtich) {
		this.tongthanhtich = tongthanhtich;
	}

	public StaffsExtend(int id_staffs, Departs departs, String name, int gender, String birthday, String photo,
			String email, String phone, float salary, int rank_staff, String note, int tongthanhtich) {
		super(id_staffs, departs, name, gender, birthday, photo, email, phone, salary, rank_staff, note);
		this.tongthanhtich = tongthanhtich;
	}

	public StaffsExtend() {
	}
}
