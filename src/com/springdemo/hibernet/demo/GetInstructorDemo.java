package com.springdemo.hibernet.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernet.demo.entity.Course;
import com.springdemo.hibernet.demo.entity.Instructor;
import com.springdemo.hibernet.demo.entity.InstructorDetail;

public class GetInstructorDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		// create Session
		Session session = factory.getCurrentSession();

		try {
			// start transaction
			session.beginTransaction();

			// get the instructor details
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			// print the instructor detail
			System.out.println("Instructor Details: " + tempInstructor);
			System.out.println("Associate Instructor : " + tempInstructor.getCourses());
			session.getTransaction().commit();
			session.close();
			// print associate instructor object
			if (tempInstructor != null)
				System.out.println("Associate Instructor : " + tempInstructor.getCourses());

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//session.close();
			factory.close();
		}

	}

}
