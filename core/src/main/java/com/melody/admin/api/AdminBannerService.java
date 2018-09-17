package com.melody.admin.api;

import com.melody.product.dto.Banner;

import java.util.List;

public interface AdminBannerService {

    List<Banner> queryAllBanner();

    int insertBanner(Banner banner);

    int updateBanner(Banner banner);
}

