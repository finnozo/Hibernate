package com.isolutions4u.hibernate.mapping.manytomany;

import com.isolutions4u.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeletePacmanCourse {

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

            //Todo : get the pacman course from db
            int courseId = 2;
            Course course = session.get(Course.class, courseId);

            //Todo : delete the course
            System.out.println("Delete Course : " + course);
            session.delete(course);

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
