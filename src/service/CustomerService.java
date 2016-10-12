package service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

import utils.msg;
import utils.test;
import bean.Customer;
import dao.CustomerDAO;

public class CustomerService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CustomerDAO customerDao;

	public Customer LoginByPassword(Customer customer) throws Exception {
		List list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.customer_customernull;
			return null;
		}

		Customer db_customer = (Customer) list.get(0);
		if (!db_customer.getCustomerPassword().equals(customer.getCustomerPassword())) {
			this.Msg = msg.customer_passwordwrong;
			System.out.println(this.Msg);
			return null;
		}

		this.Msg = msg.customer_success;
		return db_customer;
	}

	public Customer LoginByToken(Customer customer) throws Exception {
		List list = customerDao.findByCustomerToken(customer.getCustomerToken());
		if (list.size() == 0) {
			this.Msg = msg.customer_tokenwrong;
			return null;
		}

		Customer db_customer = (Customer) list.get(0);

		this.Msg = msg.customer_success;
		return db_customer;
	}

	public Customer LoginByCode(Customer customer) throws Exception {
		List list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.customer_customernull;
			return null;
		}
		Customer db_customer = (Customer) list.get(0);
		if (!db_customer.getCustomerCode().equals(customer.getCustomerCode())) {
			this.Msg = msg.customer_codewrong;
			return null;
		}

		this.Msg = msg.customer_success;
		return db_customer;
	}

	public Customer Add(Customer customer) {
		Customer db_customer = customerDao.merge(customer);
		if (db_customer == null) {
			this.Msg = msg.customer_fail;
			return null;
		}

		this.Msg = msg.customer_success;
		return db_customer;
	}

	public Customer View(int customerID) throws Exception {
		Customer db_customer = customerDao.findById(customerID);
		if (db_customer == null) {
			this.Msg = msg.customer_customernull;
			return null;
		}

		this.Msg = msg.customer_success;
		return db_customer;
	}

	public Customer FindByPhone(Customer customer) {
		List list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.customer_customernull;
			return null;
		}

		Customer db_customer = (Customer) list.get(0);
		this.Msg = msg.customer_success;
		return db_customer;
	}

	public Customer FindByToken(String token) {
		List list = customerDao.findByCustomerToken(token);
		if (list.size() == 0) {
			this.Msg = msg.customer_customernull;
			return null;
		}

		Customer db_customer = (Customer) list.get(0);
		this.Msg = msg.customer_success;
		return db_customer;
	}

	public int Count_Keyword(String keyword) throws Exception {
		int count = customerDao.getCount(keyword);
		this.Msg = msg.customer_success;
		return count;
	}

	public List Find_Keyword(String keyword, int start, int length) throws Exception {
		List list = customerDao.findAll(keyword, start, length);
		this.Msg = msg.customer_success;
		return list;
	}

	public Customer Update(Customer customer) throws Exception {
		List<?> list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.customer_customernull;
			return null;
		}

		Customer db_customer = customerDao.merge(customer);
		return db_customer;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public CustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

}
