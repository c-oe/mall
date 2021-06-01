package com.learn.coemall.coupon.dao;

import com.learn.coemall.coupon.entity.SkuLadderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品阶梯价格
 * 
 * @author coffee
 * @email coffee@gmail.com
 * @date 2021-05-31 15:19:05
 */
@Mapper
public interface SkuLadderDao extends BaseMapper<SkuLadderEntity> {
	
}