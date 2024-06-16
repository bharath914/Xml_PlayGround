package com.example.views_example.presentation.adapters

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.views_example.R
import com.example.views_example.databinding.ExpandedListItemBinding
import com.example.views_example.databinding.ReportsListItemBinding
import com.example.views_example.presentation.fragments.expandableCards.ExpandableListItem
import com.example.views_example.presentation.fragments.expandableCards.OnExpandedItems

class ReportsAdapter :
    ListAdapter<ExpandableListItem, ReportsAdapter.ReportsViewHolder>(ReportsDiffUtil()) {

    inner class ReportsViewHolder(private val binding: ReportsListItemBinding) :
        ViewHolder(binding.root) {
        private val expandedReportsAdapter = ExpandedReportsAdapter()
        fun bind(item: ExpandableListItem) {
            binding.apply {
                this.tvTitle.text = item.title
                mainContent.setOnClickListener {
                    expandedReportsAdapter
                        .apply {
                            submitList(item.items)
                        }

                    innerRecyclerView.apply {
                        adapter = expandedReportsAdapter
                        layoutManager = LinearLayoutManager(root.context)
                        setHasFixedSize(true)
                    }
                    if (item.isExpanded) {
                        collapseView(innerRecyclerView, ivExpandMore)
                    } else {
                        expandView(innerRecyclerView, ivExpandMore)
                    }

                    item.isExpanded = !item.isExpanded

                }
            }

        }
    }

    private fun expandView(view: View, button: ImageView) {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        val targetHeight = view.measuredHeight
        view.layoutParams.height = 0
        view.visibility = View.VISIBLE
        val animator = ValueAnimator.ofInt(1, targetHeight)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()
        }
        animator.duration = 400
        animator.start()
        val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
        view.startAnimation(fadeIn)

        button.animate().rotation(180f).setDuration(300).start()
    }

    private fun collapseView(view: View, button: ImageView) {
        val initialHeight = view.measuredHeight
        val animator = ValueAnimator.ofInt(initialHeight, 0)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            if (value == 0) {
                view.visibility = View.GONE
            }
            view.layoutParams.height = value
            view.requestLayout()
        }
        val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
        fadeOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                view.visibility = View.GONE
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        animator.start()
        animator.duration = 400
        view.startAnimation(fadeOut)
        button.animate().rotation(0f).setDuration(300).start()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        return ReportsViewHolder(
            ReportsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}


class ReportsDiffUtil : DiffUtil.ItemCallback<ExpandableListItem>() {
    override fun areItemsTheSame(
        oldItem: ExpandableListItem,
        newItem: ExpandableListItem,
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: ExpandableListItem,
        newItem: ExpandableListItem,
    ): Boolean {
        return oldItem == newItem
    }
}


class OnExpandedDiffUtil : DiffUtil.ItemCallback<OnExpandedItems>() {
    override fun areItemsTheSame(oldItem: OnExpandedItems, newItem: OnExpandedItems): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: OnExpandedItems, newItem: OnExpandedItems): Boolean {
        return oldItem == newItem
    }
}

class ExpandedReportsAdapter :
    ListAdapter<OnExpandedItems, ExpandedReportsAdapter.MyViewHolder>(OnExpandedDiffUtil()) {

    inner class MyViewHolder(private val binding: ExpandedListItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: OnExpandedItems) {
            binding.apply {
                tvTitle.text = item.title
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ExpandedListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}