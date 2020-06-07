package com.akash.newzz_compose

import androidx.annotation.StringDef

/**
 * Created by Akash on 06/06/20
 */

@Retention(AnnotationRetention.SOURCE)
@StringDef(value = [Category.GENERAL, Category.BUSINESS, Category.TECH])
internal annotation class Category {
    companion object {
        const val GENERAL = "general"
        const val BUSINESS = "business"
        const val TECH = "technology"
    }
}