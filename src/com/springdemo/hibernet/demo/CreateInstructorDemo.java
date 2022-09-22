package com.springdemo.hibernet.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernet.demo.entity.Course;
import com.springdemo.hibernet.demo.entity.Instructor;
import com.springdemo.hibernet.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create Session
		Session session = factory.getCurrentSession();

		try {
			// start transaction
			session.beginTransaction();

			// create objects
			Instructor TempInstructor = new Instructor("Saddam", "Mrida", "saddam@domain.edu");

			InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/saddam9922", "Gardening");

			// Associate objects instructor details
			TempInstructor.setInstructorDetailId(tempInstructorDetail);

			// associate objects courses;
			List<Course> courses = 
					new ArrayList<Course>();
			
			Course tempCourse1 = new Course("course1");
			courses.add(tempCourse1);

			tempCourse1 = new Course("course2");
			courses.add(tempCourse1);

			tempCourse1 = new Course("course3");
			courses.add(tempCourse1);

			// set courses
			TempInstructor.setCourses(courses);
			
			//save the instructor
			session.save(TempInstructor);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
