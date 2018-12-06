package com.java5.quanlynhanvien.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java5.quanlynhanvien.Services.UploadFileServicesImpl;

@CrossOrigin(origins ="http://localhost:8010")
@RestController
public class UploadAvatarController {
	@Autowired
	private UploadFileServicesImpl uploadFileServices;

	@PostMapping(value = "avatar")
	@PreAuthorize("hasRole('admin')")
	public String uploadAvatar(@RequestBody MultipartFile multipartFile) throws IOException {
		return uploadFileServices.upLoadFileAvatar(multipartFile);
	}

	@GetMapping(value = "avatar/{link}")
	public Resource getAvatar(@PathVariable("link") String link) throws IOException {
		return uploadFileServices.getImage(link);
	}

}
