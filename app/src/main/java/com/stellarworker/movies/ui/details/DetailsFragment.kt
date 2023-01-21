package com.stellarworker.movies.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.stellarworker.movies.R
import com.stellarworker.movies.databinding.FragmentDetailsBinding
import com.stellarworker.movies.domain.Top250MovieInt
import com.stellarworker.movies.utils.hide
import com.stellarworker.movies.utils.show

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            val top250MovieInt: Top250MovieInt = bundle.get(MOVIE_DETAILS) as Top250MovieInt
            with(binding) {
                fragmentDetailsLoadingProgress.hide()
                fragmentDetailsScroll.show()
            }
            showDetails(top250MovieInt)
        }
    }

    private fun showDetails(top250MovieInt: Top250MovieInt) {
        with(binding) {
            fragmentDetailsTitle.text = top250MovieInt.title
            fragmentDetailsPoster.load(top250MovieInt.image)
            fragmentDetailsFullTitle.text =
                getString(R.string.fragment_details_full_title, top250MovieInt.fullTitle)
            fragmentDetailsRank.text =
                getString(R.string.fragment_details_rank, top250MovieInt.rank)
            fragmentDetailsYear.text =
                getString(R.string.fragment_details_year, top250MovieInt.year)
            fragmentDetailsCrew.text =
                getString(R.string.fragment_details_crew, top250MovieInt.crew)
            fragmentDetailsImdbRating.text =
                getString(R.string.fragment_details_imdb_rating, top250MovieInt.imDbRating)
            fragmentDetailsImdbRatingCount.text =
                getString(
                    R.string.fragment_details_imdb_rating_count,
                    top250MovieInt.imDbRatingCount
                )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "DETAILS_FRAGMENT"
        private const val MOVIE_DETAILS = "MOVIE_DETAILS"

        @JvmStatic
        fun newInstance(top250MovieInt: Top250MovieInt): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(MOVIE_DETAILS, top250MovieInt)
            detailsFragment.arguments = args
            return detailsFragment
        }
    }
}