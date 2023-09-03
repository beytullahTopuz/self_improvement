package com.t4zb.self_improvement.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.t4zb.self_improvement.R;
import com.t4zb.self_improvement.app.AppConfig;
import com.t4zb.self_improvement.databinding.FragmentHomeBinding;
import com.t4zb.self_improvement.model.DailyStatusValue;
import com.t4zb.self_improvement.ui.adaptor.DailyCheckAdapter;
import com.t4zb.self_improvement.ui.lıstener.ItemClickedListener;
import com.t4zb.self_improvement.ui.view_model.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private Context mContext;
    private FragmentHomeBinding mBinding;
    private HomeFragmentViewModel homeViewModel;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = requireActivity();
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeFragmentViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.bind(view);
        super.onViewCreated(mBinding.getRoot(), savedInstanceState);

        initUI();
    }

    private void initUI(){
        DailyCheckAdapter dailyCheckAdapter = new DailyCheckAdapter(setEmotionList(),emotionItemClickedListener);
        mBinding.dailyCheckRv.setLayoutManager(new GridLayoutManager(mContext,2));
        mBinding.dailyCheckRv.setAdapter(dailyCheckAdapter);
    }

    private final ItemClickedListener emotionItemClickedListener = (position, value) -> {

        DailyStatusValue dailyStatusValue = new DailyStatusValue();
        dailyStatusValue.setStatusIndex(position);
        dailyStatusValue.setStatusValue(value);
        dailyStatusValue.setDate(new Date());
        // buraya degıl room  ye yaz ve roomdakı coun 5 ve 5 ın katı oldugu zaman fırabase e yaz performans ıcın
   //     AppConfig.GMSDatabase().addDailyEmotionStatus(dailyStatusValue, homeViewModel);

    };

    private List<DailyCheckAdapter.EmotionItem> setEmotionList(){
        List<DailyCheckAdapter.EmotionItem> emotionArrayList = new ArrayList<>();
        String[] emotionList = getResources().getStringArray(R.array.emotion_list);

        for (String s : emotionList) {
            DailyCheckAdapter.EmotionItem currentItem = new DailyCheckAdapter.EmotionItem();
            currentItem.setSelected(false);
            currentItem.setEmotionValue(s);
            emotionArrayList.add(currentItem);
        }
        return emotionArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}