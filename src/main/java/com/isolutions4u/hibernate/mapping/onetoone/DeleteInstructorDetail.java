package com.isolutions4u.hibernate.mapping.onetoone;

import com.isolutions4u.hibernate.entity.Instructor;
import com.isolutions4u.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetail {

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

            int id = 3;

            InstructorDetail instructorDetail = session
                    .get(InstructorDetail.class, id);

            // TODO : print the instructor detail

            System.out.println("instructorDetail : " + instructorDetail);

            // TODO : print the associate instructor

            System.out.println("The Associate instructor : " + instructorDetail.getInstructor());

            // TODO : remove the associated object reference
            // TODO : break bi-directional link
            instructorDetail.getInstructor().setInstructorDetail(null);

            // TODO : now let's delete the instructor detail

            System.out.println("Deleting instructorDetail : " + instructorDetail);
            session.delete(instructorDetail);

            // TODO : commit transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // TODO : handle connection leak issue
            if (session.isOpen()) {
                session.close();
            }
            if (!factory.isClosed()) {
                factory.close();
            }
        }
    }
}
