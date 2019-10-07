package com.example.testappmarzy.Data;

import com.example.testappmarzy.Model.ISSLocation;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface que sirve para gestionar las
 * peticiones de la Api
 */
public interface ISSDataService {
    /**
     * MMétodo con anotación GET indicándole
     * el recurso a consumir del Api
     * @return Llamada que devulve el pojo de ISSLocation
     */
    @GET("/iss-now.json")
    Call<ISSLocation> getData();
}
