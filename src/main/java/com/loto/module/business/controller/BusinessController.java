package com.loto.module.business.controller;

import com.loto.common.enums.ResultEnum;
import com.loto.common.response.ResponseResult;
import com.loto.module.base.controller.BaseController;
import com.loto.module.business.domain.Business;
import com.loto.module.business.service.IBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文件名：BusinessController.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：控制层 - 业务管理<p>
 * 创建人：蓝田_Loto
 */
@RestController
@RequestMapping("/api/business")
@Tag(name = "业务管理")
@RequiredArgsConstructor
public class BusinessController extends BaseController<IBusinessService, Business> {
    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private IBusinessService businessService;

    /**
     * 新增
     *
     * @param entity 实体类
     */
    @PostMapping("/save")
    @Operation(summary = "新增（联合主键）")
    public ResponseResult<String> save(@RequestBody Business entity) {
        try {
            // 参数验证
            if (entity == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 根据联合主键查询数据是否存在
            Business business = businessService.selectByMultiId(entity);
            if (business != null) {
                // 数据存在，则更新
                businessService.updateByMultiId(entity);
                return ResponseResult.success(String.valueOf(ResultEnum.CREATED.getCode()), "根据联合主键查询到重复数据，更新成功！");

                // 数据存在，则报错
                //logger.error("该数据已存在，请勿重复添加！");
                //return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "该数据已存在");
            }
            // 根据联合主键查询数据不存在，则新增
            baseService.save(entity);
            return ResponseResult.success(String.valueOf(ResultEnum.CREATED.getCode()), "新增成功！（联合主键）");
        } catch (Exception e) {
            logger.error("新增失败（联合主键），实体: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，新增失败（联合主键）: " + e.getMessage());
        }
    }

    /**
     * 新增或修改
     *
     * @param entity 实体类
     */
    @PostMapping("/saveOrUpdate")
    @Operation(summary = "新增或修改（联合主键）")
    public ResponseResult<String> saveOrUpdate(@RequestBody Business entity) {
        try {
            // 参数验证
            if (entity == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 新增或修改
            businessService.saveOrUpdateByMultiId(entity);
            return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "新增或修改成功！（联合主键）");
        } catch (Exception e) {
            logger.error("新增或修改失败（联合主键），实体: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，新增或修改失败（联合主键）: " + e.getMessage());
        }
    }

    /**
     * 批量新增或修改
     *
     * @param entityList 实体类列表
     */
    @PostMapping("/saveOrUpdateBatch")
    @Operation(summary = "批量新增或修改（联合主键）")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> saveOrUpdateBatch(@RequestBody List<Business> entityList) {
        try {
            // 参数校验
            if (entityList == null || entityList.isEmpty()) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 批量新增或修改
            businessService.saveOrUpdateBatchByMultiId(entityList);
            return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "批量新增或修改成功！（联合主键）");
        } catch (Exception e) {
            logger.error("批量新增或修改失败（联合主键），实体列表大小: {}", entityList != null ? entityList.size() : 0, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，批量新增或修改失败（联合主键）: " + e.getMessage());
        }
    }

    /**
     * 修改 - 根据ID修改
     *
     * @param entity 实体类
     */
    @PostMapping("/updateById")
    @Operation(summary = "修改 - 根据ID修改（不建议用）（联合主键）")
    public ResponseResult<String> updateById(@RequestBody Business entity) {
        try {
            // 参数校验
            if (entity == null) {
                return ResponseResult.failure(ResultEnum.BAD_REQUEST.getCode(), "参数不能为空");
            }
            // 根据联合主键查询数据是否存在
            Business business = businessService.selectByMultiId(entity);
            if (business != null) {
                // 根据联合主键修改
                businessService.updateByMultiId(entity);
                return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "根据ID修改成功！（联合主键）");
            }
            // 修改的数据不存在
            logger.error("根据ID修改失败（联合主键），实体: {}", entity);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据ID修改失败（联合主键）: 找不到数据！");
        } catch (Exception e) {
            logger.error("根据ID修改失败（联合主键），实体: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据ID修改失败（联合主键）: " + e.getMessage());
        }
    }

    /**
     * 查询 - 根据ID查询
     */
    @PostMapping("/getById")
    @Operation(summary = "查询 - 根据ID查询（联合主键）")
    public ResponseResult<Business> getById(@RequestBody Business entity) {
        try {
            Business business = businessService.selectByMultiId(entity);
            return ResponseResult.success(business);
        } catch (Exception e) {
            logger.error("根据ID查询失败（联合主键），实体: {}", entity, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据ID查询失败（联合主键）: " + e.getMessage());
        }
    }

    /**
     * 删除 - 根据Ids删除
     */
    @PostMapping("/deleteById")
    @Operation(summary = "删除 - 根据Ids删除（联合主键）")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> deleteById(@RequestBody List<Business> entityList) {
        try {
            for (Business entity : entityList) {
                businessService.deleteByMultiId(entity);
            }
            return ResponseResult.success(String.valueOf(ResultEnum.SUCCESS.getCode()), "根据Ids删除成功！（联合主键）");
        } catch (Exception e) {
            logger.error("根据Ids删除失败（联合主键），实体列表大小: {}", entityList != null ? entityList.size() : 0, e);
            return ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常，根据Ids删除失败（联合主键）: " + e.getMessage());
        }
    }
}
