package com.liveshopper.feature.tasklist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lab651.liveshopper.task.model.Task
import com.liveshopper.R
import com.liveshopper.feature.location.LocationRepository
import com.liveshopper.feature.task.TaskActivity
import com.liveshopper.feature.tasklist.ui.TaskListAdapter
import com.liveshopper.repository.TaskRepository
import com.liveshopper.util.getApplication
import kotlinx.android.synthetic.main.task_list_fragment.*

class TaskListFragment : Fragment() {

    private val viewModel by lazy {
        val tasks = getApplication().tasks
        val factory = TaskListViewModelFactory(TaskRepository, LocationRepository(activity!!), tasks)
        ViewModelProviders.of(this, factory).get(TaskListViewModel::class.java)
    }

    private val adapter by lazy {
        TaskListAdapter(viewModel.currentLocation) {

            val intent = Intent(activity, TaskActivity::class.java).apply {
                putExtra(EXTRA_TASK_ID, it.getId())
            }
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        observeActions()
        viewModel.activityCreated()
    }

    private fun observeActions() {
        viewModel.liveActions.observe(this, Observer { action ->
            when (action) {
                is TaskListViewModel.Action.ShowTasks -> displayTasks(action.tasks)
            }
        })
    }

    private fun displayTasks(tasks: List<Task>) {
        adapter.submitList(tasks)
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@TaskListFragment.adapter
        }
    }

    companion object {
        fun newInstance() = TaskListFragment()
        const val EXTRA_TASK_ID = "taskId"
    }
}
