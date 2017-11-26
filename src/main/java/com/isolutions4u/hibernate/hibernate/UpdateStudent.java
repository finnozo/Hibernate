package com.isolutions4u.hibernate.hibernate;

import com.isolutions4u.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudent {

    public static void main(String[] args) {
        // todo create session factory

        SessionFactory factory = new
                Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();


        //todo create session

        Session session = factory.getCurrentSession();


        try {

            int studentId = 1;

            // TODO : now get new session and start transaction

            session = factory.getCurrentSession();
            session.beginTransaction();

            // TODO : retrieve student based on the id : primary key

            System.out.println("\nGetting student with id : " + studentId);
            Student newStudent = session.get(Student.class, studentId);

            System.out.println("Updating student....");
            newStudent.setEmail("sushil@pirsq.com");

            // TODO : commit the transaction
            session.getTransaction().commit();


            // TODO : new Code

            session = factory.getCurrentSession();
            session.beginTransaction();

            // TODO : updating email for all students

            System.out.println("Updating email for all students");

            session.createQuery("update Student set email = 'foo@gmail.com'")
                    .executeUpdate();

            session.getTransaction().commit();


            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
