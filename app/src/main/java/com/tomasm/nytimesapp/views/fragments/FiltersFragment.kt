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
import com.tomasm.core.utils.FilterItem
import com.tomasm.nytimesapp.R
import com.tomasm.nytimesapp.core.base.BaseFragment
import com.tomasm.nytimesapp.databinding.FragmentFiltersBinding
import com.tomasm.nytimesapp.views.viewmodel.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersFragment : BaseFragment() {

    private lateinit var typeSelected: FilterItem
    private lateinit var periodSelected: FilterItem

    val mostItemList = mutableListOf(
        FilterItem("Mas vistos", "mostviewed"),
        FilterItem("Mas compartidos por mail", "mostemailed"),
        FilterItem("Mas compartidos en redes", "mostshared")
    )
    val periodItemList = mutableListOf(
        FilterItem("1 Día", "1"),
        FilterItem("7 Días", "7"),
        FilterItem("30 Días", "30")
    )

    private var _binding: FragmentFiltersBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ArticlesViewModel>()


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
    ): View? {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFiltersView()
    }

    private fun setFiltersView() {
        val adapterMost = ArrayAdapter(requireContext(), R.layout.filter_list_item, mostItemList)
        binding.autoCompleteTxtMost.setAdapter(adapterMost)
        binding.autoCompleteTxtMost.setOnItemClickListener { adapterView, view, i, l ->
            typeSelected = adapterView.getItemAtPosition(i) as FilterItem
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.filter_list_item, periodItemList)
        binding.autoCompleteTxtPeriod.setAdapter(adapter)
        binding.autoCompleteTxtPeriod.setOnItemClickListener { adapterView, view, i, l ->
            periodSelected = adapterView.getItemAtPosition(i) as FilterItem
        }

        binding.searchButton.setOnClickListener {
            checkButton()
        }

    }

    private fun checkButton() {
        if (!binding.textInputLayoutPeriod.editText?.text.isNullOrEmpty()
            && !binding.textInputLayoutMost.editText?.text.isNullOrEmpty()
            && binding.facebookCb.isChecked
        ) {
            getArticles(
                typeSelected.serviceName,
                periodSelected.serviceName
            )
        } else {
            Toast.makeText(requireContext(), "No anduvo boton", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getArticles(type: String, period: String, shareType: String = "") {
        viewModel.getArticles(type, period, shareType)
    }

    private fun navToArticleList(articlesView: ArticlesView?) {
        val directions = FiltersFragmentDirections.actionFiltersFragmentToArticlesListFragment(articlesView!!)
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}