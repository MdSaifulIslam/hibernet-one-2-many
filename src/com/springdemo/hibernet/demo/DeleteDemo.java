package com.springdemo.hibernet.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernet.demo.entity.Instructor;
import com.springdemo.hibernet.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		try {
			// create Session
			Session session = factory.getCurrentSession();

			// start transaction
			session.beginTransaction();

			// get the Instructor object using id/ key
			int tempId = 1;
			Instructor instructor = session.get(Instructor.class, tempId);

			//
			// delete the instructor data
			//
			// it will delete instructor details also
			// as it is cascading.....
			//
			if (instructor != null)
				session.delete(instructor);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
