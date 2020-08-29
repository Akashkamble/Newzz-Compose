package com.akash.newzz_compose.ui.newzzappui

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.akash.newzz.data.response.NewsArticle
import com.akash.newzz_compose.ui.commoncomposable.HeightSpacer
import com.akash.newzz_compose.ui.commoncomposable.RemoteImage
import com.akash.newzz_compose.ui.commoncomposable.WidthSpacer
import com.akash.newzz_compose.ui.style.NewzzTheme
import com.akash.newzz_compose.ui.style.articleTitleStyle
import com.akash.newzz_compose.ui.style.dateTextStyle
import com.akash.newzz_compose.ui.style.sourceTextStyle
import com.akash.newzz_compose.util.CustomTabUtil

/**
 * Created by Akash on 29/08/20
 */

@Composable
fun ArticleRow(article: NewsArticle, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = { onClick() })) {
        Row(
                modifier = Modifier.padding(all = 10.dp),
                verticalGravity = Alignment.CenterVertically
        ) {
            RemoteImage(
                    url = article.urlToImage,
                    modifier = Modifier.preferredSize(100.dp)
            )
            WidthSpacer(value = 10.dp)
            Column {
                if (!article.source.name.isNullOrEmpty()) {
                    Text(
                            text = article.source.name!!,
                            style = sourceTextStyle.copy(color = NewzzTheme.colors.sourceColor)
                    )
                    HeightSpacer(value = 4.dp)
                }
                Text(
                        text = article.title,
                        style = articleTitleStyle.copy(color = NewzzTheme.colors.titleColor),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = 4.dp)
                Text(
                        text = article.publishedAt.substring(0, 10),
                        style = dateTextStyle.copy(color = NewzzTheme.colors.sourceColor)
                )
            }
        }
        HeightSpacer(value = 10.dp)
        Divider(
                color = NewzzTheme.colors.dividerColor
        )
    }
}

@Composable
fun ArticleList(articles: List<NewsArticle>) {
    val context = ContextAmbient.current
    val isDark = NewzzTheme.colors.isDark
    LazyColumnFor(
            items = articles,
            itemContent = { article: NewsArticle ->
                ArticleRow(
                        article = article,
                        onClick = {
                            CustomTabUtil.launch(context, article.url.toString(), isDark)
                        }
                )
            }
    )
}