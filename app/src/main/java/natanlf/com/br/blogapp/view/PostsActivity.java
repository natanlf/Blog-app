package natanlf.com.br.blogapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import natanlf.com.br.blogapp.R;
import natanlf.com.br.blogapp.presenter.Post;
import natanlf.com.br.blogapp.presenter.PresenterMain;
import natanlf.com.br.blogapp.view.PostsAdapter;

public class PostsActivity extends AppCompatActivity {
    private PresenterMain presenter;
    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new PresenterMain();
        presenter.setActivity( this );
        initViews();

        presenter.retrievePosts( savedInstanceState );
    }

    private void initViews() {
        super.onStart();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_posts);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration( divider );

        adapter = new PostsAdapter( this, presenter.getPosts() );
        recyclerView.setAdapter( adapter );
    }

    public void updateListaRecycler(){
        adapter.notifyDataSetChanged();
    }

    public void showProgressBar( int visibilidade ){
        findViewById(R.id.pb_loading).setVisibility( visibilidade );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) { //ANTES DE DESTRUIR A ACTIVITY SALVA OS DADOS COM PARCE
        outState.putParcelableArrayList( Post.POSTS_KEY, presenter.getPosts() );
        super.onSaveInstanceState(outState);
    }
}

