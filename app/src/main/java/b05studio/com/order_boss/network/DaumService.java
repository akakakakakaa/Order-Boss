package b05studio.com.order_boss.network;

import java.util.List;

import b05studio.com.order_boss.model.DaumLocalInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by young on 2017-05-29.
 */

public interface DaumService {
    @GET("local/v1/search/keyword.json")
    Call<List<DaumLocalInfo>> listKeywordRestaurant(
             @Query("apikey") String apikey,
             @Query("query") String query,
             @Query("location") String location,
             @Query("radius") int radius
             );

    // TODO: 2017-05-29  식당 태그별 카테고리 만들기.
    // 일반 음식점 코드 FD6
    @GET("local/v1/search/category.json")
    Call<List<DaumLocalInfo>> listCategoryRestaurant();

            
}
