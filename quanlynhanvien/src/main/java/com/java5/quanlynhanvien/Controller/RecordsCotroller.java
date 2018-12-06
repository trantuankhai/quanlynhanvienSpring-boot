package com.java5.quanlynhanvien.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java5.quanlynhanvien.DAO.RecordsDaoImpl;
import com.java5.quanlynhanvien.Services.RecordsServicesImpl;
import com.java5.quanlynhanvien.model.Records;

@CrossOrigin(origins ="http://localhost:8010")
@RestController
public class RecordsCotroller {
	@Autowired
	private RecordsServicesImpl services;

	@GetMapping(value = "records")
	@PreAuthorize("hasRole('admin')")
	public List<Records> listRecords() {
		return services.listRecords();
	}

	@PostMapping(value = "records")
	@PreAuthorize("hasRole('admin')")
	public int addRecords(@RequestParam("manv") int manv, @RequestParam("type") int type,
			@RequestParam("reason") String reason, @RequestParam("date") String date) {
		return services.addRecords(manv, type, reason, date);

	}

	@GetMapping(value = "records/{id_staffs}")
	@PreAuthorize("hasRole('admin')")
	public List<Records> recordsByIdStaffs(@PathVariable("id_staffs") int id_staffs) {
		return services.recordsByIdStaffs(id_staffs);
	}
}
