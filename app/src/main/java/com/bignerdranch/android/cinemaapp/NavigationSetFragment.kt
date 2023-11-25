package com.bignerdranch.android.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class NavigationSetFragment : Fragment() {

    private val apiKey = "1dc6db90-12c9-4adb-8495-ede762b898a1"
    private lateinit var movieApiService: MovieApiService
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_navigation_set, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val titleTextView = view.findViewById<TextView>(R.id.textView2)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, 1)
        val month = SimpleDateFormat("LLLL", Locale("ru", "RU")).format(calendar.time)
        val year1 = calendar.get(Calendar.YEAR)
        val titleText = getString(R.string.title_set_for, month, year1)
        titleTextView.text = titleText

        // инициализация Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/api/v2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // создание экземпляра MovieApiService
        movieApiService = retrofit.create(MovieApiService::class.java)

        // запрос на получение кинопремьер на следующий месяц
        val nextMonth = getNextMonth()
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val call = movieApiService.getPremieres(year, nextMonth, apiKey)
        call.enqueue(object : Callback<PremieresResponse> {
            override fun onResponse(call: Call<PremieresResponse>, response: Response<PremieresResponse>) {
                if (response.isSuccessful) {
                    val premieres = response.body()?.items

                    if (premieres != null) {
                        recyclerView.adapter = MovieAdapter(premieres)
                    } else {
                        showToast("Premieres list is null")
                    }
                } else {
                    handleApiError(response.code())
                }
            }

            override fun onFailure(call: Call<PremieresResponse>, t: Throwable) {
                handleApiFailure(t)
            }
        })

        return view
    }

    private fun getNextMonth(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, 1)

        val month = SimpleDateFormat("MMMM", Locale.ENGLISH).format(calendar.time)
        return month.toUpperCase(Locale.ENGLISH)
    }

    private fun handleApiResponse(response: Response<PremieresResponse>) {
        // проверка, привязан ли фрагмент к контексту
        if (!isAdded) {
            return
        }

        if (response.isSuccessful) {
            val premieres = response.body()?.items

            if (premieres != null) {
                recyclerView.adapter = MovieAdapter(premieres)
            } else {
                Log.e("PremieresResponse", "Premieres list is null")
            }
        } else {
            Log.e("PremieresResponse", "Unsuccessful response. Code: ${response.code()}")
        }
    }

    private fun handleApiFailure(t: Throwable) {
        // проверка, привязан ли фрагмент к контексту
        if (!isAdded) {
            return
        }

        if (t is IOException) {
            showToast("Отсутствует подключение к интернету")
        } else {
            showToast("Не удалось выполнить запрос: ${t.localizedMessage}")
        }


        Log.e("PremieresResponse", "Failed to execute the request", t)
    }

    private fun handleApiError(errorCode: Int) {
        when (errorCode) {
            401 -> showToast("Пустой или неправильный токен")
            402 -> showToast("Превышен лимит запросов (или дневной, или общий)")
            429 -> showToast("Слишком много запросов. Лимит 5 запросов в секунду")
            else -> showToast("Ошибка запроса. Код: $errorCode")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
