package com.stokmate.backend.sm.api.model;

/**
 * Created by amdesai on 3/5/15.
 */
public class Stock {
    String symbol;
    String name;
    String exch;
    String type;
    String exchDisp;
    String typeDisp;

    public Stock(){

    }

    public Stock(String symbol, String name, String exch, String type, String exchDisp, String typeDisp) {
        this.symbol = symbol;
        this.name = name;
        this.exch = exch;
        this.type = type;
        this.exchDisp = exchDisp;
        this.typeDisp = typeDisp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExch() {
        return exch;
    }

    public void setExch(String exch) {
        this.exch = exch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExchDisp() {
        return exchDisp;
    }

    public void setExchDisp(String exchDisp) {
        this.exchDisp = exchDisp;
    }

    public String getTypeDisp() {
        return typeDisp;
    }

    public void setTypeDisp(String typeDisp) {
        this.typeDisp = typeDisp;
    }
}
