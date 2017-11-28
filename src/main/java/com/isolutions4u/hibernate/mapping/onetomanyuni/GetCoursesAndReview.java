package com.isolutions4u.hibernate.mapping.onetomanyuni;

import com.isolutions4u.hibernate.entity.Course;
import com.isolutions4u.hibernate.entity.Instructor;
import com.isolutions4u.hibernate.entity.InstructorDetail;
import com.isolutions4u.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesAndReview {

    public static void main(String[] args) {
        // todo create session factory

        SessionFactory factory = new
                Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();


        //todo create session

        Session session = factory.getCurrentSession();


        try {
            //todo start transaction
            session.beginTransaction();

            //Todo : get the course
            int id = 2;
            Course course = session.get(Course.class, id);

            //Todo : print the course
            System.out.println("The Course : " + course);

            //Todo : print course reviews
            System.out.println("The Reviews are : " + course.getReviews());

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
