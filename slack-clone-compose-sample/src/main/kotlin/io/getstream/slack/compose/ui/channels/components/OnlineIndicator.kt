package io.getstream.slack.compose.ui.channels.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.slack.compose.ui.theme.SlackTheme

/**
 * A simple view component representation for User online status green indicator.
 *
 * @param isOnline - boolean toggle to update to either a green or grey dot.
 * @param modifier - Modifier for styling.
 * @param shape - The shape of the online indicator.
 */
@Composable
fun OnlineIndicator(
    isOnline: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(if (isOnline) ChatTheme.colors.infoAccent else Color.Transparent)
            .border(2.dp, if (isOnline) Color.Transparent else ChatTheme.colors.borders)
    )
}

@Preview(showBackground = true)
@Composable
fun OnlineIndicatorPreviewOnline() {
    SlackTheme {
        OnlineIndicator(isOnline = true)
    }
}

@Preview(showBackground = true)
@Composable
fun OnlineIndicatorPreviewOffline() {
    SlackTheme {
        OnlineIndicator(isOnline = false)
    }
}