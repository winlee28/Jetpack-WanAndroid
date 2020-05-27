package com.win.ft_home.ui.navi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.win.ft_home.R
import com.win.lib_common_ui.flowlayout.TagFlowLayout
import com.win.lib_common_ui.flowlayout.adapter.TagAdapter

class NavigationFragment : Fragment() {

    private lateinit var notificationsViewModel: NavigationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NavigationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_navigation, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val datas = "GitHub is built for collaboration. Set up an organization to improve the way your team works together, and get access to more features.".split(" ")



        val fw1: TagFlowLayout = root.findViewById(R.id.flow1)
        val fw2: TagFlowLayout = root.findViewById(R.id.flow2)

        val adapter = object : TagAdapter() {
            override fun getItemCount(): Int {
                return datas.size
            }

            override fun createView(
                inflater: LayoutInflater,
                parent: ViewGroup,
                position: Int
            ): View {
                return inflater.inflate(R.layout.flow_layout_item, parent, false)
            }

            override fun bindView(view: View, position: Int) {
                (view as TextView).text = datas[position]
            }

        }

        fw1.setAdapter(adapter)
        fw2.setAdapter(adapter)


        return root
    }
}