package com.aaa.service;

import com.aaa.entity.Dept;
import com.aaa.entity.Files;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
    List<Dept> listAll();

    /**
     * 根据员工编号查询部门
     * @param deptNo
     * @return
     */
    Dept selectOne(Integer deptNo);

    /**
     * 修改部门
     * @param dept
     * @return
     */
    int update(Dept dept);

    /**
     * 增加部门
     * @param dept
     * @return
     */
    int insert(Dept dept);

    /**
     * 删除部门
     * @param deptNo
     * @return
     */
    int delete(Integer deptNo);

    /**
     * 增加或者修改部门
     * @param dept
     * @return
     */
    int saveOrUpdate(Dept dept);

    int testDept(List<Dept> deptList);

    int insertFile(Files files);
}
