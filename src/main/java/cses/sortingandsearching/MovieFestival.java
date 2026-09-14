package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MovieFestival {

    public static final int MOVIE_END_INDEX = 1;
    public static final int MOVIE_START_INDEX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long maxAmountOfMoviesWatchable = 0L;
        int amountOfMovies = Integer.parseInt(br.readLine());

        long[][] movies = new long[amountOfMovies][2];

        for (int i = 0; i < amountOfMovies; i++) {
            String[] movie = br.readLine().split(" ");
            movies[i][MOVIE_START_INDEX] = Long.parseLong(movie[MOVIE_START_INDEX]);
            movies[i][MOVIE_END_INDEX] = Long.parseLong(movie[MOVIE_END_INDEX]);
        }

        Arrays.sort(movies, (movie1, movie2) -> Math.toIntExact(movie1[MOVIE_END_INDEX] - movie2[MOVIE_END_INDEX]));

        long lastMovieEnd = 0;
        for (long[] movie : movies) {
            long movieStart = movie[MOVIE_START_INDEX];
            long movieEnd = movie[MOVIE_END_INDEX];

            if (movieStart >= lastMovieEnd) {
                lastMovieEnd = movieEnd;
                maxAmountOfMoviesWatchable++;
            }
        }

        System.out.println(maxAmountOfMoviesWatchable);
    }
}