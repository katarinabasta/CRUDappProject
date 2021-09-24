package com.seleniumexpress.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seleniumexpress.api.Student;
import com.seleniumexpress.dao.StudentDAOImpl;

//Pomocna metoda za unos vise od jednog rekorda u bazu

@Service("studentDaoHelper")
public class StudentDAOHelper {

	@Autowired
	private StudentDAOImpl studentDAOImpl;

	public void setUpSTudentTable() {

		Student student1 = new Student();

		student1.setID(1);
		;
		student1.setName("Kristina");
		student1.setAddress("BGD");

		Student student2 = new Student();

		student2.setID(2);
		student2.setName("Milan");
		student2.setAddress("Laz");

		Student student3 = new Student();

		student3.setID(3);
		student3.setName("Mina");
		student3.setAddress("Ar");
		
		Student student4 = new Student();

		student4.setID(4);
		student4.setName("Jelena");
		student4.setAddress("KG");

		ArrayList<Student> studentList = new ArrayList<>();
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		studentList.add(student4);
		
		studentDAOImpl.insert(studentList);
	}

	public void printStudents(List<Student> students) {
		for (Student tempStudent : students) {

			System.out.println(tempStudent);
		}

	}

}
