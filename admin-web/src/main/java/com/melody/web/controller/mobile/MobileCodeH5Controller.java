package com.melody.web.controller.mobile;


import com.melody.mobile.api.MobileCodeService;
import com.melody.mobile.dto.MobileCodeEnter;
import com.melody.mobile.dto.MobileCodeResult;
import com.melody.web.base.BaseController;
import com.melody.web.util.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "mobile/mobilecode/h5/getMobileCode")
public class MobileCodeH5Controller extends BaseController {

	private Log logger = LogFactory.getLog(this.getClass());
//
//	@Autowired
//	MobileCodeService mobileCodeService;
//
//	@RequestMapping
//	public ModelAndView execute(MobileCodeForm mobileCodeForm, BindingResult bindingResult, HttpServletRequest request) throws IOException {
//		Map<String, Object> errorResults = new HashMap<String, Object>();
//
//		if (StringUtils.isEmpty(mobileCodeForm.getPictureCode())) {
//			errorResults.put("resultCode", "-1");
//			errorResults.put("resultMessage", "请重新输入验证码！");
//			logger.warn("picture code error");
//			return new ModelAndView("registerResult", errorResults);
//		}
//		MobileCodeEnter mobileCodeEnter = new MobileCodeEnter();
//		BeanUtils.copyProperties(mobileCodeForm, mobileCodeEnter);
//		if (StringUtils.equals(mobileCodeEnter.getSendType(), "1")) {// 如果是注册，需要校验验证码,android
//																		// Or
//																		// ios
//																		// 不需要
//			mobileCodeEnter.setMobileCode(mobileCodeForm.getPictureCode());
//			mobileCodeEnter.setCertification(mobileCodeForm.getClientType());
//		}
//
//		checkIsMobileForm(mobileCodeForm, mobileCodeEnter);
//
//		MobileCodeResult result = mobileCodeService.getMobileCode(mobileCodeEnter);
//		Map<String, Object> results = JsonHelper.toRespJson(result);
//		results.put("data", result);
//		return new ModelAndView("registerResult", results);
//	}
//
//
//	private void checkIsMobileForm(MobileCodeForm mobileCodeForm, MobileCodeEnter mobileCodeEnter) {
//		mobileCodeEnter.setCertification("H5");
//	}
}
