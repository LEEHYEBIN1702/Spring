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
		
		public void insert(DeptVO vo) {
		      try {
		         // 1. connect(연결)
		         conn = JdbcUtil.connect();
		         // 2. statement (구문)
		         String sql = "INSERT INTO departments "
		        		 + "(department_id, " 
		         		 + " department_name, " 
		        		 + " manager_id, " 
		         		 + " location_id) " 
		                 + " VALUES(?,?,?,?)";
		         PreparedStatement pstmt = conn.prepareStatement(sql);

		         // 3. execute(실행)
		         pstmt.setInt(1, vo.getDepartment_id());
		         pstmt.setString(2, vo.getDepartment_name());
		         pstmt.setInt(3, vo.getManager_id());
		         pstmt.setInt(4, vo.getLocation_id());

		         int r = pstmt.executeUpdate();
		         System.out.println(r + "건이 등록됨");

		         // 4. resultset(select라면 조회결과처리)
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         // 5. close(연결해제)
		         JdbcUtil.disconnect(conn);
		      }
		}
}
