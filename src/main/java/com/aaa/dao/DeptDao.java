package com.aaa.dao;

import com.aaa.entity.Dept;
import com.aaa.entity.Files;

import java.util.List;

public interface DeptDao {
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

    int insertFile(Files files);
}
