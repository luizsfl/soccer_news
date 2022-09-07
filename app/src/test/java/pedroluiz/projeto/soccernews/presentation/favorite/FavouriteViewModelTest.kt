package pedroluiz.projeto.soccernews.presentation.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractor
import pedroluiz.projeto.soccernews.presentation.favourite.FavouriteViewModel

class FavouriteViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val newsInteractor = mockk<NewsInteractor>()
    private val viewModel = FavouriteViewModel(newsInteractor)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `on call function view model loadFavoriteNews should call newsInteractor once`() {
        //GIVE
        every { newsInteractor.getAllNewsFavourite() } returns flow { emit(listOf()) }

        //WHEN
        viewModel.loadFavouriteNews()

        //THEN
        coVerify(exactly = 1) { newsInteractor.getAllNewsFavourite() }
    }

    @Test
    fun `on call function view model save should call newsInteractor save once`() {
        //GIVE
        val news = News(1, "Marta", "Jogadora", "", "", false)

        coEvery { newsInteractor.saveNews(news) } returns Unit

        //WHEN
        viewModel.saveNews(news)

        //THEN
        coVerify(exactly = 1) { newsInteractor.saveNews(news) }
    }
}