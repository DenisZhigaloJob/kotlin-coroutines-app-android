package by.dz.kotlincoroutines.util

import android.content.Intent
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object IntentUtil {

    @Suppress("SpellCheckingInspection")
    val gson: Gson = GsonBuilder().create()
}

fun Intent.putExtraJson(name: String, src: Any) {
    putExtra(name, IntentUtil.gson.toJson(src))
}

fun <T> Intent.getJsonExtra(name: String, `class`: Class<T>): T? {
    val stringExtra = getStringExtra(name)
    if (stringExtra != null) {
        return IntentUtil.gson.fromJson<T>(stringExtra, `class`)
    }
    return null
}

fun String?.unbox() = this ?: ""

fun Int?.unbox() = this ?: 0

fun List<String>?.unbox() = this ?: listOf()