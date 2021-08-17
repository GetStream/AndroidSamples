package io.getstream.livestream.compose.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ViewStream
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.livestream.compose.R
import io.getstream.livestream.compose.models.LiveStreamChannelItem

/**
 * This is a view component for custom Channel cards to be shown on Home screen.
 *
 * @param liveStreamChannelItem - The single item [LiveStreamChannelItem] that represents a channel
 * @param modifier - Modifier for styling the card.
 * @param cardShape - Shape object for the card customization.
 * @param isDarkTheme - this is a Boolean to decide if the card needs to show dark variant or a light, by default its false
 * @param onCardClick - click handler to allow the call site to provide functionality when the Card itself is clicked.
 */
@Composable
fun LiveStreamChannelCard(
    liveStreamChannelItem: LiveStreamChannelItem,
    modifier: Modifier = Modifier,
    cardShape: Shape = RoundedCornerShape(8.dp),
    isDarkTheme: Boolean = false,
    onCardClick: (LiveStreamChannelItem) -> Unit
) {
    val context: Context = LocalContext.current
    val cardBackground = if (isDarkTheme)
        colorResource(id = R.color.cardview_dark_background)
    else
        colorResource(id = R.color.cardview_light_background)

    Card(
        shape = cardShape,
        backgroundColor = cardBackground,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onCardClick(liveStreamChannelItem) }
            ),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .height(240.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(105.dp),
                painter = painterResource(liveStreamChannelItem.channelArt),
                contentDescription = stringResource(id = R.string.accessibilityArtworkImage),
                contentScale = ContentScale.Crop,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Rounded.ViewStream,
                    contentDescription = stringResource(id = R.string.accessibilityIcon)
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 4.dp),
                    color = ChatTheme.colors.textHighEmphasis,
                    maxLines = 2,
                    text = liveStreamChannelItem.channelName
                )
            }
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = context.resources.getString(liveStreamChannelItem.channelDescription),
                maxLines = 3,
                color = ChatTheme.colors.textLowEmphasis,
                overflow = TextOverflow.Ellipsis
            )

            Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Image(
                    painter = painterResource(R.drawable.ic_participant),
                    contentDescription = stringResource(id = R.string.accessibilityArtworkImage),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(ChatTheme.colors.textHighEmphasis)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = "2",
                    fontSize = 12.sp,
                    color = ChatTheme.colors.textHighEmphasis
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(R.drawable.ic_viewers),
                    contentDescription = stringResource(id = R.string.accessibilityViewersImage),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(ChatTheme.colors.textHighEmphasis)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = "200",
                    fontSize = 12.sp,
                    color = ChatTheme.colors.textHighEmphasis,
                )
            }
        }
    }
}
