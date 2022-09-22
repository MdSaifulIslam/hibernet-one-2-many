package com.springdemo.hibernet.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernet.demo.entity.Course;
import com.springdemo.hibernet.demo.entity.Instructor;
import com.springdemo.hibernet.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create Session
		Session session = factory.getCurrentSession();

		try {
			//start transaction
			session.beginTransaction();
			
			//get the instructor
			int theId = 1;
			Instructor tempInstructor  = session.get(Instructor.class, theId);
			
			//print and test
			System.out.println("Instructor : " + tempInstructor);
			System.out.println("Course : " + tempInstructor.getCourses());
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
