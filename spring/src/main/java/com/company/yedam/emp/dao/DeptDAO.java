package com.company.yedam.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
@Component
public class DeptDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	
	//singletone
		private static DeptDAO instance;
		public static DeptDAO getInstance() {
			if (instance == null) {
				instance = new DeptDAO();
			}
			return instance;
		}
		
		public ArrayList<DeptVO> selectAll(){
			ArrayList<DeptVO> list = new ArrayList<DeptVO>();
			DeptVO vo = null;
			try {
				conn = JdbcUtil.connect();
				String sql = "select department_id,"
						+ " department_name,"
						+ " manager_id,"
						+ " location_id"
						+ " from departments"
						+ " order by department_id";
				pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					vo = new DeptVO();
					vo.setDepartment_id(rs.getInt("department_id"));
					vo.setDepartment_name(rs.getString("department_name"));
					vo.setManager_id(rs.getInt("manager_id"));
					vo.setLocation_id(rs.getInt("location_id"));
					list.add(vo);
				}
			}catch(Exception e) {
				System.out.println(e.getMessage()); //에러메세지 조회.
			}finally {
			JdbcUtil.disconnect(conn);	
			}
			return list;	
		}
}
