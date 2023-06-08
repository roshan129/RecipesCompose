package com.roshanadke.recipescompose.common

import android.text.Html
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import com.roshanadke.recipescompose.data.network.RecipeService


/*
fun getStringFromHtmlText(html: String) {
    val htmlText = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    val spans = htmlText.getSpans(0, htmlText.length, Any::class.java)

    for (span in spans) {
        when (span) {
            is android.text.style.StrikethroughSpan -> {
                addStyle(
                    style = SpanStyle(textDecoration = TextDecoration.LineThrough),
                    start = htmlText.getSpanStart(span),
                    end = htmlText.getSpanEnd(span)
                )
            }
            // Add support for other HTML tags and spans as needed
        }
    }
    append(htmlText)
}*/
