package com.paladin.account.service.impl;

import com.paladin.account.entity.ProductCategoryAttributeRelation;
import com.paladin.account.mapper.ProductCategoryAttributeRelationMapper;
import com.paladin.account.service.IProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author paladin
 * @since 2020-10-07
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper,
		ProductCategoryAttributeRelation> implements IProductCategoryAttributeRelationService {

}
