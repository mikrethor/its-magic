package com.xavierbouclet.nativeapi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final ActorRepository actorRepository;

    public MovieService(MovieRepository repository, ActorRepository actorRepository) {
        this.repository = repository;
        this.actorRepository = actorRepository;
    }

    public List<Movie> list() {
        return repository.findAll();
    }

//    public list(title: String): List<Movie> {
//        return repository.findByTitle(title)
//    }

    public Movie add(Movie movie) {
        return repository.save(movie);
    }

    public Movie modify(Movie movie) {
        return repository.save(movie);
    }

    public UUID delete(UUID id) {
        repository.deleteById(id);
        return id;
    }

    public Movie addActorToMovie(UUID id, UUID idActor) {
        var movie = repository.findById(id).get();
        var actor = actorRepository.findById(idActor).get();
        movie.getActors().add(actor);
        actor.getMovies().add(movie);
        return repository.save(movie);
    }

    public Movie get(UUID id) {
        return repository.findById(id).get();
    }

    public Movie removeActorFromMovie(UUID id, UUID idActor) {
        var movie = repository.findById(id).get();
        var actor = actorRepository.findById(idActor).get();
        movie.getActors().remove(actor);
        actor.getMovies().remove(movie);
        return repository.save(movie);
    }

}
