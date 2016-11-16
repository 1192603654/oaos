package com.ht.service.mon;

import java.util.List;

import com.ht.vo.mon.Ava;
import com.ht.vo.mon.Computer;
import com.ht.vo.mon.Getpays;
import com.ht.vo.mon.Mon;
import com.ht.vo.mon.Pay;
import com.ht.vo.mon.Pays;

public interface monservice {
	//ѧ���շѹ���
	public List avalist();
	public List avalist(int xueqi,String stuname,String classname,int pagecount,int currpage);
	public void avaadd(Ava ava);
	public List avastulist();
	public List avaclasslist();
	public void avadele(Ava ava);
	//�������
	public List monlist();
	public List monlist(int pagecount,int currpage) ;
	public void monadd(Mon mon);
	public void monupd(Mon mon);
	public void mondele(Mon mon);
	//�������ù���
	public List comlit();
	public List comlit(int pagecount,int currpage);
	public void comupd(Computer com);
	public void comadd(Computer com);
	public void comdele(Computer com);
	public Computer getcom(Integer comid);
	//֧������
	public List paylist();
	public List paylist(int pagecount,int currpage);
	public void payupd(Pay p);
	public void paydele(Pay p);
	public void payadd(Pay p);
	//���ʹ���
	public List payslst();
	public void paysadd(Pays p);
	public void paysdele(Pays p);
	public void paysupd(Pays p);
	public Pays getpays(Integer paysid);
	public int findleave(int sid);//�����������
	public int findworkcheckdesc(int sid);//����ȱ������
	//���Ź���
	public void getpaysupd(Getpays p);//�޸Ĺ���
	public Getpays findgetpays(Integer pid);
	public List getpayslist();//��ѯ���й���
	public List getpayslist(String name,String data);//������ѯ
	public void addgetpays(Getpays p);//��ӹ���
	public void delegetpays();//��չ��ʱ�
}
