package com.diagne.angular.repositories;

import com.diagne.angular.entities.AnimeCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnime extends JpaRepository<AnimeCharacter, Long> {
}
