package service;

import java.util.List;
import java.util.UUID;

import utils.msg;
import utils.test;
import bean.Customer;
import dao.CustomerDAO;

public class CustomerService {
	private String Msg;// 保存业务逻辑错误信息字段
	private CustomerDAO customerDao;
	private Customer db_customer;

	public Customer LoginByPassword(Customer customer) throws Exception {
		List<?> list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.login_fail_phone;
			System.out.println(this.Msg);
			return null;
		}

		db_customer = (Customer) list.get(0);

		if (!db_customer.getCustomerPassword().equals(customer.getCustomerPassword())) {
			this.Msg = msg.login_fail_password;
			System.out.println(this.Msg);
			return null;
		}

		String token = UUID.randomUUID().toString();
		db_customer.setCustomerToken(token);
		db_customer.setCustomerArray(db_customer.getCustomerArray() + "," + test.GetCurrentTime());

		Update(db_customer);
		return db_customer;
	}

	public Customer LoginByToken(Customer customer) throws Exception {
		List<?> list = customerDao.findByCustomerToken(customer.getCustomerToken());
		if (list.size() == 0) {
			this.Msg = msg.login_fail_token;
			System.out.println(this.Msg);
			return null;
		}

		db_customer = (Customer) list.get(0);
		db_customer.setCustomerArray(db_customer.getCustomerArray() + "," + test.GetCurrentTime());

		Update(db_customer);
		return db_customer;
	}

	public Customer LoginByCode(Customer customer) throws Exception {
		List<?> list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.login_fail_phone;
			System.out.println(this.Msg);
			return null;
		}
		db_customer = (Customer) list.get(0);
		if (!db_customer.getCustomerCode().equals(customer.getCustomerCode())) {
			this.Msg = msg.login_fail_code;
			System.out.println(this.Msg);
			return null;
		}

		String token = UUID.randomUUID().toString();
		db_customer.setCustomerToken(token);
		db_customer.setCustomerArray(db_customer.getCustomerArray() + "," + test.GetCurrentTime());

		Update(db_customer);
		return db_customer;
	}

	public Customer Add(Customer customer) {
		List<?> list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() > 0) {
			this.Msg = msg.add_fail_phone;
			System.out.println(this.Msg);
			return null;
		}

		String defaultFileName = "uploads/defaultImg.png";
		customer.setCustomerImg(defaultFileName);
		customer.setCustomerArray(test.GetCurrentTime());
		Customer result = customerDao.merge(customer);
		return result;
	}

	public Customer View(int customerID) throws Exception {
		Customer db_customer = customerDao.findById(customerID);
		if (db_customer == null) {
			this.Msg = msg.find_fail_id;
			System.out.println(this.Msg);
			return null;
		}
		return db_customer;
	}

	public Customer FindByPhone(Customer customer) {
		List<?> list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.login_fail_phone;
			System.out.println(this.Msg);
			return null;
		}

		db_customer = (Customer) list.get(0);
		return db_customer;
	}
	
	public Customer FindByToken(String token) {
		List<?> list = customerDao.findByCustomerToken(token);
		if (list.size() == 0) {
			this.Msg = msg.login_fail_phone;
			System.out.println(this.Msg);
			return null;
		}

		db_customer = (Customer) list.get(0);
		return db_customer;
	}

	public int GetCount(String keyword) throws Exception {
		return customerDao.getCount(keyword);
	}

	public List<?> Find(String keyword, int start, int length) throws Exception {
		List<?> list = customerDao.findAll(keyword, start, length);
		System.out.println("5859294:" + list.size());
		return list;
	}

	public Customer Update(Customer customer) throws Exception {
		List<?> list = customerDao.findByCustomerPhone(customer.getCustomerPhone());
		if (list.size() == 0) {
			this.Msg = msg.update_fail_phone;
			System.out.println(this.Msg);
			return null;
		}

		Customer result = customerDao.merge(customer);
		return result;
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

	public Customer getDb_customer() {
		return db_customer;
	}

	public void setDb_customer(Customer db_customer) {
		this.db_customer = db_customer;
	}

}
