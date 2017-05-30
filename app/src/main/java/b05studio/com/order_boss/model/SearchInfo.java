package b05studio.com.order_boss.model;

import java.util.Calendar;

/**
 * Created by mansu on 2017-05-31.
 */

public class SearchInfo {
    private Calendar calendar;
    private String keyword;

    public SearchInfo(Calendar calendar, String keyword) {
        this.calendar = calendar;
        this.keyword = keyword;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
