package com.melody.mobile.dto;

import com.melody.base.GeneralResult;
import lombok.Data;

import java.io.Serializable;

@Data
public class MobileCodeResult extends GeneralResult implements Serializable {

    private static final long serialVersionUID = -523113201808271802L;


    private String certification;

}
