package com.tw.training.catkeeper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.adapter.CatsNearbyAdapter
import com.tw.training.catkeeper.domain.CatsNearby
import com.tw.training.catkeeper.presenter.CatsNearbyContract
import com.tw.training.catkeeper.presenter.CatsNearbyPresenter

/**
 * Created by pchen on 25/10/2017.
 */
class CatsNearByFragment : Fragment(), CatsNearbyContract.View, CatsNearbyAdapter.OnProfileClickListener {
    private lateinit var mRecyclerView: RecyclerView
    private val TAG = "CatsNearByFragment"
    private val mPresenter: CatsNearbyPresenter = CatsNearbyPresenter(this)

    private lateinit var mCatsNearbyAdapter: CatsNearbyAdapter

    companion object {
        fun newInstance(): CatsNearByFragment {
            return CatsNearByFragment()
        }

    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.cats_nearby, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_view)

        return view
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mCatsNearbyAdapter = CatsNearbyAdapter(activity, null)
        mCatsNearbyAdapter.mProfileClickListener = this
        mRecyclerView.adapter = mCatsNearbyAdapter
        mPresenter.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "xxxxxxxxxxxxxxxxonCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "xxxxxxxxxxxxxxxxonStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "xxxxxxxxxxxxxxxxonResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "xxxxxxxxxxxxxxxxonPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "xxxxxxxxxxxxxxxxonStop")
    }

    override fun showNearbyCats(catsNearbyList: List<CatsNearby>?) {
        mCatsNearbyAdapter.mCatsNearbyList = catsNearbyList
        mCatsNearbyAdapter.notifyDataSetChanged()
    }

    override fun onGetSuccess(data: List<CatsNearby>) {
        mCatsNearbyAdapter.mCatsNearbyList = data
        mCatsNearbyAdapter.notifyDataSetChanged()
    }

    override fun onGetFailed(data: String) {
    }

    override fun onProfileClick(position: Int) {
        Toast.makeText(activity, "current position: $position", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "xxxxxxxxxxxxxxxxonDestroy")
        mPresenter.stop()
    }
}
