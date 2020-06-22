package com.aaa.controller;

import com.aaa.entity.Dept;
import com.aaa.service.DeptService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/listAll")
    public List<Dept> listAll(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("管理员")){
            return deptService.listAll();
        }
        return null;
    }

    @RequestMapping("/saveOrUpdate")
    public int saveOrUpdate(Dept dept){
        return deptService.saveOrUpdate(dept);
    }

    @RequestMapping("edit")
    public Dept edit(Integer deptno){
        return deptService.selectOne(deptno);
    }

    @RequestMapping("/delete")
    public int delete(Integer deptno){
        return deptService.delete(deptno);
    }

    @RequestMapping("/test")
    public String test(@RequestBody List<Dept> listDept){
        deptService.testDept(listDept);
        return "true:1";
    }
}
