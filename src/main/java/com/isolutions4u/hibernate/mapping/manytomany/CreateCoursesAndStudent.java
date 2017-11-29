package com.isolutions4u.hibernate.mapping.manytomany;

import com.isolutions4u.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudent {

    public static void main(String[] args) {
        // todo create session factory

        SessionFactory factory = new
                Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();


        //todo create session

        Session session = factory.getCurrentSession();


        try {
            //todo start transaction
            session.beginTransaction();

            //Todo : create a course
            Course course = new Course("Pacman - How to Score One Million Point");


            //Todo : save course
            session.save(course);

            //Todo : create Students
            Student student1 = new Student(
                    "Sushil",
                    "Dangi",
                    "sushil@pirsq.com");
            Student student2 = new Student(
                    "Sandeep",
                    "Kumar",
                    "sandeep@gmail.com");

            //Todo : add students to the course

            course.addStudent(student1);
            course.addStudent(student2);

            //Todo : save students

            System.out.println("\nSaving Student ....");
            session.save(student1);
            session.save(student2);

            System.out.println("Saved Student : " + course.getStudents());

            //todo commit transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        } finally {
            factory.close();
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}
