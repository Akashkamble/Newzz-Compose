package com.akash.newzz_compose.ui.newzzappui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.akash.newzz_compose.other.Category
import com.akash.newzz_compose.ui.style.NewzzTheme

/**
 * Created by Akash on 28/08/20
 */

@Composable
fun BottomBar(
        categoryList: List<Category>,
        activeCategory: Category,
        onMenuClicked: (Category) -> Unit
) {
    BottomAppBar(
            backgroundColor = NewzzTheme.colors.bottomNavBackground,
            elevation = 10.dp
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().preferredHeight(50.dp),
                horizontalArrangement = Arrangement.SpaceAround
        ) {
            categoryList.forEach { category ->
                BottomNavigationItem(
                        icon = { Icon(asset = vectorResource(id = category.icon)) },
                        selected = activeCategory == category,
                        onSelect = {
                            onMenuClicked(category)
                        },
                        selectedContentColor = NewzzTheme.colors.bottomNavActiveIconColor,
                        unselectedContentColor = NewzzTheme.colors.bottomNavInActiveIconColor
                )
            }
        }
    }
}



