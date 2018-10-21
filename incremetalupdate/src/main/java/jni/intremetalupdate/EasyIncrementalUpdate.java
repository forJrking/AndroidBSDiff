package jni.intremetalupdate;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by yale on 2017/12/3.
 */

public class EasyIncrementalUpdate {

    static {
        System.loadLibrary("eiu-bspatch");
    }

    /**
     * @param oldPath   old apk path
     * @param newPath   new created apk path
     * @param patchPath patch apk path
     * @return
     */
    public synchronized static native boolean patch(String oldPath, String newPath, String patchPath);

    /**
     * @param context
     * @return
     */
    public static String getApkSourceDir(Context context) {
        try {
            ApplicationInfo appInfo = context.getApplicationContext().getPackageManager()
                    .getApplicationInfo(context.getApplicationContext().getPackageName(), 0);
            return appInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param context
     * @param newApkPath
     * @param patchApkPath
     * @return
     */
    public synchronized static boolean patch(Context context, String newApkPath, String patchApkPath) {
        return patch(getApkSourceDir(context), newApkPath, patchApkPath);
    }
}
