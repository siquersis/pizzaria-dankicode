package br.com.pizzariadankcode.cursoevandro.pizza;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    private final ModelMapper modelMapper;

    public PizzaDTO criarPizza(PizzaDTO dto) {
        Pizza pizza = modelMapper.map(dto, Pizza.class);
        pizzaRepository.save(pizza);

        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public Page<PizzaDTO> buscarTodos(Pageable paginacao) {
        return pizzaRepository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PizzaDTO.class));
    }

    public PizzaDTO buscaPorId(Long id) {
        Pizza pizza = pizzaRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);


        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public PizzaDTO atualizarPizza(Long id,
                                   @Valid PizzaDTO dto) {
        Pizza pizza = modelMapper.map(dto, Pizza.class);
        pizza.setId(id);
        pizza = pizzaRepository.save(pizza);

        return modelMapper.map(pizza, PizzaDTO.class);
    }

    public void deletar(@NotNull Long id) {
        pizzaRepository.deleteById(id);
    }
}
