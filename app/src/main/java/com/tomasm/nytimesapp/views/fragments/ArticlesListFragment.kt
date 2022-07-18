package com.tomasm.nytimesapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tomasm.articles.data.models.view.ArticleView
import com.tomasm.nytimesapp.R
import com.tomasm.nytimesapp.core.base.BaseFragment
import com.tomasm.nytimesapp.core.navigation.MainActivity
import com.tomasm.nytimesapp.databinding.FragmentArticlesListBinding
import com.tomasm.nytimesapp.views.adapters.ArticlesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesListFragment : BaseFragment() {

    private var _binding: FragmentArticlesListBinding? = null
    private val binding get() = _binding!!
    private val args: ArticlesListFragmentArgs by navArgs()
    private val adapter by lazy {
        ArticlesAdapter(args.articles.results!!) { article ->
            onItemSelected(
                article
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        if (args.articles.results.isNullOrEmpty()) {
            showEmptyState()
        } else {
            configRecyclerView()
        }
    }

    private fun setToolbar() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showEmptyState() {
        binding.emptyStateText.visibility = View.VISIBLE
        binding.articlesRecyclerview.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configRecyclerView() {
        binding.articlesRecyclerview.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val decoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.articlesRecyclerview.layoutManager = layoutManager
        binding.articlesRecyclerview.adapter = adapter
        binding.articlesRecyclerview.addItemDecoration(decoration)

    }


    private fun onItemSelected(article: ArticleView) {
        val directions =
            ArticlesListFragmentDirections.actionArticlesListFragmentToArticleFragment(article)
        findNavController().navigate(directions)
    }
}