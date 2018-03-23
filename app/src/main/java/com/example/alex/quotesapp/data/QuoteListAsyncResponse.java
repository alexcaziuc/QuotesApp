package com.example.alex.quotesapp.data;

import com.example.alex.quotesapp.model.Quote;

import java.util.ArrayList;

/**
 * Created by Alex on 18-Mar-18.
 */

public interface QuoteListAsyncResponse {

    void processFinished(ArrayList<Quote> quotes);

}
