package com.loto.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loto.module.user.domain.User;
import com.loto.module.user.dto.UserDTO;
import com.loto.module.user.mapper.IUserMapper;
import com.loto.module.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文件名：UserServiceImpl.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：服务实现类 - 用户管理<p>
 * 创建人：蓝田_Loto
 */
@Service
//@DS("ds-oracle") // 指定数据源
@Transactional(readOnly = true) // 只读事务
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    /**
     * 用户管理 - 查询全部用户信息列表
     */
    @Override
    public List<User> getUserList() {
        return this.list();
    }

    /**
     * 用户管理 - 分页查询用户信息列表
     */
    @Override
    public IPage<User> getUserPage(int pageNum, int pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        return this.page(page);
    }

    /**
     * 用户管理 - 条件查询用户信息列表
     */
    @Override
    public List<User> getUserListByCondition(User user) {
        return this.list(new QueryWrapper<>(user));
    }

    /**
     * 用户管理 - 条件分页查询用户信息列表（排序）
     */
    @Override
    public IPage<User> getUserPageByCondition(int pageNum, int pageSize, String orderName, String orderValue, User user) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        // 动态添加排序条件：如果orderValue="asc"升序，否则降序
        if (orderName != null && !orderName.isEmpty()) {
            queryWrapper.orderBy(true, "asc".equalsIgnoreCase(orderValue), orderName);
        }
        return baseMapper.selectPage(page, queryWrapper); // 调用 MyBatis Plus 内置分页方法
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
        // 创建查询条件，查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("C_USERNAME", userName);
        List<User> userList = baseMapper.selectList(queryWrapper);
        // 判断查询结果
        if (userList != null && !userList.isEmpty()) {
            return UserDTO.builder()
                    .userName(userName)
                    .userList(userList)
                    .build();
        } else {
            return null;
        }
    }
}
