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

    @IntDef({BottomSheetType.SHARE, BottomSheetType.EDIT})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
    @interface BottomSheetType {
        int SHARE = 1;
        int EDIT = 2;
    }

    /**
     * @param bottomSheetType int
     * @return IBottomSheet
     */
    public static IBottomSheet getBottomSheet(@BottomSheetType int bottomSheetType) {
        if (bottomSheetType == BottomSheetType.SHARE) {
            return new ShareBottomSheet();
        } else {
            return new EditBottomSheet();
        }
    }
}
