package com.ht.service.stu;

import java.util.List;

import com.ht.vo.stu.Scoreweb;
import com.ht.vo.stu.Stuscore;

public interface WebService {
	public boolean addweb(Scoreweb web);//�������
	public List weblist();
	public List Bystu(String classid);//���ݰ༶��ѯѧ��
	public boolean addstucore(Stuscore stu);//����ӱ�����
	public List Byliststu();//��ѯ�ӱ�����
	public Stuscore Byid(int stuid);
	public boolean delstucore(Stuscore stu);
	public List stuweb(String uname);//����ѧ����������
	public List webBylist(int currpage,int pagecount);
}
