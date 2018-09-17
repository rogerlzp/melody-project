package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.admin.api.AdminBannerService;
import com.melody.admin.api.AdminBrandService;
import com.melody.product.dto.Banner;

import java.util.List;


@Service(group = "adminBannerService", timeout = 10000)
public class AdminBannerServiceImpl implements AdminBannerService {

    @Override
    public List<Banner> queryAllBanner() {
        return null;
    }

    @Override
    public int insertBanner(Banner banner) {
        return 0;
    }

    @Override
    public int updateBanner(Banner banner) {
        return 0;
    }
}
