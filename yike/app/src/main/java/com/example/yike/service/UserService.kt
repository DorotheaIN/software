package com.example.yike.service

import com.example.yike.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

//token在此处统一添加即可


interface UserService {
    @POST("addFile")//可以加Path关键字 进一步说明地址
    fun fileUpload(@Query("addPath") path: String, @Body requestBody: RequestBody):Call<UploadResponse>

    @POST("discuss/takeAntiFocusQuestion")
    fun postQuestionStatus(@Query("questionId") questionId: String, @Query("userId") userId: String): Call<PostResponse>

    @GET("discuss/checkFocusQuestion")
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

    @POST("signUpActivity")//token测试接口
    fun postSubActivity(@Header("satoken") myToken:String?, @Query("activityID") activityID: Int, @Query("individualUserID") userID: String): Call<CheckResponse>

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


    @GET("getMyFocusQuestions")
    fun getFollowQuestionsList(@Query("ID")id:String):Call<FollowQuestionResponse>

    @GET("getMyQuestions")
    fun getPublishQuestionList(@Query("ID") id:String):Call<PublishQuestionResponse>

    @GET("/sendEmail")
    fun sendEmail(@Query("email")email:String):Call<SendEmailResponse>

    @POST("signUp")
    fun getPersonRegister(@Query("email")email:String,@Query("name")name:String,@Query("password")password:String):Call<getPersonRegistResponse>

    @GET("discuss/getAllCommentByQuestionIdAndAnswerId")
    fun getAllCommentByQuestionIdAndAnswerId(@Query("answerId")answerId:Int,@Query("questionId")questionId:Int):Call<GetAllCommentByQuestionIdAndAnswerIdResponse>

    @POST("discuss/addAnswer")
    fun addAnswer(@Query("content")content:String,@Query("questionId")questionId: String,@Query("userId")userId: String):Call<AddAnswerResponse>

    @POST("discuss/addComment")
    fun comment(@Query("answerId")answerId: String,@Query("content")content: String,@Query("userId")userID: String):Call<CommentResponse>

    @GET("getMyActivities")
    fun getMyActivities(@Query("email") email: String):Call<ActivityResponse>

    @POST("orgsignUp")
    fun officialRegister(@Query("avator")avator:String,@Query("certification")certification:String,@Query("introduction")introduction:String,@Query("password")password:String,@Query("userName")userName:String):Call<OfficialRegisterResponse>

    @POST("discuss/addQuestion")
    fun addQuestion(@Query("content")content:String,@Query("title")title:String,@Query("userId")userId:String):Call<AddQuestionResponse>

    @GET("/filterActivity")
    fun getFilteredActivity(@Query("genres")genres: String,@Query("isAbleToRe")subState:String,@Query("key")key:String,@Query("status")state:String):Call<ActivityResponse>

    @GET("/getApplications")
    fun getApplications():Call<GetApplicationResponse>

    @POST("/updateOUserStatus")
    fun updateOUserStatus(@Query("ID")ID:String,@Query("flag")flag:String):Call<UpdateOUserStatusResponse>

    @POST("/sendEmailOFresult")
    fun postApplyResult(@Query("content")content:String,@Query("title")title:String,@Query("to")to:String):Call<PostApplyResultResponse>

    @POST("/AdminLogin")
    fun adminLogin(@Query("ID")ID:String,@Query("password")password: String):Call<AdminLoginResponse>

    @GET("/getReports")
    fun getReports():Call<GetReportsResponse>

    @POST("/updateIUserStatus")
    fun updateIUserStatus(@Query("ID")ID: String,@Query("flag")flag: String):Call<UpdateIUserStatusResponse>

    @POST("/reportUser")
    fun reportUser(@Query("aID")aID:String, @Query("qID")qID:String, @Query("rID")rID:String, @Query("reason")reason:String, @Query("wID")wID:String):Call<ReportUserResponse>

    @GET("/verifyCode")
    fun verifyCode(@Query("inputCode")inputCode: String):Call<VerifyCodeResponse>

    @POST("/editPassword")
    fun editPassword(@Query("ID")ID: String,@Query("password")password: String):Call<EditPasswordResponse>

    @GET("/simpleVerify")
    fun simpleVerify(@Query("to")to: String):Call<SimpleVerifyResponse>
}

