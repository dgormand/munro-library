package com.munro.library.entity;

import java.util.List;

public enum MunroEnum {
    INSTANCE;

    List<Munro> munroList;

    public void setMunroList(List<Munro> munros){
        this.munroList = munros;
    }

    public List<Munro> getMunroList() {
        return munroList;
    }
}