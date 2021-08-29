package com.group3.g3whatsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://g3-whatsapp-default-rtdb.firebaseio.com/";

    @GET("chats")
    Call<List<Messages>> getMessages();

}
