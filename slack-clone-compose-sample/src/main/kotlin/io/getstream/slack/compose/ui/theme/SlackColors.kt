package io.getstream.slack.compose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import io.getstream.chat.android.compose.ui.theme.StreamColors
import io.getstream.slack.compose.R

@Composable
fun slackLightColors(): StreamColors = StreamColors(
    textHighEmphasis = colorResource(R.color.stream_compose_text_high_emphasis),
    textLowEmphasis = colorResource(R.color.stream_compose_text_low_emphasis),
    disabled = colorResource(R.color.stream_compose_disabled),
    borders = colorResource(R.color.stream_compose_borders),
    inputBackground = colorResource(R.color.stream_compose_input_background),
    appBackground = colorResource(R.color.stream_compose_app_background),
    barsBackground = colorResource(R.color.stream_gray_dark), // colorResource(R.color.stream_compose_bars_background),
    linkBackground = colorResource(R.color.stream_compose_link_background),
    overlay = colorResource(R.color.stream_compose_overlay),
    primaryAccent = colorResource(id = R.color.stream_compose_primary_accent),
    errorAccent = colorResource(R.color.stream_compose_error_accent),
    infoAccent = colorResource(R.color.stream_compose_info_accent),
)

@Composable
fun slackDarkColors(): StreamColors = StreamColors(
    textHighEmphasis = colorResource(R.color.stream_compose_text_high_emphasis_dark),
    textLowEmphasis = colorResource(R.color.stream_compose_text_low_emphasis_dark),
    disabled = colorResource(R.color.stream_compose_disabled_dark),
    borders = colorResource(R.color.stream_compose_borders),
    inputBackground = colorResource(R.color.stream_compose_input_background_dark),
    appBackground = colorResource(R.color.stream_compose_app_background_dark),
    barsBackground = colorResource(R.color.stream_compose_bars_background_dark),
    linkBackground = colorResource(R.color.stream_compose_link_background_dark),
    overlay = colorResource(R.color.stream_compose_overlay_dark),
    primaryAccent = colorResource(id = R.color.stream_compose_primary_accent_dark),
    errorAccent = colorResource(R.color.stream_compose_error_accent_dark),
    infoAccent = colorResource(R.color.stream_compose_info_accent_dark),
)