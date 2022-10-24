package com.algaworks.thymeleaf.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.thymeleaf.model.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
    
    private static final List<Produto> DADOS = new ArrayList<>(Arrays.asList(
            new Produto(1L, "5 dias de passeio em Maceió - AL com voo ida e volta", new BigDecimal(699.99)),
            new Produto(2L, "7 dias de passeio em Paraty - RJ com voo ida e volta", new BigDecimal(947.50)),
            new Produto(3L, "7 dias de passeio em Fortaleza - CE com voo ida e volta", new BigDecimal(599.00)),
            new Produto(4L, "7 dias de passeio em Capitólio - MG com voo ida e volta", new BigDecimal(640.99)),
            new Produto(5L, "5 dias de passeio em São Paulo - SP com voo ida e volta", new BigDecimal(549.00)),
            new Produto(6L, "7 dias de passeio em Curitiba - PR com voo ida e volta", new BigDecimal(759.60)))); 
    
    @GetMapping
    public ModelAndView lista(Produto produto) {
        ModelAndView model = new ModelAndView("/produto-lista.html");
        
        List<Produto> lista = DADOS.stream()
                .filter(p -> produto.getId() == null || produto.getId().equals(p.getId()))
                .filter(p -> StringUtils.isEmpty(produto.getNome()) || p.getNome().startsWith(produto.getNome()))
                .collect(Collectors.toList());
        
        model.addObject("produtos", lista);
        
        return model;
    }
}