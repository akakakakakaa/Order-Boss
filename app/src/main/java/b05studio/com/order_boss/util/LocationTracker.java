package b05studio.com.order_boss.util;

import android.location.Location;

/**
 * Created by young on 2017-06-01.
 */

public class LocationTracker {

    private static Location curLoc;

    public static Location getCurLoc() {
        return curLoc;
    }

    public static void setCurLoc(Location curLoc) {
        LocationTracker.curLoc = curLoc;
    }
}
