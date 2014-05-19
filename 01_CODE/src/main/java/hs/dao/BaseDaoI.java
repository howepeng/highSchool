package hs.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public T getById(Class<T> c, Serializable id);

	public List<T> find(String hql);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	public List<T> findSQL(String hql);

	public List<T> findSQL(String hql, int page, int rows);

	public List<T> findSQL(String hql, Map<String, Object> params);

	public List<T> findSQL(String hql, Map<String, Object> params, int page, int rows);

	public List<T> findByE(T t, String type);

	public List<T> findByE(T t, String type, int page, int rows);

	public List<T> findByE(T t, String type, int page, int rows, String sortName, String sortOrder);

	public Integer excuteHQL(String hql);

	public Integer excuteHQL(String hql, Map<String, Object> params);
}
