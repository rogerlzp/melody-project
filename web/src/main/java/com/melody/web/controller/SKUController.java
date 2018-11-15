package com.melody.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.melody.base.GeneralEnter;
import com.melody.base.GeneralResult;
import com.melody.common.constant.BusinessCodes;
import com.melody.product.api.SKUService;
import com.melody.product.dto.*;
import com.melody.result.JsonApi;
import com.melody.user.api.LoginService;
import com.melody.user.dto.UserQueryEnter;
import com.melody.user.dto.UserQueryResult;
import com.melody.web.base.BaseController;
import com.melody.web.util.JsonHelper;
import com.melody.web.vo.BaseForm;
import com.melody.web.vo.LoginForm;
import com.melody.web.vo.SKUForm;
import com.melody.web.vo.SPUForm;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/sku")
public class SKUController extends BaseController {

    @Reference(group = "skuService", timeout = 20000)
    SKUService skuService;


    @RequestMapping("/list")
    public JsonApi get(LoginForm loginFrom, HttpServletRequest request, BindingResult bindingResult) {
        GeneralEnter generalEnter = new GeneralEnter();
        BeanUtils.copyProperties(loginFrom, generalEnter);
        List<SKU> skuList = skuService.getSKU(generalEnter);

        return JsonApi.isOk().message(skuList.get(0).getSkuNo());

    }


    @RequestMapping("/list2")
    public JsonApi get2(LoginForm loginFrom, HttpServletRequest request, BindingResult bindingResult) {
        GeneralEnter generalEnter = new GeneralEnter();
        BeanUtils.copyProperties(loginFrom, generalEnter);
        SKUListResult skuListResult = skuService.getSKUWithoutUserInfo(generalEnter);

        return JsonApi.isOk().code(BusinessCodes.SUCCESS).data(skuListResult);
    }

    @RequestMapping("/home/list")
    public GeneralResult getHomeRecommend(BaseForm baseForm, HttpServletRequest request, BindingResult bindingResult) {
        GeneralEnter generalEnter = new GeneralEnter();
        BeanUtils.copyProperties(baseForm, generalEnter);

        SKUListResult skuListResult = skuService.getSKUHomePage(generalEnter);

//        Map<String, Object> results = JsonHelper.toRespJson(skuResult);
//        results.put("data", skuResult);
        return GeneralResult.isOk().data(skuListResult);
    }


    /**
     * 列出所有的spuCode对应的SKU
     *
     * @param baseForm
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping("/home/ListSpu")
    public GeneralResult getHomeRecommend(SPUForm baseForm, HttpServletRequest request, BindingResult bindingResult) {
        SKUEnter skuEnter = new SKUEnter();
        BeanUtils.copyProperties(baseForm, skuEnter);

        SKUResult skuResult = skuService.getSKUHomeBySpu(skuEnter);

//        Map<String, Object> results = JsonHelper.toRespJson(skuResult);
//        results.put("data", skuResult);
        return GeneralResult.isOk().data(skuResult);
    }


    /**
     * 列出SKU 所有的图片，包含所有的FeatureList，所有的图片List， 对应的会员价格
     * TODO: 列出该SPU下所有的产品的销售属性，价格和数量列表
     *
     * @param baseForm
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping("/wx/detail")
    public Map<String, Object> getHomeRecommend(SKUForm baseForm, HttpServletRequest request, BindingResult bindingResult) {
        SKUEnter skuEnter = new SKUEnter();
        BeanUtils.copyProperties(baseForm, skuEnter);

        SKUDetailResult skuDetailResult = skuService.getSKUDetail(skuEnter);

        Map<String, Object> results = JsonHelper.toRespJson(skuDetailResult);
        results.put("data", skuDetailResult);
        return results;
    }



    /**
     * 列出SKU 所有的图片，包含所有的FeatureList，所有的图片List， 对应的会员价格
     * TODO: 列出该SPU下所有的产品的销售属性，价格和数量列表
     *
     * @param baseForm
     * @param request
     * @param bindingResult
     * @return
     */
    @RequestMapping("/wx/spuFilter")
    public GeneralResult getSPUFilter(SKUForm baseForm, HttpServletRequest request, BindingResult bindingResult) {
        SKUListEnter skuListEnter = new SKUListEnter();
        BeanUtils.copyProperties(baseForm, skuListEnter);

        SKUListResult skuListResult = skuService.getSKUListFilter(skuListEnter);

//        Map<String, Object> results = JsonHelper.toRespJson(skuListResult);
//        results.put("data", skuListResult);
        return GeneralResult.isOk().data(skuListResult);
    }
}
