package com.akash.newzz_compose.ui.articleListPage

import androidx.compose.Composable
import androidx.compose.State
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.clickable
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import com.akash.newzz_compose.data.response.NewsArticle
import com.akash.newzz_compose.style.*
import com.akash.newzz_compose.ui.common.HeightSpacer
import com.akash.newzz_compose.ui.common.RemoteImage
import com.akash.newzz_compose.ui.common.WidthSpacer
import com.akash.newzz_compose.utils.CustomTabUtil

/**
 * Created by Akash on 06/06/20
 */

@Composable
fun ArticleRow(article: NewsArticle, isDark: State<Boolean>, onClick: () -> Unit) {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
        Row(
            /* onClick with more than one modifier was not working hence wrapping it with Column
            * Tried modifier = Modifier.clickable(
                onClick = {
                    onClick()
                }
            ).plus(Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp))
            */
            modifier = Modifier.clickable(
                onClick = {
                    onClick()
                }
            ),
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
                        text = article.source.name,
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
    /**
     * App crashing while scrolling the AdapterList hence verticalScroller with Columns used.
     * @see <a href="https://issuetracker.google.com/issues/154653504">Issue tracker</a>
     */
    /*AdapterList(data = articles) { article ->
        ArticleRow(article = article)
        Divider(
            color = Color(0xFFDCDCDC)
        )
    }*/
    val context = ContextAmbient.current
    VerticalScroller {
        Column(modifier = Modifier.fillMaxWidth()) {
            articles.forEach { article ->
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
        }
    }
}