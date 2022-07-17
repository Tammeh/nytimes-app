package com.tomasm.nytimesapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tomasm.nytimesapp.R
import com.tomasm.nytimesapp.core.base.BaseFragment

class ArticlesListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_articles_list, container, false)
    }
}