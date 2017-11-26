package com.isolutions4u.hibernate.mapping.onetoone;

import com.isolutions4u.hibernate.entity.Instructor;
import com.isolutions4u.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Create {

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
            // TODO : Create the object

            Instructor instructor =
                    new Instructor(
                            "Sushil",
                            "Dangi",
                            "sushil@pirq.com");


            InstructorDetail instructorDetail =
                    new InstructorDetail(
                            "https://www.youtube.com/sushildangi",
                            "Love Coding");

            // TODO : Associate the object
            instructor.setInstructionDetail(instructorDetail);

            //todo start transaction

            session.beginTransaction();

            // TODO : save the instructor (This also save instructorDetail)
            System.out.println("Save Instructor : " + instructor);
            session.save(instructor);

            //todo commit transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
