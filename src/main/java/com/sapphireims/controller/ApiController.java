package com.sapphireims.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sapphireims.dto.OrderDto;
import com.sapphireims.dto.PaginationDto;
import com.sapphireims.dto.ProductDto;
import com.sapphireims.dto.UserWalletDto;
import com.sapphireims.dto.UsersDto;
import com.sapphireims.dto.WalletTxDto;
import com.sapphireims.service.FoodService;

/**
 * Spring rest api's controller java file
 * 
 * 
 * @author lokesh.yadav
 * @since 2019-01-14
 */
@SuppressWarnings("unchecked")
@RestController
public class ApiController {

	private static final Logger LOGGER = Logger.getLogger(ApiController.class);
	@Autowired
	FoodService service;

	@RequestMapping(path = "/api/customer/get", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getCustomer(@RequestParam("start") int start,
			@RequestParam("limit") int limit, @RequestParam("sort") String sort) {
		Map<String,Object> map = new HashMap<String,Object>(2);
		
		List<UsersDto> listCustomer;
		 JSONArray array = new JSONArray(sort);
		    JSONObject obj = array.getJSONObject(0);
		    String sortProperty = (String) obj.get("property");
		    String sortDirection = (String) obj.get("direction");
		PaginationDto pageDto=new PaginationDto();
		pageDto.setStart(start);
		pageDto.setLimit(limit);
		pageDto.setSortProperty(sortProperty);
		pageDto.setSortDirection(sortDirection);
		try {
			listCustomer = service.getAllCustomer(pageDto);
			LOGGER.info("Size of total customer :"+listCustomer.size());
		    map.put("TotalCount", service.totalCountCustomer());
		    map.put("data", listCustomer);
			return map;
		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());	
			return map;
		}

	}

	@RequestMapping(path = "/api/customer/register", method = RequestMethod.POST)
	public ResponseEntity<UsersDto> registerCustomer(@RequestBody UsersDto customer) {
		try {
			
			service.saveCustomer(customer);
			return new ResponseEntity(customer, HttpStatus.OK);

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return new ResponseEntity( HttpStatus.EXPECTATION_FAILED);
		}
		
	}

