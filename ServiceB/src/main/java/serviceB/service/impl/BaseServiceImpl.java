package serviceB.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;
import serviceB.dao.BaseDao;
import serviceB.pojo.SuperVO;
import serviceB.service.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by :Guozhihua
 * Date： 2016/11/24.
 */
public abstract class BaseServiceImpl<T extends SuperVO> implements BaseService<T> {

    protected abstract BaseDao<T> getBaseDao();

    //默认的缓存有效期
    public int DEFAULT_TIME_OUT=60*60*2;
    public int DEFAULT_MAX_TIME_OUT=60*60*24;

    @Override
    public List<T> selectList(Map<String, Object> paramMap) {
        return getBaseDao().selectList(paramMap);
    }

    @Override
    @Transactional(readOnly = false)
    public long update(T entity) {
        return getBaseDao().update(entity);
    }


    @Override
    @Transactional(readOnly = false)
    public void insertSelective(T entity) {
        getBaseDao().insert(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(T entiy) {
        getBaseDao().delete(entiy);
    }




    @Override
    public T queryById(Serializable id) {
        return getBaseDao().selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void add(T entity) throws Exception {
        getBaseDao().insert(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteById(Serializable id) throws Exception {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteFalse(Serializable id) throws Exception {
        return getBaseDao().deleteByIdFalse(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByIdSelective(T entity) throws Exception {
        return getBaseDao().updateByPrimaryKeySelective(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateById(T entity) throws Exception {
        return getBaseDao().updateByPrimaryKey(entity);
    }

    @Override
    public List<T> queryList(Map<String, Object> map, int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return getBaseDao().selectList(map);
    }

    @Override
    public T queryOne(Map<String, Object> map) {
        return getBaseDao().selectOne(map);
    }

    @Override
    @Transactional(readOnly = false)
    public void addSelective(T entity) throws Exception {
        getBaseDao().insertSelective(entity);

    }

    @Override
    public long selectCount(Map<String, Object> paramMap) throws Exception {
        return getBaseDao().selectCount(paramMap);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateInBatch(List<T> entityList) throws Exception {
        return getBaseDao().updateInBatch(entityList);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertInBatch(List<T> entityList) throws Exception {
        return getBaseDao().insertInBatch(entityList);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteByPrimaryKeyInBatch(List<Serializable> idList) {
        return getBaseDao().deleteByPrimaryKeyInBatch(idList);
    }
}
