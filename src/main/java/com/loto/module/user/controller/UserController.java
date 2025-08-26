package com.loto.module.user.controller;

import com.loto.module.base.controller.BaseController;
import com.loto.module.user.domain.User;
import com.loto.module.user.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名：UserController.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：控制层 - 用户管理<p>
 * 创建人：蓝田_Loto
 */
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户管理")
@RequiredArgsConstructor
public class UserController extends BaseController<IUserService, User> {
    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    //private final IUserService userService;
    //
    ///**
    // * 用户管理 - 查询全部用户信息列表
    // */
    //@GetMapping("/list")
    //@Operation(summary = "查询全部用户信息列表")
    //public ResponseResult<List<User>> getUserList() {
    //    try {
    //        List<User> list = userService.getUserList();
    //        return ResponseResult.success(list);
    //    } catch (Exception e) {
    //        logger.error("查询全部用户信息列表失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 分页查询用户信息列表
    // *
    // * @param pageNum  当前页码（默认第1页）
    // * @param pageSize 每页显示数量（默认10条）
    // * @return 分页数据
    // */
    //@GetMapping("/page")
    //@Operation(summary = "分页查询用户信息列表")
    //public ResponseResult<IPage<User>> getUserPage(
    //        @RequestParam(defaultValue = "1") int pageNum,
    //        @RequestParam(defaultValue = "10") int pageSize) {
    //    try {
    //        IPage<User> page = userService.getUserPage(pageNum, pageSize);
    //        return ResponseResult.success(page);
    //    } catch (Exception e) {
    //        logger.error("分页查询用户信息列表失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 根据条件分页查询用户信息列表（排序）
    // */
    //@PostMapping("/pageByCondition")
    //@Operation(summary = "根据条件分页查询用户信息列表（排序）")
    //public ResponseResult<IPage<User>> getUserPageByCondition(
    //        @RequestParam(defaultValue = "1") int pageNum,
    //        @RequestParam(defaultValue = "10") int pageSize,
    //        @RequestParam String orderName,
    //        @RequestParam String orderValue,
    //        @RequestBody User user) {
    //    try {
    //        IPage<User> page = userService.getUserPageByCondition(pageNum, pageSize, orderName, orderValue, user);
    //        return ResponseResult.success(page);
    //    } catch (Exception e) {
    //        logger.error("根据条件分页查询用户信息列表（排序）失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 根据条件查询用户信息列表
    // */
    //@PostMapping("/listByCondition")
    //@Operation(summary = "根据条件查询用户信息列表")
    //public ResponseResult<List<User>> getUserListByCondition(@RequestBody User user) {
    //    try {
    //        List<User> list = userService.getUserListByCondition(user);
    //        return ResponseResult.success(list);
    //    } catch (Exception e) {
    //        logger.error("根据条件查询用户信息列表失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 根据 userName 查询用户信息（返回 UserDTO）
    // */
    //@GetMapping("/getByUserName/{userName}")
    //@Operation(summary = "根据 userName 查询用户信息（返回 UserDTO）")
    //public ResponseResult<UserDTO> getUserByUserName(@PathVariable String userName) {
    //    try {
    //        UserDTO user = userService.getUserByUserName(userName);
    //        return ResponseResult.success(user);
    //    } catch (Exception e) {
    //        logger.error("根据 userName 获取用户信息失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 新增或修改用户信息
    // */
    //@PostMapping("/addOrUpdate")
    //@Operation(summary = "新增或修改用户信息")
    //public ResponseResult<String> addOrUpdateUser(@RequestBody User user) {
    //    try {
    //        // 1、参数验证
    //        if (user == null) {
    //            return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
    //        }
    //        // 2、新增或修改
    //        String isSuccess = String.valueOf(userService.saveOrUpdate(user));
    //        return ResponseResult.success(isSuccess, "保存成功");
    //    } catch (Exception e) {
    //        logger.error("新增或修改用户信息失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 批量新增或修改用户信息
    // */
    //@PostMapping("/addOrUpdateBatch")
    //@Operation(summary = "批量新增或修改用户信息")
    //public ResponseResult<String> addOrUpdateUserBatch(@RequestBody List<User> userList) {
    //    try {
    //        // 1、参数验证
    //        if (userList == null || userList.isEmpty()) {
    //            return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
    //        }
    //        // 2、新增或修改
    //        String isSuccess = String.valueOf(userService.saveOrUpdateBatch(userList));
    //        return ResponseResult.success(isSuccess, "保存成功");
    //    } catch (Exception e) {
    //        logger.error("批量新增或修改用户信息失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 删除用户信息
    // */
    //@DeleteMapping("/delete/{cId}")
    //@Operation(summary = "删除用户信息")
    //public ResponseResult<String> deleteUser(@PathVariable String cId) {
    //    try {
    //        // 1、参数验证
    //        if (cId == null || cId.isEmpty()) {
    //            return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
    //        }
    //        // 2、先查询是否存在（幂等验证）
    //        User userInfo = userService.getById(cId);
    //        if (userInfo == null) {
    //            return ResponseResult.failure(ResultEnum.NOT_FOUND.getCode(), "用户不存在");
    //        }
    //        // 3、删除
    //        String isSuccess = String.valueOf(userService.removeById(cId));
    //        return ResponseResult.success(isSuccess, "删除成功");
    //    } catch (Exception e) {
    //        logger.error("删除用户信息失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
    //
    ///**
    // * 用户管理 - 批量删除用户信息
    // */
    //@DeleteMapping("/deleteBatch")
    //@Operation(summary = "批量删除用户信息")
    //public ResponseResult<String> deleteUserBatch(@RequestBody List<String> cIdList) {
    //    try {
    //        // 1、参数验证
    //        if (cIdList == null || cIdList.isEmpty()) {
    //            return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
    //        }
    //        // 2、先查询是否存在（幂等验证）
    //        List<User> userList = userService.listByIds(cIdList);
    //        if (userList.size() != cIdList.size()) {
    //            return ResponseResult.failure(ResultEnum.NOT_FOUND.getCode(), "用户不存在");
    //        }
    //        // 3、删除
    //        String isSuccess = String.valueOf(userService.removeByIds(cIdList));
    //        return ResponseResult.success(isSuccess, "删除成功");
    //    } catch (Exception e) {
    //        logger.error("批量删除用户信息失败", e);
    //        return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常");
    //    }
    //}
}
