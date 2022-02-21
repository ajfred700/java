/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import static org.hibernate.criterion.Projections.count;
import org.hibernate.service.ServiceRegistry;



/**
 *
 * @author bufal
 */
public class CrudDao {
    static Session session;
    static SessionFactory sessionFactory;
    //Metodo para crear la conexion
    private static SessionFactory buildSessionFactory(){
        //creación de la configuracion e instancia hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        
        ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder();
        sessionFactory = config.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
        
        public static void createRecord() {
        int count = 0;
        Empleados  empleado = new Empleados();
        
        try {
            
            session = buildSessionFactory().openSession();
            session.beginTransaction();
 
            // creación de empleado
            for(int j = 101; j <= 105; j++) {
                count = count + 1;
                empleado = new Empleados();
                empleado.setClave(j);
                empleado.setNombre("Siboney");
                empleado.setDireccion("Calle 3");
                empleado.setTelefono("2284042120");
              
            }
 
            session.getTransaction().commit();
            System.out.println("\nEmpleado creado " + count + "Guardado en la base de datos");
          
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                System.out.println("error datos no guardados "); 
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
 
    @SuppressWarnings("unchecked")
    public static List displayRecords() {
        List studentsList = new ArrayList();        
        try {
            // Getting Session Object From SessionFactory
            session = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            session.beginTransaction();
 
            studentsList = session.createQuery("FROM Empleados").list();
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return studentsList;
    }
 
    // Method 3: This Method Is Used To Update A Record In The Database Table   
    public static void updateRecord(long clave) {       
        try {
            // Getting Session Object From SessionFactory
            session = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            session.beginTransaction();
 
            // Creating Transaction Entity
            Empleados empleado = (Empleados) session.get(Empleados.class, clave);
            empleado.setNombre("Juan");
            
 
            
            session.getTransaction().commit();
            System.out.println("\nEmpleado con clave: " + clave + "se ha adctualizado\n");
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
 
    // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
    public static void deleteRecord(Integer clave) {
        try {
            // Getting Session Object From SessionFactory
            session = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            session.beginTransaction();
 
            Empleados empleado = findRecordById(clave);
            session.delete(empleado);
 
            // Committing The Transactions To The Database
            session.getTransaction().commit();
            System.out.println("\nEmpleado con clave: " + clave + "se ha elimidado\n");
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
 
    // Method 4(b): This Method To Find Particular Record In The Database Table
    public static Empleados findRecordById(Integer clave) {
        Empleados findEmpleado = null;
        try {
            // Getting Session Object From SessionFactory
            session = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            session.beginTransaction();
 
            findEmpleado = (Empleados) session.load(Empleados.class, clave);
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findEmpleado;
    }
 
    // Method 5: This Method Is Used To Delete All Records From The Database Table
//    public static void deleteAllRecords() {
//        try {
//            // Getting Session Object From SessionFactory
//            sessionObj = buildSessionFactory().openSession();
//            // Getting Transaction Object From Session Object
//            sessionObj.beginTransaction();
// 
//            Query queryObj = sessionObj.createQuery("DELETE FROM Student");
//            queryObj.executeUpdate();
// 
//            // Committing The Transactions To The Database
//            sessionObj.getTransaction().commit();
//            logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
//        } catch(Exception sqlException) {
//            if(null != sessionObj.getTransaction()) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
//                sessionObj.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } finally {
//            if(sessionObj != null) {
//                sessionObj.close();
//            }
//        }
//    }
}
        
    