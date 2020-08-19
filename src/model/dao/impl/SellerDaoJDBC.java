package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs =null;
		try {
		st = conn.prepareStatement(
				"select seller.*, Department.Name as depName\r\n" + 
				"from seller inner join Department\r\n" + 
				"on seller.departmentId = Department.Id\r\n" + 
				"where seller.Id =?"
				);
		st.setInt(1, id);
		rs=st.executeQuery();
			if(rs.next()) {
			Department dep = new Department();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setName(rs.getString("depName"));
			Seller seller = new Seller();
			seller.setId(rs.getInt("Id"));
			seller.setName(rs.getString("Name"));
			seller.setEmail(rs.getString("Email"));
			seller.setBirthDate(rs.getDate("BirthDate"));
			seller.setBaseSalary(rs.getDouble("baseSalary"));
			seller.setDepartment(dep);
			return seller;
			
			}
			return null;
				
		
		}catch(SQLException erro) {
			
			throw new DbException ("Erro"+erro.getMessage());
			
		}finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
			
			
		}
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
