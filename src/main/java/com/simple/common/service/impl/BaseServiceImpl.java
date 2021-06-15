package com.simple.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cn.hutool.core.util.StrUtil;
import com.simple.common.db.constant.CommonConstant;
import com.simple.common.exception.BusinessException;
import com.simple.common.lock.DistributedLock;
import com.simple.common.model.BaseDTO;
import com.simple.common.service.IBaseService;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName BaseServiceImpl.java
 * @Description TODO
 * @createTime 2021年06月01日 09:20:00
 */
public class BaseServiceImpl <M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {
    /**
     * 幂等性新增记录 例子如下： String username = sysUser.getUsername(); boolean result = super.saveIdempotency(sysUser, lock ,
     * LOCK_KEY_USERNAME+username , new QueryWrapper<SysUser>().eq("username", username));
     *
     * @param entity
     *            实体对象
     * @param lock
     *            锁实例
     * @param lockKey
     *            锁的key
     * @param countWrapper
     *            判断是否存在的条件
     * @param msg
     *            对象已存在提示信息
     * @return
     */
    @Override
    public boolean saveIdempotency(T entity, DistributedLock lock, String lockKey, Wrapper<T> countWrapper, String msg)
            throws BusinessException {
        if (lock == null) {
            throw new BusinessException("DistributedLock is null");
        }
        if (StrUtil.isEmpty(lockKey)) {
            throw new BusinessException("lockKey is null");
        }
        try {
            // 加锁
            boolean isLock = lock.lock(lockKey);
            if (isLock) {
                // 判断记录是否已存在
                int count = super.count(countWrapper);
                if (count == 0) {
                    return super.save(entity);
                } else {
                    if (StrUtil.isEmpty(msg)) {
                        msg = "已存在";
                    }
                    throw new BusinessException(msg);
                }
            } else {
                throw new BusinessException("锁等待超时");
            }
        } finally {
            lock.releaseLock(lockKey);
        }
    }

    /**
     * 幂等性新增记录
     *
     * @param entity
     *            实体对象
     * @param lock
     *            锁实例
     * @param lockKey
     *            锁的key
     * @param countWrapper
     *            判断是否存在的条件
     * @return
     */
    @Override
    public boolean saveIdempotency(T entity, DistributedLock lock, String lockKey, Wrapper<T> countWrapper)
            throws BusinessException {
        return saveIdempotency(entity, lock, lockKey, countWrapper, null);
    }

