/*
 * Copyright (c) 2009-2016 SHENZHEN Eternal Dynasty Technology Co.,Ltd.
 * All rights reserved.
 *
 * This file contains valuable properties of  SHENZHEN Eternal Dynasty
 * Technology Co.,Ltd.,  embodying  substantial  creative efforts  and
 * confidential information, ideas and expressions.    No part of this
 * file may be reproduced or distributed in any form or by  any  means,
 * or stored in a data base or a retrieval system,  without  the prior
 * written permission  of  SHENZHEN Eternal Dynasty Technology Co.,Ltd.
 *
 */

package cn.com.dyninfo.o2o.furniture.bitmap.xutils.bitmap;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.com.dyninfo.o2o.furniture.bitmap.xutils.bitmap.core.BitmapSize;
import cn.com.dyninfo.o2o.furniture.bitmap.xutils.util.LogUtils;

import java.io.File;
import java.lang.reflect.Field;

public class BitmapCommonUtils {

    /**
     * @param context
     * @param dirName Only the folder name, not full path.
     * @return app_cache_path/dirName
     */
    public static String getDiskCacheDir(Context context, String dirName) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                cachePath = externalCacheDir.getPath();
            }
        }
        if (cachePath == null) {
            cachePath = context.getCacheDir().getPath();
        }

        return cachePath + File.separator + dirName;
    }

    public static long getAvailableSpace(File dir) {
        try {
            final StatFs stats = new StatFs(dir.getPath());
            return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
        } catch (Throwable e) {
            LogUtils.e(e.getMessage(), e);
            return -1;
        }

    }

    private static BitmapSize screenSize = null;

    public static BitmapSize getScreenSize(Context context) {
        if (screenSize == null) {
            screenSize = new BitmapSize();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            screenSize.setWidth(displayMetrics.widthPixels);
            screenSize.setHeight(displayMetrics.heightPixels);
        }
        return screenSize;
    }

    public static BitmapSize optimizeMaxSizeByView(View view, int maxImageWidth, int maxImageHeight) {
        int width = maxImageWidth;
        int height = maxImageHeight;

        if (width > 0 && height > 0) {
            return new BitmapSize(width, height);
        }

        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null) {
            if (params.width > 0) {
                width = params.width;
            } else if (params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
                width = view.getWidth();
            }

            if (params.height > 0) {
                height = params.height;
            } else if (params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                height = view.getHeight();
            }
        }

        if (width <= 0) width = getImageViewFieldValue(view, "mMaxWidth");
        if (height <= 0) height = getImageViewFieldValue(view, "mMaxHeight");

        BitmapSize screenSize = getScreenSize(view.getContext());
        if (width <= 0) width = screenSize.getWidth();
        if (height <= 0) height = screenSize.getHeight();

        return new BitmapSize(width, height);
    }

    private static int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        if (object instanceof ImageView) {
            try {
                Field field = ImageView.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                int fieldValue = (Integer) field.get(object);
                if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                    value = fieldValue;
                }
            } catch (Throwable e) {
            }
        }
        return value;
    }
}
