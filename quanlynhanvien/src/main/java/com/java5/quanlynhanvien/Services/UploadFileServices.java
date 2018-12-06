package com.java5.quanlynhanvien.Services;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileServices {
	public String upLoadFileAvatar(MultipartFile file) throws IOException;

	public Resource getImage(String filename) throws MalformedURLException;
}
