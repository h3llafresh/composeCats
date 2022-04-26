package by.vlfl.composecats.data.remote.api

import by.vlfl.composecats.data.remote.model.CatJson
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatsApiService {
    @GET("v1/images/search")
    suspend fun fetchCats(
        @Header("x-api-key") xApiKey: String,
        @Query("size") imageSize: String = "full",
        @Query("limit") numberOfCatsPerPage: Int = 10,
        @Query("page") pageNumber: Int = 1,
        @Query("order") order: String = "DESC",
        @Query("has_breeds") hasBreeds: Int = 1,
        @Query("attach_breeds") attachBreeds: Int = 1
    ): List<CatJson>
}