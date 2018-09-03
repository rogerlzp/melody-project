package com.melody.base;

import lombok.Data;

@Data
public class Sequence {

    private String tablename;

    private long currentValue;

    private long increment;

    private long cache;


}