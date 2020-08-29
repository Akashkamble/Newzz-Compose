package com.akash.newzz_compose.ui.commoncomposable

import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.akash.newzz_compose.R
import com.akash.newzz_compose.ui.style.NewzzTheme
import com.akash.newzz_compose.ui.style.titleColorDark
import dev.chrisbanes.accompanist.coil.CoilImage

/**
 * Created by Akash on 29/08/20
 */

@Composable
fun HeightSpacer(value: Dp) {
    Spacer(modifier = Modifier.preferredHeight(value))
}

@Composable
fun WidthSpacer(value: Dp) {
    Spacer(modifier = Modifier.preferredWidth(value))
}

@Composable
fun RemoteImage(
        url: String?,
        modifier: Modifier,
        errorImage: VectorAsset = vectorResource(id = R.drawable.ic_newzz_error),
        contentScale: ContentScale = ContentScale.Crop,
        shape: Shape = RoundedCornerShape(5.dp)
) {
    Box(
            modifier = modifier
    ) {
        if (url.isNullOrEmpty()) {
            Image(
                    modifier = Modifier.fillMaxSize(),
                    asset = errorImage,
                    colorFilter = ColorFilter(
                            color = if (NewzzTheme.colors.isDark) titleColorDark else Color.Black,
                            blendMode = BlendMode.SrcAtop
                    )
            )
        } else {
            Surface(
                    color = Color.Transparent,
                    shape = shape
            ) {
                CoilImage(
                        data = url,
                        modifier = modifier,
                        contentScale = contentScale,
                        loading = {
                            Stack(Modifier.fillMaxSize()) {
                                CircularProgressIndicator(
                                        color = NewzzTheme.colors.circularLoaderColor,
                                        modifier = Modifier.gravity(Alignment.Center)
                                )
                            }
                        }
                )
            }
        }
    }
}