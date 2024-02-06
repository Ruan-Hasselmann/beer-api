package net.atos.beerapi.service;

import net.atos.beerapi.model.Beer;
import net.atos.beerapi.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService implements IBeerService {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<Beer> getAllBeers() {
        return (List<Beer>) this.beerRepository.findAll();
    }

    @Override
    public Beer getBeerById(Long id) {
        Optional<Beer> optionalBeer = this.beerRepository.findById(id);
        return optionalBeer.orElse(null);
    }

    @Override
    public Beer saveBeer(Beer beer) {
        return this.beerRepository.save(beer);
    }

    @Override
    public void deleteBeer(Long id) {
        this.beerRepository.deleteById(id);
    }
}
