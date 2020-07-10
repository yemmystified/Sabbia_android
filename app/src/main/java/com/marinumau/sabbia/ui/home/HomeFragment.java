package com.marinumau.sabbia.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marinumau.sabbia.R;
import com.marinumau.sabbia.logic.BeachManager.Beach;
import com.marinumau.sabbia.logic.BeachManager.BeachAdapter;
import com.marinumau.sabbia.logic.BeachManager.BeachFactory;
import com.marinumau.sabbia.utils.AlertManager;

import java.util.Objects;

import soup.neumorphism.NeumorphButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView beachHistory;

    /**
     *
     * @param inflater the inflater
     * @param container the container
     * @param savedInstanceState the savedInstances state
     * @return the rootView
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        populateHistory(root);
        initCurrentActivityCard(root);

        return root;
    }

    /**
     *
     * @param root the root view
     */
    private void populateHistory(View root){
        BeachFactory beachFactory = BeachFactory.getInstance();
        RecyclerView recyclerView = root.findViewById(R.id.history_grid);

        recyclerView.setHasFixedSize(true);

        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        BeachAdapter adapter = new BeachAdapter(getActivity(), Objects.requireNonNull(getActivity()).getApplicationContext(), beachFactory.getBeachList(), 3);
        recyclerView.setAdapter(adapter);
    }

    /**
     *
     * @param root the root view
     */
    private void initCurrentActivityCard(View root){
        NeumorphButton stopPermanenceBtn = root.findViewById(R.id.btn_stop_registration);
        stopPermanenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertManager.stopBeachPermanenceDialog(Objects.requireNonNull(getActivity()), getContext(), new Beach(0, "Nome", "Cuglieri", "OR"));
            }
        });
    }
}