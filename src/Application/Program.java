package Application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] agrs) {
		
		SellerDao seerDao = DaoFactory.createSellerDao();
		
		Seller seller = seerDao.findById(2);
		
		System.out.println(seller);
		
	}
}
