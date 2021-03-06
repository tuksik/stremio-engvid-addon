package co.anbora.labs.engvid.data.remote.api;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface EnglishVideoAPI {

    @GET("/wp-json/wp/v2/posts")
    Call<List<LessonInfoDTO>> getLessonsByPage(@Query("page") Integer page,
                                               @Query("per_page") Integer maxItems);

    @GET("/{slug}/")
    Call<ResponseBody> getMediaInfoBySlug(@Path("slug") String slug);

}
