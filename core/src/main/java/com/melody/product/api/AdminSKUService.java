package com.melody.product.api;

import com.melody.base.GeneralResult;
import com.melody.product.dto.Feature;
import com.melody.product.dto.FeatureOption;
import com.melody.product.dto.SKU;
import com.melody.product.dto.SPU;

public interface AdminSKUService {

    GeneralResult addFeature(Feature feature);

    GeneralResult addFeatureOption(FeatureOption featureOption);

    GeneralResult addSPU(SPU spu);

    GeneralResult addSKU(SKU sku);


}
