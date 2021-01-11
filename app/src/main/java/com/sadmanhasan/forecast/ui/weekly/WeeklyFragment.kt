package com.sadmanhasan.forecast.ui.weekly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sadmanhasan.forecast.R

class WeeklyFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weekly, container, false)
    }
    /*TODO:
    *  Fetch and Update UI
    * */
}