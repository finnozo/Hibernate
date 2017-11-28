package com.isolutions4u.hibernate.mapping.onetomanyuni;

import com.isolutions4u.hibernate.entity.Course;
import com.isolutions4u.hibernate.entity.Instructor;
import com.isolutions4u.hibernate.entity.InstructorDetail;
import com.isolutions4u.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReview {

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

            //Todo : create a course
            Course course = new Course("Pacman - How to Score One Million Point");

            //Todo : add some reviews
            course.addReviews(new Review("Great Course ... Loved it!!"));
            course.addReviews(new Review("Cool course, Job well done!"));
            course.addReviews(new Review("What a dumb Course, You are an idiot!"));
            course.addReviews(new Review("Wow ... Loved it!!"));

            //Todo : save the course ... and leverage the cascade all :-)

            System.out.println("Saving the Course \n");
            System.out.println("Course is : " + course);
            System.out.println("Reviews are : " + course.getReviews());

            session.save(course);

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
