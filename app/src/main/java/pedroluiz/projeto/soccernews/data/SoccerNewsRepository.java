package pedroluiz.projeto.soccernews.data;

import android.content.Context;

import androidx.room.Room;

import pedroluiz.projeto.soccernews.data.local.SoccerNewsDb;
import pedroluiz.projeto.soccernews.data.remote.SoccerNewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    //region Constantes
    private static final String REMOTE_API_URL ="https://luizsfl.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME ="soccer-news";
    //endregion

    //region Constantes encapsula o acesso a nossa api(retrofit e BD
    private SoccerNewsApi remoteApi;
    private SoccerNewsDb LocalDb;

    public SoccerNewsApi getRemoteApi(){return remoteApi;}
    public SoccerNewsDb getLocalDb(){return LocalDb;}
    //endregion

    public SoccerNewsRepository(Context context){
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        LocalDb = Room.databaseBuilder(context ,SoccerNewsDb.class,LOCAL_DB_NAME)
        .build();

    }

}
