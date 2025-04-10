//package org.dows.log.boot.mapper;
//
//import com.baomidou.mybatisplus.core.mapper.Mapper;
//import org.objectweb.asm.ClassWriter;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.ParameterizedType;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
//import static org.objectweb.asm.Opcodes.*;
//
///**
// * 动态构建Mapper Class文件并加载
// */
//public class DynamicMapperCreator {
//    private final static String MAPPER_INTERFACE_NAME = resolveClazzName(Mapper.class);
//    private final static String OBJECT_CLASS_NAME = resolveClazzName(Object.class);
//    private final static String ANONYMOUS_MAPPER_SIGNATURE = "L"
//            + OBJECT_CLASS_NAME + ";L" + MAPPER_INTERFACE_NAME + "<L%s;L%s;>;";
//    private final static String ANONYMOUS_MAPPER_CLASS_NAME = MAPPER_INTERFACE_NAME + "$%s";
//    private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//    private static Method defineClazzMethod;
//
//    static {
//        try {
//            defineClazzMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//        defineClazzMethod.setAccessible(true);
//    }
//
//    private Map<Class<?>, Class<?>> mappers = new HashMap<>();
//
//    private static String resolveClazzName(Class<?> clazz) {
//        return clazz.getName().replaceAll("\\.", "/");
//    }
//
//    private static Class<?> loadMapperClazz(byte[] bytes)
//            throws InvocationTargetException, IllegalAccessException {
//        return (Class<?>) defineClazzMethod.invoke(classLoader, null, bytes, 0,
//                bytes.length);
//    }
//
//    public Class<?> getOrCreateMapperClazz(Class<?> entityClazz) {
//        Class<?> mapperClazz = mappers.get(entityClazz);
//
//        if (mapperClazz != null) {
//            return mapperClazz;
//        }
//
//        try {
//            mapperClazz = classLoader.loadClass(String.format(
//                    ANONYMOUS_MAPPER_CLASS_NAME, entityClazz.getSimpleName()));
//        } catch (ClassNotFoundException ignore) {
//            mapperClazz = createMapperClazz(entityClazz);
//        }
//
//        mappers.put(entityClazz, mapperClazz);
//        return mapperClazz;
//    }
//
//    public Class<?> createMapperClazz(Class<?> entityClazz) {
////        if (!PrimaryKey.class.isAssignableFrom(entityClazz)) {
////            throw new RuntimeException("Entity class must directly extends PrimaryKey.class");
////        }
//
//        try {
//            return buildAnonymousMapperClazz(entityClazz);
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 使用asm框架根据实体类动态创建Mapper接口类并加载到SystemClassLoader
//     *
//     * @param entityClazz
//     * @return
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     */
//    private Class<?> buildAnonymousMapperClazz(Class<?> entityClazz)
//            throws InvocationTargetException, IllegalAccessException {
//        String entityName = entityClazz.getSimpleName();
//
//        Class<?> pkClazz = (Class<?>) ((ParameterizedType) entityClazz
//                .getGenericInterfaces()[0]).getActualTypeArguments()[0];
//
//        String mapperClazzName = String.format(ANONYMOUS_MAPPER_CLASS_NAME,
//                entityName);
//
//        String signature = String.format(ANONYMOUS_MAPPER_SIGNATURE,
//                resolveClazzName(pkClazz), resolveClazzName(entityClazz));
//
//        ClassWriter cw = new ClassWriter(COMPUTE_MAXS);
//
//        cw.visit(V1_8, ACC_PUBLIC + ACC_INTERFACE + ACC_ABSTRACT,
//                mapperClazzName, signature, OBJECT_CLASS_NAME,
//                new String[]{MAPPER_INTERFACE_NAME});
//        cw.visitEnd();
//
//        return loadMapperClazz(cw.toByteArray());
//    }
//}
