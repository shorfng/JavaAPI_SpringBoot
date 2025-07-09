package com.loto.module.user.controller;

import com.loto.common.response.ResponseResult;
import com.loto.module.user.domain.User;
import com.loto.module.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文件名：UserController.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：控制层 - 用户管理<p>
 * 创建人：蓝田_Loto
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户管理")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 用户管理 - 查询全部用户信息列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询全部用户信息列表")
    public ResponseResult<List<User>> getUserList() {
        try {
            List<User> list = userService.getUserList();
            return ResponseResult.success(list);
        } catch (Exception e) {
            logger.error("查询全部用户信息列表失败", e);
            return ResponseResult.failure(60001, "业务异常");
        }
    }
}
