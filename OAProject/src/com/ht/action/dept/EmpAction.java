package com.ht.action.dept;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.transaction.JOnASTransactionManagerLookup;

import com.ht.action.BaseAction;
import com.ht.service.dept.DeptService;
import com.ht.service.dept.EmpService;
import com.ht.vo.emp.Emp;
import com.ht.vo.emp.Family;
import com.ht.vo.emp.Jobs;
import com.ht.vo.emp.Patrol;

public class EmpAction extends BaseAction{
	private Emp emp;
	private Jobs jobs;
	private Family family;
	private Object eid;
	private Object fid;
	private Object jid;
	private int currpage=1;
	private int pagecount=8;
	private int totalpage=0;
	private int totalrow=0;
	//��ȡ�ļ�
	private File file;
	//��ȡ�ļ�����
	private String fileFileName;
	//��ȡ�ļ�����
	//�ļ��洢·��     System.getProperty("file.separator"):�Զ�����ϵͳ�̷�    window: \  linux:/  
	private String savePath=ServletActionContext.getRequest().getRealPath("/img")+ "\\";
	EmpService es=(EmpService) getService("empservice");
	DeptService ds=(DeptService) getService("deptservice");
	public String seldept(){
		List list= ds.seldept();
		System.out.println("size::"+list.size());
		setValToRequest("list", list);
		return "toempinfo";
	}
	public String list(){
		totalrow=es.emplists().size();
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
		List list= es.emplist(currpage,pagecount);
		setValToRequest("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("currpage", currpage);
		request.setAttribute("totalrow", totalrow);
		return "toemplist";
	}
	
	
	public String add(){
		try {
			FileOutputStream fos = new FileOutputStream(this.savePath + this.fileFileName);
			FileInputStream fis = new FileInputStream(this.file);
			System.out.println("files++++"+this.file);
			System.out.println(fileFileName);
			emp.setEimg(fileFileName);
			byte[] bs = new byte[1024];
			int real = fis.read(bs);
			while(real > 0){
				fos.write(bs, 0, real);
				real = fis.read(bs);
			}
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 Random  random=new Random();
		 Double a=random.nextDouble()*1000000;
		 System.out.println("  a  "+a);
		 String  ename=a+"";
		 String eename=ename.substring(0, 6);
		 System.out.println("�˺�Ϊ"+eename);
		 emp.setEename(eename);

		boolean bln= es.addemp(emp);
		if(bln){ //����ɹ�
			List list=es.geteid(emp.getEqq());
			 //��ѯ��eid
			for (int i = 0; i < list.size(); i++) {
				Map m=(HashMap)list.get(i);
				eid=m.get("EID");
			}
			//������
			 String jcom[]=(String []) getValFromTable("jobs.jcompany");
			 String jwork[]=(String []) getValFromTable("jobs.jwork");
			 String jreason[]=(String []) getValFromTable("jobs.jreason");
			 String jmoney[]=(String []) getValFromTable("jobs.jmoney");
			 
			 //��Ա��
			 String fname[]=(String []) getValFromTable("family.fname");
			 String frelation[]=(String []) getValFromTable("family.frelation");
			 String fcompany[]=(String []) getValFromTable("family.fcompany");
			 String fjob[]=(String []) getValFromTable("family.fjob");
			 String ftel[]=(String []) getValFromTable("family.ftel");
			 String fbeizhu[]=(String []) getValFromTable("family.fbeizhu");
			 
			 
			 
			for (int i = 0; i < jcom.length; i++) {  //��ӹ�������
				Jobs j= new Jobs();
				if(jcom[i]!=null&&!jcom[i].equals("")){ //�жϲ���Ϊ��
					j.setEid(Integer.parseInt(eid+""));
					j.setJcompany(jcom[i]);
					j.setJmoney(jmoney[i]);
					j.setJreason(jreason[i]);
					j.setJwork(jwork[i]);
					es.addjobs(j);
				}
			}
			
			for (int i = 0; i < fname.length; i++) {//�����ϵ��
				Family f=new Family();
				if(fname[i]!=null&&!fname[i].equals("")){ //�жϲ���Ϊ��
					f.setEid(Integer.parseInt(eid+""));
					f.setFbeizhu(fbeizhu[i]);
					f.setFcompany(fcompany[i]);
					f.setFjob(fjob[i]);
					f.setFname(fname[i]);
					f.setFrelation(frelation[i]);
					f.setFtel(ftel[i]);
					es.addfamily(f);
				}
			}
		}
		return list();
	}
	// ���ɾ��del
	public String dels(){
		String str=ServletActionContext.getRequest().getParameter("dids");
		String strs[]=str.split(",");
		for (int i = 0; i < strs.length; i++) {
			boolean r=es.delemp(Integer.parseInt(strs[i]+""));//ɾ��Ա��
			if(r){
				boolean bln2= es.deljobs(Integer.parseInt(strs[i]+"")); //ɾ����������
				if(bln2){
					 es.delfamily(Integer.parseInt(strs[i]+"")); //ɾ����ϵ��
				 }
			} 
		}
		return list();
	}
	//ɾ��
	public String del(){
		 boolean bln=es.delemp(emp.getEid());  //ɾ��Ա��
		 if(bln){ 
			 boolean bln2= es.deljobs(emp.getEid()); //ɾ����������
			 if(bln2){
				 es.delfamily(emp.getEid()); //ɾ����ϵ��
			 }
		 }
		 return list();
	}
	//ȥ�޸�
	public String upd(){
		List list= ds.seldept(); //����list
		setValToRequest("list", list);
		Emp e= es.selemp(emp.getEid());
		List flist= es.selfamily(emp.getEid());
		List jlist= es.seljobs(emp.getEid());
		int fsize=flist.size();
		int jsize=jlist.size();
		setValToRequest("emp", e);
		setValToRequest("flist", flist);
		setValToRequest("jlist", jlist);
		setValToRequest("fsize", fsize);
		setValToRequest("jsize", jsize);
		return "toupd";
	}
	//�޸�
	public String update(){
		 String fid[]=(String [])getValFromTable("family.fid");
		 String jid[]=(String [])getValFromTable("jobs.jid");
		//������
		 String jcom[]=(String []) getValFromTable("jobs.jcompany");
		 String jwork[]=(String []) getValFromTable("jobs.jwork");
		 String jreason[]=(String []) getValFromTable("jobs.jreason");
		 String jmoney[]=(String []) getValFromTable("jobs.jmoney");
		 
		 //��Ա��
		 String fname[]=(String []) getValFromTable("family.fname");
		 String frelation[]=(String []) getValFromTable("family.frelation");
		 String fcompany[]=(String []) getValFromTable("family.fcompany");
		 String fjob[]=(String []) getValFromTable("family.fjob");
		 String ftel[]=(String []) getValFromTable("family.ftel");
		 String fbeizhu[]=(String []) getValFromTable("family.fbeizhu");
		 System.out.println("emp img" +emp.getEimg());
		 if(this.fileFileName==null){
			 this.fileFileName=emp.getEimg();
		 }else{
			 System.out.println("file :"+this.file);
			 System.out.println(this.fileFileName);
			 try {
					FileOutputStream fos = new FileOutputStream(this.savePath + this.fileFileName);
					FileInputStream fis = new FileInputStream(this.file);
					System.out.println(fileFileName);
					emp.setEimg(fileFileName);
					byte[] bs = new byte[1024];
					int real = fis.read(bs);
					while(real > 0){
						fos.write(bs, 0, real);
						real = fis.read(bs);
					}
					fos.close();
					fis.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		boolean b= es.updemp(emp);
		if(b){
			if(jid!=null&&!jid.equals("")){
				for (int i = 0; i < jid.length; i++) { //�޸Ĺ�������
					Jobs j= new Jobs();
					if(jcom[i]!=null&&!jcom[i].equals("")){ //�жϲ���Ϊ��
						if(jid[i]!=null&&!jid[i].equals("")){ //�޸�
							j.setJid(Integer.parseInt(jid[i]));
							j.setEid(Integer.parseInt(emp.getEid()+""));
							j.setJcompany(jcom[i]);
							j.setJmoney(jmoney[i]);
							j.setJreason(jreason[i]);
							j.setJwork(jwork[i]);
							es.updjobs(j);  
						}else{    //����
							j.setEid(Integer.parseInt(emp.getEid()+""));
							j.setJcompany(jcom[i]);
							j.setJmoney(jmoney[i]);
							j.setJreason(jreason[i]);
							j.setJwork(jwork[i]);
							es.addjobs(j);
						}
						
					}
				} 
				for (int i = 0; i < fid.length; i++) {    //�޸ļ���
					Family f=new Family();  
					if(fname[i]!=null&&!fname[i].equals("")){ //�жϲ���Ϊ��
						if(fid[i]!=null&&!fid[i].equals("")){
							f.setFid(Integer.parseInt(fid[i]));
							f.setEid(Integer.parseInt(emp.getEid()+""));
							f.setFbeizhu(fbeizhu[i]);
							f.setFcompany(fcompany[i]);
							f.setFjob(fjob[i]);
							f.setFname(fname[i]);
							f.setFrelation(frelation[i]);
							f.setFtel(ftel[i]);
							es.updfamily(f);
						}else{
							f.setEid(Integer.parseInt(emp.getEid()+""));
							f.setFbeizhu(fbeizhu[i]);
							f.setFcompany(fcompany[i]);
							f.setFjob(fjob[i]);
							f.setFname(fname[i]);
							f.setFrelation(frelation[i]);
							f.setFtel(ftel[i]);
							es.addfamily(f);
						}
					}
				}
			}
		}
		return list();
	}
	public String update2(){
		 String fid[]=(String [])getValFromTable("family.fid");
		 String jid[]=(String [])getValFromTable("jobs.jid");
		//������
		 String jcom[]=(String []) getValFromTable("jobs.jcompany");
		 String jwork[]=(String []) getValFromTable("jobs.jwork");
		 String jreason[]=(String []) getValFromTable("jobs.jreason");
		 String jmoney[]=(String []) getValFromTable("jobs.jmoney");
		 
		 //��Ա��
		 String fname[]=(String []) getValFromTable("family.fname");
		 String frelation[]=(String []) getValFromTable("family.frelation");
		 String fcompany[]=(String []) getValFromTable("family.fcompany");
		 String fjob[]=(String []) getValFromTable("family.fjob");
		 String ftel[]=(String []) getValFromTable("family.ftel");
		 String fbeizhu[]=(String []) getValFromTable("family.fbeizhu");
		 System.out.println("emp img" +emp.getEimg());
		 if(this.fileFileName==null){
			 this.fileFileName=emp.getEimg();
		 }else{
			 System.out.println("file :"+this.file);
			 System.out.println(this.fileFileName);
			 try {
					FileOutputStream fos = new FileOutputStream(this.savePath + this.fileFileName);
					FileInputStream fis = new FileInputStream(this.file);
					System.out.println(fileFileName);
					emp.setEimg(fileFileName);
					byte[] bs = new byte[1024];
					int real = fis.read(bs);
					while(real > 0){
						fos.write(bs, 0, real);
						real = fis.read(bs);
					}
					fos.close();
					fis.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		boolean b= es.updemp(emp);
		if(b){
			if(jid!=null&&!jid.equals("")){
				for (int i = 0; i < jid.length; i++) { //�޸Ĺ�������
					Jobs j= new Jobs();
					if(jcom[i]!=null&&!jcom[i].equals("")){ //�жϲ���Ϊ��
						if(jid[i]!=null&&!jid[i].equals("")){ //�޸�
							j.setJid(Integer.parseInt(jid[i]));
							j.setEid(Integer.parseInt(emp.getEid()+""));
							j.setJcompany(jcom[i]);
							j.setJmoney(jmoney[i]);
							j.setJreason(jreason[i]);
							j.setJwork(jwork[i]);
							es.updjobs(j);  
						}else{    //����
							j.setEid(Integer.parseInt(emp.getEid()+""));
							j.setJcompany(jcom[i]);
							j.setJmoney(jmoney[i]);
							j.setJreason(jreason[i]);
							j.setJwork(jwork[i]);
							es.addjobs(j);
						}
						
					}
				} 
				for (int i = 0; i < fid.length; i++) {    //�޸ļ���
					Family f=new Family();  
					if(fname[i]!=null&&!fname[i].equals("")){ //�жϲ���Ϊ��
						if(fid[i]!=null&&!fid[i].equals("")){
							f.setFid(Integer.parseInt(fid[i]));
							f.setEid(Integer.parseInt(emp.getEid()+""));
							f.setFbeizhu(fbeizhu[i]);
							f.setFcompany(fcompany[i]);
							f.setFjob(fjob[i]);
							f.setFname(fname[i]);
							f.setFrelation(frelation[i]);
							f.setFtel(ftel[i]);
							es.updfamily(f);
						}else{
							f.setEid(Integer.parseInt(emp.getEid()+""));
							f.setFbeizhu(fbeizhu[i]);
							f.setFcompany(fcompany[i]);
							f.setFjob(fjob[i]);
							f.setFname(fname[i]);
							f.setFrelation(frelation[i]);
							f.setFtel(ftel[i]);
							es.addfamily(f);
						}
					}
				}
			}
		}
		return "tomain";
	}
	public String sel(){
		List list= ds.seldept(); //����list
		setValToRequest("list", list);
		Emp e= es.selemp(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+""));
		List flist= es.selfamily(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+""));
		List jlist= es.seljobs(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("id")+""));
		int fsize=flist.size();
		int jsize=jlist.size();
		setValToRequest("emp", e);
		setValToRequest("flist", flist);
		setValToRequest("jlist", jlist);
		setValToRequest("fsize", fsize);
		setValToRequest("jsize", jsize);
		return "toupd2";
	}
	
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public Jobs getJobs() {
		return jobs;
	}
	public void setJobs(Jobs jobs) {
		this.jobs = jobs;
	}
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}
	
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
}
