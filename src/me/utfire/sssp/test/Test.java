package me.utfire.sssp.test;

import java.sql.SQLException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.utfire.sssp.entity.Department;
import me.utfire.sssp.entity.Employee;
import me.utfire.sssp.service.EmployeeService;

public class Test {
	
	private ApplicationContext ctx = null;
	private EntityManagerFactory entityManagerFactory = null;
	private EmployeeService employeeService = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
	
	}
	
	@org.junit.Test
	public void testAddData(){
		int c = 'a';
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		for(int i = 0;i < 20;i++){
			Employee employee = new Employee();
			employee.setBirth(new Date());
			employee.setCreateTime(new Date());
			
			Department department = new Department();
			int id = (int)Math.random()*4+1;
			department.setId(id);
			employee.setDepartment(department);
			employee.setEmail((char)i+""+(char)i+"@163.com");
			employee.setLastName((char)i+""+(char)i);
			
			em.persist(employee);
		}
		
		transaction.commit();
		em.close();
		entityManagerFactory.close();
	}
	
	
	@org.junit.Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
