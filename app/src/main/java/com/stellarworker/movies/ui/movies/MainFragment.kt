package com.stellarworker.movies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stellarworker.movies.databinding.FragmentMainBinding
import com.stellarworker.movies.domain.AppMessage
import com.stellarworker.movies.domain.Top250MovieInt
import com.stellarworker.movies.ui.details.DetailsFragment
import com.stellarworker.movies.utils.hide
import com.stellarworker.movies.utils.loadFragment
import com.stellarworker.movies.utils.makeSnackbar
import com.stellarworker.movies.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainFragmentViewModel: MainFragmentViewModel by viewModel()
    private val adapter = MainFragmentAdapter(
        onItemClicked = { top250MovieInt ->
            activity?.supportFragmentManager?.let { manager ->
                loadFragment(
                    DetailsFragment.newInstance(top250MovieInt),
                    DetailsFragment.FRAGMENT_TAG,
                    manager
                )
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initMainFragmentViewModel()
        mainFragmentViewModel.getMovies()
    }

    private fun initMainFragmentViewModel() {
        mainFragmentViewModel.messagesLiveData.observe(viewLifecycleOwner) { message ->
            processMessages(message)
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            fragmentMainList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            fragmentMainList.adapter = adapter
        }
    }

    private fun processMessages(appMessage: AppMessage) {
        with(appMessage) {
            when (this) {
                is AppMessage.Top250Movies -> showMovies(movies)
                is AppMessage.InfoSnackBar -> makeSnackbar(
                    view = binding.root,
                    text = text
                )
                is AppMessage.InfoToast -> Toast.makeText(context, text, length).show()
            }
        }
    }

    private fun showMovies(movies: List<Top250MovieInt>) {
        with(binding) {
            fragmentMainHeader.show()
            fragmentMainList.show()
            fragmentMainLoadingProgress.hide()
        }
        adapter.setData(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}