package Application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] agrs) {
		
		SellerDao seerDao = DaoFactory.createSellerDao();
		
		Seller seller = seerDao.findById(2);
		
		System.out.println("TESTE"+seller);
		
		System.out.println("\n TESTE 2");
		
		Department dep = new Department(1, null);
		
		 List<Seller> list = seerDao.findByDepartment(dep);
		 
		 for (Seller obj : list) {
			 System.out.println(obj);
			 
		 }
		 System.out.println("=====================================");
		 list = seerDao.findAll();
		 for(Seller obj: list) {
			 
			 System.out.println(obj);
			 
		 }
		
		
		
		
	}
}