    /**
     * 幂等性新增或更新记录 例子如下： String username = sysUser.getUsername(); boolean result = super.saveOrUpdateIdempotency(sysUser,
     * lock , LOCK_KEY_USERNAME+username , new QueryWrapper<SysUser>().eq("username", username));
     *
     * @param entity
     *            实体对象
     * @param lock
     *            锁实例
     * @param lockKey
     *            锁的key
     * @param countWrapper
     *            判断是否存在的条件
     * @param msg
     *            对象已存在提示信息
     * @return
     */
    @Override
    public boolean saveOrUpdateIdempotency(T entity, DistributedLock lock, String lockKey, Wrapper<T> countWrapper,
                                           String msg) throws BusinessException {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StrUtil.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable)idVal))) {
                    if (StrUtil.isEmpty(msg)) {
                        msg = "已存在";
                    }
                    return this.saveIdempotency(entity, lock, lockKey, countWrapper, msg);
                } else {
                    return updateById(entity);
                }
            } else {
                throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    /**
     * 幂等性新增或更新记录 例子如下： String username = sysUser.getUsername(); boolean result = super.saveOrUpdateIdempotency(sysUser,
     * lock , LOCK_KEY_USERNAME+username , new QueryWrapper<SysUser>().eq("username", username));
     *
     * @param entity
     *            实体对象
     * @param lock
     *            锁实例
     * @param lockKey
     *            锁的key
     * @param countWrapper
     *            判断是否存在的条件
     * @return
     */
    @Override
    public boolean saveOrUpdateIdempotency(T entity, DistributedLock lock, String lockKey, Wrapper<T> countWrapper)
            throws BusinessException {
        return this.saveOrUpdateIdempotency(entity, lock, lockKey, countWrapper, null);
    }

    /**
     * 创建搜索查询
     *
     * @return
     */
    public QueryWrapper<T> createQueryWrapperNoOrdeBy(String className, BaseDTO baseDTO) throws BusinessException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();

        try {
            Class clazz = Class.forName(className);

            List<Field> fieldList = new ArrayList<Field>();
            while (clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    String fieldName = field.getName();
                    if ("serialVersionUID".equals(fieldName) || "currentPage".equals(fieldName)
                            || "pageSize".equals(fieldName)) {
                        continue;
                    }

                    String upperChar = fieldName.substring(0, 1).toUpperCase();
                    String anotherStr = fieldName.substring(1);
                    String methodName = "get" + upperChar + anotherStr;
                    Method method = clazz.getMethod(methodName, new Class[] {});
                    method.setAccessible(true);
                    Object fieldValue = method.invoke(baseDTO, new Object[] {});
                    if (fieldValue != null && StringUtils.isNotBlank(String.valueOf(fieldValue))) {
                        if (field.getType().toString().equals("class java.lang.String")) {
                            queryWrapper.like(StringUtils.camelToUnderline(fieldName), fieldValue);
                        } else {
                            queryWrapper.eq(StringUtils.camelToUnderline(fieldName), fieldValue);
                        }
                    }
                }

                clazz = clazz.getSuperclass();
            }
            queryWrapper.ne("status", CommonConstant.STATUS_DELETE);

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return queryWrapper;
    }


    /**
     * 创建搜索查询
     *
     * @return
     */
    public QueryWrapper<T> createQueryWrapper(String className, BaseDTO baseDTO) throws BusinessException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();

        try {
            Class clazz = Class.forName(className);

            List<Field> fieldList = new ArrayList<Field>();
            while (clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    String fieldName = field.getName();
                    if ("serialVersionUID".equals(fieldName) || "currentPage".equals(fieldName)
                            || "pageSize".equals(fieldName)) {
                        continue;
                    }

                    String upperChar = fieldName.substring(0, 1).toUpperCase();
                    String anotherStr = fieldName.substring(1);
                    String methodName = "get" + upperChar + anotherStr;
                    Method method = clazz.getMethod(methodName, new Class[] {});
                    method.setAccessible(true);
                    Object fieldValue = method.invoke(baseDTO, new Object[] {});
                    if (fieldValue != null && StringUtils.isNotBlank(String.valueOf(fieldValue))) {
                        if (field.getType().toString().equals("class java.lang.String")) {
                            queryWrapper.like(StringUtils.camelToUnderline(fieldName), fieldValue);
                        } else {
                            queryWrapper.eq(StringUtils.camelToUnderline(fieldName), fieldValue);
                        }
                    }
                }

                clazz = clazz.getSuperclass();
            }
            queryWrapper.ne("status", CommonConstant.STATUS_DELETE);
            queryWrapper.orderByDesc("create_time");

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return queryWrapper;
    }

    public List<Field> listClassField(String className) throws BusinessException {
        try {
            Class clazz = Class.forName(className);
            Field[] fields = clazz.getDeclaredFields();
            return Arrays.asList(fields);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public UpdateWrapper<T> createUpdateWrapper(String className, BaseDTO baseDTO) throws BusinessException {
        UpdateWrapper<T> updayeWrapper = new UpdateWrapper<T>();

        try {
            Class clazz = Class.forName(className);

            List<Field> fieldList = new ArrayList<Field>();
            while (clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    String fieldName = field.getName();


                    String upperChar = fieldName.substring(0, 1).toUpperCase();
                    String anotherStr = fieldName.substring(1);
                    String methodName = "get" + upperChar + anotherStr;
                    Method method = clazz.getMethod(methodName, new Class[] {});
                    method.setAccessible(true);
                    Object fieldValue = method.invoke(baseDTO, new Object[] {});
                    if (fieldValue != null && StringUtils.isNotBlank(String.valueOf(fieldValue))) {
                        if (field.getType().toString().equals("class java.lang.String")) {
                            updayeWrapper.like(StringUtils.camelToUnderline(fieldName), fieldValue);
                        } else {
                            updayeWrapper.eq(StringUtils.camelToUnderline(fieldName), fieldValue);
                        }
                    }
                }

                clazz = clazz.getSuperclass();
            }

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return updayeWrapper;
    }
}
