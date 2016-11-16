package com.ht.login.Action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import com.ht.action.BaseAction;
import com.ht.login.service.LoginAfterService;
import com.ht.vo.emp.Emp;
import com.ht.vo.stu.Student;
 
public class LoginAfterAction   extends BaseAction{
		LoginAfterService  la=(LoginAfterService)getService("loginAfterService");
		private String psw1;
		private String psw2;
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//�ɵ����action �д�����id
		int  id=Integer.parseInt(session.getAttribute("id")+"");
		//��ȡ�ɵ��봫��������
		//String  i=(String)session.getAttribute("name");
		private  Emp  emp;
		private   Student  stu;
		private   List  list;
		
		
		public String updemp(){
			

			if(psw1.equals(psw2)){
				emp=la.getemp(id);
				emp.setEpwd(psw1);
				la.updpwd(emp);
				System.out.println("11111111111");
				session.removeAttribute("userpwd");
				session.setAttribute("userpwd", psw1);
				return  "to_log";
			}else{
				request.setAttribute("stauts","�������벻һ��");
				request.setAttribute("pwdd", psw1);
				return  "toback";
			}

		}
		public  String  updstu(){
			
			if(psw1.equals(psw2)){
				stu=la.getstu(id);
				stu.setSpwd(psw1);
				la.updpwd(stu);
				System.out.println("2222222");
				session.setAttribute("userpwd", psw1);
				return  "to_log1";
			}else{
				request.setAttribute("stauts","�������벻һ��");
				request.setAttribute("pwdd", psw1);
				return  "toback1";
			}
		}
		
		public Emp getEmp() {
			return emp;
		}

		public void setEmp(Emp emp) {
			this.emp = emp;
		}

		public Student getStu() {
			return stu;
		}

		public void setStu(Student stu) {
			this.stu = stu;
		}

		public List getList() {
			return list;
		}

		public void setList(List list) {
			this.list = list;
		}

		public String getPsw1() {
			return psw1;
		}

		public void setPsw1(String psw1) {
			this.psw1 = psw1;
		}

		public String getPsw2() {
			return psw2;
		}

		public void setPsw2(String psw2) {
			this.psw2 = psw2;
		}
}
