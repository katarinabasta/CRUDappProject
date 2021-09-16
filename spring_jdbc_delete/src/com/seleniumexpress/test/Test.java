package com.seleniumexpress.test;

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

// prvo se pusti batch update koji upisuje tri reda u tabelu,
// zatim se naredne dve linije koda zakomentarisu i odradi se delete operacija
// na osnovu toga koji id se odabere
		StudentDAOHelper studentDAOHelper = context.getBean("studentDaoHelper", StudentDAOHelper.class);
		studentDAOHelper.setUpSTudentTable();

		
		
		StudentDAOImpl studentDaoImpl = context.getBean("studentDao", StudentDAOImpl.class);

/*      otkomentarise se ovaj deo da bi se omogucilo brisanje na osnovu id vrednosti
		// brisanje rekorda koji ima id=2 iz tabele
		boolean isDeleted = studentDaoImpl.deleteRecordByRollNo(2);
		if (isDeleted) {
			System.out.println("the roll no 2 is deleted...>>");
		}
*/
		//brise studente sa odredjenim imenom i adresom
		studentDaoImpl.deleteRecordByStudentNameOrStudentAddress("Mina", "Laz");
		//brisanje svih podataka u tabeli
		// studentDaoImpl.cleanUp();
		 
		 
	}

}
