package com.ashupandey.twopageapi

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
                R.layout.fragment_1,
                container,
                false
        )
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //we can do our work
        runBlocking {
            async {
                getData()
            }.await()
        }

    }


    private suspend fun getData() {

        var retrofitBuilder : Retrofit? = null
        withContext(Dispatchers.Default){
             retrofitBuilder = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        //we provide its instance so we can use the function that
        // gets the data for us
        val api  = retrofitBuilder?.create(ApiInterFace::class.java)
        //previously the error was that we are not attaching the list to it
        val list1: MutableList<Data> = mutableListOf()

   coroutineScope {
    async {
        getList(1,api,list1)
    }.await()
   }
            recyclerView.adapter = CustonAdapter(list1)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
    private suspend fun getList(i: Int, api: ApiInterFace?, list: MutableList<Data>) {
              try{
                  withContext(Dispatchers.IO){
                      //you can make veriables can to safe call but can not make the toast
                      //so basically you can not do stuff that involve the looper error of kind
                      //trying to call addAll no null which gives which did not works correctly for
                      //a good reason because the list no longer exist
                    list.addAll(api?.getData()?.body()?.data!!)
                      //i have the problem to shif from io->main
                  }
                  Toast.makeText(requireContext(), "sucess full operation", Toast.LENGTH_SHORT).show()

              }catch (e:Exception){
                  Toast.makeText(requireContext(), "not rendered", Toast.LENGTH_SHORT).show()
              }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Fragment1().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}