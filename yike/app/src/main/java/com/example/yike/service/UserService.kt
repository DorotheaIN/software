package com.example.yike.service

import com.example.yike.model.*
import retrofit2.Call
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
//    @GET("login")
//    fun getLoginStatus(@Query("email") userEmail: String, @Query("password") passWord: String): Call<LoginResponse>
//
//    @GET("discuss")
//    fun getQuestionList(): Call<QuestionResponse>
//
//    @GET("recommenddis")
//    fun getQuestionByTheme(): Call<QThemeResponse>
//
//    @GET("answer")
//    fun getAnswerList(@Query("questionId") questionId: String): Call<AnswerResponse>

    @GET("followQuestion")
    fun postQuestionStatus(@Query("questionId") questionId: String, @Query("userId") userId: String): Call<PostResponse>

    @GET("checkQuestion")
    fun checkQuestionStatus(@Query("questionId") questionId: String, @Query("userId") userId: String): Call<CheckResponse>

    @GET("login")
    fun getLoginStatus(@Query("email") userEmail: String, @Query("password") passWord: String): Call<LoginResponse>

    @GET("discuss/getQuestionWithFollowNumAndLikeNum")
    fun getQuestionList(): Call<QuestionResponse>

    @GET("discuss/getQuestionWithFollowNumAndLikeNumAndAvatar")
    fun getQuestionByTheme(): Call<QThemeResponse>

    @POST("discuss/getAllAnswerByQuestionId")
    fun getAnswerList(@Query("questionId") questionId: String): Call<AnswerResponse>


    @GET("getAllActivities")//获取所有活动
    fun getActivityList(): Call<ActivityResponse>

    @GET("getActivityDetail")
    fun getActivityDetail(@Query("ID") activityID: Int):Call<ActivityDetailResponse>

    @GET("getReviewsByActivity")
    fun getEvaluationList(@Query("ID") activityID: Int):Call<EvaluationListResponse>

    @GET("getRecommendActivity")
    fun getActivityRecommended(@Query("ID") userID: String) :Call<ActivityResponse>

    @POST("likeActivity")
    fun postLikeActivity(@Query("activityID") activityID:Int,@Query("individualUserID") userID:String):Call<CheckResponse>

    @GET("checkLike")
    fun checkLike(@Query("activityID") activityID: Int,@Query("individualUserID") userID: String):Call<CheckResponse>

    @GET("checkSignUp")
    fun checkSubscribe(@Query("activityID") activityID: Int,@Query("individualUserID") userID: String):Call<CheckResponse>

    @GET("getOrganizationInfo")
    fun getOrganizationInfo(@Query("ID") id:Int):Call<OrganizationResponse>

    @GET("getAllActivityByOrg")
    fun getActivityListByOrganization(@Query("ID") organizationID: Int):Call<ActivityResponse>

    @GET("orglogin")
    fun getOrgLoginStatus(@Query("ID") id:Int,@Query("password")passWord: String):Call<OrganizationResponse>

    @POST("unlikelikeActivity")
    fun postDislikeActivity(@Query("activityID") activityID: Int,@Query("individualUserID") userID: String):Call<CheckResponse>

    @POST("signUpActivity")
    fun postSubActivity(@Query("activityID") activityID: Int,@Query("individualUserID") userID: String): Call<CheckResponse>

    @POST("cancleSignUp")
    fun postDisSubActivity(@Query("activityID") activityID: Int,@Query("individualUserID") userID: String): Call<CheckResponse>

    @POST("publishActivity")
    fun postPubActivity(@Query("capacity") capacity:Int,@Query("content") content:String,
                        @Query("date") date:String,@Query("form") form:String,
                        @Query("genres") genres:String,@Query("img") img:String,
                        @Query("introduction") intro:String, @Query("organizationID") organizationID: Int,
                        @Query("place") place:String,@Query("status") status:Int,@Query("title") title:String
    ):Call<CheckResponse>

    @POST("updateActivity")
    fun postUpdateActivity(@Query("ID") id:Int,@Query("capacity") capacity:Int,@Query("content") content:String,
                           @Query("date") data:String,@Query("form") form:String,
                           @Query("genres") genres:String,@Query("img") img:String,
                           @Query("introduction") intro:String, @Query("organizationID") organizationID: Int,
                           @Query("place") place:String,@Query("status") status:Int,@Query("title") title:String
    ):Call<CheckResponse>

    @POST("deleteActivity")
    fun postDeleteActivity(@Query("ID") id:Int):Call<CheckResponse>

    @GET("getEmotionalAnalysis")
    fun getReviewAnalysis(@Query("ID") id: Int):Call<EvaluationAnalysisResponse>

    @GET("getUserSubscribed")
    fun getSubscriberList(@Query("ID") id: Int):Call<SubscriberListResponse>

    @POST("reviewActivity")
    fun postReviewActivity(@Query("activityID") activityID: Int,@Query("content") content: String,@Query("individualUserID") userID: String,@Query("score") score:Int):Call<CheckResponse>

    @POST("deleteReview")
    fun postDeleteActivityReview(@Query("aID") activityID: Int,@Query("iID") userID: String):Call<CheckResponse>
}