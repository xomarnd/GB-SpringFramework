package com.geekbrains.hibernate;


import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static EntityManagerFactory emFactory;
    public static EntityManager em;

    public static void main(String[] args) {
        emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        em = emFactory.createEntityManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (true){
            try {
                System.out.println("Please, enter a command");
                input = reader.readLine();
                switch (input.split(" ",2)[0].toLowerCase()){
                    case "buyer":
                        showClient(Integer.parseInt(input.split(" ",2)[1]));
                        break;
                    case "product":
                        showProduct(Integer.parseInt(input.split(" ",2)[1]));
                        break;
                    case "delete":
                        if (input.split(" ",3)[1].equals("buyer")){
                            em.getTransaction().begin();
                            em.remove(em.find(Buyer.class,Integer.parseInt(input.split(" ",3)[2])));
                            em.getTransaction().commit();
                        } else if (input.split(" ",3)[1].equals("product")){
                            em.getTransaction().begin();
                            em.remove(em.find(Product.class,Integer.parseInt(input.split(" ",3)[2])));
                            em.getTransaction().commit();
                        } else {
                            System.out.println("There is error in command/");
                        }
                        break;
                    case "cdb":
                        input = reader.readLine().toLowerCase();
                        System.out.println("Please respond with 'yes' or 'no'");
                        if (input.equals("yes")) {
                            createData();
                        }
                    case "exit":
                        System.out.println("Goodbye!");
                        return;
                    case "help":
                        System.out.println(
                                "CDB - create DataBase" + "\n" +
                                "exit" + "\n" +
                                "buyer <id> - show what bought buyer with id" + "\n" +
                                "product <id> - show who bought product with id" + "\n" +
                                "delete <buyer|product id> - delete buyer|produc with id");

                    default: System.out.println("Unknown command. Enter \"help\" to see list of commands.");


                }
//                } else {
//                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void showClient(int id){
        Buyer buyer = em.find(Buyer.class,id);
        System.out.println(buyer);
        for (Product p: buyer.getProducts()) {
            System.out.println(p);
        }
    }
    public static void showProduct(int id){
        Product product = em.find(Product.class,id);
        System.out.println(product);
        for (Buyer b: product.getBuyers()) {
            System.out.println(b);
        }

    }

    public static void createData(){
        em.getTransaction().begin();
        em.persist(new Buyer("User1", "Name"));
        em.persist(new Buyer("User2", "Name"));
        em.persist(new Buyer("User3", "Name"));
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.persist(new Product("product1",100));
        em.persist(new Product("product2",200));
        em.persist(new Product("product3",300));
        em.persist(new Product("product4",400));
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.persist(new PurchasesList(1,1));
        em.persist(new PurchasesList(1,3));
        em.persist(new PurchasesList(1,5));
        em.persist(new PurchasesList(2,2));
        em.persist(new PurchasesList(4,1));
        em.persist(new PurchasesList(4,4));
        em.getTransaction().commit();
    }

}
