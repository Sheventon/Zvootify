package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Singer;
import ru.itis.repositories.SingersRepository;

@Service
public class SingersServiceImpl implements SingersService {

    @Autowired
    private SingersRepository singersRepository;

    @Override
    public Singer getById(Long id) {
        //Optional<Singer> optionalSinger = singersRepository.findById(id);
        //return optionalSinger.orElse(null);
        return singersRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("singer not found"));
    }
}
