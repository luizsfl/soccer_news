package pedroluiz.projeto.soccernews.domain.useCase
import androidx.lifecycle.MutableLiveData
import pedroluiz.projeto.soccernews.domain.model.News
class FilterNewsUseCase {
    operator fun invoke(newsApi: MutableLiveData<List<News>>,text:String) : MutableLiveData<List<News>>{
        var newsSearch: MutableLiveData<List<News>> = MutableLiveData<List<News>>()

        if (newsApi?.value != null) {
            for (indice in newsApi.value!!?.indices) {
                if (newsApi.value!!.get(indice).description.uppercase().contains(text.uppercase())
                    || newsApi.value!!.get(indice).title.uppercase().contains(text.uppercase())) {

                    newsSearch.value = newsSearch.value?.plus(newsApi.value!!.get(indice)) ?:
                    listOf(newsApi.value!!.get(indice))
                }
            }
        }
        return newsSearch
    }
}