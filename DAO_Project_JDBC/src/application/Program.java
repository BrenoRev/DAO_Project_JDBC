package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        // Testing the classes 

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TEST 1: seller findById =====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();
        System.out.println("=== TEST 2: seller findByDepartment =====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);
        System.out.println("=== TEST 3: seller findAll =====");
        list = sellerDao.findAll();
        list.forEach(System.out::println);
        System.out.println("=== TEST 4: seller insert =====");
        Seller newSeller = new Seller(null, "Greg" , "greg@gmail.com" , new Date() , 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());
        System.out.println("=== TEST 5: seller update =====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update completed");
        System.out.println("\n=== TEST 6: seller delete =====");
        System.out.print("Enter id for delete: ");
        int id = teclado.nextInt(); 
        sellerDao.deleteById(id);
        System.out.println("Delete completed");
        teclado.close();
        

            // ======= Tests for Departments =========
            
        DepartmentDaoJDBC depart = DaoFactory.createDepartmentDao();
        
        System.out.println("=== Find by ID === ");
        Department depar = depart.findById(2);
        System.out.println(depar);
        
        List<Department> demp = depart.findAll();
        System.out.println("=== Find All ===");
        demp.forEach((x) -> {
            System.out.println(x);
        });
        
        System.out.println("--- Insert Into ---");
        Department n = new Department();
        n.setName("Logistica");
        depart.insert(n);
        System.out.println("Inserted completed!");
        System.out.println();
        
        System.out.println("-=- Delete -=-");
        depart.deleteById(3);
        System.out.println("Delete completed");
        System.out.println();
        
        System.out.println("-== Update ==-");
        Department y = new Department();
        // Set name
        y.setName("Viagens");
        // in this ID
        y.setId(5);
        depart.update(y);
        System.out.println("Update completed!");

    }

}
