package com.springdemo.hibernet.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernet.demo.entity.*;

public class DeleteCoursesDemo {

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
			
			//get the course
			int theId = 10;
			Course tempCourse =
					session.get(Course.class, theId);
			
			System.out.println(tempCourse);
			
			//delete the course
			session.delete(tempCourse);
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
