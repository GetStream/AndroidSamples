/*
 * Copyright 2022 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.getstream.chat.android.customattachments.factory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import io.getstream.chat.android.client.models.Attachment
import io.getstream.chat.android.client.models.Message
import io.getstream.chat.android.customattachments.databinding.ViewQuotedDateAttachmentBinding
import io.getstream.chat.android.ui.message.list.adapter.viewholder.attachment.QuotedAttachmentFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class QuotedDateAttachmentFactory : QuotedAttachmentFactory {
    override fun canHandle(message: Message): Boolean {
        return message.attachments.any { it.type == "date" }
    }

    override fun generateQuotedAttachmentView(message: Message, parent: ViewGroup): View {
        return QuotedDateAttachmentView(parent.context).apply {
            showDate(message.attachments.first())
        }
    }

    class QuotedDateAttachmentView(context: Context) : FrameLayout(context) {

        private val binding = ViewQuotedDateAttachmentBinding.inflate(LayoutInflater.from(context), this)

        fun showDate(attachment: Attachment) {
            binding.dateTextView.text = parseDate(attachment)
        }

        private fun parseDate(attachment: Attachment): String {
            val date = attachment.extraData["payload"].toString()
            return StringBuilder().apply {
                val dateTime = SimpleDateFormat("MMMMM dd, yyyy", Locale.getDefault()).parse(date) ?: return@apply
                val year = Calendar.getInstance().apply {
                    timeInMillis = dateTime.time
                }.get(Calendar.YEAR)
                if (Calendar.getInstance().get(Calendar.YEAR) != year) {
                    append(year).append("\n")
                }
                append(date.replace(", $year", ""))
            }.toString()
        }
    }
}
