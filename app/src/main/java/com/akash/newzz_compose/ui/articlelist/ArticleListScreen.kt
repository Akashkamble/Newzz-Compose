package com.akash.newzz_compose.ui.articlelist

import androidx.compose.Composable
import androidx.compose.State
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredWidth
import androidx.ui.material.Divider
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import com.akash.newzz.data.response.NewsArticle
import com.akash.newzz_compose.style.articleTitleStyle
import com.akash.newzz_compose.style.dateTextStyle
import com.akash.newzz_compose.style.dividerColor
import com.akash.newzz_compose.style.dividerColorDark
import com.akash.newzz_compose.style.sourceTextColorDark
import com.akash.newzz_compose.style.sourceTextStyle
import com.akash.newzz_compose.style.titleColorDark
import com.akash.newzz_compose.ui.common.HeightSpacer
import com.akash.newzz_compose.ui.common.RemoteImage
import com.akash.newzz_compose.ui.common.WidthSpacer
import com.akash.newzz_compose.utils.CustomTabUtil

/**
 * Created by Akash on 06/06/20
 */

@Composable
fun ArticleRow(article: NewsArticle, isDark: State<Boolean>, onClick: () -> Unit) {
    Box(modifier = Modifier.clickable(onClick = { onClick() })) {
        Row(
            modifier = Modifier.padding(all = 10.dp),
            verticalGravity = Alignment.CenterVertically
        ) {
            RemoteImage(
                url = article.urlToImage,
                modifier = Modifier.preferredHeight(100.dp)
                    .plus(Modifier.preferredWidth(100.dp))
            )
            WidthSpacer(value = 10.dp)
            Column {
                if (!article.source.name.isNullOrEmpty()) {
                    Text(
                        text = article.source.name!!,
                        style = if (isDark.value) sourceTextStyle.copy(color = sourceTextColorDark) else sourceTextStyle
                    )
                    HeightSpacer(value = 4.dp)
                }
                Text(
                    text = article.title,
                    style = if (isDark.value) articleTitleStyle.copy(color = titleColorDark) else articleTitleStyle,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = 4.dp)
                Text(
                    text = article.publishedAt.substring(0, 10),
                    style = if (isDark.value) dateTextStyle.copy(color = sourceTextColorDark) else dateTextStyle
                )
            }
        }
    }
}

@Composable
fun ArticleList(articles: List<NewsArticle>, isDark: State<Boolean>) {
    val context = ContextAmbient.current
    LazyColumnItems(
        items = articles,
        itemContent = { article: NewsArticle ->
            ArticleRow(
                article = article,
                isDark = isDark,
                onClick = {
                    CustomTabUtil.launch(context, article.url.toString(), isDark.value)
                }
            )
            HeightSpacer(value = 10.dp)
            Divider(
                color = if (isDark.value) dividerColorDark else dividerColor
            )
        }
    )
}
