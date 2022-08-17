package pedroluiz.projeto.soccernews.presentation.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times
import pedroluiz.projeto.soccernews.data.model.entity.News
import pedroluiz.projeto.soccernews.domain.useCase.NewsInteractorImp
import pedroluiz.projeto.soccernews.presentation.ViewState

class NewsViewModelTest() {

    private val testDispatcher =  UnconfinedTestDispatcher()
    private val newsInteractorImp = mockk<NewsInteractorImp>()
    private val viewModel = NewsViewModel(newsInteractorImp)

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
    fun `when NewsInteractorImp getAllNews return two news, viewState needs to stay the same with two news `() {
        //GIVE
        val mockedList = mockkedNewsList()

        every { newsInteractorImp.getAllNews()} returns flow {emit(mockedList)}

        //WHEN
        viewModel.getAllNews()

        //THEN
        assertEquals(ViewState.SetNewsListLoaded(mockedList),viewModel.viewState.value)
    }

    @Test
    fun `when NewsInteractorImp filterNews return two news, viewState needs to stay the same with two news `() {
        //GIVE
        val mockedList = mockkedNewsList()

        every { newsInteractorImp.filterNews("Jogadora")} returns flow {emit(mockedList)}

        //WHEN
        viewModel.filterList("Jogadora")

        //THEN
        assertEquals(ViewState.SetNewsListLoaded(mockedList),viewModel.viewState.value)
    }

    @Test
    fun `on call function view model getAllNews should call newsInteractorImp once`() {
        //GIVE
        every { newsInteractorImp.getAllNews() } returns flow { emit(listOf()) }

        //WHEN
        viewModel.getAllNews()

        //THEN
        coVerify(exactly = 1) { newsInteractorImp.getAllNews() }
    }

    @Test
    fun `on call function view model save should call newsInteractorImp save once`() {
        //GIVE
        val news = News(1,"Marta","Jogadora","","",false)

        coEvery { newsInteractorImp.saveNews(news) } returns

        //WHEN
        viewModel.saveNews(news)

        //THEN
        coVerify(exactly = 1) { newsInteractorImp.saveNews(news) }
    }

    private fun mockkedNewsList() : List<News>{
        return   listOf(
            News(1,"Marta","Jogadora","","",false),
            News(2,"Formiga","Jogadora","","",false)
        )
    }
}