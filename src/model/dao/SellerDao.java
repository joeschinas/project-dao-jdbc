package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {

	void insert(Seller obj);
	void update(Seller obj);
	void DeleteById(Integer id);
	Department findById(Integer id);
	List<Seller> findAll();
}
