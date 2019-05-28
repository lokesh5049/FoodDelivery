package com.sapphireims.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.sapphireims.bo.OrderBo;
import com.sapphireims.bo.PaginationBo;
import com.sapphireims.bo.ProductBo;
import com.sapphireims.bo.RoleBo;
import com.sapphireims.bo.UserWalletBo;
import com.sapphireims.bo.UsersBo;
import com.sapphireims.bo.WalletTxBo;
import com.sapphireims.dao.UserDao;
import com.sapphireims.dto.OrderDto;
import com.sapphireims.dto.PaginationDto;
import com.sapphireims.dto.ProductDto;
import com.sapphireims.dto.UserWalletDto;
import com.sapphireims.dto.UsersDto;
import com.sapphireims.dto.WalletTxDto;

/**
 * implemented class for Interface for FoodSevice
 * 
 * @author lokesh.yadav
 * @since 2019-01-11
 *
 */
@SuppressWarnings("deprecation")
@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	UserDao dao;

	@Autowired
	@Qualifier("email")
	EmailService emailservice;

	static final String SUBJECT_WALLET = "SapphireIMS Wallet";
	static final String CURRENT_WALLET_BALANCE = "Current Balance is Rs.";
	private static final Logger LOGGER = Logger.getLogger(FoodServiceImpl.class);

	@Override
	public int saveCustomer(UsersDto dto) throws SQLException {
		UsersBo bo = new UsersBo();
		bo.setFirstname(dto.getFirstname());
		bo.setLastname(dto.getLastname());
		bo.setAddress(dto.getAddress());
		bo.setEmail(dto.getEmail());
		bo.setPassword(dto.getPassword());
		bo.setPhoneNo(dto.getPhoneNo());
		RoleBo roleBo = new RoleBo();
		roleBo.setUsername(bo);
		roleBo.setUserrole(dto.getRole());
		dao.createUser(bo, roleBo);
		emailservice.send(dto.getEmail());
		emailservice.send(dto.getEmail(), SUBJECT_WALLET, CURRENT_WALLET_BALANCE + " 0.0");

		return 1;
	}

	@Override
	public List<UsersDto> getAllCustomer(PaginationDto page) {
		try {
			PaginationBo pageBo=new PaginationBo();
			//spring util class
			BeanUtils.copyProperties(page, pageBo);
			List<UsersBo> listBo = dao.getAllUser(pageBo);

			List<UsersDto> listDto = new ArrayList<>();
			

			for (UsersBo bo : listBo) {
				UsersDto dto = new UsersDto();
				dto.setId(bo.getId());
				dto.setFirstname(bo.getFirstname());
				dto.setLastname(bo.getLastname());
				dto.setAddress(bo.getAddress());
				dto.setEmail(bo.getEmail());
				dto.setPhoneNo(bo.getPhoneNo());
				dto.setEnabled(bo.isEnabled());
				listDto.add(dto);
			}
			return listDto;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<ProductDto> getAllProduct(PaginationDto page) throws SQLException {
		PaginationBo pageBo=new PaginationBo();
		BeanUtils.copyProperties(page, pageBo);
		List<ProductBo> listBo = dao.fatchAllProduct(pageBo);
		List<ProductDto> listDto = new ArrayList<>();
		for (ProductBo p_bo : listBo) {
			ProductDto dto = new ProductDto();
			dto.setId(p_bo.getId());
			dto.setName(p_bo.getName());
			dto.setPrice(p_bo.getPrice());
			dto.setResturentName(p_bo.getResturentName());
			listDto.add(dto);
		}
		return listDto;
	}

	@Override
	public int saveProduct(ProductDto dto) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;

		if (principal instanceof UserDetails) {

			username = ((UserDetails) principal).getUsername();

		} else {

			username = principal.toString();

		}
		UsersBo bo = dao.getUser(username);
		ProductBo productBo = new ProductBo();
		productBo.setName(dto.getName());
		productBo.setPrice(dto.getPrice());
		productBo.setResturentName(dto.getResturentName());
		Set<UsersBo> usersSet = new HashSet<>();
		usersSet.add(bo);
		productBo.setUsers(usersSet);
		return dao.createProduct(productBo);
	}

	@Override
	public List<OrderDto> getAllOrder(PaginationDto page) throws SQLException {
		PaginationBo pageBo=new PaginationBo();
		BeanUtils.copyProperties(page, pageBo);
		List<OrderBo> orderBo = dao.fatchOrder(pageBo);
		List<OrderDto> orderDto = new ArrayList<>();
		for (OrderBo bo : orderBo) {
			OrderDto dto = new OrderDto();
			dto.setDate(bo.getDate().toLocaleString());
			ProductBo productBo = bo.getFoodId();
			dto.setFoodname(productBo.getName());
			dto.setResturentname(productBo.getResturentName());
			dto.setLocation(bo.getLocation());
			dto.setNumber(bo.getNumber());
			dto.setOrderbyId(bo.getOrderbyId());
			dto.setId(bo.getId());
			dto.setCost(bo.getCost());
			orderDto.add(dto);
		}

		return orderDto;
	}

	@Override
	public List<OrderDto> getAllOrderByUser(PaginationDto page) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		PaginationBo pageBo=new PaginationBo();
		BeanUtils.copyProperties(page, pageBo);
		
		List<OrderBo> orderBo = dao.fatchOrderByUser(username,pageBo);
		List<OrderDto> orderDto = new ArrayList<>();
		for (OrderBo bo : orderBo) {
			OrderDto dto = new OrderDto();
			dto.setDate(bo.getDate().toLocaleString());
			ProductBo productBo = bo.getFoodId();
			dto.setFoodname(productBo.getName());
			dto.setResturentname(productBo.getResturentName());
			dto.setLocation(bo.getLocation());
			dto.setNumber(bo.getNumber());
			dto.setOrderbyId(bo.getOrderbyId());
			dto.setId(bo.getId());
			dto.setCost(bo.getCost());
			orderDto.add(dto);
		}

		return orderDto;
	}

	@Override
	public int orderFoodByUser(OrderDto dto) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		ProductBo productBo = dao.getProduct(dto.getFoodId());
		OrderBo bo = new OrderBo();
		bo.setDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		bo.setFoodId(productBo);
		bo.setLocation(dto.getLocation());
		bo.setNumber(dto.getNumber());
		bo.setOrderbyId(username);
		bo.setCost(dto.getCost());
		Double balance = this.getBalanceOfUser();
		emailservice.send(username, SUBJECT_WALLET, CURRENT_WALLET_BALANCE + balance);
		return dao.createOrder(bo);
	}

	@Override
	public int saveWallet(UserWalletDto dto) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Double balance = this.getBalanceOfUser();
		int result = 0;
		try {
			emailservice.send(username, SUBJECT_WALLET,
					CURRENT_WALLET_BALANCE + balance + "\nAdding amount is Rs. " + dto.getAmount());
			result = dao.updateBalance(username, dto.getAmount(), dto.getCardNo());
		} catch (Exception e) {
			result = dao.updateBalance(username, dto.getAmount(), dto.getCardNo());
			e.getMessage();

		}
		return result;
	}

	@Override
	public List<String> getAllCards(String username,PaginationDto page) throws SQLException {
		return new ArrayList<>();
	}

	@Override
	public int updateWallet(UserWalletDto dto) throws SQLException {
		return 0;
	}

	@Override
	public List<UserWalletDto> getAllWallet(PaginationDto page) throws SQLException {
		PaginationBo pageBo=new PaginationBo();
		BeanUtils.copyProperties(page, pageBo);
		
		List<UserWalletBo> listBo = dao.fatchAllWallat(pageBo);
		List<UserWalletDto> listDto = new ArrayList<>();
		for (UserWalletBo bo : listBo) {
			UserWalletDto dto = new UserWalletDto();
			dto.setId(bo.getId());
			dto.setAmount(bo.getAmount());
			dto.setDate(bo.getDate().toLocaleString());
			dto.setUserid(bo.getUserid().getEmail());
			listDto.add(dto);
		}

		return listDto;
	}

	@Override
	public Double getBalanceOfUser() throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return dao.getBalance(username);
	}

	@Override
	public int deductWallet(Double amount) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Double balance = this.getBalanceOfUser();
		int result = 0;
		try {
			emailservice.send(username, SUBJECT_WALLET,
					CURRENT_WALLET_BALANCE + balance + "\nDeduction cost is Rs. " + amount);
			result = dao.subMoney(username, amount);
		} catch (Exception e) {
			result = dao.subMoney(username, amount);
			e.getMessage();
		}
		return result;
	}

	@Override
	public List<WalletTxDto> fatchTxByUser(PaginationDto page) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		PaginationBo pageBo=new PaginationBo();
		BeanUtils.copyProperties(page, pageBo);
		List<WalletTxBo> listBo = dao.getTxByUser(username,pageBo);
		List<WalletTxDto> listDto = new ArrayList<>();

		for (WalletTxBo bo : listBo) {
			WalletTxDto dto = new WalletTxDto();
			dto.setId(bo.getId());
			dto.setUserid(bo.getUsername());
			dto.setAmount(bo.getAmount());
			dto.setCardNo(bo.getCardNo());
			dto.setDate(bo.getDate().toLocaleString());
			if (bo.getCardNo() == 0) {
				dto.setType("Deduction");
			} else {
				dto.setType("Added in Wallet");
			}
			listDto.add(dto);
		}

		return listDto;
	}

	@Override
	public List<UsersDto> getAllCustomer(int start, int limit, String sortProperty, String sortDirection)
			throws SQLException {	
		return new ArrayList<>();
	}


	@Override
	public List<OrderDto> getAllOrder(int start, int limit, String sortProperty, String sortDirection)
			throws SQLException {
		
		return new ArrayList<>();
	}

	@Override
	public List<OrderDto> getAllOrderByUser(int start, int limit, String sortProperty, String sortDirection)
			throws SQLException {
		
		return new ArrayList<>();
	}

	@Override
	public List<String> getAllCards(String username, int start, int limit, String sortProperty, String sortDirection)
			throws SQLException {
	
		return new ArrayList<>();
	}

	@Override
	public List<UserWalletDto> getAllWallet(String username, int start, int limit, String sortProperty,
			String sortDirection) throws SQLException {

		return new ArrayList<>();
	}

	@Override
	public List<WalletTxDto> fatchTxByUser(String username, int start, int limit, String sortProperty,
			String sortDirection) throws SQLException {

		return new ArrayList<>();
	}

	@Override
	public List<ProductDto> getAllProduct() throws SQLException {

		return new ArrayList<>();
	}


	@Override
	public long totalCountCustomer() throws SQLException {
		
		return dao.getTotalUser();
	}

	@Override
	public long totalCountFood() throws SQLException {
		
		return dao.getTotalProduct();
	}

	@Override
	public long totalCountOrderHis() throws SQLException {
		
		return dao.getTotalOrder();
	}

	@Override
	public long totalCountTransection() throws SQLException {
		
		return dao.getTotalTx();
	}

	@Override
	public long totalCountWallet() throws SQLException {
		
		return dao.getTotalWallat();
	}
}
