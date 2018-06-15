package com.example.dan.moviesearchapp.APICalls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FandangoAPI {

    @GET("/?apikey=7nwb8aryjamb997ewwyr8f5w") // api key
    public Call<FandangoSearchResponse> theatersByPostalCodeSearch(@Query("postalcode") String zipCode);

    // This operation returns an ordered list of theaters and their performances that are playing a specific movie near the postal code specified
    public Call<FandangoSearchResponse> performancesByMoviePostalCodeSearch(@Query("movieid") int fandangoMovieID,
                                                                        @Query("postalcode") String zipCode,
                                                                        @Query("date") String date);


    // This operation returns a list of performances for a list of theaters (theaters seperated by commas, date =  MM-DD-YYYY)
    public Call<FandangoSearchResponse> performancesByTheaterList(@Query("theaterids") String theaterIDs,
                                                                  @Query("date") String date);


}
