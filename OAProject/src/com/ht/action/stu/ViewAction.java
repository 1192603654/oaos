package com.ht.action.stu;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ht.action.BaseAction;
import com.ht.service.stu.ClassService;
import com.ht.service.stu.ViewSerivce;
import com.ht.vo.stu.Views;
import com.ht.vo.stu.Viewstu;

public class ViewAction extends BaseAction {
	HttpServletRequest request=ServletActionContext.getRequest();
	//ѧ������
	ViewSerivce vi= (ViewSerivce) getService("ViewService");
	 //�༶
 	ClassService cl= (ClassService) getService("classService"); 
 	private Views v;
 	private Viewstu stu;
 	
	private String str;//��ȡ��ǰҳ
	private int currpage=1;
	private int pagecount=10;
	private int totalRow=0;
	private int totalpage=0;
 
	public String query(){
		List listclass=cl.queryClass();//��ѯ���༶
		this.setValToRequest("listclass", listclass);
		return "to_add";
	}
	//��ӷ�����Ϣ
	public String addview(){	
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= stufind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
 		v.setSid(m.get("SNAME")+"");
		vi.addview(v);
		return "to_main";
	}
	//�鿴ѧ��������¼
	public String viewlist(){
		totalRow=vi.viewList().size();
		totalpage=(totalRow+pagecount-1)/pagecount;
		if(str!=null&&!"".equals(str)){
			currpage=Integer.parseInt(str);
		}
		if(currpage>totalpage){
			currpage=totalpage;
		}
		if(currpage<1){
			currpage=1;
		}
		
		List list=vi.fenye(currpage, pagecount);
		request.setAttribute("list", list);
		request.setAttribute("currpage",currpage );
		request.setAttribute("pagecount",pagecount );
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("totalpage", totalpage);
		return "to_viewlist";
	}
	 //�鿴��ѧ�����ύ�ķ���
	public String cont(){
		
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= stufind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
 		totalRow=vi.viewList().size();
		System.out.println("----------"+m.get("SNAME")+"");
		totalpage=(totalRow+pagecount-1)/pagecount;
		if(str!=null&&!"".equals(str)){
			currpage=Integer.parseInt(str);
		}
		if(currpage>totalpage){
			currpage=totalpage;
		}
		if(currpage<1){
			currpage=1;
		}
		List li=vi.viewstu(m.get("SNAME")+"", currpage, pagecount);
		request.setAttribute("view", li);
		request.setAttribute("currpage", currpage);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("totalpage", totalpage);
		return "to_viewstu";
	}
	//����˲鿴
	public String lookview(){
		Views vid=vi.Byid(v.getVid());
		request.getSession().setAttribute("viewstu", vid);
		String sid=request.getParameter("sid");
		try {
			sid= new String(sid.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "to_stuview";
	}
	//��ѯ������Ϣ
	public String contview(){
		Views vid=vi.Byid(v.getVid());
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= stufind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
		List stulist=vi.contview(m.get("SNAME")+"");//��ѯѧ���ظ���Ϣ
		List lists=vi.teachcont(m.get("SNAME")+"");//��ѯ��ʦ�ظ���Ϣ
		request.getSession().setAttribute("viewstu", vid);
		request.setAttribute("stucont", stulist);
		request.setAttribute("teachcont", lists);
		return "to_viewcont";
	}
	//����˷��ͻص��˷���
	public String fasong(){
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= stufind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
		List stulist=vi.contview(m.get("SNAME")+"");//��ѯѧ���ظ���Ϣ
		List lists=vi.teachcont(m.get("SNAME")+"");//��ѯ��ʦ�ظ���Ϣ
		request.setAttribute("stucont", stulist);
		request.setAttribute("teachcont", lists);
		return "to_viewstucont";
		
	}
	//�ظ���Ϣ
	public String viewhui(){
		int eid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+"");
 		System.out.println("eid��ֵ��"+eid);
 		List list= stufind(eid);
 		Map m=(Map) list.get(0);
 		request.setAttribute("eid", eid);
 		//��ȡ����½�û���name
 		stu.setSid(m.get("SNAME")+"");
 		Date time=new Date();
 		stu.setStime(time);
 		vi.addviews(stu);
		return fasong();
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
	public Views getV() {
		return v;
	}
	public void setV(Views v) {
		this.v = v;
	}
	public Viewstu getStu() {
		return stu;
	}
	public void setStu(Viewstu stu) {
		this.stu = stu;
	}
}
