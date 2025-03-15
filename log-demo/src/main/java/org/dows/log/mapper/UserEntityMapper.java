package org.dows.log.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.log.entity.UserEntity;
import org.dows.framework.crud.mybatis.MybatisMapper;


@Mapper
public interface UserEntityMapper extends MybatisMapper<UserEntity> {

}
