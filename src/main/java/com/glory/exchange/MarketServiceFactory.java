package com.glory.exchange;

import com.glory.common.Constants;
import com.glory.exchange.market.BinanceMarketService;
import com.glory.exchange.market.CoinexMarketService;
import com.glory.exchange.market.HuobiMarketService;
import com.glory.exchange.market.IMarketService;
import com.glory.exchange.market.OkexMarketService;
/**
 * @Author : glory
 * @since  : 2020-11-29
 */
public class MarketServiceFactory {

	public static IMarketService getInstance(Constants.EXCHANGE_NAME exchange) {
		IMarketService iIMarketService = null;
		if (Constants.EXCHANGE_NAME.EXCHANGE_HOUBI.equals(exchange)) {
			iIMarketService = new HuobiMarketService();
		} else if (Constants.EXCHANGE_NAME.EXCHANGE_COINEX.equals(exchange)) {
			iIMarketService = new CoinexMarketService();
		} else if (Constants.EXCHANGE_NAME.EXCHANGE_OKEX.equals(exchange)) {
			iIMarketService = new OkexMarketService();
		} else if (Constants.EXCHANGE_NAME.EXCHANGE_BINANCE.equals(exchange)) {
			iIMarketService = new BinanceMarketService();
		}
		
		return iIMarketService;
	}
	
}
