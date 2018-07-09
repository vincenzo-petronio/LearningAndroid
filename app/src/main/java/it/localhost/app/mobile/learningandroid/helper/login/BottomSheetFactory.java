package it.localhost.app.mobile.learningandroid.helper.login;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author vincenzo.petronio on 23/02/2018.
 */

public class BottomSheetFactory {

    public static final int SHARE = 1;
    public static final int EDIT = 2;

    @IntDef({SHARE, EDIT})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @interface BottomSheetTypeDef {
    }

    /**
     * @param bottomSheetType int
     * @return IBottomSheet
     */
    public static IBottomSheet getBottomSheet(@BottomSheetTypeDef int bottomSheetType) {
        if (bottomSheetType == SHARE) {
            return new ShareBottomSheet();
        } else {
            return new EditBottomSheet();
        }
    }
}
