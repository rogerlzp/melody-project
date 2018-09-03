package com.melody.product.api;

import com.melody.base.GeneralResult;
import com.melody.product.dto.Brand;
import com.melody.product.dto.Category;

public interface AdminCategroyService {

    GeneralResult addCategory(Category category);
}
