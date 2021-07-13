package com.ashupandey.twopageapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
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
        return inflater.inflate(R.layout.fragment_2, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        runBlocking {
            async {
                getData()
            }.await()
        }

    }

    private suspend fun getData() {

        var retrofit : Retrofit? = null

        withContext(Dispatchers.Default){
            retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

       val api = retrofit?.create(ApiInterFace::class.java)
        val mutableList : MutableList<Data> = mutableListOf()
        val mutableListFrag:MutableList<Fragment> = mutableListOf()
        val adap = ViewPagerAdapter(childFragmentManager,mutableListFrag)


            try{
                withContext(Dispatchers.IO){
                mutableList.addAll(api?.getData(2)?.body()?.data!!)
                }
            }catch (e:Exception){
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }


        for(i in mutableList){
            val bundle  = Bundle()

            bundle.putString("KEY1",i.email)
            bundle.putString("KEY2",i.first_name)
            bundle.putString("KEY3",i.last_name)
            bundle.putString("KEY4",i.id.toString())

            val fragment = FragmentHolder()
            fragment.arguments = bundle

            adap.add(fragment)

        }

        viewPager.adapter  = adap



        }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Fragment2().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}