package com.ht.service.stu;

import java.util.List;

import com.ht.vo.stu.Computers;

public interface ComService {
	//����ѧ������������Ϣ
	public boolean addCom(Computers com);
	//��ѯ����
	public List Comlist();
	//���ݰ༶������ʦ
	public List Teachquery(String classid);
	//���Ұ�����
	public List TeachesQuery(String classid);
	//�޸�״̬
	public boolean updStart(Computers com);
	//��ȡID
	public Computers ById(int cid);
	//���Һ��ڲ�������
	public List deptQuery();
	//���ڹ�����ȷ��
	public List deql();
	//�ο���ʦ����
	public List teach();
	//ɾ��
	public boolean del(Computers cid);
	//��ѯ�Լ������������
	public List mycomput(String uname);
	//��ҳ
	public List computfy(String uname,int currpage,int pagecount);
}
