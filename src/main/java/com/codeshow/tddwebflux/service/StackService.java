package com.codeshow.tddwebflux.service;


import com.codeshow.tddwebflux.model.Stack;
import com.codeshow.tddwebflux.repository.StackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class StackService {

    @Autowired
    private final StackRepository stackRepository;

    public StackService(StackRepository stackRepository) {
        this.stackRepository = stackRepository;
    }

    public Mono<Stack> salvarStack(Stack stack){
        return stackRepository.save(stack);
    }

    public Mono<Stack> buscarPeloId(String id){
        return stackRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND)
                ));
    }

    public Mono<Void> excluirPeloId(String id){
        return stackRepository.findById(id)
                .flatMap(stackRepository::delete);
    }
}
