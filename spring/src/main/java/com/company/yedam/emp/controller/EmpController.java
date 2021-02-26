package com.company.yedam.emp.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.yedam.emp.dao.DeptDAO;
import com.company.yedam.emp.dao.EmpDAO;
import com.company.yedam.emp.dao.EmpVO;
import com.company.yedam.emp.dao.JobDAO;


@Controller //== @Component 1.컨테이너 빈으로 등록, 컨트롤러화
public class EmpController {
	
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired EmpDAO empDAO;
	@Autowired JobDAO jobDAO;
    @Autowired DeptDAO deptDAO;
	//사원목록
    @RequestMapping("/empList")
    public String  empList(HttpServletRequest request) {
    	request.setAttribute("list", empDAO.selectList() );
    	return "emp/empList";
    }
    
	//등록폼
    @RequestMapping("/empInsert")
    public String empInsert(HttpServletRequest request) {
    	request.setAttribute("jobList", jobDAO.selectAll() );
    	request.setAttribute("deptList", deptDAO.selectAll() );
    	return "emp/empInsert";
    	
    }
	
	//등록처리
    @PostMapping("/empInsert")
    public String empInsertProc(EmpVO vo) {
    	logger.debug(vo.toString());
    	empDAO.insert(vo);
    	return "redirect:empList";
    }
    	
	//수정폼
    @RequestMapping("/empUpdate")
    public String empUpdate(HttpServletRequest request) {
    	String empid = request.getParameter("employee_id");
    	request.setAttribute("empVO", empDAO.selectOne(empid));
    	request.setAttribute("jobList", jobDAO.selectAll() );
    	request.setAttribute("deptList", deptDAO.selectAll() );
    	return "emp/empInsert";
    }
	
	//수정처리
	
	//이메일체크
	
	//사원검색
    @RequestMapping("/empSearch")
    public String empSearch(HttpServletRequest request) {
    	request.setAttribute("list", empDAO.selectList());
    	return "emp/empSearch";
    }

}
