package b05studio.com.order_boss.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DaumLocalInfo {

    @SerializedName("channel")
    @Expose
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public class Channel {

        @SerializedName("item")
        @Expose
        private List<Item> item = null;
        @SerializedName("info")
        @Expose
        private Info info;

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

    }

    public class Info {

        @SerializedName("count")
        @Expose
        private String count;
        @SerializedName("page")
        @Expose
        private String page;
        @SerializedName("totalCount")
        @Expose
        private String totalCount;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }
    }

    public class Item {

        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("newAddress")
        @Expose
        private String newAddress;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("direction")
        @Expose
        private String direction;
        @SerializedName("zipcode")
        @Expose
        private String zipcode;
        @SerializedName("placeUrl")
        @Expose
        private String placeUrl;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("distance")
        @Expose
        private String distance;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("addressBCode")
        @Expose
        private String addressBCode;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNewAddress() {
            return newAddress;
        }

        public void setNewAddress(String newAddress) {
            this.newAddress = newAddress;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getPlaceUrl() {
            return placeUrl;
        }

        public void setPlaceUrl(String placeUrl) {
            this.placeUrl = placeUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAddressBCode() {
            return addressBCode;
        }

        public void setAddressBCode(String addressBCode) {
            this.addressBCode = addressBCode;
        }

    }
}





