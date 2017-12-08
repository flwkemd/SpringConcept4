package com.ex.hibernate.manyTomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ex.hibernate.manyTomany.entity.Student;
import com.ex.hibernate.manyTomany.entity.Course;
import com.ex.hibernate.manyTomany.entity.Instructor;
import com.ex.hibernate.manyTomany.entity.InstructorDetail;
import com.ex.hibernate.manyTomany.entity.Review;


public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// start a transaction
			session.beginTransaction();

			// create a course
			Course tempCourse = new Course("Pacman");
			
			// save the course
			System.out.println("\nSaving the course");
			session.save(tempCourse);
			System.out.println("Saved the course: "+tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("John", "doe", "flwkemd@naver.com");
			Student tempStudent2 = new Student("Kim", "soo", "aldhfl@naver.com");
			
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\nSaving students");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: "+tempCourse.getStudents());

			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}





