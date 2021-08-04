package io.getstream.livestream.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.getstream.chat.android.compose.handlers.SystemBackPressedHandler
import io.getstream.chat.android.compose.state.messages.Thread
import io.getstream.chat.android.compose.ui.messages.composer.MessageComposer
import io.getstream.chat.android.compose.ui.messages.composer.components.MessageInput
import io.getstream.chat.android.compose.ui.messages.header.MessageListHeader
import io.getstream.chat.android.compose.ui.messages.list.MessageList
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.messages.MessageComposerViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessageListViewModel


@Composable
fun LiveStreamScreen(
    composerViewModel: MessageComposerViewModel,
    listViewModel: MessageListViewModel,
    onBackPressed: () -> Unit = {}
) {
    val currentState = listViewModel.currentMessagesState
    val messageMode = listViewModel.messageMode
    val isNetworkAvailable by listViewModel.isOnline.collectAsState()
    val user by listViewModel.user.collectAsState()
    LaunchedEffect(Unit) {
        listViewModel.start()
    }

    val backAction = {
        val isInThread = listViewModel.isInThread
        val isShowingOverlay = listViewModel.isShowingOverlay

        when {
            isShowingOverlay -> listViewModel.selectMessage(null)
            isInThread -> {
                listViewModel.leaveThread()
                composerViewModel.leaveThread()
            }
            else -> onBackPressed()
        }
    }

    SystemBackPressedHandler(isEnabled = true, onBackPressed = backAction)

    ChatTheme(isInDarkMode = true) {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    MessageListHeader(
                        modifier = Modifier
                            .height(56.dp),
                        channel = listViewModel.channel,
                        currentUser = user,
                        isNetworkAvailable = isNetworkAvailable,
                        messageMode = messageMode,
                        onBackPressed = backAction,
                        onHeaderActionClick = {}
                    )
                },
                bottomBar = {
                    MyCustomComposer(composerViewModel)
                }
            ) {
                MessageList(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(Color.Transparent),
                    viewModel = listViewModel,
                    onThreadClick = { message ->
                        composerViewModel.setMessageMode(Thread(message))
                        listViewModel.openMessageThread(message)
                    }
                )
                YTPlayer()
            }
        }
    }
}

@Composable
fun MyCustomComposer(composerViewModel: MessageComposerViewModel) {
    MessageComposer(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        viewModel = composerViewModel,
        integrations = {},
        input = {
            MessageInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(start = 8.dp),
                value = composerViewModel.input,
                attachments = composerViewModel.selectedAttachments,
                activeAction = composerViewModel.activeAction,
                onValueChange = { composerViewModel.setMessageInput(it) },
                onAttachmentRemoved = { composerViewModel.removeSelectedAttachment(it) },
                label = {
                    Row(
                        Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Keyboard,
                            contentDescription = null
                        )

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = "Type something",
                            color = ChatTheme.colors.textLowEmphasis
                        )
                    }
                }
            )
        }
    )
}