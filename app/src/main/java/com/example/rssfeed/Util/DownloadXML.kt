package com.example.rssfeed.Util

import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection

object DownloadXML {

    //downloads the xml string from the internet
    suspend fun downloadXML(urlPath: String): String{
        val xmlResult = StringBuilder()

        try {
            val url = URL(urlPath)

            val urlConn: URLConnection = url.openConnection()

            val httpsConn: HttpsURLConnection = urlConn as HttpsURLConnection
            httpsConn.allowUserInteraction = false
            httpsConn.instanceFollowRedirects = true
            httpsConn.requestMethod = "GET"
            httpsConn.connect()

            val resCode = httpsConn.responseCode

            if(resCode == HttpURLConnection.HTTP_OK){
                Log.d("DOWNLOAD", "am reusit sa descarc")
                httpsConn.inputStream.buffered().reader().use {
                        reader->xmlResult.append(reader.readText())
                }

            }
            return xmlResult.toString()
        }catch (e: Exception){

            val errorMessage: String = when(e){
                is MalformedURLException -> "downloadXML: invalid url ${e.message}"
                is IOException -> "downloadXML: IO Exception reading data: ${e.message}"
                is SecurityException -> "downloadXML: security exception. Needs permision? ${e.message}"
                else -> "Unknown error: ${e.message}"
            }

            Log.e("EROARE", errorMessage)
        }

        return ""
    }
}