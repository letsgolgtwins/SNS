package com.sns.post.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.post.Entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

	// JPQL
	public List<PostEntity> findByOrderByIdDesc();
}
