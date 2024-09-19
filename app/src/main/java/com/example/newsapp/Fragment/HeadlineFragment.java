package com.example.newsapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.newsapp.R;

import java.util.HashMap;

public class HeadlineFragment extends Fragment {

    private OnHeadlineSelectedListener callback;

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        void onHeadlineSelected(String headline, String content);
    }

    private HashMap<String, String> newsData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline, container, false);

        ListView listView = view.findViewById(R.id.headline_list);

        // Sample headlines and their corresponding content
        newsData = new HashMap<>();
        newsData.put("BLACKPINK is set to make a comeback in your area!!", "YG Entertainment Producer and Founder Yang Hyun-Suk announced on Sunday, July 21, 2024, that the K-pop girl group will take center stage with a world tour and new music next year.\n" +
                "\n" +
                "The highly anticipated K-pop group crossed the globe with their Born Pink World Tour from October 2022 to September 2023, setting a record for the largest concert attendance in K-pop girl group history.\n" +
                "\n" +
                "In addition to their upcoming comeback, Blackpink will also release their 8th-anniversary film with limited global screenings on Wednesday, July 31, 2024.");
        newsData.put("The ultimate K-Pop merch drop ‘Reveluvs’ have been waiting for", "Following its successful collaboration with SM Entertainment on EXO, NCT, and SMTOWN, 0917 Lifestyle is back at it again. Get ready as the 0917 x Red Velvet collection makes its grand debut this August!\n" +
                "\n" +
                "With the unwavering commitment of Globe’s urban lifestyle brand to connecting people to their passions, this latest partnership with SM Entertainment, the powerhouse behind many of the K-Pop industry’s biggest groups, is set to be the talk of the local Hallyu community.\n" +
                "\n" +
                "Featuring designs inspired by Red Velvet's sizzling comeback album, \"The ReVe Festival 2022- Birthday,\" fans can expect to be slayed with merchandise that screams K-pop chic.");
        newsData.put("Jennie's label formally denies dating rumors with BamBam",
                "A representative of Jennie's label OA Entertainment has stepped up to formally deny dating rumors between her and GOT7 member/solo artist BamBam. \n" +
                        "\n" +
                        "Previously on September 17, Jennie and BamBam were spotted dining with acquaintances at a restaurant in LA. It's believed that both artists were accompanied by their managers.");
        newsData.put("Meaning of Scene Two - Roger Rabbit by Sleeping With Sirens", "\"Scene Two - Roger Rabbit\" by Sleeping With Sirens is a song about the struggles of fitting in and finding acceptance in a world where people are quick to judge and forget. The lyrics touch on the idea that in life, we have friends and foes who are always looking to make us feel small or marginalized. At times, we may even contribute to the toxic culture by criticizing and not supporting those around us.\n" +
                "\n" +
                "The chorus highlights the importance of being open and honest about one's feelings and emotions and not just putting up a façade for others. The singer reminds us that if we cannot show our vulnerability and express our true selves, nobody will love or guide us through life.");

        String[] headlines = newsData.keySet().toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, headlines);
        listView.setAdapter(adapter);

        // Handle the click on each headline
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedHeadline = headlines[position];
                String selectedContent = newsData.get(selectedHeadline);
                callback.onHeadlineSelected(selectedHeadline, selectedContent);
            }
        });

        return view;
    }
}
