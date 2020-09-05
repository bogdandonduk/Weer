package weer.elytrondesign.core

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import weer.elytrondesign.present.Home
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.IllegalArgumentException
import java.util.*

abstract class Core {

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

        fun loadFragment(fragment: Fragment, containerId: Int, backStackTag: String? = null, transition: Int = FragmentTransaction.TRANSIT_NONE) {
            if(Home.fm.findFragmentById(containerId) != null)
                when(backStackTag) {
                    null ->
                        Home.fm.beginTransaction().replace(containerId, fragment).setTransition(transition).commit()
                    "null" ->
                        Home.fm.beginTransaction().replace(containerId, fragment).addToBackStack(null).setTransition(transition).commit()
                    else ->
                        Home.fm.beginTransaction().replace(containerId, fragment).addToBackStack(backStackTag).setTransition(transition).commit()
                }
            else
                when(backStackTag) {
                    null ->
                        Home.fm.beginTransaction().add(containerId, fragment).setTransition(transition).commit()
                    "null" ->
                        Home.fm.beginTransaction().add(containerId, fragment).addToBackStack(null).setTransition(transition).commit()
                    else ->
                        Home.fm.beginTransaction().add(containerId, fragment).addToBackStack(backStackTag).setTransition(transition).commit()

                }
        }

        fun fetch(url: String, callback: Callback) {
            OkHttpClient().newCall(Request.Builder().url(url).build()).enqueue(callback)
        }

        fun writeFile(dir: File, name: String, content: ByteArray, append: Boolean, delete: Boolean = false) : File {
            val file = File(dir, name)
                file.createNewFile()

            val fOs = FileOutputStream(file, append)
                fOs.write(content)
                fOs.flush()
                fOs.close()

            if(delete) file.delete()

            return file
        }

        fun readFile(file: File): String {
            val scanner = Scanner(file)
            val builder = StringBuilder()

            while(scanner.hasNextLine()) {
                builder.append(scanner.nextLine())
            }

            return builder.toString()
        }
    }
}