package id.ac.ubaya.informatika.advweek4.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.advweek4.R
import java.lang.Exception

//Used to extend the loadImage method of ImageView
//Passed image URL, and progress bar view object
fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        }) //In case it's failed to load, it uses custom drawable to replace the image
}
