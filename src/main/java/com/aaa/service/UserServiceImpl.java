package com.aaa.service;

import com.aaa.dao.UserDao;
import com.aaa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByName(String userName) {
        return userDao.findByName(userName);
    }

    @Override
    public Set<String> findByRoleName(String userName) {
        return this.changeListTtoSet(userDao.findByRoleName(userName));
    }

    @Override
    public Set<String> findByMenuName(String userName) {
        return this.changeListTtoSet(userDao.findByMenuName(userName));
    }

    /**
     * 将List字符串集合转换成Set集合
     * @param source
     *
     * @return
     */
    private Set<String> changeListTtoSet(List<String> source) {
        Set<String> set=new HashSet<>();
        Iterator<String> iterator = source.iterator();
        while (iterator.hasNext()) {
            set.add(iterator.next());
        }
        return set;
    }
}
