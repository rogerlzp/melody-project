package com.melody.mobile.api;


import com.melody.base.GeneralResult;
import com.melody.mobile.dto.MobileCodeEnter;
import com.melody.mobile.dto.MobileCodeResult;

public interface MobileCodeService {
    GeneralResult getMobileCode(MobileCodeEnter var1);
    GeneralResult verifyMobileCode(MobileCodeEnter var1);
}
