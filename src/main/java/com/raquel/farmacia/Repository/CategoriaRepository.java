package com.raquel.farmacia.Repository;

	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raquel.farmacia.model.Categoria;

	public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	    List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
	}
