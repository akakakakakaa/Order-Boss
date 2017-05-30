package b05studio.com.order_boss.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.view.activity.MyRecordActivity;

/**
 * Created by young on 2017-05-29.
 */

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        View goToProfileView = rootView.findViewById(R.id.profileMyReportButton);
        goToProfileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyRecordActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }




}
