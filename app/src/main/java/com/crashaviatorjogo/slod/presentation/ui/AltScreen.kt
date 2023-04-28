package com.crashaviatorjogo.slod.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.presentation.viewmodel.StartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class AltScreen: Fragment() {

    private val viewModel: StartViewModel by activityViewModels()
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private var mFilePathCallback: ValueCallback<Array<Uri>>? = null
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data: Intent? = result.data
        var results: Array<Uri>? = null

        when(result.resultCode) {
            Activity.RESULT_OK -> {

                data?.let {
                    val dataString = it.dataString
                    dataString?.let { string ->
                        results = arrayOf(Uri.parse(string))
                    }
                }
            }
            Activity.RESULT_CANCELED -> {
                // The user canceled the operation.
            }
        }
        mFilePathCallback?.onReceiveValue(results)
        mFilePathCallback = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        handleOnBackPressed()

        val frameLayout = FrameLayout(requireContext())

        webView = WebView(requireContext()).apply {

            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )

            setupWebView()

            progressBar = ProgressBar(
                requireContext(),
                null,
                android.R.attr.progressBarStyleHorizontal
            ).apply {

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    16
                )
                progressDrawable.setColorFilter(android.graphics.Color.BLUE, PorterDuff.Mode.SRC_IN)
                indeterminateTintList = ColorStateList.valueOf(requireContext().getColor(R.color.primary))
                isIndeterminate = true

            }

            webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    progressBar.visibility = View.GONE

                }
            }

            webChromeClient = object : WebChromeClient() {

                override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: FileChooserParams?
                ): Boolean {

                    if (mFilePathCallback != null) {
                        mFilePathCallback?.onReceiveValue(null)
                    }
                    mFilePathCallback = filePathCallback

                    val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
                    contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
                    contentSelectionIntent.type = "*/*"

                    val chooserIntent = Intent(Intent.ACTION_CHOOSER)
                    chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
                    chooserIntent.putExtra(Intent.EXTRA_TITLE, "File Chooser")
                    launcher.launch(chooserIntent)
                    return true
                }
            }
        }

        frameLayout.addView(webView)
        frameLayout.addView(progressBar)
        loadUrl(viewModel.path)

        return frameLayout
    }

    private fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebView.setupWebView() {

        CookieManager
            .getInstance()
            .setAcceptThirdPartyCookies(
                this,
                true
            )

        this.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            builtInZoomControls = true
            displayZoomControls = false
            domStorageEnabled = true
            databaseEnabled = true
            allowContentAccess = true
            allowFileAccess = true
            javaScriptCanOpenWindowsAutomatically = true
            mediaPlaybackRequiresUserGesture = false
        }
    }

    private fun handleOnBackPressed() {

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    }
                }
            }
        )
    }
}