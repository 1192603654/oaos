package com.ht.service.edu;

import java.util.List;

import com.ht.vo.edu.DBacke;
import com.ht.vo.edu.Faculymff;
import com.ht.vo.edu.Object;
import com.ht.vo.edu.Objectimg;
import com.ht.vo.edu.Tra;

public interface eduemp {
	//object�γ̱�
	public List objectfindall();//object�б�
	public List onjectcurr(int currpage,int pagecurr);//object��ҳ
	public int gettotalpage();//��ѯ������
	public void update(Object obj);//�޸�
	public void detele(Object obj);//ɾ��
	public void add(Object obj);//���
	//dbacke��Ա����
	public List dbackefindall();
	public DBacke dbackefind(Integer did);
	public List dbackefindall(int currpage,int PageCount);
	public void dbackeadd(DBacke db);
	public void dbackeup(DBacke db);
	public void dbackedele(DBacke db);
	public List dbackefind(int sta) ;
	public List dbackefind(int sta,int currpage,int PageCount);
	//ffaculymff������
	public List femplist();//���ҷ����еĽ�Ա
	public List fclasslist(String name);//���ҽ�Ա���ڵİ༶
	public void fsave(Faculymff facu);
	public List faculist(int currpage,int pagecurr,String name);//�鿴���ַ�ҳ
	public int facurow(String name) ;
	//tra���б�
	public  void traadd(Tra tra);
	public List tralist();
	public List tralist(int currpage,int PageCount);
	public Tra trafind(int tid);
	public void tradele(Tra tra);
	//objectimg
	public List objectimglist();
	public List objectimglist(int currpage,int PageCount);
	public List objectimglist(String kecheng,String ename,String classname,int currpage,int PageCount);
	public void objectimgupdate(Objectimg obj);
	public void objectimgdelete(Objectimg obj);
	public void objectimgadd(Objectimg obj);
	public Objectimg getobj(int kid);
}
