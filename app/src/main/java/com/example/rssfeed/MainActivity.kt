package com.example.rssfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MAINACTIVITY", "started app")
        val res = async(Dispatchers.IO){ downloadXML("https://rss.itunes.apple.com/api/v1/us/apple-music/coming-soon/all/25/non-explicit.rss")}

        var result = ""

        GlobalScope.launch(this.coroutineContext) {

            result = res.await()
            //Log.d("DOWNLOAD FINISHED", result)
            textBox.text = result
            loadingPanel.visibility = View.GONE
        }


        //Log.d("DOWNLOAD", x.toString())
    }

    suspend fun downloadXML(urlPath: String): String{
        val xmlResult = StringBuilder()

        try {
//            val url = URL(urlPath)
//
//            val connection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
//
//            val response = connection.responseCode
//            Log.i("Response code", response.toString())
//
//            connection.inputStream.buffered().reader().use {
//                reader -> xmlResult.append(reader.readText())
//            }
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
            //Log.d("DOWNLOAD", xmlResult.toString())
            delay(3000)
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
