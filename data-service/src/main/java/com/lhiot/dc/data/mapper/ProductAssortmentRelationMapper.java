package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.ProductAssortmentRelation;
import com.lhiot.dc.data.domain.ProductStandardResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:套餐商品关联Mapper类
* @author yijun
* @date 2018-07-21
*/
@Mapper
public interface ProductAssortmentRelationMapper {

		/**
		 * Description:新增套餐商品关联
		 *
		 * @param productAssortmentRelation
		 * @return
		 * @author yijun
		 * @date 2018/07/24 09:55:48
		 */
		int create(ProductAssortmentRelation productAssortmentRelation);

		/**
		 * Description:根据id修改套餐商品关联
		 *
		 * @param productAssortmentRelation
		 * @return
		 * @author yijun
		 * @date 2018/07/24 09:55:48
		 */
		int updateById(ProductAssortmentRelation productAssortmentRelation);

		/**
		 * Description:根据ids删除套餐商品关联
		 *
		 * @param ids
		 * @return
		 * @author yijun
		 * @date 2018/07/24 09:55:48
		 */
		int deleteByIds(List<String> ids);

		/**
		 * Description:根据id查找套餐商品关联
		 *
		 * @param id
		 * @return
		 * @author yijun
		 * @date 2018/07/24 09:55:48
		 */
		ProductAssortmentRelation selectById(Long id);

		/**
		 * Description:查询套餐商品关联列表
		 *
		 * @param productAssortmentRelation
		 * @return
		 * @author yijun
		 * @date 2018/07/24 09:55:48
		 */
		List<ProductAssortmentRelation> pageProductAssortmentRelations(ProductAssortmentRelation productAssortmentRelation);


		/**
		 * Description: 查询套餐商品关联总记录数
		 *
		 * @param productAssortmentRelation
		 * @return
		 * @author yijun
		 * @date 2018/07/24 09:55:48
		 */
		long pageProductAssortmentRelationCounts(ProductAssortmentRelation productAssortmentRelation);

	
	//根据套餐id查询套餐商品
	List<ProductStandardResult> findProductByAssortmentId(Long assortmentId);
}
