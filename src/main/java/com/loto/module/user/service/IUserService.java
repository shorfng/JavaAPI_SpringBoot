package com.loto.module.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loto.module.user.domain.User;

import java.util.List;

/**
 * 文件名：IUserService.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：服务接口 - 用户管理<p>
 * 创建人：蓝田_Loto
 */
public interface IUserService extends IService<User> {
    /**
     * 用户管理 - 查询全部用户信息列表
     */
    List<User> getUserList();

    /**
     * 用户管理 - 分页查询用户信息列表
     */
    IPage<User> getUserPage(int pageNum, int pageSize);
}
