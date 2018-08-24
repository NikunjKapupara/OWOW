package com.echannels.moismartservices.adapters.my_req_list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.echannels.moismartservices.utils.AnimUtils
import com.owow.androidapp.R
import com.owow.androidapp.utils.DateAndTimeUtils
import com.owow.androidapp.ws_models.MatchesFeedsModel


/**
 * Created by N!K$ on 7/30/18.
 */
class MatchListAdapter(private val cotext:Context,
                       private val matchesListData: List<MatchesFeedsModel.Data>):
        RecyclerView.Adapter<MatchListViewHolder>(){

    var previousPosition = 0

    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        if (matchesListData[position].playedAt != null)
            holder.matchPlayedOnTV.text = DateAndTimeUtils.getDateinDD_MM_YYYY(matchesListData[position].playedAt)

        if (matchesListData[position].description != null)
            holder.matchDescTV.text = matchesListData[position].description.toString()

        if (matchesListData[position].images != null && !matchesListData[position].images[0].x140.isEmpty())
            Glide.with(cotext).load(matchesListData[position].images[0].x140).into(holder.matchImgIV)

        /// show location
        if (!getFulllAddress(matchesListData[position]).isEmpty()){
            holder.subTextTV.text = cotext.getString(R.string.location) + getFulllAddress(matchesListData[position])
        }

        if (matchesListData[position].commentCount != null && matchesListData[position].likeCount != null) {
            val strLikesAndCommentText = matchesListData[position].commentCount.toString() + " people has commented & "+
                    matchesListData[position].likeCount.toString() +" people liked this match."
            holder.commentsAndLikesTV.text = strLikesAndCommentText
        }

        /**
         * List Item Smooth Animation
         */
        //// it means scrolling down
        if (position > previousPosition){
            AnimUtils.animateItemToUp(holder, true)
        }
        //// it means scrolling up side
        else{
            AnimUtils.animateItemToUp(holder, false)
        }
        previousPosition = position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        return MatchListViewHolder(LayoutInflater.from(cotext).inflate(R.layout.match_feeds_listitem, parent, false))
    }

    override fun getItemCount(): Int {
        return matchesListData.size
    }


    private fun getFulllAddress(eachData:MatchesFeedsModel.Data):String{
        var location = ""
        if (eachData.course != null){

            if (eachData.course.city != null)
                location = eachData.course.city

            if (eachData.course.state != null)
                location += " - " + eachData.course.state

            if (eachData.course.country != null)
                location += " - "+eachData.course.country
        }
        return location
    }

}