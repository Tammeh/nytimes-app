package com.tomasm.nytimesapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.tomasm.nytimesapp.core.base.BaseFragment
import com.tomasm.nytimesapp.core.platform.CustomWebViewClient
import com.tomasm.nytimesapp.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : BaseFragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setWebView()
    }

    private fun setToolbar() {
        //TODO: Set titulo de articulo en toolbar
    }

    private fun setWebView() {
        binding.articleWebview.clearCache(true)
        binding.articleWebview.webViewClient = CustomWebViewClient(requireContext())
        binding.articleWebview.loadUrl(args.article.url)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}