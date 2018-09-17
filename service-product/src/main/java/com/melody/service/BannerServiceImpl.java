package com.melody.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.melody.common.constant.BusinessCodes;
import com.melody.dao.BannerDao;
import com.melody.product.api.BannerService;
import com.melody.product.dto.Banner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhengpingli on 2017/6/24.
 */
@Service(group="bannerService", timeout=10000)
public class BannerServiceImpl implements BannerService {


    @Autowired
    BannerDao bannerDao;

    @Autowired
    BaseServiceImpl baseService;

    @Override
    public List<Banner> getHomeBanner() {

        List<Banner> bannerList = bannerDao.queryHomepageBanners();

        return bannerList;
    }


//    @Override
//    public BannerListResult findAllBanner(BannerListEnter bannerListEnter) {
//        BannerListResult bannerListResult = new BannerListResult();
//        bannerListResult.setCode(BusinessCodes.SUCCESS);
//        // bannersList列表
//        List<BannerList> bannerList = bannerDao.queryAllBanners();
//        bannerListResult.setBannerList(bannerList);
//        return bannerListResult;
//    }

//
//    @Override
//    public String saveBanner(BannerCmsEnter bannerCmsEnter) {
//        String resultString = "";
//
//        if ("".equals(bannerCmsEnter.getBannerId()) || null == bannerCmsEnter.getBannerId()) {
//            // 生成新的cloan对象
//            BannerCms bannerCms = new BannerCms();
//            BeanUtils.copyProperties(bannerCmsEnter, bannerCms);
//
//            // 生成ID
//            int id = baseService.getNextSequence("TT_BANNER");
//            bannerCms.setBannerId(id);
//            bannerCms.setBannerState("1");
//
//            // 插入产品表
//            int cloanId = bannerDao.saveBanner(bannerCms);
//
//            if (cloanId > 0) {
//                resultString = "ok";
//            }
//        }
//        return resultString;
//    }
//
//    @Override
//    public String updateBanner(BannerCmsEnter bannerCmsEnter) {
//
//        String resultString = "";
//        BannerCms bannerCms = new BannerCms();
//
//        BeanUtils.copyProperties(bannerCmsEnter, bannerCms);
//
////        if (bannerCmsEnter.getBannerId() != null) {
////      //      log.info("手动更新ID");
//        bannerCms.setBannerId(Integer.parseInt(bannerCmsEnter.getBannerId()));
////        }
//        int rows = bannerDao.updateBanner(bannerCms);
//
//        if (rows > 0) {
//            resultString = "ok";
//        }
//        return resultString;
//    }
//
//    @Override
//    public BannerDetailResult findBannerDetail(BannerDetailEnter bannerDetailEnter) {
//        BannerDetailResult bannerDetailResult =
//                bannerDao.findBannerDetailById(Integer.parseInt(bannerDetailEnter.getBannerId()));
//
//        bannerDetailResult.setCode(BusinessCodes.SUCCESS);
//        if (bannerDetailResult != null) {
//            return bannerDetailResult;
//        }
//
//        return bannerDetailResult;
//    }


}