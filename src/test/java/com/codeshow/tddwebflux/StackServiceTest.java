package com.codeshow.tddwebflux;


import com.codeshow.tddwebflux.model.Stack;
import com.codeshow.tddwebflux.repository.StackRepository;
import com.codeshow.tddwebflux.service.StackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class StackServiceTest {


    @InjectMocks
    private StackService stackService;

    @Mock
    private StackRepository stackRepository;


    @BeforeEach
    public void Setup(){
        BDDMockito.when(stackRepository.save(StackMock.stackMock()))
                .thenReturn(Mono.just(StackMock.stackMock()));

        BDDMockito.when(stackRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(StackMock.stackMock()));

        BDDMockito.when(stackRepository.delete(ArgumentMatchers.any(Stack.class)))
                .thenReturn(Mono.empty());

    }



    @Test
    @DisplayName("deve criar uma stack com sucesso")
    public void deveCriarUmaStackComSucesso(){
        StepVerifier.create(stackService.salvarStack(StackMock.stackMock()))
                .expectSubscription()
                .expectNext(StackMock.stackMock())
                .verifyComplete();

    }

    @Test
    @DisplayName("deve excluir uma stack pelo id com sucesso")
    public void deveExcluirUmaStackPeloIdComSucesso(){
        StepVerifier.create(stackService.excluirPeloId(StackMock.stackMock().id()))
                .expectSubscription()
                .verifyComplete();

    }

    @Test
    @DisplayName("deve excluir uma stack pelo id com sucesso")
    public void deveBuscarUmaStackPeloIdComSucesso(){
        StepVerifier.create(stackService.buscarPeloId("5d0d6f02-2933-4aff-8219-d7dfb3ae083f"))
                .expectSubscription()
                .expectNext(StackMock.stackMock())
                .verifyComplete();
    }


    @Test
    @DisplayName("deve buscar uma stack pelo id com erro")
    public void deveBuscarUmaStackPeloIdComErro(){

        BDDMockito.when(stackRepository.findById(ArgumentMatchers.anyString()))
                        .thenReturn(Mono.empty());
        StepVerifier.create(stackService.buscarPeloId(ArgumentMatchers.anyString()))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }
}
