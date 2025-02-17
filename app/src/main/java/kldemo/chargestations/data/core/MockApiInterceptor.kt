package kldemo.chargestations.data.core

import android.content.Context
import kldemo.chargestations.R
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockApiInterceptor(private val appContext: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return when (chain.request().url.toUri().path) {
            CHARGE_STATIONS_PATH -> {
                val responseString: String =
                    appContext.resources.openRawResource(R.raw.charge_stations).bufferedReader()
                        .use { it.readText() }
                Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(
                        ResponseBody.create(
                            "application/json".toMediaTypeOrNull(),
                            responseString.toByteArray()
                        )
                    )
                    .addHeader("content-type", "application/json")
                    .build()
            }

            else -> chain.proceed(chain.request())
        }
    }

    companion object {

        private const val CHARGE_STATIONS_PATH = "/api/charge-stations"
    }
}