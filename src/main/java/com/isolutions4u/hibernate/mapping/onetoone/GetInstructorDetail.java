package com.isolutions4u.hibernate.mapping.onetoone;

import com.isolutions4u.hibernate.entity.Instructor;
import com.isolutions4u.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetail {

    public static void main(String[] args) {
        // todo create session factory

        SessionFactory factory = new
                Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();


        //todo create session

        Session session = factory.getCurrentSession();


        try {
            // TODO : start transaction
            session.beginTransaction();

            // TODO : get the Instructor Detail object

            int id = 2;

            InstructorDetail instructorDetail = session
                    .get(InstructorDetail.class, id);

            // TODO : print the instructor detail

            System.out.println("instructorDetail : " + instructorDetail);

            // TODO : print the associate instructor

            System.out.println("The Associate instructor : " + instructorDetail.getInstructor());

            // TODO : commit transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
