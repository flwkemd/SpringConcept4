package com.ex.hibernate.oneToone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ex.hibernate.oneToone.entity.Instructor;
import com.ex.hibernate.oneToone.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			//create the objects
			//Instructor tempInstructor = new Instructor("Kim", "Ho", "flwkemd@naver.com");
			//InstructorDetail tempInstructorDetail = new InstructorDetail("www.abc.com", "coding");

			Instructor tempInstructor = new Instructor("Lee", "Jun", "aldhfl@naver.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("www.cal.com", "Good");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			// Note : this will also save the details object
			// because of CascadeType.ALL
			System.out.println("Saving instructor: "+tempInstructor);
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {
			factory.close();
		}
		
	}

}
