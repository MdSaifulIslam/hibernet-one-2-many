package com.springdemo.hibernet.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernet.demo.entity.Instructor;
import com.springdemo.hibernet.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		try {
			//create Session
			Session session = factory.getCurrentSession();

			//create objects
			Instructor TempInstructor = 
					new Instructor("Saddam", "Mrida", "saddam@domain.edu");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"youtube.com/saddam9922", 
							"Gardening");
			
			//Associate objects
			TempInstructor.setInstructorDetailId(tempInstructorDetail);
			
			//start transaction
			session.beginTransaction();
			
			//
			//save the instructor data
			//
			//it will save instructor details also
			//as it is cascading.....
			//
			
			session.save(TempInstructor);
			
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
