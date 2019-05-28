/**
 * 
 */
package com.sapphireims.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapphireims.bo.OrderBo;
import com.sapphireims.bo.PaginationBo;
import com.sapphireims.bo.ProductBo;
import com.sapphireims.bo.RoleBo;
import com.sapphireims.bo.UserWalletBo;
import com.sapphireims.bo.UsersBo;
import com.sapphireims.bo.WalletTxBo;

/**
 * Interface for Data Asses Object
 * 
 * @author lokesh.yadav
 * @since   2019-01-11
 *
 */
public interface UserDao {

	//users
	public int createUser(UsersBo customer,RoleBo role) throws SQLException;
	public UsersBo checkCred(String username) throws SQLException;
	public List<UsersBo> getAllUser(PaginationBo bo) throws SQLException;
	public long getTotalUser()throws SQLException;
	public UsersBo  getUser(String username) throws SQLException;
	public String updateUser(String username,UsersBo users) throws SQLException;
	public String deleteUser(String username) throws SQLException;
	//product
	public int createProduct(ProductBo product) throws SQLException;
	public List<ProductBo> fatchAllProduct(PaginationBo bo) throws SQLException;
	public long getTotalProduct()throws SQLException;
	public ProductBo  getProduct(Integer id) throws SQLException;
	public String updateProduct(Integer id,ProductBo product) throws SQLException;
	public String deleteProduct(Integer id) throws SQLException;	
	//order
	public int createOrder(OrderBo order) throws SQLException;
	public List<OrderBo> fatchOrder(PaginationBo bo)throws SQLException;
	public long getTotalOrder()throws SQLException;
	public List<OrderBo> fatchOrderByUser(String username,PaginationBo bo)throws SQLException;
	
	//Wallet
	public int addMoney(UserWalletBo wallet,String cardNo)throws SQLException;
	public Double getBalance(String username)throws SQLException;
	public int updateBalance(String username,Double amount,String cardNo)throws SQLException;
	public List<UserWalletBo> fatchAllWallat(PaginationBo bo)throws SQLException;
	public long getTotalWallat()throws SQLException;
	public int subMoney(String username,Double amount)throws SQLException;
	
	//Transection
	public List<WalletTxBo> getTxByUser(String username,PaginationBo bo)throws SQLException;
	public long getTotalTx()throws SQLException;
	
	
	//Pagination
	
	

	
}
