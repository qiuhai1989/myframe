package com.haiqiu.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.haiqiu.entity.User;
import com.haiqiu.serivce.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		
		List<User> users = new ArrayList<>();
		
		for(int i=0;i<5;i++){
			User user = new User();
			user.setId(i);
			user.setName("name"+i);
			users.add(user);
		}
		
		return users;
	}

}
