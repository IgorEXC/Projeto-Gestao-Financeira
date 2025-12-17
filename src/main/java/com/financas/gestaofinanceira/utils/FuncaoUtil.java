package com.financas.gestaofinanceira.utils;

import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;

public interface FuncaoUtil {

    default BigDecimal getSafeValue(BigDecimal value) {
        return ObjectUtils.defaultIfNull(value, BigDecimal.ZERO);
    }
}
