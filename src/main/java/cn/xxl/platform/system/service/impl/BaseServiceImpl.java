package cn.xxl.platform.system.service.impl;

import cn.xxl.platform.system.dao.BaseDao;
import cn.xxl.platform.system.entity.BaseEntity;
import cn.xxl.platform.system.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiaoxiaolong
 * @since 2023/5/5
 */
@Service
public class BaseServiceImpl<D extends BaseDao<T>, T extends BaseEntity> implements BaseService<T> {

    @Resource
    private D dao;

    @Override
    public T findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public void updateById(T entity) {
        dao.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
