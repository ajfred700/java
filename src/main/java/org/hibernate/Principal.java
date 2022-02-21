/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate;

import java.util.List;

/**
 *
 * @author bufal
 */
public class Principal {
    public static void main(String[] args) {
        CrudDao.createRecord();
 
       
        List viewStudents = CrudDao.displayRecords();
        if(viewStudents != null & viewStudents.size() > 0) {
            for(Object empleado : viewStudents) {
                System.out.println(empleado.toString());
               
            }
        }
 
      
//        int updateId = 1;
//        CrudDao.updateRecord(updateId);
//        List updateStudent = CrudDao.displayRecords();
//        if(updateStudent != null & updateStudent.size() > 0) {
//            for(Object empleado : updateStudent) {
//                System.out.println(empleado.toString());
//                
//            }
//        }
//        int deleteId = 5;
//        CrudDao.deleteRecord(deleteId);
//        //logger.info("\n=======READ RECORDS AFTER DELETION=======\n");
//        List deleteStudentRecord = CrudDao.displayRecords();
//        for(Object empleado : deleteStudentRecord) {
//            System.out.println(empleado.toString());
//        }
 
//        logger.info("\n=======DELETE ALL RECORDS=======\n");
//        DbOperations.deleteAllRecords();
//        logger.info("\n=======READ RECORDS AFTER ALL RECORDS DELETION=======");
//        List deleteAll = DbOperations.displayRecords();
//        if(deleteAll.size() == 0) {
//            logger.info("\nNo Records Are Present In The Database Table!\n");
//        }       
        System.exit(0);
    } 
    }
   

