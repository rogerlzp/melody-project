package com.melody.base;


import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralEnter implements Serializable {
    private static final long serialVersionUID = -8015527491830557481L;
    private String version;
    private String timestamp;
    private String clientType;
    private String sessionKey;
    private Integer pageSize = 10;
    private Integer currentPage = 0;

    public GeneralEnter() {
    }
}
