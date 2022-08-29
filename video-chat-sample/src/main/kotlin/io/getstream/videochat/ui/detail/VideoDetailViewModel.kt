package io.getstream.videochat.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryChannelRequest
import io.getstream.chat.android.client.channel.ChannelClient
import io.getstream.chat.android.client.channel.subscribeFor
import io.getstream.chat.android.client.events.NewMessageEvent
import io.getstream.chat.android.client.models.Message
import io.getstream.chat.android.client.utils.observable.Disposable
import io.getstream.videochat.YoutubeVideo

class VideoDetailViewModel(
    video: YoutubeVideo,
    chatClient: ChatClient = ChatClient.instance(),
) : ViewModel() {

    private var subscription: Disposable? = null

    private var channelClient: ChannelClient = chatClient.channel("livestream", video.id)

    private val _messages: MutableLiveData<List<Message>> = MutableLiveData()

    val messages: LiveData<List<Message>> = _messages

    init {
        val query = QueryChannelRequest()
            .withMessages(30)
            .withWatch()
        channelClient.query(query).enqueue {
            if (it.isSuccess) {
                _messages.value = it.data().messages
            }
        }

        channelClient.subscribeFor<NewMessageEvent> { event ->
            _messages.value = (_messages.value ?: emptyList()) + listOf(event.message)
        }
    }

    fun sendMessage(text: String) {
        channelClient.sendMessage(Message(text = text)).enqueue()
    }

    override fun onCleared() {
        subscription?.dispose()
    }
}

class VideoDetailViewModelFactory(
    private val youtubeVideo: YoutubeVideo,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoDetailViewModel(video = youtubeVideo) as T
    }
}