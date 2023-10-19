package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.cuggishop.dto.request.SignInDto;
import com.tencoding.cuggishop.dto.request.SignUpDto;
import com.tencoding.cuggishop.dto.response.QnaListResponseDto;
import com.tencoding.cuggishop.dto.response.UserInfoDetailDto;
import com.tencoding.cuggishop.dto.response.UserInfoListDto;
import com.tencoding.cuggishop.repository.model.User;

@Mapper
public interface UserRepository {
	public int insert(SignUpDto user);
	public int updateById(@Param("user") User user);
	public int deleteById(int id);
	public User findById(int id);
	public List<User> findByAll();
	public User findByUsernameAndPassword(SignInDto signInDto);
	public User findByUsername(String username);
	public User findByEmail(String email);
	public User findByUsernameAndEmail(@Param("username") String username, @Param("email") String email);
	public List<UserInfoListDto> findByKeywordAndCurrentPage(@Param("type")String type, @Param("keyword")String keyword, @Param("startNum")Integer startNum, @Param("status")String status);
	public UserInfoDetailDto findByIdAtAdmin(int id);

}