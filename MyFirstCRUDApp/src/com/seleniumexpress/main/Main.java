package com.seleniumexpress.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seleniumexpress.api.Student;
//import com.seleniumexpress.dao.StudentDAO;
import com.seleniumexpress.dao.StudentDAOImpl;
import com.seleniumexpress.service.StudentDAOHelper;

public class Main {

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
		System.out.println("Fetching the student with the id 2");
		Student student = studentDao.findStudentsByRollNo(2);
		System.out.println(student);

		// deo koji se odnosi na brisanje cele tabele

		//studentDao.cleanUp();
		
		StudentDAOImpl studentDaoImpl = context.getBean("studentDao", StudentDAOImpl.class);

		/*      otkomentarise se ovaj deo da bi se omogucilo brisanje na osnovu id vrednosti
				// brisanje rekorda koji ima id=2 iz tabele
				boolean isDeleted = studentDaoImpl.deleteRecordByRollNo(2);
				if (isDeleted) {
					System.out.println("the roll no 2 is deleted...>>");
				}
		*/
				//brise studente sa odredjenim imenom i adresom
				studentDaoImpl.deleteRecordByStudentNameOrStudentAddress("Kristina", "KG");
				//brisanje svih podataka u tabeli
				// studentDaoImpl.cleanUp();
				
			
				Student mina  = new Student();
				mina.setAddress("BGD");
				mina.setID(3);
				
				Student milan  = new Student();
				milan.setAddress("BGD");
				milan.setID(2);
				
				//za apdejt jednog podatka
			//studentDao.updateStudent(mina);
				
				//apdejtuje adresu za dva studenta iz tabele i menja je u BGD
				List <Student> studentListupdate = new ArrayList<Student>();
				studentListupdate.add(mina);
				studentListupdate.add(milan);
				
				//batch apdejt
				studentDao.updateStudent(studentListupdate);
	}

}
