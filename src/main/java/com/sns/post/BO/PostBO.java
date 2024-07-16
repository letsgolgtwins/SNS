package com.sns.post.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.Entity.PostEntity;
import com.sns.post.Repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	// db에서 select
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc();
	}
}
