package com.melody.user.api;

import com.melody.exception.TxException;
import com.melody.user.dto.User;

public interface UserService {

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    User selectUserBy(User user);

    /**
     * 注册用户
     *
     * @param user
     * @return
     * @throws TxException
     */
    Integer saveUser(User user) throws TxException;



}
