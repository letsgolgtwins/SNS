package com.sns.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

	// Test 4번 용도 (나중에 쓰지 말 것)
	public List<Map<String, Object>> selectPostList();
}
