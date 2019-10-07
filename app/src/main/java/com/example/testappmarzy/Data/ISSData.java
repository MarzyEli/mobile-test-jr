package com.example.testappmarzy.Data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase para realizar el consumo del api
 * @author Marzy Elizalde
 */
public class ISSData {
    public static ISSDataService dataService;


    /**
     * Método que realiza la petición de los datos
     * @return Nada
     */
    public static ISSDataService getDataService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.open-notify.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return dataService = retrofit.create(ISSDataService.class);
    }
}
