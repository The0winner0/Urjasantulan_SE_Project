package com.example.urjasantulan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urjasantulan.R
import com.example.urjasantulan.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var newsAdapter: ArrayAdapter<String>
    private val newsArticles = mutableListOf<String>()
    private lateinit var adapter: NewsArticleAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val buttonSolar: Button = root.findViewById(R.id.buttonSolar)
//        val buttonWind: Button = root.findViewById(R.id.buttonWind)
//        val buttonWater: Button = root.findViewById(R.id.buttonWater)
//        val buttonThermal: Button = root.findViewById(R.id.buttonThermal)
//        val newsListView: ListView = root.findViewById(R.id.newsListView)
//
//        newsAdapter = ArrayAdapter(requireContext(), R.layout.news_item, R.id.textViewNewsTitle, newsArticles)
//        newsListView.adapter = newsAdapter
//
//
//        buttonSolar.setOnClickListener { loadNews("solar") }
//        buttonWind.setOnClickListener { loadNews("wind") }
//        buttonWater.setOnClickListener { loadNews("water") }
//        buttonThermal.setOnClickListener { loadNews("thermal") }

        setupRecyclerView()
        setupButtonListeners()


        return root
    }

    private fun loadNews(category: String) {
        // Simulate an API call to fetch news based on the category
        // Replace this with actual API logic
        newsArticles.clear()
        newsArticles.addAll(fetchNewsArticles(category))
        newsAdapter.notifyDataSetChanged()
    }

    private fun fetchNewsArticles(category: String): List<String> {
        // Simulate fetching news articles from an API
        return when (category) {
            "solar" -> listOf("Solar News 1", "Solar News 2", "Solar News 3")
            "wind" -> listOf("Wind News 1", "Wind News 2", "Wind News 3")
            "water" -> listOf("Water News 1", "Water News 2", "Water News 3")
            "thermal" -> listOf("Thermal News 1", "Thermal News 2", "Thermal News 3")
            else -> emptyList()
        }
    }

    private fun setupRecyclerView() {
        adapter = NewsArticleAdapter()
        binding.recyclerViewArticles.adapter = adapter
        binding.recyclerViewArticles.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupButtonListeners() {
        binding.btnSolar.setOnClickListener { fetchArticles("solar") }
        binding.btnWind.setOnClickListener { fetchArticles("wind") }
        binding.btnWater.setOnClickListener { fetchArticles("water") }
        binding.btnThermal.setOnClickListener { fetchArticles("thermal") }
    }

    private fun fetchArticles(tag: String) {
        // Mock API call to fetch articles based on the tag
        val articles = fetchArticlesFromApi(tag)

        // Update RecyclerView
        adapter.submitList(articles)
    }

    private fun fetchArticlesFromApi(tag: String): List<Article> {
        // Simulated API call - In a real scenario, you'd make an HTTP request here.
        return when (tag) {
            "solar" -> listOf(
                Article(
                    id = 1,
                    title = "The Race for Space-Based Solar Power",
                    description = "Once a sci-fi staple, the ability to beam solar power from space now seems closer than ever—but a lot of work remains.",
                    imageUrl = "https://australianpremiumsolar.co.in/wp-content/uploads/2024/03/PM-Surya-Ghar-Yojana_APS.jpg"
                ),
                Article(
                    id = 2,
                    title = "Solar Innovations That Could Change the World",
                    description = "Explore the latest breakthroughs in solar energy technology.",
                    imageUrl = "https://www.niir.org/blog/wp-content/uploads/2022/05/niir.org_-2.jpg"
                )
            )
            "wind" -> listOf(
                Article(
                    id = 3,
                    title = "The Future of Wind Energy",
                    description = "Wind energy is poised to play a crucial role in the global energy transition.",
                    imageUrl = "https://i.ytimg.com/vi/TFuPWjkoX6Y/maxresdefault.jpg"
                ),
                Article(
                    id = 4,
                    title = "Harnessing Wind Power in Urban Areas",
                    description = "How cities are integrating wind turbines into the urban landscape.",
                    imageUrl = "https://www.vectorenewables.com/images/easyblog_articles/218/b2ap3_amp_offshore-wind-farm.jpg"
                )
            )
            "water" -> listOf(
                Article(
                    id = 5,
                    title = "The Power of Water: Hydroelectric Energy",
                    description = "Water has been powering our world for centuries. What's next?",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqi4fdEHtPg4nG4z1IeQsYPW2rNduOy5mePA&s"
                ),
                Article(
                    id = 6,
                    title = "Tidal Energy: The Future of Renewable Energy?",
                    description = "Exploring the potential of tidal energy as a reliable renewable source.",
                    imageUrl = "https://example.com/water_image2.jpg"
                )
            )
            "thermal" -> listOf(
                Article(
                    id = 7,
                    title = "Geothermal Energy: Earth's Hidden Power",
                    description = "Harnessing the heat from within the Earth to power our homes.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKjqRXY1O6rL0HRbm8m-gF0aV-ohC4vBVWsw&s"
                ),
                Article(
                    id = 8,
                    title = "Thermal Energy Storage: Revolutionizing Renewable Energy",
                    description = "Innovative solutions for storing thermal energy and stabilizing the grid.",
                    imageUrl = "https://example.com/thermal_image2.jpg"
                )
            )
            else -> emptyList()  // In case the tag doesn't match any of the predefined categories.
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}