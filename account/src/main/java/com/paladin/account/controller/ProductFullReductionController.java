package com.paladin.account.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paladin.account.entity.ProductFullReduction;
import com.paladin.account.resp.RespOk;
import com.paladin.account.service.IProductFullReductionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品满减表(只针对同商品) 前端控制器
 * </p>
 *
 * @author paladin
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/product/full/reduction")
public class ProductFullReductionController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductFullReductionController.class);

    @Resource
    private IProductFullReductionService productFullReductionService;

    @PostMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "添加成功"), @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code = 403, message = "请求被拒绝"), @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")})
//    @ApiImplicitParams({@ApiImplicitParam})
    @ApiOperation(value = "添加产品满减", notes = "添加产品满减", response = RespOk.class)
    public RespOk addProductFullReduction(@RequestBody ProductFullReduction productFullReduction) {
        boolean result = productFullReductionService.save(productFullReduction);
        return result ? new RespOk(200, "添加成功") : new RespOk(200, "添加失败");
    }

    @DeleteMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk deleteAccount(@RequestBody ProductFullReduction productFullReduction) {
        boolean result = productFullReductionService.removeById(productFullReduction.getId());
        return result ? new RespOk(200, "删除成功") : new RespOk(200, "删除失败");
    }

    @PutMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk modifyAccount(@RequestBody ProductFullReduction productFullReduction) {
        boolean result = productFullReductionService.updateById(productFullReduction);
        return result ? new RespOk(200, "修改成功") : new RespOk(200, "修改失败");
    }

    @GetMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk findAccount(@RequestBody ProductFullReduction productFullReduction) {
        ProductFullReduction result = productFullReductionService.getById(productFullReduction);
        return new RespOk(200, "查询成功", result);
    }

    @GetMapping(value = "/list", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量查询产品满减", notes = "批量查询产品满减", responseContainer = "List", response = RespOk.class)
    public RespOk findAccountList(@RequestBody ProductFullReduction productFullReduction) {
        List<ProductFullReduction> productFullReductionList = productFullReductionService.list(new QueryWrapper<>(productFullReduction));
        return new RespOk(200, "查询成功", productFullReductionList);
    }
}
