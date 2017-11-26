package com.isolutions4u.hibernate.mapping.onetoone;

import com.isolutions4u.hibernate.entity.Instructor;
import com.isolutions4u.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Delete {

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

            // TODO : get instructor by primary key / Id
            int id = 1;
            Instructor instructor =
                    session
                            .get(Instructor.class, id);
            System.out.println("Found Instructor :" + instructor);
            // TODO : delete the instructor
            if (instructor != null) {
                System.out.println("Deleting :" + instructor);
                //TODO : Note :- Will ALSO delete associated "Details" object
                //TODO : because of CascadeType.ALL

                session.delete(instructor);
            }

            // TODO : commit transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
