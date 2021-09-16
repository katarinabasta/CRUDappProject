package com.seleniumexpress.test;

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
		List<Student> studentList = studentDao.findAllStudents();

		helper.printStudents(studentList);

		// Upit za pojedinacan rekord iz baze
		System.out.println("Fetching the student with the roll no 2");
		Student student = studentDao.findStudentsByRollNo(2);
		System.out.println(student);

		// deo koji se odnosi na brisanje cele tabele

		studentDao.cleanUp();
	}

}
