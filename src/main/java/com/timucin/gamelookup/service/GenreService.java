package com.timucin.gamelookup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timucin.gamelookup.domain.Genre;
import com.timucin.gamelookup.repository.GenreRepository;

@Service
public class GenreService {
	
private final GenreRepository genreRepository;
	
	@Autowired
	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}
	
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}
	
	public Optional<Genre> findById(Long id) {
		return genreRepository.findById(id);
	}
	
	public Genre save(Genre genre) {
		return genreRepository.save(genre);
	}
	
	public void delete(Genre genre) {
		genreRepository.delete(genre);
	}
	
	public void deleteById(Long id) {
		genreRepository.deleteById(id);
	}

}
