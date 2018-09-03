package com.melody.web.controller.mobile;


import com.alibaba.dubbo.config.annotation.Reference;
import com.melody.mobile.api.MobileCodeService;
import com.melody.mobile.dto.MobileCodeEnter;
import com.melody.mobile.dto.MobileCodeResult;
import com.melody.web.base.BaseController;
import com.melody.web.util.JsonHelper;
import com.melody.web.vo.MobileCodeVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "mobile/getMobileCode")
public class MobileCodeController extends BaseController {

    private Log logger = LogFactory.getLog(this.getClass());

    private static final String SIGN_SECRET = "ltn$%^qpdhTH18";

    public static final String PRIVATE_KEY = "private_key";

    @Reference(group = "mobileCodeService")
    MobileCodeService mobileCodeService;

    @RequestMapping
    public ModelAndView execute(MobileCodeVO mobileCodeVO, BindingResult bindingResult, HttpServletRequest request) throws IOException {

//    	String clientSign = request.getHeader("header_sign");
//    	@SuppressWarnings("unchecked")
//		Map<String, ?> parameterMap = request.getParameterMap();
//    	List<String> keys = new ArrayList<String>(parameterMap.keySet());
//    	Map<String,String> map = new HashMap<String,String>();
//    	for (int i = 0; i < keys.size(); i++) {
//    		String key = keys.get(i);
//    		map.put(key, request.getParameter(key));
//    	}
//    	map.put(PRIVATE_KEY, SIGN_SECRET);
//		HashMap<String, String> SignHashMap = ParamSignUtils.sign(map, SIGN_SECRET);
//		String sign = SignHashMap.get("appSign");
//		if (!StringUtils.equals(clientSign, sign)) {
//			Map<String, Object> results = new HashMap<String,Object>();
//			results.put("resultCode", "9998");
//			return new ModelAndView("registerResult",results);
//		}
//        logger.info("mobileCodeForm" + mobileCodeForm.toString() +"sentType: " + mobileCodeForm.getSendType()) ;

        MobileCodeEnter mobileCodeEnter = new MobileCodeEnter();
        BeanUtils.copyProperties(mobileCodeVO, mobileCodeEnter);
        if (StringUtils.equals(mobileCodeEnter.getSendType(), "1")) {//如果是注册，需要校验验证码,android Or ios 不需要
            mobileCodeEnter.setMobileCode(mobileCodeVO.getPictureCode());
            mobileCodeEnter.setCertification(mobileCodeVO.getClientType());
        }

//        logger.info("mobileCodeForm" + mobileCodeEnter.toString() + " sentType: " +mobileCodeEnter.getSendType());

        checkIsMobileForm(mobileCodeVO, mobileCodeEnter);

        MobileCodeResult result = mobileCodeService.getMobileCode(mobileCodeEnter);
        Map<String, Object> results = JsonHelper.toRespJson(result);
        results.put("data", result);
        return new ModelAndView("registerResult", results);
    }

    private void checkIsMobileForm(MobileCodeVO mobileCodeVO, MobileCodeEnter mobileCodeEnter) {
        if (StringUtils.equals("M", mobileCodeVO.getClientType())) {
            //appVersion=1.0.2&app_client_id=1&channel=AppStore&clientType=M&mobileNo=13651845860&osVersion=8.3&sendType=1&sessionKey=
            if (StringUtils.isBlank(mobileCodeVO.getAppVersion()) || StringUtils.isBlank(mobileCodeVO.getChannel()) ||
                    StringUtils.isBlank(mobileCodeVO.getOsVersion()) || StringUtils.isBlank(mobileCodeVO.getApp_client_id())) {
                mobileCodeEnter.setCertification("H5");
            }
        }
    }
}
