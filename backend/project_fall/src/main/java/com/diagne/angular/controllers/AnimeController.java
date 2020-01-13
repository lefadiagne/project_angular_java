package com.diagne.angular.controllers;

import com.diagne.angular.repositories.IAnime;
import com.diagne.angular.entities.AnimeCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/animes")
public class AnimeController {

    @Autowired
    private IAnime animeRepository;

    @GetMapping("/")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(animeRepository.findAll());
    }
    @GetMapping("/{idanime}")
    public ResponseEntity findCharacterById(@PathVariable(name = "idAnime") Long idAnime) {
        if (idAnime == null) {
            return ResponseEntity.badRequest().body(("cannot find character with null id"));
        }
        AnimeCharacter character = animeRepository.getOne((idAnime));
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((character));
    }

    @PostMapping("/")
    public ResponseEntity createdCharacter(@RequestBody AnimeCharacter character ) {
            if (character == null ){
                return ResponseEntity.badRequest().body("cannot create character with empty fields");
        }
            return ResponseEntity.ok(animeRepository.save(character));
    }

    @DeleteMapping("/idAnime")
    public ResponseEntity deletedCharacter(@PathVariable(name = "idAnime") Long idCharacter){
        if(idCharacter == null){
            return ResponseEntity.badRequest().body("cannot remove charachter with null id");
        }
        AnimeCharacter character = animeRepository.getOne((idCharacter));
        if (character == null){
            return ResponseEntity.notFound().build();
        }
        animeRepository.delete(character);
        return ResponseEntity.ok("Character removed with success");

    }

    @GetMapping("/share/{idAnime}/{isShared}")
    public ResponseEntity shareCharacter(@PathVariable(name = "idAnime") Long idCharacter, @PathVariable(name = "isSahred") boolean isShared ){
        if (idCharacter == null ){
            return ResponseEntity.badRequest().body("cannot remove charachter with null id");
        }
        AnimeCharacter character = animeRepository.getOne((idCharacter));
        if (character == null){
            return ResponseEntity.notFound().build();
        }

        character.setShared(isShared);
        return ResponseEntity.ok(animeRepository.save(character));
        //character.setShared(true);
    }
}
