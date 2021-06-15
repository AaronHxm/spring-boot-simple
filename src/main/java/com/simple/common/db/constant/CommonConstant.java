package com.simple.common.db.constant;

public interface CommonConstant {

    /**
     * 项目版本号(banner使用)
     */
    String PROJECT_VERSION = "1.0.0";

    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * The access token issued by the authorization server. This value is REQUIRED.
     */
    String ACCESS_TOKEN = "access_token";

    String BEARER_TYPE = "Bearer";

    /**
     * 标签 header key
     */
    String HEADER_LABEL = "x-label";

    /**
     * 标签 header 分隔符
     */
    String HEADER_LABEL_SPLIT = ",";

    /**
     * 标签或 名称
     */
    String LABEL_OR = "labelOr";

    /**
     * 标签且 名称
     */
    String LABEL_AND = "labelAnd";

    /**
     * 权重key
     */
    String WEIGHT_KEY = "weight";

    /**
     * 禁用
     */
    Integer STATUS_DISABLE = 0;

    /**
     * 启用
     */
    Integer STATUS_NORMAL = 1;

    /**
     * 锁定
     */
    Integer STATUS_LOCK = 2;

    /**
     * 删除
     */
    Integer STATUS_DELETE = 3;

    /**
     * 目录
     */
    Integer RESOURCE_CATALOG = 0;

    /**
     * 菜单
     */
    Integer RESOURCE_MENU = 1;

    /**
     * 按钮
     */
    Integer RESOURCE_BUTTON = 2;

    /**
     * api
     */
    Integer RESOURCE_API = 3;

    /**
     * 群组类型，公司
     */
    String GROUP_TYPE_COMPANY = "0";

    /**
     * 群组类型，家庭
     */
    String GROUP_TYPE_FAMILY = "1";

    /**
     * 授权类型，拒绝
     */
    String AUTH_TYPE_REJECT = "0";

    /**
     * 授权类型，允许
     */
    String AUTH_TYPE_PERMIT = "1";

    /**
     * 数据权限类型，个人
     */
    String DATA_AUTH_TYPE_INDIVIDUAL = "0";

    /**
     * 数据权限类型，本级
     */
    String DATA_AUTH_TYPE_LEVEL_SAME = "1";

    /**
     * 数据权限类型，本级及子级
     */
    String DATA_AUTH_TYPE_LEVEL_SAMEANDSUB = "2";

    /**
     * 数据权限类型，全部
     */
    String DATA_AUTH_TYPE_ALL = "3";

    /**
     * 数据权限类型，自定义
     */
    String DATA_AUTH_TYPE_USER_DEFINED = "4";

    /**
     * 权限
     */
    Integer PERMISSION = 2;

    /**
     * 删除标记
     */
    String DEL_FLAG = "is_del";

    /**
     * 超级管理员用户名
     */
    String ADMIN_USER_NAME = "admin";

    /**
     * 公共日期格式
     */
    String MONTH_FORMAT = "yyyy-MM";
    String DATE_FORMAT = "yyyy-MM-dd";
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String SIMPLE_MONTH_FORMAT = "yyyyMM";
    String SIMPLE_DATE_FORMAT = "yyyyMMdd";
    String SIMPLE_DATETIME_FORMAT = "yyyyMMddHHmmss";

    String DEF_USER_PASSWORD = "123456";

    String LOCK_KEY_PREFIX = "LOCK_KEY:";

    /**
     * 租户id参数
     */
    String TENANT_ID_PARAM = "tenantId";

    /**
     * 用户id参数
     */
    String USER_ID_PARAM = "userId";
    /**
     * 用户id参数
     */
    String USER_NAME = "userName";

    /**
     * 日志链路追踪id信息头
     */
    String TRACE_ID_HEADER = "x-traceId-header";
    /**
     * 日志链路追踪id日志标志
     */
    String LOG_TRACE_ID = "traceId";
    /**
     * 负载均衡策略-版本号 信息头
     */
    String Z_L_T_VERSION = "z-l-t-version";
    /**
     * 注册中心元数据 版本号
     */
    String METADATA_VERSION = "version";

    String DICT_CODE_SYS_TYPE = "sys_type";

    String DICT_CODE_STATUS = "status";

    String DICT_CODE_FAMILY_RELATION = "family_relation";

    Integer AUTH_REQUIRED_YES = 1;

    Integer AUTH_REQUIRED_NO = 0;

    /**
     * 获到不到租户id时，使用默认值
     */
    String DEFAULT_TENANT_ID = "1278680935482384386";

    /**
     * 获到不到用户id时，使用默认值，即系统用户
     */
    Long DEFAULT_USER_ID = 10000L;
    /**
     * 获到不到用户id时，使用默认值，即系统用户
     */
    String DEFAULT_USER_NAME = "default";


    /**
     * @DESC 存在不传的情况 默认header
     */
    String USER_IDENTITY = "client";
    /**
     * @DESC 存在不传的情况 默认header
     */
    String DEFAULT_USER_IDENTITY = "APP";
    /**
     * 角色信息头
     */
}
