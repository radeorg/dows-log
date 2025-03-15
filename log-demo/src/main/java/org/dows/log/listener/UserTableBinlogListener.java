//package org.dows.test.listener;
//
//import cn.hutool.core.util.StrUtil;
//import org.dows.test.entity.UserEntity;
//import org.dows.test.entity.log.UserBinLog;
//import org.dows.log.api.BinlogListener;
//import org.dows.log.api.DomainContextHolder;
//import org.dows.log.api.DomainMetadata;
//import org.dows.log.api.annotation.Binlog;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author Administrator
// * @date 2023/2/23 17:01
// */
//@Binlog(hostName = "${log.binlog.hosts[0].host}", database = "${log.binlog.hosts[0].name}", tableSchemaClass = UserEntity.class)
//public class UserTableBinlogListener implements BinlogListener<UserEntity> {
//
//    @Autowired
//    LogService logService;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public void onUpdate(UserEntity from, UserEntity to) {
//        final DomainMetadata domainMetadata = DomainContextHolder.get(UserBinLog.class);
//        final List<String> fields = domainMetadata.getFields();
//        final List<Field> collect1 = Arrays.stream(UserEntity.class.getDeclaredFields())/*.map(f -> f.getName())*/
//                .collect(Collectors.toList());
//
//        Set<Field> mappingFields = new HashSet<>();
//        for (String field : fields) {
//            for (Field s : collect1) {
//                if (field.contains(StrUtil.toUnderlineCase(s.getName()))) {
//                    mappingFields.add(s);
//                }
//            }
//        }
//        mappingFields.forEach(f -> {
//            f.setAccessible(true);
//            try {
//                String sf = StrUtil.toUnderlineCase(f.getName());
//                // 变化前的值
//                domainMetadata.setFieldValue(sf + "_form", f.get(from));
//                // 变化后的值
//                domainMetadata.setFieldValue(sf + "_to", f.get(to));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        });
//        logService.insert(domainMetadata);
//    }
//
//    @Override
//    public void onInsert(UserEntity data) {
//
//    }
//
//    @Override
//    public void onDelete(UserEntity data) {
//
//    }
//}
