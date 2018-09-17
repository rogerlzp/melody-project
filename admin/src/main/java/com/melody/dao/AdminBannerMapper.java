package com.melody.dao;

import com.melody.product.dto.Banner;

import java.util.List;

public interface AdminBannerMapper {
    List<Banner> queryAllBanners();


    int saveBanner(Banner bannerCms);

    int updateBanner(Banner bannerCms);
//
}
