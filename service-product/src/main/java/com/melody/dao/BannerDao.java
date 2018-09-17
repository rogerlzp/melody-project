package com.melody.dao;

import com.melody.product.dto.Banner;

import java.util.List;

/**
 * Created by liuyw on 2015/12/12.
 */
public interface BannerDao {

    List<Banner> queryHomepageBanners();

    List<Banner> queryAllBanners();


//    int saveBanner(BannerCms bannerCms);
//
//    int updateBanner(BannerCms bannerCms);
//
//
//    BannerDetailResult findBannerDetailById(@Param(value = "bannerId") Integer bannerId);
}
