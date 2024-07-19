package com.sns.post.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.Entity.PostEntity;
import com.sns.post.Repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	// db에서 select
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	// db에 글 및 사진(파일) 업로드 - insert
	// 이때 아웃풋이 왜 PostEntity지? 걍 insert인데
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		
		// fileManagerService이용
		String imagePath =  fileManagerService.uploadFile(file, userLoginId);
		
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
}
