package com.munro.library;

import java.util.List;

public enum MunroListEnum {
    INSTANCE;

    List<Munro> munroList;

    public void setMunroList(List<Munro> munros){
        this.munroList = munros;
    }

    public List<Munro> getMunroList() {
        return munroList;
    }
}