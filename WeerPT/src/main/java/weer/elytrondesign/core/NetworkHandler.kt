package weer.elytrondesign.core

import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

class NetworkHandler {
    companion object {
        const val FB_INFO_URL = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/info.json?alt=media&token=400bd718-de54-4f81-ab40-735f9a45878f"
        const val FB_INFO_FN = "info.json"
        const val FB_CACHED_INFO_FN = "cachedInfo.json"
        const val FB_INFO_PN_PASSWORD = "pW"
        const val FB_INFO_PN_AD = "aD"
        const val FB_INFO_PN_AD_ID = "id"
        const val FB_INFO_PN_AD_WELCOMED = "w"
        const val FB_INFO_PN_TALES = "t"
        const val FB_INFO_PN_TALES_NAME = "n"
        const val FB_INFO_PN_TALES_THUMBNAIL = "th"
        const val FB_INFO_PN_TALES_URL = "url"

        fun fetch(url: String, callback: Callback) {
            OkHttpClient().newCall(Request.Builder().url(url).build()).enqueue(callback)
        }
    }
}