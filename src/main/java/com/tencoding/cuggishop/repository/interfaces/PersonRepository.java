package com.tencoding.cuggishop.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.tencoding.cuggishop.repository.model.Person;

@Mapper
public interface PersonRepository {
	public int insert(Person person);
	public int updateById(Person person);
	public int deleteById(int personId);
	public Person findById(int personId);
	public Person findByUserId(int userId);
	public List<Person> findByAll();
	public int updateByUserId(Person person);
	public void deleteByUserId(int userId);
}
