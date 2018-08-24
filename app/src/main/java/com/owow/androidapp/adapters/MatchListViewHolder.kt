package com.echannels.moismartservices.adapters.my_req_list

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.match_feeds_listitem.view.*


/**
 * Created by mdev3 on 7/30/18.
 */
class MatchListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val matchDescTV = view.matchDescTV
    val subTextTV = view.subTextTV
    val commentsAndLikesTV = view.commentsAndLikesTV

    val matchImgIV = view.matchImgIV
    val matchPlayedOnTV = view.matchPlayedOnTV

}