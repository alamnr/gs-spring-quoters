package com.example.quoters;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class QuoteController {

    private final static Quote NONE = new Quote("None");
    private final static Random RANDOMIZER = new Random();

    private final QuoteRepository repository;

    public QuoteController(QuoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api")
    public ResponseEntity<List<QuoteResource>> getAll() {

        return new ResponseEntity<List<QuoteResource>>( repository.findAll().stream()
                .map(quote -> new QuoteResource(quote, "success"))
                .collect(Collectors.toList()), HttpStatus.OK );
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<QuoteResource> getOne(@PathVariable Long id) {

        return new  ResponseEntity<QuoteResource>(repository.findById(id)
                .map(quote -> new QuoteResource(quote, "success"))
                .orElse(new QuoteResource(NONE, "Quote " + id + " does not exist")), HttpStatus.OK
        );
    }

    @GetMapping("/api/random")
    public ResponseEntity<QuoteResource> getRandomOne() {
        return new ResponseEntity<QuoteResource>(getRandom(nextLong(1, repository.count() + 1)), HttpStatus.OK
        );
    }
    private QuoteResource getRandom(Long id){
        return repository.findById(id)
                .map(quote -> new QuoteResource(quote, "success"))
                .orElse(new QuoteResource(NONE, "Quote " + id + " does not exist"));
    }

    private long nextLong(long lowerRange, long upperRange) {
        return (long) (RANDOMIZER.nextDouble() * (upperRange - lowerRange)) + lowerRange;
    }
}
