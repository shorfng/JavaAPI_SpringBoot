package com.loto.module.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loto.common.enums.ResultEnum;
import com.loto.common.response.ResponseResult;
import com.loto.common.util.MybatisBaseUtil;
import com.loto.module.base.dto.PageParamDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

/**
 * 文件名：BaseController.java<p>
 * 创建时间：2025-07-11 17:11<p>
 * 功能描述：MybatisPlus 的基础 Controller<p>
 * 创建人：蓝田_Loto
 */
public class BaseController<S extends IService<E>, E> {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected S baseService;

    /**
     * 查询 - 根据条件查询
     *
     * @param entity 查询条件
     */
    @PostMapping("/listByCondition")
    @Operation(summary = "查询 - 根据条件查询")
    public ResponseResult<List<E>> listByCondition(@RequestBody E entity) {
        try {
            QueryWrapper<E> queryWrapper = MybatisBaseUtil.getQueryWrapper(entity);
            List<E> list = baseService.list(queryWrapper);
            return ResponseResult.success(list);
        } catch (Exception e) {
            logger.error("根据条件查询失败，查询条件: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据条件查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询 - 根据分页条件查询
     *
     * @param pageParamDTO 分页条件
     */
    @PostMapping("/pageListByCondition")
    @Operation(summary = "查询 - 根据分页条件查询")
    public ResponseResult<Page<E>> pageListByCondition(@RequestBody PageParamDTO<E> pageParamDTO) {
        try {
            // 参数验证
            if (pageParamDTO == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 创建分页对象 和 查询条件
            Page<E> page = new Page<>(pageParamDTO.getPageNum(), pageParamDTO.getPageSize());
            QueryWrapper<E> queryWrapper = MybatisBaseUtil.getQueryWrapper(pageParamDTO.getParams());
            // 升序
            String ascName = pageParamDTO.getAscName();
            if (!ObjectUtils.isEmpty(ascName) && !"null".equals(ascName)) {
                String[] split = ascName.split(",");
                if (queryWrapper != null) {
                    queryWrapper.orderByAsc(Arrays.asList(split));
                }
            }
            // 降序
            String descName = pageParamDTO.getDescName();
            if (!ObjectUtils.isEmpty(descName) && !"null".equals(descName)) {
                String[] split = descName.split(",");
                if (queryWrapper != null) {
                    queryWrapper.orderByDesc(Arrays.asList(split));
                }
            }
            // 分页查询
            Page<E> ePage = baseService.page(page, queryWrapper);
            return ResponseResult.success(ePage);
        } catch (Exception e) {
            logger.error("根据分页条件查询，分页参数: {}", pageParamDTO, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据分页条件查询: " + e.getMessage());
        }
    }

    /**
     * 新增
     *
     * @param entity 实体类
     */
    @PostMapping("/save")
    @Operation(summary = "新增")
    public ResponseResult<String> save(@RequestBody E entity) {
        try {
            // 参数验证
            if (entity == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 新增
            baseService.save(entity);
            return ResponseResult.success(String.valueOf(ResultEnum.CREATED.getCode()), "新增成功！");
        } catch (Exception e) {
            logger.error("新增失败，实体: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，新增失败: " + e.getMessage());
        }
    }

    /**
     * 新增或修改
     *
     * @param entity 实体类
     */
    @PostMapping("/saveOrUpdate")
    @Operation(summary = "新增或修改")
    public ResponseResult<String> saveOrUpdate(@RequestBody E entity) {
        try {
            // 参数验证
            if (entity == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 新增或修改
            baseService.saveOrUpdate(entity);
            return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "新增或修改成功！");
        } catch (Exception e) {
            logger.error("新增或修改失败，实体: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，新增或修改失败: " + e.getMessage());
        }
    }

    /**
     * 批量新增或修改
     *
     * @param entityList 实体类列表
     */
    @PostMapping("/saveOrUpdateBatch")
    @Operation(summary = "批量新增或修改")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> saveOrUpdateBatch(@RequestBody List<E> entityList) {
        try {
            // 参数校验
            if (entityList == null || entityList.isEmpty()) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 批量新增或修改
            baseService.saveOrUpdateBatch(entityList);
            return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "批量新增或修改成功！");
        } catch (Exception e) {
            logger.error("批量新增或修改失败，实体列表大小: {}", entityList != null ? entityList.size() : 0, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，批量新增或修改失败: " + e.getMessage());
        }
    }

    /**
     * 修改 - 根据ID修改
     *
     * @param entity 实体类
     */
    @PostMapping("/updateById")
    @Operation(summary = "修改 - 根据ID修改")
    public ResponseResult<String> updateById(@RequestBody E entity) {
        try {
            // 参数校验
            if (entity == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 根据ID修改
            baseService.updateById(entity);
            return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "根据ID修改成功！");
        } catch (Exception e) {
            logger.error("根据ID修改失败，实体: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据ID修改失败: " + e.getMessage());
        }
    }

    /**
     * 查询 - 根据ID查询
     *
     * @param id 主键ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询 - 根据ID查询")
    public ResponseResult<E> getById(@PathVariable Integer id) {
        try {
            // 判断参数
            if (id == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 根据ID查询
            return ResponseResult.success(baseService.getById(id));
        } catch (Exception e) {
            logger.error("根据ID查询失败，ID: {}", id, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据ID查询失败: " + e.getMessage());
        }
    }

    /**
     * 删除 - 根据Ids删除
     *
     * @param ids 主键ID列表
     */
    @PostMapping("/deleteByIds")
    @Operation(summary = "删除 - 根据Ids删除")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> deleteByIds(@RequestBody List<Integer> ids) {
        try {
            // 参数校验
            if (ids == null || ids.isEmpty()) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 先查询是否存在（幂等验证）
            List<E> entities = baseService.listByIds(ids);
            if (entities.size() != ids.size()) {
                logger.warn("部分ID不存在，请求ID数量: {}，实际存在数量: {}", ids.size(), entities.size());
            }
            if (entities.isEmpty()) {
                return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "根据Ids删除成功！");
            }
            // 根据Ids删除
            baseService.removeByIds(ids);
            return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "根据Ids删除成功！");
        } catch (Exception e) {
            logger.error("根据Ids删除失败，ID列表: {}", ids, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据Ids删除失败: " + e.getMessage());
        }
    }

    /**
     * 统计总数
     *
     * @param entity 查询条件
     */
    @PostMapping("/count")
    @Operation(summary = "统计总数")
    public ResponseResult<Long> count(@RequestBody E entity) {
        try {
            // 获取查询条件
            QueryWrapper<E> queryWrapper = MybatisBaseUtil.getQueryWrapper(entity);
            // 统计总数
            long count = baseService.count(queryWrapper);
            return ResponseResult.success(count);
        } catch (Exception e) {
            logger.error("统计总数失败，查询条件: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，统计总数失败: " + e.getMessage());
        }
    }
}
