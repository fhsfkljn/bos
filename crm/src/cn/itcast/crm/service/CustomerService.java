package cn.itcast.crm.service;

import java.util.List;

import cn.itcast.crm.domain.Customer;

// �ͻ�����ӿ� 
public interface CustomerService {
	// δ���������ͻ�
	public List<Customer> findnoassociationCustomers();

	// ��ѯ�Ѿ�����ָ�������Ŀͻ�
	public List<Customer> findhasassociationCustomers(String decidedZoneId);

	// ��δ���������ͻ�������������
	public void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId);
	
	//�����ֻ��Ų�ѯ�ͻ���Ϣ
	public Customer findCustomerByPhonenumber(String phonenumber);
	
	//����ȡ����ַ��ѯ����id
	public String findDecidedzoneIdByPickaddress(String address);
}
