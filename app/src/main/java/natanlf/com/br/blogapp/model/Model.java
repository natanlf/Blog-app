package natanlf.com.br.blogapp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import natanlf.com.br.blogapp.presenter.PresenterMain;

public class Model {
    private AsyncHttpClient asyncHttpClient;
    private PresenterMain presenter;

    public Model(PresenterMain presenter ){
        asyncHttpClient = new AsyncHttpClient();
        this.presenter = presenter;
    }

    //SE NÃO TIVER POSTS ENTÃO CHAMA OS POSTS
    public void retrievePosts() {
        RequestParams requestParams = new RequestParams();
        requestParams.put( JsonHttpRequest.METODO_KEY, JsonHttpRequest.METODO_POSTS );

        asyncHttpClient.post( presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }
}