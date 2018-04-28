package com.chao.bos.dao;

import java.util.List;

import com.chao.bos.dao.base.IBaseDao;
import com.chao.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region>{
	public List<Region> findByQ(String q);
}
