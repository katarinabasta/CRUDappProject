package com.seleniumexpress.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

//import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.seleniumexpress.api.Student;
import com.seleniumexpress.rowmapper.StudentRowMapper;

@Repository("studentDao")
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;// korisceno za part1 = new JdbcTemplate(getDataSource());

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
	 * part one public DataSource getDataSource(){
	 * 
	 * String url ="jdbc:mysql://127.0.0.1:3306/school"; String username = "root";
	 * String password = "Kaja.123";
	 * 
	 * DataSource dataSource = new DriverManagerDataSource(url, username, password);
	 * 
	 * return dataSource; }
	 */

//dodato za delete funkciju, part 2
	@Override
	public boolean deleteRecordByRollNo(int roolNo) {

		String sql = "DELETE FROM STUDENT WHERE ROLL_NO = ?";

		int noOfRowDeleted = jdbcTemplate.update(sql, roolNo);

		System.out.println(" No of record deleted " + noOfRowDeleted);
		return noOfRowDeleted == 1;
	}

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

// koriscen custom row mapper
	@Override
	public List<Student> findAllStudents() {
		String selectSql = "SELECT * FROM STUDENT";

		List<Student> studentList = jdbcTemplate.query(selectSql, new StudentRowMapper());

		return studentList;
	}

// koriscen Spring row mapper tj BeanPropertyRowMapper klasa
	@Override
	public Student findStudentsByRollNo(int rollNo) {
		String selectSql = "SELECT ROLL_NO as rollNo, " + "STUDENT_NAME as name, " + "STUDENT_ADDR as address "
				+ "FROM STUDENT WHERE ROLL_NO = ?";

		Student student = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<Student>(Student.class),
				rollNo);

		return student;
		
	}

	@Override
	public int updateStudent(Student student) {
		
		String sql = "UPDATE school.student set STUDENT_ADDR = ? WHERE ROLL_NO = ?";
		Object[] args = { student.getAddress(), student.getRollNo()};
		
		System.out.println("Row updated...");
		return jdbcTemplate.update(sql,args);
		
	}

	@Override
	public int updateStudent(List<Student> studentList) {
		String sql = "UPDATE school.student set STUDENT_ADDR = ? WHERE ROLL_NO = ?";
		
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			// argumenti za "UPDATE school.student set STUDENT_ADDR = ? WHERE ROLL_NO = ?"
			public void setValues(PreparedStatement arg0, int arg1) throws SQLException {
	
			arg0.setString(1, studentList.get(arg1).getAddress());  //laz
			arg0.setInt(2, studentList.get(arg1).getRollNo());  // 3
			System.out.println("Inside setValues method");	
			}
			
			@Override
			//definise se koliko puta ce se setValues() metoda izvrsiti
			public int getBatchSize() {
				
				System.out.println("Inside getBatchsize method >> " + "set value method will run for " + studentList.size() + " times");
				
				return studentList.size();
			}
		} );
		return 0;
	}

}
