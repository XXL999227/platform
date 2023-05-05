package cn.xxl.platform.system.service;

import cn.xxl.platform.system.entity.BaseEntity;

/**
 * 基础服务，所有Service的父接口
 *
 * @author xiaoxiaolong
 * @since 2023/05/05
 */
public interface BaseService<T extends BaseEntity> {

    T findById(Long id);

    void save(T entity);

    void updateById(T entity);

    void deleteById(Long id);

}
