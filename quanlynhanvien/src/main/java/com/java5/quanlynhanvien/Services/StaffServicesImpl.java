package com.java5.quanlynhanvien.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java5.quanlynhanvien.DAO.StaffsDaoImpl;
import com.java5.quanlynhanvien.model.Staffs;
import com.java5.quanlynhanvien.model.StaffsExtend;

@Service
public class StaffServicesImpl implements StaffServices {
	@Autowired
	private StaffsDaoImpl staffsDao;

	// GET ALL
	@Override
	public List<Staffs> listStaffs() {
		return staffsDao.listStaffs();
	}

// ADD STAFFS
	@Override
	public int addStaffs(Staffs staffs) {
		return staffsDao.addStaffs(staffs);
	}

// DELETE STAFFS
	@Override
	public String deleteStaffs(int id) {
		return staffsDao.deleteStaffs(id);
	}

	// EDIT STAFFS
	@Override
	public String editStaffs(int id, Staffs staffs) {
		return staffsDao.editStaffs(id, staffs);
	}

	@Override
	public List<Staffs> layKhongNhanVien(int max) {
		return staffsDao.layKhoangNhanVien(max);
	}

	@Override
	public long sumPage(long showOnePage) {
		return staffsDao.sumPage(showOnePage);
	}

	@Override
	public List<Staffs> chuyenHuongTrang(int min, int max) {
		return staffsDao.chuyenHuongTrang(min, max);
	}

	@Override
	public List<StaffsExtend> thongKeThanhTichNhanVien(int first, int end) {
		return staffsDao.thongKeThanhTichNhanVien(first, end);
	}
}
