package com.isolutions4u.hibernate.hibernate;

import com.isolutions4u.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudent {

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

            // TODO : delete the student
            System.out.println("Deleting Student : " + newStudent);
            session.delete(newStudent);

            // TODO : delete student id = 2
            System.out.println("Deleting student id = 2");
            session.createQuery("delete from Student where id = 2")
                    .executeUpdate();


            // TODO : commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
