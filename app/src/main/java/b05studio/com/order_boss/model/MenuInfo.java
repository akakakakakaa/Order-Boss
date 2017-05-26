package b05studio.com.order_boss.model;

/**
 * Created by mansu on 2017-05-20.
 */

public class MenuInfo {
    private String url;
    private String name;
    private int price;

    public MenuInfo(String url, String name, int price) {
        this.url = url;
        this.name = name;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
