package com.positiveculture.app.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * TypefaceUtil
 * Created by hungdn on 6/15/2017.
 */

public class TypefaceUtil {
  /**
   * Using reflection to override default typeface
   * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT TYPEFACE WHICH WILL BE OVERRIDDEN
   * @param context to work with assets
   * @param defaultFontNameToOverride for example "monospace"
   * @param customFontFileNameInAssets file name of the font from assets
   */
  public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
    try {
      final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

      final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
      defaultFontTypefaceField.setAccessible(true);
      defaultFontTypefaceField.set(null, customFontTypeface);
    } catch (Exception e) {
      Log.e("TypefaceUtil", "Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
    }
  }
}
