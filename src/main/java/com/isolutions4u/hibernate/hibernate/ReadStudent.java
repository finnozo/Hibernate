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
                    Student("Ram", "Kumar", "ram@gmail.com");
            //todo start transaction

            session.beginTransaction();

            //todo save the student object
            System.out.println("Saving the student..." + student);

            session.save(student);

            //todo commit transaction
            session.getTransaction().commit();


            // todo : find out the student id : primary key

            System.out.println("Saved Student. Generated Id : " + student.getId());

            // TODO : now get new session and start transaction

            session = factory.getCurrentSession();
            session.beginTransaction();

            // TODO : retrieve student based on the id : primary key

            System.out.println("\nGetting student with id : " + student.getId());
            Student newStudent = session.get(Student.class, student.getId());

            System.out.println("Get Complete :" + newStudent);

            // TODO : commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
