package com.company.yedam.emp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.yedam.emp.dao.DeptDAO;
import com.company.yedam.emp.dao.DeptVO;
import com.company.yedam.emp.dao.EmpDAO;
import com.company.yedam.emp.dao.EmpVO;
import com.company.yedam.emp.dao.JobDAO;

@Controller
public class DeptController {
	Logger logger = LoggerFactory.getLogger(EmpController.class);

	@Autowired EmpDAO empDAO;
	@Autowired JobDAO jobDAO;
	@Autowired DeptDAO deptDAO;

    //부서목록
	@RequestMapping("/deptList")
	public String deptList(HttpServletRequest request) {
		request.setAttribute("list", deptDAO.selectAll());
		return "emp/deptList";
	}

    //등록폼
	@RequestMapping("/deptInsert")
	public String deptInsert(HttpServletRequest request) {
		request.setAttribute("deptlist", deptDAO.selectAll());
		request.setAttribute("jobList", jobDAO.selectAll());
		return "emp/deptInsert";

	}

    //등록처리
	@PostMapping("/deptInsert")
	public String deptInsertProc(DeptVO vo) {
		logger.debug(vo.toString());
		deptDAO.insert(vo);
		return "redirect:deptList";
	}

}
