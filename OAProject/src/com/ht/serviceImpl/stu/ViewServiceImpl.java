package com.ht.serviceImpl.stu;

import java.util.List;

import com.ht.common.Hibrenate_Dao;
import com.ht.service.stu.ViewSerivce;
import com.ht.vo.stu.Views;
import com.ht.vo.stu.Viewstu;

public class ViewServiceImpl extends Hibrenate_Dao implements ViewSerivce{

	//ѧ����ӷ���
	public boolean addview(Views vs) {
		// TODO Auto-generated method stub
		return addObj(vs);
	}

	//��ѯѧ������
	public List viewList() {
		// TODO Auto-generated method stub
		return listbysql("select * from Views");
	}

	//��ҳ
	public List fenye(int currpage, int pagecount) {
		// TODO Auto-generated method stub
		return pagelistbysql("select * from Views", (currpage-1)*pagecount, pagecount);
	}
	//��ȡid
	public Views Byid(int vid) {
		Views v= (Views) getObj(Views.class, vid);
		return v;
	}

	//��ѯ��ѧ���ύ�ķ���
	public List viewstu(String uname) {
		// TODO Auto-generated method stub
		return listbysql("select * from Views where sid='"+uname+"'");
	}

	//��ҳ
	public List viewstu(String uname, int currpage, int pagecount) {
		
		return pagelistbysql("select * from Views where sid='"+uname+"'", (currpage-1)*pagecount, pagecount);
	}

	//��ѯ�ظ���Ϣ
	public List viewstucont(String uname,int currpage,int pagecount) {
		// TODO Auto-generated method stub
		return pagelistbysql("select * from viewstu s,viewteach t where s.sid=t.sid and t.sid='"+uname+"'", (currpage-1)*pagecount, pagecount);
	}

	//�ظ���Ϣ
	public boolean addviews(Viewstu stu) {
		
		return addObj(stu);
	}
	//��ѯѧ���ظ���Ϣ
	public List contview(String uname) {
		// TODO Auto-generated method stub
		return listbysql("select * from viewstu s where s.sid='"+uname+"' order by s.stime desc");
	}

	//��ѯ��ʦ�ظ���Ϣ
	public List teachcont(String uname) {
		// TODO Auto-generated method stub
		return listbysql("select * from viewteach t where t.sid='"+uname+"' order by t.times desc");
	}
	//��Ϣ�Ĵ�С
	public List contentview(String uname) {
		// TODO Auto-generated method stub
		return listbysql("select * from viewstu s,viewteach t where s.sid=t.sid and t.sid='"+uname+"'");
	}
}
