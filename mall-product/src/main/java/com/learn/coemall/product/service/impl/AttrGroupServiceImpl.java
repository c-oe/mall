package com.learn.coemall.product.service.impl;

import com.learn.coemall.product.service.AttrService;
import com.learn.coemall.product.vo.AttrGroupWithAttrsVo;
import com.learn.coemall.product.vo.SpuItemAttrGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.common.utils.PageUtils;
import com.learn.common.utils.Query;

import com.learn.coemall.product.dao.AttrGroupDao;
import com.learn.coemall.product.entity.AttrGroupEntity;
import com.learn.coemall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        //select * from pms_attr_group where catelog_id=? and (attr_group_id=key or attr_group_name like %key%)
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>();
        if(StringUtils.hasLength(key)){
            queryWrapper.and((obj)->{
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }

        if( catelogId == 0){
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    queryWrapper);
            return new PageUtils(page);
        }else {
            queryWrapper.eq("catelog_id",catelogId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params),
                    queryWrapper);
            return new PageUtils(page);
        }
    }

    /**
     * ????????????id???????????????????????????????????????????????????
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {

        //??????????????????
        List<AttrGroupEntity> attrGroupEntities = list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        //??????????????????
        return attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group,attrsVo);
            attrsVo.setAttrs(attrService.getRelationAttr(attrsVo.getAttrGroupId()));
            return attrsVo;
        }).collect(Collectors.toList());

    }

    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        //????????????spu?????????????????????????????????????????????????????????????????????????????????
        return baseMapper.getAttrGroupWithAttrsBySpuId(spuId,catalogId);
    }

}