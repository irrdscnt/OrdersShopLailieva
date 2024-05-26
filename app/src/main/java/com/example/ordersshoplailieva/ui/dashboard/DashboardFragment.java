package com.example.ordersshoplailieva.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ordersshoplailieva.databinding.FragmentDashboardBinding;
import com.example.ordersshoplailieva.models.Order;
import com.example.ordersshoplailieva.models.User;
import com.example.ordersshoplailieva.remote_data.RetrofitClient;
import com.example.ordersshoplailieva.ui.home.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    UserAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Call<List<User>> apiCall= RetrofitClient.getInstance().getApi().getAllUsers();
        apiCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    ArrayList<User> list=(ArrayList<User>) response.body();
                    adapter=new UserAdapter(requireActivity(),list);
                    binding.rvMainListAllUsers.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable throwable) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}