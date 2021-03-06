package com.paladin.account.controller;


import com.paladin.account.entity.ProductCategory;
import com.paladin.account.resp.RespOk;
import com.paladin.account.service.IProductCategoryService;
import com.paladin.account.vo.ProductCategoryTreeVO;
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
 * 产品分类 前端控制器
 * </p>
 *
 * @author paladin
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/product/category")
public class ProductCategoryController {

	public static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryController.class);

	@Resource
	private IProductCategoryService productCategoryService;

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({@ApiResponse(code = 200, message = "添加成功"), @ApiResponse(code = 400, message = "请求错误"),
			@ApiResponse(code = 403, message = "请求被拒绝"), @ApiResponse(code = 404, message = "请求路径不存在"),
			@ApiResponse(code = 500, message = "服务器内部错误")})
//    @ApiImplicitParams({@ApiImplicitParam})
	@ApiOperation(value = "添加产品分类", notes = "添加产品分类", response = RespOk.class)
	public RespOk addProductCategory(@RequestBody ProductCategory productCategory) {
		boolean result = productCategoryService.save(productCategory);
		return result ? new RespOk(200, "添加成功") : new RespOk(200, "添加失败");
	}

	@ApiOperation(value = "删除产品分类", notes = "删除产品分类", response = RespOk.class)
	@DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
			MediaType.APPLICATION_JSON_VALUE)
	public RespOk deleteProductCategory(@RequestBody ProductCategory productCategory) {
		boolean result = productCategoryService.removeById(productCategory.getId());
		return result ? new RespOk(200, "删除成功") : new RespOk(200, "删除失败");
	}

	@ApiOperation(value = "修改产品分类", notes = "修改产品分类", response = RespOk.class)
	@PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RespOk modifyProductCategory(@RequestBody ProductCategory productCategory) {
		boolean result = productCategoryService.updateById(productCategory);
		return result ? new RespOk(200, "修改成功") : new RespOk(200, "修改失败");
	}

	@ApiOperation(value = "查询产品分类详情", notes = "查询产品分类详情", response = RespOk.class)
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RespOk findProductCategory(@RequestBody ProductCategory productCategory) {
		ProductCategory result = productCategoryService.getById(productCategory);
		return new RespOk(200, "查询成功", result);
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "批量查询产品分类", notes = "批量查询产品分类", responseContainer = "List", response = RespOk.class)
	public RespOk findProductCategoryList() {
		List<ProductCategoryTreeVO> productCategoryTreeVOList = productCategoryService.selectProductCategoryTree();
		return new RespOk(200, "查询成功", productCategoryTreeVOList);
	}
}
