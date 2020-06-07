package com.akash.newzz_compose.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.akash.newzz_compose.R

/**
 * Created by Akash on 07/06/20
 */
object CustomTabUtil {
    private var builder: CustomTabsIntent? = null
    private var builderDark: CustomTabsIntent? = null

    fun launch(context: Context, url: String, isDark: Boolean) {
        if (isDark) {
            if (builderDark == null) {
                builderDark = CustomTabsIntent.Builder()
                    .setToolbarColor(
                        ContextCompat.getColor(context, R.color.darkTheme)
                    )
                    .setShowTitle(true)
                    .setStartAnimations(
                        context,
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                    )
                    .setExitAnimations(
                        context,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    .build()
            }
            builderDark?.launchUrl(context, Uri.parse(url))
        } else {
            if (builder == null) {
                builder = CustomTabsIntent.Builder()
                    .setToolbarColor(
                        ContextCompat.getColor(context, R.color.lightTheme)
                    )
                    .setShowTitle(true)
                    .setStartAnimations(
                        context,
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                    )
                    .setExitAnimations(
                        context,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    .build()
            }
            builder?.launchUrl(context, Uri.parse(url))
        }
    }
}

