package com.ht.service.stu;

import java.util.List;

import com.ht.vo.stu.Stuleve;

public interface StuleveService {
	public boolean addleve(Stuleve leve);
	public List query(String classid);
	public boolean update(Stuleve stu);//�޸�״̬
	public Stuleve Byid(int leveid);//��ȡID
	public List teachlist(String uname);//��ʦ����
	public List teaches();//����������
	public List dept();
	public boolean del(Stuleve stu);
	public List myleve(String uname);//�鿴�Լ��������
	public List levefy(String uname,int currpage,int pagecount);
}
