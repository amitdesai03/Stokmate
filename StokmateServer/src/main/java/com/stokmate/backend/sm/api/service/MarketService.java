package com.stokmate.backend.sm.api.service;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.stokmate.backend.external.ExternalEndpoint;
import com.stokmate.backend.external.ExternalCallClient;
import com.stokmate.backend.sm.api.helper.ResponseParser;
import com.stokmate.backend.sm.api.model.Stock;

import java.util.List;

import javax.inject.Named;

@Api(
        name = "sm",
        version = "v1",
        description = "Stokmate service",
        namespace = @ApiNamespace(
                ownerDomain = "backend.stokmate.com",
                ownerName = "backend.stokmate.com",
                packagePath = ""
        )
)
public class MarketService {
    private final ExternalCallClient extClient = new ExternalCallClient();
    private final ResponseParser parser = new ResponseParser();

    @ApiMethod(
            name = "searchStocks",
            path = "stocks/lookup/{query}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public List<Stock> searchStocks(@Named("query")String query){
        List<Stock> response = null;
        try{
            String rawResp = extClient.fetch(ExternalEndpoint.YAHOO_SYMBOL_LOOKUP.replace("$1$", query));
            response = parser.parseSymbols(rawResp);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @ApiMethod(
            name = "quoteStock",
            path = "stocks/quote/{query}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public List<Stock> quoteStock(@Named("query")String query){

        List<Stock> response = null;
        try{
            String yql = extClient.encode(ExternalEndpoint.QUOTE_YQL.replace("$1$", query));
            String rawResp = extClient.fetch(ExternalEndpoint.YAHOO_QUOTE_LOOKUP.replace("$1$", yql));
            response = parser.parseQuotes(rawResp);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
