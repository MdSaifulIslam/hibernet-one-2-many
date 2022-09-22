package com.springdemo.hibernet.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.springdemo.hibernet.demo.entity.Course;
import com.springdemo.hibernet.demo.entity.Instructor;
import com.springdemo.hibernet.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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

			//set fetch Query 
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
							+ "join fetch i.courses "
							+ "where i.id =:theInstructorId", Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();

			// print the instructor detail
			System.out.println("Instructor Details: " + tempInstructor);
			
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
