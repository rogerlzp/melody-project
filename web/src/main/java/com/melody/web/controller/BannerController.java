package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.melody.base.GeneralEnter;
import com.melody.product.api.BannerService;
import com.melody.product.dto.Banner;
import com.melody.product.dto.SKU;
import com.melody.result.JsonApi;
import com.melody.web.base.BaseController;
import com.melody.web.vo.LoginForm;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/banner")
public class BannerController extends BaseController {

    @Reference(group = "bannerService", timeout = 10000)
    BannerService bannerService;

    @RequestMapping("/list")
    public JsonApi get(HttpServletRequest request) {

        List<Banner> bannerList = bannerService.getHomeBanner();
        return JsonApi.isOk().data(bannerList);
    }
}
