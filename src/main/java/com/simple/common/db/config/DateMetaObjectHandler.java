package com.simple.common.db.config;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import com.simple.common.db.constant.CommonConstant;
import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义填充公共字段
 *
 * @Author saiqiang.zhang
 * @date 2018/12/11
 */
@Slf4j
@Component
public class DateMetaObjectHandler implements MetaObjectHandler {
    HttpServletRequest request;

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
//        Object id = getFieldValByName(autoFillProperties.getIdField(), metaObject);
         Object status = getFieldValByName("status", metaObject);
//        Object createTime = getFieldValByName(autoFillProperties.getCreateTimeField(), metaObject);
//        Object createBy = getFieldValByName(autoFillProperties.getCreateByField(), metaObject);
//        Object updateTime = getFieldValByName(autoFillProperties.getUpdateTimeField(), metaObject);
//        Object updateBy = getFieldValByName(autoFillProperties.getUpdateByField(), metaObject);
//
//        if (id == null) {
//            setFieldValByName(autoFillProperties.getIdField(), IdGenerator.getId(), metaObject);
//        }

        if (status == null) {
            setFieldValByName("status", CommonConstant.STATUS_NORMAL, metaObject);
        }

        setFieldValByName("createTime", new Date(), metaObject);
        setFieldValByName("updateTime", new Date(), metaObject);
        Long userId = this.getUserId();
        if (userId != null) {
            setFieldValByName("createUser", userId, metaObject);
        }

//        String userName = this.getUserName();
//        if(!StringUtils.isEmpty(userName)){
//            setFieldValByName(autoFillProperties.getCreateNameField(), userName, metaObject);
//        }
    }

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);
        Object status = getFieldValByName("status", metaObject);

        Long userId = this.getUserId();
        if (userId != null) {
            setFieldValByName("updateUser", userId, metaObject);
        }
//
//        String userName =  this.getUserName();
//        if(!StringUtils.isEmpty(userName)){
//            setFieldValByName(autoFillProperties.getUpdateNameField(), userName, metaObject);
//        }
//        if (status == null) {
//            setFieldValByName(autoFillProperties.getStatusField(), CommonConstant.STATUS_NORMAL, metaObject);
//        }
    }

    private Long getUserId() {
//        RpcContext rpcContext = RpcContext.getContext();
//        Long userId = null;
//        if (rpcContext.getAttachment(CommonConstant.USER_ID_PARAM) != null) {
//            userId = Long.parseLong(rpcContext.getAttachment(CommonConstant.USER_ID_PARAM));
//            return userId;
//        }

        log.info("获取不到当前用户id，使用默认值{}", CommonConstant.DEFAULT_USER_ID);
        return CommonConstant.DEFAULT_USER_ID;
    }

    private String getUserName() {
//        RpcContext rpcContext = RpcContext.getContext();
//        String userName = "";
//        if (rpcContext.getAttachment(CommonConstant.USER_NAME) != null) {
//            userName = String.valueOf(rpcContext.getAttachment(CommonConstant.USER_NAME));
//            return userName;
//        }

        log.info("获取不到当前用户名，使用默认值{}", CommonConstant.DEFAULT_USER_NAME);
        return CommonConstant.DEFAULT_USER_NAME;
    }
}
