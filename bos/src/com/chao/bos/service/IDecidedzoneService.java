package com.chao.bos.service;

import com.chao.bos.domain.Decidedzone;
import com.chao.bos.utils.PageBean;

public interface IDecidedzoneService {
	
	public void save(Decidedzone model, String[] subareaid);

	public void pageQuery(PageBean pageBean);
}
