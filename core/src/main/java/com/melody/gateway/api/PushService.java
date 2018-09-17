package com.melody.gateway.api;

import com.melody.gateway.dto.PushEnter;
import com.melody.gateway.dto.PushResult;

/**
 * 
 * Created by zhanggt on 2016-2-18.
 * 
 */
public interface PushService {

	PushResult push(PushEnter pushEnter);
}
