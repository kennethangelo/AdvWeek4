package id.ac.ubaya.informatika.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.advweek4.model.Student

class ListViewModel(application: Application):AndroidViewModel(application) { //Extends ViewModel
    //Live data that "emit" students data (in list form)
    val studentsLD = MutableLiveData<List<Student>>()
    //Live data that "emit" error status (boolean)
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    //Live data that "emit" data loading status (boolean)
    //MutableLiveData -> type of updateable LiveData
    val loadingLD = MutableLiveData<Boolean>()

    //TAG variable useful on volley request cancellation & delete inside refresh method
    val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun refresh() {
        /*
        val student1 = Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        val student2 = Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")
        val student3 = Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        val studentList:ArrayList<Student> = arrayListOf<Student>(student1, student2, student3)
        studentsLD.value = studentList
         */

        studentLoadErrorLD.value = false
        loadingLD.value = true

        //Initialize volley
        queue = Volley.newRequestQueue(getApplication());
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                //TypeToken -> retrieve the obj type of list of student
                val sType = object: TypeToken<List<Student>>() {}.type
                //fromJson -> convert JSON string to list of student
                val result = Gson().fromJson<List<Student>>(it, sType)
                //Update the student LD which is being observed by Student List Fragment
                studentsLD.value = result
                loadingLD.value = false
                Log.d("showvolley", it)
            },
            {
                Log.d("showvolley", it.toString())
                studentLoadErrorLD.value = false
                loadingLD.value = false
            }
        )

        //Start the volley queue request and set the TAG
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    //To clean up code (volley resource) to prevent memory leak problems.
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}