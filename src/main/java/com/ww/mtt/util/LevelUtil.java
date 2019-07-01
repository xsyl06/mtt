package com.ww.mtt.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    public static final String SPLITFLAG=".";
    public static final String ROOT = "0";

    /**
     * 等级计算方法
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        }else {
            return StringUtils.join(parentLevel, SPLITFLAG, parentId);
        }
    }
}
