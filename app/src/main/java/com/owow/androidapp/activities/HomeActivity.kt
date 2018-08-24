package com.owow.androidapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.echannels.moismartservices.adapters.my_req_list.MatchListAdapter
import com.echannels.moismartservices.retrofit.RetrofitApiService
import com.echannels.moismartservices.utils.*
import com.owow.androidapp.R
import com.owow.androidapp.utils.NetworkUtils
import com.owow.androidapp.ws_models.MatchesFeedsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_homescreen.*

class HomeActivity : AppCompatActivity() {


    private var disposable: Disposable? = null
    private var listObject: MutableList<MatchesFeedsModel.Data> = mutableListOf()
    private var pageIndex = 1
    private lateinit var mHandler: Handler


    /**
     * Create Instance of the Retrofit API
     */
    private val RetrofitAPI by lazy {
        RetrofitApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initToolbar()
        initRecyclerView()
    }


    /**
     * Handle screen toolbar : Title text, Logout action
     */
    private fun initToolbar() {
        logoutBTN.visibility = View.VISIBLE
        toolbarTitleTV.text = getString(R.string.screen_title_home)
        logoutBTN.setOnClickListener {
            onBackPressed()
        }
    }


    /**
     *  Initially Initialize the Recycler view with empty data
     *  and afte make Web serice call to get Matches Feed data and update the data object
     */
    private fun initRecyclerView() {
        mHandler = Handler()
        val linearLayoutManager = LinearLayoutManager(this)
        myMatchesRV.layoutManager = linearLayoutManager

        myMatchesRV.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                LogUtils.getInstance().logError("@BOTTOM: ", "Reached at the bottom of the List"+page.toString())
                pageIndex = page+1
                getAllMatches()
            }
        })
        myMatchesRV!!.adapter = MatchListAdapter(this, listObject)

        /// Handle on Swipe Down / PULL To REFRESH
        swipe_refresh_layout.setOnRefreshListener {
            pageIndex = 1
            listObject.clear()
            myMatchesRV.adapter.notifyDataSetChanged()
            getAllMatches()
        }
        getAllMatches()
    }


    /***
     * Get all the Matches Feeds from Web API
     */
    private fun getAllMatches(){
        if(!NetworkUtils.isInternetAvailable(this)){
            DialogUtils.showAlertDialogOnly(this, getString(R.string.nointernet))
            return
        }
        LoaderDialog.getInstance().showLoader(this)
        disposable = RetrofitAPI.getAllMatchesFeeds(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            if (true && !result.data.isEmpty())
                                updateList(result.data)
                            else{
                                DialogUtils.showAlertDialogOnly(this, getString(R.string.no_more_data))
                            }
                            LoaderDialog.getInstance().hideLoader()
                        },
                        {
                            error ->
                            LogUtils.getInstance().logError("ERROR: ", error.message)
                            LoaderDialog.getInstance().hideLoader()
                        })
    }


    /**
     * hIf we found data from Web Service::: --> Update List data to adapter
     */
    private fun updateList(listDataFromService:List<MatchesFeedsModel.Data>){
        listObject.addAll(listDataFromService)
        myMatchesRV.adapter.notifyDataSetChanged()
        LoaderDialog.getInstance().hideLoader()
        swipe_refresh_layout.isRefreshing = false
    }


    /**
     * Clear Disposable Web Api call
     */
    override fun onPause() {
        super.onPause()
        try {
            disposable?.dispose()
        }
        catch (e:Exception){
            e.stackTrace
        }
    }

    /**
     * Ask user to Logout on Back Button Pressed
     */
    override fun onBackPressed() {
        DialogUtils.showConfirmationDialog(this, getString(R.string.logout_confirmation),
            View.OnClickListener { p0 ->
                if (p0!!.id == R.id.btnOK){
                    LoginActivity.loginToken = ""
                    finish()
                }
        })
    }
}
