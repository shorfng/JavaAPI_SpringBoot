package com.loto.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loto.module.user.domain.User;
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
}
