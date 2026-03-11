package com.klef.fsad.exam;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Booking.class);

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // Insert record
        Booking b = new Booking();
        b.setId(1);
        b.setName("Narasimha");
        b.setDate("2026-03-11");
        b.setStatus("Confirmed");

        session.save(b);

        tx.commit();

        // Fetch all records using HQL
        Session session2 = sf.openSession();

        Query<Booking> q = session2.createQuery("from Booking", Booking.class);

        List<Booking> list = q.list();

        for(Booking bk : list)
        {
            System.out.println(
                    bk.getId()+" "
                    +bk.getName()+" "
                    +bk.getDate()+" "
                    +bk.getStatus());
        }

        session.close();
        session2.close();
        sf.close();
    }
}