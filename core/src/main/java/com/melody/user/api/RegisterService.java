package com.melody.user.api;

import com.melody.user.dto.RegisterEnter;
import com.melody.user.dto.RegisterResult;

public interface RegisterService {

    RegisterResult getPictureCode(RegisterEnter var1);

    RegisterResult registerUser(RegisterEnter var1);
}
