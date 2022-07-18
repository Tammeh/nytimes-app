package com.tomasm.nytimesapp.views.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tomasm.core.utils.Constants
import com.tomasm.nytimesapp.R
import com.tomasm.nytimesapp.core.base.BaseFragment
import com.tomasm.nytimesapp.core.navigation.MainActivity
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
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setWebView()
    }

    private fun setToolbar() {
        (activity as MainActivity).supportActionBar?.title = args.article.title
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        binding.articleWebview.clearCache(true)
        binding.articleWebview.settings.javaScriptEnabled = true
        binding.articleWebview.settings.allowFileAccess = false
        val webViewClient = webViewClientCustomization()
        binding.articleWebview.webViewClient = webViewClient
        binding.articleWebview.loadUrl(args.article.url)
    }

    private fun webViewClientCustomization() = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            handleShowSpinner(true)
        }

        override fun onPageFinished(webView: WebView, url: String) {
            super.onPageFinished(webView, url)
            handleShowSpinner(false)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            if (request?.url?.host == Constants.HOST_URL_WV) {
                return false
            }
            Intent(Intent.ACTION_VIEW, request?.url).apply {
                ContextCompat.startActivity(requireContext(), this, null)
            }
            return true
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            toast(getString(R.string.webview_error))
            findNavController().popBackStack()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}