package com.glory.exchange.trading;

import java.util.Map;

import org.apache.log4j.Logger;

import com.glory.beans.AccountInfo;
import com.glory.beans.Cancel;
import com.glory.beans.GetOrder;
import com.glory.beans.ListOrders;
import com.glory.beans.Order;
import com.glory.exchange.signature.ISignature;

/**
 * @Author : glory
 * @since  : 2020-11-20
 */
public abstract class TradingService implements ITradingService {

	protected final Logger logger = Logger.getLogger(this.getClass());
//	protected final JavaType mapType = JacksonUtils.getMapper().getTypeFactory().constructParametricType(HashMap.class, String.class, Object.class);
	
	

	@Override
	public AccountInfo accountInfo(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order.Response orders(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cancel.Response cancel(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetOrder.Response orderInfo(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListOrders.Response unmatchedList(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String historyList(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String depositList(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String withdrawList(String accessKey, String secretkey, ISignature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getOrdersParamsMap(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getCancelParamsMap(Cancel cancel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getGetOrderParamsMap(GetOrder getOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getListUnMatchedParamsMap(ListOrders listOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getListMatchedParamsMap(ListOrders listOrders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getListHistoryParamsMap(ListOrders listOrders) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
