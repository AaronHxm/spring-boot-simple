//package com.bokun.it.ctrl;
//
//
//import com.bokun.it.common.annotation.ResponseResult;
//import com.bokun.it.common.exception.BusinessException;
//import com.bokun.it.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//
//
///**
// * 文件用户
// *
// * @author chenshun
// * @email sunlightcs@gmail.com
// * @date 2021-05-28 14:06:47
// */
//@RestController
//@RequestMapping("/sysuser")
//@ResponseResult
//public class SysUserCtrl {
//    @Autowired
//    private SysUserService sysUserService;
//
//    @GetMapping("/getToken")
//    public String getToken(@RequestParam String appId,@RequestParam String appSecret) throws BusinessException {
//
//        return sysUserService.getToken(appId,appSecret);
//    }
////
////    /**
////     * 列表
////     */
////    @RequestMapping("/list")
////    @RequiresPermissions("generator:sysuser:list")
////    public R list(@RequestParam Map<String, Object> params){
////        PageUtils page = sysUserService.queryPage(params);
////
////        return R.ok().put("page", page);
////    }
////
////
////    /**
////     * 信息
////     */
////    @RequestMapping("/info/{userId}")
////    @RequiresPermissions("generator:sysuser:info")
////    public R info(@PathVariable("userId") Long userId){
////		SysUserEntity sysUser = sysUserService.getById(userId);
////
////        return R.ok().put("sysUser", sysUser);
////    }
////
////    /**
////     * 保存
////     */
////    @RequestMapping("/save")
////    @RequiresPermissions("generator:sysuser:save")
////    public R save(@RequestBody SysUserEntity sysUser){
////		sysUserService.save(sysUser);
////
////        return R.ok();
////    }
////
////    /**
////     * 修改
////     */
////    @RequestMapping("/update")
////    @RequiresPermissions("generator:sysuser:update")
////    public R update(@RequestBody SysUserEntity sysUser){
////		sysUserService.updateById(sysUser);
////
////        return R.ok();
////    }
////
////    /**
////     * 删除
////     */
////    @RequestMapping("/delete")
////    @RequiresPermissions("generator:sysuser:delete")
////    public R delete(@RequestBody Long[] userIds){
////		sysUserService.removeByIds(Arrays.asList(userIds));
////
////        return R.ok();
////    }
//
//}
