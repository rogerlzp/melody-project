package com.melody.user.api;

import com.melody.base.GeneralResult;
import com.melody.user.dto.RegisterEnter;
import com.melody.user.dto.RegisterResult;
import sun.java2d.loops.FillRect;

public interface RegisterService {

    GeneralResult<RegisterResult>   getPictureCode(RegisterEnter var1);

    GeneralResult<RegisterResult>   registerUser(RegisterEnter var1);
}
