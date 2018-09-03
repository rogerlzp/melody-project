package com.melody.system.dto;

import java.io.Serializable;

/**
 *
 */
public class DictItem implements Serializable {

    private static final long serialVersionUID = -1303627358141998347L;
    private String className;
    private String itemName;
    private String itemValue;
    private String sysName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
}
