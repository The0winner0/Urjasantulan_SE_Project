package com.example.urjasantulan.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.urjasantulan.LikedItemsHolder
import com.example.urjasantulan.R
import com.example.urjasantulan.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val likedItems = LikedItemsHolder.likedItems.toList()
        val adapter = object : ArrayAdapter<String>(requireContext(), R.layout.solution_item, likedItems) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.solution_item, parent, false)
                val textView: TextView = view.findViewById(R.id.textViewItem)
                val buttonLike: ImageButton = view.findViewById(R.id.buttonLike)
                buttonLike.setImageResource(R.drawable.postive_bookmark)

                textView.text = getItem(position)

                // Handle "like" button click
                buttonLike.setOnClickListener {
                    val item = getItem(position)
                    if (item != null) {
                        if (LikedItemsHolder.likedItems.contains(item)) {
                            LikedItemsHolder.likedItems.remove(item)
                            buttonLike.setImageResource(R.drawable.negative_bookmark)
                        } else {
                            LikedItemsHolder.likedItems.add(item)
                            buttonLike.setImageResource(R.drawable.postive_bookmark)
                        }
                    }
                }


                return view
            }
        }
        binding.bookmarkList.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}