package org.dows.log.service;

import org.dows.log.entity.UserEntity;
import org.dows.framework.crud.mybatis.MybatisRepository;

import java.util.List;

public interface UserService extends MybatisRepository<UserEntity> {

    void insert(String str1, String str2);

    void delete(Integer id);

    List<UserEntity> queryAll(String param1);

}
