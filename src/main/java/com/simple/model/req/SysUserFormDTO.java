package com.simple.model.req;




import com.simple.common.model.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件用户
 *
 * @author aaron.hu
 * @date 2021-05-31 16:38:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("SysUserFormDTO")
public class SysUserFormDTO extends BaseDTO {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long userId;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String userPhone;
    /**
     * md5盐
     */
    @ApiModelProperty("md5盐")
    private String userSalt;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String userPwd;
    /**
     * 用户真实姓名
     */
    @ApiModelProperty("用户真实姓名")
    private String userRealName;
    /**
     * appId
     */
    @ApiModelProperty("appId")
    private String appid;
    /**
     * appsecret
     */
    @ApiModelProperty("appsecret")
    private String appsecret;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Long createUser;
    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private Long updateUser;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;
}
