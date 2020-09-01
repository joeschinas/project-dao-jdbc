package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		ResultSet rs = null;
		/*try {
			st = conn.prepareStatement()
		}*/
		
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
			Department dep = instanciaDepartment(rs);
			Seller seller = instanciaSeller(rs,dep);
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

	private Seller instanciaSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("baseSalary"));
		seller.setDepartment(dep);
		return seller;
	}
	private Department instanciaDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("depName"));
		return dep;
	}
	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs =null;
		
		try {
				st = conn.prepareStatement(
				"\r\n" + 
				"select seller.*, Department.Name as depName\r\n" + 
				"from seller inner join department\r\n" + 
				"on seller.departmentId = department.Id\r\n" + 
				"order by name"
				);
				
		
		rs=st.executeQuery();
		List<Seller> objList = new ArrayList<>();
		Map<Integer, Department> map = new HashMap<>();
		
			while(rs.next()) {
			Department dep = map.get(rs.getInt("DepartmentId"));
			if(dep == null) {
			dep = instanciaDepartment(rs);
			map.put(rs.getInt("DepartmentId"),dep);
			}
			
			Seller seller = instanciaSeller(rs,dep);
			 objList.add(seller );
			
			}
			return objList;
				
		
		}catch(SQLException erro) {
			
			throw new DbException ("Erro"+erro.getMessage());
			
		}finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
			
			
		}
	}
	
	
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs =null;
		
		try {
				st = conn.prepareStatement(
				"\r\n" + 
				"select seller.*, Department.Name as depName\r\n" + 
				"from seller inner join department\r\n" + 
				"on seller.departmentId = department.Id\r\n" + 
				"where departmentId= ?\r\n" + 
				"order by name"
				);
				
		st.setInt(1, department.getId());
		
		rs=st.executeQuery();
		
		List<Seller> objList = new ArrayList<>();
		Map<Integer, Department> map = new HashMap<>();
		
			while(rs.next()) {
			Department dep = map.get(rs.getInt("DepartmentId"));
			if(dep == null) {
			dep = instanciaDepartment(rs);
			map.put(rs.getInt("DepartmentId"),dep);
			}
			
			Seller obj = instanciaSeller(rs,dep);
			 objList.add(obj );
			
			}
			return objList;
		
		}catch(SQLException erro) {
			
			throw new DbException ("Erro"+erro.getMessage());
			
		}finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
			
			
		}
	}

}
