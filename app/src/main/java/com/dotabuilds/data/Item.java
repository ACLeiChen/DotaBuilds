package com.dotabuilds.data;

import java.io.Serializable;

/**
 * Created by Lei Chen on 2017/10/25.
 */

public class Item implements Serializable {
    private Integer id;
    private String name;
    private final static long serialVersionUID = -6630966444884339024L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
