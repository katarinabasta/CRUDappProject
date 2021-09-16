package com.seleniumexpress.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.seleniumexpress.api.Student;

@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;// pre anotacije je bilo new JdbcTemplate(getDataSource());

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(Student student) {

		String sql = "INSERT INTO STUDENT VALUES (?,?,?)";

		Object[] arg = { student.getRollNo(), student.getName(), student.getAddress() };

		int noOFRowInserted = jdbcTemplate.update(sql, arg);

		System.out.println(" No of row inserted is " + noOFRowInserted);

	}
	/*
	 * definisanje pre nego sto je napravljen beans.xml public DataSource
	 * getDataSource(){
	 * 
	 * String url ="jdbc:mysql://127.0.0.1:3306/school"; String username = "root";
	 * String password = "Kaja.123";
	 * 
	 * DataSource dataSource = new DriverManagerDataSource(url, username, password);
	 * 
	 * return dataSource; }
	 */

//brisanje na osnovu rednog broja
	@Override
	public boolean deleteRecordByRollNo(int rollNo) {

		String sql = "DELETE FROM STUDENT WHERE ROLL_NO = ?";

		int noOfRowDeleted = jdbcTemplate.update(sql, rollNo);

		System.out.println(" No of record deleted " + noOfRowDeleted);
		return noOfRowDeleted == 1;
	}

//brisanje na osnovu imena ili adrese
	@Override
	public int deleteRecordByStudentNameOrStudentAddress(String studentName, String studentAddress) {

		String sql = "DELETE FROM STUDENT WHERE STUDENT_NAME = ? OR STUDENT_ADDR = ?";

		Object[] arguments = { studentName, studentAddress };

		// jdbcTemplate.update(sql, studentName, studentAddress);
		int noOfRowsDeleted = jdbcTemplate.update(sql, arguments);

		System.out.println("No of rows got deleted are " + noOfRowsDeleted);
		return noOfRowsDeleted;
	}

	public void cleanUp() {

		String sql = "TRUNCATE TABLE STUDENT";
		jdbcTemplate.update(sql);

		System.out.println("Table cleaned up >>>>");

	}

	@Override
	public void insert(List<Student> students) {

		String sql = "INSERT INTO STUDENT VALUES (?,?,?)";

		ArrayList<Object[]> sqlArgs = new ArrayList<>();

		for (Student tempStudent : students) {

			Object[] studentData = { tempStudent.getRollNo(), tempStudent.getName(), tempStudent.getAddress() };
			sqlArgs.add(studentData);
		}

		jdbcTemplate.batchUpdate(sql, sqlArgs);

		System.out.println("Batch update completed!");

	}

}
