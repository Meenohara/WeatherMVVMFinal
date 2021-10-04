package com.applauded.meena.weathermvvmfinal

//import Json4Kotlin_Base
//import Main
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.*
import com.applauded.meena.weathermvvm.glide.GlideApp
import com.applauded.meena.weathermvvmfinal.model.Json4Kotlin_Base
import com.applauded.meena.weathermvvmfinal.model.Main
import com.applauded.meena.weathermvvmfinal.viewmodel.DetailsViewModel
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

//import com.bumptech.glide.request.target.CustomTarget

class DetailsActivity : AppCompatActivity() {
    lateinit var summary :TextView
    lateinit var hiTemp :TextView
    lateinit var loTemp :TextView
    lateinit var hectoPa :TextView
    lateinit var location :TextView
    lateinit var wIcon :ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var loadingProgress = findViewById<ProgressBar>(R.id.progressBar)


        summary = findViewById<TextView>(R.id.description)
        hiTemp = findViewById<TextView>(R.id.maxtemp)
        loTemp = findViewById<TextView>(R.id.mintemp)
        hectoPa = findViewById<TextView>(R.id.hpa)
        location = findViewById<TextView>(R.id.location)
        wIcon = findViewById<ImageView>(R.id.weicon)

        var firstUIupdate = true

        Log.d(TAG, "onCreate: DetailsActivity")

        Log.d(TAG, "DetailsActivity "+Thread.currentThread().name)

        //ViewModelStoreOwner and ViewModelFactory
        val detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
//        Log.d(TAG, "onCreate: detailsViewModel.job "+ detailsViewModel.job.isActive)
        Log.d(TAG, "onCreate: weatherDet.value "+detailsViewModel.weatherDet)
        Log.d(TAG, "onCreate: wDet.value "+detailsViewModel.weatherDet.value?.main)
        Log.d(TAG, "onCreate: ${Thread.currentThread().name}")

        detailsViewModel.getRespStatus().observe(this, Observer<Boolean>() {
            if (detailsViewModel.getRespStatus().value!!) {
                Log.d(TAG, "RespStatus: "+detailsViewModel.getRespStatus().value)
                issueError(detailsViewModel)
            }else{
                Log.d(TAG, "RespStatus: "+detailsViewModel.getRespStatus().value)
            }
        })
        detailsViewModel.getIsUpdating().observe(this, Observer<Boolean>()
        {
            if (detailsViewModel.getIsUpdating().value!!) {
                loadingProgress.isVisible = true
                Log.d(TAG, "onCreate: if detailsViewModel.mIsUpdating "+detailsViewModel.mIsUpdating.value)
            }
            else
            {
                loadingProgress.isVisible = false
                Log.d(TAG, "onCreate: else detailsViewModel.mIsUpdating "+detailsViewModel.mIsUpdating.value)
            }

        })

            //updateUI(detailsViewModel)

        detailsViewModel.weatherDet.observe(this, Observer<Json4Kotlin_Base>()
        {
            Log.d(TAG, "onCreate: Observing ..."+detailsViewModel.weatherDet.value)
            updateUI(detailsViewModel)
            fun onChanged(wDetails: Main) {
                loadingProgress.isVisible = true
                Log.d(TAG, "onCreate: Does code reache here?")

                updateUI(detailsViewModel)

                loadingProgress.isVisible = false
                //WeatherDetails here is the instance of LiveData
                //DetailsActivity is the Lifecycle Owner object

                Log.d(TAG, "onChanged: HERE")
            }
        }
        );
        Log.d(TAG, "onCreate: this.lifecycle is: " + this.lifecycle.currentState.name) //TODO dig more



    }

    companion object {
        private const val TAG = "AppFlow"
    }

    fun issueError(detailsViewModel:DetailsViewModel) {
        summary.text = detailsViewModel.result as CharSequence?
        Log.d(TAG, "issueError: "+detailsViewModel.result)
    }

    fun updateUI(detailsViewModel:DetailsViewModel)
    {
        Log.d(TAG, "updateUI: updating")
      //  location.text = detailsViewModel.weatherDet.value?.name
        summary.text = detailsViewModel.weatherDet.value?.weather?.get(0)?.main
        hiTemp.text = detailsViewModel.weatherDet.value?.main?.temp_max.toString() + "C"
        loTemp.text = detailsViewModel.weatherDet.value?.main?.temp_min.toString() + "C"
        hectoPa.text = detailsViewModel.weatherDet.value?.main?.pressure.toString() + "hPa"


        val icon = detailsViewModel.weatherDet.value?.weather?.get(0)?.icon

        GlideApp.with(this)
               .load("https://openweathermap.org/img/wn/${icon}.png")
            .override(200, 200)
            .error(R.drawable.mildsunny)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                    Log.d(TAG, "onLoadFailed: $p0")
                    return false
                }

                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                    Log.d(TAG, "OnResourceReady")
                    //do something when picture already loaded
                    return false
                }
            })
            .into(wIcon)
    }
}