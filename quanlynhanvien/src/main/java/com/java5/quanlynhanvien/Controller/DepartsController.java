package com.java5.quanlynhanvien.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java5.quanlynhanvien.Services.DepartServicesImpl;
import com.java5.quanlynhanvien.model.Departs;
import com.java5.quanlynhanvien.model.DepartsExtend;
@CrossOrigin(origins ="http://localhost:8010")
@RestController
public class DepartsController {
	@Autowired
	private DepartServicesImpl departsServices;
	// GET ALL DEPARTS
	@RequestMapping(value = "departs", method = RequestMethod.GET)
	@PreAuthorize("hasRole('admin')")
	public List<Departs> listDeparts() {
		return departsServices.listDeparts();
	}
    // UPDATE DEPARTS
	@RequestMapping(value = "departs/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('admin')")
	public String updateDeparts(@PathVariable("id") int id, String name) {
		return departsServices.editDeparts(id, name);
	}
    // DELETE DEPARTS
	@RequestMapping(value = "departs/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('admin')")
	public String deleteDeparts(@PathVariable("id") int id) {
		return departsServices.deleteDeparts(id);

	}
	/// ADD DEPARTS
	@RequestMapping(value = "departs", method = RequestMethod.POST)
	@PreAuthorize("hasRole('admin')")
	public int addDeparts(@RequestBody Departs departs) {
		return departsServices.addDeparts(departs);
	}
    // GET SUM PAGE
	@GetMapping(value = "departs/sumPage/{showInPage}")
	@PreAuthorize("hasRole('admin')")
	public long sumPage(@PathVariable("showInPage") long showInPage) {
		return departsServices.sumPage(showInPage);
	}
	// CHUYỂN TRANG
	@GetMapping(value = "departs/page")
	@PreAuthorize("hasRole('admin')")
	public List<Departs> pagination(@RequestParam("First") int min , @RequestParam("End") int max) {
		return departsServices.showPagination(min, max);

	}
	 // ĐẾM SỐ LƯỢNG NHÂN VIEN
	@GetMapping(value = "departs/countStaffs/{id}")
	@PreAuthorize("hasRole('admin')")
	public long countStaffsInDeparts(@PathVariable("id") int id) {
		return departsServices.countStaffsInDeparts(id);
	}
	// LẤY SỐ LƯỢNG
	@GetMapping(value="departs/thanhtich")
	@PreAuthorize("hasRole('admin')")
	public List<DepartsExtend> listDepartsSl(@RequestParam("min") int min ,@RequestParam("max") int max){
		return departsServices.listDepartsSl(min , max);
	}
}