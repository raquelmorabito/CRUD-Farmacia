package com.raquel.farmacia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raquel.farmacia.model.Produto;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	
}


