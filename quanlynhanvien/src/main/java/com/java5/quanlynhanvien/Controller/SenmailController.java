package com.java5.quanlynhanvien.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java5.quanlynhanvien.Services.SendMailService;
@CrossOrigin(origins ="http://localhost:8010")
@RestController
public class SenmailController {
	@Autowired
	private SendMailService sendMailServices;
	@GetMapping("sendMail")
	@PreAuthorize("hasRole('admin')")
	public String sendMail(@RequestParam String to , int id_staffs) {
		if(sendMailServices.senMail(to, id_staffs)) {
			return"Thành Công";
		}else {
			return"Thất Bại";
		}
	}

}
