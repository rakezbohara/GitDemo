package com.rakezb.gitdemo.data

import com.google.gson.annotations.SerializedName

data class GithubUser(
    @SerializedName("login") var login: String?,
    @SerializedName("id") var id: Int?,
    @SerializedName("node_id") var nodeId: String?,
    @SerializedName("avatar_url") var avatarUrl: String?,
    @SerializedName("gravatar_id") var gravatarId: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("html_url") var htmlUrl: String?,
    @SerializedName("followers_url") var followersUrl: String?,
    @SerializedName("following_url") var followingUrl: String?,
    @SerializedName("gists_url") var gistsUrl: String?,
    @SerializedName("starred_url") var starredUrl: String?,
    @SerializedName("subscriptions_url") var subscriptionsUrl: String?,
    @SerializedName("organizations_url") var organizationsUrl: String?
)