	@RequestMapping(path = "/api/customer/product", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object>  getProduct(@RequestParam("start") int start,
			@RequestParam("limit") int limit, @RequestParam("sort") String sort) {
		Map<String,Object> map = new HashMap<String,Object>(2);
		try {
			 JSONArray array = new JSONArray(sort);
			    JSONObject obj = array.getJSONObject(0);
			    String sortProperty = (String) obj.get("property");
			    String sortDirection = (String) obj.get("direction");
			PaginationDto pageDto=new PaginationDto();
			pageDto.setStart(start);
			pageDto.setLimit(limit);
			pageDto.setSortProperty(sortProperty);
			pageDto.setSortDirection(sortDirection);
			List<ProductDto> product = service.getAllProduct(pageDto);
			 map.put("TotalCount", service.totalCountFood());
			    map.put("data", product);
				return map;

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return map;
		}

	}

	@RequestMapping(path = "/api/product/register", method = RequestMethod.POST)
	public ResponseEntity<ProductDto> storeProduct(@RequestBody ProductDto customer) {
		try {
			service.saveProduct(customer);
			return new ResponseEntity(customer, HttpStatus.OK);

		} catch (SQLException e) {		
			LOGGER.error(e.getLocalizedMessage());
			return new ResponseEntity( HttpStatus.EXPECTATION_FAILED);
		}
	
	}

	@RequestMapping(path = "/api/order", method = RequestMethod.POST)
	public ResponseEntity<OrderDto> orderProduct(@RequestBody OrderDto order) {
		try {

			Double balance = service.getBalanceOfUser();
			if (balance < order.getCost()) {
				return new ResponseEntity(null, HttpStatus.OK);
			} else {
				service.deductWallet(order.getCost());
				service.orderFoodByUser(order);
				return new ResponseEntity(order, HttpStatus.OK);
			}

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return new ResponseEntity( HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(path = "/api/order/get", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getAllOrder(@RequestParam("start") int start,
			@RequestParam("limit") int limit, @RequestParam("sort") String sort) {
		Map<String,Object> map = new HashMap<String,Object>(2);
		try {
			
			JSONArray array = new JSONArray(sort);
		    JSONObject obj = array.getJSONObject(0);
		    String sortProperty = (String) obj.get("property");
		    String sortDirection = (String) obj.get("direction");
		PaginationDto pageDto=new PaginationDto();
		pageDto.setStart(start);
		pageDto.setLimit(limit);
		pageDto.setSortProperty(sortProperty);
		pageDto.setSortDirection(sortDirection);
			List<OrderDto> order = service.getAllOrder(pageDto);
			LOGGER.info("Order Size:"+order.size());
			 map.put("TotalCount", service.totalCountOrderHis());
			    map.put("data", order);
				return map;

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return map;
		}
	}

	@RequestMapping(path = "/api/order/getbyuser", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getAllOrderByUser(@RequestParam("start") int start,
			@RequestParam("limit") int limit, @RequestParam("sort") String sort) {
		Map<String,Object> map = new HashMap<>(2);
		try {
			JSONArray array = new JSONArray(sort);
		    JSONObject obj = array.getJSONObject(0);
		    String sortProperty = (String) obj.get("property");
		    String sortDirection = (String) obj.get("direction");
		PaginationDto pageDto=new PaginationDto();
		pageDto.setStart(start);
		pageDto.setLimit(limit);
		pageDto.setSortProperty(sortProperty);
		pageDto.setSortDirection(sortDirection);
			List<OrderDto> order = service.getAllOrder(pageDto);
			LOGGER.info("Order Size:"+order.size());
			map.put("TotalCount", service.totalCountOrderHis());
		    map.put("data", order);
			return map;

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return map;
		}
		
	}

	@RequestMapping(path = "/api/wallet", method = RequestMethod.POST)
	public ResponseEntity<OrderDto> addMoneyWallet(@RequestBody UserWalletDto wallet) {
		try {
			service.saveWallet(wallet);
			return new ResponseEntity(wallet, HttpStatus.OK);

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return new ResponseEntity( HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(path = "/api/wallet/all", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getAllWallet(@RequestParam("start") int start,
			@RequestParam("limit") int limit, @RequestParam("sort") String sort) {
		Map<String,Object> map = new HashMap<String,Object>(2);
		try {
			
			JSONArray array = new JSONArray(sort);
		    JSONObject obj = array.getJSONObject(0);
		    String sortProperty = (String) obj.get("property");
		    String sortDirection = (String) obj.get("direction");
		PaginationDto pageDto=new PaginationDto();
		
		pageDto.setStart(start);
		pageDto.setSortProperty(sortProperty);
		pageDto.setLimit(limit);
		pageDto.setSortDirection(sortDirection);
			List<UserWalletDto> wallet = service.getAllWallet(pageDto);
			LOGGER.info("Wallet Size:"+wallet.toString());
			map.put("TotalCount", service.totalCountWallet());
		    map.put("data", wallet);
			return map;

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return map;
		}
		
	}

	@RequestMapping(path = "/api/wallet/balance", method = RequestMethod.GET)
	public ResponseEntity<Double> getWalletBlance() {
		try {
			Double balance = service.getBalanceOfUser();
			return new ResponseEntity(balance, HttpStatus.OK);

		} catch (SQLException e) {
			
			LOGGER.error(e.getLocalizedMessage());
			return new ResponseEntity( HttpStatus.EXPECTATION_FAILED);
		}
		
	}

	@RequestMapping(path = "/api/wallet/tx", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getTxOfUser(@RequestParam("start") int start,
			@RequestParam("limit") int limit, @RequestParam("sort") String sort) {
		Map<String,Object> map = new HashMap<String,Object>(2);
		try {
			JSONArray array = new JSONArray(sort);
		    JSONObject obj = array.getJSONObject(0);
		    String sortProperty = (String) obj.get("property");
		    String sortDirection = (String) obj.get("direction");
		PaginationDto pageDto=new PaginationDto();
		pageDto.setStart(start);
		pageDto.setLimit(limit);
		pageDto.setSortProperty(sortProperty);
		pageDto.setSortDirection(sortDirection);
		pageDto.setSortProperty(sortProperty);
			List<WalletTxDto> tx = service.fatchTxByUser(pageDto);
			LOGGER.info("Transection table size:"+tx.size());
			map.put("TotalCount", service.totalCountTransection());
		    map.put("data", tx);
			return map;

		} catch (SQLException e) {
			LOGGER.error(e.getLocalizedMessage());
			return map;
		}
		
	}

}
