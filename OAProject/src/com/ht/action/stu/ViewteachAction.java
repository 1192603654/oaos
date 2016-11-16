package com.ht.action.stu;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ht.action.BaseAction;
import com.ht.service.stu.ViewteachService;
import com.ht.vo.stu.Views;
import com.ht.vo.stu.Viewteach;

public class ViewteachAction extends BaseAction{
	HttpServletRequest request=ServletActionContext.getRequest();
	ViewteachService tea= (ViewteachService) getService("TeachViewService");
	private Viewteach t;
	private Views s;
	private String str;//��ȡ��ǰҳ
	private int currpage=1;
	private int pagecount=3;
	private int totalRow=0;
	private int totalpage=0;
	
	//��ӷ����ظ���Ϣ
	public String addteachview(){
		String sid=request.getParameter("sid");
		System.out.println("sid��ֵ��===="+sid);
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= eidfind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
 		t.setTeach(m.get("ENAME")+"");
 		System.out.println("��½���û���"+m.get("ENAME")+"");
 		t.setSid(sid);
		//��ȡ��ǰʱ��
 		
 		Date time=new Date();
 		System.out.println(time.toLocaleString());
		t.setTimes(time);
		tea.addteachview(t);
		return fasong();
	}
	//����˷��ͻص��˷���
	public String fasong(){
		String sid=request.getParameter("sid");
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= eidfind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
 		try {
			
			List stulist=tea.contview(sid);//��ѯѧ���ظ���Ϣ
			List lists=tea.teachcont(m.get("ENAME")+"",sid);//��ѯ��ʦ�ظ���Ϣ
			System.out.println("stulist�Ĵ�С��"+stulist);
			request.setAttribute("stucont", stulist);
			request.setAttribute("teachcont", lists);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "to_viewstucont";
		
	}
	
	//��ѯ������Ϣ
	public String contview(){
		String sid=request.getParameter("sid");
		Views vid=tea.Byid(s.getVid());
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= eidfind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
 		try {
			sid= new String(sid.getBytes("iso-8859-1"),"utf-8");
			List stulist=tea.contview(sid);//��ѯѧ���ظ���Ϣ
			List lists=tea.teachcont(m.get("ENAME")+"",sid);//��ѯ��ʦ�ظ���Ϣ
			System.out.println("stulist�Ĵ�С��"+stulist);
			request.setAttribute("stucont", stulist);
			request.setAttribute("teachcont", lists);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("viewstu", vid);
		return "to_cont";
	}
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getCurrpage() {
		return currpage;
	}
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	
	public Viewteach getT() {
		return t;
	}
	public void setT(Viewteach t) {
		this.t = t;
	}

	public Views getS() {
		return s;
	}
	public void setS(Views s) {
		this.s = s;
	}
}
