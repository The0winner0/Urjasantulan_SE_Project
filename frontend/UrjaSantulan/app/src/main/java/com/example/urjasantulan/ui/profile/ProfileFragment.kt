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

        var result = 1;
        lastStatus.text = "Stable"


        submitButton.setOnClickListener() {
            val inp1 = inp1EditText.text.toString()
            val inp2 = inp2EditText.text.toString()
            val inp3 = inp3EditText.text.toString()
            val inp4 = inp4EditText.text.toString()

            Log.i("idk", "onCreateView: $inp1, $inp2, $inp3, $inp4")

            // Model Call

            if (result == 1) {
                result = 0
                lastStatus.text = "Stable"
            } else {
                result = 1
                lastStatus.text = "Unstable"
            }
            onCreateView(inflater, container, savedInstanceState)

        }


        val solutionArray = arrayOf(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc.",
            "Solution 2",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc.",
            "Lorem ipsum dolor sit amet, cLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nundolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nunc.",
            "Lorem ipsum dolor sit amet, cons.",
            "Solution 3",
            "Solution 4",
        )

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