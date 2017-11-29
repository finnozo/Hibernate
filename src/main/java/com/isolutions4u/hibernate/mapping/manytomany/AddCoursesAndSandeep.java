package com.isolutions4u.hibernate.mapping.manytomany;

import com.isolutions4u.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesAndSandeep {

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

            //Todo : get the student sandeep from database
            int studentId = 7;
            Student student = session.get(Student.class, studentId);
            System.out.println("\n\nLoaded Student : " + student + "\n\n");
            System.out.println("\n\nCourses : " + student.getCourses() + "\n\n");

            //Todo : create more courses

            Course course1 = new Course("Rubik's Cube - How to Speed Cube");
            Course course2 = new Course("Atari 2600 - Game Development");

            //Todo : add student to courses
            course1.addStudent(student);
            course2.addStudent(student);

            //Todo : save the courses
            System.out.println("\n\nSaving Courses : \n\n");
            session.save(course1);
            session.save(course2);

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
