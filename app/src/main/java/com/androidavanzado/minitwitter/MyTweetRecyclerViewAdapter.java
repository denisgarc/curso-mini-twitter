package com.androidavanzado.minitwitter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidavanzado.minitwitter.common.Constantes;
import com.androidavanzado.minitwitter.common.SharedPreferenceManager;
import com.androidavanzado.minitwitter.databinding.FragmentTweetListBinding;
import com.androidavanzado.minitwitter.retrofit.response.Like;
import com.androidavanzado.minitwitter.retrofit.response.Tweet;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Tweet}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTweetRecyclerViewAdapter extends RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder> {

    private final List<Tweet> mValues;
    private Context context;
    private String userName;

    public MyTweetRecyclerViewAdapter(Context ctx, List<Tweet> items) {
        mValues = items;
        context = ctx;
        userName = SharedPreferenceManager.getStringValue(Constantes.PREF_USER_NAME);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // return new ViewHolder(FragmentTweetListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewUserName.setText(holder.mItem.getUser().getUsername());
        holder.textViewMessage.setText(holder.mItem.getMensaje());
        holder.textViewLikesCount.setText(String.valueOf(holder.mItem.getLikes().size()));

        String photo = holder.mItem.getUser().getPhotoUrl();
        if(!photo.equals("")){
            Glide.with(context)
                    .load("https://www.minitwitter.com/apiv1/uploads/photos/" + photo)
                    .into(holder.imageViewAvatar);
        }

        for(Like like: holder.mItem.getLikes()){
            if(like.getUsername().equals(userName)){
                Glide.with(context)
                        .load(R.drawable.ic_like_pink)
                        .into(holder.imageViewLike);
                holder.textViewLikesCount.setTextColor(context.getResources().getColor(R.color.pink));
                holder.textViewLikesCount.setTypeface(null, Typeface.BOLD);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mview;
        public final ImageView imageViewAvatar, imageViewLike;
        public final TextView textViewUserName, textViewMessage, textViewLikesCount;
        public Tweet mItem;

        public ViewHolder(View view) {
            super(view);
            mview = view;
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar);
            imageViewLike = view.findViewById(R.id.imageViewLike);

            textViewUserName = view.findViewById(R.id.textViewUserName);
            textViewMessage = view.findViewById(R.id.textViewMessage);
            textViewLikesCount = view.findViewById(R.id.textViewLikes);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewUserName.getText() + "'";
        }
    }
}