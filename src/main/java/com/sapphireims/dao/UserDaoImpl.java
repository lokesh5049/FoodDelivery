package com.sapphireims.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphireims.bo.OrderBo;
import com.sapphireims.bo.PaginationBo;
import com.sapphireims.bo.ProductBo;
import com.sapphireims.bo.RoleBo;
import com.sapphireims.bo.UserWalletBo;
import com.sapphireims.bo.UsersBo;
import com.sapphireims.bo.WalletTxBo;

/**
 * implemented class for interface for Data Asses Object
 * 
 * @author lokesh.yadav
 * @since 2019-01-11
 *
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	UserWalletBo usertx;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public int createUser(UsersBo customer, RoleBo role) throws SQLException {
		try {
			this.hibernateTemplate.save(customer);
			this.hibernateTemplate.save(role);
			UserWalletBo wallet = new UserWalletBo();
			wallet.setAmount(0);
			wallet.setDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			wallet.setUserid(customer);
			this.hibernateTemplate.save(wallet);
			return 1;

		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public UsersBo checkCred(String username) {
		List<UsersBo> users = null;
		users = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from UsersBo where email=?")
				.setParameter(0, username).list();

		if (!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<UsersBo> getAllUser(PaginationBo bo) throws SQLException {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		return session
				.createQuery(
						"from UsersBo ORDER BY " + bo.getSortProperty() + " " + bo.getSortDirection().toUpperCase())
				.setFirstResult(bo.getStart()).setMaxResults(bo.getLimit()).list();
	}

	@Override
	public UsersBo getUser(String username) {
		List<UsersBo> users = (List<UsersBo>) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from UsersBo where email=?").setParameter(0, username).list();

		UsersBo usersBo = null;

		for (UsersBo bo : users) {
			usersBo = bo;
			if (usersBo != null) {
				break;
			}

		}

		return usersBo;
	}

	@Override
	public String updateUser(String username, UsersBo users) throws SQLException {

		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("update UsersBo set firstname = :firstname ,lastname=:lastname ,"
						+ "phoneNo=:phone,address=:address" + " where email = :email");
		query.setParameter("firstname", users.getFirstname());
		query.setParameter("lastname", users.getLastname());
		query.setParameter("phone", users.getPhoneNo());
		query.setParameter("address", users.getAddress());
		query.setParameter("email", users.getEmail());

		return "inserted";
	}

	@Override
	public String deleteUser(String username) throws SQLException {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("delete UsersBo where email = :email");
		query.setParameter("eamil", username);
		return "delete";
	}

	@Override
	public int createProduct(ProductBo product) {
		this.hibernateTemplate.save(product);
		return 1;
	}

	@Override
	public List<ProductBo> fatchAllProduct(PaginationBo bo) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		return session
				.createQuery(
						"from ProductBo ORDER BY " + bo.getSortProperty() + " " + bo.getSortDirection().toUpperCase())
				.setFirstResult(bo.getStart()).setMaxResults(bo.getLimit()).list();
	}

	@Override
	public ProductBo getProduct(Integer productname) {
		List<ProductBo> productList = (List<ProductBo>) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from ProductBo where id=?").setParameter(0, productname).list();
		ProductBo product = new ProductBo();
		for (ProductBo bo : productList) {
			product = bo;
			if (product != null) {
				break;
			}

		}
		return product;
	}

	@Override
	public String updateProduct(Integer id, ProductBo product) {
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("update ProductBo set name = :name ,lastname=:lastname ,"
						+ "phoneNo=:phone,address=:address" + " where id = :id");
		query.setParameter("firstname", product.getName());
		query.setParameter("lastname", product.getPrice());
		query.setParameter("id", id);
		return null;
	}

	@Override
	public String deleteProduct(Integer username) {
		return null;
	}

	@Override
	public int createOrder(OrderBo order) throws SQLException {
		return (int) this.hibernateTemplate.save(order);
	}

	@Override
	public List<OrderBo> fatchOrder(PaginationBo bo) throws SQLException {
		
		String parem=bo.getSortProperty(); 
		if(bo.getSortProperty().equalsIgnoreCase("foodname")) 		
			parem="id"; 
		if(bo.getSortProperty().equalsIgnoreCase("resturentname")) 		
			parem="resturent_name"; 
		return (List<OrderBo>) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from OrderBo ORDER BY " +parem + " "
						+ bo.getSortDirection().toUpperCase())
				.setFirstResult(bo.getStart()).setMaxResults(bo.getLimit()).list();
		
	}

	@Override
	public List<OrderBo> fatchOrderByUser(String username, PaginationBo bo) throws SQLException {

		return (List<OrderBo>) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from OrderBo  ORDER BY " + bo.getSortProperty() + " "
						+ bo.getSortDirection().toUpperCase())
				.setFirstResult(bo.getStart()).setMaxResults(bo.getLimit()).list();
	}

	@Override
	public int addMoney(UserWalletBo wallet, String cardNo) throws SQLException {
		return 0;
	}

	@Override
	public Double getBalance(String username) throws SQLException {
		UsersBo bo = this.getUser(username);
		Double result = 0.0;
		List<UserWalletBo> userList = (List<UserWalletBo>) this.hibernateTemplate.getSessionFactory()
				.getCurrentSession().createQuery("from UserWalletBo where userid=:userid  ").setParameter("userid", bo)
				.list();
		for (UserWalletBo d : userList) {
			usertx = d;
			result = d.getAmount();
		}

		return result;
	}

	@Override
	public int updateBalance(String userid, Double amount, String cardNo) throws SQLException {
		Double oldamount = this.getBalance(userid);
		WalletTxBo tx = new WalletTxBo();
		int result = this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery("update wallet w set w.wallet_money=:amount where w.userid=:userid ")
				.setParameter("amount", amount + oldamount).setParameter("userid", userid).executeUpdate();

		if (result == 0) {
			UsersBo bo = this.getUser(userid);
			UserWalletBo walletBo = new UserWalletBo();

			walletBo.setAmount(amount);
			walletBo.setDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			walletBo.setUserid(bo);
			tx.setAmount(amount);
			tx.setUsername(userid);
			tx.setDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			tx.setCardNo(Long.parseLong(cardNo));
			tx.setTransection(walletBo);
			this.hibernateTemplate.getSessionFactory().getCurrentSession().save(walletBo);
			this.hibernateTemplate.getSessionFactory().getCurrentSession().save(tx);
		} else {
			tx.setAmount(amount);
			tx.setUsername(userid);
			tx.setDate(new java.sql.Timestamp(new java.util.Date().getTime()));
			tx.setCardNo(Long.parseLong(cardNo));
			this.getBalance(userid);
			tx.setTransection(usertx);
			this.hibernateTemplate.getSessionFactory().getCurrentSession().save(tx);
			return result;
		}
		return 0;
	}

	@Override
	public List<UserWalletBo> fatchAllWallat(PaginationBo bo) throws SQLException {
		return (List<UserWalletBo>) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(
						"from UserWalletBo ORDER BY" + bo.getSortProperty() + " " + bo.getSortDirection().toUpperCase())
				.setFirstResult(bo.getStart()).setMaxResults(bo.getLimit()).list();
	}

	@Override
	public int subMoney(String username, Double amount) throws SQLException {
		Double oldamount = this.getBalance(username);
		WalletTxBo tx = new WalletTxBo();
		int result = this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createSQLQuery("update wallet w set w.wallet_money=:amount where w.userid=:userid ")
				.setParameter("amount", oldamount - amount).setParameter("userid", username).executeUpdate();
		tx.setAmount(amount);
		tx.setUsername(username);
		tx.setDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		this.getBalance(username);
		tx.setTransection(usertx);
		this.hibernateTemplate.getSessionFactory().getCurrentSession().save(tx);
		return result;
	}

	@Override
	public List<WalletTxBo> getTxByUser(String username, PaginationBo bo) throws SQLException {
		return (List<WalletTxBo>) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from WalletTxBo where username=:userid ORDER BY " + bo.getSortProperty() + " "
						+ bo.getSortDirection().toUpperCase())
				.setParameter("userid", username).setFirstResult(bo.getStart()).setMaxResults(bo.getLimit()).list();
	}

	@Override
	public long getTotalUser() throws SQLException {

		return (Long) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("select count(*) from UsersBo").uniqueResult();
	}

	@Override
	public long getTotalProduct() throws SQLException {

		return (long) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("select count(*) from ProductBo").uniqueResult();
	}

	@Override
	public long getTotalOrder() throws SQLException {

		return (long) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("select count(*) from OrderBo").uniqueResult();
	}

	@Override
	public long getTotalWallat() throws SQLException {

		return (long) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("select count(*) from UserWalletBo").uniqueResult();
	}

	@Override
	public long getTotalTx() throws SQLException {

		return (long) this.hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("select count(*) from WalletTxBo").uniqueResult();
	}

}
