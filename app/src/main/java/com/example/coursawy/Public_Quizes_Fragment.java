package com.example.coursawy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coursawy.adapter.MyAdapter;
import com.example.coursawy.model.Item_Tabs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Public_Quizes_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Public_Quizes_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    RecyclerView simpleList;
    private String mParam1;
    private String mParam2;
    ArrayList<Item_Tabs> list_items;
    MyAdapter list_adapter;

    public Public_Quizes_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Public_Quizes_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Public_Quizes_Fragment newInstance(String param1, String param2) {
        Public_Quizes_Fragment fragment = new Public_Quizes_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quisez_2, container, false);
        simpleList = (RecyclerView) view.findViewById(R.id.recyclerView);
        fillOrders();
        return view;
    }

    private void fillOrders() {
        list_items=new ArrayList<>();
//        for(int i=0;i<6;i++) {
//           list_items.add(new Item_Tabs (  R.drawable.group2 ,"2x","Old Town Burger","123 EGP"));
//        }
        list_items.add(new Item_Tabs("5 questions","MATH II","dr.fahd","new"));
        list_items.add(new Item_Tabs("15 questions","C++","dr.Kareem","pass"));
        list_items.add(new Item_Tabs("10 questions","History","dr.Ahmed Kamal","fail"));
        list_items.add(new Item_Tabs("20 questions","Python","dr.mohamed gamal","new"));
        list_items.add(new Item_Tabs("25 questions","MATH II","dr.mohamed gamal","new"));
        list_items.add(new Item_Tabs("5 questions","MATH II","dr.mohamed gamal","new"));

        LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false);
        list_adapter=new MyAdapter (getActivity(),list_items);

        simpleList.setItemAnimator(new DefaultItemAnimator());
        simpleList.setAdapter(list_adapter);
        simpleList.setLayoutManager(layoutManager);
        Log.e("", "fill:54321111111 ");
    }
}