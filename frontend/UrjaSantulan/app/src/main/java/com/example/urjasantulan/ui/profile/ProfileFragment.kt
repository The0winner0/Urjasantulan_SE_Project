package com.example.urjasantulan.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.urjasantulan.LikedItemsHolder
import com.example.urjasantulan.R
import com.example.urjasantulan.databinding.FragmentProfileBinding
import com.example.urjasantulan.ml.SmartGridStabilityModelNew
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun adjustListViewHeightBasedOnItems(listView: ListView) {
        val listAdapter = listView.adapter ?: return

        listView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                var totalHeight = 0
                for (i in 0 until listAdapter.count) {
                    val listItem = listAdapter.getView(i, null, listView)
                    listItem.measure(
                        View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.UNSPECIFIED
                    )
                    totalHeight += listItem.measuredHeight
                }

                val params = listView.layoutParams
                params.height = totalHeight + (listView.dividerHeight * (listAdapter.count - 1))
                listView.layoutParams = params

                listView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val lastStatus: TextView = root.findViewById(R.id.lastStatus)
        val submitButton: Button = root.findViewById(R.id.submitButton)
        val inp1EditText: EditText = root.findViewById(R.id.inp1)
        val inp2EditText: EditText = root.findViewById(R.id.inp2)
        val inp3EditText: EditText = root.findViewById(R.id.inp3)
        val inp4EditText: EditText = root.findViewById(R.id.inp4)
        val inp5EditText: EditText = root.findViewById(R.id.inp5)
        val inp6EditText: EditText = root.findViewById(R.id.inp6)
        val inp7EditText: EditText = root.findViewById(R.id.inp7)
        val inp8EditText: EditText = root.findViewById(R.id.inp8)
        val inp9EditText: EditText = root.findViewById(R.id.inp9)
        val inp10EditText: EditText = root.findViewById(R.id.inp10)
        val inp11EditText: EditText = root.findViewById(R.id.inp11)
        val inp12EditText: EditText = root.findViewById(R.id.inp12)

        lastStatus.text = "IDK"

        submitButton.setOnClickListener() {
            val inp1 = inp1EditText.text.toString().toFloat()
            val inp2 = inp2EditText.text.toString().toFloat()
            val inp3 = inp3EditText.text.toString().toFloat()
            val inp4 = inp4EditText.text.toString().toFloat()
            val inp5 = inp5EditText.text.toString().toFloat()
            val inp6 = inp6EditText.text.toString().toFloat()
            val inp7 = inp7EditText.text.toString().toFloat()
            val inp8 = inp8EditText.text.toString().toFloat()
            val inp9 = inp9EditText.text.toString().toFloat()
            val inp10 = inp10EditText.text.toString().toFloat()
            val inp11 = inp11EditText.text.toString().toFloat()
            val inp12 = inp12EditText.text.toString().toFloat()

            Log.i("", "Inputs: $inp1 $inp2 $inp3 $inp4 $inp5 $inp6 $inp7 $inp8 $inp9 $inp10 $inp11 $inp12")

            // Model Call
            val model = SmartGridStabilityModelNew.newInstance(requireContext())

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 12), DataType.FLOAT32)
            inputFeature0.loadArray(floatArrayOf(inp1, inp2, inp3, inp4, inp5, inp6, inp7, inp8, inp9, inp10, inp11, inp12))

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
            val result = outputFeature0.floatArray[0]
            Log.i("idk", "nEW Result: $result")
            if(result > 2.5E-8) {
                lastStatus.text = "Stable"
            } else {
                lastStatus.text = "Unstable"
            }
            model.close()

        }

        val strategies = arrayOf(
            "Selective Load Shedding: Implement targeted load shedding in non-critical areas to maintain grid balance without disrupting essential services.",
            "Time-of-Use Pricing: Introduce dynamic pricing models that encourage consumers to use electricity during off-peak hours, reducing peak load stress.",
            "Critical Peak Pricing (CPP): Charge higher rates during peak periods to incentivize reduced consumption, lowering grid stress.",
            "Demand Response Programs: Enroll large commercial consumers in demand response programs where they voluntarily reduce consumption during high-demand periods.",
            "Direct Load Control: Enable utilities to remotely control non-essential appliances like HVAC systems during peak load times to reduce demand.",
            "Real-Time Consumption Feedback: Provide real-time usage data to consumers through smart meters, encouraging energy-efficient behavior and reducing peak loads.",
            "Load Prioritization: Prioritize critical loads such as hospitals and emergency services, ensuring they receive power during instability events.",
            "Appliance Scheduling: Encourage consumers to schedule high-energy-consuming appliances, like dishwashers and washing machines, during off-peak hours.",
            "Energy Conservation Campaigns: Launch public awareness campaigns on energy conservation practices during periods of instability to reduce overall demand.",
            "Incentive Programs for Off-Peak Usage: Offer financial incentives to consumers who shift their energy usage to off-peak times.",
            "Grid-Scale Battery Storage: Deploy large-scale battery systems to store excess energy and release it during periods of high demand.",
            "Distributed Energy Storage Systems (DESS): Encourage the installation of distributed batteries in homes and businesses to provide localized grid support.",
            "Pumped Hydro Storage: Use pumped hydroelectric storage to balance supply and demand by storing excess energy during low demand and releasing it during peak periods.",
            "Flywheel Energy Storage: Implement flywheel systems to provide short-term frequency regulation and energy balancing.",
            "Thermal Energy Storage: Utilize thermal storage technologies to store excess energy as heat or cold for later use, reducing the strain on the grid.",
            "Hydrogen Energy Storage: Convert excess electricity into hydrogen through electrolysis and store it for later use in fuel cells, providing a flexible energy reserve.",
            "Vehicle-to-Grid (V2G) Systems: Enable electric vehicles to return stored energy to the grid during peak demand, acting as distributed storage units.",
            "Smart Inverters: Use advanced inverters with energy storage systems to smooth out the variability of renewable energy sources like solar and wind.",
            "Hybrid Renewable Systems: Combine solar, wind, and energy storage in hybrid systems to ensure a steady energy supply, even when renewable output fluctuates.",
            "Curtailment of Renewable Generation: Temporarily reduce the output of renewable energy sources during periods of grid instability to prevent oversupply.",
            "Dynamic Line Rating: Adjust the power-carrying capacity of transmission lines in real-time based on ambient conditions, maximizing their efficiency and preventing overloads.",
            "Grid Reconfiguration: Reroute power through different pathways in the grid to alleviate congestion and prevent overloading critical lines.",
            "Phase-Shifting Transformers: Use phase-shifting transformers to control power flow between different sections of the grid, reducing bottlenecks.",
            "Flexible AC Transmission Systems (FACTS): Implement FACTS devices to enhance controllability and increase power transfer capability, improving overall grid stability.",
            "High-Temperature Superconductors (HTS): Utilize HTS cables to increase transmission efficiency and reduce losses, enhancing the grid's capacity to handle high loads.",
            "Undergrounding Power Lines: Bury transmission and distribution lines underground in areas prone to weather-related outages, improving grid reliability.",
            "Substation Automation: Implement automation technologies in substations to enable faster response times and more efficient management of power flows.",
            "Dynamic Voltage Restorer (DVR): Deploy DVRs to quickly restore voltage levels during dips, preventing equipment malfunctions and improving stability.",
            "High-Voltage Direct Current (HVDC) Transmission: Use HVDC lines to transmit power over long distances with lower losses, improving grid efficiency and stability.",
            "Synchronous Condensers: Install synchronous condensers to provide reactive power support, stabilizing voltage levels and improving power quality.",
            "Automatic Generation Control (AGC): Continuously adjust the output of multiple generators in real-time to match supply with demand and maintain frequency stability.",
            "Primary Frequency Response: Enable generators to automatically respond to frequency deviations by adjusting their output, stabilizing grid frequency.",
            "Fast Frequency Response (FFR): Utilize fast-responding resources like battery storage to provide immediate frequency support during sudden drops or spikes.",
            "Spinning Reserve: Maintain a reserve of online generation capacity that can be quickly ramped up in response to sudden demand increases or generation losses.",
            "Non-Spinning Reserve: Keep additional generation capacity on standby, which can be activated within a short time frame to support the grid during emergencies.",
            "Load Frequency Control (LFC): Implement LFC systems to balance load and generation across different areas of the grid, ensuring frequency stability.",
            "Droop Control: Use droop control settings in generators to share the load among multiple units, preventing any single generator from being overloaded.",
            "Inertia Emulation: Use advanced inverter-based generation to emulate the inertia provided by traditional synchronous generators, enhancing frequency stability.",
            "Governor Control: Adjust the governor settings on generators to respond more effectively to frequency changes, improving overall grid stability.",
            "Synthetic Inertia: Implement synthetic inertia in wind turbines and other renewable energy sources to provide fast frequency response during grid disturbances.",
            "Tap-Changing Transformers: Use on-load tap-changing transformers to automatically adjust voltage levels in response to changing grid conditions.",
            "Static VAR Compensators (SVCs): Deploy SVCs to provide dynamic reactive power support, stabilizing voltage levels and improving power quality.",
            "Capacitor Banks: Install capacitor banks to provide reactive power support, reducing voltage drops and improving power factor.",
            "Dynamic Voltage Control: Implement real-time voltage control systems that adjust voltage levels based on load conditions, preventing voltage instability.",
            "Power Factor Correction: Use power factor correction equipment to reduce the reactive power demand on the grid, improving overall efficiency and stability.",
            "Distribution Voltage Regulators: Install voltage regulators on distribution lines to maintain consistent voltage levels, preventing voltage fluctuations.",
            "Harmonic Filters: Deploy harmonic filters to remove harmful harmonics from the grid, improving power quality and reducing equipment stress.",
            "Voltage Optimization: Implement voltage optimization strategies to reduce energy consumption and improve voltage stability without compromising performance.",
            "Advanced Metering Infrastructure (AMI): Utilize smart meters to monitor voltage levels in real-time, enabling faster detection and correction of voltage issues.",
            "Power Quality Monitoring: Continuously monitor power quality parameters such as voltage, frequency, and harmonics to detect and address issues before they affect grid stability.",
            "Microgrid Deployment: Establish microgrids that can operate independently or in coordination with the main grid, enhancing resilience and stability.",
            "Islanded Operation of Microgrids: Enable microgrids to operate in island mode during grid disturbances, providing localized stability.",
            "Peer-to-Peer Energy Trading: Implement peer-to-peer energy trading within microgrids, optimizing local energy resources and reducing reliance on the central grid.",
            "Virtual Power Plants (VPPs): Aggregate distributed energy resources into a VPP that can be controlled like a single power plant, enhancing grid flexibility.",
            "Community Solar Projects: Promote community solar installations that feed excess energy back into the grid, supporting local grid stability.",
            "Cogeneration Systems: Utilize combined heat and power (CHP) systems in industrial facilities to generate electricity and thermal energy, reducing grid load.",
            "Small-Scale Wind Turbines: Encourage the installation of small-scale wind turbines in rural areas, providing distributed generation and reducing transmission losses.",
            "Biogas Generation: Promote biogas production from agricultural waste, which can be used as a renewable energy source for local generation.",
            "Hydropower Integration: Integrate small-scale hydropower plants into the grid, providing a reliable source of renewable energy.",
            "Geothermal Energy Utilization: Develop geothermal power plants in regions with geothermal resources, providing stable, baseload generation.",
            "Advanced Distribution Management Systems (ADMS): Implement ADMS to monitor and control the distribution network in real-time, enhancing grid stability.",
            "Self-Healing Grid Technologies: Deploy self-healing grid technologies that automatically detect and isolate faults, restoring power quickly and minimizing outages.",
            "Wide-Area Monitoring Systems (WAMS): Use WAMS to provide real-time visibility of grid conditions across large areas, enabling faster response to instability events.",
            "Phasor Measurement Units (PMUs): Install PMUs to provide high-resolution data on grid dynamics, improving the detection of stability issues.",
            "Supervisory Control and Data Acquisition (SCADA): Upgrade SCADA systems for better monitoring and control of grid operations, enhancing overall stability.",
            "Smart Relays: Use smart relays that can communicate and coordinate with other grid devices to improve fault detection and isolation.",
            "Distribution Automation (DA): Implement DA technologies that enable automatic reconfiguration of the distribution network in response to faults or other issues.",
            "Predictive Analytics: Use predictive analytics to forecast potential grid stability issues and proactively address them before they escalate.",
            "Artificial Intelligence (AI) in Grid Management: Deploy AI-based systems to optimize grid operations, predicting and mitigating instability in real-time.",
            "Blockchain for Grid Transactions: Use blockchain technology to securely manage transactions and data within the grid, enhancing transparency and trust.",
            "Intrusion Detection Systems (IDS): Implement IDS to monitor for and detect potential cyber threats to the grid, preventing malicious attacks that could cause instability.",
            "Advanced Encryption Protocols: Use advanced encryption to protect sensitive grid data and communication, ensuring the integrity and confidentiality of grid operations.",
            "Multi-Factor Authentication (MFA): Require MFA for access to critical grid control systems, reducing the risk of unauthorized access and cyberattacks.",
            "Real-Time Cyber Threat Monitoring: Continuously monitor for cyber threats in real-time, enabling quick response to potential security breaches.",
            "Cybersecurity Training for Personnel: Provide regular cybersecurity training for grid operators and personnel to prevent human error-related vulnerabilities.",
            "Secure Communication Channels: Ensure that all communication channels used in grid operations are secure and encrypted, preventing data interception.",
            "Decentralized Data Storage: Use decentralized storage systems to reduce the risk of data loss or corruption in the event of a cyberattack.",
            "Redundant Control Systems: Implement redundant control systems that can take over in the event of a cyberattack, ensuring continuous grid operation.",
            "Automated Threat Response Systems: Deploy automated systems that can detect and respond to cyber threats in real-time, minimizing the impact on grid stability.",
            "Blockchain for Cybersecurity: Utilize blockchain technology to create tamper-proof records of grid transactions and operations, enhancing overall cybersecurity.",
            "Grid Code Compliance: Ensure that all grid operators and participants comply with established grid codes and standards to maintain stability.",
            "Incentives for Energy Efficiency: Offer incentives for consumers and businesses to adopt energy-efficient technologies, reducing overall demand on the grid.",
            "Renewable Portfolio Standards (RPS): Implement RPS to encourage the integration of renewable energy sources in a controlled manner, ensuring grid stability.",
            "Energy Storage Mandates: Require utilities to maintain a certain level of energy storage capacity to provide grid stability during periods of high demand or supply fluctuations.",
            "Demand Response Mandates: Mandate the participation of large consumers in demand response programs to provide additional flexibility in grid management.",
            "Grid Modernization Programs: Invest in grid modernization initiatives that upgrade aging infrastructure, improving overall reliability and stability.",
            "Interconnection Standards for Distributed Generation: Establish clear interconnection standards for distributed generation to ensure safe and stable integration into the grid.",
            "Emergency Preparedness Plans: Develop and regularly update emergency preparedness plans that outline procedures for maintaining grid stability during extreme events.",
            "Grid Resilience Planning: Incorporate resilience planning into grid operations, focusing on minimizing the impact of extreme weather and other disruptions.",
            "Public-Private Partnerships: Encourage public-private partnerships to fund and implement grid stability projects, leveraging resources and expertise from both sectors.",
            "Research on Advanced Materials: Invest in research on advanced materials, such as superconductors, to improve the efficiency and stability of the grid.",
            "Development of New Energy Storage Technologies: Support the development of next-generation energy storage technologies that offer higher capacity and faster response times.",
            "Pilot Projects for Innovative Grid Technologies: Launch pilot projects to test and validate innovative grid technologies, such as AI-based grid management systems.",
            "Collaboration with Academic Institutions: Partner with academic institutions to conduct research on grid stability and resilience, fostering innovation and knowledge sharing.",
            "Testbeds for Grid Technologies: Create testbeds where new grid technologies can be tested and refined in a controlled environment before full-scale deployment.",
            "Funding for Grid Innovation: Provide funding opportunities for startups and researchers working on grid stability solutions, encouraging innovation in the energy sector.",
            "International Collaboration on Grid Stability: Engage in international collaboration to share best practices and technologies for grid stability, benefiting from global expertise.",
            "Standardization of Grid Technologies: Work towards the standardization of new grid technologies, ensuring interoperability and seamless integration into the existing grid.",
            "Public Awareness of Grid Innovation: Raise public awareness of the importance of grid stability and the role of innovative technologies in ensuring a reliable energy supply.",
            "Long-Term Grid Planning: Focus on long-term planning for grid stability, considering future energy demands, technology advancements, and environmental challenges."
        )

        val solutionArray = strategies
            .toList()            // Convert the array to a list
            .shuffled()         // Shuffle the list
            .take(5)            // Take the first 5 elements
            .toTypedArray()     // Convert back to an array



        val adapter = object : ArrayAdapter<String>(requireContext(), R.layout.solution_item, solutionArray) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.solution_item, parent, false)
                val textView: TextView = view.findViewById(R.id.textViewItem)
                val buttonLike: ImageButton = view.findViewById(R.id.buttonLike)
                val item = getItem(position)
                if (LikedItemsHolder.likedItems.contains(item)) {
                    buttonLike.setImageResource(R.drawable.postive_bookmark)
                } else {
                    buttonLike.setImageResource(R.drawable.negative_bookmark)
                }

                textView.text = getItem(position)

                // Handle "like" button click
                buttonLike.setOnClickListener {
                    val item = getItem(position)
                    if (item != null) {
                        if (LikedItemsHolder.likedItems.contains(item)) {
                            LikedItemsHolder.likedItems.remove(item)
                            buttonLike.setImageResource(R.drawable.negative_bookmark)
                            Toast.makeText(requireContext(), "Bookmark Removed", Toast.LENGTH_SHORT).show()
                        } else {
                            LikedItemsHolder.likedItems.add(item)
                            buttonLike.setImageResource(R.drawable.postive_bookmark)
                            Toast.makeText(requireContext(), "Bookmark Added", Toast.LENGTH_SHORT).show()
                        }
                    }
                }


                return view
            }
        }
        binding.solutionList.adapter = adapter
        adjustListViewHeightBasedOnItems(binding.solutionList)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
