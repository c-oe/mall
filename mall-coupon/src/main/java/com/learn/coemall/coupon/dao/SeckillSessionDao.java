package com.learn.coemall.coupon.dao;

import com.learn.coemall.coupon.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 *
 * @author coffee
 * @email coffee@gmail.com
 * @date 2021-05-31 15:19:05
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {

}
