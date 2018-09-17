package com.melody.product.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Banner implements Serializable {
    private static final long serialVersionUID = 819344592132312698L;
    private int bannerId;
    private String bannerName;
    private String bannerUrl;
    private String linkUrl;
    private String bannerState;

}