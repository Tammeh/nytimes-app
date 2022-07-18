package com.tomasm.nytimesapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tomasm.articles.data.models.view.ArticlesView
import com.tomasm.core.extensions.failure
import com.tomasm.core.extensions.observe
import com.tomasm.core.utils.Constants
import com.tomasm.core.utils.FilterItem
import com.tomasm.core.utils.FiltersProvider
import com.tomasm.nytimesapp.R
import com.tomasm.nytimesapp.core.base.BaseFragment
import com.tomasm.nytimesapp.core.navigation.MainActivity
import com.tomasm.nytimesapp.databinding.FragmentFiltersBinding
import com.tomasm.nytimesapp.views.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersFragment : BaseFragment() {


    private var typeSelected: FilterItem? = null
    private var periodSelected: FilterItem? = null

    private var _binding: FragmentFiltersBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ArticlesViewModel>()

    private val adapterPeriod by lazy {
        ArrayAdapter(requireContext(), R.layout.filter_list_item, FiltersProvider.periodItemList)
    }

    private val adapterType by lazy {
        ArrayAdapter(requireContext(), R.layout.filter_list_item, FiltersProvider.mostItemList)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(articles, ::navToArticleList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFiltersView()
        setToolbar()
    }

    private fun setToolbar() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setFiltersView() {
        setFilterType()
        setFilterPeriod()
        binding.searchButton.setOnClickListener {
            checkButton()
        }

    }


    private fun setFilterPeriod() {
        binding.autoCompleteTxtPeriod.setAdapter(adapterPeriod)
        binding.autoCompleteTxtPeriod.setOnItemClickListener { adapterView, _, i, _ ->
            periodSelected = adapterView.getItemAtPosition(i) as FilterItem
        }
    }

    private fun setFilterType() {
        binding.autoCompleteTxtMost.setAdapter(adapterType)
        binding.autoCompleteTxtMost.setOnItemClickListener { adapterView, _, i, _ ->
            typeSelected = adapterView.getItemAtPosition(i) as FilterItem
            if (typeSelected?.serviceName.equals(Constants.MOST_SHARED, true)) {
                binding.constraintChecks.visibility = View.VISIBLE
            } else {
                binding.constraintChecks.visibility = View.GONE
            }
        }
    }

    private fun checkButton() {
        if (filtersAreSelected() || filtersAreSelected() && validateCheckBox()) {
            getArticles(
                typeSelected!!.serviceName,
                periodSelected!!.serviceName,
                getShareTypes()
            )
        } else {
            Toast.makeText(requireContext(), getString(R.string.filter_error), Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun getArticles(type: String, period: String, shareType: String = "") {
        viewModel.getArticles(type, period, shareType)
    }

    private fun navToArticleList(articlesView: ArticlesView?) {
        val directions =
            FiltersFragmentDirections.actionFiltersFragmentToArticlesListFragment(articlesView!!)
        findNavController().navigate(directions)
    }

    private fun validateCheckBox() = (typeSelected!!.serviceName.equals(
        Constants.MOST_SHARED,
        true) && (binding.facebookCb.isChecked || binding.twitterCb.isChecked))

    private fun filtersAreSelected() = (typeSelected != null && periodSelected != null)

    private fun getShareTypes(): String {
        val list = mutableListOf<String>()
        if (binding.facebookCb.isChecked) list.add(binding.facebookCb.text.toString())
        if (binding.twitterCb.isChecked) list.add(binding.twitterCb.text.toString())
        return if (list.isNotEmpty() && validateCheckBox()) "/" + list.joinToString(Constants.TYPESHARE_SEPARATOR)
            .lowercase() else ""
    }


    override fun onResume() {
        super.onResume()
        clearFilters()
    }


    private fun clearFilters() {
        binding.autoCompleteTxtPeriod.text.clear()
        binding.textInputLayoutPeriod.editText?.text?.clear()
        binding.autoCompleteTxtMost.text.clear()
        binding.textInputLayoutMost.editText?.text?.clear()
        binding.twitterCb.isChecked = false
        binding.facebookCb.isChecked = false
        typeSelected = null
        periodSelected = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}