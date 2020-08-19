package model.dao;

import db.Db;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public interface DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(Db.getConnection());
	}
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC();
	}
}
