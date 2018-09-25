package com.lhiot.dc.base.common.util;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description: 公共工具类
 *
 * @author Limiaojun
 * @date: 2018-05-31 17:52:20
 * @version 1.0
 * @since JDK 1.8
 */
public class CommonUtils {

    /**
     * Description:获取集合第一个元素
     *
     * @param list
     * @return
     * @author Limiaojun
     * @date 2018-05-31 17:53
     */
    public static Object getCollectionsFirst(List<?> list) {
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

}
