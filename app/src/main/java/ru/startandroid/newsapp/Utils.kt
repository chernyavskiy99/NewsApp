package ru.startandroid.newsapp

import io.reactivex.Observable
import java.net.HttpURLConnection
import java.net.URL

fun createRequest(url: String) = Observable.create<String> {
    var urlConnection = URL(url).openConnection() as HttpURLConnection
    try {
        urlConnection.connect()

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) {
            it.onError(RuntimeException(urlConnection.responseMessage))
        } else {
            val str = urlConnection.inputStream.bufferedReader().readText()
            it.onNext(str)
        }
    } finally {
        urlConnection.disconnect()
    }
}