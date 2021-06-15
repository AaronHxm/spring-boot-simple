package com.simple.ctrl;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaron.hu
 * @version 1.0.0
 * @ClassName FileCtrl.java
 * @Description TODO
 * @createTime 2021年05月27日 10:49:00
 */
@Api(value = "file", tags = "该接口为文件接口，主要用来做一些文件的基本操作，如创建目录，删除，移动，复制等。")
@RestController
@Slf4j
@RequestMapping("/file")
public class FileCtrl {
}
