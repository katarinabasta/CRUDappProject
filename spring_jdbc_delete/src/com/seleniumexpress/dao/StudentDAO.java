package com.seleniumexpress.dao;

import java.util.List;

import com.seleniumexpress.api.Student;

public interface StudentDAO {

	void insert(Student student);

	void insert(List<Student> students);

	// brisanje na osnovu rednog broja u tabeli
	boolean deleteRecordByRollNo(int roolNo);

	int deleteRecordByStudentNameOrStudentAddress(String studentName, String studentAddress);

}
