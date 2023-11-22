package com.tencoding.cuggishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.cuggishop.dto.request.DeleteUserDto;
import com.tencoding.cuggishop.dto.request.SignInDto;
import com.tencoding.cuggishop.dto.request.SignUpDto;
import com.tencoding.cuggishop.dto.request.UpdateUserDto;
import com.tencoding.cuggishop.handler.exception.CustomRestfulException;
import com.tencoding.cuggishop.handler.exception.UnSignUpException;
import com.tencoding.cuggishop.repository.interfaces.PersonRepository;
import com.tencoding.cuggishop.repository.interfaces.UserRepository;
import com.tencoding.cuggishop.repository.model.Person;
import com.tencoding.cuggishop.repository.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	private User imsiLogin() {
		return userRepository.findById(1);
	}

	@Transactional
	public void signUp(SignUpDto signUpDto) {
		String rawPwd = signUpDto.getPassword();
		String hashPwd = passwordEncoder.encode(rawPwd);
		signUpDto.setPassword(hashPwd);
		if (signUpDto.getLevel() == null) {
			signUpDto.setLevel(0);
		}
		int result = userRepository.insert(signUpDto);

		User user = userRepository.findByUsername(signUpDto.getUsername());
		Person person = signUpDto.toPersonEntity(user.getId());
		int result2 = personRepository.insert(person);
		if (result != 1) {
			throw new CustomRestfulException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public User signIn(SignInDto signInDto) {
		User userEntity = userRepository.findByUsername(signInDto.getUsername());

		if (userEntity == null || userEntity.getUsername().equals(signInDto.getUsername()) == false) {
			throw new UnSignUpException("존재하지 않는 계정입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		boolean isPwdMatched = passwordEncoder.matches(signInDto.getPassword(), userEntity.getPassword());
		if (isPwdMatched == false) {
			throw new CustomRestfulException("잘못 입력하셨습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return userEntity;
	}

	public User searchUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void updateUserForm(UpdateUserDto updateUserDto) {
		User user = userRepository.findById(updateUserDto.getUserId());

		if (updateUserDto == null || user.getUsername().contains("_kakao")) {
			updateUserDto.setPassword(user.getPassword());
		} else {
			String hashPwd = passwordEncoder.encode(updateUserDto.getPassword());
			updateUserDto.setPassword(hashPwd);
		}

		User userEntity = updateUserDto.toUserEntity();
		Person personEntity = updateUserDto.toPersonEntity();
		userRepository.updateById(userEntity);
		personRepository.updateByUserId(personEntity);
		Person persons = personRepository.findByUserId(personEntity.getUserId());

	}

	public UpdateUserDto findUserandPerson(int id) {
		User userEntity = userRepository.findById(id);
		Person personEntity = personRepository.findByUserId(id);
		UpdateUserDto updateUserDto = new UpdateUserDto(userEntity, personEntity);
		return updateUserDto;
	}

	public void deleteUser(DeleteUserDto deleteUserDto, User user) {
		User userEntity = userRepository.findById(user.getId());
		// Person personEntity = personRepository.findByUserId(person.getUserId());

		if (!userEntity.getUsername().contains("_kakao")) {
			boolean isPwdMatched = passwordEncoder.matches(deleteUserDto.getPassword(), userEntity.getPassword());
			if (isPwdMatched == false) {
				throw new CustomRestfulException("잘못 입력하셨습니다", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (isPwdMatched == true) {
				userRepository.deleteById(userEntity.getId());
				personRepository.deleteByUserId(userEntity.getId());
			}
		} else {
			userRepository.deleteById(userEntity.getId());
			personRepository.deleteByUserId(userEntity.getId());
		}
	}

	public String findId(String email) {
		User userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new CustomRestfulException("존재하지 않은 계정입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return userEntity.getUsername();
	}

	public void findPassword(String username, String email, String newPwd) {
		User userEntity = userRepository.findByUsernameAndEmail(username, email);
		if (userEntity == null) {
			throw new CustomRestfulException("존재하지 않은 계정입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String hashPwd = passwordEncoder.encode(newPwd);
		userEntity.setPassword(hashPwd);
		userRepository.updateById(userEntity);

	}

	public int duplicateCheck(String email, String username) {
		int result = 1;
		User user = new User();
		if (email != null) {
			user = userRepository.findByEmail(email);
		}
		if (username != null) {
			user = userRepository.findByUsername(username);
		}

		if (user != null) {
			result = 0;
		}
		return result;
	}
}
