package com.melody.user.api;


import com.melody.annotation.Role;
import com.melody.user.dto.UserQueryEnter;
import com.melody.user.dto.UserQueryResult;

public interface LoginService {
    UserQueryResult doLogin(UserQueryEnter var1);

    @Role
    UserQueryResult doLogout(UserQueryEnter var1);
}
