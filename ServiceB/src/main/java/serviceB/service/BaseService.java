package serviceB.service;


import java.io.Serializable;
import java.util.List;
import java.util.Map;


/** 
* <p>类功能说明: 基本服务封装</p>
* @version V1.0
*/

public  interface BaseService<T> {
	List<T> selectList(Map<String, Object> paramMap);

	long update(T entity);

	void insertSelective(T entity);

	void delete(T entiy);



	public T queryById(Serializable id) ;

	public T queryOne(Map<String, Object> map) ;

	public List<T> queryList(Map<String, Object> map, int pageNum, int pageSize) throws Exception ;

//	public PageInfo<List<T>> queryPage(Map<String,Object> map,int pageNum, int pageSize) throws Exception ;

	public void add(T entity) throws Exception ;

	public void addSelective(T entity) throws Exception;

	public int deleteById(Serializable id) throws Exception ;

	public int deleteFalse(Serializable id) throws Exception ;

	public int updateByIdSelective(T entity) throws Exception ;

	public int updateById(T entity) throws Exception ;

	public long selectCount(Map<String, Object> paramMap) throws Exception;

	public int updateInBatch(List<T> entityList) throws  Exception;

	public int insertInBatch(List<T> entityList) throws  Exception;

	public int deleteByPrimaryKeyInBatch(List<Serializable> idList);

}
