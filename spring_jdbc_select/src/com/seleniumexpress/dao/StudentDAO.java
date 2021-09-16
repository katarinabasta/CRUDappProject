package com.seleniumexpress.dao;

import java.util.List;

import com.seleniumexpress.api.Student;

public interface StudentDAO {

	void insert(Student student);

	void insert(List<Student> students);

	// dodato za delete metod, part 2
	boolean deleteRecordByRollNo(int rollNo);

	int deleteRecordByStudentNameOrStudentAddress(String studentName, String studentAddress);

//svi studenti koji nalaze u tabeli
	List<Student> findAllStudents();

	Student findStudentsByRollNo(int rollNo);

}
