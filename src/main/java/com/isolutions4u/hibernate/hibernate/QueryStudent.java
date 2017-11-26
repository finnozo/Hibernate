package com.isolutions4u.hibernate.hibernate;

import com.isolutions4u.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudent {

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

            //todo start transaction
            session.beginTransaction();

            // TODO : query students
            List<Student> students = session.createQuery("from Student").list();

            // TODO : display students Java 8 Ways
            displayStudents(students);

            // TODO : Query students : lastName = 'dangi'

            students = session.createQuery("from Student s where s.lastName='dangi'").list();

            // TODO : display students Java 8 Ways
            System.out.println("\nLast Name = Dangi\n");
            displayStudents(students);


            // TODO : lastName = 'dangi' or first='sushil'

            students = session
                    .createQuery("from Student s " +
                            "where s.lastName = 'dangi' or s.firstName='sushil'")
                    .list();


            // TODO : display students Java 8 Ways
            System.out.println("\nLast Name = Dangi and fistName='Sushil'\n");
            displayStudents(students);


            // TODO : Query student where email LIKE %gmail.com

            students = session.createQuery("from Student s where" +
                    " s.email LIKE '%gmail.com'")
                    .list();

            // TODO : display students Java 8 Ways
            System.out.println("\nEmail like %gmail.com\n");
            displayStudents(students);

            //todo commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        students
                .stream()
                .forEach(System.out::println);
    }
}
