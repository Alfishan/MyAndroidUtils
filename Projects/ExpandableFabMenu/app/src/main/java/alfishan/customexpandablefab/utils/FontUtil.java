package alfishan.customexpandablefab.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * Created by root on 10/8/17.
 */
public class FontUtil
{
    public static String FONT_Montserrat_SEMI_BOLD = "fonts/MontserratSemibold.ttf";
    public static String FONT_Montserrat_LIGHT = "fonts/MontserratLight.ttf";
    public static String FONT_Montserrat_MEDUIM;
    public static String FONT_Montserrat_REGULAR = "fonts/MontserratRegular.ttf";
    private final AssetManager mAssets;

    static
    {
        FONT_Montserrat_MEDUIM = "fonts/MontserratMedium.ttf";
    }

    public FontUtil(AssetManager paramAssetManager)
    {
        this.mAssets = paramAssetManager;
    }

    public Typeface getMontserratDemiBold()
    {
        return TypefaceUtils.load(this.mAssets, FONT_Montserrat_SEMI_BOLD);
    }

    public Typeface getMontserratMedium()
    {
        return TypefaceUtils.load(this.mAssets, FONT_Montserrat_MEDUIM);
    }

    public Typeface getMontserratRegular()
    {
        return TypefaceUtils.load(this.mAssets, FONT_Montserrat_REGULAR);
    }

    public Typeface getMontserratLight()
    {
        return TypefaceUtils.load(this.mAssets, FONT_Montserrat_LIGHT);
    }

}
