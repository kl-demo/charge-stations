package kldemo.chargestations.data.core

import android.content.Context
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kldemo.chargestations.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideApi(okHttpClient: OkHttpClient): Api = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()
        .create(Api::class.java)

    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(MockApiInterceptor(appContext))
            .build()
}