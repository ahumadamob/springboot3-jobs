package com.ahumadamob.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	public static String saveImage(MultipartFile multiPart, String path) {
		String fileName = randomAlphaNumeric(32);
		try {
			File imageFile = new File(path + fileName);
			multiPart.transferTo(imageFile);
			return fileName;
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	
	public static String randomAlphaNumeric(int lenght) {
		String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYXabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder builder = new StringBuilder();
		while(lenght-- != 0) {
			int position = (int) (Math.random() * CHARS.length());
			builder.append(CHARS.charAt(position));
		}
		return builder.toString();
	}

}
