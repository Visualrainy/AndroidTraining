package com.tw.training.catkeeper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tw.training.catkeeper.R

/**
 * Created by pchen on 25/10/2017.
 */
class MyCatsFragment: Fragment() {

    companion object {
        fun newInstance(): MyCatsFragment {
            return MyCatsFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.my_cats, container, false)
    }
}