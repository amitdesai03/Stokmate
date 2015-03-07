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

    public List<Stock> parseSymbols(String response) throws Exception{
        List<Stock> stocks = new ArrayList<Stock>();
        response=response.replace("YAHOO.Finance.SymbolSuggest.ssCallback","");
        response=response.replace("(","");
        response=response.replace(")","");
        JSONObject json = new JSONObject(response);
        JSONObject resultSet =json.getJSONObject("ResultSet");
        JSONArray result = resultSet.getJSONArray("Result");
        for(int i=0;i<result.length();i++){
            Stock stock = new Stock(result.getJSONObject(i).getString("symbol"),
                    result.getJSONObject(i).getString("name"),
                    result.getJSONObject(i).getString("exch"),
                    result.getJSONObject(i).getString("type"),
                    result.getJSONObject(i).getString("exchDisp"),
                    result.getJSONObject(i).getString("typeDisp"));
            stocks.add(stock);
        }
        return stocks;
    }
}
