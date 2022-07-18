package com.tomasm.nytimesapp.core.platform

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity

class CustomWebViewClient(private val context: Context) : WebViewClient(){
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == "www.nytimes.com") {
            return false
        }
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            startActivity(context, this, null)
        }
        return true
    }


}