package com.baozistore.controller;

import com.baozistore.model.Pedido;
import com.baozistore.repository.ClienteRepository;
import com.baozistore.repository.PedidoRepository;
import com.baozistore.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // POST - criar
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Pedido pedido) {
        if (!clienteRepository.existsById(pedido.getClienteId())) {
            return ResponseEntity.badRequest().body("Cliente com id " + pedido.getClienteId() + " nao encontrado.");
        }
        if (!produtoRepository.existsById(pedido.getProdutoId())) {
            return ResponseEntity.badRequest().body("Produto com id " + pedido.getProdutoId() + " nao encontrado.");
        }
        Pedido salvo = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET - listar todos
    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // GET /{id} - consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /{id} - atualizar (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido dadosAtualizados) {
        if (!clienteRepository.existsById(dadosAtualizados.getClienteId())) {
            return ResponseEntity.badRequest().body("Cliente com id " + dadosAtualizados.getClienteId() + " nao encontrado.");
        }
        if (!produtoRepository.existsById(dadosAtualizados.getProdutoId())) {
            return ResponseEntity.badRequest().body("Produto com id " + dadosAtualizados.getProdutoId() + " nao encontrado.");
        }
        return pedidoRepository.findById(id)
                .map(pedidoExistente -> {
                    pedidoExistente.setClienteId(dadosAtualizados.getClienteId());
                    pedidoExistente.setProdutoId(dadosAtualizados.getProdutoId());
                    pedidoExistente.setQuantidade(dadosAtualizados.getQuantidade());
                    Pedido atualizado = pedidoRepository.save(pedidoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /{id} - apagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
