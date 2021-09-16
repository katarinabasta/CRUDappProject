package com.seleniumexpress.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seleniumexpress.api.Student;
//import com.seleniumexpress.dao.StudentDAO;
import com.seleniumexpress.dao.StudentDAOImpl;
import com.seleniumexpress.service.StudentDAOHelper;

public class Test {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("Application context loaded..");

		StudentDAOHelper helper = context.getBean("studentDaoHelper", StudentDAOHelper.class);
		// upis podataka u bazu
		helper.setUpSTudentTable();

		// poziv findAllStudents() metode koja cita podatke iz baze
		StudentDAOImpl studentDao = context.getBean("studentDao", StudentDAOImpl.class);

	
		Student mina  = new Student();
		mina.setAddress("BGD");
		mina.setRollNo(3);
		
		Student milan  = new Student();
		milan.setAddress("BGD");
		milan.setRollNo(2);
		
		//za apdejt jednog podatka
	//studentDao.updateStudent(mina);
		
		//apdejtuje adresu za dva studenta iz tabele i menja je u BGD
		List <Student> studentList = new ArrayList<Student>();
		studentList.add(mina);
		studentList.add(milan);
		
		//batch apdejt
		studentDao.updateStudent(studentList);
		
		
	}

}
