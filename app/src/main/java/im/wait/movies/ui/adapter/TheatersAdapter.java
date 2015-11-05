package im.wait.movies.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import im.wait.movies.R;
import im.wait.movies.api.ApiImpl;
import im.wait.movies.bean.Subject;
import im.wait.movies.http.network.SimpleJoyResponce;
import im.wait.movies.model.BaseModel;
import im.wait.movies.ui.activity.MovieDetailActivity;
import im.wait.movies.utils.CommonUtil;

/**
 * 作者：thinkloki on 15/11/4 17:42
 * 邮箱：thinkloki@gmail.com
 * github:https://github.com/thinkloki
 */
public class TheatersAdapter extends BaseAdapter {
    private List<Subject> subjects;
    private int SCREENWIDTH, ITEM_WIDTH;
    private float ITEM_HEIGHT;
    private Context mContext;

    public TheatersAdapter(List<Subject> subjects,Context mContext) {
        this.subjects = subjects;
        this.mContext=mContext;
        SCREENWIDTH = CommonUtil.getWindowManager(mContext).getWidth();
        ITEM_WIDTH = (SCREENWIDTH - 64) / 3;
        ITEM_HEIGHT = CommonUtil.getHeight(ITEM_WIDTH, 300, 428);
    }

    @Override
    public int getCount() {
        return subjects == null ? 0 : subjects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_theaters, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Subject subject = subjects.get(i);
        holder.ivCover.setLayoutParams(new LinearLayout.LayoutParams(ITEM_WIDTH, (int) ITEM_HEIGHT));
        holder.ivCover.setTag(subject.images.large);
        if (holder.ivCover.getTag() == subject.images.large) {
            ImageLoader.getInstance().displayImage((String) holder.ivCover.getTag(), holder.ivCover);
            int stars = subject.rating.stars / 10;
            holder.ivStartOne.setSelected(false);
            holder.ivStartTwo.setSelected(false);
            holder.ivStartThree.setSelected(false);
            holder.ivStartFour.setSelected(false);
            holder.ivStartFive.setSelected(false);
            switch (stars) {
                case 0:

                case 1:
                    holder.ivStartOne.setSelected(true);
                    break;
                case 2:
                    holder.ivStartOne.setSelected(true);
                    holder.ivStartTwo.setSelected(true);
                    break;
                case 3:
                    holder.ivStartOne.setSelected(true);
                    holder.ivStartTwo.setSelected(true);
                    holder.ivStartThree.setSelected(true);
                    break;
                case 4:
                    holder.ivStartOne.setSelected(true);
                    holder.ivStartTwo.setSelected(true);
                    holder.ivStartThree.setSelected(true);
                    holder.ivStartFour.setSelected(true);
                    break;
                case 5:
                    holder.ivStartOne.setSelected(true);
                    holder.ivStartTwo.setSelected(true);
                    holder.ivStartThree.setSelected(true);
                    holder.ivStartFour.setSelected(true);
                    holder.ivStartFive.setSelected(true);
                    break;
            }
        }
        holder.ivName.setText(subject.title);

        holder.tvStar.setText(subject.rating.average);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MovieDetailActivity.class);
                mContext.startActivity(intent);
                ApiImpl.getInstance().getPhotos(subject.id,0,20,new SimpleJoyResponce<BaseModel>(){
                    @Override
                    public void onSuccess(BaseModel response) {
                    }
                });
            }
        });


        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_cover)
        ImageView ivCover;
        @InjectView(R.id.iv_start_one)
        ImageView ivStartOne;
        @InjectView(R.id.iv_start_two)
        ImageView ivStartTwo;
        @InjectView(R.id.iv_start_three)
        ImageView ivStartThree;
        @InjectView(R.id.iv_start_four)
        ImageView ivStartFour;
        @InjectView(R.id.iv_start_five)
        ImageView ivStartFive;
        @InjectView(R.id.ll_rating)
        LinearLayout llRating;
        @InjectView(R.id.iv_name)
        TextView ivName;
        @InjectView(R.id.tv_star)
        TextView tvStar;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}

