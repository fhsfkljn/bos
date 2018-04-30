package com.chao.bos.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chao.bos.crm.CustomerService;
import com.chao.bos.dao.IDecidedzoneDao;
import com.chao.bos.dao.INoticebillDao;
import com.chao.bos.dao.IWorkbillDao;
import com.chao.bos.domain.Decidedzone;
import com.chao.bos.domain.Noticebill;
import com.chao.bos.domain.Staff;
import com.chao.bos.domain.Workbill;
import com.chao.bos.service.INoticebillService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService{

	@Resource
	private INoticebillDao noticebillDao;
	@Resource
	private CustomerService proxy;
	@Resource
	private IDecidedzoneDao decidedzoneDao;
	@Resource
	private IWorkbillDao workbillDao;
	/**
	 * ����ҵ��֪ͨ���������Զ��ֵ�
	 * @return
	 */
	public void save(Noticebill model) {
		noticebillDao.save(model);//�־ö���
		//��ȡȡ����ַ
		String pickaddress = model.getPickaddress();
		//����ȡ����ַ��ѯ����id---��crm�����ѯ 
		String dId = proxy.findDecidedzoneIdByPickaddress(pickaddress);
		if(dId != null){
			//��ѯ������id�������Զ��ֵ�
			Decidedzone decidedzone = decidedzoneDao.findById(dId);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);//ҵ��֪ͨ������ƥ�䵽��ȡ��Ա
			model.setOrdertype("�Զ�");//�ֵ�����
			//��ҪΪȡ��Ա����һ������
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//׷������
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//����������ʱ��
			workbill.setNoticebill(model);//��������ҵ��֪ͨ��
			workbill.setPickstate("δȡ��");//ȡ��״̬
			workbill.setRemark(model.getRemark());//��ע
			workbill.setStaff(staff);//��������ȡ��Ա
			workbill.setType("�µ�");
			
			workbillDao.save(workbill);//���湤��
			
			//���ö���ƽ̨���񣬸�ȡ��Ա���Ͷ���
		}else{
			//û�в�ѯ������id��תΪ�˹��ֵ�
			model.setOrdertype("�˹�");
		}
	}
	
}
