package com.java5.quanlynhanvien.Services;

import java.util.List;

import com.java5.quanlynhanvien.model.Staffs;
import com.java5.quanlynhanvien.model.StaffsExtend;

public interface StaffServices {

	// GET ALL
	public List<Staffs> listStaffs();

// ADD STAFFS
	public int addStaffs(Staffs staffs);

// DELETE STAFFS
	public String deleteStaffs(int id);

	// EDIT STAFFS
	public String editStaffs(int id, Staffs staffs);

	public List<Staffs> layKhongNhanVien(int max);

	public long sumPage(long showOnePage);

	public List<Staffs> chuyenHuongTrang(int min, int max);
	public List<StaffsExtend> thongKeThanhTichNhanVien(int first , int end);
}
