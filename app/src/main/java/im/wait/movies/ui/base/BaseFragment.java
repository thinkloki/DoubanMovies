package im.wait.movies.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import im.wait.movies.api.ApiImpl;
import im.wait.movies.http.network.NetWorkHelper;

/**
 * Created by root on 15-3-31.
 */
public abstract class BaseFragment extends Fragment {
    protected final int DELAY_TIME=2000;

    protected FragmentActivity mContext;
    protected View rootView;
    protected final String TAG = BaseFragment.class.getSimpleName();
    protected NetWorkHelper netWorkHelper;
    protected ApiImpl mApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentView(), container, false);
        ButterKnife.inject(this, rootView);
        netWorkHelper = NetWorkHelper.getInstance();
        mApi = ApiImpl.getInstance();
        initView();
        initEvents();
        initDatas();
        return rootView;

    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void initEvents();

    protected abstract void initDatas();


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = this.getActivity();
    }

    @Override
    public void onDetach() {
        mContext = null;
        super.onDetach();
    }

    @Override
    public final void onDestroyView() {
        onFragmentDestroyView();
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    protected void onFragmentDestroyView() {

    }

}