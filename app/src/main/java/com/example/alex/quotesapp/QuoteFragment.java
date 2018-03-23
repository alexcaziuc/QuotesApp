package com.example.alex.quotesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alex.quotesapp.model.Quote;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    private Button shareBtn;
    Intent sharedIntent;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);

        TextView quoteText = quoteView.findViewById(R.id.quoteText);
        TextView authorText = quoteView.findViewById(R.id.quoteAuthor);
        CardView cardView = quoteView.findViewById(R.id.quotesCardView);

        final String quote = getArguments().getString("quote");
        final String author = getArguments().getString("author");

        int colors[] = new int[] {R.color.md_amber_900, R.color.md_green_900, R.color.md_blue_900,
        R.color.md_blue_grey_900, R.color.md_deep_purple_400, R.color.md_deep_orange_A400,
        R.color.md_blue_700, R.color.md_light_green_600, R.color.md_red_900, R.color.md_brown_800,
        R.color.md_teal_900, R.color.md_purple_900, R.color.md_pink_900, R.color.md_grey_700
        };

        quoteText.setText(quote);
        authorText.setText(" - " + author);
        cardView.setBackgroundResource(getRandomQuoteColor(colors));

        shareBtn = quoteView.findViewById(R.id.share_btn);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedIntent = new Intent(Intent.ACTION_SEND);
                sharedIntent.setData(Uri.parse("mailto:"));
                sharedIntent.setType("text/plain");
                sharedIntent.putExtra(Intent.EXTRA_SUBJECT, "Quote of the day");
                sharedIntent.putExtra(Intent.EXTRA_TEXT, "\"" + quote + "\"" + "\n" + "by " + author);

                startActivity(Intent.createChooser(sharedIntent, "Share with your friends via"));


            }
        });


        return quoteView;
    }

    public static final QuoteFragment newInstance(String quote, String author) {

        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);

        return fragment;

    }

    int getRandomQuoteColor(int[] colorArray) {

        int color;
        int quotesArrayLenght = colorArray.length;

        int randomNum = ThreadLocalRandom.current().nextInt(quotesArrayLenght);
        color = colorArray[randomNum];

        return color;

    }

}
