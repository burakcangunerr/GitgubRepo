package com.example.gitgubrepo.model

import com.example.gitgubrepo.model.Owner
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReposModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("node_id")
    var node_id: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("owner")
    var owner: Owner = Owner(),
    @SerializedName("stargazers_count")
    var stargazersCount: Int = 0,
    @SerializedName("open_issues")
    var openIssues: Int = 0,
    var fav: Boolean = false

): Serializable