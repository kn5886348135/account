package com.paladin.account.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paladin.account.entity.OrderReturnApply;
import com.paladin.account.resp.RespOk;
import com.paladin.account.service.IOrderReturnApplyService;
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
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author paladin
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/order/return/apply")
public class OrderReturnApplyController {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderReturnApplyController.class);

    @Resource
    private IOrderReturnApplyService orderReturnApplyService;

    @PostMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "添加成功"), @ApiResponse(code = 400, message = "请求错误"),
            @ApiResponse(code = 403, message = "请求被拒绝"), @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")})
//    @ApiImplicitParams({@ApiImplicitParam})
    @ApiOperation(value = "添加订单退货申请", notes = "添加订单退货申请", response = RespOk.class)
    public RespOk addOrderReturnApply(@RequestBody OrderReturnApply orderReturnApply) {
        boolean result = orderReturnApplyService.save(orderReturnApply);
        return result ? new RespOk(200, "添加成功") : new RespOk(200, "添加失败");
    }

    @DeleteMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk deleteAccount(@RequestBody OrderReturnApply orderReturnApply) {
        boolean result = orderReturnApplyService.removeById(orderReturnApply.getId());
        return result ? new RespOk(200, "删除成功") : new RespOk(200, "删除失败");
    }

    @PutMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk modifyAccount(@RequestBody OrderReturnApply orderReturnApply) {
        boolean result = orderReturnApplyService.updateById(orderReturnApply);
        return result ? new RespOk(200, "修改成功") : new RespOk(200, "修改失败");
    }

    @GetMapping(value = "/", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespOk findAccount(@RequestBody OrderReturnApply orderReturnApply) {
        OrderReturnApply result = orderReturnApplyService.getById(orderReturnApply);
        return new RespOk(200, "查询成功", result);
    }

    @GetMapping(value = "/list", MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量查询订单退货申请", notes = "批量查询订单退货申请", responseContainer = "List", response = RespOk.class)
    public RespOk findAccountList(@RequestBody OrderReturnApply orderReturnApply) {
        List<OrderReturnApply> orderReturnApplyList = orderReturnApplyService.list(new QueryWrapper<>(orderReturnApply));
        return new RespOk(200, "查询成功", orderReturnApplyList);
    }
}
