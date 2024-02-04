package com.codeshow.tddwebflux.controller;


import com.codeshow.tddwebflux.model.Stack;
import com.codeshow.tddwebflux.service.StackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stacks")
public class StackController {

    private final StackService stackService;

    public StackController(StackService stackService) {
        this.stackService = stackService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Stack> criarStack(@RequestBody Stack stack){
        return stackService.salvarStack(stack);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Stack> buscarPeloid(@PathVariable String id){
        return stackService.buscarPeloId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> excluirPeloId(@PathVariable String id){
        return stackService.excluirPeloId(id);
    }
}
