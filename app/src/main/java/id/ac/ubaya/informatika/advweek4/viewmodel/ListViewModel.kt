package id.ac.ubaya.informatika.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.informatika.advweek4.model.Student

class ListViewModel: ViewModel() { //Extends ViewModel
    //Live data that "emit" students data (in list form)
    val studentsLD = MutableLiveData<List<Student>>()
    //Live data that "emit" error status (boolean)
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    //Live data that "emit" data loading status (boolean)
    //MutableLiveData -> type of updateable LiveData
    val loadingLD = MutableLiveData<Boolean>()

    //Load data from server/local and prepare livedata objects (ready to be observed)
    fun refresh() {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        val student2 = Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")
        val student3 = Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        val studentList:ArrayList<Student> = arrayListOf<Student>(student1, student2, student3)
        studentsLD.value = studentList
        studentLoadErrorLD.value = false
        loadingLD.value = false
    }

}