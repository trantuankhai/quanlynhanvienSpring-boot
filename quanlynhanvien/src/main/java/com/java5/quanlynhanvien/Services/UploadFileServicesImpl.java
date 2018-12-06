package com.java5.quanlynhanvien.Services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServicesImpl implements UploadFileServices {
	private final String UPLOAD_AVATAR = System.getProperty("user.dir")+"/imagesAvatar";
	// UPLOAD FILE
	@Override
	public String upLoadFileAvatar(MultipartFile file) throws IOException{
		File file2 = new File(UPLOAD_AVATAR);
		file2.mkdirs();
		if (file.isEmpty()) {
			return"Empty";
			
		}else {
			String uploadFilePath = UPLOAD_AVATAR + "/" + file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadFilePath);
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
	}
	@Override
	public Resource getImage(String filename) throws MalformedURLException {
		File file = new File(UPLOAD_AVATAR + "/" + filename);
		return new UrlResource(file.toURI());
	}

}
