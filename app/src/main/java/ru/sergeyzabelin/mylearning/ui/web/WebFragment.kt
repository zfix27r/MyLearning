package ru.sergeyzabelin.mylearning.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryWebViewBinding


class WebFragment : Fragment() {

    private val viewViewModel by viewModels<WebViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryWebViewBinding.inflate(inflater, container, false)

        val webView = dataBinding.dictionaryWebView
        webView.visibility = View.VISIBLE
        webView.loadUrl("https://developer.alexanderklimov.ru/android/views/webview.php")
        return dataBinding.root
    }
}