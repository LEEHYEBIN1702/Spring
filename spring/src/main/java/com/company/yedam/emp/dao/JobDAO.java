package com.company.yedam.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
@Component
public class JobDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	//singletone
		private static JobDAO instance;
		public static JobDAO getInstance() {
			if (instance == null) {
				instance = new JobDAO();
			}
			return instance;
		}
		
		public ArrayList<JobVO> selectAll(){
			ArrayList<JobVO> list = new ArrayList<JobVO>();
			JobVO vo = null;
			try {
				conn = JdbcUtil.connect();
				String sql = "select job_id,"
						+ " job_title,"
						+ " min_salary,"
						+ " max_salary"
						+ " from jobs"
						+ " order by job_id";
				pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					vo = new JobVO();
					vo.setJob_id(rs.getString("job_id"));
					vo.setJob_title(rs.getString("job_title"));
					vo.setMin_salary(rs.getInt("min_salary"));
					vo.setMax_salary(rs.getInt("max_salary"));
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
