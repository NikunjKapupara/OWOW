package com.owow.androidapp.ws_models

import com.google.gson.annotations.SerializedName

data class MatchesFeedsModel(
        @SerializedName("current_page")
        var currentPage: Int, // 1
        var data: List<Data>,
        @SerializedName("first_page_url")
        var firstPageUrl: String, // https://api.wearematchplay.com/v2/matches?page=1
        var from: Int, // 1
        @SerializedName("last_page")
        var lastPage: Int, // 2
        @SerializedName("last_page_url")
        var lastPageUrl: String, // https://api.wearematchplay.com/v2/matches?page=2
        @SerializedName("next_page_url")
        var nextPageUrl: String, // https://api.wearematchplay.com/v2/matches?page=2
        var path: String, // https://api.wearematchplay.com/v2/matches
        @SerializedName("per_page")
        var perPage: Int, // 15
        @SerializedName("prev_page_url")
        var prevPageUrl: Any, // null
        var to: Int, // 15
        var total: Int // 16
) {
    data class Data(
            var id: Int, // 4
            @SerializedName("course_id")
            var courseId: Int, // 19134
            @SerializedName("show_course")
            var showCourse: Boolean, // false
            var type: Int, // 1
            @SerializedName("winning_score")
            var winningScore: Int, // 1
            @SerializedName("holes_not_played")
            var holesNotPlayed: Int, // 0
            @SerializedName("winning_team")
            var winningTeam: Int, // 2
            var description: Any, // null
            @SerializedName("played_at")
            var playedAt: String, // 2018-03-13 00:00:00
            @SerializedName("created_at")
            var createdAt: String, // 2018-03-13 08:51:26
            @SerializedName("updated_at")
            var updatedAt: String, // 2018-03-13 08:51:26
            @SerializedName("deleted_at")
            var deletedAt: Any, // null
            @SerializedName("verified_at")
            var verifiedAt: String, // 2018-05-16 06:13:41
            var liked: Boolean, // false
            @SerializedName("like_count")
            var likeCount: Int, // 6
            @SerializedName("comment_count")
            var commentCount: Int, // 2
            var course: Course,
            var users: List<User>,
            var images: List<Image>
    ) {
        data class Course(
                var id: Int, // 19134
                @SerializedName("club_id")
                var clubId: Any, // null
                var name: String, // Golfcampus De Tongelreep Par 3
                var country: String, // Netherlands
                var state: Any, // null
                var city: String, // Eindhoven
                var lat: String, // 51.40793665
                var lng: String, // 5.47222540
                @SerializedName("created_at")
                var createdAt: String, // 2018-05-15 16:54:08
                @SerializedName("updated_at")
                var updatedAt: String, // 2018-05-15 16:54:08
                var tees: List<Tee>
        ) {
            data class Tee(
                    var id: Int, // 52823
                    @SerializedName("course_id")
                    var courseId: Int, // 19134
                    var name: String, // Men
                    @SerializedName("course_rating")
                    var courseRating: Double, // 69.4
                    @SerializedName("slope_rating")
                    var slopeRating: Any, // null
                    var par: Int, // 27
                    var holes: List<Hole>
            ) {
                data class Hole(
                        var id: Int, // 829476
                        @SerializedName("tee_id")
                        var teeId: Int, // 52823
                        var par: Int, // 3
                        var tee: Int, // 60
                        @SerializedName("stroke_index")
                        var strokeIndex: Int // 17
                )
            }
        }

        data class User(
                var id: Int, // 132
                @SerializedName("first_name")
                var firstName: String, // Thomas
                @SerializedName("last_name")
                var lastName: String, // van der Veen
                var username: String, // thomas
                var email: String, // thomas@owow.io
                @SerializedName("federation_number")
                var federationNumber: Any, // null
                var handicap: Double, // 54
                @SerializedName("course_id")
                var courseId: Int, // 19419
                @SerializedName("pro_at")
                var proAt: Any, // null
                @SerializedName("verified_at")
                var verifiedAt: Any, // null
                @SerializedName("created_at")
                var createdAt: String, // 2018-03-13 07:58:16
                @SerializedName("updated_at")
                var updatedAt: String, // 2018-05-22 07:07:56
                @SerializedName("is_following")
                var isFollowing: Boolean, // false
                @SerializedName("is_guest")
                var isGuest: Boolean, // false
                @SerializedName("is_blocked")
                var isBlocked: Boolean, // false
                var pivot: Pivot,
                var image: Image
        ) {
            data class Pivot(
                    @SerializedName("match_id")
                    var matchId: Int, // 4
                    @SerializedName("user_id")
                    var userId: Int, // 132
                    var status: Int, // 3
                    var team: Int, // 2
                    var handicap: Int, // 0
                    @SerializedName("stroke_count")
                    var strokeCount: Int, // 0
                    @SerializedName("is_creator")
                    var isCreator: Int, // 0
                    var scores: List<Any>,
                    @SerializedName("tee_id")
                    var teeId: Any // null
            )

            data class Image(
                    @SerializedName("140")
                    var x140: String, // https://d40lni4zfjpyo.cloudfront.net/3/medialibraryRxL3MT-140
                    @SerializedName("210")
                    var x210: String, // https://d40lni4zfjpyo.cloudfront.net/3/medialibraryRxL3MT-210
                    @SerializedName("560")
                    var x560: String, // https://d40lni4zfjpyo.cloudfront.net/3/medialibraryRxL3MT-560
                    var default: String // https://d40lni4zfjpyo.cloudfront.net/3/medialibraryRxL3MT-optimized
            )
        }

        data class Image(
                @SerializedName("140")
                var x140: String, // https://d40lni4zfjpyo.cloudfront.net/7/medialibraryy2B5DN-140
                @SerializedName("210")
                var x210: String, // https://d40lni4zfjpyo.cloudfront.net/7/medialibraryy2B5DN-210
                @SerializedName("560")
                var x560: String, // https://d40lni4zfjpyo.cloudfront.net/7/medialibraryy2B5DN-560
                var default: String // https://d40lni4zfjpyo.cloudfront.net/7/medialibraryy2B5DN-optimized
        )
    }
}