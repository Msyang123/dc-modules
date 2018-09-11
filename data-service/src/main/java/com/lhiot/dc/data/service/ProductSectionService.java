package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductSection;
import com.lhiot.dc.data.domain.ProductStandard;
import com.lhiot.dc.data.domain.out.ProductSectionProductResult;
import com.lhiot.dc.data.domain.out.ProductSectionSubResult;
import com.lhiot.dc.data.mapper.ProductSectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* Description:商品板块服务类
* @author Limiaojun
* @date 2018/07/28
*/
@Service
@Transactional
public class ProductSectionService {

    private final ProductSectionMapper productSectionMapper;
    private final ProducStandardService producStandardService;
    private final StoreService storeService;
    private final ProductSaleService productSaleService;
    @Autowired
    public ProductSectionService(ProductSectionMapper productSectionMapper,
                                 ProducStandardService producStandardService,StoreService storeService,ProductSaleService productSaleService) {
        this.productSectionMapper = productSectionMapper;
        this.producStandardService=producStandardService;
        this.storeService=storeService;
        this.productSaleService = productSaleService;
    }

    /** 
    * Description:新增商品板块
    *  
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */  
    public int create(ProductSection productSection){
        return this.productSectionMapper.create(productSection);
    }

    /** 
    * Description:根据id修改商品板块
    *  
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */ 
    public int updateById(ProductSection productSection){
        return this.productSectionMapper.updateById(productSection);
    }

    /** 
    * Description:根据ids删除商品板块
    *  
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */ 
    public int deleteByIds(String ids){
        return this.productSectionMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找商品板块
    *  
    * @param id
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */ 
    public ProductSection selectById(Long id){
        return this.productSectionMapper.selectById(id);
    }

    /** 
    * Description: 查询商品板块总记录数
    *  
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */  
    public long count(ProductSection productSection){
        return this.productSectionMapper.pageProductSectionCounts(productSection);
    }
    
    /** 
    * Description: 查询商品板块分页列表
    *  
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */  
    public PagerResultObject<ProductSection> pageList(ProductSection productSection) {
       long total = 0;
       if (productSection.getRows() != null && productSection.getRows() > 0) {
           total = this.count(productSection);
       }
       return PagerResultObject.of(productSection, total,
              this.productSectionMapper.pageProductSections(productSection));
    }

    public ProductSectionSubResult findSubByPositionName(String positionName, String applyType,String storeId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("positionName",positionName);
        map.put("applyType",applyType);
        ProductSection productSection = productSectionMapper.findByPositionName(map);
        ProductSectionSubResult productSectionResult = new ProductSectionSubResult();
        com.leon.microx.util.BeanUtils.of(productSectionResult).populate(productSection);
        //子节点
        if(null != productSection && null != productSection.getId()){
            List<String> parentIds = new ArrayList<>();
            parentIds.add(productSection.getId()+"");
            List<ProductSection> productSections = productSectionMapper.findByParentId(parentIds);
            for(ProductSection subSection:productSections){
                ProductStandard productStandard = new ProductStandard();
                productStandard.setApplyType(applyType);
                productStandard.setProductSectionId(subSection.getId());
                productStandard.setRows(null);
                List<ProductStandard> list = producStandardService.pageList(productStandard).getRows();
                subSection.setProductStandardList(list);
                /*Store store = storeService.selectById(Long.valueOf(storeId));
                if(null != store){
                    producStandardService.productInv(store.getStoreCode(),list);
                }*/
                productSaleService.setSaleCount(list,applyType);
            }
            productSectionResult.setSubSectionList(productSections);
        }
        return productSectionResult;
    }
    public ProductSectionProductResult findProductByPositionName(String positionName, String applyType,String storeId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("positionName",positionName);
        map.put("applyType",applyType);
        ProductSection productSection = productSectionMapper.findByPositionName(map);
        if(null == productSection){
            return null;
        }else{
            ProductSectionProductResult productSectionResult = new ProductSectionProductResult();
            com.leon.microx.util.BeanUtils.of(productSectionResult).populate(productSection);
            ProductStandard productStandard = new ProductStandard();
            productStandard.setApplyType(applyType);
            productStandard.setProductSectionId(productSectionResult.getId());
            productStandard.setRows(null);
            List<ProductStandard> list = producStandardService.pageList(productStandard).getRows();
            productSectionResult.setProductStandardList(list);
            /*Store store = storeService.selectById(Long.valueOf(storeId));
            if(null != store){
                producStandardService.productInv(store.getStoreCode(),list);
            }*/
            productSaleService.setSaleCount(list,applyType);
            return productSectionResult;
        }
    }


    public List<ProductSectionProductResult> sectionTree(ProductSection productSection) {
        return productSectionMapper.sectionTree(productSection);
    }

    public boolean canDeleteGroup(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        List<ProductSection> list = productSectionMapper.findByParentId(idList);
        return null != list && list.size()>0;
    }
}

