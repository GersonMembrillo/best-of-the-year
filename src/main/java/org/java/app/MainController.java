package org.java.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    private List<Movie> getBestMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Il padrino"));
        movies.add(new Movie(2, "Il padrino - Parte II"));
        movies.add(new Movie(3, "Shining"));
        return movies;
    }

    private List<Song> getBestSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Imagine"));
        songs.add(new Song(2, "Bohemian Rhapsody"));
        songs.add(new Song(3, "Hey Jude"));
        return songs;
    }
    
    @GetMapping("/movies/{id}")
	public String getMovieID(@PathVariable int id, Model model) {
		
		Movie movie = getBestMovies().stream()
							.filter(m -> m.getId() == id)
						.findFirst().get();
		String movieTitle = movie.getTitle();
		
		model.addAttribute("movieTitle", movieTitle);
		
		return "movie";
	}
    @GetMapping("/songs/{id}")
    public String getSongID(@PathVariable int id, Model model) {

        Song song = getBestSongs().stream()
            .filter(s -> s.getId() == id)
            .findFirst().get();
        String songTitle = song.getTitle();

        model.addAttribute("songTitle", songTitle);

        return "song";
    }

    @GetMapping("/")
    public String test(Model model) {
        final String name = "Gerson Membrillo";

        model.addAttribute("name", name);

        return "name";
    }

    @GetMapping("/movies")
//    public String movies(Model model) {
//        String moviesList = getBestMovies().stream().map(m -> m.getTitle()).collect(Collectors.joining(", "));
//        model.addAttribute("movies", moviesList);
//        return "movies";
//    }
    
    public List<Movie> movies(Model model) {
        List<Movie> movies = getBestMovies();
        model.addAttribute("movies", movies);
        return movies;
    }

    @GetMapping("/songs")
//    public String songs(Model model) {
//        String songsList = getBestSongs().stream().map(s -> s.getTitle()).collect(Collectors.joining(", "));
//        model.addAttribute("songs", songsList);
//        return "songs";
//    }
    
    public List<Song> songs(Model model) {
        List<Song> songs = getBestSongs();
        model.addAttribute("songs", songs);
        return songs;
    }
    
    

}
