package com.ht.service.stu;


import java.util.List;

import com.ht.vo.stu.Views;
import com.ht.vo.stu.Viewstu;

public interface ViewSerivce {
	public boolean addview(Views vs);//���ѧ������
	public List viewList();//��ѯѧ������
	public List fenye(int currpage,int pagecount);//��ҳ
	public Views Byid(int vid);//��ȡid
	public List viewstu(String uname);//����ID��ѯ���ύ�ķ���
	public List viewstu(String uname,int currpage,int pagecount);//��ҳ
	public List viewstucont(String uname,int currpage,int pagecount);//��ѯ�ظ�����
	public boolean addviews(Viewstu stu);//�ظ���Ϣ
	public List contview(String uname);//��ѯѧ���ظ���Ϣ
	public List teachcont(String uname);//��ѯ��ʦ�ظ���Ϣ
	public List contentview(String uname);//������Ϣ�Ĵ�С
	
}
