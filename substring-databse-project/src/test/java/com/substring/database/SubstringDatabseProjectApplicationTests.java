package com.substring.database;

import com.substring.database.dao.UserDao;
import com.substring.database.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class SubstringDatabseProjectApplicationTests
{


	@Autowired
	private UserDao userDoa;

	@Test
	void userTest(){

		User user=new User();
		user.setUserId(new Random().nextInt(10000));
		user.setName("Gaurav");
		user.setEmail("gaurav@gmai.com");
		user.setPassword("gaurav123");
		user.setCity("Delhi");
		user.setSalary(10000);

		int rows = userDoa.saveUser(user);
		Assertions.assertEquals(1,rows);


	}

}
