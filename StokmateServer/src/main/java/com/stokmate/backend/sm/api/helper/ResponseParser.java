package com.stokmate.backend.sm.api.helper;

import com.stokmate.backend.sm.api.model.Stock;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amdesai on 3/6/15.
 */
public class ResponseParser {

    public List<Stock> parseSymbols(String response) throws Exception {
        List<Stock> stocks = new ArrayList<Stock>();
        response = response.replace("YAHOO.Finance.SymbolSuggest.ssCallback", "");
        response = response.replace("(", "");
        response = response.replace(")", "");
        JSONObject json = new JSONObject(response);
        JSONObject resultSet = json.getJSONObject("ResultSet");
        JSONArray result = resultSet.getJSONArray("Result");
        for (int i = 0; i < result.length(); i++) {
            Stock stock = new Stock(result.getJSONObject(i).getString("symbol"),
                    result.getJSONObject(i).getString("name"),
                    result.getJSONObject(i).getString("exch"),
                    result.getJSONObject(i).getString("type"),
                    result.getJSONObject(i).getString("exchDisp"),
                    result.getJSONObject(i).getString("typeDisp"),
                    null, null, null, null);
            stocks.add(stock);
        }
        return stocks;
    }

    public List<Stock> parseQuotes(String rawResp) throws Exception {
        List<Stock> stocks = new ArrayList<Stock>();
        JSONObject json = new JSONObject(rawResp);
        JSONObject query = json.getJSONObject("query");
        JSONObject results = query.getJSONObject("results");
        Object quoteObj = results.get("quote");
        if (quoteObj instanceof JSONArray) {
            JSONArray quotes = results.getJSONArray("quote");

            for (int i = 0; i < quotes.length(); i++) {
                Stock stock = new Stock(quotes.getJSONObject(i).getString("symbol"),
                        quotes.getJSONObject(i).getString("Name"),
                        null, null, null, null,
                        quotes.getJSONObject(i).getString("LastTradePriceOnly"),
                        quotes.getJSONObject(i).getString("Change"),
                        quotes.getJSONObject(i).getString("PercentChange"),
                        quotes.getJSONObject(i).getString("Volume"));
                stocks.add(stock);
            }
        } else {
            JSONObject quote = results.getJSONObject("quote");
            Stock stock = new Stock(quote.getString("symbol"),
                    quote.getString("Name"),
                    null, null, null, null,
                    quote.getString("LastTradePriceOnly"),
                    quote.getString("Change"),
                    quote.getString("PercentChange"),
                    quote.getString("Volume"));
            stocks.add(stock);

        }


        return stocks;
    }

}
