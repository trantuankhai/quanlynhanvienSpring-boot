package com.java5.quanlynhanvien.DAO;

import java.util.List;

import com.java5.quanlynhanvien.model.Staffs;
import com.java5.quanlynhanvien.model.StaffsExtend;

public interface StaffDAO {
	public List<Staffs> listStaffs();

	public int addStaffs(Staffs staffs);

	public String editStaffs(int id, Staffs staffs);

	public String deleteStaffs(int id);

	public List<Staffs> layKhoangNhanVien(int max);

	public long sumPage(long showOnePage);

	public List<Staffs> chuyenHuongTrang(int min, int max);

	public List<Staffs> top10Staffs();

	public List<StaffsExtend> thongKeThanhTichNhanVien(int first , int end);
}
