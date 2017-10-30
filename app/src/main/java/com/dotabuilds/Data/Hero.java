package com.dotabuilds.Data;

import java.io.Serializable;

/**
 * Created by Lei Chen on 2017/10/25.
 */

class Hero implements Serializable {
    private String name;
    private Integer id;
    private String localizedName;
    private final static long serialVersionUID = -2477045963919966875L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
