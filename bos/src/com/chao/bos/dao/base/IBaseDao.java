package com.chao.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import com.chao.bos.utils.PageBean;

/**
 * 抽取持久层通用方法
 * @author chao
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	//提供通用修改方法
	public void executeUpdate(String queryName,Object ...objects);
	public void pageQuery(PageBean pageBean);
}
