package com.ht.action.dept;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ht.action.BaseAction;
import com.ht.service.dept.WorkDoingService;
import com.ht.vo.emp.Workdoing;

public class WorkDoingAction extends BaseAction{
	private Workdoing wd;
	private List list;
	private int currpage=1;
	private int pagecount=8;
	private int totalpage=0;
	private int totalrow=0;
	WorkDoingService wds=(WorkDoingService) getService("wdoingservice");
	public String list(){
		totalrow=wds.sellist().size();
		totalpage=(totalrow+pagecount-1)/pagecount;
		//��ȡҳ�洫����ҳ��
		HttpServletRequest request= ServletActionContext.getRequest();
		String pages=request.getParameter("page");
		if(pages!=null&&!pages.equals("")){
		       currpage=Integer.parseInt(pages);
		}
		if(currpage<1){
		       currpage=1;
		}
		if(currpage>=totalpage){
		       currpage=totalpage;
		}  
		List list= wds.sellists(currpage,pagecount);
		setValToRequest("list", list);
		List emplist= empfind();
		setValToRequest("emplist", emplist);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currpage", currpage);
		request.setAttribute("totalrow", totalrow);
		return "tolist";
	}
	public String add(){
		wds.addwork(wd);	
		return list();
	}
	public String del(){  //ɾ��
		Workdoing w= wds.getwork(wd.getWid());
		wds.delwork(w);
		return list();
	}
	
	public String toupd(){
		List list= empfind();  //Ա���б�
		setValToRequest("emplist", list);
		List blist=selbzr();
		setValToRequest("blist", blist); //������
		Workdoing w= wds.getwork(wd.getWid());
		setValToRequest("wd", w);
		return "toupd";
	}
	public String upd(){
		wds.updwork(wd);
		return list();
	}
	public String seltel(){
		list= telfind();
		return "success";
	}
	public String seldata(){   //Ա���б�
		List emplist= empfind();
		setValToRequest("emplist", emplist);
		List blist=selbzr();
		setValToRequest("blist", blist);
		return "tojsp";
	}
	public Workdoing getWd() {
		return wd;
	}
	public void setWd(Workdoing wd) {
		this.wd = wd;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
