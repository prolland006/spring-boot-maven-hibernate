package org.exemple.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class BookManager {
    protected SessionFactory sessionFactory;

    protected void setup() {
        // code to load Hibernate Session factory
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            ex.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected void exit() {
        // code to close Hibernate Session factory
        this.sessionFactory.close();
    }

    protected void create() {
        // code to save a book
        Book b1 = new Book();
        b1.setTitle("Effective Java");
        b1.setAuthor("Joshua Bloch");
        b1.setPrice(32.59f);

        Book b2 = new Book();
        b2.setTitle("Effective c++");
        b2.setAuthor("Raymond devos");
        b2.setPrice(30.00f);

        Book b3 = new Book();
        b3.setTitle("Effective delphi");
        b3.setAuthor("Mickael Jackson");
        b3.setPrice(35.00f);

        Shelf s1=new Shelf();
        s1.setLabel("etagère 1");

        Shelf s2=new Shelf();
        s2.setLabel("etagère 2");

        b1.setShelf(s1);
        b2.setShelf(s1);
        b3.setShelf(s2);

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        session.save(s1);
        session.save(s2);
        session.save(b1);
        session.save(b2);
        session.save(b3);

        session.getTransaction().commit();
        session.close();
    }

    protected void read() {
        // code to get a book
        Session session = this.sessionFactory.openSession();

        Shelf shelf = session.get(Shelf.class, (long)1);

        System.out.println("READ Shelf: " + shelf.getLabel());

        session.close();
    }

    protected void readAll() {
        // code to get a book
        Session session = this.sessionFactory.openSession();

        List<Book> bookList = session.createQuery("from Book", Book.class).getResultList();

        for (Book book: bookList) {
            System.out.println("------------------------------------------");
            System.out.println("Id: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Price: " + book.getPrice());
            if (book.getShelf() != null) {
                System.out.println("Shelf: " + book.getShelf().getLabel());
            }
        }

        session.close();
    }

    protected void update() {
        // code to modify a book
        Book book = new Book();
        book.setId(20);
        book.setTitle("Ultimate Java Programming");
        book.setAuthor("Nam Ha Minh");
        book.setPrice(19.99f);

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        session.update(book);

        session.getTransaction().commit();
        session.close();
    }

    protected void deleteAll() {
        // code to get a book
        Session session = this.sessionFactory.openSession();

        List<Book> bookList = session.createQuery("from Book", Book.class).getResultList();

        session.beginTransaction();
        for (Book book: bookList) {
            session.delete(book);
        }

        session.getTransaction().commit();
        session.close();
    }

}