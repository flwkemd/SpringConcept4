package com.ex.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ex.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// use the session object to save Java object
		try {
			
			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='Back'
			theStudents = session.createQuery("from Student s where s.lastName='Back'").getResultList();
			
			System.out.println("\nStudents who have last name of Back");
			displayStudents(theStudents);
			
			// query students: lastName='Duck' OR firstName='Park'
			theStudents = session.createQuery("from Student s where "
					+ "s.lastName='Duck' OR s.firstName='Park'").getResultList();
			
			System.out.println("\nStudents who have last name of Duck OR first name Park");
			displayStudents(theStudents);
			
			// query students where email LIKE '%naver.com'
			theStudents = session.createQuery("from Student s where "
					+ "s.email Like '%naver.com'").getResultList();
			
			System.out.println("\nStudents whose email ends with naver.com");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
