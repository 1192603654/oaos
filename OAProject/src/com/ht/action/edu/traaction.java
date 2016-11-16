package com.ht.action.edu;

import java.util.List;

import com.ht.action.BaseAction;
import com.ht.common.find;
import com.ht.service.edu.eduemp;
import com.ht.vo.edu.Tra;

public class traaction  extends BaseAction{
	private Tra tra;
	eduemp imp=(eduemp) getService("eduservice");
	public Tra getTra() {
		return tra;
	}
	public void setTra(Tra tra) {
		this.tra = tra;
	}
	public String totra(){
		find f=new find();
		List list=f.enamefind();
		setValToRequest("list", list);
		return "totra";
	}
	//������ֻ�
	public String add(){
		imp.traadd(tra);
		return "todefault";
	}
	int pagecount;
	int currpage;
	int totalpage;
	int totalrow;
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getCurrpage() {
		return currpage;
	}
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTotalrow() {
		return totalrow;
	}
	public void setTotalrow(int totalrow) {
		this.totalrow = totalrow;
	}
	//���ֻ�鿴���
	public String tralist(){
		List li=imp.tralist();
		pagecount=10;//ÿҳ����
		 totalpage=li.size();//������
		 totalrow=(totalpage+pagecount-1)/pagecount;//��ҳ��
		if(currpage<1){
			currpage=1;
		}
		if(currpage>=totalrow){
			currpage=totalrow;
		}
		List list=imp.tralist(currpage,pagecount);
		setValToRequest("list", list);
		return "totralist";
	}
	//���ֻ�鿴����
	public String  tradetails(){
		tra=imp.trafind(tra.getTid());
		return "totradetails";
	}
	//���ֻ�ɾ��
	public String  tradele(){
		imp.tradele(tra);
		return tralist();
	}
}
