package com.glory.exchange.market;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import org.apache.commons.lang3.StringUtils;

import com.glory.beans.DealtInfo;
import com.glory.beans.Market;
import com.glory.exchange.api.HoubiAPI;
import com.glory.exchange.coins.HuobiProfiles;
import com.glory.exchange.common.JacksonHelper;
import com.glory.exchange.common.RequestUtils;
import com.glory.exchange.signature.HuobiParamsSigner;
import com.glory.exchange.signature.ISignature;

/**
 * @Author : glory
 * @since  : 2020-11-24
 */
public class HuobiMarketService extends MarketService implements IMarketService {

	/**
	 * 可传多个但必传
	 * {"status":"ok","ch":"market.bchbtc.detail.merged","ts":1534497122571,
	 * "tick":{"amount":101377.7608,"open":0.081960,"close":0.083042,"high":0.083878,"id":16636904119,
	 * "count":12499,"low":0.081418,"version":16636904119,
	 * "ask":[0.083053,0.5110],"vol":8375.8348273504,"bid":[0.082986,0.2000]}}
	 */
	@Override
	public List<Market> quotationTicker(String accessKey, String  secretkey, String... symbols) {
		String param = Arrays.toString(symbols);
		
		ISignature signature = new HuobiParamsSigner();
		signature.setApiRoot(HoubiAPI.rest_market_root);
		signature.setApiUri(HoubiAPI.market_detail_merged);
		signature.putParam(HoubiAPI.Market_detail_merged_params.symbol.getCode(), StringUtils.trim(param.substring(1, (param.length() - 1))));
		String uri = signature.sign(accessKey, secretkey);
		logger.warn("uri="  + uri);
		
		
//		StringBuilder uri = new StringBuilder(HoubiAPI.rest_market_root);
//		uri.append(HoubiAPI.market_detail_merged)
//		.append("?")
//		.append(HoubiAPI.Market_detail_merged_params.symbol.getCode())
//		.append("=")
//		.append(StringUtils.trim(param.substring(1, (param.length() - 1))));
		
		Content content = null;
    	try {
    		Request request = Request.Get(uri.toString());
    		RequestUtils.setProxy(request);
    		HuobiProfiles.addHeader(request, HoubiAPI.market_detail_merged_method_get);
    		content = request
			.execute()
			.returnContent();
    		
    		
    		String result = content.asString();
        	logger.warn("ticker=" + result);
        	Map<String, Object> jsonMap = JacksonHelper.getJsonMap(result);
        	Map<String, Object> tickerMap = (Map<String, Object>)jsonMap.get("tick");
        	List<Market> listMarket = new ArrayList<Market>(1);
        	Market market = new Market();
        	market.setAmount(new Double(tickerMap.get("vol").toString()));
        	market.setAsk(((List<Double>)tickerMap.get("ask")).get(0));
        	market.setAskVolume(((List<Double>)tickerMap.get("ask")).get(1));
        	market.setBid(((List<Double>)tickerMap.get("bid")).get(0));
        	market.setBidVolume(((List<Double>)tickerMap.get("bid")).get(1));
        	
        	market.setHighest(new Double(tickerMap.get("high").toString()));
        	market.setLowest(new Double(tickerMap.get("low").toString()));
        	market.setLast(new Double(tickerMap.get("close").toString()));
        	market.setOpen(new Double(tickerMap.get("open").toString()));
        	market.setSymbol(symbols[0]);
        	market.setTs(Long.valueOf(jsonMap.get("ts").toString()));
        	market.setVolume(new Double(tickerMap.get("amount").toString()));
        	
        	listMarket.add(market);
        	return listMarket;
		} catch (Exception e) {
			e.printStackTrace();
			super.logger.error(com.glory.common.Constants.EXCHANGE_NAME.EXCHANGE_HOUBI+":"+HoubiAPI.market_detail_merged+" 请求失败", e);
		}
    	
    	return null;
	}

	/**
	 * h:
		"data": {
		    "id": 消息id,
		    "ts": 最新成交时间,
		    "data": [
		      {
		        "id": 成交id,
		        "price": 成交价,
		        "amount": 成交量,
		        "direction": 主动成交方向,
		        "ts": 成交时间
		      }
		    ]
		  }
		{"id":17019178043,"ts":1534740234226,"data":[{"amount":9.9011,"ts":1534740234226,"id":1701917804310646552255,"price":0.087349,"direction":"buy"}]}
	 */
	@Override
	public DealtInfo getSettlementPrice(String accessKey, String secretkey, Integer size, String... symbols) {
		String param = Arrays.toString(symbols);
		
		ISignature signature = new HuobiParamsSigner();
		signature.setApiRoot(HoubiAPI.rest_market_root);
		signature.setApiUri(HoubiAPI.market_history_trade);
		signature.putParam(HoubiAPI.Market_history_trade_params.symbol.getCode(), StringUtils.trim(param.substring(1, (param.length() - 1))));
		if (1 <= size && size <= 2000) {
			signature.putParam(HoubiAPI.Market_history_trade_params.size.getCode(), size.toString());
		} else {
			signature.putParam(HoubiAPI.Market_history_trade_params.size.getCode(), "100");
		}
		
		String uri = signature.sign(accessKey, secretkey);
		logger.warn("uri="  + uri);
		
		Content content = null;
    	try {
    		Request request = Request.Get(uri.toString());
    		RequestUtils.setProxy(request);
    		HuobiProfiles.addHeader(request, HoubiAPI.market_history_trade_method_get);
    		content = request
			.execute()
			.returnContent();
    		
    		
    		String result = content.asString();
        	logger.warn("ticker=" + result);
        	Map<String, Object> jsonMap = JacksonHelper.getJsonMap(result);
        	List<Map<String, Object>> tickerList = (List<Map<String, Object>>)jsonMap.get("data");
        	DealtInfo dealtInfo = null;
        	Map<String, Object> item = null;
        	
        	long time = getSplittingTime();//确定应用服务器与交易所服务器时间一致
//        	for (Entry<String, Object> entry : tickerMap.entrySet()) {
//        		item = (Map<String, Object>) ((Map<String, Object>)entry.getValue()).get("data");
//        		if (Long.valueOf(item.get("ts").toString()).longValue() <= time) {
//        			System.out.println("ts=" +Long.valueOf(item.get("ts").toString()).longValue());
//        			market = new Market();
//        			break;
//        		}
//    		}
        	
        	for (Map<String, Object> map : tickerList) {
        		item = ((List<Map<String, Object>>) map.get("data")).get(0);
        		if (Long.valueOf(item.get("ts").toString()).longValue() <= time) {
        			System.out.println("ts=" +Long.valueOf(item.get("ts").toString()).longValue());
        			dealtInfo = new DealtInfo();
        			dealtInfo.setDirection(com.glory.common.Constants.TRADING_DIRECTION.valueOf(item.get("direction").toString().toUpperCase()));
        			dealtInfo.setId(item.get("id").toString());
        			dealtInfo.setPrice(new Double(item.get("price").toString()));
        			dealtInfo.setTs(Long.valueOf(item.get("ts").toString()));
        			dealtInfo.setVolume(new Double(item.get("amount").toString()));
        			break;
        		}
			}                                              
        	
        	return dealtInfo;
		} catch (Exception e) {
			e.printStackTrace();
			super.logger.error(com.glory.common.Constants.EXCHANGE_NAME.EXCHANGE_HOUBI+":"+HoubiAPI.market_history_trade+" 请求失败", e);
		}
    	
    	return null;
	}

	
	
}
