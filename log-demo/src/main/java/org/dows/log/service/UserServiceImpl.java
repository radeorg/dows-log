//package org.dows.test.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.dows.test.entity.UserEntity;
//import org.dows.test.mapper.UserEntityMapper;
//import org.dows.framework.crud.mybatis.MybatisRepositoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Slf4j
//@Service
//@AllArgsConstructor
//// @CacheConfig(cacheNames = LogService.CACHE_KEY)
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
//public class UserServiceImpl extends MybatisRepositoryImpl<UserEntityMapper, UserEntity> implements UserService {
//
//    @Autowired
//    private UserEntityMapper userEntityMapper;
//
//    @Override
//    public void insert(String str1, String str2) {
//        final UserEntity userEntity = new UserEntity();
//        userEntity.setUserName("1111");
//        userEntityMapper.insert(userEntity);
//    }
//
//
////    @AuditLog(type = LogActionType.ADD, tableSchemaClass = UserAuditLog1.class)
////    public void insert2(String str1, String str2) {
////        final UserEntity userEntity = new UserEntity();
////        userEntity.setUserName("1111");
////        userEntityMapper.insert(userEntity);
////    }
//
//    @Override
//    public void delete(Integer id) {
//        userEntityMapper.deleteById(id);
//    }
//
//    @Override
//    public List<UserEntity> queryAll(String param1) {
//        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("str1", param1);
//        return userEntityMapper.selectList(wrapper);
//    }
//
//
//}
