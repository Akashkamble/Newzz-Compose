package com.akash.newzz_compose.ui.common

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Shape
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Spacer
import androidx.ui.layout.Stack
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredWidth
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.akash.newzz_compose.R
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade

/**
 * Created by Akash on 07/06/20
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
                asset = errorImage
            )
        } else {
            Surface(
                color = Color.Transparent,
                shape = shape
            ) {
                CoilImageWithCrossfade(
                    data = url,
                    contentScale = contentScale,
                    loading = {
                        Stack(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(Modifier.gravity(Alignment.Center))
                        }
                    }
                )
            }
        }
    }
}
