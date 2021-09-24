package com.seleniumexpress.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seleniumexpress.api.Student;

public class StudentRowMapper implements RowMapper<Student> {

	// mapira red u tabeli sa objektom klase Student
	// map daje ResultSet (podaci koji se nalaze u tabeli) jedan po jedan
	@Override
	public Student mapRow(ResultSet res, int rowNum) throws SQLException {

		Student newStudent = new Student();

		newStudent.setID(res.getInt("ID"));
		newStudent.setName(res.getString("STUDENT_NAME"));
		newStudent.setAddress(res.getString("STUDENT_ADDR"));

		// System.out.println("mapRow() called...");

		return newStudent;
	}

}
