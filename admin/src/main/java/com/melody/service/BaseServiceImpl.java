package com.melody.service;



import com.melody.dao.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;


/**
 * Created by liuyw on 2015/11/20.
 */
@Service
public class BaseServiceImpl<T> {
    @Autowired
    SequenceDao sequenceDao;

    BaseMapper<T> baseMapper;

    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }
    
    /**
     * 根据条件集合进行分页查询
     *
     * @param t           查询条件
     * @param currentPage 当前页数
     * @param pageSize    页面大小
     * @return 返回Pager对象
     */
    public Pager<T> queryPage(T t, Integer currentPage,
                              Integer pageSize) {

        int totalCount = getBaseMapper().count(t);//获取总条数

        Pager pager = new Pager(pageSize, totalCount, currentPage);

        int offset = (currentPage - 1) * pageSize;

        if (offset > totalCount) {
            offset = pager.getPageCount() - 1 * pageSize;
        }

        List<T> list = getBaseMapper().queryPage(t, offset, pageSize);
        pager.setDataList(list);

        return pager;
    }


    public Long getNextSequence(String tabelName) {
        sequenceDao.updateSequence(tabelName);
        int current_value = sequenceDao.getNextSequence(tabelName);
        return (long)current_value;
    }

}
