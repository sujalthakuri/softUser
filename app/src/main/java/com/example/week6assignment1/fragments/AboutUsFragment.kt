package com.example.week6assignment1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.week6assignment1.R


class AboutUsFragment : Fragment() {
    private lateinit var wvAboutUs: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_us, container, false)
        wvAboutUs = view.findViewById(R.id.wvAboutUs)
        wvAboutUs.loadUrl("https://softwarica.edu.np/")
        wvAboutUs.settings.javaScriptEnabled = true
        wvAboutUs.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }
        return view
    }


}