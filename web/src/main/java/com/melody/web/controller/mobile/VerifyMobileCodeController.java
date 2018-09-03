package com.melody.web.controller.mobile;


import com.melody.mobile.api.MobileCodeService;
import com.melody.mobile.dto.MobileCodeEnter;
import com.melody.mobile.dto.MobileCodeResult;
import com.melody.web.base.BaseController;
import com.melody.web.util.JsonHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * Created by liuyw on 2015/11/27.
 */

@Controller
@RequestMapping(value = "mobile/mobilecode/verifyMobileCode")
public class VerifyMobileCodeController extends BaseController {
//    @Autowired
//    MobileCodeService mobileCodeService;
//
//    @RequestMapping
//    public ModelAndView execute(MobileCodeForm mobileCodeForm, BindingResult bindingResult) throws IOException {
//        MobileCodeEnter mobileCodeEnter=new MobileCodeEnter();
//        BeanUtils.copyProperties(mobileCodeForm,mobileCodeEnter);
//
//        MobileCodeResult result=mobileCodeService.verifyMobileCode(mobileCodeEnter);
//        Map<String, Object> results = JsonHelper.toRespJson(result);
//        results.put("data", result);//verifyMobileCode
//        return new ModelAndView("registerResult", results);
//    }
}
