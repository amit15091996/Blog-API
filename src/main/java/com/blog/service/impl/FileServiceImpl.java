package com.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) throws IOException {

		
		String name = multipartFile.getOriginalFilename();

		String randomID = UUID.randomUUID().toString();

		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

		String filePath = path + File.separator + fileName1;

		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		return new FileInputStream(fullPath);
	}

}
