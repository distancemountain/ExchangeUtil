package com.glory.exchange.market;

import java.util.List;

import com.glory.beans.DealtInfo;
import com.glory.beans.Market;

/**
 * @Author : glory
 * @since  : 2020-11-24
 */
public interface IMarketService {
	
//	public Map<String, Object> quotationTicker(String accessKey, String  secretkey, String ... symblos);
	
	public List<Market> quotationTicker(String accessKey, String  secretkey, String ... symbols);
	
	public DealtInfo getSettlementPrice(String accessKey, String  secretkey, Integer size, String ... symbols);
}
