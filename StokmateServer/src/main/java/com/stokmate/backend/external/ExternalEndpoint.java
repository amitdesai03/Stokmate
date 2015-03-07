package com.stokmate.backend.external;

/**
 * Created by amdesai on 3/6/15.
 */
public class ExternalEndpoint {
    public static final String YAHOO_SYMBOL_LOOKUP = "http://autoc.finance.yahoo.com/autoc?query=$1$&callback=YAHOO.Finance.SymbolSuggest.ssCallback";
    public static final String YAHOO_QUOTE_LOOKUP = "http://query.yahooapis.com/v1/public/yql?q=$1$&diagnostics=false&env=http://datatables.org/alltables.env&format=json";
    public static final String QUOTE_YQL ="select * from yahoo.finance.quotes where symbol in ($1$)";
}
