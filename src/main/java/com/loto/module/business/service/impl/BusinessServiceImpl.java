package com.loto.module.business.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.loto.module.business.domain.Business;
import com.loto.module.business.mapper.IBusinessMapper;
import com.loto.module.business.service.IBusinessService;
import org.springframework.stereotype.Service;

/**
 * 文件名：BusinessServiceImpl.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：服务实现类 - 业务管理<p>
 * 创建人：蓝田_Loto
 */
@Service
//@DS("ds-oracle") // 指定数据源
//@Transactional(readOnly = true) // 只读事务
public class BusinessServiceImpl extends MppServiceImpl<IBusinessMapper, Business> implements IBusinessService {


}
