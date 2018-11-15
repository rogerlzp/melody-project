package com.melody.user.api;


import com.melody.annotation.Role;
import com.melody.base.GeneralResult;
import com.melody.user.dto.UserQueryEnter;
import com.melody.user.dto.UserQueryResult;

public interface LoginService {
    GeneralResult doLogin(UserQueryEnter var1);

    @Role
    GeneralResult doLogout(UserQueryEnter var1);
}
