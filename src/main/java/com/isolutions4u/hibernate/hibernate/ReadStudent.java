package com.isolutions4u.hibernate.hibernate;

import com.isolutions4u.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudent {

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
            //todo use the session object to save Java object
            //todo create a student object
            System.out.println("Creating new student object.....");
            Student student = new
                    Student("Sushil", "Dangi", "dangi.sushil5@gmail.com");
            //todo start transaction

            session.beginTransaction();

            //todo save the student object
            System.out.println("Saving the student...");

            session.save(student);

            //todo commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
