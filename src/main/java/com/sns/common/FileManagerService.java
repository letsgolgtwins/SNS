package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

	// 실제 업로드 된 이미지가 저장될 서버의 경로
	public static final String FILE_UPLOAD_PATH = "C:\\이민희\\6_spring_project\\sns\\sns_workspace\\images/";

	// input: MultipartFile, userLoginId
	// output: String (이미지 경로)
	public String uploadFile(MultipartFile file, String loginId) { // 궁금증1 LoginId는 session에 userLoginId로 저장되어 있는데 왜 여기선 session으로 안가져옴?
		// 폴더(디렉토리) 생성
		String directoryName = loginId + "_" + System.currentTimeMillis();
		
		// 파일경로명 
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		// 폴더 생성
		File directory = new File(filePath);
		if (directory.mkdir() == false) { // 만약 폴더생성에 실패할 시,
			return null; // null을 return 하겟다.
			// 궁금증2. imagePath는 not null 이라 무조건 들어가야 하는데 왜 null?
		}
		
		// 파일 업로드
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null; // 이미지 업로드 실패시 경로 null return
			// 궁금증2. imagePath는 not null 이라 무조건 들어가야 하는데 왜 null
		}
		
		// 파일업로드가 성공할 시 url path를 리턴
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
	}
}
