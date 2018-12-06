package com.java5.quanlynhanvien.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java5.quanlynhanvien.Services.StaffServicesImpl;
import com.java5.quanlynhanvien.model.Staffs;
import com.java5.quanlynhanvien.model.StaffsExtend;

@CrossOrigin(origins ="http://localhost:8010")
@RestController
public class StaffsController {
	@Autowired
	private StaffServicesImpl staffServices;

	@RequestMapping(value = "staffs", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin')")
	public List<Staffs> listStaffs() {
		return staffServices.listStaffs();
	}

	@RequestMapping(value = "staffs", method = RequestMethod.POST)
	@PreAuthorize("hasRole('admin')")
	public int addStaffs(@RequestBody Staffs staffs) {
		return staffServices.addStaffs(staffs);
	}

	@RequestMapping(value = "staffs/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('admin')")
	public String updateStaffs(@PathVariable("id") int id, @RequestBody Staffs staffs) {
		return staffServices.editStaffs(id, staffs);
	}

	@RequestMapping(value = "staffs/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('admin')")
	public String deleteStaffs(@PathVariable("id") int id) {
		return staffServices.deleteStaffs(id);
	}

	@RequestMapping(value = "staffs/About/{max}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin')")
	public List<Staffs> getKhoangNhanVien(@PathVariable("max") int max) {
		return staffServices.layKhongNhanVien(max);
	}

	@RequestMapping(value = "staffs/navigationPage/{min}/{max}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin')")
	public List<Staffs> sumpage(@PathVariable("min") int min, @PathVariable("max") int max) {
		return staffServices.chuyenHuongTrang(min, max);
	}

	@RequestMapping(value = "staffs/sumPage/{showOnePage}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin')")
	public long chuyenTrang(@PathVariable("showOnePage") long showOnePage) {
		return staffServices.sumPage(showOnePage);
	}

	@RequestMapping(value = "staffs/thanhtich", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin')")
	public List<StaffsExtend> thanhTich(@RequestParam("First") int Firts, @RequestParam("End") int End) {
		return staffServices.thongKeThanhTichNhanVien(Firts, End);
	}
}