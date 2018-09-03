package com.melody.mobile.api;


import com.melody.mobile.dto.MobileCodeEnter;
import com.melody.mobile.dto.MobileCodeResult;

public interface MobileCodeService {
    MobileCodeResult getMobileCode(MobileCodeEnter var1);

    MobileCodeResult verifyMobileCode(MobileCodeEnter var1);


}
