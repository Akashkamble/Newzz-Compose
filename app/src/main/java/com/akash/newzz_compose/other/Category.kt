package com.akash.newzz_compose.other

import com.akash.newzz_compose.R

/**
 * Created by Akash on 28/08/20
 */

interface Category {
    val category: String
    val icon: Int
}


data class General(
    override val category: String = "general",
    override val icon: Int = R.drawable.ic_general
) : Category

data class Business(
    override val category: String = "business",
    override val icon: Int = R.drawable.ic_business
) : Category

data class Technology(
    override val category: String = "technology",
    override val icon: Int = R.drawable.ic_tech
) : Category


fun getTitleResource(activeCategory: Category): Int = when (activeCategory) {
    is General -> R.string.title_general
    is Business -> R.string.title_business
    is Technology -> R.string.title_technology
    else -> throw IllegalAccessException("Page number is invalid")
}
