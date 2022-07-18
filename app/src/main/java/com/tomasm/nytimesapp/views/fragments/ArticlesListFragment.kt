package com.tomasm.nytimesapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tomasm.articles.data.models.view.ArticleView
import com.tomasm.nytimesapp.core.base.BaseFragment
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
    ): View? {
        _binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val decoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.articlesRecyclerview.layoutManager = layoutManager
        binding.articlesRecyclerview.adapter = adapter
        binding.articlesRecyclerview.addItemDecoration(decoration)

    }


    private fun onItemSelected(article: ArticleView) {
        val directions = ArticlesListFragmentDirections.actionArticlesListFragmentToArticleFragment(article)
        findNavController().navigate(directions)
        Toast.makeText(requireContext(), article.title, Toast.LENGTH_SHORT).show()
    }
}