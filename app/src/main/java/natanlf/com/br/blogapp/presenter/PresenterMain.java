package natanlf.com.br.blogapp.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import natanlf.com.br.blogapp.view.PostsActivity;
import natanlf.com.br.blogapp.model.Model;

public class PresenterMain {
    private Model model;
    private PostsActivity activity;
    private ArrayList<Post> posts = new ArrayList<>();


    public PresenterMain(){
        model = new Model( this );
    }

    public void setActivity(PostsActivity activity){
        this.activity = activity;
    }

    public Activity getContext() {
        return activity;
    }

    public void retrievePosts( Bundle instanceState ) {

        if( instanceState != null ){ //CASO DESTRUA A ACTIVITY PEGA PELO PARCE, NÃO PEGA DO BACKEND
            posts.addAll( (ArrayList) instanceState.getParcelableArrayList( Post.POSTS_KEY ) );
        }
        else{
            model.retrievePosts(); //CASO NÃO TENHA POST PEGA NO BACKEND
        }
    }

    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        activity.showProgressBar( visibilidade );
    }

    public void updateListaRecycler(Object object) {
        List<Post> postsCarregados = (List<Post>) object;
        posts.clear();
        posts.addAll( postsCarregados );
        activity.updateListaRecycler();
        showProgressBar( !(posts.size() > 0) );
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}

