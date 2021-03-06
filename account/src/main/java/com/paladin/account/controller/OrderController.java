package com.paladin.account.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paladin.account.entity.Order;
import com.paladin.account.resp.RespOk;
import com.paladin.account.service.IOrderService;
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
 * 订单表 前端控制器
 * </p>
 *
 * @author paladin
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	public static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Resource
	private IOrderService orderService;

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({@ApiResponse(code = 200, message = "添加成功"), @ApiResponse(code = 400, message = "请求错误"),
			@ApiResponse(code = 403, message = "请求被拒绝"), @ApiResponse(code = 404, message = "请求路径不存在"),
			@ApiResponse(code = 500, message = "服务器内部错误")})
//    @ApiImplicitParams({@ApiImplicitParam})
	@ApiOperation(value = "创建订单", notes = "创建订单", response = RespOk.class)
	public RespOk generateOrder(@RequestBody Order order) {
		boolean result = orderService.save(order);
		return result ? new RespOk(200, "添加成功") : new RespOk(200, "添加失败");
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RespOk findOrderDetail(@RequestBody Order order) {
		Order result = orderService.getById(order);
		return new RespOk(200, "查询成功", result);
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
			MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "批量查询订单", notes = "批量查询订单", responseContainer = "List", response = RespOk.class)
	public RespOk findOrderList(@RequestBody Order order) {
		List<Order> orderList = orderService.list(new QueryWrapper<>(order));
		return new RespOk(200, "查询成功", orderList);
	}

}
