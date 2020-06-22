package com.aaa.service;

import com.aaa.dao.DeptDao;
import com.aaa.entity.Dept;
import com.aaa.entity.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> listAll() {
        return deptDao.listAll();
    }

    @Override
    public Dept selectOne(Integer deptNo) {
        return deptDao.selectOne(deptNo);
    }

    @Override
    public int update(Dept dept) {
        return deptDao.update(dept);
    }

    @Override
    public int insert(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public int delete(Integer deptNo) {
        return deptDao.delete(deptNo);
    }

    @Override
    public int saveOrUpdate(Dept dept) {
        int i = 0;
        if (dept.getDeptno()!=null){
            i = this.update(dept);
        }else {
            i = this.insert(dept);
        }

        return i;
    }

    //方法中所有的数据操作作为事务的一部分去执行，没有问题就提交事务，一旦某一个数据操作发生异常，所有的数据操作都会回滚
    //isolation = Isolation.DEFAULT:使用数据库中事务的默认的隔离级别
    //propagation = Propagation.REQUIRED:事务的传播机制
    //readOnly = true,：只读事务
    //timeout = 60：超时时间，单位是秒，在指定的时间内，如果没有完成数据操作，并且后续还有其他数据操作，会回滚
    //rollbackFor = RuntimeException.class：引发事务回顾的异常类型，默认就是运行时异常
    //noRollbackFor = 异常类型：不会引发事务回滚的异常类型
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false,timeout = 60, rollbackFor = RuntimeException.class)
    @Override
    public int testDept(List<Dept> deptList) {
        this.insert(deptList.get(0));
        this.insert(deptList.get(1));
        System.out.println(1/0);
        this.insert(deptList.get(2));
        return 0;
    }

    @Override
    public int insertFile(Files files) {
        int i= 0;
        i = deptDao.insertFile(files);
        return i;
    }
}
