package com.loto.module.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loto.common.enums.ResultEnum;
import com.loto.common.response.ResponseResult;
import com.loto.module.user.domain.User;
import com.loto.module.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
        }
    }

    /**
     * 用户管理 - 分页查询用户信息列表
     *
     * @param pageNum  当前页码（默认第1页）
     * @param pageSize 每页显示数量（默认10条）
     * @return 分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户信息列表")
    public ResponseResult<IPage<User>> getUserPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        try {
            IPage<User> page = userService.getUserPage(pageNum, pageSize);
            return ResponseResult.success(page);
        } catch (Exception e) {
            logger.error("分页查询用户信息列表失败", e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
        }
    }

    /**
     * 用户管理 - 根据条件查询用户信息列表
     */
    @PostMapping("/listByCondition")
    @Operation(summary = "根据条件查询用户信息列表")
    public ResponseResult<List<User>> getUserListByCondition(@RequestBody User user) {
        try {
            List<User> list = userService.getUserListByCondition(user);
            return ResponseResult.success(list);
        } catch (Exception e) {
            logger.error("根据条件查询用户信息列表失败", e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
        }
    }

    /**
     * 用户管理 - 根据条件分页查询用户信息列表（排序）
     */
    @PostMapping("/pageByCondition")
    @Operation(summary = "根据条件分页查询用户信息列表（排序）")
    public ResponseResult<IPage<User>> getUserPageByCondition(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam String orderName,
            @RequestParam String orderValue,
            @RequestBody User user) {
        try {
            IPage<User> page = userService.getUserPageByCondition(pageNum, pageSize, orderName, orderValue, user);
            return ResponseResult.success(page);
        } catch (Exception e) {
            logger.error("根据条件分页查询用户信息列表（排序）失败", e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
        }
    }

    /**
     * 用户管理 - 新增或修改用户信息
     */
    @PostMapping("/addOrUpdate")
    @Operation(summary = "新增或修改用户信息")
    public ResponseResult<String> addOrUpdateUser(@RequestBody User user) {
        try {
            String isSuccess = String.valueOf(userService.saveOrUpdate(user));
            return ResponseResult.success(isSuccess, "保存成功");
        } catch (Exception e) {
            logger.error("新增或修改用户信息失败", e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
        }
    }

    /**
     * 用户管理 - 批量新增或修改用户信息
     */
    @PostMapping("/addOrUpdateBatch")
    @Operation(summary = "批量新增或修改用户信息")
    public ResponseResult<String> addOrUpdateUserBatch(@RequestBody List<User> userList) {
        try {
            String isSuccess = String.valueOf(userService.saveOrUpdateBatch(userList));
            return ResponseResult.success(isSuccess, "保存成功");
        } catch (Exception e) {
            logger.error("批量新增或修改用户信息失败", e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
        }
    }
}
