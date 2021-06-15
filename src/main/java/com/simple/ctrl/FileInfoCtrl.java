package com.simple.ctrl;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





/**
 * 文件信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-28 14:06:47
 */
@RestController
@RequestMapping("generator/fileinfo")
public class FileInfoCtrl {
//    @Autowired
//    private FileInfoService fileInfoService;
//
//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = fileInfoService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{fileId}")
//    @RequiresPermissions("generator:fileinfo:info")
//    public R info(@PathVariable("fileId") Long fileId){
//		FileInfoEntity fileInfo = fileInfoService.getById(fileId);
//
//        return R.ok().put("fileInfo", fileInfo);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    @RequiresPermissions("generator:fileinfo:save")
//    public R save(@RequestBody FileInfoEntity fileInfo){
//		fileInfoService.save(fileInfo);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    @RequiresPermissions("generator:fileinfo:update")
//    public R update(@RequestBody FileInfoEntity fileInfo){
//		fileInfoService.updateById(fileInfo);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    @RequiresPermissions("generator:fileinfo:delete")
//    public R delete(@RequestBody Long[] fileIds){
//		fileInfoService.removeByIds(Arrays.asList(fileIds));
//
//        return R.ok();
//    }

}
