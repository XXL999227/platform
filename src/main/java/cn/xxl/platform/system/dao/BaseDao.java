package cn.xxl.platform.system.dao;

import cn.xxl.platform.system.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaoxiaolong
 * @since 2023/4/23
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Integer> {

    @Override
    @Query(value = "select t from #{#entityName} t where t.id = ?1 and t.isDeleted = 0")
    @Transactional(readOnly = true)
    Optional<T> findById(Integer integer);

    @Override
    @Query(value = "select t from #{#entityName} t where t.isDeleted = 0")
    @Transactional(readOnly = true)
    List<T> findAll();

    /**
     * 逻辑删除通过id
     *
     * @param id id
     */
    @Query(value = "update #{#entityName} t set t.isDeleted = 1 where t.id = ?1")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void logicDeleteById(Integer id);
}
