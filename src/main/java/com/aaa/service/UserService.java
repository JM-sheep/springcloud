package com.aaa.service;

import com.aaa.entity.User;

import java.util.Set;

public interface UserService {
    User findByName(String userName);
    Set<String> findByRoleName(String userName);
    Set<String> findByMenuName(String userName);
}
