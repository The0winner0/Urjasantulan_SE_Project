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
        fetchArticles("solar")
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
                    title = "Coal Power Defined This Minnesota Town. Can Solar Win It Over?",
                    description = "Towering over Becker, a community of a little more than 5,000 people northwest of Minneapolis, is one of the nation's largest coal power plants.",
                    imageUrl = "https://static01.nyt.com/images/2024/08/12/00coal-to-solar-21218-cover/00coal-to-solar-21218-cover-popup.jpg"
                ),
                Article(
                    title = "Solar-powered EV chargers power up college campus thanks to student engineers",
                    description = "Solar-powered EV chargers power up college campus thanks to student engineers ... Imagine charging your EV while on campus entirely by the sun?",
                    imageUrl = "https://electrek.co/wp-content/uploads/sites/3/2024/08/Solar-powered-EV-chargers-college.jpeg?quality=82&strip=all"
                ),
                Article(
                    title = "5 solar innovations that have a bright future",
                    description = "Researchers have been constantly developing solar technology to make it more efficient and reliable.",
                    imageUrl = "https://s.yimg.com/ny/api/res/1.2/26Qn6Z2FTpQAA5eHUCG2vA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD04MDA-/https://media.zenfs.com/en/the_cool_down_737/e2a033ac2c09d1e582cd9eb817ae133a"
                ),
                Article(
                    title = "Homer Electric deal sets stage for a dramatic jump in solar power production in Alaska",
                    description = "Jenn Miller, with Renewable IPP, says the new solar farm will be built near Puppy Dog Lake in Nikiski on the Kenai Peninsula.",
                    imageUrl = "https://www.adn.com/resizer//K9p3-nV1zPq0fiHE8rfHnE4sW5o=/1200x630/filters:format(jpg):quality(70)/cloudfront-us-east-1.images.arcpublishing.com/adn/A27ISEHIGBCAJI7NELVXRHOEV4.jpg"
                ),
                Article(
                    title = "Buffalo Township hearings set on ordinances for solar energy, repeat emergency responses",
                    description = "Supervisor Ron Zampogna said the ordinance is broad-based and encompasses solar energy generation for residences and commercial solar energy ...",
                    imageUrl = "https://assets-varnish.triblive.com/2024/08/7648117_web1_gtr-SolarHomes1-071623.jpg"
                ),
                Article(
                    title = "Time Machine: 50 years ago, Robert Redford visits Vail, touts solar energy",
                    description = "Three Vail Mountain rescue workers and two hikers found Burke, and Sheriff A. J. Johnson said he apparently fell down from the summit, because ...",
                    imageUrl = "https://swiftmedia.s3.amazonaws.com/mountain.swiftcom.com/images/sites/7/2024/08/16094323/Screen-Shot-2024-08-16-at-6.11.51-AM.png"
                ),
                Article(
                    title = "Solar energy systems can get a boost from leaf-like concentrators",
                    description = "The new leaf model design improves photon collection efficiency, reduces losses, and makes the system more adaptable and replaceable.",
                    imageUrl = "https://interestingengineering.com/_next/image?url=https%3A%2F%2Fcms.interestingengineering.com%2Fwp-content%2Fuploads%2F2024%2F08%2FInteresting-Engineering_video-1344-x-1080-px-489.jpg&w=1200&q=75"
                ),
                Article(
                    title = "Taiwan Reboots Its Solar-Power Fishponds",
                    description = "A maze of brackish and freshwater ponds covers Taiwan's coastal plain, supporting aquaculture operations that produce roughly NT $30 billion US ...",
                    imageUrl = "https://assets.rbl.ms/53380335/origin.jpg"
                ),
                Article(
                    title = "$80,000 bill for unfinished rooftop solar power system exposes pitfall for consumers",
                    description = "$80,000 bill for unfinished rooftop solar power system exposes pitfall for consumers. 'Homeowners can find themselves in quite a mess if they ...",
                    imageUrl = "https://www.ocregister.com/wp-content/uploads/2024/08/LDN-L-SOLAR-0807-002.jpg?w=1024"
                ),
                Article(
                    title = "White County Commissioners Approve Large-Scale Solar Farm: Pushes Moratorium and Ordinace Revisions Aside",
                    description = "The White County Commissioners denied public comment over solar ordinance revisions, and a two-year moratorium, after voting in favor a ...",
                    imageUrl = "https://bloximages.newyork1.vip.townnews.com/wlfi.com/content/tncms/assets/v3/editorial/3/30/330d33c6-5e8a-11ef-bcb3-1f9ced8f10d2/66c3e1b6bed28.image.jpg?resize=200%2C113"
                ),
                Article(
                    title = "Solar panels: Great moments from more than 140 years of innovation",
                    description = "From a New York rooftop in the 1800s to their first journey to space. From kidnappings and bribery to a world-changing mistake with an ...",
                    imageUrl = "https://assets.newatlas.com/dims4/default/ed4105f/2147483647/strip/true/crop/1884x1256+2+0/resize/1200x800!/quality/90/?url=http%3A%2F%2Fnewatlas-brightspot.s3.amazonaws.com%2F3f%2Fd9%2F6cb4d0c84557a5f9663c93926024%2Fscreenshot-2024-08-20-at-2.38.54%E2%80%AFPM.png"
                ),
                Article(
                    title = "High  energy: Illuminate USA hires 1,000 employees, makes 1 million solar panels",
                    description = "Illuminate USA CEO Frank Zhu said the company is now Pataskala's largest employer and is on track to be the largest and most productive solar ...",
                    imageUrl = "https://www.newarkadvocate.com/gcdn/authoring/authoring-images/2024/08/16/PNEW/74821925007-2024-pat-illuminate-panels.jpg?width=660&height=441&fit=crop&format=pjpg&auto=webp"
                ),
                Article(
                    title = "Validated simulations optimize solar power generation with row crops",
                    description = "WEST LAFAYETTE, Ind. — A Purdue University research team has demonstrated how to optimize yield in corn fields equipped with solar power ...",
                    imageUrl = "https://www.purdue.edu/newsroom/wp-content/uploads/2024/08/Agrivoltaics-LO.jpg"
                ),
                Article(
                    title = "Ridgefield High School's new solar panels over parking lot to save town $1.5M in energy costs",
                    description = "The Ridgefield High School Solar Canopy project is part of an effort to install solar arrays at town and school buildings.",
                    imageUrl = "https://s.hdnux.com/photos/01/41/30/61/25529409/5/1920x0.jpg"
                ),
                Article(
                    title = "Meta: Helping RWE Develop Solar Farms in the Deep South",
                    description = "Renewable energy business RWE signs long-term power purchase agreements with Meta to generate clean energy from two solar farms in Louisiana ...",
                    imageUrl = "https://assets.bizclikmedia.net/138/fb3cce8c148f65faaebb2278fde37e92:b5eb34fe4aed68a1f33068d778643ad5/bwf20210608121lr.webp"
                ),
                Article(
                    title = "US college students unveil solar-powered EV chargers for campus use",
                    description = "A group of college students in the US has created four innovative charging stations for electric vehicles (EVs) powered by a solar energy ...",
                    imageUrl = "https://interestingengineering.com/_next/image?url=https%3A%2F%2Fcms.interestingengineering.com%2Fwp-content%2Fuploads%2F2024%2F08%2FUntitled-design-2024-08-20T145939.276.jpg&w=1200&q=75"
                ),
                Article(
                    title = "EIA expects 62.8 GW to come online in 2024, led by solar, energy storage",
                    description = "Rows of sustainable energy solar panels set up on farmland. The Energy Information Administration said Aug. 19, 2024, that it expects power ...",
                    imageUrl = "https://www.utilitydive.com/imgproxy/vUpqQPfr7JS0ZEaf9SaK1UHfjNcv2NZLf17t3DcrTWM/g:ce/rs:fit:770:435/bG9jYWw6Ly8vZGl2ZWltYWdlL0dldHR5SW1hZ2VzLTE0NTM4NTkyMjIuanBn.webp"
                ),
                Article(
                    title = "Can a doctor give you a prescription for solar panels?",
                    description = "A new program at the Boston Medical Center says it prescribes solar panels to patients. But we wondered, what does that really mean?",
                    imageUrl = "https://i0.wp.com/newspack-thepublicsradio.s3.amazonaws.com/uploads/2024/08/082024-Possibly-solarprescription-1.jpg?fit=1200%2C900&ssl=1"
                ),
                Article(
                    title = "Q&A: How Alibaba’s DAMO Academy Uses AI to Improve Solar and Wind Energy Forecasting in China",
                    description = "DAMO engineers and scientists are leveraging artificial intelligence and machine learning to improve renewable energy forecasting in China.",
                    imageUrl = "https://alizila.oss-us-west-1.aliyuncs.com/uploads/2024/08/solar-wind-energy-alibaba-damo-academy-ai.png"
                ),
                Article(
                    title = "Federal funds for community solar in NV slowly trickling",
                    description = "Under the Nevada Clean Energy Fund plan, Nevada residents living in households restricted from installing solar panels will qualify for rebates ...",
                    imageUrl = "https://nevadacurrent.com/wp-content/uploads/2023/09/SolarRC_002.JPG-Ronda-1536x989.jpg"
                )
            ).shuffled()         // Shuffle the list
                .take(3)
            "wind" -> listOf(
                Article(
                    title = "Are US wind farms killing thousands of birds?",
                    description = "Estimates of annual bird deaths linked to wind turbines in the continental U.S. ranged between 230000 and 600000 per year in 2013 and 2014.",
                    imageUrl = "https://i0.wp.com/newspack-coloradosun.s3.amazonaws.com/wp-content/uploads/2024/05/Copy-of-Environment-1.png?fit=1200%2C630&quality=80&ssl=1"
                ),
                Article(
                    title = "Federal government grants first floating offshore wind power research lease to Maine",
                    description = "The federal government has issued the nation's first floating offshore wind research lease to the state of Maine, comprising about 23 square ...",
                    imageUrl = "https://s.abcnews.com/images/US/wirestory_819db6ab1dbd2fbcf9a542a0399d1ac0_16x9_1600.jpg"
                ),
                Article(
                    title = "With help from Texas, wind-generated power set springtime records as coal continues to decline",
                    description = "Wind turbines spin Tuesday, March 2, 2021 near Raymondville in the Rio Grande Valley in far south Texas.",
                    imageUrl = "https://s.hdnux.com/photos/01/36/60/56/24841149/5/ratio3x2_1920.jpg"
                ),
                Article(
                    title = "America's biggest failure in history, exposed: The energy that every country but us can develop",
                    description = "America's new energy has failed, and is a historic shame: This source can be produced in every country, but not here, and it's futuristic.",
                    imageUrl = "https://www.ecoticias.com/en/wp-content/uploads/2024/08/offshore-wind-energy-America-essentialbusiness-1.jpg"
                ),
                Article(
                    title = "Wind turbine fire in Mechanicsville could cost farmer millions",
                    description = "MECHANICSVILLE, Iowa ...",
                    imageUrl = "https://gray-kcrg-prod.cdn.arcpublishing.com/resizer/v2/WCI2GBYXERHL5LLYTWS25A42CA.png?auth=7c5b05d9c55257273f63dae3553261cde950fc422a1c621b4e6252ad77c8798d&width=1200&height=600&smart=true"
                ),
                Article(
                    title = "'We lost the battle last week:' Virginia Beach business loses some property to Dominion Energy wind project",
                    description = "A spokesperson for Dominion explained that the property was essential to help connect 174 offshore wind turbines to the grid to power 660,000 ...",
                    imageUrl = "https://ewscripps.brightspotcdn.com/dims4/default/78e3853/2147483647/strip/true/crop/3280x1722+0+54/resize/1200x630!/quality/90/?url=http%3A%2F%2Fewscripps-brightspot.s3.amazonaws.com%2Fd8%2Fe3%2F918508f44231a0b9b4cf4c940dd0%2Fscreenshot-2024-08-16-at-11-26-42-pm.png"
                ),
                Article(
                    title = "U.S. Wind and Solar Are on Track to Overtake Coal This Year",
                    description = "Two renewable resources, wind and solar, together have produced more power than coal through July—a first for the U.S..",
                    imageUrl = "https://static.scientificamerican.com/dam/m/4805eaa8a1fdec69/original/GettyImages-489486136_WEB.jpg?w=600"
                ),
                Article(
                    title = "How to Recycle Wind Turbines",
                    description = "Recycling wind turbines is a complex issue. The same materials that increase their durability, such as steel, copper, glass, carbon fiber, and ...",
                    imageUrl = "https://www.thomasnet.com/insights/_next/image/?url=https%3A%2F%2Fthomasmkt.wpengine.com%2Fwp-content%2Fuploads%2F2024%2F08%2Fshutterstock_2339674745.jpg&w=3840&q=75"
                ),
                Article(
                    title = "Letter: Offshore wind offers a glimmer of hope",
                    description = "This initiative represents a significant step toward sustainable energy and reducing our carbon footprint. Offshore wind turbines, proven ...",
                    imageUrl = ""
                ),
                Article(
                    title = "BOEM grants US’s first floating offshore wind research lease to Maine",
                    description = "The lease is expected to generate up to 144MW of renewable energy from 12 floating offshore wind turbines. Claire Jenns August 20, 2024.",
                    imageUrl = "https://www.power-technology.com/wp-content/uploads/sites/21/2024/08/shutterstock_347956412-430x241.jpg"
                ),
                Article(
                    title = "Interior Department Issues Offshore Wind Research Lease to State of Maine",
                    description = "The lease area covers less than 15,000 acres located 28 nautical miles offshore Maine on the U.S. Outer Continental Shelf and could allow for ...",
                    imageUrl = "https://www.doi.gov/sites/default/files/styles/social_media_2_1/public/images/2024-08/23f21ff6-9212-40db-8332-2bea583a598a-002.jpg?itok=Mpznqow8",
                ),
                Article(
                    title = "Purdue research: More than green energy, wind turbines are cost-saving CO2 eliminators",
                    description = "In a “game-changing” win against climate change, Purdue University research has discovered that wind turbines can not only provide the first ...",
                    imageUrl = "https://www.purdue.edu/research/features/wp-content/uploads/2024/08/FR-Turbines.jpg"
                ),
                Article(
                    title = "Q&A: How Alibaba’s DAMO Academy Uses AI to Improve Solar and Wind Energy Forecasting in China",
                    description = "DAMO engineers and scientists are leveraging artificial intelligence and machine learning to improve renewable energy forecasting in China.",
                    imageUrl = "https://alizila.oss-us-west-1.aliyuncs.com/uploads/2024/08/solar-wind-energy-alibaba-damo-academy-ai.png"
                ),
                Article(
                    title = "State, feds finalize agreement for nation’s 1st floating offshore wind power research lease",
                    description = "The deal brings Maine one step closer to creating the nation's first floating wind facility, tentatively a 15-square-mile array that will ...",
                    imageUrl = "https://www.pressherald.com/wp-content/uploads/sites/4/2024/07/Offshore_Research_Lease_63129_356701-1720639036.jpg"
                ),
                Article(
                    title = "US Interior Department issues offshore wind research lease to Maine",
                    description = "Maine secures the first floating offshore wind research lease from the US Department of the Interior, spanning 15000 acres.",
                    imageUrl = "https://media.assettype.com/bairdmaritime/2024-07/ea7af915-1ea3-4166-8ddd-00e2a45f26ba/bairdmaritime_import_wp_content_uploads_windmills_5994471_12801.jpg?w=1200&h=675&auto=format%2Ccompress&fit=max&enlarge=true"
                ),
                Article(
                    title = "Dominion secures another offshore wind lease — right next to Virginia Beach project",
                    description = "The new lease site could yield enough electricity to power up to 1.4 million homes, according to the federal government.",
                    imageUrl = "https://assets.vpm.org/dims4/default/0cb508e/2147483647/strip/true/crop/4032x2483+0+270/resize/880x542!/quality/90/?url=https%3A%2F%2Fnpr.brightspotcdn.com%2F0c%2F1d%2Fbdf153394ba3b3cb3c11d9dce997%2Fimg-4665.JPG"
                ),
                Article(
                    title = "Offshore Wind Development in the US is Reaching Gale Force",
                    description = "Today there are enough offshore wind projects moving forward to power nearly 5 million homes. Get a FREE Sierra magazine subscription today.",
                    imageUrl = "https://www.sierraclub.org/sites/default/files/styles/sierra_full_page_width/public/2024-08/sierra-offshore-wind-wb.jpg.webp?itok=jzwPqHcY"
                ),
                Article(
                    title = "‘Gateway’ to wind energy – Louisiana’s first wind turbine arrives in Jefferson Parish",
                    description = "In April, Louisiana's first wind turbine and its components arrived at AGG after a transatlantic journey from Ireland.",
                    imageUrl = "https://neworleanscitybusiness.com/files/2024/08/20240409_GWT_418-850x548.jpg"
                ),
                Article(
                    title = "This is Vestas",
                    description = "Every day, our 29,000 employees help to create a better world by designing, manufacturing, installing, developing, and servicing wind energy and ...",
                    imageUrl = "https://s7e5a.scene7.com/is/image/vestas/2019%20MHI%20Vestas-Aerial-Horns%20Rev%203-32871-HR-AdobeRGB?qlt=85&wid=1200&ts=1656054696825&\$Default-Image$&dpr=off"
                ),
                Article(
                    title = "Turbines return to world's first commercial floating wind farm after shutdown",
                    description = "Three of five turbines have returned to the Hywind Scotland floating offshore wind farm in the North Sea amid an ongoing shutdown of operations ...",
                    imageUrl = "https://cached.imagescaler.hbpl.co.uk/resize/scaleWidth/1272/cached.offlinehbpl.hbpl.co.uk/news/OPW/Equinormajority-ownstheHywindScotlandprojectintheScottishNorthSea(piccredit%C3%98yvindGrava%CC%8AsWoldcam)).png"
                )
            ).shuffled().take(3)
            "water" -> listOf(
                Article(
                    title =  "'Lacking information': Chile regulator kicks back Colbun's 800MW pumped hydro project environmental review",
                    description = "Chile utility Colbun has faced a setback to an 800MW pumped hydro storage project, after its environmental review process was terminated.",
                    imageUrl =  "https://www.energy-storage.news/wp-content/uploads/2024/08/image-2-9-1024x470.jpg",
                ),

                Article(
                    title =  "Statkraft announces plan to invest in Norwegian hydro and wind power",
                    description = "Statkraft is planning to invest up to €6 billion (US$6.6 billion) in upgrades to its Norwegian hydro and wind facilities and construction of ...",
                    imageUrl =  "https://www.hydroreview.com/wp-content/uploads/2022/03/svean_1_1500x1200.jpg",
                ),

                Article(
                    title =  "Share of primary energy consumption from hydroelectric power, 2023",
                    description = "Share of energy consumption by source, World. Measured as a percentage of primary energy, using thesubstitution method.",
                    imageUrl =  "https://ourworldindata.org/grapher/thumbnail/hydro-share-energy.png?imType=og",
                ),

                Article(
                    title =  "Floating photovoltaics may reduce the risk of hydro-dominated energy development in Africa",
                    description = "Floating photovoltaics (FPV) is fast becoming cost-competitive, but its social and environmental impacts are under debate.",
                    imageUrl =  "https://media.springernature.com/m685/springer-static/image/art%3A10.1038%2Fs41560-024-01510-0/MediaObjects/41560_2024_1510_Fig1_HTML.png",
                ),

                Article(
                    title =  "Abnormally Dry Canada Taps U.S. Energy, Reversing Usual Flow",
                    description = "Lower-than-normal rain and snow have reduced Canada's hydropower production, raising worries in the industry about the effects of climate ...",
                    imageUrl =  "https://static01.nyt.com/images/2024/05/22/multimedia/00hydro-01alt-hzqp/00hydro-01alt-hzqp-articleLarge.jpg?quality=75&auto=webp&disable=upscale",
                ),

                Article(
                    title =  "The World’s Biggest Hydro Powers",
                    description = "This chart shows the countries with the highest electricity generation from hydro power in 2022.",
                    imageUrl =  "https://cdn.statcdn.com/Infographic/images/normal/32027.jpeg",
                ),

                Article(
                    title =  "Research on joint dispatch of wind, solar, hydro, and thermal power based on pumped storage power stations",
                    description = "The joint operation of wind, solar, water, and thermal power based on pumped storage power stations is not only a supplement and improvement to traditional ...",
                    imageUrl =  "https://www.frontiersin.org/files/Articles/1373588/fenrg-12-1373588-HTML/image_m/fenrg-12-1373588-g001.jpg",
                ),

                Article(
                    title =  "China's hydro generators wait for the rains to come",
                    description = "Installed hydro capacity had climbed 18% to 422 million kilowatts (kW) by the end of 2023 from 358 million kW at the end of 2019, according to ...",
                    imageUrl =  "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/CGGJ3BFS2ZM3TDKBBUDVZK67P4.jpg",
                ),

                Article(
                    title =  "Glen Earrach Energy reveals plan for 2GW hydro project in Scotland",
                    description = "Glen Earrach Energy (GEE) has unveiled plans for a 2GW pumped storage hydro project on the Balmacaan estate in Scotland.",
                    imageUrl =  "https://www.power-technology.com/wp-content/uploads/sites/21/2024/05/Glen-Earrach-Energy-shutterstock_477298513-430x241.jpg",
                ),

                Article(
                    title =  "Southeast Asia Bets Billions On Pumped Hydro Energy Storage",
                    description = "Southeast Asia's pumped hydro capacity is projected to grow from 2.3 GW to 18 GW by 2033. Major investments are planned in the Philippines, ...",
                    imageUrl =  "https://d32r1sh890xpii.cloudfront.net/article/1200x900/2024-06-17_8qmenrvegs.jpg",
                ),

                Article(
                    title =  "Pumped Storage Hydro Could be Key to the Clean Energy Transition. But Where Will the Water Come From?",
                    description = "Dozens of proposed projects would pump water uphill to reservoirs that release it to generate electricity when wind and solar can't. But their ...",
                    imageUrl =  "https://insideclimatenews.org/wp-content/uploads/2023/11/PumpStorage_Ely_Gould006-scaled.jpg",
                ),

                Article(
                    title =  "How Much Energy Can Hydro Turbines In Water Pipes Generate?",
                    description = "The Easy Hydro system generates up to 600 kilowatts of energy, or enough to cover the needs of 300 households. When exploring the potential for ...",
                    imageUrl =  "https://vertassets.blob.core.windows.net/image/baa6a130/baa6a130-0661-4baa-b2ba-ef31a6e4b70d/375_250-0324_revolutionized.jpg",
                ),

                Article(
                    title =  "Hydro announces leadership changes",
                    description = "Hydro's new President and CEO Eivind Kallevik, who assumed the position May 13, has introduced changes to the company's executive leadership ...",
                    imageUrl =  "https://www.recyclingtoday.com/remote/aHR0cHM6Ly9naWVjZG4uYmxvYi5jb3JlLndpbmRvd3MubmV0L2ZpbGV1cGxvYWRzL2ltYWdlLzIwMjQvMDUvMTMvaHlkcm9hbGNhc3Rpbmd3ZWIuanBn.ONABo4XdANI.jpg?w=948&h=533&format=webp&mode=pad&anchor=middlecenter&scale=both&bgcolor=F0F1F2",
                ),

                Article(
                    title =  "China: Largest pumped hydro energy storage plant in the world fully operational",
                    description = "The 3.6GW Fengning Pumped Storage Power Station is located on the Luanhe River in Chengde City, Hebei Province, and is the largest PHES plant by ...",
                    imageUrl =  "https://www.energy-storage.news/wp-content/uploads/2024/08/China-Fengning-pumped-hydro-energy-storage-plant-2-1024x532.jpg",
                ),

                Article(
                    title =  "Per capita energy consumption from hydropower, 2023",
                    description = "Per capita primary energy consumption by source, 2023. Primary energy is measured in kilowatt-hours per person, usingthe substitution method.",
                    imageUrl =  "https://ourworldindata.org/grapher/thumbnail/per-capita-hydro.png?imType=twitter",
                ),

                Article(
                    title =  "Brazil's hydro power adds to global gas surplus",
                    description = "Brazil's gas imports fell to their lowest level for two decades in 2023 as its brimming hydroelectric reservoirs and rapid wind and solar ...",
                    imageUrl =  "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/ZKZEOCWQMFFUTIKBDA2CZSLG2M.jpg",
                ),

                Article(
                    title =  "RheEnergise to build first-of-a-kind hydro storage system",
                    description = "The UK company RheEnergise is to construct its first-of-a-kind hydro-storage reactor at a mine in Devon.",
                    imageUrl =  "https://www.power-technology.com/wp-content/uploads/sites/21/2021/02/source-Denis-Egan.jpg",
                ),

                Article(
                    title =  "Pumped hydro energy storage projects in New South Wales, Australia, granted 'critical' status",
                    description = "The New South Wales (NSW) government has deemed three pumped hydro energy storage (PHES) sites as “critical” given their importance to the ...",
                    imageUrl =  "https://www.energy-storage.news/wp-content/uploads/2024/01/Cruachan-dam-reservoir-and-substation.jpg",
                ),

                Article(
                    title =  "China to build hydro and gas, upgrade coal fleet for a more flexible power system",
                    description = "NDRC said new gas power plants will be built in areas with a stable supply of affordable natural gas, without giving a timeline. By 2027, NDRC ...",
                    imageUrl =  "https://www.reuters.com/resizer/v2/CL5VYZB5OVNL5HFDOTCFXRK5LU.jpg?auth=8359fe0ad4086da09c738f80285cd3162c173e526a47d8ed390a6162697d9e85&width=480&height=251&smart=true",
                ),

                Article(
                    title =  "Brazil diversifies clean power sources away from hydro",
                    description = "Brazilian utilities have plans for the further expansion of clean generation sources, with 10.8 gigawatts (GW) of solar capacity, 4.9 GW of wind ...",
                    imageUrl =  "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/OGQUQMGTQFE3VF5WGIN25KO2OM.jpg",
                ),

                Article(
                    title =  "UK’s 1st waterless hydro-energy storage to offer 2.5 times more power",
                    description = "UK startup is set to develop innovative waterless hydro energy storage system, addressing limitations of traditional hydro power.",
                    imageUrl =  "https://i.ytimg.com/vi/uGYi28zhnpM/maxresdefault.jpg",
                ),

                Article(
                    title =  "Estonian ministry, Zero Terrain sign MOU for pumped hydro energy storage",
                    description = "The ministry said signing this MOU will help Estonia achieve its 100% renewable energy goal by 2030. Zero Terrain is collaborating closely with ...",
                    imageUrl =  "https://www.hydroreview.com/wp-content/uploads/2024/04/ZeroTerrain_joonis-2-2048x1340-1-400x262.webp",
                ),

                Article(
                    title =  "Portugal's energy transition goals pumped up by hydro",
                    description = "Portugal sourced a record 72% of its electricity from clean power sources over the first 11 months of 2023, up from 56% over the same period ...",
                    imageUrl =  "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/AYT7XAGCHZEKDMXBC6WEBXJVSQ.jpg",
                ),

                Article(
                    title =  "‘Thermal hydro’ long-duration energy storage player Raygen gets SLB investment in Series D",
                    description = "RayGen, a startup developing long-duration energy storage (LDES) technology combined with CSP and PV, has kicked off Series D funding round.",
                    imageUrl =  "https://www.energy-storage.news/wp-content/uploads/2024/04/raygen-carwarp-victoria.jpg",
                ),

                Article(
                    title =  "Pumped hydro: Estonia government signs MoU for country’s first; NREL tool models costs of US projects",
                    description = "Pumped hydro: Estonia government signs MoU for country's first; NREL tool models costs of US projects · Enjoy 12 months of exclusive analysis.",
                    imageUrl =  "https://www.energy-storage.news/wp-content/uploads/2024/04/zeroterrain-joonis-300x196.jpg",
                ),

                Article(
                    title =  "Estonia’s First Pumped-Hydro Energy Storage Project Zero Terrain partners with the Estonian government and receives a grant of €1,9M",
                    description = "Construction of the country's first pumped-hydro storage plant will begin in 2025. During the nominal operating cycle of 12 hours, Zero Terrain ...",
                    imageUrl =  "https://ml-eu.globenewswire.com/Resource/Download/2cbd052e-529e-4878-b248-52bed8e1134f",
                ),

                Article(
                    title =  "Integrating pumped hydro with compressed air energy storage",
                    description = "Researchers from China's Harbin Institute of Technology proposed to combine pumped hydro storage systems with compressed air energy storage ( ...",
                    imageUrl =  "https://www.pv-magazine.com/wp-content/uploads/2024/05/Unbenannt-final.jpg",
                )
            ).shuffled().take(3)
            "thermal" -> listOf(
                Article(
                    title = "Ice Thermal Energy Storage Market to Reach $442.9 Billion, Globally, by 2030 at 12.6% CAGR: Allied Market Research",
                    description = "According to the report, the ice thermal energy storage market was valued at $192.5 billion in 2023, and is estimated to reach $442.9 billion by ...",
                    imageUrl = "https://s.yimg.com/ny/api/res/1.2/dkuAvQcz.kQCh_Y7pO2i_w--/YXBwaWQ9aGlnaGxhbmRlcjt3PTM2MDtoPTE5NA--/https://media.zenfs.com/en/globenewswire.com/9ee03c6bfd22081946a82dd9732ea895"
                ),
                Article(
                    title = "Innovative Use of Salt for Thermal Energy Storage by Georgia Tech Mechanical Engineers",
                    description = "Researchers at Georgia Tech's School of Mechanical Engineering are pioneering the use of common salts to store clean energy as heat.",
                    imageUrl = "https://www.tun.com/home/wp-content/uploads/2023/09/google.jpeg"
                ),
                Article(
                    title = "Brenmiller Energy expands bGen tech for AI data center cooling applications",
                    description = "Brenmiller Energy Ltd, a global leader in thermal energy storage (TES) solutions, has announced plans to expand its bGen™ technology to ...",
                    imageUrl = "https://images.jpost.com/image/upload/q_auto/c_fill,g_faces:center,h_537,w_822/616349"
                ),
                Article(
                    title = "EU-funded GEOFLEXheat project to harness waste geothermal heat for industrial use",
                    description = "The EU-funded GEOFLEXheat project, led by Brunel University London, seeks to harness waste heat from geothermal brine for industrial ...",
                    imageUrl = "https://www.thinkgeoenergy.com/wp-content/uploads/2024/08/Brunel_University_London_Campus_August_2023_02.jpg"
                ),
                Article(
                    title = "The role of pit lake thermal dynamics on the thermal performance of ground heat exchangers",
                    description = "In this study, we develop and validate finite element models to assess the relevance of lake thermal stratification in the performance of a ...",
                    imageUrl = "https://media.springernature.com/m685/springer-static/image/art%3A10.1038%2Fs41598-024-69225-6/MediaObjects/41598_2024_69225_Fig1_HTML.png"
                ),
                Article(
                    title = "What You Can Learn From Thermal Energy International Inc.'s ",
                    description = "What Does Thermal Energy International's P/S Mean For Shareholders? With revenue growth that's superior to most other companies of late, Thermal ...",
                    imageUrl = "https://images.simplywall.st/asset/industry/3072000-choice1-main-header/1585186775716"
                ),
                Article(
                    title = "Solar-heated cement calcining – aided by the greenhouse gas effect?",
                    description = "It leverages the greenhouse gas effect: By injecting H2O and CO2 into the solar receiver, the sunlight collected by the receiver cannot escape ...",
                    imageUrl = "https://www.solarpaces.org/wp-content/uploads/2024/08/solar-heated-cement-calcining.jpg"
                ),
                Article(
                    title = "‘Every building sits on a thermal asset’: how networked geothermal power could change cities",
                    description = "The ground is humming with geothermal energy that could heat or cool our homes – and now the big US utilities are starting to take note.",
                    imageUrl = "https://i.guim.co.uk/img/media/03c2fce204640a3daf81bc0f510dcbd251161f21/0_573_7378_4427/master/7378.jpg?width=480&dpr=1&s=none"
                ),
                Article(
                    title = "Thermal will remain key player in India’s energy mix: SECI chief",
                    description = "Coal in India's energy mix will remain a key player by the 2060s to meet the country's growing power demand while the non-fossil share is ...",
                    imageUrl = "https://akm-img-a-in.tosshub.com/businesstoday/images/story/202408/66c482eec6a2e-rp-gupta--cmd--seci--at-bt-india-100-205005507-16x9.jpg?size=948:533"
                ),
                Article(
                    title = "Massachusetts kicks off first pilot to shift gas utilities to clean heat",
                    description = "On Tuesday, utility Eversource will flip the switch on the country's first utility-operated underground thermal energy network. The $14 million ...",
                    imageUrl = "https://img.canarymedia.com/content/uploads/Eversource-Framingham-Geothermal-borehole-drilling.jpg?auto=compress%2Cformat&crop=focalpoint&fit=crop&fp-x=0.1654&fp-y=0.5916&h=501&q=80&w=864&s=d76c01504c18678d886446bfe25480ad"
                ),
                Article(
                    title = "Things are heating up, while UNH is cooling down",
                    description = "Since there is less energy demand at night, UNH is able to cool water using only this renewable fuel. There is more chilled water produced at ...",
                    imageUrl = "https://www.unh.edu/sustainability/sites/default/files/styles/landscape_480x310/public/media/2024-02/unh-energy-thermal-tank-project.jpg?h=19f14c2c&itok=PIcxuBS2"
                ),
                Article(
                    title = "China: coal production up 2.8% in July",
                    description = "China recorded coal production of 390.37 million tonnes in July 2024, marking an increase of 2.8% on the previous year. This increase comes ...",
                    imageUrl = "https://energynews.pro/wp-content/uploads/2024/08/china.jpg"
                ),
                Article(
                    title = "Funding Notice: Combined Wellbore Construction High Temperature Tools and Reservoir Thermal Energy Storage (RTES)",
                    description = "Projects under Topic Area 1 will reduce costs and technical challenges associated with wellbore construction for enhanced geothermal systems (EGS), which will ...",
                    imageUrl = "https://www.energy.gov/sites/default/files/styles/full_article_width/public/2024-03/RTES-graphics-v3-04.png?itok=VOABOt5A"
                ),

                Article(
                    title = "Washingtonians Will Soon Enjoy Cleaner Heating and Cooling Options",
                    description = "Washington's House Bill 2131 opens the door for any energy utility, electric or gas, public or private, to sell thermal energy. Gas utilities ...",
                    imageUrl = "https://www.sightline.org/wp-content/uploads/2024/03/sewer-heat_900xx1760-990-37-0-772x434.jpg"
                ),
                Article(
                    title = "China's thermal power generation falls for third month in July despite record heat",
                    description = "China's thermal power generation last month fell 4.9% from a year ago to 574.9 billion kilowatt-hours (kWh), despite total power generation r ...",
                    imageUrl = "https://www.naturalgasworld.com/content/112661/power1_f175x175.jpg"
                ),
                Article(
                    title = "CenterPoint Energy Issues All-Source RFP Seeking Renewable, Thermal and Demand-Side Resources",
                    description = "",
                    imageUrl = ""
                ),
                Article(
                    title = "CenterPoint Energy Issues All-Source RFP Seeking Renewable, Thermal and Demand-Side Resources",
                    description = "CenterPoint Energy Indiana South launches ambitious All-Source RFP, seeking diverse energy solutions to power 150000 customers by 2028.",
                    imageUrl = "https://static.stocktitan.net/company-logo/cnp-lg.webp"
                ),
                Article(
                    title = "Electrification of process heat stands to slash industry’s emissions",
                    description = "Technologies available now can decarbonise most heat demand, but cost and infrastructure barriers still exist.",
                    imageUrl = "https://d2cbg94ubxgsnp.cloudfront.net/Pictures/480x270/0/4/2/536042_shutterstock_2323764417hero_722910.jpg"
                ),
                Article(
                    title = "Supercooled erythritol for high-performance seasonal thermal energy storage",
                    description = "Seasonal storage of solar thermal energy through supercooled phase change materials (PCM) offers a promising solution for decarbonizing ...",
                    imageUrl = "https://media.springernature.com/m685/springer-static/image/art%3A10.1038%2Fs41467-024-49333-7/MediaObjects/41467_2024_49333_Fig1_HTML.png"
                ),
                Article(
                    title = "CEO Spotlight: Brenmiller Energy Is Bringing Thermal Energy Storage Solutions To The Mainstream",
                    description = "A. The bGen™ ZERO is a cutting-edge TES system that utilizes crushed rocks to store heat at high temperatures. This stored heat can be released ...",
                    imageUrl = "https://media.zenfs.com/en/newsfile_64/67a76ca4a7e3bfa082e4596c9e2c385c"
                )
            ).shuffled()
                .take(3)
            else -> emptyList()  // In case the tag doesn't match any of the predefined categories.
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}