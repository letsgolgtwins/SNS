package com.sns.post.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.post.Entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

	// JPQL
	// 타임라인 목록 뿌리기 - db에서 List select
	public List<PostEntity> findByOrderByIdDesc();
}
