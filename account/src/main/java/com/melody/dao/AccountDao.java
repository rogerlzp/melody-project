package com.melody.dao;

import com.melody.user.dto.Account;
import com.melody.user.dto.UserLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuyw on 2015/12/11.
 */
public interface AccountDao {
    Account findUserAccountByUserId(@Param(value = "userId") long userId);

    void updateAccountBalance(@Param(value = "userId") long userId, @Param(value = "orderAmount") double orderAmount);//修改余额

    Account findMyAccountByUserId(@Param(value = "userId") long userId);

//    List<Coupon> findFinancialCouponByUserId(@Param(value = "userId") long userId);

    int countMyInvestment(@Param(value = "userId") long userId, @Param(value = "status") String status, @Param(value = "orderStatus") String orderStatus);

//    List<Investment> findMyInvestmentPage(InvestmentEnter ie);

    int insertAccount(Account account);//插入账户信息

//    int insertBirdCoinAccount(BirdCoinAccount birdCoinAccount);//插入鸟币账户

//    int insertBankAccount(BankAccount bankAccount);//插入银行卡信息

//    BankAccount findLatestReplaceBankAccount(@Param(value = "userId") long userId);//查询最新的换卡信息

//    UserWatercourse findUserWatercourseByOrderNo(@Param(value = "orderNo") String orderNo);//查询换卡流水

    int getReplaceCardCheckStatus(@Param(value = "userId") long userId);//判断换卡还是查询进度

    int updateReplaceCardStatus(@Param(value = "id") String id);//更新换卡还是查询进度

    Account findAccountTotalByUserId(@Param(value = "userId") long userId);//查询用户总资产


    int updateAccountUsableBalance(@Param(value = "id") String id, @Param(value = "usableBalance") double usableBalance);//修改总金额

    UserLevel findUserLevel(@Param(value = "userId") long userId);//获取级别

    int findPartnerDown(@Param(value = "userId") long userId);//获取下线

    int findUserIsStaff(@Param(value = "userId") long userId);//判断是否是内部人员

    int updateBirdCoinAccountAmount(@Param(value = "amount") double amount, @Param(value = "userId") long userId);//更新鸟币

    public int insertbirdCoinOrder(@Param(value = "id") long id, @Param(value = "goldenEggDetailId") String goldenEggDetailId,
                                   @Param(value = "user_id") long user_id, @Param(value = "io_type") String ioType, @Param(value = "type") String type,
                                   @Param(value = "amount") double amount);//插入鸟币水流

    public void insertUserLevel(UserLevel userLevel);

    public void promoteUserLevel(UserLevel userLevel);

//    List<BankAccount> findNoBkBankAccount(@Param(value = "startTime") String startTime);

    int updateBankAccountStatusByError(@Param(value = "orderNo") String orderNo, @Param(value = "userId") long userId);

//    List<Coupon> findAvailableCouponByUserId(@Param(value = "userId") long id);

//    BankResult getBankInfoByBankName(@Param(value = "userId") long id);

    int findsxtIsShow();

    int findAxtProductOrder(@Param(value = "mobileNo") String mobileNo);

    int updateAccountSubAmount(@Param(value = "orderId") String orderId, @Param(value = "trade_no") String trade_no,
                               @Param(value = "mer_date") String mer_date, @Param(value = "balance") String balance,
                               @Param(value = "subAmount") double subAmount, @Param(value = "userId") long userId);

    int updateExchangeCodeByUserId(@Param(value = "userId") long userId, @Param(value = "code") String code);
//    ExchangeCode findExchangeCode(@Param(value = "code") String code);


}

